/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingUtilities;

import fenetres.Fen_jeu;

/**
 *
 * @author Adri
 */
public class main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SwingUtilities.invokeLater(new Fen_jeu(null,false,-1,new VariablesIA(true,2,0)));
	}

}
