/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import fenetres.Fen_Dialog;

/**
 *
 * @author light
 */
public class Ecouteur_Fen_fin implements MouseListener {

	Fen_Dialog fd;

	public Ecouteur_Fen_fin( Fen_Dialog d) {
		fd=d;
	}



	@Override
	public void mousePressed(MouseEvent e) {
		fd.img_quitter = new ImageIcon("images/quitter_clique.png");
		fd.quitter.setIcon(fd.img_quitter);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(fd.quitter.contains(e.getX(), e.getY())){
			System.exit(0);
		} else {
			fd.img_quitter = new ImageIcon("images/quitter_pas_clique.png");
			fd.quitter.setIcon(fd.img_quitter);
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
