
package fenetres;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ecouteurs.Ecouteur_regle;

public class Fen_Regle {
	public Toolkit tool;
	public Dimension screen;
	public Dimension dim_img;
	public JFrame frame;
	public ImageIcon img;
    public JPanel panel;
    public Image image;
    public JButton droite;
    public JButton gauche;
    public Fen_Dessin_regle fen_dessin;
    public String [] fichier;
    public JLabel posi;
    public int position;
    public int width = 556;
    public int height = 720;

    
    public Fen_Regle(){
        fichier = new String[5];
        for(int i =1; i<6;i++){
            fichier[i-1] = new String("images/unlur_regle"+i+".png"); 
        }
        position = 0;
        tool = Toolkit.getDefaultToolkit();
        //dim_img = new Dimension(556, 720);
        dim_img = new Dimension(556+120, 720);
        frame = new JFrame();
        fen_dessin = new Fen_Dessin_regle("images/unlur_regle1.png",dim_img,this);
        fen_dessin.setBounds(0, 0,width+120, height);
        //boutons
        droite = new JButton();
        droite.setIcon(new ImageIcon("images/flechedroite.png"));
        droite.setBounds(width +25 , height/2, 100, 100);
        droite.setBorderPainted(false);
        droite.addMouseListener(new Ecouteur_regle("droite",this));
        droite.setBackground(Color.white);
        
        gauche = new JButton();
        gauche.setIcon(new ImageIcon("images/flechegauche.png"));
        gauche.setBounds(-10, height/2, 100, 100);
        gauche.setBorderPainted(false);
        gauche.addMouseListener(new Ecouteur_regle("gauche",this));
        gauche.setBackground(Color.white);
        gauche.setEnabled(false);
        
        
        //panel = new JPanel();
        //panel.setLayout(null);
        //panel.add(new Fen_Dessin_regle("unlur_regle1.png",dim_img));
        frame.setLayout(null);
        //frame.setBackground(Color.white);
        frame.add(gauche);
        frame.add(droite);
        frame.add(fen_dessin);
        frame.setSize(width +120, height);
        frame.setVisible(true);
    }
    
    
    
    
}
