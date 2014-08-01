/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fenetres.Fen_jeu;
import unlurlol.Objet_save;
import unlurlol.Ordinateur;

/**
 *
 * @author lecaultg
 */
public class EcouteurJoueurJcIA implements MouseListener {
 public int difficulte;
 public boolean tour_joueur;   //variable pour le sablier
 Fen_jeu f;
boolean deja_sauvegarde = false;

    public EcouteurJoueurJcIA(int difficulte,Fen_jeu fen) {
        this.difficulte = difficulte;
        tour_joueur=true;
        f=fen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    synchronized public void attendre_coup(){
        new Ordinateur(f,this,null);
    }
    
    @Override


   synchronized public void mousePressed(MouseEvent e) {
        if(!f.jeu_fini && f.activer_tuto == false) {
            if(tour_joueur==true){
                if(!deja_sauvegarde){
                     f.pile.empiler(new Objet_save(f));
                     deja_sauvegarde = true;           
                }
           
                if(f.outils.__jouerUtil(e.getX(), e.getY())){
                    tour_joueur=false;
                    if(!f.jeu_fini){
                        //partie test
                        f.undo.setEnabled(true);
                        attendre_coup();
                        if(f.res == null){
                            f.doit_sauvegarde = true;}
                        f.activer_annuler_refaire(); 
                        f.depiler_avant_coup();
                        Objet_save a = new Objet_save(f);
                        //afficher_matrice_sous_forme_text(a);
                        f.pile.empiler(a);
                        f.nbr_coups_joue ++;
                        f.compte = 0;
                        //System.out.println("blanc"+f.num_coup_blanc);
                        //System.out.println("aAa "+f.nbr_coups_joue);
                        
                    }
                }
               
            }
            
        }

    }

    @Override

    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
    public void afficher_matrice_sous_forme_text(Objet_save a){
        for(int i=0; i<f.N; i++){
                for(int j=0; j<f.N; j++){
                        
                                //selon etat
                                if(a.mat[i][j].etat == -1){
                                        System.out.print(a.mat[i][j].etat+" ");
                                }else{
                                        System.out.print(" "+a.mat[i][j].etat+" ");
                                }
                        
                    
                }
                System.out.println("");
        }
        System.out.println("");
    }
 
}
