/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import fenetres.Fen_jeu;

/**
 *
 * @author Adri
 */
public class Ecouteur_tuto implements ActionListener
{
Fen_jeu f;
JButton b;

public Ecouteur_tuto(Fen_jeu fen,JButton but)
    {
     f = fen;
     b = but;
    }

    public void actionPerformed(ActionEvent ae)
    {
  
        if(b.getName() == "precedent")
            {f.didac.clic = true;

            try {
                f.didac.lancer_bulle(f.didac.bulle_actuelle - 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ecouteur_tuto.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

        if(b.getName() == "suivant")
            {
            //System.out.println("actuelle : "+f.didac.bulle_actuelle);
            f.didac.clic = true;
            try {
                f.didac.lancer_bulle(f.didac.bulle_actuelle + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ecouteur_tuto.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            }

        else if (b.getName() == "retour")
            {
            try {
                f.didac.fin_tuto();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ecouteur_tuto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Ecouteur_tuto.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }

}
