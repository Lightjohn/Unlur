/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;
/////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.List;


//Les variables glogales à rajouter dans TestFenetre:

// boolean partie_commencee;
// partie_commencee vaut faux au debut de la partie. Elle passe à vrai au moment quand un des deux joueurs passe (choisi ca couleur).

// int joueur_courant;
// indique le numero du joueur qui a la main (joueur courant). Vaut 1 au début de la partie.

// joueur Joueur1;
// joueur Joueur2;

// outils Moteur;

// caseM [][]Matrice;

/////////////////////////////////////////////////////////

// class caseM {
//	int x;
//	int y;
//	int etat (= -1, =0, =1 ou =2 ou =12);  // 12= quand partie pas commencee pour que les cases qui soient noir du debut
// ne comptent pas comme appartenant par exemple au joueur 2 plus tard meme s'il a pris les blancs.
//}

/////////////////////////////////////////////////////////

//class joueur{
//	String nom;
//	int couleur; //couleur est au debut vaut 1 (noir) pour tout les deux joueurs;
//}

/////////////////////////////////////////////////////////



interface interface_Outils{

	public ArrayList<caseM> successeurs(caseM x);
	//retource une liste des elements de type 'case', une liste de successeurs de la case x.
        public ArrayList<caseM> successeurs_couleur(caseM x);
        //renvoit les successeurs de la case x de la meme couleur.
        public boolean est_voisin_d_un_bord(caseM x,int bord);

	public boolean estBord(caseM x);
	//vaut vrai si la case x est un bord de l'hexagone et faux sinon.

	public boolean estPossibleClic(caseM x);
	//vaut vrai si le joueur a le droit de cliquer sur la case x et faux sinon.
	//faut noter que si la partie n'est pas initialisée, cad si la couleur blanche n'a pas encore été attribué à un des deux joueurs, ce cas est
	//testé dans cette methode et on ne peut pas cliquer sur le bord dans ce cas.

        //**** update est possible Clic prend un numero de joueur et nom la strucutre Joueur avec joueur 1 ou joueur 2 dedans
        //**** faire le test a l'interieur de la fonction.... if(joueur==1) alors Joueur1 sinon ...etc
        
        
	public void changerEtat(caseM x, int joueur);
	//change l'état de la case x dans la matrice globale à la couleur du numéro joueur.
    
        public void remplir_matrice();
        /* Remplit les differents cases de la matrice f.matrice.
         * Utilisé dans les differents tests.
         */
        
        public void remplir_matrice_a_vide();
        /* Cette action remplit une matrice f.matrice à vide,
         * Cad, les états à 0 si à l'intérieur de l'hexagone et -1 si en dehors;
         * les numcote à des valeurs correspondants (voir caseM.java pour numcote).
         */
        
        public void afficher_matrice_sous_forme_text(int selon);
        /* Affiche la matrice f.matrice dans le terminal sous forme textuelle.
         * Les lignes correspondent aux premières coordonnées de f.matrice,
         * les colonnes aux deuxièmes.
         * On peut voir la matrice f.matrice comme ceci:
         *       /////
         *      /////
         *     /////
         * Si on incline f.matrice, on peut voir l'hexagone à l'intérieur.
         */
          public ArrayList<caseM> prendre_de_cote_diff_ponts_bords(int numcote, ArrayList<caseM>L1);
        public boolean estVictoire_ponts(int joueur);
        /*Renvoit vrai si le joueur a un chemins contenant des ponts lui permettant de gagner */

        public ArrayList<caseM> estVictoire_retourne_chemin(int joueur);

        /*Retourne le chemin gagnant */
         public ArrayList<caseM> estVictoire_retourne_chemin_ponts(int joueur);
         /*Retourne un chemin contenant des ponts lui permettant de gagner */

	public boolean estVictoire(int joueur);
	//vrai si le joueur j gagne la partie.
	//cette methode utilise des methodes existe_chemin.
         
}
