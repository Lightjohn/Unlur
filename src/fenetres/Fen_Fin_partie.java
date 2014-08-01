
package fenetres;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ecouteurs.EcouteurMenu;
import ecouteurs.EcouteurRecommencer;
import ecouteurs.Ecouteur_Fen_fin;

public class Fen_Fin_partie {

    boolean victoire;
    int j;
    Fen_jeu fj;
    Fen_Dialog f;
    JPanel panel;
    Dimension ecran;
    JButton continuer, quitter, retour_menu;
    JTextArea message;

    public Fen_Fin_partie(Fen_jeu fenetre, boolean b, int joueur){
        j= joueur;
        victoire = b;
        fj = fenetre;
        f= new Fen_Dialog(fj.f, "Fin de la partie", true);

        f.setSize(520,325);
        panel=new JPanel(){
            public void paintComponent(Graphics g) {
                Image background = new ImageIcon("images/metal.png").getImage();
                g.drawImage(background, 0, 0, null);
                repaint();
            }
        };
        
        //bouton recommencer
        f.recommencer.setBounds(90, 200, 100, 20);
        f.recommencer.addMouseListener(new EcouteurRecommencer(fj, f));

        //bouton quitter
        f.quitter.setBounds(210, 200, 100, 20);
        f.quitter.addMouseListener(new Ecouteur_Fen_fin(f));

        //bouton menu
        f.menu.setBounds(330, 200, 100, 20);
        f.menu.addMouseListener(new EcouteurMenu(fj, f));
        
        //texte
        Font ff = new Font(null, Font.BOLD, 22);
        message = new JTextArea();
        message.setText(choix_texte());
        message.setForeground(Color.blue);
        message.setFont(ff);
        message.setBackground(new Color(0, 0, 0, 0));
        message.setEditable(false);
        message.setBounds(170,50, 400, 100);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
            
        
        panel.add(message);
        panel.add(f.menu);
        panel.add(f.quitter);
        panel.add(f.recommencer);
        panel.setLayout(null);
        f.add(panel);        

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((screen.width - f.getSize().width)/2,(screen.height - f.getSize().height)/2);

        f.setVisible(true);
        f.setDefaultCloseOperation(Fen_Dialog.DISPOSE_ON_CLOSE);
    
    }
    
    
    private String choix_texte(){
        if(j == 1){
            if (fj.mode_IA != null) {
                return fj.nom_joueur1 + " a gagné !!!\n\n" + " Luxo a perdu ...";
            } else {
                return fj.nom_joueur1 + " a gagné !!!\n\n" + fj.nom_joueur2 + " a perdu ...";
            }
        }
        else{
            if (fj.mode_IA != null) {
                return "Luxo a gagné !!!\n\n" + fj.nom_joueur1 + " a perdu ...";
            } else {
                return fj.nom_joueur2 + " a gagné !!!\n\n" + fj.nom_joueur1 + " a perdu ...";
            }
        }
    
    }
    
}
