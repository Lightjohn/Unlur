/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import thread.Thread_clignote_boule;
import thread.Thread_erreur;
import unlurlol.AireDeDessin;
import unlurlol.ChoisirNoir;
import unlurlol.Didacticiel;
import unlurlol.JouerReseau;
import unlurlol.Outils;
import unlurlol.Pile_objet_save;
import unlurlol.Reseau;
import unlurlol.Save_load;
import unlurlol.Statistiques;
import unlurlol.VariablesIA;
import unlurlol.caseM;
import unlurlol.joueur;
import ecouteurs.EcouteurAbandonnerJ1;
import ecouteurs.EcouteurAbandonnerJ2;
import ecouteurs.EcouteurAnnuler_Refaire;
import ecouteurs.EcouteurCredits;
import ecouteurs.EcouteurFen_jeu;
import ecouteurs.EcouteurIAvsIA;
import ecouteurs.EcouteurJoueurJcIA;
import ecouteurs.EcouteurMJNouvellePartie;
import ecouteurs.EcouteurMJPreferences;
import ecouteurs.EcouteurMenuRegle;
import ecouteurs.EcouteurMotionFen_jeu;
import ecouteurs.EcouteurQuitter;
import ecouteurs.EcouteurSourisReseau;
import ecouteurs.Ecouteur_coup;
import ecouteurs.Ecouteur_item_Jeu;
import ecouteurs.Ecouteur_save_load;
import ecouteurs.Ecouteur_tuto;
/**
 *
 *
 * @author adri
 */
public class Fen_jeu implements Runnable{

	public final int MODE_UTIL = 0; //mode utilisateur
	public final int MODE_ORDI_1 = 1; //mode ordinateur

	public final int MODE_ORDI_2 = 2; //mode ordinateur
	public  final int MODE_ORDI_3 = 3;

	//deux modes différents pour permettre de faire jouer deux IAs ensemble :

	public int mode_j1; //mode JcJ = 0, mode IA 1 = 1, mode IA 2 = 2...
	public int mode_j2; //mode JcJ = 0, mode IA 1 = 1, mode IA 2 = 2...

	public caseM[][] matrice; //matrice - hexagone

	public int N; //taille de l'hexagone
	public int joueur_courant;
	public int couleur_actuelle;   //1 -> noir 2-> blanc
	public int niveau_ia;  // 1 facile/ 2 moyen etc ...
	public int taille_plateau_pref;

	public Outils outils;

	public joueur joueurUn;
	public joueur joueurDeux;

	public boolean partie_commencee;
	public boolean jeu_fini;
	public boolean createur;
	public boolean Reseau_a_joue;
	public boolean activer_tuto;

	public String nom_joueur1;
	public String nom_joueur2;
	public String adresse_ip;

	public JFrame f;
	public AireDeDessin aire;
	public JButton choixnoir, undo, redo, coup;
	public JButton abandonner_j1, abandonner_j2;
	public JButton tuto_g , tuto_d;
	public ImageIcon img_choisir, img_undo, img_redo, img_coup, img_redo_indispo;
	public ImageIcon img_undo_indispo, img_abandonner_j2, img_abandonner_indispo_j2;
	public ImageIcon img_abandonner_j1, img_abandonner_indispo_j1, img_choisir_indispo;
	public ImageIcon img_fg,img_fd,img_croix, img_coup_indispo;

	public JLabel lab_j1,lab_j2;
	public JLabel commentaire;
	public JTextArea msg_tuto;

	public Reseau res;

	public JMenuBar menubar;
	public JMenu men_jeux;
	public JMenu men_aide;
	public JMenuItem it_newgame;
	public JMenuItem it_sauv;
	public JMenuItem it_load;
	public JMenuItem it_pref;
	public JMenuItem it_quit;
	public JMenuItem it_regle;
	public JMenuItem it_tuto;
	public JMenuItem it_credits;

	public Fen_tchat tchat;

	public Save_load sl;
	public Didacticiel didac;

	public FileReader fr;

	//Partie annuler refaire
	public Pile_objet_save pile;
	public int compte = 0;
	public int nbr_coups_joue = 0;
	public int num_coup_blanc = 255;
	//fin partie annuler refaire
	//partie message d'erreur
	public Queue file_erreur;
	//fin partie message d'erreur
	public VariablesIA mode_IA;
	public EcouteurJoueurJcIA ecvIA;
	public EcouteurIAvsIA eiavia;
	public Thread thread_tuto;
	public boolean thread_libre;
	public boolean est_serveur;
	public boolean doit_sauvegarde;
	public boolean doit_clignote;
	public boolean tracer_eclipse;

	public int couleur_clignotante;
	public caseM case_clignotante;
	public Thread clignotant;
	public Thread annulerIA;

	public Statistiques stats;
	public Boolean IA_doit_dormir;

	public Fen_jeu(Reseau r,boolean crea, int taille,VariablesIA IAz) throws FileNotFoundException, IOException
	{

		//System.out.println("coucou");
		res=r;
		createur=crea;
		N=taille;
		mode_j1 = MODE_UTIL; //défaut
		mode_j2 = MODE_UTIL; //défaut
		jeu_fini = false;
		Reseau_a_joue=true;
		sl = new Save_load(this);
		activer_tuto = false;
		mode_IA=IAz;

		pile=new Pile_objet_save();

		tracer_eclipse = false;


		/******************* récupération du fichier de préférence ********************/


		boolean temp = false;

		if(N == -1)
		{
			try{
				fr = new FileReader("preferences.txt");
			}catch (FileNotFoundException e)
			{
				temp = true;

				FileWriter fw = new FileWriter("preferences.txt",false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(11+"\n");  //taille plateau
				taille_plateau_pref = 11;
				N = 11;
				bw.write(1+"\n");   //niveau ia
				niveau_ia = 1;
				activer_tuto = true;
				bw.write(false+"\n");    //activer tuto...
				nom_joueur1 = System.getProperty("user.name");
				bw.write(nom_joueur1+"\n");      //j1
				nom_joueur2 = "Joueur2";
				bw.write(nom_joueur2+"\n");      //j2
				adresse_ip = "127.0.0.1";
				bw.write(adresse_ip+"\n");
				doit_clignote = false;
				bw.write(doit_clignote+"\n");
				bw.flush();
				bw.close();
			}

			if(temp == false)
			{
				BufferedReader input = new BufferedReader(fr);
				String tmps = new String();

				int tmp = Integer.parseInt(input.readLine()); //recup taille plateau
				N = tmp;      //taille plateau
				taille_plateau_pref = N;
				niveau_ia = Integer.parseInt(input.readLine());      //recup niveau ia
				activer_tuto =Boolean.parseBoolean(input.readLine());  //recup activer ou non le tuto
				nom_joueur1 = input.readLine();      //recup nom perso du joueur1
				nom_joueur2 = input.readLine();      //recup nom perso du joueur2
				adresse_ip = input.readLine();      //recup dernière adresse ip retenue
				doit_clignote = Boolean.parseBoolean(input.readLine());
				input.close();
			}

		}

		else
		{
			adresse_ip = "127.0.0.1";
			nom_joueur1 = System.getProperty("user.name");
			nom_joueur2 = "Joueur2";
			taille_plateau_pref = taille;
			niveau_ia = 1;
			doit_clignote = false;
		}



		/***moiiiiii*/////
		file_erreur = new LinkedList();
		thread_libre = true;
		est_serveur = false;
		doit_sauvegarde = false;
		couleur_clignotante = 1;
		IA_doit_dormir = true;
		//fin moi
	}

	public void run() {


		try {
			//N=11;
			matrice = new caseM[N][N];
			outils = new Outils(this);
			partie_commencee = false;
			joueur_courant = 1;
			couleur_actuelle = 1;
			joueurUn = new joueur("joueur1", 1);
			joueurDeux = new joueur("joueur2", 1);
			outils.remplir_matrice_a_vide();

			outils.init_joker2();
			outils.init_joker1();
			//outils.afficher_matrice_sous_forme_text(3);
			f = new JFrame("Unlur");

			//bouton choisir les noirs  //c'est toi le noir !!
			img_choisir = new ImageIcon("images/noir_pas_appuye.png");
			choixnoir = new JButton();
			choixnoir.setIcon(img_choisir);
			choixnoir.setFocusPainted(false);
			choixnoir.setBorderPainted(false);
			choixnoir.setContentAreaFilled(false);
			choixnoir.setToolTipText("Choisir les noirs");
			ChoisirNoir ecoutNoir = new ChoisirNoir(this);
			img_choisir_indispo = new ImageIcon("images/noir_indispo.png");
			choixnoir.setDisabledIcon(img_choisir_indispo);

			/**************************** fleches tuto  ***********************************/

			img_croix = new ImageIcon("images/croix_tuto.png");
			tuto_g = new JButton("");
			img_fg = new ImageIcon("images/fleche_g_tuto.png");
			tuto_g.setIcon(img_fg);
			tuto_g.setFocusPainted(false);
			tuto_g.setBorderPainted(false);
			tuto_g.setContentAreaFilled(false);
			tuto_g.setVisible(false);

			tuto_d = new JButton("");
			img_fd = new ImageIcon("images/fleche_d_tuto.png");
			tuto_d.setIcon(img_fd);
			tuto_d.setFocusPainted(false);
			tuto_d.setBorderPainted(false);
			tuto_d.setContentAreaFilled(false);
			tuto_d.setVisible(false);



			//bouton undo
			img_undo = new ImageIcon("images/undo_pas_appuye.png");
			undo = new JButton();
			undo.setIcon(img_undo);
			undo.setFocusPainted(false);
			undo.setBorderPainted(false);
			undo.setContentAreaFilled(false);
			undo.setToolTipText("Annuler le coup");
			undo.addMouseListener(new EcouteurAnnuler_Refaire(this, "undo"));
			undo.setEnabled(false);
			img_undo_indispo = new ImageIcon("images/undo_indispo.png");
			undo.setDisabledIcon(img_undo_indispo);

			//bouton redo
			img_redo = new ImageIcon("images/redo_pas_appuye.png");
			redo = new JButton();
			redo.setIcon(img_redo);
			redo.setFocusPainted(false);
			redo.setBorderPainted(false);
			redo.setContentAreaFilled(false);
			redo.setToolTipText("Refaire le dernier coup");
			redo.addMouseListener(new EcouteurAnnuler_Refaire(this, "redo"));
			redo.setEnabled(false);
			img_redo_indispo = new ImageIcon("images/redo_indispo.png");
			redo.setDisabledIcon(img_redo_indispo);

			//bouton coup

			img_coup = new ImageIcon("images/ampoule_pas_appuye.png");
			coup = new JButton();
			coup.setIcon(img_coup);
			coup.setFocusPainted(false);
			coup.setBorderPainted(false);
			coup.setContentAreaFilled(false);
			coup.setToolTipText("Conseil");
			coup.addMouseListener(new Ecouteur_coup(this));
			img_coup_indispo = new ImageIcon("images/ampoule_indispo.png");
			coup.setDisabledIcon(img_coup_indispo);

			//bouton abandonner j1

			img_abandonner_j1 = new ImageIcon("images/abandonner_pas_clique.png");
			abandonner_j1 = new JButton();

			abandonner_j1.setIcon(img_abandonner_j1);
			abandonner_j1.setFocusPainted(false);
			abandonner_j1.setBorderPainted(false);
			abandonner_j1.setContentAreaFilled(false);
			abandonner_j1.setToolTipText("Abandonner la partie");
			abandonner_j1.repaint();

			//bouton abandonner j2
			img_abandonner_j2 = new ImageIcon("images/abandonner_pas_clique.png");
			abandonner_j2 = new JButton();
			abandonner_j2.setIcon(img_abandonner_j2);
			abandonner_j2.setFocusPainted(false);
			abandonner_j2.setBorderPainted(false);
			abandonner_j2.setContentAreaFilled(false);
			abandonner_j2.setToolTipText("Abandonner la partie");
			abandonner_j2.setEnabled(false);
			img_abandonner_indispo_j2 = new ImageIcon("images/abandonner_indispo.png");
			abandonner_j2.setDisabledIcon(img_abandonner_indispo_j2);

			aire = new AireDeDessin(this);
			lab_j1 = new JLabel(nom_joueur1,SwingConstants.CENTER);

			//modifier le nom quand on joue contre l'ordi
			if(mode_IA == null) {
				lab_j2 = new JLabel(nom_joueur2,SwingConstants.CENTER);
			}
			if(mode_IA != null) {
				lab_j2 = new JLabel("Luxo",SwingConstants.CENTER);
			}
			commentaire = new JLabel("",SwingConstants.CENTER);

			menubar = new JMenuBar();
			men_jeux = new JMenu("Jeu");
			men_aide = new JMenu("Aide");
			it_newgame = new JMenuItem("Nouvelle partie");
			it_sauv = new JMenuItem("Sauver");
			it_load = new JMenuItem("Charger");

			it_sauv.setEnabled(false);

			it_pref = new JMenuItem("Preferences");
			it_quit = new JMenuItem("Quitter");
			it_regle = new JMenuItem("Regles");
			it_tuto = new JMenuItem("Tutoriel");
			it_credits = new JMenuItem("A propos");
			if(mode_IA != null){it_tuto.setEnabled(true);}
			else{it_tuto.setEnabled(false);}



			/********INIT IA***********************/
			stats = null;
			if(mode_IA!=null){
				if(mode_IA.JcIA==true){
					mode_j1 = MODE_UTIL;
					mode_j2 = mode_IA.DifficulteIA1;
					ecvIA=new EcouteurJoueurJcIA(mode_IA.DifficulteIA1,this);
					aire.addMouseListener(ecvIA);
				}
				else{
					mode_j1 = mode_IA.DifficulteIA1;
					mode_j2 = mode_IA.DifficulteIA2;

					eiavia=new EcouteurIAvsIA(mode_IA.DifficulteIA1,mode_IA.DifficulteIA2,this);


					//tester les statistiques :


						// stats = new Statistiques(this, mode_IA.DifficulteIA1, mode_IA.DifficulteIA2, 25, 1);

					stats = new Statistiques(this, 3, 2, 25, 10);

				}

			}
			else{
				/****************** truc bizarre *************************/

				if (res != null) {
					JouerReseau JR = new JouerReseau(this);
					EcouteurSourisReseau Ereseau = new EcouteurSourisReseau(this, res, JR);
					aire.addMouseListener(Ereseau);
					tchat = new Fen_tchat(this);

				} else {
					aire.addMouseListener(new EcouteurFen_jeu(aire, this));
				}

			}
			/****************** fin truc bizarre *************************/

			//ecouteurs

			choixnoir.addMouseListener(ecoutNoir);
			abandonner_j1.addMouseListener(new EcouteurAbandonnerJ1(this));
			abandonner_j2.addMouseListener(new EcouteurAbandonnerJ2(this));
			aire.addMouseMotionListener(new EcouteurMotionFen_jeu(this));
			EcouteurMJPreferences pref = new EcouteurMJPreferences(this);
			EcouteurMJNouvellePartie nouv = new EcouteurMJNouvellePartie(this);
			EcouteurQuitter quit = new EcouteurQuitter(this);
			EcouteurCredits cred = new EcouteurCredits(this);
			it_newgame.addActionListener(nouv);
			it_pref.addActionListener(pref);
			it_quit.addActionListener(quit);
			it_credits.addActionListener(cred);
			it_regle.addActionListener(new EcouteurMenuRegle());
			it_load.addActionListener(new Ecouteur_save_load(this, it_load));
			it_sauv.addActionListener(new Ecouteur_save_load(this, it_sauv));
			it_tuto.addActionListener(new Ecouteur_save_load(this, it_tuto));
			tuto_g.addActionListener(new Ecouteur_tuto(this,tuto_g));
			tuto_d.addActionListener(new Ecouteur_tuto(this,tuto_d));
			men_jeux.addMouseListener(new Ecouteur_item_Jeu(this, men_jeux));

			f.setResizable(false);

			/******specif 6x6*****/
			if(N == 11) {
				abandonner_j1.setBounds(70, 220, 110, 20);
				abandonner_j2.setBounds(70, 370, 110, 20);
				choixnoir.setBounds(705, 320, 150, 80);
				undo.setBounds(705,135,70,70);
				redo.setBounds(785,135,70,70);
				coup.setBounds(730,210,100,100);
				lab_j1.setBounds(20,130,200,40);
				lab_j2.setBounds(20, 290,200,40);
				commentaire.setBounds(0,20,900,30);
				f.setSize(900, 550);
			}


			/******specif 8x8*****/
			else if(N == 15) {
				abandonner_j1.setBounds(50, 275, 110, 20);
				abandonner_j2.setBounds(50, 425, 110, 20);
				choixnoir.setBounds(795, 370, 150, 100);
				undo.setBounds(790,190,70,70);
				redo.setBounds(880,190,70,70);
				coup.setBounds(820,270,100,100);
				lab_j1.setBounds(2, 190,200,40);
				lab_j2.setBounds(2, 345,200,40);
				commentaire.setBounds(0,20,975,30);
				f.setSize(975, 650);
			}



			/**************** ajout des menus et des Jcomponents ******************/

			aire.add(lab_j1);
			aire.add(lab_j2);
			aire.add(abandonner_j1);
			if((mode_IA == null) && (res == null)){
				aire.add(abandonner_j2);
			}
			aire.add(undo);
			aire.add(redo);
			aire.add(coup);
			aire.add(choixnoir);
			aire.add(commentaire);
			aire.add(tuto_g);
			aire.add(tuto_d);

			menubar.add(men_jeux);
			menubar.add(men_aide);
			men_jeux.add(it_newgame);
			men_jeux.add(it_sauv);
			men_jeux.add(it_load);
			men_jeux.add(it_pref);
			men_jeux.add(it_quit);
			men_aide.add(it_regle);
			men_aide.add(it_tuto);
			men_aide.add(it_credits);

			/**************** personalisation des menus et des Jcomponents ****************/

			//penser a modifier la police !
			Font ff = new Font("Palatino", Font.PLAIN, 35);
			lab_j2.setForeground(Color.blue);
			lab_j1.setForeground(Color.blue);
			lab_j1.setFont(ff);
			lab_j2.setFont(ff);
			commentaire.setForeground(new Color(255,250,150));
			commentaire.setFont(ff);

			/*************** affichage de la fenetre ***********************/

			f.add(aire);
			f.setJMenuBar(menubar);
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			/*************************** ajout du tuto *************************************/
			if(activer_tuto == true && mode_IA != null)
			{thread_tuto = new Thread(didac = new Didacticiel(this));
			thread_tuto.start();

			}

			/********** suppression de sauver/charger en reseau **********/

			if(res != null)
			{
				it_load.setEnabled(false);
				it_sauv.setEnabled(false);

			}

			/********** desactivation "proposer coup" pour reseau et JvJ **********/

			if(mode_IA == null)
			{
				coup.setEnabled(false);
			}

			if(stats == null) {
				if (mode_j1 == MODE_ORDI_1 || mode_j1 == MODE_ORDI_2 || mode_j1 == MODE_ORDI_3) {
					outils.__commencerParOrdi();
				}
			}

		} catch (IOException ex) {
			Logger.getLogger(Fen_jeu.class.getName()).log(Level.SEVERE, null, ex);

		}


		//this.outils.remplir_matrice2();

	}

	//Partie annuler refaire
	public void activer_annuler_refaire(){

		if(pile.refaire.isEmpty() && !jeu_fini){
			redo.setEnabled(false);

		}
		if(pile.pile.isEmpty() && !jeu_fini){
			undo.setEnabled(false);
		}

		aire.repaint();

	}  
	public void depiler_avant_coup(){
		boolean deja_fait = false;
		while(compte != 0 && !pile.refaire.isEmpty()){
			pile.refaire.pop();
			compte--;
			if(!deja_fait){
				compte++;

			}
			nbr_coups_joue--;
		}

		redo.setEnabled(false);
		aire.repaint();

	}
	synchronized public void ajout_message_erreur(String s){
		file_erreur.offer(s);
		if(thread_libre){
			while(!file_erreur.isEmpty()){
				thread_libre = false;
				Thread t = new Thread(new Thread_erreur(this,(String) file_erreur.poll()));
				t.start();
			}
		}
	}
	synchronized public void arreter_thread_clignotant(){
		if(clignotant != null){
			clignotant.stop();
			case_clignotante.etat = couleur_clignotante;
			case_clignotante.couleur = couleur_clignotante;
			aire.repaint();
		}
	}   
	synchronized public void activer_thread_clignotant(caseM temp){
		if(doit_clignote){

			if(clignotant != null && clignotant.isAlive()){
				arreter_thread_clignotant();


			}
			case_clignotante = temp;
			clignotant = new Thread(new Thread_clignote_boule(temp, this));
			clignotant.start();

		}
	}
	public void modifier_preferences(int num , String s) throws FileNotFoundException, IOException
	{
		/* pour num : 0 -> taille plateau (int)
		 *            1 -> niveau ia (int)
		 *            2 -> boolean du tuto (boolean)
		 *            3 -> nom joueur 1 (string)
		 *            4 -> nom joueur 2 (string)
		 *            5 -> adresse ip (string)
		 *            6 -> clignotement (boolean)
		 */

		 FileReader fr = new FileReader("preferences.txt");
		 BufferedReader br = new BufferedReader(fr);
		 String [] tab = new String[7];
		 String tmp = new String();
		 int taille = 7;

		 for(int i = 0 ; i < taille ; i++)
		 {
			 tab[i] = br.readLine();
		 }

		 br.close();

		 FileWriter fw = new FileWriter("preferences.txt",false);
		 BufferedWriter bw = new BufferedWriter(fw);

		 for(int i = 0 ; i < taille ; i++)
		 {
			 if(i != num) {bw.write(tab[i]+"\n");}
			 else {bw.write(s+"\n");}
		 }

		 bw.flush();
		 bw.close();
	}

	public void afficher_matrice_sous_forme_text(){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){

				//selon etat
				if(matrice[i][j].etat == -1){
					System.out.print(matrice[i][j].couleur+" ");
				}else{
					System.out.print(" "+matrice[i][j].couleur+" ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
}