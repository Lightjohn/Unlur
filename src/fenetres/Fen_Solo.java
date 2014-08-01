/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ecouteurs.EcouteurAnnulerSousMenuNouvellePartie;
import ecouteurs.Ecouteur_Fen_solo;

/**
 *
 * @author jox
 */
public class Fen_Solo {
	public Fen_Dialog f;
	public JButton facile, moyen;
	public Fen_jeu fj;
	public JPanel p;
	public JTextField joueur;
	public JLabel nom, niveau, taille;
	public EcouteurAnnulerSousMenuNouvellePartie a;
	public JRadioButton six,huit;

    public Fen_Solo(Fen_jeu fen){
        fj = fen;

        f = new Fen_Dialog(fen.f, "Jouer contre Luxo", true);
        f.setSize(400,250);
        p = new JPanel() {

            public void paintComponent(Graphics g) {
                Image background = new ImageIcon("images/metal.png").getImage();
                g.drawImage(background, 0, 0, null);
                repaint();
            }
        };

        //bouton retour
        a = new EcouteurAnnulerSousMenuNouvellePartie(fj, f);        
        f.retour.addMouseListener(a);


        
        joueur = new JTextField(System.getProperty("user.name"));
        nom=new JLabel("Nom du Joueur");
        taille=new JLabel("Taille du plateau de jeu");
        taille.setForeground(Color.blue);
        niveau=new JLabel("Niveau de Luxo");
        niveau.setForeground(Color.blue);

        six = new JRadioButton("Petit");
        six.setMnemonic(KeyEvent.VK_S);
        six.setActionCommand("Petit");
        six.setFocusPainted(false);
        six.setBorderPainted(false);
        six.setContentAreaFilled(false);

        huit = new JRadioButton("Grand");
        huit.setMnemonic(KeyEvent.VK_H);
        huit.setActionCommand("Grand");
        huit.setFocusPainted(false);
        huit.setBorderPainted(false);
        huit.setContentAreaFilled(false);

        if(fj.taille_plateau_pref == 11)
            {
                six.setSelected(true);
            }
        else{
                huit.setSelected(true);
            }

        ButtonGroup group = new ButtonGroup();
        group.add(six);
        group.add(huit);
       
        nom.setBounds(90, 20, 100, 20);
        joueur.setBounds(205,20,100,20);
        taille.setBounds(135, 60, 250, 20);

        six.setBounds(90, 90, 100, 20);
        huit.setBounds(235, 90, 100, 20);

        niveau.setBounds(150, 120, 100, 20);
        f.facile.setBounds(90, 150, 100, 20);
        f.moyen.setBounds(200, 150, 100, 20);
        f.retour.setBounds(150, 190, 100, 20);

        p.add(nom);
        p.add(joueur);
        p.add(taille);
        p.add(six);
        p.add(huit);
        p.add(niveau);
        p.add(f.facile);
        p.add(f.moyen);
        p.add(f.retour);

        f.facile.addMouseListener(new Ecouteur_Fen_solo(fj,f.facile,f,this));
        f.moyen.addMouseListener(new Ecouteur_Fen_solo(fj,f.moyen,f,this));

        
        p.setLayout(null);
        f.add(p);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((screen.width - f.getSize().width)/2,(screen.height - f.getSize().height)/2);

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
