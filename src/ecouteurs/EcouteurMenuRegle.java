/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fenetres.Fen_Regle;

/**
 *
 * @author light
 */
public class EcouteurMenuRegle implements ActionListener {

    public EcouteurMenuRegle(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Fen_Regle();
        
    }
    
}
