/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;

import javax.swing.*;

import fenetres.Fen_JcJ;
import fenetres.Fen_NouvellePartie;
import fenetres.Fen_jeu;


/**
 *
 * @author desgrour
 */
public class EcouteurMPJcJ implements MouseListener {
    JFrame fen_fond;
    Fen_NouvellePartie f;
    Fen_jeu fj;

    public EcouteurMPJcJ(Fen_jeu fen, Fen_NouvellePartie c){
      f=c;
      fj = fen;
    }

    public void mouseReleased(MouseEvent e) {
        if(f.solo.contains(e.getX(), e.getY()) && fj.activer_tuto == false){
            f.f.dispose();
            Fen_JcJ p = new Fen_JcJ(fj);
        } else {
            f.img_jcj = new ImageIcon("images/pvp_pas_clique.png");
            f.jcj.setIcon(f.img_jcj);
        }
        

    }

    public void mousePressed(MouseEvent e) {
        f.img_jcj = new ImageIcon("images/pvp_clique.png");
        f.jcj.setIcon(f.img_jcj);

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    

}
