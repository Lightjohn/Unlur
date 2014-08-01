
package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;

import fenetres.Fen_Dialog;
import fenetres.Fen_jeu;
import thread.Thread_reseau;
import unlurlol.Reseau;

public class EcouteurCreerReseau implements MouseListener {

    Fen_jeu fp;
    Fen_Dialog fenetreR;
    ButtonGroup g;
    int N;

    public EcouteurCreerReseau(Fen_Dialog f, ButtonGroup group, Fen_jeu fen){
        fp=fen;
        fenetreR=f;
        g=group;
    }

    public void mouseReleased(MouseEvent e) {
        if(fenetreR.creer.contains(e.getX(), e.getY())){
            try {
                ButtonModel selection = g.getSelection();
                if (selection.getMnemonic() == KeyEvent.VK_S) {
                    N = 11;

                } else {
                    N = 15;

                }
                fenetreR.dispose();
                fp.f.dispose();
                Reseau r = new Reseau();
                Fen_jeu f = new Fen_jeu(r, true, N, null);
                f.run();
                f.est_serveur =true;


                f.f.setAlwaysOnTop(true);
                f.f.setAlwaysOnTop(false);
                f.Reseau_a_joue = false;
                f.choixnoir.setEnabled(false);
                

                Thread t = new Thread(new Thread_reseau(f));
                t.start();

            } catch (Exception ex) {
                System.out.println(ex);
            } 
        } else {
            fenetreR.img_creer = new ImageIcon("images/creer_pas_clique.png");
            fenetreR.creer.setIcon(fenetreR.img_creer);
        }


    }

    public void mousePressed(MouseEvent e) {
        fenetreR.img_creer = new ImageIcon("images/creer_clique.png");
        fenetreR.creer.setIcon(fenetreR.img_creer);

    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}


//    public void actionPerformed(ActionEvent e) {
//        try {
//            ButtonModel selection = g.getSelection();
//            if(selection.getMnemonic()==KeyEvent.VK_S){
//                N=11;
//
//            }
//            else{
//                N=15;
//
//            }
//            fenetreR.dispose();
//            fp.f.dispose();
//            Reseau r = new Reseau();
//            r.affiche_ip();
//            Fen_jeu f=new Fen_jeu(r,true, N,null);
//            f.run();
//
//
//            f.f.setAlwaysOnTop(true);
//            f.f.setAlwaysOnTop(false);
//            f.Reseau_a_joue=false;
//            f.choixnoir.setEnabled(false);
//
//
//                Thread t = new Thread(new Thread_reseau(f));
//                t.start();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(EcouteurCreerReseau.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(EcouteurCreerReseau.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        }




    }