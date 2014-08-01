
package ecouteurs;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import fenetres.Fen_Dialog;
import fenetres.Fen_Reseau;
import fenetres.Fen_jeu;
import thread.Thread_reseau;
import unlurlol.Reseau;

public class EcouteurRejoindreReseau implements MouseListener {
	String ip;
	JTextField AIP;
	Fen_jeu fj;
	int numport,N;
	Fen_Reseau fr;
	Fen_Dialog fenetreR;
	boolean serveur_cree;

	public EcouteurRejoindreReseau(JTextField AIPZ,Fen_Dialog f,Fen_Reseau Fen_Res,boolean sc, Fen_jeu fen){
		fj=fen;
		AIP=AIPZ;
		fr=Fen_Res;
		serveur_cree=sc;
		fenetreR=f;      
	}

	public void mouseReleased(MouseEvent e) {
		if(fenetreR.rejoindre.contains(e.getX(), e.getY())){
			try {
				Reseau reso = new Reseau();



				ip = AIP.getText();

				reso.rejoindre_socket(ip);
				Fen_jeu f = new Fen_jeu(reso, false, 11, null);

				fenetreR.dispose();
				fj.f.dispose();

				if (serveur_cree == false) {
					Thread t = new Thread(new Thread_reseau(f));
					t.start();
					serveur_cree = true;

				}                
				if (!reso.get_ip()) {

					fr.f.dispose();
					new Fen_Reseau(serveur_cree, null);
				} else {

					f.run();
					f.f.setAlwaysOnTop(true);
					f.f.setAlwaysOnTop(false);
					f.res.envoyer_Nom(f.nom_joueur1);
					f.Reseau_a_joue = false;
					f.choixnoir.setEnabled(false);
					f.joueur_courant = 2;
					f.aire.repaint();

				}
			} catch (FileNotFoundException ex) {
				Logger.getLogger(EcouteurRejoindreReseau.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(EcouteurRejoindreReseau.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			fenetreR.img_rejoindre= new ImageIcon("images/rejoindre_pas_clique.png");
			fenetreR.rejoindre.setIcon(fenetreR.img_rejoindre);
		}


	}

	public void mousePressed(MouseEvent e) {
		fenetreR.img_rejoindre= new ImageIcon("images/rejoindre_clique.png");
		fenetreR.rejoindre.setIcon(fenetreR.img_rejoindre);

	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}

	//    public void actionPerformed(ActionEvent e) {
		//        try {
			//            Reseau reso = new Reseau();
	//
	//
	//
	//            ip=AIP.getText();
	//
	//            reso.rejoindre_socket(ip);
	//            Fen_jeu f=new Fen_jeu(reso,false, 11,null);
	//
	//            fenetreR.dispose();
	//            fj.f.dispose();
	//
	//             if(serveur_cree==false){
	//               Thread t = new Thread(new Thread_reseau(f));
	//               t.start();
	//               serveur_cree=true;
	//
	//             }
	//            /* else{
	//                reso.rejoindre_socket("localhost");
	//                reso.fin_connexion();
	//                Thread t = new Thread(new Thread_reseau(f));
	//                t.start();
	//
	//             }*/
	//            if(!reso.get_ip()){
	//
	//                fr.f.dispose();
	//                new Fen_Reseau(serveur_cree,null);
	//             }
	//
	//            else{
	//
	//                 f.run();
	//
	//
	//                 f.f.setAlwaysOnTop(true);
	//                 f.f.setAlwaysOnTop(false);
	//                 f.Reseau_a_joue=false;
	//                 f.choixnoir.setEnabled(false);
	//
	//             }
	//        } catch (FileNotFoundException ex) {
	//            Logger.getLogger(EcouteurRejoindreReseau.class.getName()).log(Level.SEVERE, null, ex);
	//        } catch (IOException ex) {
	//            Logger.getLogger(EcouteurRejoindreReseau.class.getName()).log(Level.SEVERE, null, ex);
	//        }
	//
	//
	//
	//    }


}
