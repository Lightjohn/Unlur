
package thread;

import java.awt.Color;
import java.awt.Font;

import fenetres.Fen_jeu;

/**
 *
 * @author light
 */
public class Thread_erreur implements Runnable {

	Fen_jeu f;
	String message;
	public Thread_erreur(Fen_jeu fen,String mess) {
		f = fen;
		message = mess;
	}

	@Override
	synchronized public void run() {        

		f.commentaire.setText(message);
		f.aire.repaint();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			System.out.println("Je peux pas dormir2");
		}
		f.commentaire.setText("");
		f.aire.repaint();
		f.thread_libre = true;

	}

}
