/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;

import javax.swing.*;

import fenetres.Fen_jeu;
import unlurlol.Credits;

/**
 *
 * @author desgrour
 */
public class EcouteurCredits implements ActionListener {

    Fen_jeu f;
    public EcouteurCredits(Fen_jeu fj){
      f=fj;
    }

    public void actionPerformed(ActionEvent e) {
//        f.dispose();
        Credits p = new Credits(f);

    }



}
