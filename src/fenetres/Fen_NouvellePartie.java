/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;

import java.awt.*;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ecouteurs.EcouteurMPJcJ;
import ecouteurs.EcouteurMPReseau;
import ecouteurs.EcouteurMPRetourJeu;
import ecouteurs.EcouteurMPSolo;

/**
 *
 * @author jox
 */
public class Fen_NouvellePartie {
	public Fen_Dialog f;
	public Fen_jeu fj;
	public ImageIcon img_solo, img_jcj, img_reseau, img_retour;
	public JButton solo, jcj, reseau, retour;
	public EcouteurMPSolo PSolo;
	public EcouteurMPReseau PReseau;
	public EcouteurMPJcJ PJcJ;
	public EcouteurMPRetourJeu R;
	public JPanel p;
	public JFrame fen;


	public Fen_NouvellePartie(Fen_jeu fenetre) throws IOException{

		fj=fenetre;
		f = new Fen_Dialog(fenetre.f, "Nouvelle Partie", true);

		f.setSize(400,325);
		p=new JPanel(){
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon("images/metal.png").getImage();
				g.drawImage(background, 0, 0, null);
				repaint();
			}
		};

		// suppression d'un chat deja existant.


		//bouton solo
		img_solo = new ImageIcon("images/pve_pas_clique.png");
		solo = new JButton();
		solo.setIcon(img_solo);
		solo.setFocusPainted(false);
		solo.setBorderPainted(false);
		solo.setContentAreaFilled(false);
		PSolo =new EcouteurMPSolo(fenetre,this);
		solo.addMouseListener(PSolo);

		//bouton jcj
		img_jcj = new ImageIcon("images/pvp_pas_clique.png");
		jcj = new JButton();
		jcj.setIcon(img_jcj);
		jcj.setFocusPainted(false);
		jcj.setBorderPainted(false);
		jcj.setContentAreaFilled(false);
		PJcJ=new EcouteurMPJcJ(fenetre,this);
		jcj.addMouseListener(PJcJ);

		//bouton reseau
		img_reseau = new ImageIcon("images/reseau_pas_clique.png");
		reseau = new JButton();
		reseau.setIcon(img_reseau);
		reseau.setFocusPainted(false);
		reseau.setBorderPainted(false);
		reseau.setContentAreaFilled(false);
		PReseau=new EcouteurMPReseau(this,fenetre);
		reseau.addMouseListener(PReseau);


		//bouton retour
		img_retour = new ImageIcon("images/retour_jeu_pas_clique.png");
		retour = new JButton();
		retour.setIcon(img_retour);
		retour.setFocusPainted(false);
		retour.setBorderPainted(false);
		retour.setContentAreaFilled(false);
		R = new EcouteurMPRetourJeu(this, fj);
		retour.addMouseListener(R);


		solo.setBounds(60, 10, 280, 60);
		jcj.setBounds(60, 80, 280, 60);
		reseau.setBounds(60, 150, 280, 60);
		retour.setBounds(60, 220, 280, 60);

		p.add(solo);
		p.add(jcj);
		p.add(reseau);
		p.add(retour);
		p.setLayout(null);
		f.add(p);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation((screen.width - f.getSize().width)/2,(screen.height - f.getSize().height)/2);

		f.setVisible(true);
		f.setDefaultCloseOperation(Fen_Dialog.DISPOSE_ON_CLOSE);
	}


}
