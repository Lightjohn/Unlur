
package ecouteurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fenetres.Fen_jeu;
import unlurlol.JouerReseau;
import unlurlol.Reseau;
import unlurlol.caseM;

public class EcouteurSourisReseau implements MouseListener {
    Fen_jeu f;
    Reseau r;
    JouerReseau JR;

    public EcouteurSourisReseau(Fen_jeu fen,Reseau res,JouerReseau JRlolz){
        f=fen;
        r=res;
        JR=JRlolz;
    }
    
    public void mouseClicked(MouseEvent e) {
        //System.out.println(f.doit_sauvegarde);
        int i,j;
        caseM tmp=null;
        int jcvd;
        if(f.Reseau_a_joue==true)
            tmp=JR.jouer(e.getX(),e.getY());
        if(tmp!=null){
            i=tmp.i;
            j=tmp.j;
            jcvd=f.joueur_courant;
            //System.out.println("jcourant envoye "+f.joueur_courant);
            if(f.Reseau_a_joue==true){
                if(f.doit_clignote){
                    f.arreter_thread_clignotant();
                    f.activer_thread_clignotant(tmp);
                }
                r.envoyer_case(i, j, jcvd);
                f.Reseau_a_joue=false;
                f.choixnoir.setEnabled(false);
               
            }
        }




    }
    





    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
