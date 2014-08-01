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
 * @author romain
 */
public class EcouteurAnnuler implements MouseListener {

    Fen_Dialog f;

    public EcouteurAnnuler(Fen_Dialog c) {
        f = c;
    }

    public void mouseReleased(MouseEvent e) {
        if(f.annuler.contains(e.getX(), e.getY())){
            f.dispose();
        } else {
            f.img_annuler = new ImageIcon("images/annuler_pas_clique.png");
            f.annuler.setIcon(f.img_annuler);
        }

    }

    public void mousePressed(MouseEvent e) {
        f.img_annuler = new ImageIcon("images/annuler_clique.png");
        f.annuler.setIcon(f.img_annuler);

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}