/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;

/**
 *
 * @author Adri
 */
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ecouteurs.EcouteurFen_tchat;


public class Fen_tchat extends JFrame{

	public JFrame fen;
	public JTextArea area;
	public JPanel pan_tot;
	public JScrollPane scroll;
	public int position;
	public Fen_jeu f;
	public JSeparator s1,s2,s3;
	public JTextField text;

	public Fen_tchat(Fen_jeu fene)
	{
		f=fene;
		fen = new JFrame();
		area = new JTextArea();
		area.setEditable(false);
		scroll = new JScrollPane(area);
		pan_tot = new JPanel();
		s1 = new JSeparator();
		s2 = new JSeparator();
		s3 = new JSeparator();
		text = new JTextField("");
		//text = new JTextField("Entrez votre texte ici...");

		this.add("\nLancement de la fenetre de discussion...\n");
		fen.setSize(300,395);

		pan_tot.setLayout(null);

		text.setBounds(10,310,280,50);
		s1.setBounds(30,298,240,10);
		s2.setBounds(10,303,280,10);
		s3.setBounds(30,308,240,10);
		pan_tot.setBounds(0,0,300,500);
		scroll.setBounds(10,10,280,290);

		pan_tot.add(s1);
		pan_tot.add(s2);
		pan_tot.add(s3);
		pan_tot.add(text);

		pan_tot.add(scroll);
		fen.add(pan_tot);

		fen.setContentPane(pan_tot);
		fen.setTitle("Fenetre de discussion");
		fen.setLocation(10,214);
		fen.setResizable(false);
		fen.setVisible(true);
		text.addKeyListener(new EcouteurFen_tchat(this,f));
		text.setFocusable(true);
	}

	public void add(String ch)
	{
		area.append(ch);
		position = position + ch.length();
		area.setCaretPosition(position);
	}

}
