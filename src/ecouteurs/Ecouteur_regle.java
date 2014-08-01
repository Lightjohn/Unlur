/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fenetres.Fen_Regle;

/**
 *
 * @author light
 */
public class Ecouteur_regle implements MouseListener {
    String bouton;
    Fen_Regle f;
    public Ecouteur_regle(String s, Fen_Regle fen) {
        bouton = s;
        f =fen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("pos: "+f.position);
        if(bouton.compareTo("droite") == 0 && f.position <= 4 && f.position >= 0){
            f.position = (f.position+1)%5;
            f.fen_dessin.img_path = (f.fichier[f.position]);
            
        
        }
        else{
            if(f.position >= 0 && f.position <= 5 
                                && !(bouton.compareTo("droite") == 0)){
                f.position--;
                if(f.position < 0)
                    f.position = 4;
                f.fen_dessin.img_path = (f.fichier[f.position]);
            }
        
        }
        f.fen_dessin.repaint();
        
        if(f.position >=1 && f.position < 5){
            f.gauche.setEnabled(true);
            f.droite.setEnabled(true);
        }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
    
}
