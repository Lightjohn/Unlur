/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;

import javax.swing.*;

import fenetres.Fen_NouvellePartie;
import fenetres.Fen_Reseau;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurMPReseau implements MouseListener {
    Fen_jeu fj;
    Fen_NouvellePartie f;
    
    public EcouteurMPReseau(Fen_NouvellePartie c,Fen_jeu fen){
      f=c;
      if(fen!=null)fj=fen;
     
    }

    public void mouseReleased(MouseEvent e) {
        if(f.reseau.contains(e.getX(), e.getY()) && fj.activer_tuto == false){
            f.f.dispose();
            Fen_Reseau p = new Fen_Reseau(false, fj);
        } else {
            f.img_reseau = new ImageIcon("images/reseau_pas_clique.png");
            f.reseau.setIcon(f.img_reseau);
        }
        

    }

    public void mousePressed(MouseEvent e) {
        f.img_reseau = new ImageIcon("images/reseau_clique.png");
        f.reseau.setIcon(f.img_reseau);

    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
        

    

}
