/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import fenetres.Fen_Dialog;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurOuiNouvelle implements MouseListener {

	Fen_Dialog f;
	Fen_jeu fj;

	public EcouteurOuiNouvelle(Fen_jeu fen, Fen_Dialog c){
		f=c;
		fj = fen;
	}

	public void mouseReleased(MouseEvent e) {
		if (f.oui.contains(e.getX(), e.getY())) {
			fj.doit_sauvegarde = false;
			if (fj != null) {
				if (fj.res != null) {

					fj.tchat.fen.dispose();
				}
			}
			try {
				f.dispose();
				fj.sl.Sauver();
			} catch (FileNotFoundException ex) {
				Logger.getLogger(Ecouteur_save_load.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(Ecouteur_save_load.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			f.img_oui = new ImageIcon("images/oui_pas_clique.png");
			f.oui.setIcon(f.img_oui);
		}


	}

	public void mousePressed(MouseEvent e) {
		f.img_oui = new ImageIcon("images/oui_clique.png");
		f.oui.setIcon(f.img_oui);

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

}
