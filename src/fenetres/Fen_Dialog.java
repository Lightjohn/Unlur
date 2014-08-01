/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;

/**
 *
 * @author desgrour
 */

import javax.swing.*;

public class Fen_Dialog extends JDialog {

	public JButton accepter, annuler, oui, non, retour;
	public JButton go, facile, moyen, creer, rejoindre;
	public JButton recommencer, quitter;
	public JButton menu;
	public ImageIcon img_accepter, img_annuler, img_oui, img_non, img_retour;
	public ImageIcon img_go, img_facile, img_moyen, img_creer, img_rejoindre;
	public ImageIcon img_recommencer, img_quitter;
	public ImageIcon img_menu;

	public Fen_Dialog(JFrame parent, String title, boolean modal){
		//On appelle le construteur de JDialog correspondant
		super(parent, title, modal);
		//La position
		this.setLocationRelativeTo(null);
		//La boîte ne devra pas être redimensionnable
		this.setResizable(false);

		//bouton accepter
		img_accepter = new ImageIcon("images/accepter_pas_clique.png");
		accepter = new JButton();
		accepter.setName("Accepter");
		accepter.setIcon(img_accepter);
		accepter.setFocusPainted(false);
		accepter.setBorderPainted(false);
		accepter.setContentAreaFilled(false);

		//bouton annuler
		img_annuler = new ImageIcon("images/annuler_pas_clique.png");
		annuler = new JButton();
		annuler.setName("Annuler");
		annuler.setIcon(img_annuler);
		annuler.setFocusPainted(false);
		annuler.setBorderPainted(false);
		annuler.setContentAreaFilled(false);

		//bouton oui
		img_oui = new ImageIcon("images/oui_pas_clique.png");
		oui = new JButton();
		oui.setName("Oui");
		oui.setIcon(img_oui);
		oui.setFocusPainted(false);
		oui.setBorderPainted(false);
		oui.setContentAreaFilled(false);

		//bouton non
		img_non = new ImageIcon("images/non_pas_clique.png");
		non = new JButton();
		non.setName("Non");
		non.setIcon(img_non);
		non.setFocusPainted(false);
		non.setBorderPainted(false);
		non.setContentAreaFilled(false);

		//bouton retour
		img_retour = new ImageIcon("images/retour_pas_clique.png");
		retour = new JButton();
		retour.setIcon(img_retour);
		retour.setFocusPainted(false);
		retour.setBorderPainted(false);
		retour.setContentAreaFilled(false);

		//bouton jouer
		img_go = new ImageIcon("images/jouer_pas_clique.png");
		go = new JButton();
		go.setIcon(img_go);
		go.setFocusPainted(false);
		go.setBorderPainted(false);
		go.setContentAreaFilled(false);

		//bouton facile
		img_facile = new ImageIcon("images/facile_pas_clique.png");
		facile = new JButton();
		facile.setName("Facile");
		facile.setIcon(img_facile);
		facile.setFocusPainted(false);
		facile.setBorderPainted(false);
		facile.setContentAreaFilled(false);

		//bouton moyen
		img_moyen = new ImageIcon("images/moyen_pas_clique.png");
		moyen = new JButton();
		moyen.setName("Moyen");
		moyen.setIcon(img_moyen);
		moyen.setFocusPainted(false);
		moyen.setBorderPainted(false);
		moyen.setContentAreaFilled(false);

		//bouton creer
		img_creer = new ImageIcon("images/creer_pas_clique.png");
		creer = new JButton();
		creer.setIcon(img_creer);
		creer.setFocusPainted(false);
		creer.setBorderPainted(false);
		creer.setContentAreaFilled(false);

		//bouton rejoindre
		img_rejoindre = new ImageIcon("images/rejoindre_pas_clique.png");
		rejoindre = new JButton();
		rejoindre.setIcon(img_rejoindre);
		rejoindre.setFocusPainted(false);
		rejoindre.setBorderPainted(false);
		rejoindre.setContentAreaFilled(false);

		//bouton recommencer
		img_recommencer = new ImageIcon("images/recommencer_pas_clique.png");
		recommencer = new JButton();
		recommencer.setName("Recommencer");
		recommencer.setIcon(img_recommencer);
		recommencer.setFocusPainted(false);
		recommencer.setBorderPainted(false);
		recommencer.setContentAreaFilled(false);

		//bouton quitter
		img_quitter = new ImageIcon("images/quitter_pas_clique.png");
		quitter = new JButton();
		quitter.setName("Quitter");
		quitter.setIcon(img_quitter);
		quitter.setFocusPainted(false);
		quitter.setBorderPainted(false);
		quitter.setContentAreaFilled(false);

		//bouton menu
		img_menu = new ImageIcon("images/menu_pas_clique.png");
		menu = new JButton();
		menu.setName("Menu");
		menu.setIcon(img_menu);
		menu.setFocusPainted(false);
		menu.setBorderPainted(false);
		menu.setContentAreaFilled(false);
	}


}

