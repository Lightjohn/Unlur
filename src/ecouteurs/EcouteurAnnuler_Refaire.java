
package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import fenetres.Fen_jeu;
import thread.ThreadAnnulerIA;
import unlurlol.Objet_save;

 
public class EcouteurAnnuler_Refaire implements MouseListener {

    Fen_jeu f;
    Objet_save save;
    String objet;
    
    boolean deja_arrete = false;
    boolean est_possible = true;
//    boolean deja_depile = false;
//    boolean deja_repile = false;


          
    public EcouteurAnnuler_Refaire(Fen_jeu fen,String s){
        f = fen;
        objet = s;
    }
    
    public void annuler_coup(){
        f.joueur_courant = save.joueur_courant;
        for(int i = 0 ;i < f.N ; i++){
            for(int j = 0; j< f.N; j++){
                f.matrice[i][j].etat = save.mat[i][j].etat;      
                f.matrice[i][j].couleur = save.mat[i][j].etat; 
            }
        }
        
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
    
    public boolean dansBouton(int clic_x, int clic_y) {
        double center_x = 70 / 2;
        double center_y = 70 / 2;
        double x = clic_x - center_x;
        double y = clic_y - center_y;

        return ((x*x)/(30*30) + (y*y)/(30*30) <= 1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int b= f.nbr_coups_joue -f.compte ;
        System.out.println("coup "+b);
        f.activer_annuler_refaire();
        if(f.clignotant != null){
            f.arreter_thread_clignotant(); 
            deja_arrete = true;
        }
        if(f.annulerIA != null){
            f.annulerIA.stop();
        }
        
        //System.out.println("undo "+f.undo.isEnabled());
        
        if(objet.compareTo("undo") == 0 && f.undo.isEnabled() 
                && dansBouton(e.getX(),e.getY()) && f.activer_tuto == false){
            
            //System.out.println("entered the matrix\n");
            if(f.nbr_coups_joue - f.compte <= f.num_coup_blanc+1){
            f.choixnoir.setEnabled(true);
            f.joueurUn.couleur = 1;
            f.joueurDeux.couleur = 1;
            f.partie_commencee = false;
        }
            
            
            f.redo.setEnabled(true);
            //afficher_matrice_sous_forme_text(f.pile.depiler());
            save = f.pile.depiler();
            if(save != null)
                f.pile.refaire.push(save);
            save = f.pile.prendre_dernier();  
            if(save != null){
                //afficher_matrice_sous_forme_text(save);
                //System.out.println("un:"+save.joueur1.couleur+" deux:"+save.joueur2.couleur);
                annuler_coup();
                //System.out.println("compte++");
                f.compte++;
            }
            //mode IA
            

            
        }
        else if(objet.compareTo("redo") == 0 && f.redo.isEnabled()
                && dansBouton(e.getX(),e.getY()) && f.activer_tuto == false){
            
            save = (Objet_save) f.pile.refaire.peek();
            
            if(f.nbr_coups_joue -f.compte >= f.num_coup_blanc){
                    //System.out.println("un:"+save.joueur1.couleur+" deux:"+save.joueur2.couleur);
                    f.choixnoir.setEnabled(false);
                    f.partie_commencee = true;
                    f.joueurUn.couleur = save.joueur1.couleur;
                    f.joueurDeux.couleur = save.joueur2.couleur;
                    f.aire.repaint();
                }
            
            try { 
                save = (Objet_save) f.pile.refaire.pop();
                if(save != null){
                    f.pile.empiler(save);
                }
                
                save = (Objet_save) f.pile.prendre_dernier();
                //afficher_matrice_sous_forme_text(save);
            } catch (Exception eb) {
                est_possible = false;
            }         
            if(est_possible && save != null){
                //afficher_matrice_sous_forme_text(save);
                annuler_coup();
                f.compte--;
                f.undo.setEnabled(true);
            }
            est_possible = true;
            
            
            
            
            
        }
        
        if(f.joueur_courant == 2 && f.mode_j2 != 0 && !f.jeu_fini){
                f.annulerIA =new Thread(new ThreadAnnulerIA(f));
                f.annulerIA.start();
            }
        
        if(f.compte == 0){
            
            f.redo.setEnabled(false);
            
        }
        if(f.nbr_coups_joue == f.compte){
            //System.out.println("undo false\n");
            f.undo.setEnabled(false);
            
        }

        //int b= f.nbr_coups_joue -f.compte ;
        //int c =  f.num_coup_blanc+1;
        //System.out.println("blanc = "+c+" decompte= "+b+" tot= "+f.nbr_coups_joue);
        //System.out.println("compte " +f.compte);
        
        f.aire.repaint();
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(objet.compareTo("undo") == 0){
            f.undo.setIcon(new ImageIcon("images/undo_appuye.png"));
        }
        else if(objet.compareTo("redo") == 0)
            f.redo.setIcon(new ImageIcon("images/redo_appuye.png"));
        f.aire.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(objet.compareTo("undo") == 0){
            f.undo.setIcon(new ImageIcon("images/undo_pas_appuye.png"));
        }
        else if(objet.compareTo("redo") == 0)
            f.redo.setIcon(new ImageIcon("images/redo_pas_appuye.png"));
        f.aire.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
