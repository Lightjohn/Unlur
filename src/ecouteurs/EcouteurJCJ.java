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

import javax.swing.*;

import fenetres.Fen_Dialog;
import fenetres.Fen_JcJ;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurJCJ implements MouseListener {

	Fen_Dialog f;
	ButtonGroup g;
	Fen_jeu fj;
	Fen_JcJ jcj;

	public EcouteurJCJ(Fen_jeu fen, Fen_Dialog c, ButtonGroup group,Fen_JcJ jcja){
		f=c;
		g=group;
		fj = fen;
		jcj = jcja;
	}

	public void mouseReleased(MouseEvent e) {

		try {
			fj.modifier_preferences(4, jcj.joueur2.getText());
			fj.modifier_preferences(3, jcj.joueur1.getText());
		} catch (FileNotFoundException ex) {
			Logger.getLogger(EcouteurJCJ.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(EcouteurJCJ.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (f.go.contains(e.getX(), e.getY())) {
			try {
				ButtonModel selection = g.getSelection();
				if (selection.getMnemonic() == KeyEvent.VK_S && fj != null) {
					fj.taille_plateau_pref = 11;
					fj.modifier_preferences(0,"11");
					System.out.println("on modifie preferences ! ");
					f.dispose();
					fj.f.dispose();

					Fen_jeu f = new Fen_jeu(null, false,-1, null);
					f.mode_j1 = f.MODE_UTIL;
					f.mode_j2 = f.MODE_UTIL;
					SwingUtilities.invokeLater(f);

				} else {
					if (fj != null) {
						fj.taille_plateau_pref = 15;
						fj.modifier_preferences(0,"15");
						f.dispose();
						fj.f.dispose();

						Fen_jeu f = new Fen_jeu(null, false,-1, null);
						f.mode_j1 = f.MODE_UTIL;
						f.mode_j2 = f.MODE_UTIL;
						SwingUtilities.invokeLater(f);
					}

				}


			} catch (FileNotFoundException ex) {
				Logger.getLogger(EcouteurJCJ.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(EcouteurJCJ.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			f.img_go = new ImageIcon("images/jouer_pas_clique.png");
			f.go.setIcon(f.img_go);
		}

	}

	public void mousePressed(MouseEvent e) {
		f.img_go = new ImageIcon("images/jouer_clique.png");
		f.go.setIcon(f.img_go);

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

}
