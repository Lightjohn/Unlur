/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import fenetres.Fen_jeu;

/**
 *
 * @author Adri
 */
public class EcouteurMotionFen_jeu implements MouseMotionListener{

    Fen_jeu f;
    private BufferedImage img;

    public EcouteurMotionFen_jeu(Fen_jeu fen)
    {
      f = fen;
    }

    public void mouseDragged(MouseEvent me)
    {
    
    }

    public void mouseMoved(MouseEvent me)
    {

        if(f.activer_tuto == false)
            {
        f.aire.current_x = me.getX();
        f.aire.current_y = me.getY();
        f.aire.repaint();
            }
        /*if((f.joueur_courant == 2 && f.MODE_ORDI_2 != 0))
        {
            
        }
        else{*/
//            f.aire.current_x = me.getX();
//            f.aire.current_y = me.getY();
//            f.aire.repaint();
        
        //}
    }

}
