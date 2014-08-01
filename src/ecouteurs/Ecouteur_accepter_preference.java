/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fenetres.Fen_Preferences;
import fenetres.Fen_jeu;

/**
 *
 * @author Adri
 */
public class Ecouteur_accepter_preference implements MouseListener{

Fen_jeu f;
JButton jb;
Fen_Preferences fr;
 public Ecouteur_accepter_preference(Fen_jeu fen,JButton jbu,Fen_Preferences fe) throws FileNotFoundException
      {
      f = fen;
      jb = jbu;
      fr = fe;
      

      }

      public void mouseReleased(MouseEvent e) {
        if (fr.fd.accepter.contains(e.getX(), e.getY())) {
            if(jb.getName() == "Accepter")
                {
            try {FileWriter fw = new FileWriter("preferences.txt",false);
                 BufferedWriter output = new BufferedWriter(fw);

                     if(fr.six.isSelected() == true)        //on ecrit la taille du plateau
                        {output.write("11\n");
                         f.taille_plateau_pref = 11;}
                     else
                        {output.write("15\n");
                         f.taille_plateau_pref = 15;}

                            output.write(f.niveau_ia+"\n");

                output.write(Boolean.toString(f.activer_tuto)+"\n");  //on ecrit boolean activer_tuto
                output.write(f.nom_joueur1+"\n");                     //on ecrit nom joueur1
                output.write(f.nom_joueur2+"\n");                     //on ecrit nom joueur2
                output.write(f.adresse_ip+"\n");    //on ecrit adresse ip
                if(fr.activer.isSelected() == true)
                    {output.write(true+"\n");
                    f.doit_clignote = true;
                    }
                if(fr.desactiver.isSelected() == true)
                    {output.write(false+"\n");
                    f.doit_clignote = false;
                    }
                
                     output.flush();
                     output.close();
                f.aire.repaint();
                fr.fd.dispose();

            } catch (IOException ex) {
                Logger.getLogger(Ecouteur_accepter_preference.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        } else {
            fr.fd.img_accepter = new ImageIcon("images/accepter_pas_clique.png");
            fr.fd.accepter.setIcon(fr.fd.img_accepter);
        }


    }

    public void mousePressed(MouseEvent e) {
        fr.fd.img_accepter = new ImageIcon("images/accepter_clique.png");
        fr.fd.accepter.setIcon(fr.fd.img_accepter);

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}
