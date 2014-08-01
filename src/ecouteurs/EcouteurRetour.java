/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteurs;

import java.awt.event.*;

import javax.swing.ImageIcon;

import fenetres.Fen_Dialog;

/**
 *
 * @author desgrour
 */
public class EcouteurRetour implements MouseListener {

    Fen_Dialog f;

    public EcouteurRetour(Fen_Dialog c) {
        f = c;
    }

    public void mouseReleased(MouseEvent e) {
        if(f.retour.contains(e.getX(), e.getY())){
            f.dispose();
        } else {
            f.img_retour = new ImageIcon("images/retour_pas_clique.png");
            f.retour.setIcon(f.img_retour);
        }

    }

    public void mousePressed(MouseEvent e) {
        f.img_retour = new ImageIcon("images/retour_clique.png");
        f.retour.setIcon(f.img_retour);

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
   
}