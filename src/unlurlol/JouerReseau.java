
package unlurlol;

import java.util.logging.Level;
import java.util.logging.Logger;

import fenetres.Fen_Fin_partie;
import fenetres.Fen_jeu;

public class JouerReseau {
    Fen_jeu f;

    public JouerReseau(Fen_jeu fen){
        f=fen;
    }

    public caseM jouer(int x,int y){
        boolean b=false;

        if(!f.partie_commencee){

            caseM temp = null;
           for(int i=0;i<f.N;i++){
               if(b==false)
                   {
                    for(int j=0;j<f.N;j++){
                        if(f.matrice[i][j].etat!=-1 && f.matrice[i][j].poly.contains(x,y)){
                            b=true;
                            temp=f.matrice[i][j];
                         }
                     }
                   }
             }
             if (temp != null && f.outils.estPossibleClic(temp)){

                f.outils.changerEtat(temp,f.joueur_courant);


                if(f.joueur_courant==1)
                   {f.joueur_courant=2;}
                else
                   {f.joueur_courant=1;}

                f.aire.repaint();
                return temp;
            }
             else
                return null;
        }
        else{

            caseM temp = null;
            for(int i=0;i<f.N;i++){
                if(b==false){
                    for(int j=0;j<f.N;j++){
                        if(f.matrice[i][j].etat!=-1 && f.matrice[i][j].poly.contains(x,y)){
                            temp=f.matrice[i][j];
                            b=true;

                        }
                    }
                }

            }
        
            if (temp != null && f.outils.estPossibleClic(temp)){

                f.outils.changerEtat(temp,f.joueur_courant);

                if(f.outils.estVictoire(f.joueur_courant)){
                    //System.out.println("JOUEUR"+f.joueur_courant+" gagne.");
                    f.Reseau_a_joue=false;
                    if(f.joueur_courant==1)
                        f.res.envoyer_case(temp.i, temp.j, 2);
                    else
                        f.res.envoyer_case(temp.i, temp.j, 1);
                    f.res.envoyer_Victoire();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println("envoyer defaite erreur dodo");
                    }
                    //f.res.envoyer_Fin_connexion();
                    new Fen_Fin_partie(f, true, f.joueur_courant);
                }

                else if(f.outils.estDefaite(f.joueur_courant)){
                    //System.out.println("Vous avez perdu");
                    f.Reseau_a_joue=false;
                    if(f.joueur_courant==1)
                        f.res.envoyer_case(temp.i, temp.j, 2);
                    else
                        f.res.envoyer_case(temp.i, temp.j, 1);
                    f.res.envoyer_Defaite();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println("envoyer defaite erreur dodo");
                    }
                    //f.res.envoyer_Fin_connexion();
                    new Fen_Fin_partie(f, false, f.joueur_courant);
                }

                if(f.joueur_courant==1)
                    f.joueur_courant=2;
                else
                    f.joueur_courant=1;

                f.aire.repaint();
                return temp;
            }
                     
            else{
                return null;}

        }

            

    }
   

}
