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
public class EcouteurAbandonnerJ2 implements MouseListener{

    Fen_jeu f;

    public EcouteurAbandonnerJ2(Fen_jeu fen){
        f=fen;
    }


    public void mouseReleased(MouseEvent e) {
            if (f.abandonner_j2.contains(e.getX(), e.getY())) {
                new Fen_Abandon(f, 2);
                f.img_abandonner_j2 = new ImageIcon("images/abandonner_pas_clique.png");
                f.abandonner_j2.setIcon(f.img_abandonner_j2);
            } else {
                f.img_abandonner_j2 = new ImageIcon("images/abandonner_pas_clique.png");
                f.abandonner_j2.setIcon(f.img_abandonner_j2);
            }
    }


    public void mousePressed(MouseEvent e) {
           f.img_abandonner_j2 = new ImageIcon("images/abandonner_clique.png");
            f.abandonner_j2.setIcon(f.img_abandonner_j2);
    }


    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
