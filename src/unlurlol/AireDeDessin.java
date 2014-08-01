package unlurlol;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import fenetres.Fen_jeu;


/* @author a@dri*/

public class AireDeDessin extends JComponent{

	public Fen_jeu f;

	public Graphics2D drawable;

	public int current_x;
	public int current_y;
	public int tuto_x;
	public int tuto_y;
	public int bulle_x;
	public int bulle_y;
	public int fleche_x;
	public int fleche_y;
	public int plateau_x;
	public int plateau_y;

	public JButton choixnoir;

	public Image img;
	public Image img_mot;
	public Image img_fleche;
	public Image img_fg;
	public Image img_fd;
	public Image img_fh;
	public Image img_45;
	public Image img_135;
	public Image img_noir;
	public Image img_blanc;
	public Image img_rouge;
	public Image img_noir_trans;
	public Image img_blanc_trans;
	public Image img_fond;
	public Image img_tuto;
	public Image img_didac_1;
	public Image img_didac_2;
	public Image img_bulle;
	public Image img_bulle_1;
	public Image img_bulle_2;
	public Image img_plateaunom;
	public Image img_plateaubouton;
	public Image img_6_noir;
	public Image img_6_blanc;
	public Image img_8_noir;
	public Image img_8_blanc;
	public Image  img_plateau_tuto;

	public Polygon poly_prop_coup;



public AireDeDessin(Fen_jeu fen) throws IOException
{
f = fen;

 img_fd = ImageIO.read(new File("images/flechedroite.png"));
 img_fg = ImageIO.read(new File("images/flechegauche.png"));
 img_fh = ImageIO.read(new File("images/flechehaut.png"));
 img_45 = ImageIO.read(new File("images/fleche45.png"));
 img_135 = ImageIO.read(new File("images/fleche135.png"));
 img_noir = ImageIO.read(new File("images/noir.png"));
 img_blanc = ImageIO.read(new File("images/blanc.png"));
 img_noir_trans = ImageIO.read(new File("images/noir_trans.png"));
 img_blanc_trans = ImageIO.read(new File("images/blanc_trans.png"));
 img_rouge = ImageIO.read(new File("images/vert.png"));
 img_didac_1 = ImageIO.read(new File("images/ampoule_tuto.png"));
 img_didac_2 = ImageIO.read(new File("images/ampoule_tuto2.png"));
 img_fond = ImageIO.read(new File("images/fond.png"));
 img_plateaunom = ImageIO.read(new File("images/plateau_noms.png"));
 img_plateaubouton = ImageIO.read(new File("images/plateau_noms.png"));
 img_bulle_1 = ImageIO.read(new File("images/bulle_msg.png"));
 img_bulle_2 = ImageIO.read(new File("images/bulle_msg2.png"));
 img_6_noir = ImageIO.read(new File("images/plateau_6_noir.png"));
 img_6_blanc = ImageIO.read(new File("images/plateau_6_blanc.png"));
 img_8_noir = ImageIO.read(new File("images/plateau_8_noir.png"));
 img_8_blanc = ImageIO.read(new File("images/plateau_8_blanc.png"));

 current_x = 0;
 current_y = 0;
}

synchronized public void paintComponent(Graphics g)
  {
        
            drawable = (Graphics2D) g;
            if (f.N == 11) {
                try {
                    img = ImageIO.read(new File("images/plateau_6.png"));
                } catch (IOException ex) {
                    Logger.getLogger(AireDeDessin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.N == 15) {
                try {
                    img = ImageIO.read(new File("images/plateau_8.png"));
                } catch (IOException ex) {
                    Logger.getLogger(AireDeDessin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

             drawable.drawImage(img_fond, -100,0, this); //affichage du fond
            
           
            //affichage du plateau
            //affichage du plateau des noms
            if (f.N == 11) {
                drawable.drawImage(img_plateaunom,-80,60, this);
                drawable.drawImage(img_plateaubouton,580,60, this);
                drawable.drawImage(img, 239, 79, this);
            } else {
                drawable.drawImage(img_plateaunom,-95,120, this);
                drawable.drawImage(img_plateaubouton,670,120, this);
                drawable.drawImage(img, 205, 77, this);
            }
            //affichage de la boule à coté du joueur
            if (f.joueurUn.couleur == 1) {
                if (f.N == 11) {
                    g.drawImage(img_noir,105,180, this);
                } else {
                    g.drawImage(img_noir, 90, 230, this);
                }
            } else {
                if (f.N == 11) {
                    g.drawImage(img_blanc, 105,180, this);
                } else {
                    g.drawImage(img_blanc, 90, 230, this);
                }
            }
            if (f.joueurDeux.couleur == 1) {
                if (f.N == 11) {
                    g.drawImage(img_noir, 105, 330, this);
                } else {
                    g.drawImage(img_noir, 90, 380, this);
                }
            } else {
                if (f.N == 11) {
                    g.drawImage(img_blanc, 105, 330, this);
                } else {
                    g.drawImage(img_blanc, 90, 380, this);
                }
            }
            //affichage de la fleche + attribution de la couleur pour les boules
            //transparantes
            if (f.joueur_courant == 1) {
                if (f.N == 11) {
                    g.drawImage(img_fg,140,180, this);
                    g.drawImage(img_fd,45,180, this);
                    f.abandonner_j1.setEnabled(true);
                    f.abandonner_j2.setEnabled(false);
                    f.img_abandonner_indispo_j2 = new ImageIcon("images/abandonner_indispo.png");
                    f.abandonner_j2.setDisabledIcon(f.img_abandonner_indispo_j2);
                } else {
                    g.drawImage(img_fg, 125, 230, this);
                    g.drawImage(img_fd, 30, 230, this);
                    f.abandonner_j1.setEnabled(true);
                    f.abandonner_j2.setEnabled(false);
                    f.img_abandonner_indispo_j2 = new ImageIcon("images/abandonner_indispo.png");
                    f.abandonner_j2.setDisabledIcon(f.img_abandonner_indispo_j2);
                }
                if (f.Reseau_a_joue == true) {
                    if (f.joueurUn.couleur == 1) {
                        img_mot = img_noir_trans;
                    } else {
                        img_mot = img_blanc_trans;
                    }
                }
            } else {
                if (f.N == 11) {
                    g.drawImage(img_fg,140,330, this);
                    g.drawImage(img_fd,45,330, this);
                    f.abandonner_j2.setEnabled(true);
                    f.abandonner_j1.setEnabled(false);
                    f.img_abandonner_indispo_j1 = new ImageIcon("images/abandonner_indispo.png");
                    f.abandonner_j1.setDisabledIcon(f.img_abandonner_indispo_j1);
                } else {
                    g.drawImage(img_fg, 125, 380, this);
                    g.drawImage(img_fd, 30, 380, this);
                    f.abandonner_j2.setEnabled(true);
                    f.abandonner_j1.setEnabled(false);
                    f.img_abandonner_indispo_j1 = new ImageIcon("images/abandonner_indispo.png");
                    f.abandonner_j1.setDisabledIcon(f.img_abandonner_indispo_j1);
                }
                if (f.Reseau_a_joue == true) {
                    if (f.joueurDeux.couleur == 1) {
                        img_mot = img_noir_trans;
                    } else {
                        img_mot = img_blanc_trans;
                    }
                }
            }
            //affichage des boules sur le plateau + affichage des boules transparantes
         if(f.activer_tuto == false)
             {
            for (int i = 0; i < f.N; i++)
            {
                for (int j = 0; j < f.N; j++)
                {
                    if (f.matrice[i][j].couleur == 1)
                        {
                        drawable.drawImage(img_noir, f.matrice[i][j].pos_x + 1, f.matrice[i][j].pos_y - 6, this);
                        }
                    else if (f.matrice[i][j].couleur == 5)
                        {
                        drawable.drawImage(img_rouge, f.matrice[i][j].pos_x + 1, f.matrice[i][j].pos_y - 6, this);
                        }
                    else if (f.matrice[i][j].couleur == 2)
                        {
                        g.drawImage(img_blanc, f.matrice[i][j].pos_x + 1, f.matrice[i][j].pos_y - 6, this);

                        }
                    if (f.outils.estPossibleClic(f.matrice[i][j]) && f.matrice[i][j].etat == 0 && f.matrice[i][j].poly.contains(current_x, current_y) && f.Reseau_a_joue)
                        {
                        if( f.ecvIA==null ||  f.ecvIA.tour_joueur==true)
                            drawable.drawImage(img_mot, f.matrice[i][j].pos_x + 1, f.matrice[i][j].pos_y - 6, f.aire);
                                
                        }


                    if (f.matrice[i][j].etat > 0 && f.res == null)
                        {
                        f.it_sauv.setEnabled(true);
                        }
                }
            }
            }
            /****************** didacticiel *********************/
            if(f.activer_tuto == true)
            {
             f.aire.drawable.drawImage(img_plateau_tuto,plateau_x,plateau_y,f.aire);
             f.aire.drawable.drawImage(img_tuto,tuto_x,tuto_y,f.aire);
             f.aire.drawable.drawImage(img_bulle,bulle_x,bulle_y,f.aire);
             f.aire.drawable.drawImage(img_fleche,fleche_x,fleche_y,f.aire);
            }

             /********** hexagone pour proposition de coup *************/

             if(poly_prop_coup != null)
                 {
                  f.aire.drawable.setStroke(new BasicStroke(7));
                  f.aire.drawable.setColor(Color.blue);
                  f.aire.drawable.drawPolygon(poly_prop_coup);  
                 }

           /********** affichage de l'ellipse pour choisir les noirs *************/
            
          if(f.tracer_eclipse == true)
              {
            this.drawable.setStroke(new BasicStroke(7));
            this.drawable.setColor(Color.blue);
            if(f.N == 11){this.drawable.drawOval(705,320,150,80);}
            else{this.drawable.drawOval(795,380,150,80);}
            
              }
}
}


