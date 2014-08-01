/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenetres;

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ecouteurs.EcouteurRetour;
import ecouteurs.Ecouteur_accepter_preference;

/**
 *
 * @author romain
 */
public class Fen_Preferences {

	public Fen_Dialog fd;
	public JLabel taille, IA, clign;
	public JPanel p;
	public Fen_jeu f;
	public JRadioButton six , huit , facile , moyen, activer, desactiver;
	public ButtonGroup cligno, group, groupe;

    public Fen_Preferences(Fen_jeu fen) throws FileNotFoundException{
        fd = new Fen_Dialog(fen.f, "Preférences", true);
        fd.setSize(400,220);

        p = new JPanel() {

            public void paintComponent(Graphics g) {
                Image background = new ImageIcon("images/metal.png").getImage();
                g.drawImage(background, 0, 0, null);
                repaint();
            }
        };

        f = fen;

        //clignotement des coups
        clign = new JLabel("Clignotement des coups de Luxo");

        activer = new JRadioButton("Activer");
        activer.setActionCommand("Activer");
        activer.setFocusPainted(false);
        activer.setBorderPainted(false);
        activer.setContentAreaFilled(false);

        desactiver = new JRadioButton("Desactiver");
        desactiver.setActionCommand("Desactiver");
        desactiver.setFocusPainted(false);
        desactiver.setBorderPainted(false);
        desactiver.setContentAreaFilled(false);

        if(f.doit_clignote){
            activer.setSelected(true);
        } else {
            desactiver.setSelected(true);
        }

        cligno = new ButtonGroup();
        cligno.add(activer);
        cligno.add(desactiver);

        //taille du plateau
        taille=new JLabel("Taille du plateau de jeu");

        six = new JRadioButton("Petit");
        six.setActionCommand("Petit");
        six.setFocusPainted(false);
        six.setBorderPainted(false);
        six.setContentAreaFilled(false);
        

        huit = new JRadioButton("Grand");
        huit.setActionCommand("Grand");
        huit.setFocusPainted(false);
        huit.setBorderPainted(false);
        huit.setContentAreaFilled(false);


        //selection de la taille du plateau
        if(f.taille_plateau_pref == 11){six.setSelected(true);}
        else if(f.taille_plateau_pref== 15){huit.setSelected(true);}


        group = new ButtonGroup();
        group.add(six);
        group.add(huit);


//        //niveau de l'IA
//        IA=new JLabel("Niveau de Luxo");
//        facile = new JRadioButton("Facile");
//        facile.setActionCommand("Facile");
//        facile.setFocusPainted(false);
//        facile.setBorderPainted(false);
//        facile.setContentAreaFilled(false);
//
//        moyen = new JRadioButton("Moyen");
//        moyen.setActionCommand("Moyen");
//        moyen.setFocusPainted(false);
//        moyen.setBorderPainted(false);
//        moyen.setContentAreaFilled(false);
//
//        //selection du niveau de difficulté de l'ia
//        if(f.niveau_ia == 1){facile.setSelected(true);}
//        else if(f.niveau_ia == 2){moyen.setSelected(true);}
//
//        groupe = new ButtonGroup();
//        groupe.add(facile);
//        groupe.add(moyen);

        //placement des boutons
        clign.setBounds(100, 20, 250, 20);
        activer.setBounds(75, 50, 100, 20);
        desactiver.setBounds(225, 50, 100, 20);
        
        taille.setBounds(125, 80, 250, 20);
        six.setBounds(75, 110, 100, 20);
        huit.setBounds(225,110, 100, 20);

//        IA.setBounds(150, 140, 100, 20);
//        facile.setBounds(75, 170, 100, 20);
//        moyen.setBounds(225, 170, 100, 20);

        fd.accepter.setBounds(75, 150, 100, 20);
        fd.retour.setBounds(225, 150, 100, 20);


/**************** personalisation des menus et des Jcomponents ****************/
        //penser a modifier la police !
        //Font ff = new Font("Palatino", Font.PLAIN, 14);

        clign.setForeground(Color.blue);
        taille.setForeground(Color.blue);
//        IA.setForeground(Color.blue);

/******************************************************************************/

        p.add(clign);
        p.add(activer);
        p.add(desactiver);

        p.add(taille);
        p.add(six);
        p.add(huit);

//        p.add(IA);
//        p.add(facile);
//        p.add(moyen);

        p.add(fd.accepter);
        p.add(fd.retour);

        fd.retour.addMouseListener(new EcouteurRetour(fd));
        fd.accepter.addMouseListener(new Ecouteur_accepter_preference(f,fd.accepter,this));

        p.setLayout(null);
        fd.add(p);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        fd.setLocation((screen.width - fd.getSize().width)/2,(screen.height - fd.getSize().height)/2);

        fd.setVisible(true);
        fd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

}
