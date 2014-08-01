

package unlurlol;

import java.awt.Polygon;



public class caseM {
	public int i;
	public int j;
	public int etat;
	public int pos_x;  //position de x et de y pour le 1er point
	public int pos_y;
	public int numero_floyd;
	public int couleur;
	public Polygon poly;
	public int [] x;
	public int [] y;

    //etat est une couleur de la caseM
	public int numcote; 
    //numcote est le numero de la côte où se trouve cette caseM
    //par def. numcote = 0 si cette caseM ne touche pas le bord
    //numcote = 1 si cette caseM touche le bord du coté haut
    //numcote = 2 si cette caseM touche le bord du coté haut à droite
    //numcote = 3 si cette caseM touche le bord du coté bas à droite
    //numcote = 4 si cette caseM touche le bord du coté bas
    //numcote = 5 si cette caseM touche le bord du coté bas à gauche
    //numcote = 6 si cette caseM touche le bord du coté haut à gauche
    //numcote = 12. 23. 34. 45. 56. 61 si cette caseM appartient au deux
    

    public caseM(int cx,int cy,int e, int cote,Polygon p){
        numcote = cote;
        i=cx;
        j=cy;
        etat=e; //(= -1, =0, =1 ou =2)
        couleur = etat;

       poly=p;
       x = new int[6];
       y = new int[6];

    }
}
