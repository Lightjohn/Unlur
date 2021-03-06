/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import ecouteurs.EcouteurAnnuler;
import ecouteurs.EcouteurNonNouvelle;
import ecouteurs.EcouteurOuiNouvelle;

/**
 *
 * @author desgrour
 */
public class Fen_SauverNouvelle {

	Fen_Dialog f;
	JLabel confirm;
	JPanel p;
	EcouteurOuiNouvelle o;
	EcouteurNonNouvelle n;
	EcouteurAnnuler a;

	public Fen_SauverNouvelle(Fen_jeu fj) {

		f = new Fen_Dialog(fj.f, "Information", true);
		f.setSize(400, 140);

		p = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon("images/metal.png").getImage();
				g.drawImage(background, 0, 0, null);
				repaint();
			}
		};

		confirm = new JLabel("Voulez-vous enregistrer ?");
		confirm.setBounds(100, 30, 190, 20);

		//bouton oui
		f.oui.setBounds(40, 60, 100, 20);
		o = new EcouteurOuiNouvelle(fj, f);
		f.oui.addMouseListener(o);


		//bouton non
		f.non.setBounds(150, 60, 100, 20);
		n = new EcouteurNonNouvelle(fj, f);
		f.non.addMouseListener(n);


		//bouton annuler
		f.annuler.setBounds(260, 60, 100, 20);
		a = new EcouteurAnnuler(f);
		f.annuler.addMouseListener(a);


		p.add(confirm);
		p.add(f.oui);
		p.add(f.non);
		p.add(f.annuler);

		p.setLayout(null);
		f.add(p);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation((screen.width - f.getSize().width) / 2, (screen.height - f.getSize().height) / 2);

		f.setVisible(true);
		f.setDefaultCloseOperation(Fen_Dialog.DISPOSE_ON_CLOSE);

	}

}
