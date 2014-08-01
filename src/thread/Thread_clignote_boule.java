/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.util.logging.Level;
import java.util.logging.Logger;

import unlurlol.caseM;
import fenetres.Fen_jeu;

/**
 *
 * @author light
 */
public class Thread_clignote_boule implements Runnable {
    
    caseM casez;
    Fen_jeu f;
    public Thread_clignote_boule(caseM cas,Fen_jeu fen){
        casez = cas;
        f = fen;
    }
    
    @Override
    public void run() 
    {
        try {
            int etat_save = casez.etat;
            f.couleur_clignotante = etat_save;
            casez.couleur = 5;
            for(int i = 0; i < 5; i++){
                    
                    f.aire.repaint();
                    Thread.sleep(200);
                    casez.couleur = etat_save;
                    f.aire.repaint();
                    Thread.sleep(200);
                    casez.couleur = 5;
                    f.aire.repaint();
                }
            casez.couleur = etat_save;
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_clignote_boule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
