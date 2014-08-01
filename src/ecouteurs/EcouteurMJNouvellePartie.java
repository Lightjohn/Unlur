/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fenetres.Fen_NouvellePartie;
import fenetres.Fen_SauverNouvelle;
import fenetres.Fen_jeu;

/**
 *
 * @author romain
 */
public class EcouteurMJNouvellePartie implements ActionListener {

    Fen_jeu f;
   
    public EcouteurMJNouvellePartie(Fen_jeu fen){
      f=fen;
      
    }

    public void actionPerformed(ActionEvent e) {
            if(f.doit_sauvegarde){
                Fen_SauverNouvelle s = new Fen_SauverNouvelle(f);
                f.doit_sauvegarde = false;
            }
            else{
                try {
                    Fen_NouvellePartie n = new Fen_NouvellePartie(f);
                } catch (IOException ex) {
                    Logger.getLogger(EcouteurMJNouvellePartie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }


}
