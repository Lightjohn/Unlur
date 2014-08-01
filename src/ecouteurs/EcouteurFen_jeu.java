
package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fenetres.Fen_jeu;
import unlurlol.AireDeDessin;
import unlurlol.Objet_save;



public class EcouteurFen_jeu implements MouseListener{
    AireDeDessin aire;
    Fen_jeu f;
    boolean deja_sauvegarde = false;

    public EcouteurFen_jeu(AireDeDessin a,Fen_jeu fen){
        aire=a;
        f=fen;
        
    }

    public void mousePressed(MouseEvent e) {
        
        if(f.activer_tuto == false){
            if(!deja_sauvegarde){
                f.pile.empiler(new Objet_save(f));
                deja_sauvegarde = true;
            }
               
            if(f.outils.__jouerUtil(e.getX(),e.getY())){
                if(f.res == null){
                    f.doit_sauvegarde = true;
                }
                f.undo.setEnabled(true);
                f.activer_annuler_refaire(); 
                f.arreter_thread_clignotant();
                f.outils.__continuerParOrdi();
                //Partie annuler refaire  
                f.depiler_avant_coup();
                Objet_save a = new Objet_save(f);
                f.pile.empiler(a);
                //afficher_matrice_sous_forme_text(a);
                //System.out.println("un:"+a.joueur1.couleur+" deux:"+a.joueur2.couleur);
                f.nbr_coups_joue ++;
                f.compte = 0;
            }
            }
    }



    public void mouseClicked(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

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
