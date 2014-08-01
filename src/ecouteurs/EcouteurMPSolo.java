/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;

import javax.swing.*;

import fenetres.Fen_NouvellePartie;
import fenetres.Fen_Solo;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurMPSolo implements MouseListener {
    JFrame fen_fond;
    Fen_NouvellePartie f;
    Fen_jeu fj;

    public EcouteurMPSolo(Fen_jeu fene,Fen_NouvellePartie c){
      f=c;
      fj = fene;
    }

    @Override

    public void mouseReleased(MouseEvent e) {
        if(f.solo.contains(e.getX(), e.getY())){
            f.f.dispose();
            Fen_Solo p = new Fen_Solo(fj);
        } else {
            f.img_solo = new ImageIcon("images/pve_pas_clique.png");
            f.solo.setIcon(f.img_solo);
        }
        

    }

    @Override
    public void mousePressed(MouseEvent e) {
        f.img_solo = new ImageIcon("images/pve_clique.png");
        f.solo.setIcon(f.img_solo);


    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

}
