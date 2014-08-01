/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.util.logging.Level;
import java.util.logging.Logger;

import unlurlol.Ordinateur;
import fenetres.Fen_jeu;

/**
 *
 * @author light
 */
public class ThreadAnnulerIA implements Runnable {
    
    Fen_jeu f;
    public ThreadAnnulerIA(Fen_jeu fen){
        f = fen;
    }

    @Override
    public void run() {

            try {
                
                Thread.sleep(2000);
                f.IA_doit_dormir = false;
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadAnnulerIA.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
        
        if(f.joueur_courant == 2 && !f.IA_doit_dormir){
            //System.out.println("je me lance");
            new Ordinateur(f, f.ecvIA, null);}
        //else{System.out.println("je ne fais rien");}
    }
    
}
