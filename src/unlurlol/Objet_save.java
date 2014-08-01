package unlurlol;

import fenetres.Fen_jeu;

public class Objet_save {
	public caseM [][]mat;
	public int joueur_courant;
	public int nbr_case;
	public joueur joueur1;
	public joueur joueur2;
	public Fen_jeu f;
    
    
    public Objet_save(Fen_jeu fen){
        f = fen;
        joueur_courant = f.joueur_courant;
        nbr_case = f.N;
        joueur1 = new joueur(f.joueurUn.nom,f.joueurUn.couleur);
        joueur2 = new joueur(f.joueurDeux.nom,f.joueurDeux.couleur);
        
        
        copie_matrice();
        //afficher_matrice_sous_forme_text();
    }


    private void copie_matrice() {
        mat = new caseM[nbr_case][nbr_case];
        for(int i = 0 ;i < nbr_case ; i++){
            for(int j = 0; j< nbr_case; j++){
                mat[i][j] = new caseM(0, 0, f.matrice[i][j].etat, 0, null);
                //System.out.println("loc:" + mat[i][j].etat + " dist:" +f.matrice[i][j].etat);
                //mat[i][j].etat = f.matrice[i][j].etat;
            }
        }
    }
    
    
    public void afficher_matrice_sous_forme_text(){
        for(int i=0; i<f.N; i++){
                for(int j=0; j<f.N; j++){
                        
                                //selon etat
                                if(mat[i][j].etat == -1){
                                        System.out.print(mat[i][j].etat+" ");
                                }else{
                                        System.out.print(" "+mat[i][j].etat+" ");
                                }
                }
                System.out.println("");
        }
        System.out.println("");
    }

}
