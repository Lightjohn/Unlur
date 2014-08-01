
package unlurlol;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import fenetres.Fen_jeu;

public class ChoisirNoir implements MouseListener{
    Fen_jeu f;

    public ChoisirNoir(Fen_jeu fen){
        f=fen;
    }

 @Override
    synchronized public void mouseReleased(MouseEvent e) {

     f.tracer_eclipse = false;

        if (f.choixnoir.contains(e.getX(), e.getY()) && f.choixnoir.isEnabled()) {
            if(f.activer_tuto == false){
                 if(f.mode_IA==null){
                     f.depiler_avant_coup();
                     f.num_coup_blanc = f.nbr_coups_joue;
                     if(f.Reseau_a_joue==true){
                         f.partie_commencee = true;
                         if(f.res!=null){
                             f.res.envoyer_bool(true);
                             f.Reseau_a_joue=false;
                         }
                         if(f.joueur_courant==1){
                             f.joueurDeux.couleur=2;
//                             f.joueur_courant=2;/////////////////////////////
                             f.outils.nbcoup_restantJ2=500;
                         } else {
                             f.joueurUn.couleur=2;
//                             f.joueur_courant=1;/////////////////////////////
                             f.outils.nbcoup_restantJ1=500;
                         }
                         f.choixnoir.setEnabled(false);
                         f.img_choisir_indispo = new ImageIcon("images/noir_indispo.png");
                         f.choixnoir.setDisabledIcon(f.img_choisir_indispo);
                         f.aire.repaint();

                         if(f.mode_j2 == f.MODE_ORDI_2 || f.mode_j1 == f.MODE_ORDI_2 || f.mode_j2 == f.MODE_ORDI_3 || f.mode_j1 == f.MODE_ORDI_3){
                             int[][] floyd_nouveau = f.outils.remplir_matrice_floyd();
                             int[][] floyd_final= f.outils.floyd_warshall(floyd_nouveau);
                             f.outils.ancienne_composante_ia=null;
                             f.outils.composante_ia=null;
                             f.outils.composante_humain = f.outils.composante_connexe_principale(1,floyd_final);
                             
                         }

//                         a ne faire que dans le cas de l'IA 2 :
                         if((f.joueur_courant == 1 && f.mode_j1 != f.MODE_ORDI_1) ||
                                 (f.joueur_courant == 2 && f.mode_j2 != f.MODE_ORDI_1)) {
                             if(f.joueur_courant==1){
                                 f.joueur_courant=2;
                             } else {
                                 f.joueur_courant=1;
                             }
                             f.aire.repaint();
                         }

//                         f.outils.__jouerOrdi_1();
                         
                         f.outils.__continuerParOrdi();
                         f.aire.repaint();
                     }
                 } else {
                     f.partie_commencee=true;
                     if(f.joueur_courant==1){
                         f.joueurDeux.couleur=2;
//                         f.joueur_courant=2;/////////////////////////////////
                         f.outils.nbcoup_restantJ2=500;
                     } else {
                         f.joueurUn.couleur=2;
//                         f.joueur_courant=1;/////////////////////////////////
                         f.outils.nbcoup_restantJ1=500;
                     }

                     f.choixnoir.setEnabled(false);
                     f.img_choisir_indispo = new ImageIcon("images/noir_indispo.png");
                     f.choixnoir.setDisabledIcon(f.img_choisir_indispo);
                     f.aire.repaint();

                     if(f.mode_j2 == f.MODE_ORDI_2 || f.mode_j1 == f.MODE_ORDI_2){
                         int[][] floyd_nouveau = f.outils.remplir_matrice_floyd();
                         int[][] floyd_final= f.outils.floyd_warshall(floyd_nouveau);
                         f.outils.ancienne_composante_ia=null;
                         f.outils.composante_ia=null;
                         f.outils.composante_humain = f.outils.composante_connexe_principale(1,floyd_final);
                         
                     }

//                     a ne faire que dans le cas de l'IA 2 :
                     if((f.joueur_courant == 1 && f.mode_j1 != f.MODE_ORDI_1) ||
                             (f.joueur_courant == 2 && f.mode_j2 != f.MODE_ORDI_1)) {
                         if(f.joueur_courant==1){
                             f.joueur_courant=2;
                         } else {
                             f.joueur_courant=1;
                         }
                         f.aire.repaint();
                     }
                     
                     f.outils.__continuerParOrdi();

                     f.aire.repaint();
                 }
                 f.pile.pile.add(new Objet_save(f));
            }
        } else {
                f.img_choisir = new ImageIcon("images/noir_pas_appuye.png");
                f.choixnoir.setIcon(f.img_choisir);
                f.aire.repaint();
        }


    }

    @Override
    synchronized public void mousePressed(MouseEvent e) {
        if(f.choixnoir.isEnabled()){
            f.img_choisir = new ImageIcon("images/noir_appuye.png");
            f.choixnoir.setIcon(f.img_choisir);
            f.aire.repaint();
        } else {
            f.img_choisir_indispo = new ImageIcon("images/noir_indispo.png");
            f.choixnoir.setDisabledIcon(f.img_choisir_indispo);
            f.aire.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

//    

}
