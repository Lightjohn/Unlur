/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.BasicStroke;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import fenetres.Fen_jeu;
import unlurlol.caseM;

/**
 *
 * @author Adri
 */
public class Ecouteur_coup implements MouseListener
{

    Fen_jeu f;

    public Ecouteur_coup(Fen_jeu f){
        this.f = f;
    }


    public void mouseReleased(MouseEvent e) {

        if (f.coup.contains(e.getX(), e.getY()) && f.coup.isEnabled()) {
            if (f.partie_commencee) {

                caseM p = f.outils.proposer_coup();
                f.aire.poly_prop_coup = p.poly;
                f.aire.repaint();

                if (p != null) {
                    System.out.println("ON PROPOSE LA CASE i :" + p.i + "  j: " + p.j);
                }
            } else {
                caseM p = f.outils.proposer_coup_avant_debut();
                f.aire.poly_prop_coup = p.poly;
                f.aire.repaint();
                if (p != null && p.i == 999 && p.j == 999) {
                    f.tracer_eclipse = true;

                } else if (p != null) {
                    System.out.println("ON PROPOSE LA CASE i :" + p.i + "  j: " + p.j);
                }

            }
            f.img_coup = new ImageIcon("images/ampoule_pas_appuye.png");
            f.coup.setIcon(f.img_coup);
        } else {
            f.img_coup = new ImageIcon("images/ampoule_pas_appuye.png");
            f.coup.setIcon(f.img_coup);
        }
        f.outils.afficher_matrice_sous_forme_text(2);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        f.img_coup = new ImageIcon("images/ampoule_appuye.png");
        f.coup.setIcon(f.img_coup);


    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

//    @Override
//    public void actionPerformed(ActionEvent ae)
//    {   if(f.partie_commencee){
//
//            caseM p=f.outils.proposer_coup();
//            f.aire.poly_prop_coup = p.poly;
//            f.aire.repaint();
//
//            if(p!=null)
//                System.out.println("ON PROPOSE LA CASE i :"+p.i+"  j: "+p.j);
//        }
//
//        else{
//            caseM p=f.outils.proposer_coup_avant_debut();
//            f.aire.poly_prop_coup = p.poly;
//            f.aire.repaint();
//            if(p!=null && p.i==999 && p.j==999){
//               f.tracer_eclipse = true;
//
//            }
//            else if(p!=null){
//               System.out.println("ON PROPOSE LA CASE i :"+p.i+"  j: "+p.j);
//            }
//
//        }
//    }

}
