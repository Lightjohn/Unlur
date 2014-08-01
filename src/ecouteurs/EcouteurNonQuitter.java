/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import fenetres.Fen_Dialog;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurNonQuitter implements MouseListener {

    Fen_Dialog f;
    Fen_jeu fj;

    public EcouteurNonQuitter(Fen_jeu fen, Fen_Dialog c){
      f=c;
      fj = fen;
    }

    public void mouseReleased(MouseEvent e) {
        if (f.non.contains(e.getX(), e.getY())) {
            System.exit(0);
        } else {
            f.img_non = new ImageIcon("images/non_pas_clique.png");
            f.non.setIcon(f.img_non);
        }


    }

    public void mousePressed(MouseEvent e) {
        f.img_non = new ImageIcon("images/non_clique.png");
        f.non.setIcon(f.img_non);

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}
