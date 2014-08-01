/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import fenetres.Fen_Dialog;
import fenetres.Fen_NouvellePartie;
import fenetres.Fen_jeu;

/**
 *
 * @author romain
 */
public class EcouteurNonNouvelle implements MouseListener {

	Fen_Dialog f;
	Fen_jeu fj;

	public EcouteurNonNouvelle(Fen_jeu fen, Fen_Dialog c){
		f=c;
		fj = fen;
	}

	public void mouseReleased(MouseEvent e){
		if (f.non.contains(e.getX(), e.getY())) {
			fj.doit_sauvegarde = false;
			if (fj != null) {
				if (fj.res != null) {

					fj.tchat.fen.dispose();
				}
				try {
					f.dispose();
					Fen_NouvellePartie n = new Fen_NouvellePartie(fj);
				} catch (IOException ex) {
					Logger.getLogger(EcouteurNonNouvelle.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} else {
			f.img_non = new ImageIcon("images/non_pas_clique.png");
			f.non.setIcon(f.img_non);
		}


	}

	public void mousePressed(MouseEvent e) {
		f.img_non = new ImageIcon("images/non_clique.png");
		f.non.setIcon(f.img_non);

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

}
