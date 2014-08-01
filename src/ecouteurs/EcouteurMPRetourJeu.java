/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;

import javax.swing.*;

import fenetres.Fen_NouvellePartie;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurMPRetourJeu implements MouseListener {

    Fen_NouvellePartie f;
    Fen_jeu fj;
    public EcouteurMPRetourJeu(Fen_NouvellePartie c, Fen_jeu fp){
      f=c;
      fj=fp;
    }

    public void mouseReleased(MouseEvent e) {
        if(f.retour.contains(e.getX(), e.getY())){
            f.f.dispose();
        } else {
            f.img_retour = new ImageIcon("images/retour_jeu_pas_clique.png");
            f.retour.setIcon(f.img_retour);
        }
    }

    public void mousePressed(MouseEvent e) {
        f.img_retour = new ImageIcon("images/retour_jeu_clique.png");
        f.retour.setIcon(f.img_retour);

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    


}
