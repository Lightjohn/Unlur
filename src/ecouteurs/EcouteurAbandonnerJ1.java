/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import fenetres.Fen_Abandon;
import fenetres.Fen_jeu;


/**
 *
 * @author desgrour
 */
public class EcouteurAbandonnerJ1 implements MouseListener{

    Fen_jeu f;
    
    public EcouteurAbandonnerJ1(Fen_jeu fen){
        f=fen;
    }


    public void mouseReleased(MouseEvent e) {
            if(f.abandonner_j1.contains(e.getX(), e.getY())){
                if(f.res!=null){
                    f.res.envoyer_Abandon();
                }
                new Fen_Abandon(f, 1);
                f.img_abandonner_j1 = new ImageIcon("images/abandonner_pas_clique.png");
                f.abandonner_j1.setIcon(f.img_abandonner_j1);
            } else {
                f.img_abandonner_j1 = new ImageIcon("images/abandonner_pas_clique.png");
                f.abandonner_j1.setIcon(f.img_abandonner_j1);
                
            }
        
    }


    public void mousePressed(MouseEvent e) {
            f.img_abandonner_j1 = new ImageIcon("images/abandonner_clique.png");
            f.abandonner_j1.setIcon(f.img_abandonner_j1);
            
    }

    
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
