/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import ecouteurs.EcouteurAnnulerSousMenuNouvellePartie;
import ecouteurs.EcouteurCreerReseau;
import ecouteurs.EcouteurRejoindreReseau;

/**
 *
 * @author jox
 */
public class Fen_Reseau {

	public Fen_Dialog f;
	public Fen_jeu fj;
	public JButton facile, moyen,creeer, rejoindree;
	public JPanel p;
	public JTextField joueur, AIP, port1, port2;
	public JLabel nom, niveau, taille, creer, rejoindre, IP, nport, nuport;
	public JSeparator ligne1, ligne2, ligne3;
	public EcouteurRejoindreReseau ERejoindre;
	public EcouteurCreerReseau ECreer;
	public boolean serveur_cree;
	public EcouteurAnnulerSousMenuNouvellePartie a;
	public Fen_Reseau(boolean sc, Fen_jeu fp){
		fj=fp;
		serveur_cree=sc;
		f = new Fen_Dialog(fp.f, "Jouer en réseau", true);
		f.setSize(300,400);
		//        f.setBounds(720, 410, 400, 400);
		p = new JPanel() {

			public void paintComponent(Graphics g) {
				Image background = new ImageIcon("images/metal.png").getImage();
				g.drawImage(background, 0, 0, null);
				repaint();
			}
		};

		nom=new JLabel("Nom du Joueur");
		joueur = new JTextField(System.getProperty("user.name"));
		ligne1=new JSeparator();
		creer=new JLabel("Créer une partie");
		creer.setFont(new Font(null, Font.BOLD, 16));
		taille=new JLabel("Taille du plateau de jeu");
		taille.setForeground(Color.blue);


		JRadioButton six = new JRadioButton("Petit");
		six.setMnemonic(KeyEvent.VK_S);
		six.setActionCommand("petit");

		six.setFocusPainted(false);
		six.setBorderPainted(false);
		six.setContentAreaFilled(false);

		JRadioButton huit = new JRadioButton("Grand");
		huit.setMnemonic(KeyEvent.VK_H);
		huit.setActionCommand("grand");
		huit.setFocusPainted(false);
		huit.setBorderPainted(false);
		huit.setContentAreaFilled(false);

		if(fj.taille_plateau_pref == 11)
		{
			six.setSelected(true);
		}
		else{
			huit.setSelected(true);
		}

		ButtonGroup group = new ButtonGroup();
		group.add(six);
		group.add(huit);        




		nport=new JLabel("Numéro de port");
		port1=new JTextField("8080");

		//bouton creer partie
		ECreer=new EcouteurCreerReseau(f,group,fj);
		f.creer.addMouseListener(ECreer);

		ligne2=new JSeparator();
		rejoindre=new JLabel("Rejoindre une partie");
		rejoindre.setFont(new Font(null, Font.BOLD, 16));
		IP=new JLabel("Adresse IP à rejoindre");
		AIP=new JTextField();




		nuport=new JLabel("Numéro de port");
		port2=new JTextField("8080");


		//bouton rejoindre partie
		ERejoindre=new EcouteurRejoindreReseau(AIP,f,this,serveur_cree,fj);
		f.rejoindre.addMouseListener(ERejoindre);

		ligne3=new JSeparator();


		//bouton retour
		a = new EcouteurAnnulerSousMenuNouvellePartie(fj, f);
		f.retour.addMouseListener(a);


		nom.setBounds(40, 20, 100, 20);
		joueur.setBounds(160,20,100,20);

		ligne1.setBounds(30, 60, 240, 10);
		creer.setBounds(85, 70, 200, 20);
		taille.setBounds(80, 100, 150, 20);
		six.setBounds(60, 130, 100, 20);
		huit.setBounds(190, 130, 100, 20);

		f.creer.setBounds(100, 160, 100, 20);

		ligne2.setBounds(30, 200, 240, 10);
		rejoindre.setBounds(75, 210, 250, 20);
		IP.setBounds(10, 250, 150, 20);
		AIP.setBounds(150, 250, 130, 20);
		f.rejoindre.setBounds(100, 290, 100, 20);

		ligne3.setBounds(30, 320, 240, 10);

		f.retour.setBounds(100, 340, 100, 20);

		p.add(nom);
		p.add(joueur);

		p.add(ligne1);
		p.add(creer);
		p.add(taille);
		p.add(six);
		p.add(huit);
		// p.add(nport);
		//  p.add(port1);
		p.add(f.creer);

		p.add(ligne2);
		p.add(rejoindre);
		p.add(IP);
		p.add(AIP);
		// p.add(nuport);
		// p.add(port2);
		p.add(f.rejoindre);
		p.add(ligne3);

		p.add(f.retour);


		p.setLayout(null);
		f.add(p);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation((screen.width - f.getSize().width)/2,(screen.height - f.getSize().height)/2);

		f.setVisible(true);
		f.setDefaultCloseOperation(Fen_Dialog.DISPOSE_ON_CLOSE);
	}


}
