/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;


import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ecouteurs.EcouteurAnnulerSousMenuNouvellePartie;
import ecouteurs.EcouteurJCJ;

/**
 *
 * @author jox
 */
public class Fen_JcJ {
	public Fen_Dialog f;
	public JPanel p;
	public JFrame fp;
	public  JTextField joueur1, joueur2;
	public JLabel nom1, nom2, taille;
	public Fen_jeu fj;
	public EcouteurJCJ j;
	public EcouteurAnnulerSousMenuNouvellePartie a;

    public Fen_JcJ(Fen_jeu fen){
        f = new Fen_Dialog(fen.f, "Jouer contre un ami", true);
        f.setSize(400,250);
        p = new JPanel() {

            public void paintComponent(Graphics g) {
                Image background = new ImageIcon("images/metal.png").getImage();
                g.drawImage(background, 0, 0, null);
                repaint();
            }
        };
        fj = fen;

        nom1=new JLabel("Nom du Joueur1");
        joueur1 = new JTextField(System.getProperty("user.name"));
        nom2=new JLabel("Nom du Joueur2");
        joueur2 = new JTextField(fj.nom_joueur2);
        taille=new JLabel("Taille du plateau de jeu");
        taille.setForeground(Color.blue);


        JRadioButton six = new JRadioButton("Petit");
        six.setMnemonic(KeyEvent.VK_S);
        six.setActionCommand("petit");
        six.setFocusPainted(false);
        six.setBorderPainted(false);
        six.setContentAreaFilled(false);

        JRadioButton huit = new JRadioButton("Grand");
        huit.setMnemonic(KeyEvent.VK_H);
        huit.setActionCommand("grand");
        huit.setFocusPainted(false);
        huit.setBorderPainted(false);
        huit.setContentAreaFilled(false);



        if(fj != null && fj.taille_plateau_pref == 11){
            six.setSelected(true);
        } else {
            huit.setSelected(true);
        }

        ButtonGroup group = new ButtonGroup();
        group.add(six);
        group.add(huit);

        

        //bouton jouer        
        j = new EcouteurJCJ(fj,f,group,this);
        f.go.addMouseListener(j);

        
        //bouton annuler
        a = new EcouteurAnnulerSousMenuNouvellePartie(fj, f);
        f.retour.addMouseListener(a);
        

        nom1.setBounds(85, 20, 120, 20);
        joueur1.setBounds(205,20,100,20);
        nom2.setBounds(85, 50, 120, 20);
        joueur2.setBounds(205,50,100,20);
        taille.setBounds(130, 100, 250, 20);

        six.setBounds(90, 130, 100, 20);
        huit.setBounds(245, 130, 100, 20);

        f.go.setBounds(90, 180, 100, 20);
        f.retour.setBounds(210, 180, 100, 20);

        p.add(nom1);
        p.add(joueur1);
        p.add(nom2);
        p.add(joueur2);
        p.add(taille);
        p.add(six);
        p.add(huit);
        p.add(f.go);
        p.add(f.retour);


        p.setLayout(null);
        f.add(p);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((screen.width - f.getSize().width)/2,(screen.height - f.getSize().height)/2);

        f.setVisible(true);
        f.setDefaultCloseOperation(Fen_Dialog.DISPOSE_ON_CLOSE);
    }

}
