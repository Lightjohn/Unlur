/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import fenetres.Fen_jeu;





/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class AffichageAvecDelai implements ActionListener {

    Fen_jeu f;
    Timer t;
    int index;
    int taille;
    ArrayList<caseM> L;
    int i, j;


    public AffichageAvecDelai(Fen_jeu a, int delai, ArrayList<caseM> Liste) {

        f = a;
        L = Liste;
        index = 0;
        taille = Liste.size();
        i = -1;
        j = -1;
        
        t = new Timer(delai, this);
        t.start();
    }

    @Override
    synchronized public void actionPerformed(ActionEvent e) {
        

        if(i == -1 && j == -1){
            caseM c = L.get(index);
            if(f.matrice[c.i][c.j].etat == 0){
                f.matrice[c.i][c.j].etat = 5;
            }
            i = c.i;
            j = c.j;
            f.aire.repaint();
            index++;
            if(index == taille)
                t.stop();
        }else{
            f.matrice[i][j].etat = 0;
            i = -1;
            j =-1;
            f.aire.repaint();
        }
    }
}
