/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

/**
 *
 * @author romain
 */
import java.awt.event.*;
import javax.swing.*;

class EcouteurDeBouton implements ActionListener {
    JButton b;
    EcouteurDeBouton(JButton c){
      b=c;
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("Vous avez appuye sur : " + b.getText());
    }

}
