/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import fenetres.Fen_Preferences;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurMJPreferences implements ActionListener {
    Fen_jeu f;
    JFrame jf;
    public EcouteurMJPreferences(Fen_jeu fen){
        f = fen;
      jf=f.f;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Fen_Preferences p = new Fen_Preferences(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EcouteurMJPreferences.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
