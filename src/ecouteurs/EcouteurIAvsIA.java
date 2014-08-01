/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fenetres.Fen_jeu;

/**
 *
 * @author lecaultg
 */
public class EcouteurIAvsIA {
    int diff1;
    int diff2;
    Fen_jeu f;

    synchronized public void attendre_coup(){
       // new Ordinateur(f,null,this);
       
        
         
    }

    public EcouteurIAvsIA(int diff1, int diff2,Fen_jeu fen) {
        this.diff1 = diff1;
        this.diff2 = diff2;
        f=fen;
        attendre_coup();
    }
}
