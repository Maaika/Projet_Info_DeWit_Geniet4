package FonctionModifDonneesEnseignant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import AppilBureautique.FenAdmin;
import ConnectionJdbc.ConnectionJdbc;
import Donnees.College;
import Donnees.Enseignant;
import User.UserAdmin;
/**
 * Cette fen�tre permet de choisir le nouveau coll�ge principal de l'enseignant. 
 * Cette classe h�rite de JFrame qui impl�mente ActionListener.
 * 
 * @author Apolline De Wit, Florent Geniet
 */
public class ModifCollegeS extends JFrame implements ActionListener{
	/*
	 *  attributs
	 */
	// m�me construction que les classes pr�c�dentes
	public static final long serialVersionUID = 1L;
	public JPanel panModifCollS = new JPanel();
	public JLabel label = new JLabel();
	
	public UserAdmin admin;
	public Enseignant ens;
	private JComboBox<String> combo ; // Ajout d'un combobox
	
	
	private JButton bValider = new JButton ("Valider");
	
	private JPanel boutonPaneValider = new JPanel();
	
	private JButton bRetour = new JButton("Retour");
	private JPanel boutonPaneRetour = new JPanel();
	
	/**
	 * Ce constructeur permet de choisir le coll�ge secondaire d'un enseignant
	 * @param admin : UserAdmin de l'utilisateur de la session
	 * @param ens : enseignant dont on veut modifier une information
	 */
	public ModifCollegeS(UserAdmin admin, Enseignant ens) {
		this.ens = ens;
		this.admin = admin;
		// Parametres de la fenetre
		this.setTitle("Modifier le coll�ge secondaire"); // Modifier le titre de la fenetre
		this.setSize(400,200); // Modifier la taille
		this.setResizable(false); // taille non modifiable par l'utilisateur
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Un clic sur croix entraine une fermeture de la fenetre
		this.setLocationRelativeTo(null); // Centrer la fenetre par rapport a l'ecran de l'ordi
		panModifCollS.setBackground(Color.LIGHT_GRAY); // on d�finit sa couleur de fond
		this.setContentPane(panModifCollS); // on pr�vient notre JFrame que pan sera son content pane

		
		// Combobox
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		this.combo = new JComboBox<String>(model);
		
		Connection conn = ConnectionJdbc.getInstance();
		
		try {
			Statement st = conn.createStatement();
	    	ResultSet result = st.executeQuery("SELECT * FROM college");
	    	
	    	while(result.next()) {
	    		combo.addItem(result.getInt("numero_academique")+":"+result.getString("nom"));
	    	}
	    	result.close();
	    	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					label.setText("Vous avez s�lectionn� " + e.getItem());
				}
			}
		});
		panModifCollS.add(combo);
		
		
		boutonPaneValider.add(bValider);
		panModifCollS.add(boutonPaneValider);
		bValider.addActionListener(this);
		
		boutonPaneRetour.add(bRetour);
		panModifCollS.add(boutonPaneRetour);
		bRetour.addActionListener(this);
		
		
	    this.setVisible(true);
	}

	/**
	 *  Cette m�thode d�finit ce qu'il se passe lorsque l'on clique sur les boutons. 
	 * 	Le bouton valider confirme le choix du coll�ge secondaire de l'enseignant. 
	 * Le bouton retour revient � la page pr�c�dente.
	 *  @param e : il s'agit d'une action effectu�e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(combo.getSelectedItem().toString());
		int college = Integer.parseInt(combo.getSelectedItem().toString().split(":")[0]); 
	    College coll = new College(college);
		if(e.getSource() == bValider) {
			dispose();
			this.admin.modifierCollegeSecondaireEnseignant(coll, this.ens);
			FenAdmin fenAd = new FenAdmin(this.admin.id, this.admin.getMdp());
			fenAd.setVisible(true);
		}
		if(e.getSource()==bRetour) {
			dispose();
			FenAdmin fenAd = new FenAdmin(this.admin.id, this.admin.getMdp());
			fenAd.setVisible(true);
		}
		
	}

}