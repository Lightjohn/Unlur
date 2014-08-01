/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;

import fenetres.Fen_jeu;

/**
 *
 * @author Adri
 */
public class Didacticiel implements Runnable{

	public Fen_jeu f;
	public int j_courant;
	public int temps;
	public int bulle_actuelle;
	public boolean clic;



	public Didacticiel (Fen_jeu fen) throws IOException
	{
		f = fen;
		f.activer_tuto = true;
		f.it_tuto.setEnabled(false);
		j_courant = f.joueur_courant;
		temps = 5000;
		clic = false;
	}

	private void bulle_1() throws InterruptedException
	{

		f.tuto_g.setEnabled(false);
		bulle_actuelle = 1;
		f.aire.img_fleche = f.aire.img_fh;
		f.aire.fleche_x = 35;
		f.aire.fleche_y = 15;
		f.msg_tuto.setText("Bienvenue dans Unlur !\nJe m'apelle Luxo. Cliquez sur Tutoriel dans le menu Aide pour m'appeler de nouveau");
		f.aire.repaint();


	}

	private void bulle_2() throws InterruptedException
	{
		f.tuto_g.setEnabled(true);
		bulle_actuelle = 2;
		f.aire.fleche_x = -150;
		f.aire.fleche_y = -150;
		f.msg_tuto.setText("\nUne partie se déroule en deux temps : ");
		f.aire.repaint();
	}

	private void bulle_3() throws InterruptedException
	{
		bulle_actuelle = 3;
		f.msg_tuto.setText("D'abord, chaque joueur place,\nà tour de rôle une boule noire sur le plateau de jeu excepté\nsur le bord.");
		f.aire.repaint();
	}

	private void bulle_4() throws InterruptedException
	{
		if(f.N == 15)
		{f.aire.plateau_x = 205;
		f.aire.plateau_y = 77;}
		else
		{
			f.aire.plateau_x = 239;
			f.aire.plateau_y = 79;
		}
		bulle_actuelle = 4;
		f.aire.img_plateau_tuto = f.aire.img;
		f.msg_tuto.setText("Ensuite, vous pouvez soit poser une boule noire soit choisir les noirs forçant ainsi l'adversaire à jouer blanc.");
		f.aire.repaint();
	}

	private void bulle_5() throws InterruptedException
	{
		bulle_actuelle = 5;
		if(f.N == 15)
		{
			f.aire.plateau_x = 205;
			f.aire.plateau_y = 77;
			f.aire.img_plateau_tuto = f.aire.img_8_noir;
		}

		if(f.N == 11)
		{
			f.aire.plateau_x = 239;
			f.aire.plateau_y = 79;
			f.aire.img_plateau_tuto = f.aire.img_6_noir;
		}

		f.msg_tuto.setText("Si vous jouez noir, vous devez joindre trois côtés de couleur différente et non adjacente.");
		f.aire.repaint();
	}

	private void bulle_6() throws InterruptedException
	{
		bulle_actuelle = 6;
		if(f.N == 15)
		{
			f.aire.img_plateau_tuto = f.aire.img_8_blanc;
		}

		if(f.N == 11)
		{
			f.aire.img_plateau_tuto = f.aire.img_6_blanc;
		}
		f.aire.fleche_x = -250;
		f.aire.fleche_y = -250;
		f.msg_tuto.setText("Si vous jouez blanc, vous devez relier deux mêmes couleurs.");
		f.aire.repaint();
	}

	private void bulle_7() throws InterruptedException
	{
		bulle_actuelle = 7;
		if(f.N == 15){
			f.aire.fleche_x = 150;
			f.aire.fleche_y = 280;
			f.aire.tuto_x = 680;
			f.aire.tuto_y = 460;
			f.aire.img_tuto = f.aire.img_didac_1;
			f.aire.img_bulle = f.aire.img_bulle_2;
			f.aire.img_fleche = f.aire.img_135;
			f.aire.img_plateau_tuto = f.aire.img;
		}

		if(f.N == 11)
		{
			f.aire.fleche_x = 10;
			f.aire.fleche_y = 200;
			f.aire.tuto_x = 300;
			f.aire.tuto_y = 380;
			f.aire.img_fleche = f.aire.img_45;
			f.aire.img_plateau_tuto = f.aire.img;
			f.aire.img_bulle = f.aire.img_bulle_2;
			f.aire.img_tuto = f.aire.img_didac_1;
		}

		f.msg_tuto.setText("Les flêches bleue indiquent le joueur courant ainsi que sa couleur.");
		f.aire.repaint();
	}

	private void bulle_8() throws InterruptedException
	{
		bulle_actuelle = 8;
		if(f.N == 15){
			f.aire.tuto_x = 380;
			f.aire.tuto_y = 460;
			f.aire.fleche_x = 735;
			f.aire.fleche_y = 235;
		}
		if(f.N == 11)
		{
			f.aire.tuto_x = 10;
			f.aire.tuto_y = 380;
			f.aire.fleche_x = 650;
			f.aire.fleche_y = 180;
			f.aire.bulle_x = 80;
			f.msg_tuto.setLocation(125, 280);
			f.tuto_g.setBounds(80,300,30,30);
			f.tuto_d.setBounds(350,300,30,30);
		}
		f.aire.img_fleche = f.aire.img_45;
		f.aire.img_bulle = f.aire.img_bulle_1;
		f.aire.img_tuto = f.aire.img_didac_2;
		f.msg_tuto.setText("A tout moment il vous est possible de rejouer votre coup grâce aux boutons Annuler et Refaire.");
		f.aire.repaint();
	}

	private void bulle_9() throws InterruptedException
	{
		bulle_actuelle = 9;
		if(f.N == 15){
			f.aire.fleche_x = 750;
			f.aire.fleche_y = 330;}
		if(f.N == 11)
		{
			f.aire.fleche_x = 670;
			f.aire.fleche_y = 270;
		}
		f.aire.img_fleche = f.aire.img_45;
		f.msg_tuto.setText("Si vous êtes bloqué je peux vous conseiller. Pour cela, cliquez sur moi.");
		f.aire.repaint();
	}

	private void bulle_10() throws InterruptedException
	{
		//a supprimet et mettre fleche dans bulle explicative 4
		bulle_actuelle = 10;
		if(f.N == 15)
		{
			f.aire.tuto_x = 380;
			f.aire.tuto_y = 460;
			f.aire.fleche_x = 830;
			f.aire.fleche_y = 470;
		}

		if(f.N == 11)
		{
			f.aire.fleche_x = 750;
			f.aire.fleche_y = 400;
			f.aire.tuto_x = 10;
			f.aire.tuto_y = 380;
		}

		f.tuto_d.setIcon(f.img_fd);
		f.tuto_d.setName("suivant");
		f.aire.img_fleche = f.aire.img_fh;
		f.aire.img_bulle = f.aire.img_bulle_1;
		f.aire.img_tuto = f.aire.img_didac_2;
		f.msg_tuto.setText("Cliquez sur le bouton choisir\nles noirs pour récupérer les noirs et donner les blancs à votre adversaire.");
		f.aire.repaint();
	}

	private void bulle_11() throws InterruptedException
	{
		f.tuto_d.setEnabled(true);
		bulle_actuelle = 11;
		f.aire.fleche_x = 35;
		f.aire.fleche_y = 15;
		if(f.N == 15){
			f.aire.tuto_x = 680;
			f.aire.tuto_y = 460;}
		if(f.N == 11){
			f.aire.tuto_x = 300;
			f.aire.tuto_y = 380;}

		f.aire.img_bulle = f.aire.img_bulle_2;
		f.aire.img_tuto = f.aire.img_didac_1;
		f.msg_tuto.setText("Pour plus de détails sur les regles du Unlur, cliquez sur Regles dans le menu d'aide.");
		f.aire.repaint();
	}

	private void bulle_12() throws InterruptedException
	{
		if(f.N == 15)
		{
			f.aire.tuto_x = 680;
			f.aire.tuto_y = 460;
			//f.aire.fleche_x = 830;
			//f.aire.fleche_y = 470;
		}

		if(f.N == 11)
		{
			//f.aire.fleche_x = 750;
			//f.aire.fleche_y = 400;
			f.aire.tuto_x = 300;
			f.aire.tuto_y = 380;
		}

		f.tuto_d.setName("retour");
		f.tuto_d.setIcon(f.img_croix);
		f.msg_tuto.setText("Pour quitter le tutoriel, cliquez sur la croix rouge");
		f.aire.repaint();
	}

	private void tempo() throws InterruptedException
	{
		if(clic == false)
		{
			Thread.sleep(temps);
			if(clic == false){
				f.msg_tuto.setText("");
				f.aire.repaint();}
		}
	}

	public void fin_tuto() throws FileNotFoundException, IOException
	{
		f.msg_tuto.setBounds(-5, -5, 0, 0);
		f.joueur_courant = j_courant;
		f.tuto_d.setName("suivant");
		f.tuto_d.setVisible(false);
		f.tuto_g.setVisible(false);
		f.tuto_g.setSize(0,0);
		f.tuto_d.setSize(0,0);
		f.tuto_g.setLocation(0,0);
		f.tuto_d.setLocation(0,0);
		if (f.mode_IA == null) {
			f.lab_j2.setText(f.nom_joueur2);
		} else {
			f.lab_j2.setText("Luxo");
		}
		f.activer_tuto = false;
		f.it_tuto.setEnabled(true);
		f.aire.repaint();
		f.tuto_d.setIcon(f.img_fd);
		f.modifier_preferences(2,"false");
	}

	public void lancer_bulle(int num_bulle) throws InterruptedException
	{

		// System.out.println("Lancer la bulle "+num_bulle);
		switch (num_bulle)
		{
		case 1 : bulle_1();   break;
		case 2 : bulle_2();   break;
		case 3 : bulle_3();   break;
		case 4 : bulle_4();   break;
		case 5 : bulle_5();   break;
		case 6 : bulle_6();   break;
		case 7 : bulle_7();   break;
		case 8 : bulle_8();   break;
		case 9 : bulle_9();   break;
		case 10 : bulle_10(); break;
		case 11 : bulle_11(); break;
		case 12 : bulle_12(); break;
		default : System.out.println("defaut switch :( !");
		}
	}

	public void run()
	{
		try {
			f.joueur_courant = 1;
			f.msg_tuto = new JTextArea();
			f.msg_tuto.setBackground(new Color(0, 0, 0, 0));
			f.msg_tuto.setLineWrap(true);
			f.msg_tuto.setWrapStyleWord(true);
			f.tuto_g.setVisible(true);
			f.tuto_d.setVisible(true);
			f.tuto_d.setName("suivant");
			f.tuto_g.setName("precedent");
			if (f.N == 15) {
				f.msg_tuto.setSize(230, 110);
				f.tuto_g.setBounds(460, 380, 30, 35);
				f.tuto_d.setBounds(730, 380, 30, 35);
			}
			if (f.N == 11) {
				f.msg_tuto.setSize(230, 120);
				f.tuto_g.setBounds(80,300,30,30);
				f.tuto_d.setBounds(350,300,30,30);
			}
			f.aire.add(f.msg_tuto);
			f.tuto_g.setEnabled(false);
			Font ff = new Font(null, Font.PLAIN, 14);
			f.msg_tuto.setFont(ff);
			f.msg_tuto.setForeground(Color.blue);
			f.msg_tuto.setEditable(false);
			f.aire.repaint();

			if (f.N == 15) {
				f.aire.tuto_x = 680;
				f.aire.tuto_y = 480;
				f.aire.bulle_x = 460;
				f.aire.bulle_y = 330;
				f.msg_tuto.setLocation(505, 360);
			} else {
				f.lab_j2.setText("");
				f.aire.tuto_x = 300;
				f.aire.tuto_y = 380;
				f.aire.bulle_x = 80;
				f.aire.bulle_y = 250;
				f.msg_tuto.setLocation(125, 280);
				f.tuto_g.setBounds(80,300,30,30);
				f.tuto_d.setBounds(350,300,30,30);
			}
			f.aire.img_bulle = f.aire.img_bulle_2;
			f.aire.img_tuto = f.aire.img_didac_1;
			f.aire.img_fleche = f.aire.img_fh;
			/**********************/
			if(clic == false){bulle_1();}
			tempo();
			if(clic == false){bulle_2();}
			tempo();
			if(clic == false){bulle_3();}
			tempo();
			if(clic == false){bulle_4();}
			tempo();
			if(clic == false){bulle_5();}
			tempo();
			if(clic == false){bulle_6();}
			tempo();
			if(clic == false){bulle_7();}
			tempo();
			if(clic == false){bulle_8();}
			tempo();
			if(clic == false){bulle_9();}
			tempo();
			if(clic == false){bulle_10();}
			tempo();
			if(clic == false){bulle_11();}
			tempo();
			if(clic == false){bulle_12();}
			Thread.sleep(temps);



		} catch (InterruptedException ex) {
			Logger.getLogger(Didacticiel.class.getName()).log(Level.SEVERE, null, ex);
		}


	}

}



