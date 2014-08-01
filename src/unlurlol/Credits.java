/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ecouteurs.EcouteurRetour;
import fenetres.Fen_Dialog;
import fenetres.Fen_jeu;

/**
 *
 * @author romain
 */
public class Credits {

    Fen_Dialog f;
    JTextArea credits;
    JButton retour;
    JPanel p;
    StyledDocument doc;
    MutableAttributeSet center;

    public Credits( Fen_jeu fj){
        f = new Fen_Dialog(fj.f, "A propos", true);
        f.setSize(400,260);

        p = new JPanel() {

            public void paintComponent(Graphics g) {
                Image background = new ImageIcon("images/metal.png").getImage();
                g.drawImage(background, 0, 0, null);
                repaint();
            }
        };

        credits=new JTextArea();
        credits.setOpaque(true);
        
           credits.setBackground(new Color(0, 0, 0, 0));
           credits.setLineWrap(true);
            credits.setWrapStyleWord(true);
            credits.setForeground(Color.blue);
          
        credits.setText("Ce jeu a été réalisé par :\n\n"
                + " Romain DESGROUAS\n"
                + " Alexandre GAUTHIER\n"
                + " Grégory LÉCAULT\n"
                + " Jonathan LEGER\n"
                + " Adrien MOUTARD\n"
                + " Ilya ZVEREV\n");
        
        credits.setEditable(false);
        credits.setBackground(new Color(0, 0, 0, 0));

        credits.setBounds(135,30, 300,160);

        //bouton retour
        f.retour.setBounds(150, 200, 100, 20);
        EcouteurRetour r = new EcouteurRetour(f);
        f.retour.addMouseListener(r);

        

        p.add(credits);
        p.add(f.retour);
        


        p.setLayout(null);
        f.add(p);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((screen.width - f.getSize().width) / 2, (screen.height - f.getSize().height) / 2);

        f.setVisible(true);
        f.setDefaultCloseOperation(Fen_Dialog.DISPOSE_ON_CLOSE);
    }

}
