/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import ecouteurs.EcouteurIAvsIA;
import ecouteurs.EcouteurJoueurJcIA;
import fenetres.Fen_Fin_partie;
import fenetres.Fen_jeu;
/**
 *
 * @author lecaultg
 */

public class Ordinateur implements ActionListener {
    EcouteurJoueurJcIA ec;
    EcouteurIAvsIA ec2;
    Fen_jeu f;
    Timer t;

    public Ordinateur(Fen_jeu a,EcouteurJoueurJcIA ev,EcouteurIAvsIA ev2) {
        int temps = 1000;
        ec=ev;
        ec2=ev2;
        f = a;

        //si on joue en mode IA vs IA, on accélère la vitesse de jeu :
        if(ec2 != null)
            temps = 500;

        t = new Timer(temps, this);
        t.start();

    }

   synchronized public void actionPerformed(ActionEvent e) {
        Random r;
        int i, j;
        caseM c;
        boolean b;
        b = true;
        r = new Random();
        f.ecvIA.tour_joueur = true;
        if(ec!=null && ec.difficulte==1){
            if(!f.partie_commencee) {
                int click;
                click = r.nextInt(100);
                if(click < 25) {//25 % de chances
                    f.partie_commencee = true;
                    if(f.joueur_courant == 1){
                        f.joueurDeux.couleur = 2;
                    }
                    else{
                        f.joueurUn.couleur = 2;
                    }
                    
                    f.img_choisir_indispo = new ImageIcon("images/noir_indispo.png");
                    f.choixnoir.setDisabledIcon(f.img_choisir_indispo);
                    f.choixnoir.setEnabled(false);
                    f.aire.repaint();
                    b = false;
                }
// ici nv version
                else{
                    f.joueurUn.couleur = 2;
                }
                f.choixnoir.setEnabled(false);
                f.img_choisir_indispo = new ImageIcon("images/noir_indispo.png");
                f.choixnoir.setDisabledIcon(f.img_choisir_indispo);
                f.aire.repaint();
                b = false;
// fin version
            }
       }
        
       if(b) {
           if(ec!=null && ec.difficulte==1){
               do {
                    i = r.nextInt(f.N);
                    j = r.nextInt(f.N);
                    c = f.matrice[i][j];
               }
               while(!f.outils.estPossibleClic(c));
               f.outils.changerEtat(c, f.joueur_courant);
               f.aire.repaint();
           }else if(ec!=null && ec.difficulte==2){

               f.outils.jouerOrdi_2();
               // if(f.partie_commencee){

                     
                // }


           }else if(ec!=null && ec.difficulte==3){
               
               f.outils.jouerOrdi_3();
               // if(f.partie_commencee){


                // }


           }
       }

         //quand le joueur joue contre l'IA, il faut lancer le timer
        //qui attend x millisecondes avant de se stopper
        ArrayList<caseM> list;
        if(ec != null) {
            ec.tour_joueur=true;
            t.stop();
            if(f.partie_commencee && f.outils.estVictoire(f.joueur_courant)) {
                f.jeu_fini = true;
                System.out.println("Le joueur " + f.joueur_courant + " a gagné !!!");
               list=f.outils.estVictoire_retourne_chemin(f.joueur_courant);
                ListIterator<caseM> L=list.listIterator();
                System.out.println("Resultat size :" + list.size());
                while (L.hasNext()) {
                    caseM next = L.next();
                    System.out.println("Case i:" + next.i + "j :" + next.j);
                    f.matrice[next.i][next.j].etat = 5;
                }
                f.aire.repaint();

                new Fen_Fin_partie(f, true, f.joueur_courant);

                
                f.aire.repaint();

            }
            else if(f.partie_commencee && f.outils.estDefaite(f.joueur_courant)) {
                f.jeu_fini = true;
                 System.out.println("Le joueur " + f.joueur_courant + " a perdu...");
                f.aire.repaint();
            }
       }

        //quand l'IA joue contre une autre IA, il faut continuer tant qu'il
        //n'y a pas de victoire
        if(ec2 != null) {

            //test de la victoire :
            if(f.partie_commencee && (list=f.outils.estVictoire_retourne_chemin(f.joueur_courant))!=null) {
                f.jeu_fini = true;
                System.out.println("Le joueur " + f.joueur_courant + " a gagné !!!");
                ListIterator<caseM> L=list.listIterator();
                System.out.println("Resultat size :" + list.size());
                while (L.hasNext()) {
                    caseM next = L.next();
                    System.out.println("Case i:" + next.i + "j :" + next.j);
                    f.matrice[next.i][next.j].etat = 5;
                }
              
                f.aire.repaint();
                new Fen_Fin_partie(f, true, f.joueur_courant);
                t.stop();
                
            }

            //test de la défaite :
            else if(f.partie_commencee && f.outils.estDefaite(f.joueur_courant)) {
                f.jeu_fini = true;
                System.out.println("Le joueur " + f.joueur_courant + " a perdu...");
                f.aire.repaint();
                new Fen_Fin_partie(f, false, f.joueur_courant);
                t.stop();
                
            }

        }

       
         //changement de joueur
        if(ec.difficulte == 1) {
            if(f.joueur_courant==1)
                f.joueur_courant=2;
             else
                f.joueur_courant=1;
            f.aire.repaint();
        }
        //Annuler refaire
        // annuler refaire IA
        f.activer_annuler_refaire();
        if(f.res == null){
            f.doit_sauvegarde = true;}
        f.depiler_avant_coup();
        Objet_save a = new Objet_save(f);
        f.pile.empiler(a);
        f.nbr_coups_joue ++;
        f.compte = 0;
        //System.out.println("un:"+a.joueur1.couleur+" deux:"+a.joueur2.couleur);

    }


}