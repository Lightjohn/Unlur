/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fenetres.Fen_Dialog;
import fenetres.Fen_jeu;
import unlurlol.Reseau;
import unlurlol.VariablesIA;

/**
 *
 * @author desgrour
 */
public class EcouteurRecommencer implements MouseListener{

	Fen_jeu fj;
	Fen_Dialog fd;
	Reseau r;
	boolean crea;
	int taille;
	VariablesIA IAz;

	public EcouteurRecommencer ( Fen_jeu f, Fen_Dialog d ){
		fj=f;
		fd=d;
		r = fj.res;
		crea = fj.createur;
		taille = fj.N;
		IAz = fj.mode_IA;
	}

	//    @Override
	//    public void actionPerformed(ActionEvent e) {
	//        try {
	//            Fen_jeu p = new Fen_jeu(r, crea, -1, IAz);
	//            fj.f.dispose();
	//            p.run();
	//        } catch (FileNotFoundException ex) {
	//            Logger.getLogger(EcouteurRecommencer.class.getName()).log(Level.SEVERE, null, ex);
	//        } catch (IOException ex) {
	//            Logger.getLogger(EcouteurRecommencer.class.getName()).log(Level.SEVERE, null, ex);
	//        }
	//    }



	@Override
	public void mousePressed(MouseEvent e) {
		fd.img_recommencer = new ImageIcon("images/recommencer_clique.png");
		fd.recommencer.setIcon(fd.img_recommencer);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(fd.recommencer.contains(e.getX(), e.getY())){
			try {
				Fen_jeu p = new Fen_jeu(r, crea, -1, IAz);
				fj.f.dispose();
				p.run();
			} catch (FileNotFoundException ex) {
				Logger.getLogger(EcouteurRecommencer.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(EcouteurRecommencer.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			fd.img_recommencer = new ImageIcon("images/recommencer_pas_clique.png");
			fd.recommencer.setIcon(fd.img_recommencer);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
