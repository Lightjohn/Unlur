/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fenetres.Fen_Dialog;
import fenetres.Fen_Solo;
import fenetres.Fen_jeu;
import unlurlol.VariablesIA;

/**
 *
 * @author Adri
 */
public class Ecouteur_Fen_solo implements MouseListener{

	public Fen_jeu f;
	public JButton b;
	public Fen_Dialog jfr;
	public Fen_Solo fs;

	public Ecouteur_Fen_solo(Fen_jeu fen, JButton but, Fen_Dialog jf,Fen_Solo aaa)
	{
		f = fen;
		b = but;
		jfr = jf;
		fs = aaa;
	}


	public void mouseReleased(MouseEvent e) {

		if (b.getName() == "Facile") {
			if (jfr.facile.contains(e.getX(), e.getY())) {
				System.out.println("Facile");
				try {
					f.modifier_preferences(3,fs.joueur.getText());
					if(fs.six.isSelected() == true){f.modifier_preferences(0,"11");}
					if(fs.huit.isSelected() == true){f.modifier_preferences(0,"15");}

					SwingUtilities.invokeLater(new Fen_jeu(null, false, -1, new VariablesIA(true, 3, 0)));
					f.f.dispose();
					jfr.dispose();
				} catch (FileNotFoundException ex) {
					Logger.getLogger(Ecouteur_Fen_solo.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(Ecouteur_Fen_solo.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				jfr.img_facile = new ImageIcon("images/facile_pas_clique.png");
				jfr.facile.setIcon(jfr.img_facile);
			}
		}
		if (b.getName() == "Moyen") {
			if (jfr.moyen.contains(e.getX(), e.getY())) {
				System.out.println("Moyen");
				try {

					if(fs.six.isSelected() == true){f.modifier_preferences(0,"11");}
					if(fs.huit.isSelected() == true){f.modifier_preferences(0,"15");}
					f.modifier_preferences(3, fs.joueur.getText());


					SwingUtilities.invokeLater(new Fen_jeu(null, false, -1, new VariablesIA(true, 2, 0)));
				} catch (FileNotFoundException ex) {
					Logger.getLogger(Ecouteur_Fen_solo.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(Ecouteur_Fen_solo.class.getName()).log(Level.SEVERE, null, ex);
				}
				f.f.dispose();
				jfr.dispose();

			} else {
				jfr.img_moyen = new ImageIcon("images/moyen_pas_clique.png");
				jfr.moyen.setIcon(jfr.img_moyen);
			}
		}       
	}

	public void mousePressed(MouseEvent e) {

		if(b.getName() == "Facile"){
			jfr.img_facile = new ImageIcon("images/facile_clique.png");
			jfr.facile.setIcon(jfr.img_facile);
		}
		if(b.getName() == "Moyen"){
			jfr.img_moyen = new ImageIcon("images/moyen_clique.png");
			jfr.moyen.setIcon(jfr.img_moyen);
		}

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}


	//    public void actionPerformed(ActionEvent ae)
	//    {
	//    if(b.getName() == "Facile")
	//        {
	//            System.out.println("Facile");
	//            try {
	//                SwingUtilities.invokeLater(new Fen_jeu(null, false, -1, new VariablesIA(true, 1, 0)));
	//                 f.f.dispose();
	//                 jfr.dispose();
	//            } catch (FileNotFoundException ex) {
	//                Logger.getLogger(Ecouteur_Fen_solo.class.getName()).log(Level.SEVERE, null, ex);
	//            } catch (IOException ex) {
	//                Logger.getLogger(Ecouteur_Fen_solo.class.getName()).log(Level.SEVERE, null, ex);
	//            }
	//        }
	//     if(b.getName() == "Moyen")
	//        {
	//         System.out.println("Moyen");
	//            try {
	//                SwingUtilities.invokeLater(new Fen_jeu(null, false, -1, new VariablesIA(true, 1, 0)));
	//                f.f.dispose();
	//                jfr.dispose();
	//            } catch (FileNotFoundException ex) {
	//                Logger.getLogger(Ecouteur_Fen_solo.class.getName()).log(Level.SEVERE, null, ex);
	//            } catch (IOException ex) {
	//                Logger.getLogger(Ecouteur_Fen_solo.class.getName()).log(Level.SEVERE, null, ex);
	//            }
	//        }
	//
	//    }

}
