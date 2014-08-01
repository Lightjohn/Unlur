/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fenetres.Fen_Dialog;
import fenetres.Fen_NouvellePartie;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurAnnulerSousMenuNouvellePartie implements MouseListener {
    Fen_jeu fj;;
    Fen_Dialog f;

    
    public EcouteurAnnulerSousMenuNouvellePartie(Fen_jeu fen, Fen_Dialog c){
      f=c;
      fj=fen;
    }

    public void mouseReleased(MouseEvent e) {
        if(f.retour.contains(e.getX(), e.getY())){
            f.setVisible(false);
            try {
                Fen_NouvellePartie n = new Fen_NouvellePartie(fj);
            } catch (IOException ex) {
                Logger.getLogger(EcouteurAnnulerSousMenuNouvellePartie.class.getName()).log(Level.SEVERE, null, ex);
            }
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
