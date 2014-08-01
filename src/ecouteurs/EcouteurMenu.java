/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import fenetres.Fen_Dialog;
import fenetres.Fen_NouvellePartie;
import fenetres.Fen_jeu;

/**
 *
 * @author desgrour
 */
public class EcouteurMenu implements MouseListener {

	public Fen_jeu fj;
	public Fen_Dialog fd;

	public EcouteurMenu(Fen_jeu f, Fen_Dialog d) {
		fj=f;
		fd=d;
	}

	//    @Override
	//    public void actionPerformed(ActionEvent e) {
	//        fd.dispose();
	//        try {
	//            Fen_NouvellePartie fn = new Fen_NouvellePartie(fj);
	//        } catch (IOException ex) {
	//            Logger.getLogger(EcouteurMenu.class.getName()).log(Level.SEVERE, null, ex);
	//        }
	//
	//    }



	@Override
	public void mousePressed(MouseEvent e) {
		fd.img_menu = new ImageIcon("images/menu_clique.png");
		fd.menu.setIcon(fd.img_menu);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(fd.menu.contains(e.getX(), e.getY())){
			fd.dispose();
			try {
				Fen_NouvellePartie fn = new Fen_NouvellePartie(fj);
			} catch (IOException ex) {
				Logger.getLogger(EcouteurMenu.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			fd.img_menu = new ImageIcon("images/menu_pas_clique.png");
			fd.menu.setIcon(fd.img_menu);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

}
