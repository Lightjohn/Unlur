/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;

import java.awt.event.*;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import fenetres.Fen_jeu;

/**
 *
 * @author lecaultg
 */

class StatistiquesPartie implements ActionListener {

    Fen_jeu f;
    Timer t;

    boolean partie_finie; //pour les ensembles de statistiques

    int couleur_joueur_1; //couleur du joueur 1 : noir = 1, blanc = 2
    int couleur_joueur_2; //couleur du joueur 1 : noir = 1, blanc = 2

    int type_ia_joueur_1; //niveau de l'IA du joueur 1 : 1, 2 ...
    int type_ia_joueur_2; //niveau de l'IA du joueur 2 ; 1, 2 ...

    int nb_boules_joueur_1; //nombre de boules noires sur le plateau
    int nb_boules_joueur_2; //nombre de boules blanches sur le plateau

    //nombre de boules noires posées avant qu'un des deux joueurs prennent
    //les noirs :
    int nb_boules_noires_posees_avant_jeu;

    int joueur_gagnant; //joueur gagnant : 1 ou 2, et 0 s'il n'y en a pas

    //si le joueur qui a gagné
    //a réalisé son objectif (estVictoire)
    //ou si c'est l'autre joueur qui l'a réalisé (estDéfaite)
    boolean estVictoire_gagnant;

    //pourcentage pour lequel l'IA va choisir les noirs :
    int pourcentage;

    //nombre de cases totales dans le plateau :
    int nombreCasesTotales;

    //nombre de cases restantes testées pour éviter une boucle infinie :
    int nombreCasesRestantes;

    //nombre de cases centrales, cad le nombre de cases que l'on peut prendre
    //avant la prise des noirs (pour éviter une boucle infinie) :
    int nombreCasesCentrales;

    boolean estCaseCentrale(caseM c) {
        if(f.outils.estBord(c))
            return false;
        else
            return true;
    }

    boolean estObligeDePrendreLesNoirs() {
        return this.nombreCasesCentrales == 0;
    }

    int nombreCaseSelonTaille(int taille) {
        int nombre = 0;
        int cote = (taille + 1) / 2; // 6 ou 8
        for(int i = cote; i < taille; i++)
            nombre += 2 * i;
        nombre += taille;
        return nombre;
    }

    StatistiquesPartie(Fen_jeu f, int diff_IA1, int diff_IA2, int p) {
       
        this.f = f;

        //reinitialisation :
        this.f.jeu_fini = false;
        this.f.matrice = new caseM[this.f.N][this.f.N];
        this.f.outils = new Outils(this.f);
        this.f.partie_commencee = false;
        this.f.joueur_courant = 1;
        this.f.couleur_actuelle = 1;
        this.f.joueurUn = new joueur("joueur1", 1);
        this.f.joueurDeux = new joueur("joueur2", 1);
        this.f.outils.remplir_matrice_a_vide();
        System.out.print("PROUT");
        this.partie_finie = false;

        this.couleur_joueur_1 = 1; //noir au début
        this.couleur_joueur_2 = 1; //noir au début

        this.type_ia_joueur_1 = diff_IA1;
        this.type_ia_joueur_2 = diff_IA2;

        this.nb_boules_joueur_1 = 0;
        this.nb_boules_joueur_2 = 0;

        this.nb_boules_noires_posees_avant_jeu = 0;

        this.joueur_gagnant = 0; //aucun gagnant

        this.estVictoire_gagnant = false;

        this.pourcentage = p;

        this.nombreCasesTotales = this.nombreCaseSelonTaille(f.N);

        this.nombreCasesCentrales = this.nombreCaseSelonTaille(f.N - 2);

        this.nombreCasesRestantes = this.nombreCasesTotales;
                
        this.t = new Timer(1000, this);

        this.t.start();
           
        
    }

    public void afficherStats() {

        String J1, J2, J3, J4;
        String F; // fin du jeu (victoire ou défaite)
        String FJ; // joueur ayant provoqué la fin du jeu
        String V; // vainqueur
        String P; // perdant

        J1 = new String("Joueur 1");
        J2 = new String("Joueur 2");
        J3 = new String("Aucun");
        J4 = new String("Aucun");
        
        switch(this.joueur_gagnant) {
            case 1:
                V = J1;
                P = J2;
                break;
            case 2:
                V = J2;
                P = J1;
                break;
            default:
                V = J3;
                P = J4;
                break;
        }

        if(this.estVictoire_gagnant) {
            F = "Victoire";
            FJ = V;
        }
        else {
            F = "Défaite";
            FJ = P;
        }

        System.out.println("----------- STATISTIQUES -----------");

        System.out.println(" PLATEAU : ");
        System.out.println("    Taille : ");
        if(f.N == 11)
            System.out.println("Moyenne (11 x 11).");
        else
            System.out.println("Grande (15 x 15).");
        System.out.println("    Nombre de cases totales : "
            + this.nombreCasesTotales);
        System.out.println("    Nombre de cases restantes : "
            + this.nombreCasesRestantes);
        System.out.println("");

        System.out.println(" VAINQUEUR : ");
        System.out.println("    " + V + ".");
        System.out.println("");

        System.out.println(" FIN DU JEU : ");
        if(this.joueur_gagnant != 0)
            System.out.println("    " + F + " du " + FJ + ".");
        else
            System.out.println("    Matrice Pleine.");
        System.out.println("");

        System.out.println(" PRISE DES NOIRS : ");
        System.out.println("    " + this.nb_boules_noires_posees_avant_jeu +
            " boules posées.");
        System.out.println("");

        System.out.println(" JOUEUR 1 :");

        System.out.print("    Difficulté : ");
        if(this.type_ia_joueur_1 == 1)
            System.out.println("Facile.");
        else
            System.out.println("Difficile.");

        System.out.print("    Couleur : ");
        if(this.couleur_joueur_1 == 1)
            System.out.println("Noirs.");
        else
            System.out.println("Blancs.");

        System.out.print("    Boules posées : ");
        System.out.println(this.nb_boules_joueur_1 + ".");
        
        System.out.println("");

        System.out.println(" JOUEUR 2 :");

        System.out.print("    Difficulté : ");
        if(this.type_ia_joueur_2 == 1)
            System.out.println("Facile.");
        else
            System.out.println("Difficile.");

        System.out.print("    Couleur : ");
        if(this.couleur_joueur_2 == 1)
            System.out.println("Noirs.");
        else
            System.out.println("Blancs.");

        System.out.print("    Boules posées : ");
        System.out.println(this.nb_boules_joueur_2 + ".");

        System.out.println("------------------------------------");
        System.out.println("");
    }

    @Override
    synchronized public void actionPerformed(ActionEvent e) {
         
        Random r;
        int i, j;
        caseM c = null;
        boolean b;

        b = true;
        r = new Random();

        boolean faireAleatoire = false;
        boolean utiliserIntelligence = false;

        if(f.joueur_courant == 1) {
            if(this.type_ia_joueur_1 == 1)
                faireAleatoire = true;
            else if(this.type_ia_joueur_1 == 2)
                utiliserIntelligence = true;
        }
        else if(f.joueur_courant == 2) {
            if(this.type_ia_joueur_2 == 1)
                faireAleatoire = true;
            else if(this.type_ia_joueur_2 == 2)
                utiliserIntelligence = true;
        }

        if(faireAleatoire && !f.partie_commencee) {
            int click;
            click = r.nextInt(100);
            if(click < this.pourcentage //this.pourcentage % de chances
                || this.estObligeDePrendreLesNoirs()) {
                f.partie_commencee = true;
                if(f.joueur_courant == 1) {
                    f.joueurDeux.couleur = 2;
                    this.couleur_joueur_2 = 2;
                    f.joueur_courant = 2;//////////
                }
                else{
                    f.joueurUn.couleur = 2;
                    this.couleur_joueur_1 = 2;
                    f.joueur_courant = 1;////////
                }
                f.choixnoir.setEnabled(false);
                f.aire.repaint();
                b = false;
                return;
            }
        }

        if(b) {
            if(faireAleatoire) {
                do {
                    i = r.nextInt(f.N);
                    j = r.nextInt(f.N);
                    c = f.matrice[i][j];
                }
                while(!f.outils.estPossibleClic(c));
                f.outils.changerEtat(c, f.joueur_courant);
                f.aire.repaint();
            }
            else if(utiliserIntelligence) {
                //code IA.......................................................
                caseM res;
               
                if(!f.partie_commencee){
                    res=f.outils.proposer_coup_avant_debut();
                    if(res.i!=999 && res.j!=999){
                        System.out.println("__-_-_-_-j ="+f.joueur_courant+"__-_-_-_- ia joue1 "+res.i+","+res.j);
                        f.outils.changerEtat(res, f.joueur_courant);
                        f.aire.repaint();
                        f.outils.init_composante_humain(res);
                    }else{
                        System.out.println("__-_-_-_-j ="+f.joueur_courant+"-_-_-_- APPUI1 sur le bouton");
                        
                        if(f.joueur_courant==1)
                            f.joueurDeux.couleur=2;
                        else
                            f.joueurUn.couleur=2;
                        f.partie_commencee = true;
                        f.choixnoir.setEnabled(false);
                        f.img_choisir = new ImageIcon("images/noir_indispo.png");
                        f.choixnoir.setDisabledIcon(f.img_choisir);
                        f.aire.repaint();
                         int[][] floyd_nouveau = f.outils.remplir_matrice_floyd();
                         int[][] floyd_final= f.outils.floyd_warshall(floyd_nouveau);
                         f.outils.ancienne_composante_ia=null;
                         f.outils.composante_ia=null;
                         f.outils.composante_humain = f.outils.composante_connexe_principale(1,floyd_final);
                    }


                }else{
                    System.out.println("couleur j1 ="+f.joueurUn.couleur+",couleur j2 ="+f.joueurDeux.couleur);
                    res=f.outils.proposer_coup();
                    
//                    System.out.println("__-_-_-_-j ="+f.joueur_courant+"__-_-_-_- ia joue2 "+res.i+","+res.j);
                   if(res == null){
                       System.out.println("res == null");
                    }
                       else{
                           f.outils.changerEtat(res, f.joueur_courant);
                           f.outils.init_composante_humain(res);
                       }

                    f.aire.repaint();
                }


            }

            //on a joué, on doit incrémenter le nombre de boules jouées :
            if(f.joueur_courant == 1)
                this.nb_boules_joueur_1 ++;
            else
                this.nb_boules_joueur_2 ++;

            if(!f.partie_commencee)
                this.nb_boules_noires_posees_avant_jeu ++;
            
            this.nombreCasesRestantes --;

            if(c != null && this.estCaseCentrale(c))
                this.nombreCasesCentrales --;
        }

        //test de la victoire :
        if(f.partie_commencee && f.outils.estVictoire(f.joueur_courant)) {
            f.jeu_fini = true;
            //System.out.println("Le joueur " + f.joueur_courant + " a gagné !!!");
            f.aire.repaint();
            t.stop();

            if(f.joueur_courant == 1)
                this.joueur_gagnant = 1;
            else
                this.joueur_gagnant = 2;

            this.estVictoire_gagnant = true;
            
            this.afficherStats();
            
            this.partie_finie = true;

            return;
       }

        //test de la défaite :
        else if(f.partie_commencee && f.outils.estDefaite(f.joueur_courant)) {
            f.jeu_fini = true;
            //System.out.println("Le joueur " + f.joueur_courant + " a perdu...");
            f.aire.repaint();
            t.stop();
            
            if(f.joueur_courant == 1)
                this.joueur_gagnant = 2;
            else
                this.joueur_gagnant = 1;

            this.estVictoire_gagnant = false;

            this.afficherStats();

            this.partie_finie = true;

            return;
        }

        //test du remplissage complet de matrice :
        else if(this.nombreCasesRestantes == 0) {
            f.jeu_fini = true;
            f.aire.repaint();
            t.stop();

            this.joueur_gagnant = 0;

            this.estVictoire_gagnant = false;

            this.afficherStats();

            this.partie_finie = true;

            return;
        }

         //changement de joueur
        if(f.joueur_courant == 1)
            f.joueur_courant=2;
         else
            f.joueur_courant=1;
    }

}

class Parties implements ActionListener {

    Fen_jeu f;
    int diff_IA1;
    int diff_IA2;
    int p;

    Timer t;
    int index;
    boolean changement;
    int nombre_parties;

    // tableau contenant les statistiques des différentes parties :
    StatistiquesPartie [] tableau_de_stats;

    Parties(Fen_jeu f, int diff_IA1, int diff_IA2, int p, int c) {
        this.f = f;
        this.diff_IA1 = diff_IA1;
        this.diff_IA2 = diff_IA2;
        this.p = p;
        this.index = 0;
        this.changement = true;
        this.nombre_parties = c;
        this.tableau_de_stats = new StatistiquesPartie[c];
        this.t = new Timer(500, this);
        this.t.start();
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        
        if(this.changement) {
            this.tableau_de_stats[this.index] = new StatistiquesPartie(
                this.f, this.diff_IA1, this.diff_IA2, this.p);
            this.changement = false;
        }
        if(this.tableau_de_stats[this.index].partie_finie) {
            this.index ++;
            this.changement = true;
        }
        if(this.index == this.nombre_parties) {
            int nombre_gagnees_joueur_1 = 0; //premier a avoir la main
            int nombre_gagnees_joueur_2 = 0;
            int nombre_gagnees_joueur_blanc = 0;
            int nombre_gagnees_joueur_noir = 0;
            int nombre_gagnees_IA_niveau_1 = 0;
            int nombre_gagnees_IA_niveau_2 = 0;
            int nombre_gagnees_IAn1_contre_IAn2 = 0;
            int nombre_gagnees_IAn2_contre_IAn1 = 0;

            StatistiquesPartie stats_partie_courante;

            for(int i = 0; i < this.nombre_parties; i++) {
                stats_partie_courante = this.tableau_de_stats[i];
                switch(stats_partie_courante.joueur_gagnant) {
                    case 1:
                        /******************************************************/
                        nombre_gagnees_joueur_1 ++;
                        /******************************************************/
                        if(stats_partie_courante.couleur_joueur_1 == 1)
                            nombre_gagnees_joueur_noir ++;
                        else
                            nombre_gagnees_joueur_blanc ++;
                        /******************************************************/
                        if(stats_partie_courante.type_ia_joueur_1 == 1) {
                            nombre_gagnees_IA_niveau_1 ++;
                            if(stats_partie_courante.type_ia_joueur_2 == 2)
                                nombre_gagnees_IAn1_contre_IAn2 ++;
                        }
                        else if(stats_partie_courante.type_ia_joueur_1 == 2) {
                            nombre_gagnees_IA_niveau_2 ++;
                            if(stats_partie_courante.type_ia_joueur_2 == 1)
                                nombre_gagnees_IAn2_contre_IAn1 ++;
                        }
                        /******************************************************/
                        break;
                    case 2:
                        /******************************************************/
                        nombre_gagnees_joueur_2 ++;
                        /******************************************************/
                        if(stats_partie_courante.couleur_joueur_2 == 1)
                            nombre_gagnees_joueur_noir ++;
                        else
                            nombre_gagnees_joueur_blanc ++;
                        /******************************************************/
                        if(stats_partie_courante.type_ia_joueur_2 == 1) {
                            nombre_gagnees_IA_niveau_1 ++;
                            if(stats_partie_courante.type_ia_joueur_1 == 2)
                                nombre_gagnees_IAn1_contre_IAn2 ++;
                        }
                        else if(stats_partie_courante.type_ia_joueur_2 == 2) {
                            nombre_gagnees_IA_niveau_2 ++;
                            if(stats_partie_courante.type_ia_joueur_1 == 1)
                                nombre_gagnees_IAn2_contre_IAn1 ++;
                        }
                        /******************************************************/
                        break;
                    default:
                        break;
                }
            }

            System.out.println("Nombre de parties jouées : " + nombre_parties);

            System.out.println("Nombre de victoires du joueur 1 : "
                + nombre_gagnees_joueur_1 + " / " + nombre_parties);
            System.out.println("Nombre de victoires du joueur 2 : "
                + nombre_gagnees_joueur_2 + " / " + nombre_parties);

            System.out.println("Nombre de victoires du joueur noir : "
                + nombre_gagnees_joueur_noir + " / " + nombre_parties);
            System.out.println("Nombre de victoires du joueur blanc : "
                + nombre_gagnees_joueur_blanc + " / " + nombre_parties);

            System.out.println("Nombre de victoires du niveau 1 : "
                + nombre_gagnees_IA_niveau_1 + " / " + nombre_parties);
            System.out.println("Nombre de victoires du niveau 2 : "
                + nombre_gagnees_IA_niveau_2 + " / " + nombre_parties);

            System.out.println("Nombre de victoires du niveau 1 contre le "
                + "niveau 2 : " + nombre_gagnees_IAn1_contre_IAn2
                + " / " + nombre_parties);
            System.out.println("Nombre de victoires du niveau 2 contre le "
                + "niveau 1 : " + nombre_gagnees_IAn2_contre_IAn1
                + " / " + nombre_parties);

            this.t.stop();
        }
    }
}

public class Statistiques {
    Parties stats_parties;
    public Statistiques(Fen_jeu f, int diff_IA1, int diff_IA2, int p, int c) {
        stats_parties = new Parties(f, diff_IA1, diff_IA2, p, c);
    }
}