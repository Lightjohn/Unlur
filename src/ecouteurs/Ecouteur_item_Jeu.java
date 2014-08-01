/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JMenu;

import fenetres.Fen_jeu;

/**
 *
 * @author adri
 */
public class Ecouteur_item_Jeu implements MouseListener{

    Fen_jeu f;
    JMenu jm;
    
    public Ecouteur_item_Jeu(Fen_jeu f, JMenu jm)
    {
    this.f = f; 
    this.jm = jm;
    }

    public void mouseClicked(MouseEvent me) {
        if(f.activer_tuto == true)
            {
            try {
                f.didac.fin_tuto();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ecouteur_item_Jeu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Ecouteur_item_Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }

    
public void mousePressed(MouseEvent me) {}    
public void mouseReleased(MouseEvent me) {}    
public void mouseEntered(MouseEvent me) {}
public void mouseExited(MouseEvent me) {}
    
}
