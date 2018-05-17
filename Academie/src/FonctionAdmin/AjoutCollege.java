package FonctionAdmin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AppilBureautique.FenAdmin;
import User.UserAdmin;
/**
 * Cette fen�tre permet � un administrateur d'ajouter un coll�ge � la base de donn�es 
 * Cette classe h�rite de JFrame et impl�mente ActionListener. 
 * 
 * @author Apolline De Wit, Florent Geniet
 */
public class AjoutCollege extends JFrame implements ActionListener{

	/*
	 * Attributs de la fonction cr�er un enseignant
	 */
	private static final long serialVersionUID = 1L;		// attribut h�rit� de JFrame
	private UserAdmin admin;		// attribut UserAdmin de l'utilisateur qui se connecte
	private JPanel panColl = new JPanel();		// instantiation d'un panneau de fond
	
	// D�finition de textes et de zones de texte :
	private JLabel nom = new JLabel("Nom du college: ");
	private JLabel adresse = new JLabel("Adresse : ");
	private JLabel siteInternet = new JLabel("SiteInternet : ");
	private JTextField nomColl = new JTextField();
	private JTextField adresseColl = new JTextField();
	private JTextField siteInternetColl = new JTextField();
	
	// D�finition d'un bouton de retour � la fen�tre pr�c�dante et d'un bouton valider ainsi que des panneaux les comprenant :
	private JButton bRetour = new JButton("Retour");
	private JButton valider = new JButton();
	private JPanel boutonPaneRetour = new JPanel();
	private JPanel boutonPane = new JPanel();
	
	
	/**
	 * Cette m�thode permet � l'administrateur d'ajouter un coll�ge � la base de donn�es 
	 * @param admin : UserAdmin qui vient de se connecter
	 */
	public AjoutCollege(UserAdmin admin) {
		this.admin = admin;
		
		// Param�tres de la fen�tre
		this.setTitle("Ajouter un coll�ge � la base de donn�es"); 		// Modifier le titre de la fenetre
		this.setSize(400,200); 		// Modifier la taille
		this.setResizable(false);		 // taille non modifiable par l'utilisateur
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// Un clic sur croix entraine une fermeture de la fenetre
		this.setLocationRelativeTo(null); 		// Centrer la fenetre par rapport a l'ecran de l'ordi
		panColl.setBackground(Color.lightGray); 		// on d�finit sa couleur de fond
		this.setContentPane(panColl);		 // On pr�vient notre JFrame que pan sera son content pane
		
		// On d�finit la taille des zones de texte
		nomColl.setColumns(10);
		adresseColl.setColumns(10);
		siteInternetColl.setColumns(10);
		
		// On ajoute les boutons aux panneaux :
		boutonPaneRetour.add(bRetour);
		boutonPane.add(valider);
		
		// On ajoute les panneaux au fond :
		panColl.add(nom);
		panColl.add(nomColl);
		panColl.add(adresse);
		panColl.add(adresseColl);
		panColl.add(siteInternet);
		panColl.add(siteInternetColl);
		panColl.add(boutonPaneRetour);
		panColl.add(boutonPane);
		
	  
	    // Action Listener permet de d�finir l'action avec la m�thode impl�ment�e quand l'on clique sur les boutons
	    bRetour.addActionListener(this);
	    valider.addActionListener(this);
	    
		// On rend la fen�tre visible
	    this.setVisible(true);
	}

	/**
	 *  Cette m�thode d�finit ce qu'il se passe lorsque l'on clique sur les boutons. 
	 *  On valide l'ajout d'un coll�ge � la base de donn�es ou l'on retourne � la page pr�c�dente. 
	 *  @param e : il s'agit d'une action effectu�e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == valider) {
			dispose();
			String nom = nomColl.getText();
		    String adresse = adresseColl.getText();
		    String siteInternet = siteInternetColl.getText(); 
			this.admin.creerCollege(nom, adresse, siteInternet);
		}
		if(e.getSource()==bRetour) {
			dispose();
			FenAdmin fenAd = new FenAdmin(this.admin.id, this.admin.getMdp());
			fenAd.setVisible(true);
		}
		
	}



}
