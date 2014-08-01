/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;

import fenetres.Fen_SauverQuitter;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurQuitter implements ActionListener {

    Fen_jeu f;
    public EcouteurQuitter(Fen_jeu c){
      f=c;
      //System.out.println("lala");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(f.doit_sauvegarde){
            Fen_SauverQuitter s = new Fen_SauverQuitter(f);
        } else {
            System.exit(0);
        }
    }

}
