/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fenetres.Fen_jeu;
import fenetres.Fen_tchat;

/**
 *
 * @author Adri
 */
public class EcouteurFen_tchat implements KeyListener{

	public Fen_tchat ft;
	public Fen_jeu fenetre;
	public EcouteurFen_tchat(Fen_tchat f,Fen_jeu fj)
	{
		ft = f;
		fenetre=fj;
	}


	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == ke.VK_ENTER)
		{
			fenetre.res.envoyer_texte(fenetre.nom_joueur1+": " +ft.text.getText()+"\n");
			ft.add(ft.text.getText()+"\n");
			ft.text.setText("");

		}
	}


	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke) {}
}
