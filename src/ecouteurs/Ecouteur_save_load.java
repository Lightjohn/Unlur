/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JMenuItem;

import fenetres.Fen_jeu;
import unlurlol.Didacticiel;

/**
 *
 * @author adri
 */
public class Ecouteur_save_load implements ActionListener{

    Fen_jeu f;
    JMenuItem i;
    
    public Ecouteur_save_load(Fen_jeu fen,JMenuItem item)
    {
    f = fen;
    i = item;
    }
    
    @Override
public void actionPerformed(ActionEvent ae)
    {
           
      if(i.getText() == "Charger")
        {
            try {
                f.sl.Charger();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ecouteur_save_load.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Ecouteur_save_load.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      if(i.getText() == "Sauver")
        {
            try {
                f.sl.Sauver();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ecouteur_save_load.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Ecouteur_save_load.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }

       if(i.getText() == "Tutoriel")
           {
            try {
                f.thread_tuto = new Thread(f.didac = new Didacticiel(f));
                f.thread_tuto.start();
            } catch (IOException ex) {
                Logger.getLogger(Ecouteur_save_load.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
    }
    
}
