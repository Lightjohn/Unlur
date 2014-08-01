package unlurlol;

import java.awt.Polygon;
import java.util.*;

import javax.swing.ImageIcon;

import fenetres.Fen_Fin_partie;
import fenetres.Fen_jeu;

//UNLUR ACCELERE
public class Outils implements interface_Outils {
    Fen_jeu f;
    private boolean joueur_press_button;
    private class liaison_floyd{
        int poids;
        ArrayList<caseM> list;

        public liaison_floyd(int indice, ArrayList<caseM> list) {
            this.poids = indice;
            this.list = list;
        }


    }
    private boolean[][] MatriceDeMarquage;
    private int [][] Floyd;
    private int [][] Floyd_coup_courant;
    ArrayList<caseM> composante_ia;
    ArrayList<caseM> ancienne_composante_ia;
    ArrayList<caseM> composante_humain;
    private boolean variable_une_boule_sur_plateau;
    private int[][] pred_floyd;
   // public liaison_floyd [][] matrice_liaison_courante;
    private ConversionFloyd [] numero_vers_case;
    public int nbcoup_restantJ1=500,nbcoup_restantJ2=500;
    public int[] objectif_bordJ1,objectif_bordJ2;
    private caseM [] Bord_deja_repare_blancs;
    private caseM [] Bord_deja_repare_noirs;
    public ArrayList<caseM> chemin_restant_J1=new ArrayList<caseM>(),chemin_restant_J2=new ArrayList<caseM>();

        int cote_poly = 20;
        int pos_x = 350;
        int pos_y = 100;
    private ArrayList<Joker2> Joker2;
    private ArrayList<Joker1> Joker1;
    public Outils(Fen_jeu fen){
        f=fen;
        composante_ia = null;
        composante_humain = null;
        MatriceDeMarquage = new boolean[f.N][f.N];
        Joker2=new ArrayList<Joker2>();
        Joker1=new ArrayList<Joker1>();

        objectif_bordJ1=new int[3];
        objectif_bordJ2=new int[3];
        Bord_deja_repare_blancs=new caseM [6];
        Bord_deja_repare_noirs=new caseM [6];
        for(int k=0;k<6;k++){
            Bord_deja_repare_blancs[k]=null;
            Bord_deja_repare_noirs[k]=null;
        }
        if(f.N==11){
            Floyd=new int[91][91];
            pred_floyd=new int[91][91];
           numero_vers_case=new ConversionFloyd[91];
        }
        else{
            Floyd=new int[169][169];
            pred_floyd=new int[169][169];
            numero_vers_case=new ConversionFloyd[169];

        }
        joueur_press_button = false;
        variable_une_boule_sur_plateau = true;
    }

    //MARCHE successeurs
    //MARCHE estBord

    public void init_joker2(){
     caseM pont1,pont2,pont3,pont4;

      if(f.N==11){
        Joker2 tmp;
        pont1=f.matrice[3][1];
        pont2=f.matrice[1][0];
        pont3=f.matrice[0][1];
        pont4=f.matrice[1][3];
        tmp=new Joker2(f.matrice[2][2], 6,1,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[1][3];
        pont2=f.matrice[0][4];
        pont3=f.matrice[1][6];
        pont4=f.matrice[3][7];
        tmp=new Joker2(f.matrice[2][5], 1,2,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[3][7];
        pont2=f.matrice[4][9];
        pont3=f.matrice[6][10];
        pont4=f.matrice[7][9];
        tmp=new Joker2(f.matrice[5][8], 2,3,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[7][9];
        pont2=f.matrice[9][10];
        pont3=f.matrice[10][9];
        pont4=f.matrice[9][7];
        tmp=new Joker2(f.matrice[8][8], 3,4,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[9][7];
        pont2=f.matrice[10][6];
        pont3=f.matrice[9][4];
        pont4=f.matrice[7][3];
        tmp=new Joker2(f.matrice[8][5], 4,5,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[3][1];
        pont2=f.matrice[4][0];
        pont3=f.matrice[6][1];
        pont4=f.matrice[7][3];
        tmp=new Joker2(f.matrice[5][2], 5,6,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
       }
    else{
        Joker2 tmp;
        pont1=f.matrice[3][1];
        pont2=f.matrice[1][0];
        pont3=f.matrice[0][1];
        pont4=f.matrice[1][3];
        tmp=new Joker2(f.matrice[2][2], 6,1,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[1][5];
        pont2=f.matrice[0][6];
        pont3=f.matrice[1][8];
        pont4=f.matrice[3][9];
        tmp=new Joker2(f.matrice[2][7], 1,2,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[5][11];
        pont2=f.matrice[6][13];
        pont3=f.matrice[8][14];
        pont4=f.matrice[6][13];
        tmp=new Joker2(f.matrice[7][12], 2,3,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[11][13];
        pont2=f.matrice[13][14];
        pont3=f.matrice[14][13];
        pont4=f.matrice[13][11];
        tmp=new Joker2(f.matrice[12][12], 3,4,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[13][9];
        pont2=f.matrice[14][8];
        pont3=f.matrice[13][6];
        pont4=f.matrice[11][5];
        tmp=new Joker2(f.matrice[12][7], 4,5,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);
        pont1=f.matrice[5][1];
        pont2=f.matrice[6][0];
        pont3=f.matrice[8][1];
        pont4=f.matrice[9][3];
        tmp=new Joker2(f.matrice[7][2], 5,6,pont1,pont2,pont3,pont4);
        Joker2.add(tmp);


    }

    }
    public void init_joker1(){
       Joker1 tmp;
       caseM pont1,pont2,pont3;
        if(f.N==11){
            pont1=f.matrice[1][1];
            pont2=f.matrice[0][2];
            pont3=f.matrice[1][4];
            tmp=new Joker1(f.matrice[2][3], 1,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[1][2];
            pont2=f.matrice[0][3];
            pont3=f.matrice[1][5];
            tmp=new Joker1(f.matrice[2][4], 1,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[1][5];
            pont2=f.matrice[2][7];
            pont3=f.matrice[4][8];
            tmp=new Joker1(f.matrice[3][6], 2,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[2][6];
            pont2=f.matrice[3][8];
            pont3=f.matrice[5][9];
            tmp=new Joker1(f.matrice[4][7], 2,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[5][9];
            pont2=f.matrice[7][10];
            pont3=f.matrice[8][9];
            tmp=new Joker1(f.matrice[6][8], 3,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[6][9];
            pont2=f.matrice[8][10];
            pont3=f.matrice[9][9];
            tmp=new Joker1(f.matrice[7][8], 3,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[9][9];
            pont2=f.matrice[10][8];
            pont3=f.matrice[9][6];
            tmp=new Joker1(f.matrice[8][7], 4,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[9][5];
            pont2=f.matrice[10][7];
            pont3=f.matrice[9][8];
            tmp=new Joker1(f.matrice[8][6], 4,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[9][5];
            pont2=f.matrice[8][3];
            pont3=f.matrice[6][2];
            tmp=new Joker1(f.matrice[7][4], 5,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[8][4];
            pont2=f.matrice[7][2];
            pont3=f.matrice[5][1];
            tmp=new Joker1(f.matrice[6][3], 5,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[5][1];
            pont2=f.matrice[3][0];
            pont3=f.matrice[2][1];
            tmp=new Joker1(f.matrice[4][2], 6,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[4][1];
            pont2=f.matrice[2][0];
            pont3=f.matrice[1][1];
            tmp=new Joker1(f.matrice[3][2], 6,pont1,pont2,pont3);
            Joker1.add(tmp);
        }
      else{
            pont1=f.matrice[1][1];
            pont2=f.matrice[0][2];
            pont3=f.matrice[1][4];
            tmp=new Joker1(f.matrice[2][3], 1,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[1][2];
            pont2=f.matrice[0][3];
            pont3=f.matrice[1][5];
            tmp=new Joker1(f.matrice[2][4], 1,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[1][3];
            pont2=f.matrice[0][4];
            pont3=f.matrice[1][6];
            tmp=new Joker1(f.matrice[2][5], 1,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[1][4];
            pont2=f.matrice[0][5];
            pont3=f.matrice[1][7];
            tmp=new Joker1(f.matrice[2][6], 1,pont1,pont2,pont3);
            Joker1.add(tmp);


            pont1=f.matrice[1][7];
            pont2=f.matrice[2][9];
            pont3=f.matrice[4][10];
            tmp=new Joker1(f.matrice[3][8], 2,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[2][8];
            pont2=f.matrice[3][10];
            pont3=f.matrice[5][11];
            tmp=new Joker1(f.matrice[4][9], 2,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[3][9];
            pont2=f.matrice[4][10];
            pont3=f.matrice[6][12];
            tmp=new Joker1(f.matrice[5][10], 2,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[4][10];
            pont2=f.matrice[5][11];
            pont3=f.matrice[7][13];
            tmp=new Joker1(f.matrice[6][11], 2,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[7][13];
            pont2=f.matrice[9][14];
            pont3=f.matrice[10][13];
            tmp=new Joker1(f.matrice[8][12], 3,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[8][13];
            pont2=f.matrice[10][14];
            pont3=f.matrice[11][13];
            tmp=new Joker1(f.matrice[9][12], 3,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[9][13];
            pont2=f.matrice[10][14];
            pont3=f.matrice[11][13];
            tmp=new Joker1(f.matrice[10][12], 3,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[10][13];
            pont2=f.matrice[11][14];
            pont3=f.matrice[12][13];
            tmp=new Joker1(f.matrice[11][12], 3,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[13][7];
            pont2=f.matrice[14][9];
            pont3=f.matrice[13][10];
            tmp=new Joker1(f.matrice[12][8], 4,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[13][8];
            pont2=f.matrice[14][10];
            pont3=f.matrice[13][11];
            tmp=new Joker1(f.matrice[12][9], 4,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[13][9];
            pont2=f.matrice[14][11];
            pont3=f.matrice[13][12];
            tmp=new Joker1(f.matrice[12][10], 4,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[13][10];
            pont2=f.matrice[14][12];
            pont3=f.matrice[13][13];
            tmp=new Joker1(f.matrice[12][11], 4,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[13][7];
            pont2=f.matrice[12][5];
            pont3=f.matrice[10][4];
            tmp=new Joker1(f.matrice[11][6], 5,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[12][6];
            pont2=f.matrice[11][4];
            pont3=f.matrice[9][3];
            tmp=new Joker1(f.matrice[10][5], 5,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[11][5];
            pont2=f.matrice[10][3];
            pont3=f.matrice[8][2];
            tmp=new Joker1(f.matrice[9][4], 5,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[10][4];
            pont2=f.matrice[9][2];
            pont3=f.matrice[7][1];
            tmp=new Joker1(f.matrice[8][3], 5,pont1,pont2,pont3);
            Joker1.add(tmp);

            pont1=f.matrice[1][1];
            pont2=f.matrice[2][0];
            pont3=f.matrice[4][1];
            tmp=new Joker1(f.matrice[3][2], 6,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[2][1];
            pont2=f.matrice[3][0];
            pont3=f.matrice[5][1];
            tmp=new Joker1(f.matrice[4][2], 6,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[3][1];
            pont2=f.matrice[4][0];
            pont3=f.matrice[6][1];
            tmp=new Joker1(f.matrice[5][2], 6,pont1,pont2,pont3);
            Joker1.add(tmp);
            pont1=f.matrice[4][1];
            pont2=f.matrice[5][0];
            pont3=f.matrice[7][1];
            tmp=new Joker1(f.matrice[6][2], 6,pont1,pont2,pont3);
            Joker1.add(tmp);

      }

    }
    private int est_joker2(caseM x){
        // retourne le nombre de ponts libre
        Joker2 res=null;
        ListIterator<Joker2> IT=Joker2.listIterator();

        while(IT.hasNext()){
            Joker2 tmp=IT.next();
            if(tmp.joker==x)
                res=tmp;
        }
        int i=0;

        if(res!=null){
            if(!est_pont_cassé(res.joker, res.pont1) && (est_voisin_d_un_bord(res.pont1, 1) || est_voisin_d_un_bord(res.pont1, 2) || est_voisin_d_un_bord(res.pont1, 3) || est_voisin_d_un_bord(res.pont1, 4) ||
                    est_voisin_d_un_bord(res.pont1, 5) || est_voisin_d_un_bord(res.pont1, 6))){
                    i++;
            }
            if(!est_pont_cassé(res.joker, res.pont4) && (est_voisin_d_un_bord(res.pont4, 1) || est_voisin_d_un_bord(res.pont4, 2) || est_voisin_d_un_bord(res.pont1, 3) || est_voisin_d_un_bord(res.pont4, 4) ||
                    est_voisin_d_un_bord(res.pont4, 5) || est_voisin_d_un_bord(res.pont4, 6))){
                    i++;
            }
            if(estBord(res.pont2) && !est_pont_cassé(res.joker, res.pont2))

                i++;

            if(estBord(res.pont3) && !est_pont_cassé(res.joker, res.pont3))

                i++;
        }
        return i;
    }
    private int est_joker1(caseM x){
        // retourne le nombre de ponts libre
        ListIterator<Joker1> IT=Joker1.listIterator();
       Joker1 res=null;
        while(IT.hasNext()){
            Joker1 tmp=IT.next();
            if(tmp.joker==x)
                res=tmp;
        }
       int i=0;

       if(res!=null){
           if(!est_pont_cassé(res.joker, res.pont1) && est_voisin_d_un_bord(res.pont1,numero_bord_joker1(res))){
                    i++;

            }
           if(!est_pont_cassé(res.joker, res.pont3) && est_voisin_d_un_bord(res.pont3, numero_bord_joker1(res))){
                    i++;

            }
           if(estBord(res.pont2) && !est_pont_cassé(res.joker, res.pont2)){

                i++;


            }

        }

        return i;
    }
    private boolean est_joker1_selon_bord(caseM x,int bord){
         // retourne le nombre de ponts libre
        ListIterator<Joker1> IT=Joker1.listIterator();
       Joker1 res=null;
        while(IT.hasNext()){
            Joker1 tmp=IT.next();
            if(tmp.joker==x)
                res=tmp;
        }
       int i=0;
       if(res!=null){
            if(!est_pont_cassé(res.joker, res.pont1) && est_voisin_d_un_bord(res.pont1, bord)){
                    i++;

            }
           if(!est_pont_cassé(res.joker, res.pont3) && est_voisin_d_un_bord(res.pont3, bord)){
                    i++;

            }
           if(est_bord_selon_numcote(res.pont2, bord) && !est_pont_cassé(res.joker, res.pont2)){
                i++;

            }
        }
        return i>=2;
    }
    private boolean est_joker2_selon_bord(caseM x,int bord){
         // retourne le nombre de ponts libre
        Joker2 res=null;
        ListIterator<Joker2> IT=Joker2.listIterator();

        while(IT.hasNext()){
            Joker2 tmp=IT.next();
            if(tmp.joker==x)
                res=tmp;
        }

        int i=0;
        if(res!=null){
            if(!est_pont_cassé(res.joker, res.pont1) && est_voisin_d_un_bord(res.pont1, bord)){
                    i++;

            }
            if(!est_pont_cassé(res.joker, res.pont4) && est_voisin_d_un_bord(res.pont4, bord)){
                    i++;

            }
            if(est_bord_selon_numcote(res.pont2,bord) && !est_pont_cassé(res.joker, res.pont2)){
                i++;

            }
            if(est_bord_selon_numcote(res.pont3,bord) && !est_pont_cassé(res.joker, res.pont3)){
                i++;

            }
        }
        return i>=2;
    }
   synchronized private int numero_bord_joker1(Joker1 x){

        return x.bord;
    }
   synchronized private int[] numero_bord_joker2(Joker2 x){
        int [] tmp=new int[2];
        tmp[0]=x.bord1;
        tmp[1]=x.bord2;
        return tmp;
    }
   synchronized private boolean est_joker1_casse(caseM x){

       ListIterator<Joker1> it=Joker1.listIterator();
        boolean b=false; boolean continuer = true;
        while(it.hasNext()){
            Joker1 tmp=it.next();
            if(tmp.joker==x){

               if(tmp.joker.etat==1){
                    if(touche_le_bord(1, tmp.bord))
                        continuer=false;
                    if(Bord_deja_repare_noirs[tmp.bord-1]!=null)
                        continuer=false;
               }
                else if(tmp.joker.etat==2){
                     if(touche_le_bord(2, tmp.bord))
                        continuer=false;
                    if(Bord_deja_repare_blancs[tmp.bord-1]!=null)
                         continuer=false;
               }

                ArrayList<caseM> pts=pont_caseM_couleur(tmp.joker);
                ListIterator<caseM> pit=pts.listIterator();

                while(pit.hasNext()){
                    caseM templol=pit.next();
                    if((templol.i==tmp.pont1.i && templol.j==tmp.pont1.j && templol.etat==tmp.pont1.etat) || (templol.i==tmp.pont2.i && templol.j==tmp.pont2.j && templol.etat==tmp.pont2.etat) ||
                            (templol.i==tmp.pont3.i && templol.j==tmp.pont3.j && templol.etat==tmp.pont3.etat)){
                        continuer=false;
                    }


                }
                if(continuer){
                  /*  int i=0;
                    if(est_pont_cassé(tmp.joker, tmp.pont1) || est_pont_obstrué(tmp.joker,tmp.pont1)){
                        i++;
                        System.out.println("Test joker1 cassé : pont 1  cassé.");
                    }
                    if(est_pont_cassé(tmp.joker, tmp.pont2) || est_pont_obstrué(tmp.joker,tmp.pont2)){
                        i++;
                        System.out.println("Test joker1 cassé : pont 2  cassé.");
                    }
                    if(est_pont_cassé(tmp.joker, tmp.pont3)  || est_pont_obstrué(tmp.joker,tmp.pont3)){
                        i++;
                        System.out.println("Test joker1 cassé : pont 3  cassé.");
                    }
                    if(i==2)
                        b=true;

                   */
                    if(est_pont_obstrué(tmp.joker,tmp.pont1)){
                        if(est_pont_cassé(tmp.joker,tmp.pont2) && !est_pont_cassé(tmp.joker,tmp.pont3) && !est_pont_obstrué(tmp.joker,tmp.pont3)){
                            b=true;
                            if(tmp.joker.etat==1)
                                Bord_deja_repare_noirs[tmp.bord-1]=tmp.joker;
                            else if(tmp.joker.etat==2)
                                Bord_deja_repare_blancs[tmp.bord-1]=tmp.joker;
                        }
                    }else if(est_pont_obstrué(tmp.joker,tmp.pont2)){
                        b=false;
                    }else if(est_pont_obstrué(tmp.joker,tmp.pont3)){
                        if(est_pont_cassé(tmp.joker,tmp.pont2) && !est_pont_cassé(tmp.joker,tmp.pont1) && !est_pont_obstrué(tmp.joker,tmp.pont1)){
                            b=true;
                            if(tmp.joker.etat==1)
                                Bord_deja_repare_noirs[tmp.bord-1]=tmp.joker;
                            else if(tmp.joker.etat==2)
                                Bord_deja_repare_blancs[tmp.bord-1]=tmp.joker;
                        }
                    }else if(!est_pont_obstrué(tmp.joker,tmp.pont1) && !est_pont_obstrué(tmp.joker,tmp.pont2) && !est_pont_obstrué(tmp.joker,tmp.pont3)){
                        int i=0;
                        if(est_pont_cassé(tmp.joker, tmp.pont1) && est_pont_cassé(tmp.joker, tmp.pont3))
                            b=true;
                        else{
                            if(est_pont_cassé(tmp.joker, tmp.pont1))
                                    i++;
                            if(est_pont_cassé(tmp.joker, tmp.pont2))
                                i++;
                            if(est_pont_cassé(tmp.joker, tmp.pont3))
                                i++;
                            if(i==2){
                                b=true;
                                if(tmp.joker.etat==1)
                                    Bord_deja_repare_noirs[tmp.bord-1]=tmp.joker;
                                else if(tmp.joker.etat==2)
                                    Bord_deja_repare_blancs[tmp.bord-1]=tmp.joker;
                            }
                            else
                                b=false;
                        }
                    }
                    boolean b1 = false;
                    boolean b2 = false;
                    System.out.println("tmp.joker = "+tmp.joker.i+","+tmp.joker.j);
                    caseM[] tab = cases_milieu_pont(tmp.joker, tmp.pont1);
                    if((tab[0].etat == couleur_joueur_courant()
                            && est_voisin_d_un_bord(tab[0], tmp.bord))
                            || (tab[1].etat == couleur_joueur_courant()
                            && est_voisin_d_un_bord(tab[1], tmp.bord))){
                        b1 = true;
                    }
                    tab = cases_milieu_pont(tmp.joker, tmp.pont3);
                    if((tab[0].etat == couleur_joueur_courant()
                            && est_voisin_d_un_bord(tab[0], tmp.bord))
                            || (tab[1].etat == couleur_joueur_courant()
                            && est_voisin_d_un_bord(tab[1], tmp.bord))){
                        b2 = true;
                    }
                    if(b1 || b2){
                        return false;
                    }

                }
            }
        }
        return b;
    }
   synchronized private boolean est_joker2_casse(caseM x,int bord){
        ListIterator<Joker2> it=Joker2.listIterator();
        boolean b=false;boolean continuer = true;
        caseM pont1=null;caseM pont2=null;
        while(it.hasNext()){
            Joker2 tmp=it.next();

            if(tmp.joker==x){
                System.out.println("initialisation des ponts");
                System.out.println("tmp.joker = ("+tmp.joker.i+","+tmp.joker.j+")");
                System.out.println("bord ="+bord);
                System.out.println("tmp.pont1 = ("+tmp.pont1.i+","+tmp.pont1.j+")");
                System.out.println("tmp.pont2 = ("+tmp.pont2.i+","+tmp.pont2.j+")");
                System.out.println("tmp.pont3 = ("+tmp.pont3.i+","+tmp.pont3.j+")");
                System.out.println("tmp.pont4 = ("+tmp.pont4.i+","+tmp.pont4.j+")");

                if(est_voisin_d_un_bord(tmp.pont1, bord)){
//                    System.out.println("pont11 initialisé");
                    pont1=tmp.pont1;
                }
                if(est_bord_selon_numcote(tmp.pont2, bord)){
//                    System.out.println("pont21 initialisé");
                    pont2=tmp.pont2;
                }
                if(est_voisin_d_un_bord(tmp.pont4, bord)){
//                    System.out.println("pont12 initialisé");
                    pont1=tmp.pont4;
                }
                if(est_bord_selon_numcote(tmp.pont3, bord)){
//                    System.out.println("pont22 initialisé");
                    pont2=tmp.pont3;
                }
                System.out.append("initialisation des ponts terminé");
                ArrayList<caseM> pts=pont_caseM_couleur(tmp.joker);
                ListIterator<caseM> pit=pts.listIterator();
                if(tmp.joker.etat==1){
                    if(touche_le_bord(1, bord))
                        continuer=false;
                    if(Bord_deja_repare_noirs[bord-1]!=null)
                        continuer=false;
                }
                else if(tmp.joker.etat==2){
                    if(touche_le_bord(2, bord))
                        continuer=false;
                    if(Bord_deja_repare_blancs[bord-1]!=null)
                         continuer=false;
                }
                while(pit.hasNext()){
                    caseM templol=pit.next();
                    if(pont1!=null && pont2!=null){
                        if((templol.i==pont1.i && templol.j==pont1.j && templol.etat==pont1.etat) || (templol.i==pont2.i && templol.j==pont2.j && templol.etat==pont2.etat)){
                            continuer=false;
                        }
                    }

               /* int i=0;
                if(est_voisin_d_un_bord(tmp.pont1, bord) && est_pont_cassé(tmp.joker, tmp.pont1)  && !est_pont_obstrué(tmp.joker,tmp.pont1))

                    i++;
                if(est_voisin_d_un_bord(tmp.pont4, bord) && est_pont_cassé(tmp.joker, tmp.pont4)  && !est_pont_obstrué(tmp.joker,tmp.pont4))
                    i++;
                if(est_bord_selon_numcote(tmp.pont2, bord) && est_pont_cassé(tmp.joker, tmp.pont2)  && !est_pont_obstrué(tmp.joker,tmp.pont2))
                    i++;
                if(est_bord_selon_numcote(tmp.pont3, bord) && est_pont_cassé(tmp.joker, tmp.pont3)  && !est_pont_obstrué(tmp.joker,tmp.pont3))
                    i++;
                if(i==1)
                    b=true;*/
                   if(continuer){
                       if(est_pont_obstrué(tmp.joker,pont2))
                           b=false;
                       else if(est_pont_obstrué(tmp.joker, pont2) && est_pont_obstrué(tmp.joker, pont1))
                           b=false;
                       else if(est_pont_obstrué(tmp.joker, pont1) && est_pont_cassé(tmp.joker, pont2)){
                          caseM [] tab=cases_milieu_pont(tmp.joker, pont2);
                          if(tab[0].etat==0)
                              if(est_voisin_d_un_bord(tab[0], bord)){
                                  b=true;
                                  if(tmp.joker.etat==1)
                                    Bord_deja_repare_noirs[bord-1]=tmp.joker;
                                  else if(tmp.joker.etat==2)
                                    Bord_deja_repare_blancs[bord-1]=tmp.joker;
                              }
                          if(tab[1].etat==0)
                              if(est_voisin_d_un_bord(tab[1], bord)){
                                  b=true;
                                  if(tmp.joker.etat==1)
                                    Bord_deja_repare_noirs[bord-1]=tmp.joker;
                                  else if(tmp.joker.etat==2)
                                    Bord_deja_repare_blancs[bord-1]=tmp.joker;
                              }

                       }
                       else if(est_pont_cassé(tmp.joker, pont1) && est_pont_cassé(tmp.joker, pont2)){
                            caseM [] tab=cases_milieu_pont(tmp.joker, pont2);
                          if(tab[0].etat==0)
                              if(est_voisin_d_un_bord(tab[0], bord)){
                                  b=true;
                                  if(tmp.joker.etat==1)
                                    Bord_deja_repare_noirs[bord-1]=tmp.joker;
                                  else if(tmp.joker.etat==2)
                                    Bord_deja_repare_blancs[bord-1]=tmp.joker;
                              }
                          if(tab[1].etat==0)
                              if(est_voisin_d_un_bord(tab[1], bord)){
                                  b=true;
                                  if(tmp.joker.etat==1)
                                    Bord_deja_repare_noirs[bord-1]=tmp.joker;
                                  else if(tmp.joker.etat==2)
                                    Bord_deja_repare_blancs[bord-1]=tmp.joker;
                              }
                       }
                        else if(est_pont_cassé(tmp.joker, pont1) && !est_pont_cassé(tmp.joker, pont2)
                        && !est_pont_obstrué(tmp.joker, pont2)){
                              b = true;
                        }
                         if(pont2 != null && pont2.etat == couleur_joueur_adverse() && !est_pont_cassé(tmp.joker,pont1) && !est_pont_obstrué(tmp.joker,pont1) && !est_pont_obstrué(tmp.joker,pont2)  ){

                             b= true;
                         }

                         if(pont2 != null && pont2.etat == 0){
                             caseM[] tab = cases_milieu_pont(tmp.joker, pont2);
                             if((tab[1].etat==couleur_joueur_adverse() && tab[0].etat==0) 
                                     || (tab[0].etat==couleur_joueur_adverse() && tab[1].etat==0)){
                                 return true;
                             }
                         }
                         ArrayList<caseM> sucs_couleur = successeurs_couleur(tmp.joker);
                         if(!sucs_couleur.isEmpty()){
                             ListIterator<caseM> it2 = sucs_couleur.listIterator();
                             while(it2.hasNext()){
                                 caseM ce = it2.next();
                                 if(est_voisin_d_un_bord(ce, bord)){
                                     return false;
                                 }
                             }
                         }

                    }
                }
                }
        }
        return b;
    }
    @Override
    synchronized public boolean estBord(caseM x) {

        if(x.numcote != 0){
            return true;
        }
        return false;
    }
    private boolean est_pont(caseM depart,caseM arrive){
        boolean b=false;
        caseM [] tab=cases_milieu_pont(depart, arrive);
        int i=0;

            if(tab[0]!=null && tab[0].etat==0){
                    i++;
                }
            if(tab[1]!=null && tab[1].etat==0){
                    i++;
                }
            if(arrive.etat==depart.etat)
                i++;
            if(i==3){
                b=true;
            }




        return b;
    }
    private boolean est_pont_obstrué(caseM x,caseM y){
        // si les 2 case smilieu  ont une couleur opposé
        boolean b=false;
        caseM [] tab=cases_milieu_pont(x, y);
        int i=0;
        if(x.etat==1){
            if(tab[0]!=null && tab[0].etat==2)
                i++;
            if(tab[1]!=null && tab[1].etat==2)
                i++;
        }else if(x.etat==2){
            if (tab[0] != null && tab[0].etat == 1) {
                i++;
            }
            if (tab[1] != null && tab[1].etat == 1) {
                i++;
            }


        }
        if(i==2)
            b=true;
        return b;
    }
    synchronized private boolean est_pont_cassé(caseM depart,caseM arrive){
        boolean b=false;
        caseM [] tab=cases_milieu_pont(depart, arrive);



            int i=0;
            if(depart.etat==1){
                if(tab[0]!=null && tab[0].etat==2){
                    i++;
                }
                if(tab[1]!=null && tab[1].etat==2){
                    i++;
                }
                if(i==1)
                    b=true;
                if(arrive!=null && arrive.etat==2)
                    b=true;

            }
             else if(depart.etat==2){
                if(tab[0]!=null && tab[0].etat==1){
                    i++;
                }
                if(tab[1]!=null && tab[1].etat==1){
                    i++;
                }
                if(i==1)
                    b=true;
                if(arrive!=null && arrive.etat==1)
                    b=true;

            }

        return b;
    }

    private ArrayList<caseM> pont_caseM_couleur(caseM x){
        int couleur = x.etat;
        ArrayList<caseM> res = new ArrayList<caseM>();
        ArrayList<caseM> list = pont_caseM(x);
        ListIterator<caseM> it = list.listIterator();
        while(it.hasNext()){
            caseM y = it.next();
            if(y.etat == couleur){
                res.add(y);
            }
        }
        return res;
    }

    private ArrayList<caseM> pont_caseM(caseM x){
        /* Retourne la liste des cases (quelque soit la couleur) qui construisent le pont avec x
         * Par def, s'il y a déja une case qui casse le pont, une case n'est pas
         * dans ma miste de retour.
         */
        ArrayList<caseM> L = new ArrayList<caseM>();
        if(x.i>=2 && x.j>=1 && f.matrice[x.i-2][x.j-1].etat!=-1
                && (f.matrice[x.i-1][x.j-1].etat==0 && f.matrice[x.i-1][x.j].etat==0)){
            // (i-2, j-1) h
            L.add(f.matrice[x.i-2][x.j-1]);
        }
        if(x.i>=1 && x.j>=2 && f.matrice[x.i-1][x.j-2].etat!=-1
                && (f.matrice[x.i-1][x.j-1].etat==0 && f.matrice[x.i][x.j-1].etat==0)){
            // (i-1, j-2) hg
            L.add(f.matrice[x.i-1][x.j-2]);
        }
        if(x.i<=f.N-2 && x.j>=1 && f.matrice[x.i+1][x.j-1].etat!=-1
                && (f.matrice[x.i][x.j-1].etat==0 && f.matrice[x.i+1][x.j].etat==0)){
            // (i+1, j-1) bg
            L.add(f.matrice[x.i+1][x.j-1]);
        }
        if(x.i<=f.N-3 && x.j<=f.N-2 && f.matrice[x.i+2][x.j+1].etat!=-1
                && (f.matrice[x.i+1][x.j].etat==0 && f.matrice[x.i+1][x.j+1].etat==0)){
            // (i+2, j+1) b
            L.add(f.matrice[x.i+2][x.j+1]);
        }
        if(x.i<=f.N-2 && x.j<=f.N-3 && f.matrice[x.i+1][x.j+2].etat!=-1
                && (f.matrice[x.i+1][x.j+1].etat==0 && f.matrice[x.i][x.j+1].etat==0)){
            // (i+1, j+2) bd
            L.add(f.matrice[x.i+1][x.j+2]);
        }
        if(x.i>=1 && x.j<=f.N-2 && f.matrice[x.i-1][x.j+1].etat!=-1
                && (f.matrice[x.i][x.j+1].etat==0 && f.matrice[x.i-1][x.j].etat==0)){
            // (i-1, j+1) hd
            L.add(f.matrice[x.i-1][x.j+1]);
        }
        return L;
    }

    private ArrayList<caseM> pont_caseM_autre(caseM x){
        /* Retourne la liste des cases (quelque soit la couleur) qui construisent le pont avec x
         * Par def, s'il y a dÃ©ja une case qui casse le pont, une case n'est pas
         * dans ma miste de retour.
         */
        ArrayList<caseM> L = new ArrayList<caseM>();
        if(x.i>=2 && x.j>=1 && f.matrice[x.i-2][x.j-1].etat!=-1){
            // (i-2, j-1) h
            L.add(f.matrice[x.i-2][x.j-1]);
        }
        if(x.i>=1 && x.j>=2 && f.matrice[x.i-1][x.j-2].etat!=-1){
            // (i-1, j-2) hg
            L.add(f.matrice[x.i-1][x.j-2]);
        }
        if(x.i<=f.N-2 && x.j>=1 && f.matrice[x.i+1][x.j-1].etat!=-1){
            // (i+1, j-1) bg
            L.add(f.matrice[x.i+1][x.j-1]);
        }
        if(x.i<=f.N-3 && x.j<=f.N-2 && f.matrice[x.i+2][x.j+1].etat!=-1){
            // (i+2, j+1) b
            L.add(f.matrice[x.i+2][x.j+1]);
        }
        if(x.i<=f.N-2 && x.j<=f.N-3 && f.matrice[x.i+1][x.j+2].etat!=-1){
            // (i+1, j+2) bd
            L.add(f.matrice[x.i+1][x.j+2]);
        }
        if(x.i>=1 && x.j<=f.N-2 && f.matrice[x.i-1][x.j+1].etat!=-1){
            // (i-1, j+1) hd
            L.add(f.matrice[x.i-1][x.j+1]);
        }
        return L;
    }

    private ArrayList<caseM> pont_caseM_couleur_autre(caseM x){
        int couleur = x.etat;
        ArrayList<caseM> res = new ArrayList<caseM>();
        ArrayList<caseM> list = pont_caseM_autre(x);
        ListIterator<caseM> it = list.listIterator();
        while(it.hasNext()){
            caseM y = it.next();
            if(y.etat == couleur){
                res.add(y);
            }
        }
        return res;
    }

    private ArrayList<caseM> pont_caseM_libre(caseM x){
        /* Retourne la liste de tous les ponts libre du plateau
         */
        ArrayList<caseM> res = new ArrayList<caseM>();
        ArrayList<caseM> list = pont_caseM(x);
        ListIterator<caseM> it = list.listIterator();
        while(it.hasNext()){
            caseM y = it.next();
            if(y.etat == 0){
                res.add(y);
            }
        }
        return res;
    }

    private int nb_pont_caseM_meme_etat(caseM x){
        ArrayList<caseM> list_ponts = pont_caseM(x);
        ListIterator E = list_ponts.listIterator();
        int res = 0;
        int etat = x.etat;
        while(E.hasNext()){
            caseM p = (caseM) E.next();
            if(p.etat == etat){
                res++;
            }
        }
        return res;
    }
    //MARCHE successeurs
    private caseM analyser_debut2(){
        caseM res=null;
        if(variable_une_boule_sur_plateau){
            variable_une_boule_sur_plateau = false;
            if(f.N==11 && f.matrice[5][5].etat==0)
                res=f.matrice[5][5];
            else if(f.N==11 && f.matrice[5][5].etat==1)
                res=f.matrice[5][4];
            if(f.N==15 && f.matrice[7][7].etat==0){
                res=f.matrice[7][7];
            }else if(f.N==15 &f.matrice[7][7].etat==1)
                res=f.matrice[7][6];

        }else{
            
            Random r=new Random(1000);
            int i=r.nextInt(6);
            Joker2 tmp=Joker2.get(i);
            if(tmp.joker.etat==1){
                i--;
                tmp=Joker2.get(i);
                res=tmp.joker;
            }
            else
                res=tmp.joker;

            
        }
        ArrayList<caseM> temp=list_boules(f.matrice, 1);
        if(f.N==11){
            if(temp.size()>=4)
               joueur_press_button=true;
        }else{
            if(temp.size()>=6)
                joueur_press_button=true;
        }


        return res;
    }
    private caseM analyser_debut(){
        /* La fonction replit la variable globale joueur_press_button,
         * le met à true si le joueur(ia) doit appuiller sur le bouton et
         * dans ce cas retourne null. Sinon joueur_press_button est à faut et
         * la valeur de retour est une case à jouer.
         */

        int[][] floyd_nouveau = remplir_matrice_floyd();
        int [][] floyd_final = floyd_warshall(floyd_nouveau);
        ArrayList<caseM> composante_joueur_courant = composante_connexe_principale(1,floyd_final);

        if(variable_une_boule_sur_plateau){
            variable_une_boule_sur_plateau = false;
        }else{
            /**********************************************************/
            /********Regarder pour chauqe boules a part****************/
            ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
            int nb_coups_une_boule;
            int nb_coups_blanc_potentiel = 1024;
            //caseM case_mem2 = null;
            for(int i=0; i<f.N; i++){
                for(int j=0; j<f.N; j++){
                    if(f.matrice[i][j].etat == 0){
                        //if(!case_est_marquee(f.matrice[i][j])){
                        //if(!est_pont_libre(f.matrice[i][j], composante_joueur_courant) && !est_successeur_libre(f.matrice[i][j], composante_joueur_courant)){
                           // marquer_case(f.matrice[i][j]);

                            f.matrice[i][j].etat = 2;
                           // floyd_nouveau = remplir_matrice_floyd();
                            int[][] floyd_final3 = floyd_warshall_epure(floyd_nouveau,f.matrice[i][j].numero_floyd);
                            liste_une_boule.clear();
                            liste_une_boule.add(f.matrice[i][j]);
                            nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(2, liste_une_boule, floyd_final3);
                            f.matrice[i][j].etat = 0;

                            if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                //case_mem2 = f.matrice[i][j];
                            }
                            /*if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                Random r = new Random();
                                int choix = r.nextInt(2);
                                if(choix == 1){
                                    nb_coups_blanc_potentiel = nb_coups_une_boule;
                                }
                            }*/
                      //  }
                        // si nb coups restants de cette comp connexe (un point) est plus petit que celui du chemin principal:
                    }
                }
            }
            System.out.println("coup restant noirs:"+(nbcoup_restantJ1-1)+" coup potentiels blanc restant:"+nb_coups_blanc_potentiel);
            if(nbcoup_restantJ1-1 <= nb_coups_blanc_potentiel){
                joueur_press_button = true;
                return null;
            }

        }
         caseM case_mem=null;
        if(composante_joueur_courant!=null){
            int nb_coups_restants_noir = 1024;
            ListIterator<caseM> composante = composante_joueur_courant.listIterator();
            boolean[][]marquage = new boolean[f.N][f.N];
            for(int i=0; i<f.N; i++){
                for(int j=0; j<f.N; j++){
                    marquage[i][j] = false;
                }
            }

            int diff_mem = 1024; //reinit
            int nb_coups_avec_ce_pont;
            case_mem = null;
            /*********Regarder pour chaque pont est successeur avec mem nb coups restants*****/
            ArrayList<caseM> tmp= (ArrayList<caseM>) composante_joueur_courant.clone();
            ListIterator<caseM> tmpit=tmp.listIterator();
            while(composante.hasNext()){
                //pour chaque case du chemin principal:
                caseM case_composante1 = composante.next();
                ArrayList<caseM> ponts_composante1 = pont_caseM_libre(case_composante1);

                ListIterator<caseM> ponts_composante = ponts_composante1.listIterator();
                while(ponts_composante.hasNext()){
                    //pour chaque pont de cette case:
                    caseM un_pont_composante = ponts_composante.next();
                    if(marquage[un_pont_composante.i][un_pont_composante.j]==false){
                        marquage[un_pont_composante.i][un_pont_composante.j]=true;

                        f.matrice[un_pont_composante.i][un_pont_composante.j].etat = 1;
                        //int[][] floyd_nouveau1 = remplir_matrice_floyd();
                        int [][] floyd_final1 = floyd_warshall_epure(floyd_nouveau,f.matrice[un_pont_composante.i][un_pont_composante.j].numero_floyd);
                        tmpit.add(un_pont_composante);
                        nb_coups_avec_ce_pont = nb_coup_restant_pour_composante_connexe(1,tmp, floyd_final1);
                        tmpit.previous();
                        tmpit.remove();
                        f.matrice[un_pont_composante.i][un_pont_composante.j].etat = 0;

                        if(nb_coups_avec_ce_pont < nb_coups_restants_noir){
                            nb_coups_restants_noir = nb_coups_avec_ce_pont;
                            case_mem = f.matrice[un_pont_composante.i][un_pont_composante.j];
                        }
                        if(nb_coups_avec_ce_pont == nb_coups_restants_noir){
                            Random r = new Random();
                            int choix = r.nextInt(2);
                            if(choix == 1){
                                nb_coups_restants_noir = nb_coups_avec_ce_pont;
                                case_mem = f.matrice[un_pont_composante.i][un_pont_composante.j];
                            }
                        }
                    }
                }
            }

            }else{
                if(f.N==11)
                  case_mem=f.matrice[5][5];
                else
                    case_mem=f.matrice[7][7];
                          

            }

        /**********************************************************/


        joueur_press_button = false;
        return case_mem;
    }

    private caseM[][] copie_matrice(){
        caseM[][] res = new caseM[f.N][f.N];
        for(int i=0; i<f.N; i++){
            for(int j=0; j<f.N; j++){
                    res[i][j] = f.matrice[i][j];
            }
        }
        return res;
    }

    private boolean[][] copie_matriceDeMarquage(){
        boolean[][] res = new boolean[f.N][f.N];
        for(int i=0; i<f.N; i++){
            for(int j=0; j<f.N; j++){
                    res[i][j] = MatriceDeMarquage[i][j];
            }
        }
        return res;
    }

    private void affecter_matriceDeMarquage(boolean[][]mat){
        for(int i=0; i<f.N; i++){
            for(int j=0; j<f.N; j++){
                    MatriceDeMarquage[i][j] = mat[i][j];
            }
        }
    }

    private ArrayList copie_list(ArrayList L){
        ArrayList<caseM> res = new ArrayList<caseM>();
        ListIterator<caseM> E = L.listIterator();
        while(E.hasNext()){
            res.add(E.next());
        }
        return res;
    }

    private ArrayList<caseM> tous_pont_de_matrice_couleur(caseM [][]matrice, int couleur){
        /* Retourne la liste des caseM-ponts qui sont libres.
         */
            boolean[][]marquage = new boolean[f.N][f.N];
            for(int i=0; i<f.N; i++){
                for(int j=0; j<f.N; j++){
                    marquage[i][j] = false;
                }
            }
            ArrayList<caseM> res = new ArrayList<caseM>();
            // res - une liste de résultat
            ArrayList<caseM> L = list_boules(matrice, couleur);
            // L - liste de toutes les boules de couleur couleur
            ListIterator<caseM> E = L.listIterator();
            while(E.hasNext()){
                caseM boule = E.next();
                ArrayList<caseM> L2 = pont_caseM_libre(boule);
                // L2 = liste de toutes les ponts de boule_couleur
                ListIterator<caseM> it = L2.listIterator();
                while(it.hasNext()){
                    caseM y = it.next();
                    if(marquage[y.i][y.j]==false){
                        // si on la case n'a pas été déjà parcouru
                        marquage[y.i][y.j]=true;
                        res.add(y);
                    }
                }
            }
            return res;
    }
    private ArrayList<caseM> tout_successeur_de_matrice_couleur(caseM [][]matrice, int couleur){
        boolean[][]marquage = new boolean[f.N][f.N];
        for(int i=0; i<f.N; i++){
            for(int j=0; j<f.N; j++){
                marquage[i][j] = false;
            }
        }

        ArrayList<caseM> res = new ArrayList<caseM>();
        // res - une liste de résultat
        ArrayList<caseM> L = list_boules(matrice, couleur);
        // L - liste de toutes les boules de couleur couleur
        ListIterator<caseM> E = L.listIterator();
        while(E.hasNext()){
            caseM tmp = E.next();
            ArrayList<caseM> liste_suc_libre = successeurs_libres(tmp);
            //pour tout successeur de la case noir ou blanche
            if(!liste_suc_libre.isEmpty()){
                ListIterator<caseM> liste_suc_libre_it = liste_suc_libre.listIterator();
                while(liste_suc_libre_it.hasNext()){
                    caseM coup = liste_suc_libre_it.next();
                    if(estPossibleClic(coup)){
                        //si possible cliquer et pas marquée
                        if(marquage[coup.i][coup.j]==false){
                            marquage[coup.i][coup.j]=true;
                            res.add(coup);
                        }
                    }
                }
            }
        }
        return res;
    }
    private int couleur_joueur_courant(){
        if(!f.partie_commencee){
            return 1;
        }
        if(f.joueur_courant==1){
            return f.joueurUn.couleur;
        }
        return f.joueurDeux.couleur;
    }
    private int couleur_joueur_adverse(){
        if(!f.partie_commencee){
            return 1;
        }
        if(f.joueur_courant==1){
            return f.joueurDeux.couleur;
        }
        return f.joueurUn.couleur;
    }
    private int mon_max(int[] tab){
        int taille = tab.length;
        int max = tab[0];
        int ichoisit=0;
        Random r = new Random();
        for(int i=1; i<taille; i++){
            if(tab[i]>max){
                max = tab[i];
                ichoisit = i;
            }else if(tab[i]==max){
                int rand = r.nextInt(2);
                if(rand == 1){
                    ichoisit = i;
                }
            }
        }
        return ichoisit;
    }
    private int mon_min(int[] tab){
        int taille = tab.length;
        int min = tab[0];
        int ichoisit=0;
        Random r = new Random();
        for(int i=1; i<taille; i++){
            if(tab[i]<min){
                min = tab[i];
                ichoisit = i;
            }else if(tab[i]==min){
                int rand = r.nextInt(2);
                if(rand == 1){
                    ichoisit = i;
                }
            }
        }
        return ichoisit;
    }
    private ArrayList<caseM> list_cases_libres(caseM[][] matrice){
        ArrayList<caseM> res = new ArrayList<caseM>();
        for(int i=0; i<f.N; i++){
            for(int j=0; j<f.N; j++){
                if(matrice[i][j].etat == 0){
                    res.add(matrice[i][j]);
                }
            }
        }
        return res;
    }

    public ArrayList<caseM> successeurs(caseM x) {
        //tester le bord différement
        ArrayList<caseM> temp=new ArrayList<caseM>();
        ListIterator it=temp.listIterator();
        if(x.j>0 && f.matrice[x.i][x.j-1].etat!=-1){
            it.add(f.matrice[x.i][x.j-1]);
            //f.matrice[x.i][x.j-1].etat = 4;
        }
        if(x.j<f.N-1 && f.matrice[x.i][x.j+1].etat!=-1){
            it.add(f.matrice[x.i][x.j+1]);
            //f.matrice[x.i][x.j+1].etat = 4;
        }
        if(x.i<f.N-1 && f.matrice[x.i+1][x.j].etat!=-1){
            it.add(f.matrice[x.i+1][x.j]);
            //f.matrice[x.i+1][x.j].etat = 4;
        }
        if(x.i<f.N-1 && x.j<f.N-1 && f.matrice[x.i+1][x.j+1].etat!=-1){
            it.add(f.matrice[x.i+1][x.j+1]);
            //f.matrice[x.i+1][x.j+1].etat = 4;
        }
        if(x.i>0 && f.matrice[x.i-1][x.j].etat!=-1){
            it.add(f.matrice[x.i-1][x.j]);
            //f.matrice[x.i-1][x.j].etat = 4;
        }
        if(x.i>0 && x.j>0 && f.matrice[x.i-1][x.j-1].etat!=-1){
            it.add(f.matrice[x.i-1][x.j-1]);
            //f.matrice[x.i-1][x.j-1].etat = 4;
        }
        return temp;
    }

    public ArrayList<caseM> successeurs_couleur(caseM x) {
        //tester le bord différement
        ArrayList<caseM> temp=new ArrayList<caseM>();
        ListIterator it=temp.listIterator();
        if(x.j>0 && f.matrice[x.i][x.j-1].etat==x.etat){
            it.add(f.matrice[x.i][x.j-1]);
            //f.matrice[x.i][x.j-1].etat = 4;
        }
        if(x.j<f.N-1 && f.matrice[x.i][x.j+1].etat==x.etat){
            it.add(f.matrice[x.i][x.j+1]);
           //f.matrice[x.i][x.j+1].etat = 4;
        }
        if(x.i<f.N-1 && f.matrice[x.i+1][x.j].etat==x.etat){
            it.add(f.matrice[x.i+1][x.j]);
            //f.matrice[x.i+1][x.j].etat = 4;
        }
        if(x.i<f.N-1 && x.j<f.N-1 && f.matrice[x.i+1][x.j+1].etat==x.etat){
         it.add(f.matrice[x.i+1][x.j+1]);
            //f.matrice[x.i+1][x.j+1].etat = 4;
        }
        if(x.i>0 && f.matrice[x.i-1][x.j].etat==x.etat){
            it.add(f.matrice[x.i-1][x.j]);
            //f.matrice[x.i-1][x.j].etat = 4;
        }
        if(x.i>0 && x.j>0 && f.matrice[x.i-1][x.j-1].etat==x.etat){
            it.add(f.matrice[x.i-1][x.j-1]);
            //f.matrice[x.i-1][x.j-1].etat = 4;
        }
        return temp;
    }

       private ArrayList<caseM> successeurs_libres(caseM x){
        ArrayList<caseM> temp = successeurs(x);
        ArrayList<caseM> res = new ArrayList<caseM>();
        ListIterator<caseM> E = temp.listIterator();
        while(E.hasNext()){
            caseM y = E.next();
            if(y.etat == 0){
                res.add(y);
            }
        }
        return res;
    }

    synchronized public boolean estPossibleClic(caseM x) {
       boolean b=false;

       if(f.jeu_fini)
           return false;

        if(!f.partie_commencee){
            if(estBord(x))
                b=false;
            else if(!estBord(x) && x.etat==0){
                b=true;
            }
        }else{
            if(x.etat==0)
                b=true;
            else
                b=false;
        }
       return b;
    }

    public void changerEtat(caseM x, int joueur) {
        if(estPossibleClic(x)){
            // peut etre modifier par if(f.joueur_courant==1) directement?
            if(joueur==1){
                f.matrice[x.i][x.j].etat=f.joueurUn.couleur;
                 f.matrice[x.i][x.j].couleur=f.joueurUn.couleur;
            }else{
                f.matrice[x.i][x.j].etat=f.joueurDeux.couleur;
                 f.matrice[x.i][x.j].couleur=f.joueurDeux.couleur;
            }
            f.aire.poly_prop_coup = null;
            f.tracer_eclipse = false;
        }
    }

    private ArrayList<caseM> boules_sur_un_bord(int etat, int cote){

        int i, j;
        ArrayList<caseM> L = new ArrayList<caseM>();

        if(cote == 1){
            i = 0;
            while(i<=(f.N/2)){ //N correspond a la taille de l'hexagone
                    if(f.matrice[0][i].etat == etat){
                        //mettre tous les caseM avec l'etat etat dans la liste temp
                        L.add(f.matrice[0][i]);
                    }
                    i++;
            }
        }else if(cote == 2){
            i = 0;
            j = f.N/2;
            while((i<=(f.N/2)) && (j<f.N)){ //N correspond a la taille de l'hexagone
                    if(f.matrice[i][j].etat == etat){
                            L.add(f.matrice[i][j]);
                    }
                    //f.matrice[i][j].etat = 8; //test
                    i++;
                    j++;
            }
        }else if(cote == 3){
            i = f.N/2;
            while(i<f.N){ //N correspond a la taille de l'hexagone
                    if(f.matrice[i][f.N-1].etat == etat){
                        L.add(f.matrice[i][f.N-1]);
                    }
                    i++;
            }
        }else if(cote == 4){
            i=f.N/2;
            while(i<f.N){ //N correspond a la taille de l'hexagone
                    if(f.matrice[f.N-1][i].etat == etat){
                        L.add(f.matrice[f.N-1][i]);
                    }
                    i++;
            }

        }else if(cote == 5){
            i = f.N/2;
            j = 0;
            while((i<f.N) && j<=(f.N/2)){ //N correspond a la taille de l'hexagone
                    if(f.matrice[i][j].etat == etat){
                            L.add(f.matrice[i][j]);
                    }
                    //f.matrice[i][j].etat = 8; //test
                    i++;
                    j++;
            }
        }else if(cote == 6){
            i=0;
            while(i<=(f.N/2)){ //N correspond a la taille de l'hexagone
                    if(f.matrice[i][0].etat == etat){
                        L.add(f.matrice[i][0]);
                    }
                    i++;
            }
        }
        return L;
    }

    private ArrayList<caseM> boule_voisins_bord_ou_bord_deux(int etat, int cote1, int cote2){
        boolean b;
        int i;
        ArrayList<caseM> L1 = new ArrayList<caseM>();
        L1 = boules_a_cote_bord(etat, cote1);
        if(L1.isEmpty()){  // si cote1 n'a pas de boules a cote des bords
            L1=boules_sur_un_bord(etat, cote1);
            if(L1.isEmpty()){  // si cote1 n'a pas de boules a cote des bords ni de boules sur les bords
                return new ArrayList<caseM>();
            }
            else{  // si cote1 a juste des boules sur le bord
                ArrayList<caseM> L2 = new ArrayList<caseM>();
                L2 = boules_a_cote_bord(etat, cote2);
                if(L2.isEmpty()){  //  si cote1 a juste des boules sur le bord mais cote2 n'a pas de boule a cote des bords
                  L2=boules_sur_un_bord(etat, cote2);
                  if(L2.isEmpty()){ // si cote1 a juste des boules sur le bord et cote 2 n'a pas de boules a cote ou sur les bords
                       return new ArrayList<caseM>();
                   }
                  else{  // si cote1 a juste des boules sur le bord et cote2 a juste des boules sur le bord
                       ListIterator<caseM> E = L2.listIterator();
                       while(E.hasNext()){
                            L1.add(E.next());
                        }
                     return L1;

                  }
                }
                else{ // si cote1 a juste des boules sur le bord et cote2 a des boules a cote des bords
                 ListIterator<caseM> E = L2.listIterator();
                 while(E.hasNext()){
                        L1.add(E.next());
                 }
                 return L1;
                }


            }


        }
        else{  // si cote1  a des boules a cote des bords
            ArrayList<caseM> L2 = new ArrayList<caseM>();
            L2 = boules_a_cote_bord(etat, cote2);
            if(L2.isEmpty()){ // si cote1  a des boules a cote des bords et cote2 n'a pas de boule a cote des bords
                L2=boules_sur_un_bord(etat, cote2);
                if(L2.isEmpty()){ // si cote1  a des boules a cote des bords et cote2 n'a pas de boule a cote des bords ni de boules sur els bords
                    return new ArrayList<caseM>();
                }
                else{ // si cote1  a des boules a cote des bords et cote2 a juste des boules sur les bords
                    ListIterator<caseM> E = L2.listIterator();
                    while(E.hasNext()){
                        L1.add(E.next());
                    }
                    return L1;

                }
            }
            else{ // cote 1 et cote2 ont des boules a cote des bords
                ListIterator<caseM> E = L2.listIterator();
                while(E.hasNext()){
                        L1.add(E.next());
                }
                return L1;
            }

        }

    }

    private ArrayList<caseM> boules_sur_deux_bord(int etat, int cote1, int cote2){
        /* boules_sur_deux_bord(e, b1; b2) retourne une liste des caseM's qui ont
         * le même état que e (1er parametre) qui se trouvent sur les deux bords
         * indiquées par b1 et b2 (le 2eme et 3eme parametres).
         * La valeur de retour est une liste vide ssi soit il n'y a pas de
         * caseM's sur aucun bord indiqué par e, soit il y a des caseM's
         * sur un des deux côtes mais pas sur les deux.
         *
         * Par def. les côtes 1 et 4 sont les deux bords opposés en haut et
         * en bas de l'hexagone.
         * Les côtes 2 et 5 sont ceux en haut à droite et en bas à gauche.
         * Les côtes 3 et 6 sont ceux en bas à droite et en haut à gauche.
         */

        boolean Bool;
        int i;
        ArrayList<caseM> L1 = new ArrayList<caseM>();

        L1 = boules_sur_un_bord(etat, cote1);
        //regarder le 1er bord
        if(L1.isEmpty()){
           // System.out.println("1er Bord = false");
            //1er bord vide -> retourner liste vide
            return new ArrayList<caseM>();
        }else{
            //si le 1er bord n'est pas vide
           // System.out.println("1er Bord = true");
            ArrayList<caseM> L2 = new ArrayList<caseM>();
            L2 = boules_sur_un_bord(etat, cote2);
            //regarder le 2eme bord (opposé)
                if(L2.isEmpty()){
                    System.out.println("2er Bord = false");
                    //2eme bord vide -> retourner liste vide
                    return new ArrayList<caseM>();
                }else{
                    //si le 2eme bord n'est pas vide
                    System.out.println("2er Bord = true");
                    ListIterator<caseM> E = L2.listIterator();
                    while(E.hasNext()){
                        L1.add(E.next());
                    }
                    return L1;
                }
        }
    }

    private void effacer_45(){

        for(int i = 0 ; i < f.N ; i++){
            for(int j = 0 ; j < f.N ; j++){
                if(f.matrice[i][j].etat == 4 || f.matrice[i][j].etat == 5){
                    f.matrice[i][j].etat = 0;
                }
            }
       }
    }

    public void remplir_matrice_a_vide(){


        int tmp_x = pos_x;

            int cote = 20;
            int numero=1,k=0;

            for(int i=0; i<f.N; i++){


                    for(int j=0; j<f.N; j++)
                    {
                            if(i==0 && j==0){
                                    cote = 61;
                            }else if(i==0 && j==f.N/2){
                                    cote = 12;
                            }else if(i==f.N/2 && j==f.N-1){
                                    cote = 23;
                            }else if(i==f.N-1 && j==f.N-1){
                                    cote = 34;
                            }else if(i==f.N-1 && j==f.N/2){
                                    cote = 45;
                            }else if(i==f.N/2 && j==0){
                                    cote = 56;
                            }else if(i==0 && j>=0 && j<=f.N/2){
                                    cote = 1;
                            }else if(i==f.N-1 && j>=f.N/2){
                                    cote = 4;
                            }else if(i>=0 && i<f.N/2 && j==0){
                                    cote = 6;
                            }else if(i>=f.N/2 && i<f.N && j==f.N-1){
                                    cote = 3;
                            }else if(i>=0 && i<=f.N/2 && j==f.N/2+i){
                                    cote = 2;
                            }else if(j>=0 && j<=f.N/2 && i==f.N/2+j){
                                    cote = 5;
                            }else{
                                    cote = 0;
                            }

                            if(j>((f.N/2)+i) || i>((f.N/2)+j)){

                                f.matrice[i][j] = new caseM(i, j, -1, cote,null);
                                f.matrice[i][j].numero_floyd=-1;

                            }
                            else{
                                  int x[] = {pos_x,pos_x+(int)(cote_poly*Math.cos(Math.PI/6)),pos_x+(int)(2*cote_poly*Math.cos(Math.PI/6)),pos_x+(int)(2*cote_poly*Math.cos(Math.PI/6)),pos_x+(int)(cote_poly*Math.cos(Math.PI/6)),pos_x};
                                   int y[]= {pos_y,pos_y-(int)(cote_poly*Math.cos(Math.PI/3)),pos_y,pos_y+cote_poly,pos_y+cote_poly+(int)(cote_poly*Math.cos(Math.PI/3)),pos_y+cote_poly};
                                    Polygon tmp = new Polygon(x,y,6);

                                    f.matrice[i][j] = new caseM(i, j, 0, cote,tmp);
                                    f.matrice[i][j].pos_x = pos_x;
                                     f.matrice[i][j].pos_y = pos_y;
                                      f.matrice[i][j].numero_floyd=numero;
                                      numero_vers_case[k]=new ConversionFloyd(f.matrice[i][j], numero);
                                       numero++;
                                       k++;
                            }
                                    pos_x =(int)(pos_x+2*cote_poly*Math.cos(Math.PI/6));
                    }
                        pos_y = pos_y + cote_poly +(int)(cote_poly*Math.cos(Math.PI/3));
                         tmp_x = tmp_x - (int)(cote_poly*Math.cos(Math.PI/6));
                         pos_x = tmp_x;

            }


    }

    public void afficher_matrice_sous_forme_text(int selon){
        System.out.print("    ");
        for(int m=0;m<f.N;m++){
           System.out.print(m+"  ");
       }
        System.out.println("");
        for(int m=0;m<f.N;m++){
           System.out.print("---");
       }
       System.out.println("");
        for(int i=0; i<f.N; i++){

                    System.out.print(i+"| ");

                for(int j=0; j<f.N; j++){
                        if(selon==1){
                                //selon cote
                                if(f.matrice[i][j].numcote == 12
                                   || f.matrice[i][j].numcote == 23
                                   || f.matrice[i][j].numcote == 34
                                   || f.matrice[i][j].numcote == 45
                                   || f.matrice[i][j].numcote == 56
                                   || f.matrice[i][j].numcote == 61){
                                        System.out.print(f.matrice[i][j].numcote+" ");
                                }else{
                                        System.out.print(" "+f.matrice[i][j].numcote+" ");
                                }
                        }else if(selon==2){
                                //selon etat
                                if(f.matrice[i][j].etat == -1){
                                        System.out.print(f.matrice[i][j].etat+" ");
                                }else{
                                        System.out.print(" "+f.matrice[i][j].etat+" ");
                                }
                        }
                        else if(selon==3){
                            if(f.matrice[i][j].numero_floyd == -1){
                                System.out.print(f.matrice[i][j].numero_floyd+" ");
                            }
                            else{
                                System.out.print(" "+f.matrice[i][j].numero_floyd+" ");
                            }
                    }


                }
                System.out.println("");

        }
        System.out.println("");
        System.out.println("========AFFICHAGE TABLEAU DE CONVERSION DE FLOYD============");
         if(selon==3){
            for(int k=0;k<numero_vers_case.length;k++){
              System.out.print("Numero "+numero_vers_case[k].numero+" case :  i"+numero_vers_case[k].case_matrice.i+" , j"+numero_vers_case[k].case_matrice.j+" etat: "+numero_vers_case[k].case_matrice.etat+"  // ");
             }
        }
    }

    public void afficher_floyd(int [][] matrice_floyd){
            for(int i=0;i<matrice_floyd.length;i++){
                 for(int j=0;j<matrice_floyd.length;j++){

                     if(matrice_floyd[i][j]<100){
                        System.out.print(matrice_floyd[i][j]+" |");
                     }
                     else{
                         System.out.print("++|");
                     }
                 }
                 System.out.println("");
            }
            System.out.println("===================");
            System.out.println("");




    }
  /*  public void afficher_liaison_floyd(){
        for(int i=0;i<matrice_liaison_courante.length;i++){
                 for(int j=0;j<matrice_liaison_courante.length;j++){

                     if(matrice_liaison_courante[i][j].indice<100)
                        System.out.print(matrice_liaison_courante[i][j].indice+" |");
                     else
                         System.out.print("++|");
                 }
                 System.out.println("");
            }
            System.out.println("===================");
            System.out.println("");


    }*/

    public void remplir_matrice(){
        f.matrice[0][0].etat = 1;
        f.matrice[1][0].etat = 1;
        f.matrice[2][0].etat = 1;
        f.matrice[3][0].etat = 1;
        f.matrice[4][0].etat = 1;
        f.matrice[5][0].etat = 1;
        f.matrice[6][1].etat = 1;
        f.matrice[7][2].etat = 1;
        f.matrice[8][3].etat = 1;
        f.matrice[9][4].etat = 1;
        f.matrice[10][5].etat = 1;
    }
    public void remplir_matrice2(){
     //   f.matrice[2][2].etat = 1;
        f.matrice[2][3].etat = 1;
        f.matrice[5][2].etat = 1;
        f.matrice[5][6].etat = 1;
        f.matrice[7][7].etat = 1;
        f.matrice[8][9].etat = 1;
        //f.matrice[3][4].etat = 1;
        f.matrice[4][4].etat = 1;
        f.matrice[4][3].etat = 1;
        f.partie_commencee=true;
        f.joueurUn.couleur = 2;
        f.joueurDeux.couleur = 1;
        f.joueur_courant = 1;
        f.mode_IA.DifficulteIA2 = 2;
        f.mode_IA.JcIA = true;
    }

    private void marquer_case(caseM x){
        MatriceDeMarquage[x.i][x.j] = true;
    }
    private void demarquer_case(caseM x){
        MatriceDeMarquage[x.i][x.j] = false;
    }

    private void demarquer_tout_case(){
        for(int i=0; i<f.N; i++){
            for(int j=0; j<f.N; j++){
                MatriceDeMarquage[i][j] = false;
            }
        }
    }

    private boolean liste_marquee(ArrayList<caseM> L){
        // Indique si chaque element de la liste L est marqué
        ListIterator<caseM> E = L.listIterator();
        while(E.hasNext()){
            caseM res = E.next();
            if(!case_est_marquee(res)){
                return false;
            }
        }
        return true;
    }

    private int nb_ponts_chemin(ArrayList<caseM> L){
        /* Retourne le nombre de ponts dans la liste L.
         */
        int res = 0;
        ArrayList<caseM> copieL = copie_list(L);
        ListIterator<caseM> E2;
        ListIterator<caseM> E1 = copieL.listIterator();
        while(E1.hasNext()){
            caseM x = E1.next();
            ArrayList<caseM> ponts_x = pont_caseM(x);
            E1.remove();
            E2 = copieL.listIterator();
            while(E2.hasNext()){
                caseM y = E2.next();
                if(cases_egales(y, ponts_x)){
                    res++;
                }
            }
        }
        return res;
    }
    private boolean case_est_marquee(caseM x){
        return MatriceDeMarquage[x.i][x.j];
    }

    private boolean _existe_chemin(caseM x, caseM y) {
        if(x==y){
            return true;
        }else{
            marquer_case(x);
            ArrayList<caseM> L = successeurs_couleur(x);
            ListIterator LI = L.listIterator();
            while(LI.hasNext()){
                caseM next = (caseM)LI.next();
                if(!case_est_marquee(next)){
                    if(_existe_chemin(next, y)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private boolean existe_chemin(caseM x, caseM y) {
        demarquer_tout_case();
        return _existe_chemin(x, y);
    }

    private boolean cases_egales(caseM x, ArrayList<caseM> H){
        ListIterator<caseM> LI = H.listIterator();
        while(LI.hasNext()){
            caseM y = LI.next();
            if((x.i == y.i) && (x.j == y.j)){
                    return true;
            }
        }
        return false;
    }

    private boolean est_bord_selon_numcote(caseM x,int num){

        switch (num){
            case 1:
                return (x.numcote==61 || x.numcote==12 || x.numcote==1) ;

            case 2:
                return (x.numcote==12 || x.numcote==23 || x.numcote==2);
            case 3:
                return (x.numcote==23 || x.numcote==34 || x.numcote==3);
            case 4:
                return (x.numcote==34 || x.numcote==45 || x.numcote==4);
            case 5:
                return (x.numcote==45 || x.numcote==56 || x.numcote==5);
            case 6:
                return (x.numcote==56 || x.numcote==61 || x.numcote==6);
            default:
                return false;
        }


    }

    public boolean est_voisin_d_un_bord(caseM x,int bord){
        ArrayList<caseM> succ =new ArrayList<caseM>();
        succ=successeurs(x);
        int i=0;
        ListIterator<caseM> it=succ.listIterator();

        while(it.hasNext()){
            caseM tmp=it.next();
            if(est_bord_selon_numcote(tmp, bord) && (tmp.etat==0 || tmp.etat==x.etat)){
                i++;

            }
        }

        return i>=2 && !est_bord_selon_numcote(x, bord);
    }
    public boolean est_voisin_d_un_bord_casse(caseM x,int bord){
       boolean b=false;
       if(est_voisin_d_un_bord(x, bord)){
           ArrayList<caseM> succ =new ArrayList<caseM>();
           succ=successeurs(x);
           ListIterator<caseM> it=succ.listIterator();
           int i=0;
           while(it.hasNext()){
               caseM tmp=it.next();
               if(est_bord_selon_numcote(tmp, bord) && tmp.etat!=0 && tmp.etat!=x.etat)
                   i++;

           }
           if(i==1)
               b=true;
       }
       return b;
    }
    private ArrayList<caseM> existe_jokers(int bord,int couleur){
        ListIterator<Joker1> IT=Joker1.listIterator();
        ArrayList<caseM> res=new ArrayList<caseM>();
        while(IT.hasNext()){
            Joker1 tmp1=IT.next();
            if(tmp1.bord==bord && tmp1.joker.etat==couleur){
                if(est_joker1_selon_bord(tmp1.joker,bord))
                    res.add(tmp1.joker);
                 //   System.out.println("POUET POUET ICI J4AJOUTE   "+tmp1.joker.i+"  "+tmp1.joker.j);
            }


        }
        ListIterator<Joker2> IT2=Joker2.listIterator();
        while(IT2.hasNext()){
            Joker2 tmp2=IT2.next();

                int i=0;
                if(tmp2.joker.etat==couleur){
                    /*if(tmp2.pont2.numcote==bord){

                        if(tmp2.pont2.etat==couleur || tmp2.pont2.etat==0)
                            i++;
                        if(est_voisin_d_un_bord(tmp2.pont1, bord))
                            i++;
                    }

                    else if (tmp2.pont3.numcote == bord) {
                         if(tmp2.pont3.etat==couleur || tmp2.pont3.etat==0)
                            i++;
                        if(est_voisin_d_un_bord(tmp2.pont4, bord))
                            i++;
                    }*/
                    if(est_joker2_selon_bord(tmp2.joker, bord))
                        res.add(tmp2.joker);
                }
             /* if(i>=2)
                  res.add(tmp2.joker);*/

        }
        return res;
    }
    private Joker1 retourne_joker1_depuis_case(caseM x){
        ListIterator<Joker1> it=Joker1.listIterator();
        Joker1 res = null;
        while(it.hasNext()){
            Joker1 tmp=it.next();
            if(tmp.joker==x)
                res=tmp;
        }
        return res;
    }
    private Joker2 retourne_joker2_depuis_case(caseM x){
        ListIterator<Joker2> it=Joker2.listIterator();
        Joker2 res = null;
        while(it.hasNext()){
            Joker2 tmp=it.next();
            if(tmp.joker==x)
                res=tmp;
        }
        return res;
    }
    private ArrayList<caseM> boules_a_cote_bord(int couleur,int cote){
        //retourne les jokers libre, sinon les boules voisines au bord

        ArrayList<caseM> L = new ArrayList<caseM>();
         ArrayList<caseM> L2 = new ArrayList<caseM>();
        int i,j;
        L=existe_jokers(cote, couleur);



            if(cote == 1){
                i = 1;
                while(i<=((f.N-1)/2)){ //N correspond a la taille de l'hexagone
                        if(f.matrice[1][i].etat == couleur && est_voisin_d_un_bord(f.matrice[1][i], cote)){
                            //mettre tous les caseM avec l'etat etat dans la liste temp
                            L2.add(f.matrice[1][i]);
                        }
                        i++;
                }

            }
            if(cote == 2){
                i = 1;
                j=(f.N-1)/2;
                while(i<=((f.N-1)/2) && j<f.N-1){ //N correspond a la taille de l'hexagone
                        if(f.matrice[i][j].etat == couleur && est_voisin_d_un_bord(f.matrice[i][j], cote)){
                            //mettre tous les caseM avec l'etat etat dans la liste temp
                            L2.add(f.matrice[i][j]);
                        }
                        i++;
                        j++;
                }

            }
            if(cote == 3){
                i =(f.N-1)/2;

                while(i<=((f.N-1))){ //N correspond a la taille de l'hexagone
                        if(f.matrice[i][f.N-2].etat == couleur && est_voisin_d_un_bord(f.matrice[i][f.N-2], cote)){
                            //mettre tous les caseM avec l'etat etat dans la liste temp
                            L2.add(f.matrice[i][f.N-2]);
                        }
                        i++;

                }

            }
            if(cote == 4){

                i = (f.N-1)/2;

                while(i<=(f.N-1)){ //N correspond a la taille de l'hexagone
                        if(f.matrice[f.N-2][i].etat == couleur && est_voisin_d_un_bord(f.matrice[f.N-2][i], cote)){
                            //mettre tous les caseM avec l'etat etat dans la liste temp
                            L2.add(f.matrice[f.N-2][i]);
                        }
                        i++;
                }

            }
            if(cote == 5){
                i = (f.N-1)/2;
                j=1;
                while(i<=((f.N-1)) && j<(f.N-1)/2){ //N correspond a la taille de l'hexagone
                        if(f.matrice[i][j].etat == couleur && est_voisin_d_un_bord(f.matrice[i][j], cote)){
                            //mettre tous les caseM avec l'etat etat dans la liste temp
                            L2.add(f.matrice[i][j]);
                        }
                        i++;
                        j++;
                }

            }
            if(cote == 6){
                i = 1;
                while(i<=((f.N-1)/2)){ //N correspond a la taille de l'hexagone
                        if(f.matrice[i][1].etat == couleur && est_voisin_d_un_bord(f.matrice[i][1], cote)){
                            //mettre tous les caseM avec l'etat etat dans la liste temp
                            L2.add(f.matrice[i][1]);
                        }
                        i++;
                }

            }
       ListIterator<caseM> it2=L2.listIterator();
       while(it2.hasNext()){
           caseM xd=it2.next();
           L.add(xd);
       }

       return L;
    }

    private boolean _existe_chemin_successeurs_pont(int numcote, caseM x, ArrayList<caseM> H){
        int n = x.numcote;
        if(!(((numcote == 1 || numcote == 61 || numcote == 12) && (n == 1 || n == 61 || n == 12))
                    || ((numcote == 2 || numcote == 12 || numcote == 23) && (n == 2 || n == 12 || n == 23))
                    || ((numcote == 3 || numcote == 23 || numcote == 34) && (n == 3 || n == 23 || n == 34))
                    || ((numcote == 4 || numcote == 34 || numcote == 45) && (n == 4 || n == 34 || n == 45))
                    || ((numcote == 5 || numcote == 45 || numcote == 56) && (n == 5 || n == 45 || n == 56))
                    || ((numcote == 6 || numcote == 56 || numcote == 61) && (n == 6 || n == 56 || n == 61)))
                    && cases_egales(x, H)){
            marquer_case(x);
            return true;
        }else
            /*probleme avec deux côtes adjacentes si x appartient aux deux*/
            /*res: prendre les deux côtes de H, si 1 et 2, alors si numcote = 1 alors oui etc.*/
            /*ne doit pas se produire vu les règles du jeu*/
            /*if((numcote == 12 ||
                numcote == 23 ||
                numcote == 34 ||
                numcote == 45 ||
                numcote == 56 ||
                numcote == 61) && numcote == x.numcote){
            return true;
        }else*/{
            marquer_case(x);
            ArrayList<caseM> L = successeurs_couleur(x);
            ArrayList<caseM> P= pont_caseM_couleur(x);

            ListIterator PI = P.listIterator();
            while(PI.hasNext()){

               caseM next=(caseM)PI.next();
              if(!est_pont_cassé(x, next))
                L.add(next);

            }
            ListIterator LI = L.listIterator();
            while(LI.hasNext()){
                caseM next = (caseM)LI.next();
                if(!case_est_marquee(next)){
                    if(_existe_chemin_successeurs_pont(numcote, next, H)){
                        return true;
                    }
                }
            }
            return false;
        }
    }
    //MARCHE existe_chemin_successeurs
    private boolean existe_chemin_successeurs_pont(caseM x, ArrayList<caseM> H) {
        /* Indique s'il existe un chemin qui commence obligatoirement par x et
         * qui se termine par une des cases qui se trouvent dans la liste H.
         * Precond: x et tous les elements de la liste H sont de la meme couleur.
         */
        demarquer_tout_case();
        if(_existe_chemin_successeurs_pont(x.numcote, x, H)){
            /*
            System.out.println("Tableau de Marquage:----------------");
            for(int i = 0 ; i < f.N ; i++){
                for(int j = 0 ; j < f.N ; j++){
                    if(MatriceDeMarquage[i][j]==true){
                        System.out.print("1 ");
                    }else{
                        System.out.print("0 ");
                    }
                }
                System.out.println("");
            }
            */
            return true;
        }
        else{
            /*
            System.out.println("Tableau de Marquage:----------------");
            for(int i = 0 ; i < f.N ; i++){
                for(int j = 0 ; j < f.N ; j++){
                    if(MatriceDeMarquage[i][j]==true){
                        System.out.print("1 ");
                    }else{
                        System.out.print("0 ");
                    }
                }
                System.out.println("");
            }
            */
            return false;
        }
    }
    //MARCHE _existe_chemin_successeurs2
    // renvoit un arraylist des ponts possibles ainsi que des successeurs.
    private ArrayList<caseM> _existe_chemin_successeurs2_pont(int numcote, caseM x, ArrayList<caseM> H){
        marquer_case(x);
        if(cases_egales(x, H)){
            /*if(x.numcote != numcote){*/
            int n = x.numcote;
            if(!(((numcote == 1 || numcote == 61 || numcote == 12) && (n == 1 || n == 61 || n == 12))
                    || ((numcote == 2 || numcote == 12 || numcote == 23) && (n == 2 || n == 12 || n == 23))
                    || ((numcote == 3 || numcote == 23 || numcote == 34) && (n == 3 || n == 23 || n == 34))
                    || ((numcote == 4 || numcote == 34 || numcote == 45) && (n == 4 || n == 34 || n == 45))
                    || ((numcote == 5 || numcote == 45 || numcote == 56) && (n == 5 || n == 45 || n == 56))
                    || ((numcote == 6 || numcote == 56 || numcote == 61) && (n == 6 || n == 56 || n == 61)))){
                // si on arrive a la fin et c'est sur le bord different du début
                ArrayList<caseM> res = new ArrayList<caseM>();
                res.add(x);
                return res;
            }else{
                // si on arrive a la fin mais x est sur le meme côte qu'au début
                return new ArrayList<caseM>();
            }
        }else{
            ArrayList<caseM> L = successeurs_couleur(x);
            ArrayList<caseM> res = new ArrayList<caseM>();
            ArrayList<caseM> res2 = new ArrayList<caseM>();
            //
            ArrayList<caseM> P= pont_caseM_couleur(x);

            ListIterator PI = P.listIterator();
            while(PI.hasNext()){
               caseM next=(caseM)PI.next();
               if(!est_pont_cassé(x, next))
                L.add(next);

            }
            ListIterator LI = L.listIterator();
            //
            while(LI.hasNext()){
                caseM next = (caseM)LI.next();
                if(!case_est_marquee(next)){
                    res = _existe_chemin_successeurs2_pont(numcote, next, H);
                    if(!res.isEmpty()){
                        // si une branche choisie arrive au resultat
                       ListIterator ResI = res.listIterator();
                       while(ResI.hasNext()){
                         caseM nextz= (caseM) ResI.next();
                         res2.add(nextz);
                        }
                       res2.add(x);
                    }
                }
            }
            return res2;

        }
    }
    //MARCHE existe_chemin_successeurs2
    public ArrayList<caseM> existe_chemin_successeurs2_pont(caseM x, ArrayList<caseM> H) {
        if(x.numcote == 61){
                demarquer_tout_case();
                return _existe_chemin_successeurs2_pont(1, x, H);

        }
        if(x.numcote == 12){
                demarquer_tout_case();
                return _existe_chemin_successeurs2_pont(2, x, H);

        }
        if(x.numcote == 23){
                demarquer_tout_case();
                return _existe_chemin_successeurs2_pont(3, x, H);
        }
        if(x.numcote == 34){
                demarquer_tout_case();
                return _existe_chemin_successeurs2_pont(4, x, H);

        }
        if(x.numcote == 45){
                demarquer_tout_case();
                return _existe_chemin_successeurs2_pont(5, x, H);

        }
        if(x.numcote == 56){
                demarquer_tout_case();
                return _existe_chemin_successeurs2_pont(6, x, H);

        }
        demarquer_tout_case();
        return _existe_chemin_successeurs2_pont(x.numcote, x, H);
    }

    private boolean _existe_chemin_successeurs(int numcote, caseM x, ArrayList<caseM> H){
        int n = x.numcote;
        if(!(((numcote == 1 || numcote == 61 || numcote == 12) && (n == 1 || n == 61 || n == 12))
                    || ((numcote == 2 || numcote == 12 || numcote == 23) && (n == 2 || n == 12 || n == 23))
                    || ((numcote == 3 || numcote == 23 || numcote == 34) && (n == 3 || n == 23 || n == 34))
                    || ((numcote == 4 || numcote == 34 || numcote == 45) && (n == 4 || n == 34 || n == 45))
                    || ((numcote == 5 || numcote == 45 || numcote == 56) && (n == 5 || n == 45 || n == 56))
                    || ((numcote == 6 || numcote == 56 || numcote == 61) && (n == 6 || n == 56 || n == 61)))
                    && cases_egales(x, H)){
            marquer_case(x);
            marquer_case(x);
            return true;
        }else
            /*probleme avec deux côtes adjacentes si x appartient aux deux*/
            /*res: prendre les deux côtes de H, si 1 et 2, alors si numcote = 1 alors oui etc.*/
            /*ne doit pas se produire vu les règles du jeu*/
            /*if((numcote == 12 ||
                numcote == 23 ||
                numcote == 34 ||
                numcote == 45 ||
                numcote == 56 ||
                numcote == 61) && numcote == x.numcote){
            return true;
        }else*/{
            marquer_case(x);
            ArrayList<caseM> L = successeurs_couleur(x);
            ListIterator LI = L.listIterator();
            while(LI.hasNext()){
                caseM next = (caseM)LI.next();
                if(!case_est_marquee(next)){
                    if(_existe_chemin_successeurs(numcote, next, H)){
                        return true;
                    }
                }
            }
            return false;
        }
    }
    //MARCHE existe_chemin_successeurs
    private boolean existe_chemin_successeurs(caseM x, ArrayList<caseM> H) {
        /* Indique s'il existe un chemin qui commence obligatoirement par x et
         * qui se termine par une des cases qui se trouvent dans la liste H.
         * Precond: x et tous les elements de la liste H sont de la meme couleur.
         */
        demarquer_tout_case();
        if(_existe_chemin_successeurs(x.numcote, x, H)){
            /*
            System.out.println("Tableau de Marquage:----------------");
            for(int i = 0 ; i < f.N ; i++){
                for(int j = 0 ; j < f.N ; j++){
                    if(MatriceDeMarquage[i][j]==true){
                        System.out.print("1 ");
                    }else{
                        System.out.print("0 ");
                    }
                }
                System.out.println("");
            }
            */
            return true;
        }
        else{
            /*
            System.out.println("Tableau de Marquage:----------------");
            for(int i = 0 ; i < f.N ; i++){
                for(int j = 0 ; j < f.N ; j++){
                    if(MatriceDeMarquage[i][j]==true){
                        System.out.print("1 ");
                    }else{
                        System.out.print("0 ");
                    }
                }
                System.out.println("");
            }
            */
            return false;
        }
    }
    //MARCHE _existe_chemin_successeurs2
    // renvoit un arraylist des successeurs possibles
    private ArrayList<caseM> _existe_chemin_successeurs2(int numcote, caseM x, ArrayList<caseM> H){
        marquer_case(x);
        if(cases_egales(x, H)){
            /*if(x.numcote != numcote){*/
            int n = x.numcote;
            if(!(((numcote == 1 || numcote == 61 || numcote == 12) && (n == 1 || n == 61 || n == 12))
                    || ((numcote == 2 || numcote == 12 || numcote == 23) && (n == 2 || n == 12 || n == 23))
                    || ((numcote == 3 || numcote == 23 || numcote == 34) && (n == 3 || n == 23 || n == 34))
                    || ((numcote == 4 || numcote == 34 || numcote == 45) && (n == 4 || n == 34 || n == 45))
                    || ((numcote == 5 || numcote == 45 || numcote == 56) && (n == 5 || n == 45 || n == 56))
                    || ((numcote == 6 || numcote == 56 || numcote == 61) && (n == 6 || n == 56 || n == 61)))){
                // si on arrive a la fin et c'est sur le bord different du début
                ArrayList<caseM> res = new ArrayList<caseM>();
                res.add(x);
                return res;
            }else{
                // si on arrive a la fin mais x est sur le meme côte qu'au début
                return new ArrayList<caseM>();
            }
        }else{
            ArrayList<caseM> L = successeurs_couleur(x);
            ArrayList<caseM> res = new ArrayList<caseM>();
            ArrayList<caseM> res2 = new ArrayList<caseM>();
            ListIterator LI = L.listIterator();
            while(LI.hasNext()){
                caseM next = (caseM)LI.next();
                if(!case_est_marquee(next)){
                    res = _existe_chemin_successeurs2(numcote, next, H);
                    if(!res.isEmpty()){
                       ListIterator ResI = res.listIterator();
                       while(ResI.hasNext()){
                         caseM nextz= (caseM) ResI.next();
                         res2.add(nextz);
                        }
                       res2.add(x);
                    }
                }
            }
            return res2;

        }
    }
    //MARCHE existe_chemin_successeurs2

    public ArrayList<caseM> existe_chemin_successeurs2(caseM x, ArrayList<caseM> H) {
        if(x.numcote == 61){
                demarquer_tout_case();
                return _existe_chemin_successeurs2(1, x, H);

        }
        if(x.numcote == 12){
                demarquer_tout_case();
                return _existe_chemin_successeurs2(2, x, H);

        }
        if(x.numcote == 23){
                demarquer_tout_case();
                return _existe_chemin_successeurs2(3, x, H);
        }
        if(x.numcote == 34){
                demarquer_tout_case();
                return _existe_chemin_successeurs2(4, x, H);

        }
        if(x.numcote == 45){
                demarquer_tout_case();
                return _existe_chemin_successeurs2(5, x, H);

        }
        if(x.numcote == 56){
                demarquer_tout_case();
                return _existe_chemin_successeurs2(6, x, H);

        }
        demarquer_tout_case();
        return _existe_chemin_successeurs2(x.numcote, x, H);
    }

    @Override
    public ArrayList<caseM> prendre_de_cote_diff_ponts_bords(int numcote, ArrayList<caseM>L1){
        ArrayList<caseM> res1 = new ArrayList<caseM>();
       /* ArrayList<caseM> res2 = new ArrayList<caseM>();*/
        ArrayList<caseM> joker=new ArrayList<caseM>();
        ListIterator<caseM> LE = L1.listIterator();
        while(LE.hasNext()){
            caseM c = LE.next();


            if(numcote == 1){
                if(!((joker=existe_jokers(4,c.etat)).isEmpty())){
                    ListIterator<caseM> jokerIT=joker.listIterator();
                    while(jokerIT.hasNext()){
                        caseM tmplol=jokerIT.next();
                        res1.add(tmplol);
                    }
                }
                else if(est_voisin_d_un_bord(c, 4))
                    res1.add(c);
                else if (c.numcote == 4 || c.numcote == 34 || c.numcote == 45) {
                    res1.add(c);
                }
            }else if(numcote == 2){
                if(!((joker=existe_jokers(5,c.etat)).isEmpty())){
                    ListIterator<caseM> jokerIT=joker.listIterator();
                    while(jokerIT.hasNext()){
                        caseM tmplol=jokerIT.next();
                        res1.add(tmplol);
                    }
                }
                else if(est_voisin_d_un_bord(c, 5))
                    res1.add(c);
                else if(c.numcote == 5 || c.numcote == 45 || c.numcote == 56)
                {
                    res1.add(c);
                }
            }else if(numcote == 3){
                if(!((joker=existe_jokers(6,c.etat)).isEmpty())){
                    ListIterator<caseM> jokerIT=joker.listIterator();
                    while(jokerIT.hasNext()){
                        caseM tmplol=jokerIT.next();
                        res1.add(tmplol);
                    }
                }
                else if(est_voisin_d_un_bord(c, 6))
                    res1.add(c);
                else if(c.numcote == 6 || c.numcote == 56 || c.numcote ==61)
                {
                    res1.add(c);
                }
            }else if(numcote == 4){
                if(!((joker=existe_jokers(1,c.etat)).isEmpty())){
                    ListIterator<caseM> jokerIT=joker.listIterator();
                    while(jokerIT.hasNext()){
                        caseM tmplol=jokerIT.next();
                        res1.add(tmplol);
                    }
                }
                else if(est_voisin_d_un_bord(c, 1))
                    res1.add(c);
                else if (c.numcote ==1 || c.numcote==12  || c.numcote ==61) {
                    res1.add(c);
                }
            }else if(numcote == 5){
                if(!((joker=existe_jokers(2,c.etat)).isEmpty())){
                    ListIterator<caseM> jokerIT=joker.listIterator();
                    while(jokerIT.hasNext()){
                        caseM tmplol=jokerIT.next();
                        res1.add(tmplol);
                    }
                }
                else if(est_voisin_d_un_bord(c, 2))
                    res1.add(c);
                else if(c.numcote == 2 || c.numcote == 12 || c.numcote == 23)
                {
                    res1.add(c);
                }
            }else if(numcote == 6){
                if(!((joker=existe_jokers(6,c.etat)).isEmpty())){
                    ListIterator<caseM> jokerIT=joker.listIterator();
                    while(jokerIT.hasNext()){
                        caseM tmplol=jokerIT.next();
                        res1.add(tmplol);
                    }
                }
                else if(est_voisin_d_un_bord(c, 3))
                    res1.add(c);
                else if(c.numcote == 3 || c.numcote == 34 || c.numcote ==23)
                {
                    res1.add(c);
                }

            }


        }
       /* ListIterator<caseM> IT=res1.listIterator();
        while(IT.hasNext()){
                caseM tmp=IT.next();
                res2.add(tmp);
            }*/
        return res1;

    }

    public ArrayList<caseM> prendre_de_cote_diff(int numcote, ArrayList<caseM>L1){
        /* Retourne la liste des caseM de L1 qui sont de numcote different que
         * numcote donné en paramètre.
         */
        ArrayList<caseM> res = new ArrayList<caseM>();
        ListIterator<caseM> LE = L1.listIterator();
        while(LE.hasNext()){
            caseM c = LE.next();
            if(numcote == 1){
                if(c.numcote !=1 && c.numcote!=61 && c.numcote!=12){
                    res.add(c);
                }
            }else if(numcote == 2){
                if(c.numcote !=2 && c.numcote!=12 && c.numcote!=23){
                    res.add(c);
                }
            }else if(numcote == 3){
                if(c.numcote !=3 && c.numcote!=23 && c.numcote!=34){
                    res.add(c);
                }
            }else if(numcote == 4){
                if(c.numcote !=4 && c.numcote!=34 && c.numcote!=45){
                    res.add(c);
                }
            }else if(numcote == 5){
                if(c.numcote !=5 && c.numcote!=45 && c.numcote!=56){
                    res.add(c);
                }
            }else if(numcote == 6){
                if(c.numcote !=6 && c.numcote!=56 && c.numcote!=61){
                    res.add(c);
                }
            }
        }
        return res;
    }

    private void affichage_list(ArrayList<caseM> L){
        ListIterator<caseM> E = L.listIterator();
            System.out.println("*********LIST : ");
        while(E.hasNext()){
            caseM x = (caseM) E.next();
            System.out.println("*********("+x.i+","+x.j+")");
        }
    }

    private boolean existe_chemin(ArrayList<caseM> L1){
        /* vrai si la liste L1, qui contient les caseM des deux côtes differentes
         * contient les boules des 2 côtes differentes qui forment eux meme un chemin.
         * Precond: L1 != vide
         */
        ListIterator<caseM> E = L1.listIterator();
        int numcote = L1.get(0).numcote;
        //boolean indique s'il existe un chemin quelconque entre deux cotes opposées
        /*System.out.println("liste normale------");
        affichage_list(L1);
        System.out.println("-------------------");*/
        while(E.hasNext()){
            caseM c = E.next();
            System.out.println("c = ("+c.i+","+c.j+")");
            int n = c.numcote;
            //prendre l'element du meme coté
            if( ((numcote == 1 || numcote == 61 || numcote == 12) && (n == 1 || n == 61 || n == 12))
                    || ((numcote == 2 || numcote == 12 || numcote == 23) && (n == 2 || n == 12 || n == 23))
                    || ((numcote == 3 || numcote == 23 || numcote == 34) && (n == 3 || n == 23 || n == 34))
                    || ((numcote == 4 || numcote == 34 || numcote == 45) && (n == 4 || n == 34 || n == 45))
                    || ((numcote == 5 || numcote == 45 || numcote == 56) && (n == 5 || n == 45 || n == 56))
                    || ((numcote == 6 || numcote == 56 || numcote == 61) && (n == 6 || n == 56 || n == 61))){
                //verifier s'il existe chemin jusqu'au l'autre coté
                ArrayList<caseM> LH1 = prendre_de_cote_diff(numcote, L1);
                /*System.out.println("liste inverse------------");
                affichage_list(LH1);
                System.out.println("-------------------------");*/
                if(existe_chemin_successeurs_pont(c, LH1)){
                    return true;
                }
                E.remove(); //supp de la liste et continuer
            }
        }
        return false;
    }

    public ArrayList<caseM> existe_chemin2(ArrayList<caseM> L1){
        /* De même que existe_chemin(ArrayList<caseM> L1), mais avec la
         * valeur de retour qui est égale au chemin cherché
         */
        ListIterator<caseM> E = L1.listIterator();
        int numcote = L1.get(0).numcote;
        //boolean indique s'il existe un chemin quelconque entre deux cotes opposées
        while(E.hasNext()){
            caseM c = E.next();
            int n = c.numcote;
            //prendre l'element du meme coté
            if( ((numcote == 1 || numcote == 61 || numcote == 12) && (n == 1 || n == 61 || n == 12))
                    || ((numcote == 2 || numcote == 12 || numcote == 23) && (n == 2 || n == 12 || n == 23))
                    || ((numcote == 3 || numcote == 23 || numcote == 34) && (n == 3 || n == 23 || n == 34))
                    || ((numcote == 4 || numcote == 34 || numcote == 45) && (n == 4 || n == 34 || n == 45))
                    || ((numcote == 5 || numcote == 45 || numcote == 56) && (n == 5 || n == 45 || n == 56))
                    || ((numcote == 6 || numcote == 56 || numcote == 61) && (n == 6 || n == 56 || n == 61))){
                //verifier s'il existe chemin jusqu'au l'autre coté
                ArrayList<caseM> LH1 = prendre_de_cote_diff(numcote, L1);
                ArrayList<caseM> res = existe_chemin_successeurs2_pont(c, LH1);
                //retourner si la liste contient le chemin cherché
                if(!res.isEmpty()){
                    return res;
                }
                E.remove(); //supp de la liste et continuer
            }
        }
        //retourner la liste vide si on ne trouve aucun chemin
        return new ArrayList<caseM>();
    }

     synchronized public boolean estDefaite(int joueur) {
        int couleur;
        if(joueur == 1){
            couleur = f.joueurUn.couleur;
        }else{
            couleur = f.joueurDeux.couleur;
        }

        if(couleur == 2){ //couleur blanc

            ArrayList<caseM> cote2 = boules_sur_un_bord(couleur, 2);
            ArrayList<caseM> cote4 = boules_sur_un_bord(couleur, 4);
            ArrayList<caseM> cote6 = boules_sur_un_bord(couleur, 6);
            /*System.out.println("cote2:");
            affichage_liste(cote2);
            System.out.println("fin");
            System.out.println("cote4:");
            affichage_liste(cote4);
            System.out.println("fin");
            System.out.println("cote6:");
            affichage_liste(cote6);
            System.out.println("fin");*/
            if(!cote2.isEmpty() && !cote4.isEmpty() && !cote6.isEmpty()){
                ListIterator<caseM> E = cote2.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote4) && existe_chemin_successeurs(c, cote6)){
                        return true;

                    }
                    //E.remove(); //supp de la liste et continuer

                }

                cote2 = boules_sur_un_bord(couleur, 2);
                ListIterator<caseM> E2 = cote4.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote2) && existe_chemin_successeurs(c, cote6)){
                        return true;
                    }
                    //E2.remove(); //supp de la liste et continuer
                }

                cote4 = boules_sur_un_bord(couleur, 4);
                ListIterator<caseM> E3 = cote6.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote2) && existe_chemin_successeurs(c, cote4)){
                        return true;
                    }
                    //E3.remove(); //supp de la liste et continuer
                }
            }

            ArrayList<caseM> cote1 = boules_sur_un_bord(couleur, 1);
            ArrayList<caseM> cote3 = boules_sur_un_bord(couleur, 3);
            ArrayList<caseM> cote5 = boules_sur_un_bord(couleur, 5);
            if(!cote1.isEmpty() && !cote3.isEmpty() && !cote5.isEmpty()){
                ListIterator<caseM> E = cote1.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote3) && existe_chemin_successeurs(c, cote5)){
                        return true;
                    }
                    //E.remove(); //supp de la liste et continuer
                }

                cote1 = boules_sur_un_bord(couleur, 1);
                ListIterator<caseM> E2 = cote3.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote1) && existe_chemin_successeurs(c, cote5)){
                        return true;
                    }
                    //E2.remove(); //supp de la liste et continuer
                }

                cote3 = boules_sur_un_bord(couleur, 3);
                ListIterator<caseM> E3 = cote5.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote1) && existe_chemin_successeurs(c, cote3)){
                        return true;
                    }
                    //E3.remove(); //supp de la liste et continuer
                }
            }
            return false;

        }else if(couleur == 1){ //couleur noire

            //tester en haut et en bas:
            ArrayList<caseM> L1 = boules_sur_deux_bord(couleur, 1, 4);
            //tester au bords droit en haut et gauche en bas
            ArrayList<caseM> L2 = boules_sur_deux_bord(couleur, 2, 5);
            //tester au bords droit en bas et gauche en haut
            ArrayList<caseM> L3 = boules_sur_deux_bord(couleur, 3, 6);

            if(!L1.isEmpty()){
                //il existe bien au minimum 2 boules en haut et en bas
                ListIterator<caseM> E = L1.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté
                    if(c.numcote == 1 || c.numcote == 61 || c.numcote == 12){
                        //verifier s'il existe chemin jusqu'au l'autre coté
                        ArrayList<caseM> LH1 = prendre_de_cote_diff(1, L1);
                        if(existe_chemin_successeurs(c, LH1)){
                            return true;
                        }
                        //E.remove(); //supp de la liste et continuer
                    }
                }
            }

            if(!L2.isEmpty()){
                /* il existe bien au minimum 2 boules en haut à droite
                 * et en bas à gauche
                 */
                ListIterator<caseM> E = L2.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté
                    if(c.numcote == 2 || c.numcote == 12 || c.numcote == 23){
                        //verifier s'il existe chemin jusqu'au l'autre coté
                        ArrayList<caseM> LH2 = prendre_de_cote_diff(2, L2);
                        if(existe_chemin_successeurs(c, LH2)){
                            return true;
                        }
                        //E.remove(); //supp de la liste et continuer
                    }
                }
            }

            if(!L3.isEmpty()){
                /* il existe bien au minimum 2 boules en bas à droite
                 * et en haut à gauche.
                 */
                ListIterator<caseM> E = L3.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté
                    if(c.numcote == 3 || c.numcote == 23 || c.numcote == 34){
                        //verifier s'il existe chemin jusqu'au l'autre coté
                        ArrayList<caseM> LH3 = prendre_de_cote_diff(3, L3);
                        if(existe_chemin_successeurs(c, LH3)){
                            return true;
                        }
                        //E.remove(); //supp de la liste et continuer
                    }
                }
            }

            return false;
        }
        System.out.println("estDefaite(couleur differente de noir et blanc");
        return false;
    }

    public boolean estVictoire(int joueur) {
        int couleur;
        if(joueur == 1){
            couleur = f.joueurUn.couleur;
        }else{
            couleur = f.joueurDeux.couleur;
        }

        if(couleur == 1){ //couleur noire

            ArrayList<caseM> cote2 = boules_sur_un_bord(1, 2);
            ArrayList<caseM> cote4 = boules_sur_un_bord(1, 4);
            ArrayList<caseM> cote6 = boules_sur_un_bord(1, 6);
            /*System.out.println("cote2:");
            affichage_liste(cote2);
            System.out.println("fin");
            System.out.println("cote4:");
            affichage_liste(cote4);
            System.out.println("fin");
            System.out.println("cote6:");
            affichage_liste(cote6);
            System.out.println("fin");*/
            if(!cote2.isEmpty() && !cote4.isEmpty() && !cote6.isEmpty()){
                ListIterator<caseM> E = cote2.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote4) && existe_chemin_successeurs(c, cote6)){
                        return true;

                    }
                    //E.remove(); //supp de la liste et continuer

                }

                //cote2 = boules_sur_un_bord(1, 2);
                ListIterator<caseM> E2 = cote4.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote2) && existe_chemin_successeurs(c, cote6)){
                        return true;
                    }
                    //E2.remove(); //supp de la liste et continuer
                }

                //cote4 = boules_sur_un_bord(1, 4);
                ListIterator<caseM> E3 = cote6.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote2) && existe_chemin_successeurs(c, cote4)){
                        return true;
                    }
                    //E3.remove(); //supp de la liste et continuer
                }
            }

            ArrayList<caseM> cote1 = boules_sur_un_bord(1, 1);
            ArrayList<caseM> cote3 = boules_sur_un_bord(1, 3);
            ArrayList<caseM> cote5 = boules_sur_un_bord(1, 5);
            if(!cote1.isEmpty() && !cote3.isEmpty() && !cote5.isEmpty()){
                ListIterator<caseM> E = cote1.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote3) && existe_chemin_successeurs(c, cote5)){
                        return true;
                    }
                    //E.remove(); //supp de la liste et continuer
                }

                //cote1 = boules_sur_un_bord(1, 1);
                ListIterator<caseM> E2 = cote3.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote1) && existe_chemin_successeurs(c, cote5)){
                        return true;
                    }
                    //E2.remove(); //supp de la liste et continuer
                }

                //cote3 = boules_sur_un_bord(1, 3);
                ListIterator<caseM> E3 = cote5.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs(c, cote1) && existe_chemin_successeurs(c, cote3)){
                        return true;
                    }
                    //E3.remove(); //supp de la liste et continuer
                }
            }
            return false;

        }else if(couleur == 2){ //couleur blanche

            //tester en haut et en bas:
            ArrayList<caseM> L1 = boules_sur_deux_bord(2, 1, 4);
            //tester au bords droit en haut et gauche en bas
            ArrayList<caseM> L2 = boules_sur_deux_bord(2, 2, 5);
            //tester au bords droit en bas et gauche en haut
            ArrayList<caseM> L3 = boules_sur_deux_bord(2, 3, 6);

            if(!L1.isEmpty()) {
                //il existe bien au minimum 2 boules en haut et en bas
                ListIterator<caseM> E = L1.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté
                    if(c.numcote == 1 || c.numcote == 61 || c.numcote == 12){
                        //verifier s'il existe chemin jusqu'au l'autre coté
                        ArrayList<caseM> LH1 = prendre_de_cote_diff(1, L1);
                        if(existe_chemin_successeurs(c, LH1)){
                            return true;
                        }
                        //E.remove(); //supp de la liste et continuer
                    }
                }
            }

            if(!L2.isEmpty()) {
                /* il existe bien au minimum 2 boules en haut à droite
                 * et en bas à gauche
                 */
                ListIterator<caseM> E = L2.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté
                    if(c.numcote == 2 || c.numcote == 12 || c.numcote == 23){
                        //verifier s'il existe chemin jusqu'au l'autre coté
                        ArrayList<caseM> LH2 = prendre_de_cote_diff(2, L2);
                        if(existe_chemin_successeurs(c, LH2)){
                            return true;
                        }
                        //E.remove(); //supp de la liste et continuer
                    }
                }
                return false;
            }

            if(!L3.isEmpty()){
                /* il existe bien au minimum 2 boules en bas à droite
                 * et en haut à gauche.
                 */
                ListIterator<caseM> E = L3.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté
                    if(c.numcote == 3 || c.numcote == 23 || c.numcote == 34){
                        //verifier s'il existe chemin jusqu'au l'autre coté
                        ArrayList<caseM> LH3 = prendre_de_cote_diff(3, L3);
                        if(existe_chemin_successeurs(c, LH3)){
                            return true;
                        }
                        //E.remove(); //supp de la liste et continuer
                    }
                }
            }

            return false;

        }

        System.out.println("estVictoire(couleur differente de noir et blanc");
        return false;
    }

   synchronized public ArrayList<caseM> estVictoire_retourne_chemin(int joueur) {
        int couleur;
        ArrayList<caseM> vict = new ArrayList<caseM>();
        ArrayList<caseM> L1, L2, L3;
        if(joueur == 1){
            couleur = f.joueurUn.couleur;
        }else{
            couleur = f.joueurDeux.couleur;
        }

        if(couleur == 1){ //couleur noire

            ArrayList<caseM> cote2 = boules_sur_un_bord(1, 2);
            ArrayList<caseM> cote4 = boules_sur_un_bord(1, 4);
            ArrayList<caseM> cote6 = boules_sur_un_bord(1, 6);
            /*System.out.println("cote2:");
            affichage_liste(cote2);
            System.out.println("fin");
            System.out.println("cote4:");
            affichage_liste(cote4);
            System.out.println("fin");
            System.out.println("cote6:");
            affichage_liste(cote6);
            System.out.println("fin");*/
            if(!cote2.isEmpty() && !cote4.isEmpty() && !cote6.isEmpty()){
                ListIterator<caseM> E = cote2.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté
                    L1 = existe_chemin_successeurs2(c, cote4);
                    L2 = existe_chemin_successeurs2(c, cote6);
                    if(!L1.isEmpty() && !L2.isEmpty()){
                        vict = union_array(L1, L2);
                        return vict;
                    }
                    //E.remove(); //supp de la liste et continuer

                }

                //cote2 = boules_sur_un_bord(1, 2);
                ListIterator<caseM> E2 = cote4.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    L1 = existe_chemin_successeurs2(c, cote2);
                    L2 = existe_chemin_successeurs2(c, cote6);
                    if(!L1.isEmpty() && !L2.isEmpty()){
                        vict = union_array(L1, L2);
                        return vict;
                    }
                    //E2.remove(); //supp de la liste et continuer
                }

                //cote4 = boules_sur_un_bord(1, 4);
                ListIterator<caseM> E3 = cote6.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    L1 = existe_chemin_successeurs2(c, cote2);
                    L2 = existe_chemin_successeurs2(c, cote4);
                    if(!L1.isEmpty() && !L2.isEmpty()){
                        vict = union_array(L1, L2);
                        return vict;
                    }
                    //E3.remove(); //supp de la liste et continuer
                }
            }

            ArrayList<caseM> cote1 = boules_sur_un_bord(1, 1);
            ArrayList<caseM> cote3 = boules_sur_un_bord(1, 3);
            ArrayList<caseM> cote5 = boules_sur_un_bord(1, 5);
            if(!cote1.isEmpty() && !cote3.isEmpty() && !cote5.isEmpty()){
                ListIterator<caseM> E = cote1.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté


                    L1 = existe_chemin_successeurs2(c, cote3);
                    L2 = existe_chemin_successeurs2(c, cote5);
                    if(!L1.isEmpty() && !L2.isEmpty()){
                        vict = union_array(L1, L2);
                        return vict;
                    }
                    //E.remove(); //supp de la liste et continuer
                }

                //cote1 = boules_sur_un_bord(1, 1);
                ListIterator<caseM> E2 = cote3.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    L1 = existe_chemin_successeurs2(c, cote1);
                    L2 = existe_chemin_successeurs2(c, cote5);
                    if(!L1.isEmpty() && !L2.isEmpty()){
                        vict = union_array(L1, L2);
                        return vict;
                    }
                    //E2.remove(); //supp de la liste et continuer
                }

                //cote3 = boules_sur_un_bord(1, 3);
                ListIterator<caseM> E3 = cote5.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    L1 = existe_chemin_successeurs2(c, cote1);
                    L2 = existe_chemin_successeurs2(c, cote3);
                    if(!L1.isEmpty() && !L2.isEmpty()){
                        vict = union_array(L1, L2);
                        return vict;
                    }
                    //E3.remove(); //supp de la liste et continuer
                }
            }
            return vict;

        }else if(couleur == 2){ //couleur blanche

            ArrayList<caseM> cote4 = boules_sur_un_bord(2, 4);
            ArrayList<caseM> cote1 = boules_sur_un_bord(2, 1);
            
            ListIterator<caseM> E1 = cote1.listIterator();
            while(E1.hasNext()){
                caseM c = E1.next(); //prendre l'element du meme coté

                L1 = existe_chemin_successeurs2(c, cote4);
                if(!L1.isEmpty()){
                    return L1;
                }
                //E2.remove(); //supp de la liste et continuer
            }
            ArrayList<caseM> cote2 = boules_sur_un_bord(2, 2);
            ArrayList<caseM> cote5 = boules_sur_un_bord(2, 5);
            E1 = cote2.listIterator();
            while(E1.hasNext()){
                caseM c = E1.next(); //prendre l'element du meme coté

                L1 = existe_chemin_successeurs2(c, cote5);
                if(!L1.isEmpty()){
                    return L1;
                }
                //E2.remove(); //supp de la liste et continuer
            }
            ArrayList<caseM> cote3 = boules_sur_un_bord(2, 3);
            ArrayList<caseM> cote6 = boules_sur_un_bord(2, 6);
            E1 = cote3.listIterator();
            while(E1.hasNext()){
                caseM c = E1.next(); //prendre l'element du meme coté

                L1 = existe_chemin_successeurs2(c, cote6);
                if(!L1.isEmpty()){
                    return L1;
                }
                //E2.remove(); //supp de la liste et continuer
            }
            return vict;
        }
        return vict;

    }

   synchronized private ArrayList<caseM> union_array(ArrayList<caseM> L1,ArrayList<caseM> L2){

        ListIterator<caseM> L2IT=L2.listIterator();
        while(L2IT.hasNext()){
            caseM tmp=L2IT.next();
            if(!L1.contains(tmp))
                L1.add(tmp);

        }
        return L1;
    }

   synchronized public boolean estVictoire_ponts(int joueur) {
        int couleur;
        if(joueur == 1){
            couleur = f.joueurUn.couleur;
        }else{
            couleur = f.joueurDeux.couleur;
        }

        if(couleur == 1){ //couleur noire

            ArrayList<caseM> cote2 = boules_a_cote_bord(1, 2);
            ArrayList<caseM> cote4 = boules_a_cote_bord(1, 4);
            ArrayList<caseM> cote6 = boules_a_cote_bord(1, 6);

                cote2=union_array(cote2, boules_sur_un_bord(1, 2)) ;
                cote4=union_array(cote4, boules_sur_un_bord(1, 4)) ;
                cote6=union_array(cote6, boules_sur_un_bord(1, 6)) ;


           /* System.out.println("cote2:");
            affichage_list(cote2);
            System.out.println("cote4:");
            affichage_list(cote4);
            System.out.println("cote6:");
            affichage_list(cote6);*/
            if(!cote2.isEmpty() && !cote4.isEmpty() && !cote6.isEmpty()){
                ListIterator<caseM> E = cote2.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté


                    if(existe_chemin_successeurs_pont(c, cote4) && existe_chemin_successeurs_pont(c, cote6)){
                        return true;

                    }
                    //E.remove(); //supp de la liste et continuer
                }
                    System.out.print("cote 2->4 et 2->6 vide");

                ListIterator<caseM> E2 = cote4.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs_pont(c, cote2) && existe_chemin_successeurs_pont(c, cote6)){
                        return true;
                    }
                    //E2.remove(); //supp de la liste et continuer
                }
                    System.out.print("cote 4->2 et 4->6 vide");

                ListIterator<caseM> E3 = cote6.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs_pont(c, cote2) && existe_chemin_successeurs_pont(c, cote4)){
                        return true;
                    }
                    //E3.remove(); //supp de la liste et continuer
                }
                    System.out.print("cote 6->2 et 6->4 vide");
            }

            ArrayList<caseM> cote1 = boules_a_cote_bord(1, 1);
            ArrayList<caseM> cote3 = boules_a_cote_bord(1, 3);
            ArrayList<caseM> cote5 = boules_a_cote_bord(1, 5);
                cote1=union_array(cote1, boules_sur_un_bord(1, 1)) ;
                cote3=union_array(cote3, boules_sur_un_bord(1, 3)) ;
                cote5=union_array(cote5, boules_sur_un_bord(1, 5)) ;
            if(!cote1.isEmpty() && !cote3.isEmpty() && !cote5.isEmpty()){

           /* System.out.println("cote1:");
            affichage_list(cote1);
            System.out.println("cote3:");
            affichage_list(cote3);
            System.out.println("cote5:");
            affichage_list(cote5);*/
                ListIterator<caseM> E = cote1.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs_pont(c, cote3) && existe_chemin_successeurs_pont(c, cote5)){
                        return true;
                    }
                    //E.remove(); //supp de la liste et continuer
                }
                    System.out.print("cote 1->3 et 1->5 vide");

                ListIterator<caseM> E2 = cote3.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs_pont(c, cote1) && existe_chemin_successeurs_pont(c, cote5)){
                        return true;
                    }
                   // E2.remove(); //supp de la liste et continuer
                }
                    System.out.print("cote 3->1 et 3->5 vide");

                ListIterator<caseM> E3 = cote5.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs_pont(c, cote1) && existe_chemin_successeurs_pont(c, cote3)){
                        return true;
                    }
                   // E3.remove(); //supp de la liste et continuer
                }
                    System.out.print("cote 5->1 et 5->3 vide");
            }
            return false;

        }else if(couleur == 2){ //couleur blanche
            ArrayList<caseM> cote1 = boules_a_cote_bord(2, 1);
            ArrayList<caseM> cote2 = boules_a_cote_bord(2, 2);
            ArrayList<caseM> cote3 = boules_a_cote_bord(2, 3);
            ArrayList<caseM> cote4 = boules_a_cote_bord(2, 4);
            ArrayList<caseM> cote5 = boules_a_cote_bord(2, 5);
            ArrayList<caseM> cote6 = boules_a_cote_bord(2, 6);
                cote1=union_array(cote1, boules_sur_un_bord(2, 1)) ;
                cote2=union_array(cote2, boules_sur_un_bord(2, 2)) ;
                cote3=union_array(cote3, boules_sur_un_bord(2, 3)) ;
                cote4=union_array(cote4, boules_sur_un_bord(2, 4)) ;
                cote5=union_array(cote5, boules_sur_un_bord(2, 5)) ;
                cote6=union_array(cote6, boules_sur_un_bord(2, 6)) ;

            if(!cote1.isEmpty() && !cote4.isEmpty()){
                ListIterator<caseM> E = cote1.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs_pont(c, cote4)){
                        return true;
                    }
                    //E.remove(); //supp de la liste et continuer
                }

            }
            if(!cote2.isEmpty() && !cote5.isEmpty()){
                ListIterator<caseM> E = cote2.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs_pont(c, cote5)){
                        return true;
                    }
                    //E.remove(); //supp de la liste et continuer
                }

            }
            if(!cote3.isEmpty() && !cote6.isEmpty()){
                ListIterator<caseM> E = cote3.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(existe_chemin_successeurs_pont(c, cote6)){
                        return true;
                    }
                    //E.remove(); //supp de la liste et continuer
                }
            }

            return false;
        }
        return false;
    }

   synchronized public boolean estVictoire_composante_ponts(ArrayList<caseM> L, int couleur){
       ListIterator<caseM> E = L.listIterator();
       int[]tab = new int[7];
       for(int j=0;j<7;j++){
           tab[j]=0;
       }
       tab[0]=-999;
       ArrayList<caseM> elements_bord1 = elements_bord_couleur2(1, couleur);
       ArrayList<caseM> elements_bord2 = elements_bord_couleur2(2, couleur);
       ArrayList<caseM> elements_bord3 = elements_bord_couleur2(3, couleur);
       ArrayList<caseM> elements_bord4 = elements_bord_couleur2(4, couleur);
       ArrayList<caseM> elements_bord5 = elements_bord_couleur2(5, couleur);
       ArrayList<caseM> elements_bord6 = elements_bord_couleur2(6, couleur);
      /* System.out.println("elements_bord1:");
       affichage_list(elements_bord1);
       System.out.println("elements_bord2:");
       affichage_list(elements_bord2);
       System.out.println("elements_bord3:");
       affichage_list(elements_bord3);
       System.out.println("elements_bord4:");
       affichage_list(elements_bord4);
       System.out.println("elements_bord5:");
       affichage_list(elements_bord5);
       System.out.println("elements_bord6:");
       affichage_list(elements_bord6);*/

       while(E.hasNext()){
           caseM c = E.next();
               if(elements_bord1.contains(c)){
                   tab[1]=1;
               }
               if(elements_bord2.contains(c)){
                   tab[2]=1;
               }
               if(elements_bord3.contains(c)){
                   tab[3]=1;
               }
               if(elements_bord4.contains(c)){
                   tab[4]=1;
               }
               if(elements_bord5.contains(c)){
                   tab[5]=1;
               }
               if(elements_bord6.contains(c)){
                   tab[6]=1;
               }
       }
       if(couleur == 1)
       {
           if((tab[1]==1 && tab[3]==1 && tab[5]==1) || (tab[2]==1 && tab[4]==1 && tab[6]==1))
           {
               return true;
           }
       }
       else if(couleur == 2)
       {
           if((tab[1]==1 && tab[4]==1) || (tab[2]==1 && tab[5]==1) || (tab[3]==1 && tab[6]==1))
           {
               return true;
           }
       }
       return false;
   }

   synchronized public ArrayList<caseM> estVictoire_retourne_chemin_ponts(int joueur) {
        ArrayList<caseM> list1,list2;
        Joker1 tmpz1;
        Joker2 tmpz2;
        int couleur;
        if(joueur == 1){
            couleur = f.joueurUn.couleur;
        }else{
            couleur = f.joueurDeux.couleur;
        }

        if(couleur == 1){ //couleur noire

            ArrayList<caseM> cote2 = boules_a_cote_bord(1, 2);
            ArrayList<caseM> cote4 = boules_a_cote_bord(1, 4);
            ArrayList<caseM> cote6 = boules_a_cote_bord(1, 6);

            if(cote2.isEmpty())
                cote2=boules_sur_un_bord(1, 2);
            if(cote4.isEmpty())
                cote4=boules_sur_un_bord(1, 4);
            if(cote6.isEmpty())
                cote6=boules_sur_un_bord(1, 6);

            if(!cote2.isEmpty() && !cote4.isEmpty() && !cote6.isEmpty()){
                ListIterator<caseM> E = cote2.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if((list1=existe_chemin_successeurs2_pont(c, cote4))!=null && (list2=existe_chemin_successeurs2_pont(c, cote6))!=null){
                        ListIterator<caseM> LI1;
                        LI1=list1.listIterator();
                        while(LI1.hasNext()){
                            caseM nextnoir=LI1.next();
                            if(!list2.contains(nextnoir))
                                list2.add(nextnoir);
                        }
                        return list2;
                    }
                    //E.remove(); //supp de la liste et continuer

                }

                //cote2 = boules_a_cote_bord(1, 2);
                //if(cote2.isEmpty())
               //     cote2=boules_sur_un_bord(1, 2);
                ListIterator<caseM> E2 = cote4.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    if(!(list1=existe_chemin_successeurs2_pont(c, cote2)).isEmpty() && !(list2=existe_chemin_successeurs2_pont(c, cote6)).isEmpty()){
                        ListIterator<caseM> LI1;
                        LI1=list1.listIterator();
                        while(LI1.hasNext()){
                            caseM nextnoir=LI1.next();
                            if(!list2.contains(nextnoir))
                                list2.add(nextnoir);
                        }
                        return list2;
                    }
                    //E2.remove(); //supp de la liste et continuer
                }

               // cote4 = boules_a_cote_bord(1, 4);
               // if(cote4.isEmpty())
               //     cote4=boules_sur_un_bord(1, 4);

                ListIterator<caseM> E3 = cote6.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    if(!(list1=existe_chemin_successeurs2_pont(c, cote2)).isEmpty() && !(list2=existe_chemin_successeurs2_pont(c, cote4)).isEmpty()){
                         ListIterator<caseM> LI1;
                        LI1=list1.listIterator();
                        while(LI1.hasNext()){
                            caseM nextnoir=LI1.next();
                            if(!list2.contains(nextnoir))
                                list2.add(nextnoir);
                        }
                        return list2;
                    }
                   // E3.remove(); //supp de la liste et continuer
                }
            }

            ArrayList<caseM> cote1 = boules_a_cote_bord(1, 1);
            ArrayList<caseM> cote3 = boules_a_cote_bord(1, 3);
            ArrayList<caseM> cote5 = boules_a_cote_bord(1, 5);
            if(cote1.isEmpty())
                cote1=boules_sur_un_bord(1, 1);
            if(cote3.isEmpty())
                cote3=boules_sur_un_bord(1, 3);
            if(cote5.isEmpty())
                cote5=boules_sur_un_bord(1, 5);

            if(!cote1.isEmpty() && !cote3.isEmpty() && !cote5.isEmpty()){
                ListIterator<caseM> E = cote1.listIterator();
                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté

                    if(!(list1=existe_chemin_successeurs2_pont(c, cote3)).isEmpty() && !(list2=existe_chemin_successeurs2_pont(c, cote5)).isEmpty()){
                        ListIterator<caseM> LI1;
                        LI1=list1.listIterator();
                        while(LI1.hasNext()){
                            caseM nextnoir=LI1.next();
                            if(!list2.contains(nextnoir))
                                list2.add(nextnoir);
                        }
                        return list2;
                    }
                   // E.remove(); //supp de la liste et continuer
                }

               // cote1 = boules_a_cote_bord(1, 1);
               //  if(cote1.isEmpty())
               //      cote1=boules_sur_un_bord(1, 1);


                ListIterator<caseM> E2 = cote3.listIterator();
                while(E2.hasNext()){
                    caseM c = E2.next(); //prendre l'element du meme coté

                    if(!(list1=existe_chemin_successeurs2_pont(c, cote1)).isEmpty() && !(list2=existe_chemin_successeurs2_pont(c, cote5)).isEmpty()){
                        ListIterator<caseM> LI1;
                        LI1=list1.listIterator();
                        while(LI1.hasNext()){
                            caseM nextnoir=LI1.next();
                            if(!list2.contains(nextnoir))
                                list2.add(nextnoir);
                        }
                        return list2;
                    }
                  //  E2.remove(); //supp de la liste et continuer
                }

               // cote3 = boules_a_cote_bord(1, 3);
               // if(cote3.isEmpty())
               //      cote3=boules_sur_un_bord(1, 3);

                ListIterator<caseM> E3 = cote5.listIterator();
                while(E3.hasNext()){
                    caseM c = E3.next(); //prendre l'element du meme coté

                    if(!(list1=existe_chemin_successeurs2_pont(c, cote1)).isEmpty() && !(list2=existe_chemin_successeurs2_pont(c, cote3)).isEmpty()){
                        ListIterator<caseM> LI1;
                        LI1=list1.listIterator();
                        while(LI1.hasNext()){
                            caseM nextnoir=LI1.next();
                            if(!list2.contains(nextnoir))
                                list2.add(nextnoir);
                        }
                        return list2;
                    }
                  //  E3.remove(); //supp de la liste et continuer
                }
            }
            return null;

        }else if(couleur == 2){ //couleur blanche

            //tester en haut et en bas:
            ArrayList<caseM> L1 = boule_voisins_bord_ou_bord_deux(2, 1, 4);
            ArrayList<caseM> L2 = boule_voisins_bord_ou_bord_deux(2, 2, 5);
            ArrayList<caseM> L3 = boule_voisins_bord_ou_bord_deux(2, 3, 6);

            if(!L3.isEmpty()) {


                      /*  System.out.println("Boule voisins bord ou bord des deux cotes CAS 3  6 :");
                        for(int m=0;m<L3.size();m++){
                            caseM tempz=L3.get(m);
                            System.out.println(" I: "+tempz.i+" J :"+tempz.j);
                        }
*/

                        /* il existe bien au minimum 2 boules en bas à droite
                         * et en haut à gauche.
                         */
                        ListIterator<caseM> E = L3.listIterator();
                        ArrayList<caseM> LH3 = prendre_de_cote_diff_ponts_bords(3, L3);
                        while(E.hasNext()){
                            caseM c = E.next(); //prendre l'element du meme coté
                           if(est_voisin_d_un_bord(c, 3)  || est_joker1_selon_bord(c,3) || est_joker2_selon_bord(c,3)){


                                     //verifier s'il existe chemin jusqu'au l'autre coté

                                     if(!(list1=existe_chemin_successeurs2_pont(c, LH3)).isEmpty()){
                                           return list1;
                                      }
                                      E.remove(); //supp de la liste et continuer

                            }
                            else if(estBord(c)){
                            if(c.numcote == 3 || c.numcote == 23 || c.numcote == 34){
                                 //verifier s'il existe chemin jusqu'a l'autre coté

                                    if(!(list1=existe_chemin_successeurs2_pont(c, LH3)).isEmpty()){
                                      return list1;
                                     }
                                     E.remove(); //supp de la liste et continuer
                             }

                        }

                        }

            }

            if(!L2.isEmpty()) {


                  /*   System.out.println("Boule voisins bord ou bord des deux cotes CAS 2 5 :");
                        for(int m=0;m<L2.size();m++){
                            caseM tempz=L2.get(m);
                            System.out.println(" I: "+tempz.i+" J :"+tempz.j);
                        }
*/
                    /* il existe bien au minimum 2 boules en haut à droite
                     * et en bas à gauche
                     */
                    ListIterator<caseM> E = L2.listIterator();
                    ArrayList<caseM> LH2 = prendre_de_cote_diff_ponts_bords(2, L2);
                    while(E.hasNext()){
                        caseM c = E.next(); //prendre l'element du meme coté
                        if(est_voisin_d_un_bord(c, 2) || est_joker1_selon_bord(c,2) || est_joker2_selon_bord(c,2)){

                                 //verifier s'il existe chemin jusqu'a l'autre coté

                                    if(!(list1=existe_chemin_successeurs2_pont(c, LH2)).isEmpty()){
                                      return list1;
                                     }
                                     E.remove(); //supp de la liste et continuer

                        }
                        else if(estBord(c)){
                            if(c.numcote == 2 || c.numcote == 12 || c.numcote == 23){
                                 //verifier s'il existe chemin jusqu'a l'autre coté

                                    if(!(list1=existe_chemin_successeurs2_pont(c, LH2)).isEmpty()){
                                      return list1;
                                     }
                                     E.remove(); //supp de la liste et continuer
                             }

                        }
                     }

            }

            if(!L1.isEmpty()) {


               /*  System.out.println("Boule voisins bord ou bord des deux cotes  CAS 1 :");
                        for(int m=0;m<L1.size();m++){
                            caseM tempz=L1.get(m);
                            System.out.println(" I: "+tempz.i+" J :"+tempz.j);
                        }
*/
                //il existe bien au minimum 2 boules en haut et en bas
                ListIterator<caseM> E = L1.listIterator();
                 ArrayList<caseM> LH1 = prendre_de_cote_diff_ponts_bords(1, L1);
                              //----------------------
                               /*   System.out.println("Boules cote opposé::");
                                  for(int m=0;m<LH1.size();m++){
                                      caseM tempz=LH1.get(m);
                                      System.out.println(" I opposé : "+tempz.i+" J opposé  :"+tempz.j);
                                   }
*/
                                  //-----------------------

                while(E.hasNext()){
                    caseM c = E.next(); //prendre l'element du meme coté
                      if(est_voisin_d_un_bord(c, 1) || est_joker1_selon_bord(c,1)  || est_joker2_selon_bord(c,2) ) {

                             //verifier s'il existe chemin jusqu'au l'autre coté

                             if(!(list1=existe_chemin_successeurs2_pont(c, LH1)).isEmpty()){
                                   return list1;
                              }
                               E.remove(); //supp de la liste et continuer

                      }
                      else if(estBord(c)){

                            if(c.numcote == 1 || c.numcote == 61 || c.numcote == 12){
                                 //verifier s'il existe chemin jusqu'a l'autre coté


                                    if(!(list1=existe_chemin_successeurs2_pont(c, LH1)).isEmpty()){
                                      return list1;
                                     }
                                     E.remove(); //supp de la liste et continuer
                             }

                        }
                }

            }
        }

        System.out.println("estVictoire(couleur differente de noir et blanc");
        return new ArrayList<caseM> ();
    }

   synchronized private caseM[] cases_milieu_pont(caseM depart,caseM arrivee){
        caseM [] res=new caseM[2];
        res[0]=null;
        res[1]=null;
        if(depart!=null && arrivee!=null){
            if(depart.i== (arrivee.i-2) && depart.j==(arrivee.j-1)){
               if(depart.i<f.N && f.matrice[depart.i+1][depart.j]!=null && f.matrice[depart.i+1][depart.j].etat!=-1)
                res[0]=f.matrice[depart.i+1][depart.j];
               if(depart.i<f.N && f.matrice[depart.i+1][depart.j+1]!=null && f.matrice[depart.i+1][depart.j+1].etat!=-1)
                res[1]=f.matrice[depart.i+1][depart.j+1];
            }
            else if(depart.i== (arrivee.i-1) && depart.j==(arrivee.j-2)){
               if(depart.j<f.N && f.matrice[depart.i][depart.j+1]!=null && f.matrice[depart.i][depart.j+1].etat!=-1)
                res[0]=f.matrice[depart.i][depart.j+1];
               if(depart.i<f.N && depart.j<f.N && f.matrice[depart.i+1][depart.j+1]!=null && f.matrice[depart.i+1][depart.j+1].etat!=-1)
                res[1]=f.matrice[depart.i+1][depart.j+1];
            }
            else if(depart.i== (arrivee.i+1) && depart.j==(arrivee.j-1)){
              if(depart.i>=0 && f.matrice[depart.i-1][depart.j]!=null && f.matrice[depart.i-1][depart.j].etat!=-1)
                res[0]=f.matrice[depart.i-1][depart.j];
              if(depart.j<f.N && f.matrice[depart.i][depart.j+1]!=null && f.matrice[depart.i][depart.j+1].etat!=-1)
                res[1]=f.matrice[depart.i][depart.j+1];
            }
            else if(depart.i== (arrivee.i+2) && depart.j==(arrivee.j+1)){
               if(depart.i>=0 && depart.j>=0 && f.matrice[depart.i-1][depart.j-1]!=null && f.matrice[depart.i-1][depart.j-1].etat!=-1)
                res[0]=f.matrice[depart.i-1][depart.j-1];
               if(depart.i>=0 && f.matrice[depart.i-1][depart.j]!=null && f.matrice[depart.i-1][depart.j].etat!=-1)
                res[1]=f.matrice[depart.i-1][depart.j];
            }
            else if(depart.i== (arrivee.i+1) && depart.j==(arrivee.j+2)){
               if(depart.j>=0 && f.matrice[depart.i][depart.j-1]!=null && f.matrice[depart.i][depart.j-1].etat!=-1)
                res[0]=f.matrice[depart.i][depart.j-1];
               if(depart.i>=0 && depart.j>=0 && f.matrice[depart.i-1][depart.j-1]!=null && f.matrice[depart.i-1][depart.j-1].etat!=-1)
                res[1]=f.matrice[depart.i-1][depart.j-1];
            }
            else if(depart.i== (arrivee.i-1) && depart.j==(arrivee.j+1)){
               if(depart.j>=0 && f.matrice[depart.i][depart.j-1]!=null && f.matrice[depart.i][depart.j-1].etat!=-1)
                res[0]=f.matrice[depart.i][depart.j-1];
               if(depart.i<f.N && f.matrice[depart.i+1][depart.j]!=null && f.matrice[depart.i+1][depart.j].etat!=-1)
                res[1]=f.matrice[depart.i+1][depart.j];
            }
       }
        return res;
    }

   synchronized private caseM completer_victoire(ArrayList<caseM> L,int joueur){
        Random r=new Random();

        ArrayList<caseM> res=new ArrayList<caseM>();
        ListIterator<caseM> IT=L.listIterator();
        while(IT.hasNext()){
            caseM tmp=IT.next();
            ArrayList<caseM> ponts;
            if(joueur==1){

                    ponts=pont_caseM_couleur(tmp);
                    ListIterator<caseM> ITponts=ponts.listIterator();
                    while(ITponts.hasNext()){
                        caseM tmppont=ITponts.next();

                       if(L.contains(tmppont)){
                            int i=r.nextInt(2);
                            caseM [] tabrnd=cases_milieu_pont(tmp, tmppont);
                            if(tabrnd[0].etat==0 && tabrnd[1].etat==0)
                                return tabrnd[i];
                       }

                    }


                if((est_joker1_selon_bord(tmp, objectif_bordJ1[0]) && !touche_le_bord(couleur_joueur_courant(),objectif_bordJ1[0])) ||
                            (est_joker1_selon_bord(tmp, objectif_bordJ1[1]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ1[1])) ||
                            (objectif_bordJ1[2]!=0 && est_joker1_selon_bord(tmp, objectif_bordJ1[2]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ1[2]))){
                    Joker1 temp;
                    temp=retourne_joker1_depuis_case(tmp);
                    int i=r.nextInt(3);
                    caseM [] tabrnd=new caseM[3];
                    tabrnd[0]=temp.pont1;
                    tabrnd[1]=temp.pont2;
                    tabrnd[2]=temp.pont3;
                   if((tabrnd[0].etat==0 || tabrnd[0].etat==couleur_joueur_adverse() ) && (tabrnd[1].etat==0 || tabrnd[1].etat==couleur_joueur_adverse() ) && (tabrnd[2].etat==0 || tabrnd[2].etat==couleur_joueur_adverse())){
                    if(est_pont_cassé(temp.joker, tabrnd[i])){
                       if(i!=2)
                         res.add(tabrnd[i+1]);
                       else
                           res.add(tabrnd[0]);
                    }
                    else
                        res.add(tabrnd[i]);
                }
                }
                else if((est_joker2_selon_bord(tmp, objectif_bordJ1[0]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ1[0])) ||
                            (est_joker2_selon_bord(tmp, objectif_bordJ1[1])&& !touche_le_bord(couleur_joueur_courant(), objectif_bordJ1[1])) ||
                            (objectif_bordJ1[2]!=0 && est_joker2_selon_bord(tmp, objectif_bordJ1[2]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ1[2])) ){
                    Joker2 temp;
                    temp=retourne_joker2_depuis_case(tmp);


                    if(est_voisin_d_un_bord(temp.pont1, objectif_bordJ1[0]) && temp.pont1.etat==0){
                        res.add(temp.pont1);
                        if(temp.pont2.etat==0)
                            res.add(temp.pont2);
                    }else if(est_voisin_d_un_bord(temp.pont4, objectif_bordJ1[0]) && temp.pont4.etat==0){
                        res.add(temp.pont4);
                        if(temp.pont3.etat==0)
                            res.add(temp.pont3);

                    }
                    if(est_voisin_d_un_bord(temp.pont1, objectif_bordJ1[1]) && temp.pont1.etat==0){
                        res.add(temp.pont1);
                        if(temp.pont2.etat==0)
                            res.add(temp.pont2);
                    }else if(est_voisin_d_un_bord(temp.pont4, objectif_bordJ1[1]) && temp.pont4.etat==0){
                        res.add(temp.pont4);
                        if(temp.pont3.etat==0)
                            res.add(temp.pont3);

                }
                    if(objectif_bordJ1[2]!=0 && est_voisin_d_un_bord(temp.pont1, objectif_bordJ1[2]) && temp.pont1.etat==0){
                        res.add(temp.pont1);
                        if(temp.pont2.etat==0)
                            res.add(temp.pont2);
                    }else if(objectif_bordJ1[2]!=0 && est_voisin_d_un_bord(temp.pont4, objectif_bordJ1[2]) && temp.pont4.etat==0){
                        res.add(temp.pont4);
                        if(temp.pont3.etat==0)
                            res.add(temp.pont3);

                    }


                }
                else if((est_voisin_d_un_bord(tmp, objectif_bordJ1[0]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ1[0]))
                            || (est_voisin_d_un_bord(tmp, objectif_bordJ1[1]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ1[1]))
                            || (objectif_bordJ1[2]!=0 && est_voisin_d_un_bord(tmp,objectif_bordJ1[2]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ1[2]))){
                        System.out.println("LOL COUCOU ICI MDR");
                        int i=r.nextInt(2);
                        caseM [] tabrnd=cases_bord_depuis_case_voisine(tmp);
                         return tabrnd[i];

                }

            }
            else{
                if((est_joker1_selon_bord(tmp, objectif_bordJ2[0]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[0]))
                        || (est_joker1_selon_bord(tmp, objectif_bordJ2[1]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[1]))
                        || (objectif_bordJ2[2]!=0 && est_joker1_selon_bord(tmp, objectif_bordJ2[2]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[2]))){
                    Joker1 temp;
                    System.out.println("Complete Joker1");
                    temp=retourne_joker1_depuis_case(tmp);
                    int i = r.nextInt(3);
                    caseM[] tabrnd = new caseM[3];
                    tabrnd[0] = temp.pont1;
                    tabrnd[1] = temp.pont2;
                    tabrnd[2] = temp.pont3;
                    if ((tabrnd[0].etat == 0 || tabrnd[0].etat == couleur_joueur_adverse()) && (tabrnd[1].etat == 0 || tabrnd[1].etat == couleur_joueur_adverse()) && (tabrnd[2].etat == 0 || tabrnd[2].etat == couleur_joueur_adverse())) {
                        if (est_pont_cassé(temp.joker, tabrnd[i])) {
                            if (i != 2) {
                                res.add(tabrnd[i + 1]);
                            } else {
                           res.add(tabrnd[0]);
                    }
                        } else {
                        res.add(tabrnd[i]);
                        }
                    }

                }
                 else if((est_joker2_selon_bord(tmp, objectif_bordJ2[0]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[0]))
                 || (est_joker2_selon_bord(tmp, objectif_bordJ2[1]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[1]))
                 || (objectif_bordJ2[2]!=0 && est_joker2_selon_bord(tmp, objectif_bordJ2[2]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[2])) ){
                    Joker2 temp;
                    System.out.println("Complete Joker 2");
                    temp=retourne_joker2_depuis_case(tmp);
                    if(est_voisin_d_un_bord(temp.pont1, objectif_bordJ2[0]) && temp.pont1.etat==0){
                        res.add(temp.pont1);
                        if(temp.pont2.etat==0)
                            res.add(temp.pont2);
                    }else if(est_voisin_d_un_bord(temp.pont4, objectif_bordJ2[0]) && temp.pont4.etat==0){
                        res.add(temp.pont4);
                        if(temp.pont3.etat==0)
                            res.add(temp.pont3);

                    }
                    if(est_voisin_d_un_bord(temp.pont1, objectif_bordJ2[1]) && temp.pont1.etat==0){
                        res.add(temp.pont1);
                        if(temp.pont2.etat==0)
                            res.add(temp.pont2);
                    }else if(est_voisin_d_un_bord(temp.pont4, objectif_bordJ2[1]) && temp.pont4.etat==0){
                        res.add(temp.pont4);
                        if(temp.pont3.etat==0)
                            res.add(temp.pont3);

                }
                    if(objectif_bordJ2[2]!=0 && est_voisin_d_un_bord(temp.pont1, objectif_bordJ2[2]) && temp.pont1.etat==0){
                        res.add(temp.pont1);
                        if(temp.pont2.etat==0)
                            res.add(temp.pont2);
                    }else if(objectif_bordJ2[2]!=0 && est_voisin_d_un_bord(temp.pont4, objectif_bordJ2[2]) && temp.pont4.etat==0){
                        res.add(temp.pont4);
                        if(temp.pont3.etat==0)
                            res.add(temp.pont3);

                    }
                }
                else if((est_voisin_d_un_bord(tmp, objectif_bordJ2[0]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[0]))
                        || (est_voisin_d_un_bord(tmp, objectif_bordJ2[1]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[1]))
                        || (objectif_bordJ2[2]!=0 && est_voisin_d_un_bord(tmp,objectif_bordJ2[2]) && !touche_le_bord(couleur_joueur_courant(), objectif_bordJ2[2]))){
                   //     System.out.println("Complete voisin bord");
                        int i=r.nextInt(2);
                        caseM [] tabrnd=cases_bord_depuis_case_voisine(tmp);
                        return tabrnd[i];

                }
                 else{
                    ponts=pont_caseM_couleur(tmp);
                    ListIterator<caseM> ITponts=ponts.listIterator();
                    while(ITponts.hasNext()){
                        caseM tmppont=ITponts.next();
                       if(L.contains(tmppont)){
                            int i=r.nextInt(2);
                            caseM [] tabrnd=cases_milieu_pont(tmp, tmppont);
                            res.add(tabrnd[i]);
                       }

                    }

                }


            }
         }
        System.out.println("NOMBRE DE TROU A COMPLETER POUR LA VICTOIRE :"+res.size());
         if(res.size()>0){
            ListIterator<caseM> it = res.listIterator();
            while(it.hasNext()){
                caseM get = it.next();
                f.matrice[get.i][get.j].etat = couleur_joueur_courant();
                if(!estDefaite(f.joueur_courant)){
                    f.matrice[get.i][get.j].etat = 0;
                    return get;
                }
                f.matrice[get.i][get.j].etat = 0;
            }
            int j=r.nextInt(res.size());
            return res.get(j);
       }
        return null;
    }

   synchronized private caseM[] cases_bord_depuis_case_voisine(caseM x){
       caseM [] res=new caseM[2];
       if(est_voisin_d_un_bord(x,1)){
           res[0]=f.matrice[x.i-1][x.j];
           res[1]=f.matrice[x.i-1][x.j-1];
       }
        else if(est_voisin_d_un_bord(x,2)){
           res[0]=f.matrice[x.i-1][x.j];
           res[1]=f.matrice[x.i][x.j+1];
       }
       else if(est_voisin_d_un_bord(x,3)){
           res[0]=f.matrice[x.i][x.j+1];
           res[1]=f.matrice[x.i+1][x.j+1];
       }
       else if(est_voisin_d_un_bord(x,4)){
           res[0]=f.matrice[x.i+1][x.j];
           res[1]=f.matrice[x.i+1][x.j+1];
       }
       else if(est_voisin_d_un_bord(x,5)){
           res[0]=f.matrice[x.i][x.j-1];
           res[1]=f.matrice[x.i+1][x.j];
       }
       else if(est_voisin_d_un_bord(x,6)){
           res[0]=f.matrice[x.i][x.j-1];
           res[1]=f.matrice[x.i-1][x.j-1];
       }

       return res;
    }

   synchronized private ArrayList<caseM> list_boules(caseM[][] matrice, int couleur){
        //retourne la liste des boules de la matrice f.matrice de couleur couleur.
        ArrayList<caseM> L = new ArrayList<caseM>();
        for(int i=0; i<f.N; i++){
            for(int j=0; j<f.N; j++){
                if(matrice[i][j].etat == couleur){
                    L.add(matrice[i][j]);
                }
            }
        }
        return L;
    }

   synchronized private caseM completer_joker_casse(caseM tmp,int couleur){
        caseM res=null;
        int [] tabObjectif=new int[3];
        if(f.joueur_courant==1)
           tabObjectif=objectif_bordJ1;
       else if(f.joueur_courant==2)
           tabObjectif=objectif_bordJ2;

        if(est_joker2_casse(tmp,tabObjectif[0])){
                caseM pont1=null;caseM pont2=null;
               System.out.println("APPAREMENT JOKER 2 CASSE ICI"+tabObjectif[0]);
               Joker2 temp=retourne_joker2_depuis_case(tmp);
               if(est_voisin_d_un_bord(temp.pont1,tabObjectif[0]))
                    pont1=temp.pont1;
                if(est_bord_selon_numcote(temp.pont2, tabObjectif[0]))
                    pont2=temp.pont2;
                if(est_voisin_d_un_bord(temp.pont4, tabObjectif[0]))
                    pont1=temp.pont4;
                if(est_bord_selon_numcote(temp.pont3, tabObjectif[0]))
                    pont2=temp.pont3;

                if(est_pont_obstrué(temp.joker, pont1) && est_pont_cassé(temp.joker, pont2)){
                  caseM [] tab=cases_milieu_pont(temp.joker, pont2);
                  if(tab[0].etat==0)
                      if(est_voisin_d_un_bord(tab[0], tabObjectif[0]))
                          res=tab[0];
                  if(tab[1].etat==0)
                      if(est_voisin_d_un_bord(tab[1], tabObjectif[0]))
                          res=tab[1];

               }
               else if(est_pont_cassé(temp.joker, pont1) && est_pont_cassé(temp.joker, pont2)){
                    caseM [] tab=cases_milieu_pont(temp.joker, pont2);
                  if(tab[0].etat==0)
                      if(est_voisin_d_un_bord(tab[0], tabObjectif[0]))
                          res=tab[0];
                  if(tab[1].etat==0)
                      if(est_voisin_d_un_bord(tab[1], tabObjectif[0]))
                          res=tab[1];
               }
               if(pont2.etat == couleur_joueur_adverse() && !est_pont_cassé(temp.joker,pont1) && !est_pont_obstrué(temp.joker,pont1) && !est_pont_obstrué(temp.joker, pont2)  ){

                res=pont1;

                }
               if(!est_pont_cassé(temp.joker, pont2) && !est_pont_obstrué(temp.joker, pont2) && est_pont_cassé(temp.joker,pont1))
                   res=pont2;
               /*
               if(est_voisin_d_un_bord(temp.pont1, tabObjectif[0]) && !est_pont_cassé(temp.joker, temp.pont1))
                    res=temp.pont1;
               else if(est_bord_selon_numcote(temp.pont2, tabObjectif[0]) && !est_pont_cassé(temp.joker, temp.pont2))
                   res=temp.pont2;
               else if(est_bord_selon_numcote(temp.pont3, tabObjectif[0]) && !est_pont_cassé(temp.joker, temp.pont3))
                   res=temp.pont3;
               else if(est_voisin_d_un_bord(temp.pont4, tabObjectif[0]) && !est_pont_cassé(temp.joker, temp.pont4))
                   res=temp.pont4;*/


             if(pont2 != null && pont2.etat == 0){
                 caseM[] tab = cases_milieu_pont(temp.joker, pont2);
                 if(tab[1].etat==couleur_joueur_adverse() && tab[0].etat==0){
                     res = tab[0];
                 }else{
                     res = tab[1];
                 }
             }
           }else if(est_joker2_casse(tmp,tabObjectif[1])){
               System.out.println("APPAREMENT JOKER 2 CASSE ICI"+tabObjectif[1]);
               Joker2 temp = retourne_joker2_depuis_case(tmp);
               caseM pont1=null;caseM pont2=null;
                   if (est_voisin_d_un_bord(temp.pont1, tabObjectif[1])) {
                    pont1 = temp.pont1;
                }
                if (est_bord_selon_numcote(temp.pont2, tabObjectif[1])) {
                    pont2 = temp.pont2;
                }
                if (est_voisin_d_un_bord(temp.pont4, tabObjectif[1])) {
                    pont1 = temp.pont4;
                }
                if (est_bord_selon_numcote(temp.pont3, tabObjectif[1])) {
                    pont2 = temp.pont3;
                }

                if (est_pont_obstrué(temp.joker, pont1) && est_pont_cassé(temp.joker, pont2)) {
                    caseM[] tab = cases_milieu_pont(temp.joker, pont2);
                    if (tab[0].etat == 0) {
                        if (est_voisin_d_un_bord(tab[0], tabObjectif[1])) {
                            res = tab[0];
                        }
                    }
                    if (tab[1].etat == 0) {
                        if (est_voisin_d_un_bord(tab[1], tabObjectif[1])) {
                            res = tab[1];
                        }
                    }

                } else if (est_pont_cassé(temp.joker, pont1) && est_pont_cassé(temp.joker, pont2)) {
                    caseM[] tab = cases_milieu_pont(temp.joker, pont2);
                    if (tab[0].etat == 0) {
                        if (est_voisin_d_un_bord(tab[0], tabObjectif[1])) {
                            res = tab[0];
                        }
                    }
                    if (tab[1].etat == 0) {
                        if (est_voisin_d_un_bord(tab[1], tabObjectif[1])) {
                            res = tab[1];
                        }
                    }
                }
            if (pont2.etat == couleur_joueur_adverse() && !est_pont_cassé(temp.joker, pont1) && !est_pont_obstrué(temp.joker, pont1) && !est_pont_obstrué(temp.joker, pont2)) {
                res=pont1;
            }
              /* if (est_voisin_d_un_bord(temp.pont1, tabObjectif[1]) && !est_pont_cassé(temp.joker, temp.pont1)) {
                   res = temp.pont1;
               } else if (est_bord_selon_numcote(temp.pont2, tabObjectif[1]) && !est_pont_cassé(temp.joker, temp.pont2)) {
                   res = temp.pont2;
               } else if (est_bord_selon_numcote(temp.pont3, tabObjectif[1]) && !est_pont_cassé(temp.joker, temp.pont3)) {
                   res = temp.pont3;
               } else if (est_voisin_d_un_bord(temp.pont4, tabObjectif[1]) && !est_pont_cassé(temp.joker, temp.pont4)) {
                   res = temp.pont4;
               }*/

             if(pont2 != null && pont2.etat == 0){
                 caseM[] tab = cases_milieu_pont(temp.joker, pont2);
                 if(tab[1].etat==couleur_joueur_adverse() && tab[0].etat==0){
                     res = tab[0];
                 }else{
                     res = tab[1];
                 }
             }
           }else if(couleur_joueur_courant()==1 && est_joker2_casse(tmp,tabObjectif[2])){
               System.out.println("APPAREMENT JOKER 2 CASSE ICI"+tabObjectif[2]);
               Joker2 temp = retourne_joker2_depuis_case(tmp);

                caseM pont1=null;caseM pont2=null;
                   if (est_voisin_d_un_bord(temp.pont1, tabObjectif[2])) {
                    pont1 = temp.pont1;
                }
                if (est_bord_selon_numcote(temp.pont2, tabObjectif[2])) {
                    pont2 = temp.pont2;
                }
                if (est_voisin_d_un_bord(temp.pont4, tabObjectif[2])) {
                    pont1 = temp.pont4;
                }
                if (est_bord_selon_numcote(temp.pont3, tabObjectif[2])) {
                    pont2 = temp.pont3;
                }

                if (est_pont_obstrué(temp.joker, pont1) && est_pont_cassé(temp.joker, pont2)) {
                    caseM[] tab = cases_milieu_pont(temp.joker, pont2);
                    if (tab[0].etat == 0) {
                        if (est_voisin_d_un_bord(tab[0], tabObjectif[2])) {
                            res = tab[0];
                        }
                    }
                    if (tab[1].etat == 0) {
                        if (est_voisin_d_un_bord(tab[1], tabObjectif[2])) {
                            res = tab[1];
                        }
                    }

                } else if (est_pont_cassé(temp.joker, pont1) && est_pont_cassé(temp.joker, pont2)) {
                    caseM[] tab = cases_milieu_pont(temp.joker, pont2);
                    if (tab[0].etat == 0) {
                        if (est_voisin_d_un_bord(tab[0], tabObjectif[2])) {
                            res = tab[0];
                        }
                    }
                    if (tab[1].etat == 0) {
                        if (est_voisin_d_un_bord(tab[1], tabObjectif[2])) {
                            res = tab[1];
                        }
                    }
                }
            if (pont2.etat == couleur_joueur_adverse() && !est_pont_cassé(temp.joker,pont1) && !est_pont_obstrué(temp.joker, pont1) && !est_pont_obstrué(temp.joker, pont2)) {
                res=pont1;
            }
               /*if (est_voisin_d_un_bord(temp.pont1, tabObjectif[2]) && !est_pont_cassé(temp.joker, temp.pont1)) {
                   res = temp.pont1;
               } else if (est_bord_selon_numcote(temp.pont2, tabObjectif[2]) && !est_pont_cassé(temp.joker, temp.pont2)) {
                   res = temp.pont2;
               } else if (est_bord_selon_numcote(temp.pont3, tabObjectif[2]) && !est_pont_cassé(temp.joker, temp.pont3)) {
                   res = temp.pont3;
               } else if (est_voisin_d_un_bord(temp.pont4, tabObjectif[2]) && !est_pont_cassé(temp.joker, temp.pont4)) {
                   res = temp.pont4;
               }*/
             if(pont2 != null && pont2.etat == 0){
                 caseM[] tab = cases_milieu_pont(temp.joker, pont2);
                 if(tab[1].etat==couleur_joueur_adverse() && tab[0].etat==0){
                     res = tab[0];
                 }else{
                     res = tab[1];
                 }
             }

           }
           else if(est_joker1_casse(tmp)){
               System.out.println("APPAREMENT JOKER 1 CASSE ICI");
               Joker1 temp=retourne_joker1_depuis_case(tmp);
              if(est_pont_obstrué(temp.joker,temp.pont1)){
                        if(est_pont_cassé(temp.joker,temp.pont2) && !est_pont_cassé(temp.joker,temp.pont3) && !est_pont_obstrué(temp.joker,temp.pont3))
                            res=temp.pont3;


                }else if(est_pont_obstrué(temp.joker,temp.pont3)){
                    if(est_pont_cassé(temp.joker,temp.pont2) && !est_pont_cassé(temp.joker,temp.pont1) && !est_pont_obstrué(temp.joker,temp.pont1))
                        res=temp.pont1;

                }else if(!est_pont_obstrué(temp.joker,temp.pont1) && !est_pont_obstrué(temp.joker,temp.pont2) && !est_pont_obstrué(temp.joker,temp.pont3)){
                    int i=0;
                    if(est_pont_cassé(temp.joker, temp.pont1) && est_pont_cassé(temp.joker, temp.pont3)){
                        caseM [] tab=cases_milieu_pont(temp.joker, temp.pont2);
                        if(tab[0].etat==0)
                            res=tab[0];
                        else if(tab[1].etat==0)
                            res=tab[1];
                    }else{

                        if(est_pont_cassé(temp.joker, temp.pont1))
                                i++;
                        if(est_pont_cassé(temp.joker, temp.pont2))
                            i++;
                        if(est_pont_cassé(temp.joker, temp.pont3))
                            i++;
                        if(i==2){
                           if(!est_pont_cassé(temp.joker,temp.pont1))
                             res=temp.pont1;
                          else if(!est_pont_cassé(temp.joker,temp.pont2))
                            res=temp.pont2;
                          else if(!est_pont_cassé(temp.joker,temp.pont3))
                              res=temp.pont3;
                      }
                  }
                }




           }

       return res;
    }

   private class double_caseM{
        caseM a;
        caseM b;

        public double_caseM(caseM a, caseM b){
            this.a = a;
            this.b = b;
        }
    }

   synchronized private ArrayList<double_caseM> caseM_completer_pont_casse(caseM[][] mat, caseM x){
        /* Retourne la liste de double cases qui sont  les pponts cassés autour
         * du point x donné en paramètre. Premier element de double case est un
         * point qui construit le pont avec x et le deuxieme element de cette
         * structure est une case a completer si le pont est cassé.
         */

        boolean[][]marquage = new boolean[f.N][f.N];
        for(int i=0; i<f.N; i++){
            for(int j=0; j<f.N; j++){
                marquage[i][j] = false;
            }
        }
        ArrayList<double_caseM> R = new ArrayList<double_caseM>();
        caseM avec = null;
        // ((mat[x.i-1][x.j-1].numcote !=0 || mat[x.i-1][x.j].numcote != 0)
        // && mat[x.i-2][x.j-1].etat==-1)
        if(x.i>=2 && x.j>=1 && (mat[x.i-2][x.j-1].etat==x.etat)){
            //tester si on est dans la matrice
                avec = mat[x.i-2][x.j-1];
            if(!existe_chemin(x, avec)){
            if(mat[x.i-1][x.j-1].etat==couleur_joueur_adverse() && mat[x.i-1][x.j].etat==0){
                //si on la case n'est pas marquée -> marquer et ajouter au resultat
                marquage[x.i-1][x.j]=true;
                R.add(new double_caseM(avec, mat[x.i-1][x.j]));
            }else if(mat[x.i-1][x.j-1].etat==0 && mat[x.i-1][x.j].etat==couleur_joueur_adverse()){
                marquage[x.i-1][x.j-1]=true;
                R.add(new double_caseM(avec, mat[x.i-1][x.j-1]));
            }
        }
        }
        if(x.i>=1 && x.j>=2 && mat[x.i-1][x.j-2].etat==x.etat){
                avec = mat[x.i-1][x.j-2];
            if(!existe_chemin(x, avec)){
            if(mat[x.i-1][x.j-1].etat==couleur_joueur_adverse() && mat[x.i][x.j-1].etat==0){
                if(marquage[x.i][x.j-1]==false){
                    marquage[x.i][x.j-1]=true;
                    R.add(new double_caseM(avec, mat[x.i][x.j-1]));
                }
            }else if(mat[x.i-1][x.j-1].etat==0 && mat[x.i][x.j-1].etat==couleur_joueur_adverse()){
                if(marquage[x.i-1][x.j-1]==false){
                    marquage[x.i-1][x.j-1]=true;
                    R.add(new double_caseM(avec, mat[x.i-1][x.j-1]));
                }
            }
        }
        }
        if(x.i<=f.N-2 && x.j>=1 && mat[x.i+1][x.j-1].etat==x.etat){
                avec = mat[x.i+1][x.j-1];
            if(!existe_chemin(x, avec)){
            if(mat[x.i][x.j-1].etat==couleur_joueur_adverse() && mat[x.i+1][x.j].etat==0){
                if(marquage[x.i+1][x.j]==false){
                    marquage[x.i+1][x.j]=true;
                    R.add(new double_caseM(avec, mat[x.i+1][x.j]));
                }
            }else if(mat[x.i][x.j-1].etat==0 && mat[x.i+1][x.j].etat==couleur_joueur_adverse()){
                if(marquage[x.i][x.j-1]==false){
                    marquage[x.i][x.j-1]=true;
                    R.add(new double_caseM(avec, mat[x.i][x.j-1]));
                }
            }
        }
        }
        if(x.i<=f.N-3 && x.j<=f.N-2 && mat[x.i+2][x.j+1].etat==x.etat){
                avec = mat[x.i+2][x.j+1];
            if(!existe_chemin(x, avec)){
            if(mat[x.i+1][x.j].etat==couleur_joueur_adverse() && mat[x.i+1][x.j+1].etat==0){
                if(marquage[x.i+1][x.j+1]==false){
                    marquage[x.i+1][x.j+1]=true;
                    R.add(new double_caseM(avec, mat[x.i+1][x.j+1]));
                }
            }else if(mat[x.i+1][x.j].etat==0 && mat[x.i+1][x.j+1].etat==couleur_joueur_adverse()){
                if(marquage[x.i+1][x.j]==false){
                    marquage[x.i+1][x.j]=true;
                    R.add(new double_caseM(avec, mat[x.i+1][x.j]));
                }
            }
        }
        }
        if(x.i<=f.N-2 && x.j<=f.N-3 && mat[x.i+1][x.j+2].etat==x.etat){
             avec = mat[x.i+1][x.j+2];
            if(!existe_chemin(x, avec)){
            if(mat[x.i+1][x.j+1].etat==couleur_joueur_adverse() && mat[x.i][x.j+1].etat==0){
                if(marquage[x.i][x.j+1]==false){
                    marquage[x.i][x.j+1]=true;
                    R.add(new double_caseM(avec, mat[x.i][x.j+1]));
                }
            }else if(mat[x.i+1][x.j+1].etat==0 && mat[x.i][x.j+1].etat==couleur_joueur_adverse()){
                if(marquage[x.i+1][x.j+1]==false){
                    marquage[x.i+1][x.j+1]=true;
                    R.add(new double_caseM(avec, mat[x.i+1][x.j+1]));
                }
            }
        }
        }
        if(x.i>=1 && x.j<=f.N-2 && mat[x.i-1][x.j+1].etat==x.etat){
             avec = mat[x.i-1][x.j+1];
            if(!existe_chemin(x, avec)){
            if(mat[x.i][x.j+1].etat==couleur_joueur_adverse() && mat[x.i-1][x.j].etat==0){
                if(marquage[x.i-1][x.j]==false){
                    marquage[x.i-1][x.j]=true;
                    R.add(new double_caseM(avec, mat[x.i-1][x.j]));
                }
            }else if(mat[x.i][x.j+1].etat==0 && mat[x.i-1][x.j].etat==couleur_joueur_adverse()){
                if(marquage[x.i][x.j+1]==false){
                    marquage[x.i][x.j+1]=true;
                    R.add(new double_caseM(avec, mat[x.i][x.j+1]));
                }
            }
        }
        }
        return R;
        //if(!L.isEmpty()){
            /* RETOURNER une valeur aléatoire de la liste L */
            /* optimisation possible */
        //    System.out.println("Pont est cassé pour ("+x.i+", "+x.j+") :");
          /*  affichage_list(L);
            Random rand = new Random();
            int r = rand.nextInt(L.size());
            double_caseM res = new double_caseM(avec, L.get(r));
            return res;
        }else{
            return null;
        }*/
    }

   synchronized private boolean contain_tab(int[] tab, int bord){
        for(int i=0; i<tab.length; i++){
            if(tab[i]==bord) return true;
        }
        return false;
    }

   private boolean touche_le_bord(int couleur, int bord){
        return !boules_sur_un_bord(couleur, bord).isEmpty();
   }

   private boolean touche_le_bord_avec_composante_ia(int bord){
        ArrayList<caseM> L = boules_sur_un_bord(couleur_joueur_courant(), bord);
        if(!L.isEmpty()){
            ListIterator<caseM> it = L.listIterator();
            while(it.hasNext()){
                caseM p = it.next();
                if(composante_ia.contains(p)){
                    return true;
                }
            }
        }
        return false;
   }

   private boolean liste_touche_le_bord(ArrayList<caseM> L, int bord){
       ListIterator<caseM> it = L.listIterator();
       while(it.hasNext()){
           caseM x = it.next();
           if(est_bord_selon_numcote(x, bord)){
               return true;
           }
       }
       return false;
   }

   synchronized private caseM caseM_completer_bord_casse(caseM[][] mat, caseM x){
        /* Si x est en liaison avec le bord et celui-la est cassé,
         * retourner la case qui complète le bord cassé.
         */
        int ac = 1;
        if(x.etat == 1){
            ac = 2;
        }
        ArrayList<caseM> L = new ArrayList<caseM>();

        // ((f.matrice[x.i-1][x.j-1].numcote ==ac || f.matrice[x.i-1][x.j].numcote ac != 0)
        // && f.matrice[x.i-2][x.j-1].etat==-1)

        if(x.i>=1 && x.j>=1 && mat[x.i-1][x.j-1].numcote!=0
                && mat[x.i-1][x.j].numcote!=0){
            // (i-2, j-1) h
            if(((f.joueur_courant == 1 && contain_tab(objectif_bordJ1, 1))
                    || (f.joueur_courant == 2 && contain_tab(objectif_bordJ2, 1)))
                    && !touche_le_bord_avec_composante_ia(1)){
                if(mat[x.i-1][x.j-1].etat==ac && mat[x.i-1][x.j].etat==0){
                    L.add(mat[x.i-1][x.j]);
                }else if(mat[x.i-1][x.j-1].etat==0 && mat[x.i-1][x.j].etat==ac){
                    L.add(mat[x.i-1][x.j-1]);
                }
            }
        }
        if(x.i>=1 && x.j>=1 && mat[x.i-1][x.j-1].numcote!=0
                && mat[x.i][x.j-1].numcote!=0){
            if(((f.joueur_courant == 1 && contain_tab(objectif_bordJ1, 6))
                    || (f.joueur_courant == 2 && contain_tab(objectif_bordJ2, 6)))
                    && !touche_le_bord_avec_composante_ia(6)){
                if(mat[x.i-1][x.j-1].etat==ac && mat[x.i][x.j-1].etat==0){
                    L.add(mat[x.i][x.j-1]);
                }else if(mat[x.i-1][x.j-1].etat==0 && mat[x.i][x.j-1].etat==ac){
                    L.add(mat[x.i-1][x.j-1]);
                }
            }
        }
        if(x.i<=f.N-2 && x.j>=1 && mat[x.i][x.j-1].numcote!=0
                && mat[x.i+1][x.j].numcote!=0){
            if(((f.joueur_courant == 1 && contain_tab(objectif_bordJ1, 5))
                    || (f.joueur_courant == 2 && contain_tab(objectif_bordJ2, 5)))
                    && !touche_le_bord_avec_composante_ia(5)){
                if(mat[x.i][x.j-1].etat==ac && mat[x.i+1][x.j].etat==0){
                    L.add(mat[x.i+1][x.j]);
                }else if(mat[x.i][x.j-1].etat==0 && mat[x.i+1][x.j].etat==ac){
                    L.add(mat[x.i][x.j-1]);
                }
            }
        }
        if(x.i<=f.N-2 && x.j<=f.N-2 && mat[x.i+1][x.j].numcote!=0
                && mat[x.i+1][x.j+1].numcote!=0){
            if(((f.joueur_courant == 1 && contain_tab(objectif_bordJ1, 4))
                    || (f.joueur_courant == 2 && contain_tab(objectif_bordJ2, 4)))
                    && !touche_le_bord_avec_composante_ia(4)){
                if(mat[x.i+1][x.j].etat==ac && mat[x.i+1][x.j+1].etat==0){
                    L.add(mat[x.i+1][x.j+1]);
                }else if(mat[x.i+1][x.j].etat==0 && mat[x.i+1][x.j+1].etat==ac){
                    L.add(mat[x.i+1][x.j]);
                }
            }
        }
        if(x.i<=f.N-2 && x.j<=f.N-2 && mat[x.i+1][x.j+1].numcote!=0
                && mat[x.i][x.j+1].numcote!=0){
            if(((f.joueur_courant == 1 && contain_tab(objectif_bordJ1, 3))
                    || (f.joueur_courant == 2 && contain_tab(objectif_bordJ2, 3)))
                    && !touche_le_bord_avec_composante_ia(3)){
                if(mat[x.i+1][x.j+1].etat==ac && mat[x.i][x.j+1].etat==0){
                    L.add(mat[x.i][x.j+1]);
                }else if(mat[x.i+1][x.j+1].etat==0 && mat[x.i][x.j+1].etat==ac){
                    L.add(mat[x.i+1][x.j+1]);
                }
            }
        }

        if(x.i>=1 && x.j<=f.N-2 && mat[x.i][x.j+1].numcote!=0
                && mat[x.i-1][x.j].numcote!=0){
            if(((f.joueur_courant == 1 && contain_tab(objectif_bordJ1, 2))
                    || (f.joueur_courant == 2 && contain_tab(objectif_bordJ2, 2)))
                    && !touche_le_bord_avec_composante_ia(2)){
                if(mat[x.i][x.j+1].etat==ac && mat[x.i-1][x.j].etat==0){
                    L.add(mat[x.i-1][x.j]);
                }else if(mat[x.i][x.j+1].etat==0 && mat[x.i-1][x.j].etat==ac){
                    L.add(mat[x.i][x.j+1]);
                }
            }
        }
        if(!L.isEmpty()){
            /* RETOURNER une valeur aléatoire de la liste L */
            /* optimisation possible */
//            System.out.println("Bord est cassé pour ("+x.i+", "+x.j+") :");
           // affichage_list(L);
            Random rand = new Random();
            int r = rand.nextInt(L.size());
            return L.get(r);
        }else{
            return null;
        }
    }

   synchronized private caseM caseM_completer_chemin_principal_casse(ArrayList<caseM> L, int couleur){
        // Retourne une caseM a completer dans le cas si il existe un bord
        // cassé ou un pont cassé (jok - pont - bord)

        ArrayList<caseM> F = new ArrayList<caseM> ();

       ListIterator<caseM> T = L.listIterator();
            ArrayList<double_caseM> res_poss_list;
            while(T.hasNext()){
                caseM b = T.next();



               res_poss_list = caseM_completer_pont_casse(f.matrice, b);
                
               if(!res_poss_list.isEmpty()){
                   
                    ListIterator<double_caseM> it = res_poss_list.listIterator();
                        double_caseM res = it.next();

                        f.matrice[res.b.i][res.b.j].couleur=res.b.etat;
                        caseM [] tab = cases_milieu_pont(b, res.a);
                        caseM boule_adv = null;
                        if(tab[0].i==res.b.i && tab[0].i==res.b.i){
                            boule_adv = tab[1];
                        }else{
                            boule_adv = tab[0];
                        }
                        ArrayList<caseM> s_adv = successeurs(boule_adv);
                        ListIterator<caseM> it3 = s_adv.listIterator();
                        int compt = 0;
                        int compt2 = 0;
                        while(it3.hasNext()){
                            caseM a = it3.next();
                            if(a.etat == couleur_joueur_courant()){
                                compt ++;
                            }
                            if(a.etat == 0){
                                compt2 ++;
                            }
                        }
                        if(!(compt == 4 && compt2 == 2)){
                            System.out.println("compt ="+compt+"compt2 ="+compt2);
                            f.matrice[res.b.i][res.b.j].etat = couleur_joueur_courant();
                            if(!estDefaite(f.joueur_courant)){
                                f.matrice[res.b.i][res.b.j].etat = 0;
                                System.out.println("-------------IA complete un pont cassé");
                                System.out.println("x = ("+b.i+","+b.j+"), avec y = ("+res.a.i+", "+res.a.j+")");
                                System.out.println("-----------------------------");

                                return res.b;
                            }

                            f.matrice[res.b.i][res.b.j].etat = 0;

                        }
                      
                }
                caseM bord_casse = caseM_completer_bord_casse(f.matrice, b);
                if(bord_casse != null){

                    f.matrice[bord_casse.i][bord_casse.j].etat = couleur_joueur_courant();
                    if(!estDefaite(f.joueur_courant)){
                        f.matrice[bord_casse.i][bord_casse.j].etat = 0;
                        System.out.println("-------------IA complete un bord cassé");
                        System.out.println("-----------------------------");
                        return bord_casse;
                    }
                    f.matrice[bord_casse.i][bord_casse.j].etat = 0;
                    
                }
                 caseM x;
                x = completer_joker_casse(b, b.etat);
                if (x != null) {
                    f.matrice[x.i][x.j].etat = couleur_joueur_courant();
                    if(!estDefaite(f.joueur_courant)){
                        f.matrice[x.i][x.j].etat = 0;
                        System.out.println("-------------IA complete un jocker cassé");
                        return x;
                    }
                    f.matrice[x.i][x.j].etat = 0;
                }


            }
        return null;
    }
   private caseM caseM_completer_chemin_principal_casse2(ArrayList<caseM> L, int couleur){
        ArrayList<caseM> F = new ArrayList<caseM> ();

       ListIterator<caseM> T = L.listIterator();
            ArrayList<double_caseM> res_poss_list;
            while(T.hasNext()){
                caseM b = T.next();



                res_poss_list = caseM_completer_pont_casse(f.matrice, b);

               if(!res_poss_list.isEmpty()){
                    ListIterator<double_caseM> it = res_poss_list.listIterator();
                   // while(it.hasNext()){
                        double_caseM res = it.next();

                        f.matrice[res.b.i][res.b.j].etat=res.b.etat;
                        f.matrice[res.b.i][res.b.j].couleur=res.b.etat;


                      //  if(L.contains(res.a)){
                            System.out.println("-------------IA complete un pont cassé");
                            System.out.println("x = ("+b.i+","+b.j+"), avec y = ("+res.a.i+", "+res.a.j+")");
                            System.out.println("-----------------------------");

                            F.add(res.b);
                    //    }


                    //}

                }
                caseM bord_casse = caseM_completer_bord_casse(f.matrice, b);
                if(bord_casse != null){
                    System.out.println("-------------IA complete un bord cassé");
                    System.out.println("-----------------------------");
                    F.add(bord_casse);
                }
              


            }
            if(!F.isEmpty()){
                ListIterator<caseM> it = F.listIterator();
                while(it.hasNext()){
                    caseM get = it.next();
                    f.matrice[get.i][get.j].etat = couleur_joueur_courant();
                    if(!estDefaite(f.joueur_courant)){
                        f.matrice[get.i][get.j].etat = 0;
                        return get;
                    }
                    f.matrice[get.i][get.j].etat = 0;
                }
            }
        return null;
   }
   private boolean est_element_bord(caseM x){
        boolean bool=false;
        bool = estBord(x);
        if(f.joueur_courant==1){
            bool = bool || (est_voisin_d_un_bord(x, objectif_bordJ1[0])
            || est_voisin_d_un_bord(x, objectif_bordJ1[1])
            || est_voisin_d_un_bord(x, objectif_bordJ1[2]));

            bool = bool || est_joker1_selon_bord(x, objectif_bordJ1[0])
                    || est_joker1_selon_bord(x, objectif_bordJ1[1])
                    || est_joker1_selon_bord(x, objectif_bordJ1[2]);
        }else if(f.joueur_courant==2){
            bool = bool || (est_voisin_d_un_bord(x, objectif_bordJ1[0])
            || est_voisin_d_un_bord(x, objectif_bordJ1[1]));

            bool = bool || est_joker1_selon_bord(x, objectif_bordJ1[0])
                    || est_joker1_selon_bord(x, objectif_bordJ1[1]);
        }
        return bool;
    }

   private ArrayList<caseM> anti_ponts(caseM x){
       ArrayList<caseM> res = new ArrayList<caseM>();
       int i = x.i;
       int j = x.j;
       if(i<=f.N-3 && j<=f.N-3 && f.matrice[i+2][j+2]!=null){
           res.add(f.matrice[i+2][j+2]);
       }
       if(i<=f.N-3 && f.matrice[i+2][j] != null){
           res.add(f.matrice[i+2][j]);
       }
       if(j>=2 && f.matrice[i][j-2] != null){
           res.add(f.matrice[i][j-2]);
       }
       if(i>=2 && j>=2 && f.matrice[i-2][j-2] != null){
           res.add(f.matrice[i-2][j-2]);
       }
       if(i>=2 && f.matrice[i-2][j] != null){
           res.add(f.matrice[i-2][j]);
       }
       if(j<=f.N-3 && f.matrice[i][j+2] != null){
           res.add(f.matrice[i][j+2]);
       }
       return res;
   }

   private ArrayList<caseM> anti_ponts_couleur(caseM x){
       int couleur = x.etat;
       ArrayList<caseM> res = new ArrayList<caseM>();
       ArrayList<caseM> restmp = anti_ponts(x);
       ListIterator<caseM> it = restmp.listIterator();
       while(it.hasNext()){
           caseM h = it.next();
           if(h.etat == couleur){
               res.add(h);
           }
       }
       return res;
   }

   private ArrayList<caseM> anti_ponts_libre(caseM x){
       ArrayList<caseM> res = new ArrayList<caseM>();
       ArrayList<caseM> restmp = anti_ponts(x);
       ListIterator<caseM> it = restmp.listIterator();
       while(it.hasNext()){
           caseM h = it.next();
           if(h.etat == 0){
               res.add(h);
           }
       }
       return res;
   }

   private caseM caseM_milieur_anti_pont(caseM x, caseM y){
       int i = x.i;
       int j = x.j;
       if(i<=f.N-3 && j<=f.N-3 && f.matrice[i+2][j+2]!=null && y.i == i+2 && y.j == j+2){
           return f.matrice[i+1][j+1];
       }
       if(i<=f.N-3 && f.matrice[i+2][j] != null && y.i == i+2 && y.j == j){
           return f.matrice[i+1][j];
       }
       if(j>=2 && f.matrice[i][j-2] != null && y.i == i && y.j == j-2){
           return f.matrice[i][j-1];
       }
       if(i>=2 && j>=2 && f.matrice[i-2][j-2] != null && y.i == i-2 && y.j == j-2){
           return f.matrice[i-1][j-1];
       }
       if(i>=2 && f.matrice[i-2][j] != null && y.i == i-2 && y.j == j){
           return f.matrice[i-1][j];
       }
       if(j<=f.N-3 && f.matrice[i][j+2] != null && y.i == i && y.j == j+2){
           return f.matrice[i][j+1];
       }
       System.out.println("movaise valeur de y dans caseM_milieur_anti_pont");
       return null;
   }

   private caseM completer_anti_pont_ennemi_noir_a_cote(){
        ListIterator<caseM> it = composante_humain.listIterator();
        while(it.hasNext()){
            caseM x = it.next();

            ArrayList<caseM> ponts_couleur_couleur_courant = pont_caseM(x);
            ListIterator<caseM> ponts = ponts_couleur_couleur_courant.listIterator();
            while(ponts.hasNext()){
                caseM p = ponts.next();
                if(p.etat == couleur_joueur_courant()){
                    ArrayList<caseM> anti_ponts1 = anti_ponts_couleur(x);
                    ListIterator<caseM> anti_pont = anti_ponts1.listIterator();
                    while(anti_pont.hasNext()){
                        caseM a = anti_pont.next();
                        caseM res = caseM_milieur_anti_pont(x, a);
                        if(res.etat == 0){
                            return res;
                        }
                    }
                }
            }

        }
        System.out.println("Pas de anti ponts");
        return null;
   }

      private caseM completer_anti_pont_ennemi_tout_conditions(){
        ListIterator<caseM> it = composante_humain.listIterator();
        while(it.hasNext()){
            caseM x = it.next();
            ArrayList<caseM> anti_ponts1 = anti_ponts_couleur(x);
            ListIterator<caseM> anti_pont = anti_ponts1.listIterator();
            while(anti_pont.hasNext()){
                caseM a = anti_pont.next();
                caseM res = caseM_milieur_anti_pont(x, a);
                if(res.etat == 0){
                    return res;
                }
            }
        }
        System.out.println("Pas de anti ponts");
        return null;
   }

   private caseM completer_pont_ennemi_laison_autre_composante(){
        ListIterator<caseM> it = composante_humain.listIterator();
        while(it.hasNext()){
            caseM x = it.next();

            ArrayList<caseM> ponts_couleur_courant = pont_caseM_libre(x);
            ListIterator<caseM> ponts = ponts_couleur_courant.listIterator();
            while(ponts.hasNext()){
                caseM p = ponts.next();
                ArrayList<caseM> _sucs = successeurs(p);
                ListIterator<caseM> sucs = _sucs.listIterator();
                while(sucs.hasNext()){
                    caseM a = sucs.next();
                    if(p.etat == 0 && !composante_humain.contains(a)){
                        return p;
                    }
                }
            }

        }
        System.out.println("Pas de ponts libres en liaison avec une autre composante adversaire");
        return null;
   }

   private boolean est_joker1_selon_bords_selon_couleur(caseM x, int couleur){
       if(x.etat == couleur){
           if(couleur == 1){
                if(est_joker1_selon_bord(x, objectif_bordJ1[0])
                   || est_joker1_selon_bord(x, objectif_bordJ1[1])
                   || est_joker1_selon_bord(x, objectif_bordJ1[2]))
                   return true;
           }else if(couleur == 2){
                if(est_joker1_selon_bord(x, objectif_bordJ1[0])
                   || est_joker1_selon_bord(x, objectif_bordJ1[1]))
                   return true;
           }
       }
       return false;
   }

   synchronized private int _minimax2Ordi2(caseM x, int [] objectif){
        /* Mise à jour de nbcoup_restantJ2 et nbcoup_restantJ1 */
        //int[][] floyd_nouveau = remplir_matrice_floyd();
        int[][] floyd_final= floyd_warshall_epure(Floyd_coup_courant,x.numero_floyd);
        ArrayList<caseM> composante_adverse1 = composante_connexe_principale(couleur_joueur_adverse(),floyd_final);
        ArrayList<caseM> composante_joueur_courant = composante_connexe_principale(couleur_joueur_courant(), floyd_final);
        int difference = 0;
        int nbCoupj1 = 0;
        int nbCoupj2 = 0;
        if(f.joueur_courant==1 && f.joueurUn.couleur==1){
            nbCoupj1 = nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ1[0]);
            nbCoupj1 = nbCoupj1 + nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ1[1]);
            nbCoupj1 = nbCoupj1 + nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ1[2]);

            nbCoupj2 = nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[0]);
            nbCoupj2 = nbCoupj2 + nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[1]);
           
            difference = nbCoupj2 - nbCoupj1;
        }else if(f.joueur_courant==2 && f.joueurDeux.couleur==1){
            nbCoupj2 = nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ2[0]);
            nbCoupj2 = nbCoupj2 + nombre_coup_jusqua_bord(couleur_joueur_courant(),composante_joueur_courant, floyd_final, objectif_bordJ2[1]);
            nbCoupj2 = nbCoupj2 + nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ2[2]);

            nbCoupj1 = nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[0]);
            nbCoupj1 = nbCoupj1 + nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[1]);
           
            difference = nbCoupj1 - nbCoupj2;

        }else if(f.joueur_courant==1 && f.joueurUn.couleur==2){
            nbCoupj1 = nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ1[0]);
            nbCoupj1 = nbCoupj1 + nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ1[1]);

            nbCoupj2 = nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[0]);
            nbCoupj2 = nbCoupj2 + nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[1]);
            nbCoupj2 = nbCoupj2 + nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[2]);
            difference = nbCoupj2 - nbCoupj1;
        }else if(f.joueur_courant==2 && f.joueurDeux.couleur==2){
             nbCoupj2 = nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ2[0]);
             nbCoupj2 = nbCoupj2 + nombre_coup_jusqua_bord(couleur_joueur_courant(), composante_joueur_courant, floyd_final, objectif_bordJ2[1]);

             nbCoupj1 = nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[0]);
             nbCoupj1 = nbCoupj1 + nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[1]);
             nbCoupj1 = nbCoupj1 + nombre_coup_jusqua_bord(couleur_joueur_adverse(), composante_adverse1, floyd_final, objectif[2]);
            difference = nbCoupj1 - nbCoupj2;

        }

      //  System.out.println("_minimax: nbcoup_restantJ1="+nbCoupj1+",nbcoup_restantJ2="+nbCoupj2);
     //   System.out.println("Difference minimax = "+difference+" pour la case i:"+x.i+"   j:"+x.j);

        return difference;
    }

   private boolean est_caseM_au_milieu_pont_du_chemin_principal_ia(caseM successeur){
       ListIterator<caseM> composante = composante_ia.listIterator();
       while(composante.hasNext()){
           caseM c = composante.next();
           ArrayList<caseM> liste_ponts1 = pont_caseM_couleur(c);
           ListIterator<caseM> liste_ponts = liste_ponts1.listIterator();
           while(liste_ponts.hasNext()) {
               caseM case_pont = liste_ponts.next();
               caseM[] case_milieu = cases_milieu_pont(c, case_pont);
               if(successeur.equals(case_milieu[0])|| successeur.equals(case_milieu[1])){
                   return true;
               }
           }
       }
       return false;
   }

   private caseM return_caseM_libre_pont_casse_de_la_liste(ArrayList<caseM> L, int couleur){
       ListIterator<caseM> it = L.listIterator();
       int ac = 1;
       if(couleur == 1){
           ac = 2;
       }
       while(it.hasNext()){
           caseM x = it.next();
           ArrayList<caseM> ponts_coul1 = pont_caseM_couleur_autre(x);
           ListIterator<caseM> ponts_coul = ponts_coul1.listIterator();
           while(ponts_coul.hasNext()){
               caseM y = ponts_coul.next();
               if(L.contains(y)){
                   caseM [] tab = cases_milieu_pont(x, y);
               if(tab[0].etat == couleur_joueur_courant() && tab[1].etat == 0){
                       return tab[1];
               }else if(tab[0].etat == 0 && tab[1].etat == couleur_joueur_courant()){
                       return tab[0];
                   }
               }
           }
       }
       return null;
   }

   private ArrayList<caseM> estim_manhattan_caseM_plus_proche_vers_bord(ArrayList<caseM> L, int bord){
       ArrayList<caseM> res = new ArrayList<caseM>();
       int min = 1000;
       caseM minimal = new caseM(404, 404, 0, 0, null);
       caseM minimalres = null;
       ArrayList<caseM> mini = new ArrayList<caseM>();
       for(int i=0; i< 3; i++){
           ListIterator<caseM> it = L.listIterator();
           while(it.hasNext()){
               caseM element = it.next();
               int tmp = estim_manhattan_bord(element, bord);
               if(tmp < min && !mini.contains(element)){
                   min = tmp;
                   minimalres = element;
               }
           }
           min = 1000;
           res.add(minimalres);
           mini.add(minimalres);
           minimal = minimalres;
       }
       return res;
   }

   private boolean est_voisin_selon_joueur_courant_objectif(caseM x){
        if(f.joueur_courant == 1 && f.joueurUn.couleur == 1){
            if(est_voisin_d_un_bord(x, objectif_bordJ1[0])
                    || est_voisin_d_un_bord(x, objectif_bordJ1[1])
                    || est_voisin_d_un_bord(x, objectif_bordJ1[1])){
                return true;
            }
        }
        else if(f.joueur_courant == 1 && f.joueurUn.couleur == 2){
            if(est_voisin_d_un_bord(x, objectif_bordJ1[0])
                    || est_voisin_d_un_bord(x, objectif_bordJ1[1])){
                return true;
            }

        }
        else if(f.joueur_courant == 2 && f.joueurUn.couleur == 1){
            if(est_voisin_d_un_bord(x, objectif_bordJ2[0])
                    || est_voisin_d_un_bord(x, objectif_bordJ2[1])
                    || est_voisin_d_un_bord(x, objectif_bordJ2[1])){
                return true;
            }
        }
        else if(f.joueur_courant == 2 && f.joueurUn.couleur == 2){
            if(est_voisin_d_un_bord(x, objectif_bordJ2[0])
                    || est_voisin_d_un_bord(x, objectif_bordJ2[1])){
                return true;
            }
        }
        return false;
   }
   private caseM minmaxOrdi3(){
       System.out.println("JOUEUR COURANT ICI :"+couleur_joueur_courant());
       ArrayList<caseM> Listrandom=new ArrayList<caseM>();
       if(Floyd_coup_courant==null){
           int [][] floyd_new=remplir_matrice_floyd();
           Floyd_coup_courant=floyd_warshall(floyd_new);

       }
        System.out.println("Objectif J1 : "+objectif_bordJ1[0]+" , "+objectif_bordJ1[1]+"  , ici le 3e (si 3e) "+objectif_bordJ1[2]);
        System.out.println("Objectif J2 : "+objectif_bordJ2[0]+" , "+objectif_bordJ2[1]+"  , ici le 3e (si 3e) "+objectif_bordJ2[2]);
        if(!f.partie_commencee){
            caseM case_a_jouer = analyser_debut2();
            //avec la fonction -> mise a jour de joueur_press_button
            if(!joueur_press_button){
                //si on n'appuie pas sur le bouton:
                return case_a_jouer;
            }else{
                //si on appuie sur le bouton:
                f.num_coup_blanc = f.nbr_coups_joue;
                f.partie_commencee = true;
                if(f.joueur_courant == 1)
                    f.joueurDeux.couleur = 2;
                else
                    f.joueurUn.couleur = 2;

                f.choixnoir.setEnabled(false);
                f.img_choisir_indispo = new ImageIcon("images/noir_indispo.png");
                f.choixnoir.setDisabledIcon(f.img_choisir_indispo);
                f.aire.repaint();
                return null;
            }
        }else{
            int nb_joueur_adverse = 1;
            if(f.joueur_courant == 1){
                nb_joueur_adverse=2;
            }
            if(estVictoire(nb_joueur_adverse)){
                caseM resulta1 = completer_victoire(composante_humain, nb_joueur_adverse);
                return resulta1;
            }
            // Si partie est commencée:
//
            if(composante_ia!=null){

                if(estVictoire_composante_ponts(composante_ia, couleur_joueur_courant())){
                    // S'il existe un chemin qui conduit à la VICTOIRE:
                    caseM completer_pb_casse = caseM_completer_chemin_principal_casse2(composante_ia, couleur_joueur_courant());
                    if(completer_pb_casse != null){
                            f.matrice[completer_pb_casse.i][completer_pb_casse.j].etat=0;
                            return completer_pb_casse;
                    }
                       // return completer_victoire(f.matrice, couleur_joueur_courant(), L);
                        return completer_victoire(composante_ia,f.joueur_courant);

                }
            }
                // S'il n'existe pas de chemin qui conduit à la VICTOIRE:

            if(composante_ia != null & composante_humain != null){
                System.out.println("composante_ia:--"+composante_ia.size());
                System.out.println("composante_humain:--"+composante_humain.size());
            }

                caseM completer = null;

                if(composante_ia!=null){
                    completer = caseM_completer_chemin_principal_casse2(composante_ia, couleur_joueur_courant());
                }
                if(completer != null){
                    //Si il existe un pont cassé dans un chemin principal:

                     f.matrice[completer.i][completer.j].etat=couleur_joueur_courant();
                        if(!estDefaite(f.joueur_courant) || estVictoire(f.joueur_courant)){
                            f.matrice[completer.i][completer.j].etat=0;
                            return completer;
                         }
                        f.matrice[completer.i][completer.j].etat=0;
                }

                        //Cas général:

                    int difference; //difference entre le nb de coups de l'adversaire et celui du joueur courant
                    if(f.joueur_courant==1){

                        difference = nbcoup_restantJ2 - nbcoup_restantJ1;

                    }else{
                        difference = nbcoup_restantJ1 - nbcoup_restantJ2;

                    }
                    System.out.println("DIFF = "+difference+", nbcoup_restantJ2 = "+nbcoup_restantJ2+", nbcoup_restantJ1 = "+nbcoup_restantJ1);
                    //  System.out.println("JOUEUR NUM COURANT "+joueur_num_cur+" ADV"+joueur_num_adv);

                    if(difference > 0){

 //mode offensif:

                        System.out.println("--Mode Offensif");
                     //   System.out.println("CJC"+couleur_joueur_courant());
                        if(composante_ia==null){
                            ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                            int nb_coups_une_boule;
                            int nb_coups_blanc_potentiel = 1024;
                            caseM case_mem2 = null;
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    if(f.matrice[i][j].etat == 0){

                                        f.matrice[i][j].etat = couleur_joueur_courant();
                                       // int[][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                        liste_une_boule.clear();
                                        liste_une_boule.add(f.matrice[i][j]);
                                        nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                        f.matrice[i][j].etat = 0;


                                        if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                            nb_coups_blanc_potentiel = nb_coups_une_boule;
                                            case_mem2 = f.matrice[i][j];
                                        }
                                        /*if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                            Random r = new Random();
                                            int choix = r.nextInt(2);
                                            if(choix == 1){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                        }*/
                                    }
                                }
                            }
                            return case_mem2;

                        }else{
                            if(composante_humain != null){
                                caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                                if(res != null){
                                    Listrandom.add(res);
                                }
                            }

                            caseM anti_pont_a_completer = completer_anti_pont_ennemi_noir_a_cote();
                            if(anti_pont_a_completer!=null){
                                System.out.println("Bob complete un anti pont!");
                                Listrandom.add(anti_pont_a_completer);
                            }


                            int diff_mem = difference; //reinit
                            boolean[][]marquage = new boolean[f.N][f.N];
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    marquage[i][j] = false;
                                }
                            }
                            int nb_coups_tmp;
                            int nb_coups_min = 1024;
                            caseM case_preferable = null;

                            ListIterator<caseM> composante_joueur_courant_it = composante_ia.listIterator();

                            ArrayList<caseM> tmp= (ArrayList<caseM>) composante_ia.clone();
                            ListIterator<caseM> tmpit=tmp.listIterator();
                            while(composante_joueur_courant_it.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_joueur_courant23 = composante_joueur_courant_it.next();
                                if(!est_element_bord(case_libre_composante_joueur_courant23)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant23, couleur_joueur_courant()))){


                                    ArrayList<caseM> ponts_composante1 = pont_caseM_libre(case_libre_composante_joueur_courant23);
                                    ListIterator<caseM> ponts_composante = ponts_composante1.listIterator();
                                    while(ponts_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_pont_composante = ponts_composante.next();
                                        if(marquage[un_pont_composante.i][un_pont_composante.j]==false){
                                            marquage[un_pont_composante.i][un_pont_composante.j]=true;

                                            f.matrice[un_pont_composante.i][un_pont_composante.j].etat = couleur_joueur_courant();

                                            //int[][] floyd_nouveau = remplir_matrice_floyd();
                                            int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_pont_composante.i][un_pont_composante.j].numero_floyd);



                                            tmpit.add(un_pont_composante);

                                                //composante_joueur_courant_it.add(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                            nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                       //composante_joueur_courant.remove(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                            // if(tmpit.hasNext()){
                                                 tmpit.previous();
                                                 tmpit.remove();
                                           // }
                                            f.matrice[un_pont_composante.i][un_pont_composante.j].etat = 0;

                                            if(nb_coups_tmp <= nb_coups_min){
                                                nb_coups_min = nb_coups_tmp;
                                                case_preferable = un_pont_composante;
                                                Listrandom.add(case_preferable);

                                            }
                                            /*if(nb_coups_tmp == nb_coups_min){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_pont_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }
                            
                            
                            while(composante_joueur_courant_it.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_joueur_courant23 = composante_joueur_courant_it.next();
                                if(!est_element_bord(case_libre_composante_joueur_courant23)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant23, couleur_joueur_courant()))){


                             ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_joueur_courant23);
                                    ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                    while(successeurs_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_successeur_composante = successeurs_composante.next();
                                        if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false
                                                && !est_caseM_au_milieu_pont_du_chemin_principal_ia(un_successeur_composante)){
                                            marquage[un_successeur_composante.i][un_successeur_composante.j]=true;

                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();


                                        //int [][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_successeur_composante.i][un_successeur_composante.j].numero_floyd);

                                         tmpit.add(un_successeur_composante);
                                        //composante_joueur_courant.add(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                        nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                       // composante_joueur_courant.remove(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                       // if(tmpit.hasNext()){
                                             tmpit.previous();
                                            tmpit.remove();
                                         //   }

                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                            if(nb_coups_tmp < nb_coups_min){
                                                nb_coups_min = nb_coups_tmp;
                                                case_preferable = un_successeur_composante;
                                                Listrandom.add(case_preferable);
                                                System.out.println("cases preferable offensive :  i :"+case_preferable.i+"  j:"+case_preferable.j+"  nb coup :"+nb_coups_tmp);
                                            }
                                         /*   if(nb_coups_tmp == nb_coups_min){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }
                            caseM xd;
                            int s=Listrandom.size();
                            int z;
                            Random rand=new Random(1000);
                            z=rand.nextInt(s);
                            xd=Listrandom.get(z);


                            while(xd.etat!=0){
                                s=Listrandom.size();                               
                                z=rand.nextInt(s);
                               xd=Listrandom.get(z);
                            }
                            if(xd.etat==0)
                                return xd;
                            else
                                return null;

                        }

                    }else{
//mode defensif:
                        System.out.println("--Mode Defensif");
                        if(composante_humain != null){
                            caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                            if(res != null){
                                Listrandom.add(res);
                            }
                        }
                        if(composante_ia==null){
                            ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                            int nb_coups_une_boule;
                            int nb_coups_blanc_potentiel = 1024;
                            caseM case_mem2 = null;
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    if(f.matrice[i][j].etat == 0){

                                        f.matrice[i][j].etat = couleur_joueur_courant();
                                        //int[][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                        liste_une_boule.clear();
                                        liste_une_boule.add(f.matrice[i][j]);
                                        nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                        f.matrice[i][j].etat = 0;

                                        if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                            nb_coups_blanc_potentiel = nb_coups_une_boule;
                                            case_mem2 = f.matrice[i][j];
                                        }
                                      /*  if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                            Random r = new Random();
                                            int choix = r.nextInt(2);
                                            if(choix == 1){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                        }*/

                                    }
                                }
                            }
                            return case_mem2;
                        }else{
                            caseM case_preferable = null;
                            ListIterator<caseM> composante_adverse = composante_humain.listIterator();
                            boolean[][]marquage = new boolean[f.N][f.N];
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    marquage[i][j] = false;
                                }
                            }
                            int diff_mem = difference; //reinit
                            while(composante_adverse.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_adverse = composante_adverse.next();
                                if(!est_voisin_selon_joueur_courant_objectif(case_libre_composante_adverse) && !est_joker1_selon_bords_selon_couleur(case_libre_composante_adverse,couleur_joueur_adverse())){
                                    ArrayList<caseM> ponts_composante_adverse1 = pont_caseM_libre(case_libre_composante_adverse);
                                    ListIterator<caseM> ponts_composante_adverse = ponts_composante_adverse1.listIterator();
                                    while(ponts_composante_adverse.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_pont_composante_adverse = ponts_composante_adverse.next();
                                        if(marquage[un_pont_composante_adverse.i][un_pont_composante_adverse.j]==false){
                                            marquage[un_pont_composante_adverse.i][un_pont_composante_adverse.j]=true;

                                            f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j].etat = couleur_joueur_courant();
                                            //f.joueur_courant = joueur_num_adv;
                                            int nb1 = nbcoup_restantJ1;
                                            int nb2 = nbcoup_restantJ2;
                                            if(f.joueur_courant==1){
                                                nbcoup_restantJ1=500;
                                                nbcoup_restantJ2=500;
                                            }else{
                                                nbcoup_restantJ2=500;
                                                nbcoup_restantJ1=500;
                                            }
                                            int difference_minimax;
                                            if(f.joueur_courant==1){
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j], objectif_bordJ2);
                                            }else{
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j], objectif_bordJ1);
                                            }
                                            //f.joueur_courant = joueur_num_cur;
                                            nbcoup_restantJ1 = nb1;
                                            nbcoup_restantJ2 = nb2;
                                            f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j].etat = 0;

                                            if(difference_minimax > diff_mem || est_joker1_selon_bords_selon_couleur(un_pont_composante_adverse, couleur_joueur_adverse())){
                                                diff_mem = difference_minimax;
                                                case_preferable = un_pont_composante_adverse;
                                            }
                                           /* if(difference_minimax == diff_mem){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    diff_mem = difference_minimax;
                                                    case_preferable = un_pont_composante_adverse;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }
                            if(case_preferable!=null){
                                System.out.print("diff avant minimax = "+difference+", diff apres minimax = "+diff_mem);
                                Listrandom.add(case_preferable);
                            }

                            composante_adverse = composante_humain.listIterator();
                            System.out.println("passage au seccesseurs");
                            while(composante_adverse.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_adverse = composante_adverse.next();
                                if(!est_voisin_selon_joueur_courant_objectif(case_libre_composante_adverse) && !est_joker1_selon_bords_selon_couleur(case_libre_composante_adverse,couleur_joueur_adverse())){
                                    ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_adverse);
                                    ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                    while(successeurs_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_successeur_composante = successeurs_composante.next();
                                        if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false){
                                            marquage[un_successeur_composante.i][un_successeur_composante.j]=true;
                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();

                                            int nb1 = nbcoup_restantJ1;
                                            int nb2 = nbcoup_restantJ2;
                                            if(f.joueur_courant==1){
                                                nbcoup_restantJ1=500;
                                                nbcoup_restantJ2=500;
                                            }else{
                                                nbcoup_restantJ2=500;
                                                nbcoup_restantJ1=500;
                                            }
                                            int difference_minimax;
                                            if(f.joueur_courant==1){
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_successeur_composante.i][un_successeur_composante.j], objectif_bordJ2);
                                            }else{
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_successeur_composante.i][un_successeur_composante.j], objectif_bordJ1);
                                            }
                                            nbcoup_restantJ1 = nb1;
                                            nbcoup_restantJ2 = nb2;
                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                            if( difference_minimax >= diff_mem || est_joker1_selon_bords_selon_couleur(un_successeur_composante, couleur_joueur_adverse())){
                                                diff_mem = difference_minimax;
                                                case_preferable = un_successeur_composante;
                                            }
                                        /*    if(difference_minimax == diff_mem){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    diff_mem = difference_minimax;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }

                            if(case_preferable!=null){
                                System.out.print("diff avant minimax = "+difference+", diff apres minimax = "+diff_mem);
                                Listrandom.add(case_preferable);
                            }

/*
                            caseM anti_pont_a_completer = completer_pont_ennemi_laison_autre_composante();
                            if(anti_pont_a_completer!=null){
                                System.out.println("Bob complete un anti pont2!");
                                Listrandom.add(anti_pont_a_completer);
                            }

                            anti_pont_a_completer = completer_anti_pont_ennemi_noir_a_cote();
                            if(anti_pont_a_completer!=null){
                                System.out.println("Bob complete un anti pont1!");
                                Listrandom.add(anti_pont_a_completer);
                            }

                            anti_pont_a_completer = completer_anti_pont_ennemi_tout_conditions();
                            if(anti_pont_a_completer!=null){
                                System.out.println("Bob complete un anti pont!");
                               Listrandom.add(anti_pont_a_completer);
                            }
*/
        //si echec -> comme pour le mode offensif:
                            System.out.println("echec->comme pour le mode offensif");
                            /******************************************/
                            if(composante_ia==null){
                                ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                                int nb_coups_une_boule;
                                int nb_coups_blanc_potentiel = 1024;
                                caseM case_mem2 = null;
                                for(int i=0; i<f.N; i++){
                                    for(int j=0; j<f.N; j++){
                                        if(f.matrice[i][j].etat == 0){

                                            f.matrice[i][j].etat = couleur_joueur_courant();
                                            //int [][] floyd_nouveau = remplir_matrice_floyd();
                                            int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                            liste_une_boule.clear();
                                            liste_une_boule.add(f.matrice[i][j]);
                                            nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                            f.matrice[i][j].etat = 0;

                                            if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                          /*  if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                    case_mem2 = f.matrice[i][j];
                                                }
                                            }*/
                                        }
                                    }
                                }

                                Listrandom.add(case_mem2);
                            }else{
                        if(composante_humain != null){
                            caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                            if(res != null){
                                Listrandom.add(res);
                            }
                        }
                                diff_mem = difference; //reinit
                                for(int i=0; i<f.N; i++){
                                    for(int j=0; j<f.N; j++){
                                        marquage[i][j] = false;
                                    }
                                }
                                int nb_coups_tmp;
                                int nb_coups_min = 1024;
                                case_preferable = null;
                                ArrayList<caseM> tmp= (ArrayList<caseM>) composante_ia.clone();
                                ListIterator<caseM> tmpit=tmp.listIterator();
                                ListIterator<caseM> composante_joueur_courant_it = composante_ia.listIterator();
                                while(composante_joueur_courant_it.hasNext()){
                                    //pour chaque case du chemin principal de l'adversaire:
                                    caseM case_libre_composante_joueur_courant34 =
                                            composante_joueur_courant_it.next();
                                    if(!est_element_bord(case_libre_composante_joueur_courant34)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant34, couleur_joueur_courant()))){


                                        ArrayList<caseM> ponts_composante1 = pont_caseM_libre(case_libre_composante_joueur_courant34);
                                        ListIterator<caseM> ponts_composante = ponts_composante1.listIterator();
                                        while(ponts_composante.hasNext()){
                                            //pour chaque pont de cette case:
                                            caseM un_pont_composante = ponts_composante.next();
                                            if(marquage[un_pont_composante.i][un_pont_composante.j]==false){
                                                marquage[un_pont_composante.i][un_pont_composante.j]=true;

                                                f.matrice[un_pont_composante.i][un_pont_composante.j].etat = couleur_joueur_courant();

                                                //int [][] floyd_nouveau = remplir_matrice_floyd();
                                                int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_pont_composante.i][un_pont_composante.j].numero_floyd);


                                                tmpit.add(un_pont_composante);

                                               // composante_joueur_courant.add(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                                nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), tmp, floyd_final3);
                                               // composante_joueur_courant.remove(f.matrice[un_pont_composante.i][un_pont_composante.j]);


                                                f.matrice[un_pont_composante.i][un_pont_composante.j].etat = 0;
                                                tmpit.previous();
                                                tmpit.remove();
                                                if(nb_coups_tmp < nb_coups_min){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_pont_composante;
                                                }
                                               /* if(nb_coups_tmp == nb_coups_min){
                                                    Random r = new Random();
                                                    int choix = r.nextInt(2);
                                                    if(choix == 1){
                                                        nb_coups_min = nb_coups_tmp;
                                                        case_preferable = un_pont_composante;
                                                    }
                                                }*/
                                            }
                                        }

                                        ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_joueur_courant34);
                                        ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                        while(successeurs_composante.hasNext()){
                                            //pour chaque pont de cette case:
                                            caseM un_successeur_composante = successeurs_composante.next();
                                            if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false
                                                && !est_caseM_au_milieu_pont_du_chemin_principal_ia(un_successeur_composante)){

                                                marquage[un_successeur_composante.i][un_successeur_composante.j]=true;

                                                f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();

                                                //int [][] floyd_nouveau = remplir_matrice_floyd();
                                                int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_successeur_composante.i][un_successeur_composante.j].numero_floyd);


                                                tmpit.add(un_successeur_composante);

                                            //composante_joueur_courant.add(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                            nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                           // composante_joueur_courant.remove(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                            tmpit.previous();
                                            tmpit.remove();

                                                f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                                if(nb_coups_tmp < nb_coups_min){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            /*    if(nb_coups_tmp == nb_coups_min){
                                                    Random r = new Random();
                                                    int choix = r.nextInt(2);
                                                    if(choix == 1){
                                                        nb_coups_min = nb_coups_tmp;
                                                        case_preferable = un_successeur_composante;
                                                    }
                                                }*/
                                            }
                                        }
                                    }
                                }
                               


                                System.out.println("Joueur decide à jouer la case préférable--");
                                if(case_preferable==null) {
                                    ArrayList<caseM> L = list_boules(f.matrice, 0);
                                    Random r = new Random();
                                    int i = r.nextInt(L.size());
                                    System.out.println("Je ne joue pas cette fois ci");
                                    Listrandom.add(case_preferable);
                                }
                               
                            }
                            /******************************************/
                        }
                         if(!Listrandom.isEmpty()){

                                    caseM xd = null;
                                    int s = Listrandom.size();
                                    int z;
                                    Random rand = new Random(1000);
                                    z = rand.nextInt(s);
                                    xd=Listrandom.get(z);
                                    while(xd!=null && xd.etat!=0){

                                       s = Listrandom.size();
                                       z = rand.nextInt(s);
                                       xd =Listrandom.get(z);
                                    }
                                    if(xd.etat==0)
                                        return xd;
                                    else
                                        return null;

                         }
                    }


        }
        return null;
    }
   
   private caseM minimaxOrdi2(){
       System.out.println("JOUEUR COURANT ICI :"+couleur_joueur_courant());
       if(Floyd_coup_courant==null){
           int [][] floyd_new=remplir_matrice_floyd();
           Floyd_coup_courant=floyd_warshall(floyd_new);

       }
        System.out.println("Objectif J1 : "+objectif_bordJ1[0]+" , "+objectif_bordJ1[1]+"  , ici le 3e (si 3e) "+objectif_bordJ1[2]);
        System.out.println("Objectif J2 : "+objectif_bordJ2[0]+" , "+objectif_bordJ2[1]+"  , ici le 3e (si 3e) "+objectif_bordJ2[2]);
        if(!f.partie_commencee){
            caseM case_a_jouer = analyser_debut();
            //avec la fonction -> mise a jour de joueur_press_button
            if(!joueur_press_button){
                //si on n'appuie pas sur le bouton:
                return case_a_jouer;
            }else{
                //si on appuie sur le bouton:
                f.num_coup_blanc = f.nbr_coups_joue;
                f.partie_commencee = true;
                if(f.joueur_courant == 1)
                    f.joueurDeux.couleur = 2;
                else
                    f.joueurUn.couleur = 2;

                f.choixnoir.setEnabled(false);
                f.img_choisir_indispo = new ImageIcon("images/noir_indispo.png");
                f.choixnoir.setDisabledIcon(f.img_choisir_indispo);
                f.aire.repaint();
                return null;
            }
        }else{
            int nb_joueur_adverse = 1;
            if(f.joueur_courant == 1){
                nb_joueur_adverse=2;
            }
            if(estVictoire(nb_joueur_adverse)){
                caseM resulta1 = completer_victoire(composante_humain, nb_joueur_adverse);
                return resulta1;
            }
            // Si partie est commencée:
//
            if(composante_ia!=null){

                if(estVictoire_composante_ponts(composante_ia, couleur_joueur_courant())){
                    // S'il existe un chemin qui conduit à la VICTOIRE:
                    caseM completer_pb_casse = caseM_completer_chemin_principal_casse(composante_ia, couleur_joueur_courant());
                    if(completer_pb_casse != null){
                            f.matrice[completer_pb_casse.i][completer_pb_casse.j].etat=0;
                            return completer_pb_casse;
                    }
                       // return completer_victoire(f.matrice, couleur_joueur_courant(), L);
                        return completer_victoire(composante_ia,f.joueur_courant);
                    
                }
            }
                // S'il n'existe pas de chemin qui conduit à la VICTOIRE:

            if(composante_ia != null & composante_humain != null){
                System.out.println("composante_ia:--"+composante_ia.size());
                System.out.println("composante_humain:--"+composante_humain.size());
            }

                caseM completer = null;

                if(composante_ia!=null){
                    completer = caseM_completer_chemin_principal_casse(composante_ia, couleur_joueur_courant());
                }
                if(completer != null){
                    //Si il existe un pont cassé dans un chemin principal:

                     f.matrice[completer.i][completer.j].etat=couleur_joueur_courant();
                        if(!estDefaite(f.joueur_courant) || estVictoire(f.joueur_courant)){
                            f.matrice[completer.i][completer.j].etat=0;
                            return completer;
                    }
                        f.matrice[completer.i][completer.j].etat=0;
                }

                        //Cas général:

                    int difference; //difference entre le nb de coups de l'adversaire et celui du joueur courant
                    if(f.joueur_courant==1){

                        difference = nbcoup_restantJ2 - nbcoup_restantJ1;

                    }else{
                        difference = nbcoup_restantJ1 - nbcoup_restantJ2;

                    }
                    System.out.println("DIFF = "+difference+", nbcoup_restantJ2 = "+nbcoup_restantJ2+", nbcoup_restantJ1 = "+nbcoup_restantJ1);
                    //  System.out.println("JOUEUR NUM COURANT "+joueur_num_cur+" ADV"+joueur_num_adv);

                    if(difference > 0){

 //mode offensif:

                        System.out.println("--Mode Offensif");
                     //   System.out.println("CJC"+couleur_joueur_courant());
                        if(composante_ia==null){
                            ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                            int nb_coups_une_boule;
                            int nb_coups_blanc_potentiel = 1024;
                            caseM case_mem2 = null;
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    if(f.matrice[i][j].etat == 0){

                                        f.matrice[i][j].etat = couleur_joueur_courant();
                                       // int[][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                        liste_une_boule.clear();
                                        liste_une_boule.add(f.matrice[i][j]);
                                        nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                        f.matrice[i][j].etat = 0;


                                        if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                            nb_coups_blanc_potentiel = nb_coups_une_boule;
                                            case_mem2 = f.matrice[i][j];
                                        }
                                        /*if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                            Random r = new Random();
                                            int choix = r.nextInt(2);
                                            if(choix == 1){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                        }*/
                                    }
                                }
                            }
                            return case_mem2;

                        }else{
                            if(composante_humain != null){
                                caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                                if(res != null){
                                    return res;
                                }
                            }

                            caseM anti_pont_a_completer = completer_anti_pont_ennemi_noir_a_cote();
                            if(anti_pont_a_completer!=null){
                                System.out.println("Bob complete un anti pont!");
                                return anti_pont_a_completer;
                            }


                            int diff_mem = difference; //reinit
                            boolean[][]marquage = new boolean[f.N][f.N];
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    marquage[i][j] = false;
                                }
                            }
                            int nb_coups_tmp;
                            int nb_coups_min = 1024;
                            caseM case_preferable = null;

                            ListIterator<caseM> composante_joueur_courant_it = composante_ia.listIterator();

                            ArrayList<caseM> tmp= (ArrayList<caseM>) composante_ia.clone();
                            ListIterator<caseM> tmpit=tmp.listIterator();
                            while(composante_joueur_courant_it.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_joueur_courant23 = composante_joueur_courant_it.next();
                                if(!est_element_bord(case_libre_composante_joueur_courant23)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant23, couleur_joueur_courant()))){


                                    ArrayList<caseM> ponts_composante1 = pont_caseM_libre(case_libre_composante_joueur_courant23);
                                    ListIterator<caseM> ponts_composante = ponts_composante1.listIterator();
                                    while(ponts_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_pont_composante = ponts_composante.next();
                                        if(marquage[un_pont_composante.i][un_pont_composante.j]==false){
                                            marquage[un_pont_composante.i][un_pont_composante.j]=true;

                                            f.matrice[un_pont_composante.i][un_pont_composante.j].etat = couleur_joueur_courant();

                                            //int[][] floyd_nouveau = remplir_matrice_floyd();
                                            int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_pont_composante.i][un_pont_composante.j].numero_floyd);



                                            tmpit.add(un_pont_composante);

                                                //composante_joueur_courant_it.add(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                            nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                       //composante_joueur_courant.remove(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                            // if(tmpit.hasNext()){
                                                 tmpit.previous();
                                                 tmpit.remove();
                                           // }
                                            f.matrice[un_pont_composante.i][un_pont_composante.j].etat = 0;

                                            if(nb_coups_tmp < nb_coups_min){
                                                nb_coups_min = nb_coups_tmp;
                                                case_preferable = un_pont_composante;

                                            }
                                            /*if(nb_coups_tmp == nb_coups_min){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_pont_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }
                            if(case_preferable != null){
                            return case_preferable;
                            }
                            while(composante_joueur_courant_it.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_joueur_courant23 = composante_joueur_courant_it.next();
                                if(!est_element_bord(case_libre_composante_joueur_courant23)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant23, couleur_joueur_courant()))){


                             ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_joueur_courant23);
                                    ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                    while(successeurs_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_successeur_composante = successeurs_composante.next();
                                        if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false
                                                && !est_caseM_au_milieu_pont_du_chemin_principal_ia(un_successeur_composante)){
                                            marquage[un_successeur_composante.i][un_successeur_composante.j]=true;

                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();


                                        //int [][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_successeur_composante.i][un_successeur_composante.j].numero_floyd);

                                         tmpit.add(un_successeur_composante);
                                        //composante_joueur_courant.add(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                        nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                       // composante_joueur_courant.remove(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                       // if(tmpit.hasNext()){
                                             tmpit.previous();
                                            tmpit.remove();
                                         //   }

                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                            if(nb_coups_tmp < nb_coups_min){
                                                nb_coups_min = nb_coups_tmp;
                                                case_preferable = un_successeur_composante;
                                                System.out.println("cases preferable offensive :  i :"+case_preferable.i+"  j:"+case_preferable.j+"  nb coup :"+nb_coups_tmp);
                                            }
                                         /*   if(nb_coups_tmp == nb_coups_min){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }

                            
                            System.out.println("Joueur decide Ã  jouer la case prÃ©fÃ©rable--");
                            return case_preferable;
                        }

                    }else{
//mode defensif:
                        System.out.println("--Mode Defensif");
                        if(composante_humain != null){
                            caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                            if(res != null){
                                return res;
                            }
                        }
                        if(composante_ia==null){
                            ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                            int nb_coups_une_boule;
                            int nb_coups_blanc_potentiel = 1024;
                            caseM case_mem2 = null;
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    if(f.matrice[i][j].etat == 0){

                                        f.matrice[i][j].etat = couleur_joueur_courant();
                                        //int[][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                        liste_une_boule.clear();
                                        liste_une_boule.add(f.matrice[i][j]);
                                        nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                        f.matrice[i][j].etat = 0;

                                        if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                            nb_coups_blanc_potentiel = nb_coups_une_boule;
                                            case_mem2 = f.matrice[i][j];
                                        }
                                      /*  if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                            Random r = new Random();
                                            int choix = r.nextInt(2);
                                            if(choix == 1){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                        }*/

                                    }
                                }
                            }
                            return case_mem2;
                        }else{
                            caseM case_preferable = null;
                            ListIterator<caseM> composante_adverse = composante_humain.listIterator();
                            boolean[][]marquage = new boolean[f.N][f.N];
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    marquage[i][j] = false;
                                }
                            }
                            int diff_mem = difference; //reinit
                            while(composante_adverse.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_adverse = composante_adverse.next();
                                if(!est_voisin_selon_joueur_courant_objectif(case_libre_composante_adverse) && !est_joker1_selon_bords_selon_couleur(case_libre_composante_adverse,couleur_joueur_adverse())){
                                    ArrayList<caseM> ponts_composante_adverse1 = pont_caseM_libre(case_libre_composante_adverse);
                                    ListIterator<caseM> ponts_composante_adverse = ponts_composante_adverse1.listIterator();
                                    while(ponts_composante_adverse.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_pont_composante_adverse = ponts_composante_adverse.next();
                                        if(marquage[un_pont_composante_adverse.i][un_pont_composante_adverse.j]==false){
                                            marquage[un_pont_composante_adverse.i][un_pont_composante_adverse.j]=true;

                                            f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j].etat = couleur_joueur_courant();
                                            //f.joueur_courant = joueur_num_adv;
                                            int nb1 = nbcoup_restantJ1;
                                            int nb2 = nbcoup_restantJ2;
                                            if(f.joueur_courant==1){
                                                nbcoup_restantJ1=500;
                                                nbcoup_restantJ2=500;
                                            }else{
                                                nbcoup_restantJ2=500;
                                                nbcoup_restantJ1=500;
                                            }
                                            int difference_minimax;
                                            if(f.joueur_courant==1){
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j], objectif_bordJ2);
                                            }else{
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j], objectif_bordJ1);
                                            }
                                            //f.joueur_courant = joueur_num_cur;
                                            nbcoup_restantJ1 = nb1;
                                            nbcoup_restantJ2 = nb2;
                                            f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j].etat = 0;

                                            if(difference_minimax > diff_mem || est_joker1_selon_bords_selon_couleur(un_pont_composante_adverse, couleur_joueur_adverse())){
                                                diff_mem = difference_minimax;
                                                case_preferable = un_pont_composante_adverse;
                                            }
                                           /* if(difference_minimax == diff_mem){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    diff_mem = difference_minimax;
                                                    case_preferable = un_pont_composante_adverse;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }
                            if(case_preferable!=null){
                                System.out.print("diff avant minimax = "+difference+", diff apres minimax = "+diff_mem);
                                return case_preferable;
                            }

                            composante_adverse = composante_humain.listIterator();
                            System.out.println("passage au seccesseurs");
                            while(composante_adverse.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_adverse = composante_adverse.next();
                                if(!est_voisin_selon_joueur_courant_objectif(case_libre_composante_adverse) && !est_joker1_selon_bords_selon_couleur(case_libre_composante_adverse,couleur_joueur_adverse())){
                                    ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_adverse);
                                    ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                    while(successeurs_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_successeur_composante = successeurs_composante.next();
                                        if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false){
                                            marquage[un_successeur_composante.i][un_successeur_composante.j]=true;

                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();

                                            int nb1 = nbcoup_restantJ1;
                                            int nb2 = nbcoup_restantJ2;
                                            if(f.joueur_courant==1){
                                                nbcoup_restantJ1=500;
                                                nbcoup_restantJ2=500;
                                            }else{
                                                nbcoup_restantJ2=500;
                                                nbcoup_restantJ1=500;
                                            }
                                            int difference_minimax;
                                            if(f.joueur_courant==1){
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_successeur_composante.i][un_successeur_composante.j], objectif_bordJ2);
                                            }else{
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_successeur_composante.i][un_successeur_composante.j], objectif_bordJ1);
                                            }
                                            nbcoup_restantJ1 = nb1;
                                            nbcoup_restantJ2 = nb2;
                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                            if( difference_minimax >= diff_mem || est_joker1_selon_bords_selon_couleur(un_successeur_composante, couleur_joueur_adverse())){
                                                diff_mem = difference_minimax;
                                                case_preferable = un_successeur_composante;
                                            }
                                        /*    if(difference_minimax == diff_mem){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    diff_mem = difference_minimax;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }

                            if(case_preferable!=null){
                                System.out.print("diff avant minimax = "+difference+", diff apres minimax = "+diff_mem);
                                return case_preferable;
                            }

/*
                            caseM anti_pont_a_completer = completer_pont_ennemi_laison_autre_composante();
                            if(anti_pont_a_completer!=null){
                                System.out.println("Bob complete un anti pont2!");
                                return anti_pont_a_completer;
                            }

                            anti_pont_a_completer = completer_anti_pont_ennemi_noir_a_cote();
                            if(anti_pont_a_completer!=null){
                                System.out.println("Bob complete un anti pont1!");
                                return anti_pont_a_completer;
                            }

                            anti_pont_a_completer = completer_anti_pont_ennemi_tout_conditions();
                            if(anti_pont_a_completer!=null){
                                System.out.println("Bob complete un anti pont!");
                                return anti_pont_a_completer;
                            }
*/
        //si echec -> comme pour le mode offensif:
                            System.out.println("echec->comme pour le mode offensif");
                            /******************************************/
                            if(composante_ia==null){
                                ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                                int nb_coups_une_boule;
                                int nb_coups_blanc_potentiel = 1024;
                                caseM case_mem2 = null;
                                for(int i=0; i<f.N; i++){
                                    for(int j=0; j<f.N; j++){
                                        if(f.matrice[i][j].etat == 0){

                                            f.matrice[i][j].etat = couleur_joueur_courant();
                                            //int [][] floyd_nouveau = remplir_matrice_floyd();
                                            int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                            liste_une_boule.clear();
                                            liste_une_boule.add(f.matrice[i][j]);
                                            nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                            f.matrice[i][j].etat = 0;

                                            if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                          /*  if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                    case_mem2 = f.matrice[i][j];
                                                }
                                            }*/
                                        }
                                    }
                                }

                                return case_mem2;
                            }else{
                        if(composante_humain != null){
                            caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                            if(res != null){
                                return res;
                            }
                        }
                                diff_mem = difference; //reinit
                                for(int i=0; i<f.N; i++){
                                    for(int j=0; j<f.N; j++){
                                        marquage[i][j] = false;
                                    }
                                }
                                int nb_coups_tmp;
                                int nb_coups_min = 1024;
                                case_preferable = null;
                                ArrayList<caseM> tmp= (ArrayList<caseM>) composante_ia.clone();
                                ListIterator<caseM> tmpit=tmp.listIterator();
                                ListIterator<caseM> composante_joueur_courant_it = composante_ia.listIterator();
                                while(composante_joueur_courant_it.hasNext()){
                                    //pour chaque case du chemin principal de l'adversaire:
                                    caseM case_libre_composante_joueur_courant34 =
                                            composante_joueur_courant_it.next();
                                    if(!est_element_bord(case_libre_composante_joueur_courant34)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant34, couleur_joueur_courant()))){


                                        ArrayList<caseM> ponts_composante1 = pont_caseM_libre(case_libre_composante_joueur_courant34);
                                        ListIterator<caseM> ponts_composante = ponts_composante1.listIterator();
                                        while(ponts_composante.hasNext()){
                                            //pour chaque pont de cette case:
                                            caseM un_pont_composante = ponts_composante.next();
                                            if(marquage[un_pont_composante.i][un_pont_composante.j]==false){
                                                marquage[un_pont_composante.i][un_pont_composante.j]=true;

                                                f.matrice[un_pont_composante.i][un_pont_composante.j].etat = couleur_joueur_courant();

                                                //int [][] floyd_nouveau = remplir_matrice_floyd();
                                                int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_pont_composante.i][un_pont_composante.j].numero_floyd);


                                                tmpit.add(un_pont_composante);

                                               // composante_joueur_courant.add(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                                nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), tmp, floyd_final3);
                                               // composante_joueur_courant.remove(f.matrice[un_pont_composante.i][un_pont_composante.j]);


                                                f.matrice[un_pont_composante.i][un_pont_composante.j].etat = 0;
                                                tmpit.previous();
                                                tmpit.remove();
                                                if(nb_coups_tmp < nb_coups_min){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_pont_composante;
                                                }
                                               /* if(nb_coups_tmp == nb_coups_min){
                                                    Random r = new Random();
                                                    int choix = r.nextInt(2);
                                                    if(choix == 1){
                                                        nb_coups_min = nb_coups_tmp;
                                                        case_preferable = un_pont_composante;
                                                    }
                                                }*/
                                            }
                                        }

                                        ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_joueur_courant34);
                                        ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                        while(successeurs_composante.hasNext()){
                                            //pour chaque pont de cette case:
                                            caseM un_successeur_composante = successeurs_composante.next();
                                            if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false
                                                && !est_caseM_au_milieu_pont_du_chemin_principal_ia(un_successeur_composante)){

                                                marquage[un_successeur_composante.i][un_successeur_composante.j]=true;

                                                f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();

                                                //int [][] floyd_nouveau = remplir_matrice_floyd();
                                                int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_successeur_composante.i][un_successeur_composante.j].numero_floyd);


                                                tmpit.add(un_successeur_composante);

                                            //composante_joueur_courant.add(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                            nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                           // composante_joueur_courant.remove(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                            tmpit.previous();
                                            tmpit.remove();

                                                f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                                if(nb_coups_tmp < nb_coups_min){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            /*    if(nb_coups_tmp == nb_coups_min){
                                                    Random r = new Random();
                                                    int choix = r.nextInt(2);
                                                    if(choix == 1){
                                                        nb_coups_min = nb_coups_tmp;
                                                        case_preferable = un_successeur_composante;
                                                    }
                                                }*/
                                            }
                                        }
                                    }
                                }


                                System.out.println("Joueur decide à jouer la case préférable--");
                                if(case_preferable==null) {
                                    ArrayList<caseM> L = list_boules(f.matrice, 0);
                                    Random r = new Random();
                                    int i = r.nextInt(L.size());
                                    System.out.println("Je ne joue pas cette fois ci");
                                    return L.get(i);
                                }
                                return case_preferable;
                            }
                            /******************************************/
                        }
                    }


        }
    }
   private caseM minimaxOrdi2_proposer_coup(){
       System.out.println("JOUEUR COURANT ICI :"+couleur_joueur_courant());
       if(Floyd_coup_courant==null){
           int [][] floyd_new=remplir_matrice_floyd();
           Floyd_coup_courant=floyd_warshall(floyd_new);

       }
        System.out.println("Objectif J1 : "+objectif_bordJ1[0]+" , "+objectif_bordJ1[1]+"  , ici le 3e (si 3e)"+objectif_bordJ1[2]);
        System.out.println("Objectif J2 : "+objectif_bordJ2[0]+" , "+objectif_bordJ2[1]+"  , ici le 3e (si 3e)"+objectif_bordJ2[2]);
        if(!f.partie_commencee){
            caseM case_a_jouer = analyser_debut();
            //avec la fonction -> mise a jour de joueur_press_button
            if(!joueur_press_button){
                //si on n'appuie pas sur le bouton:
           //     System.out.println("Joueur decide à ne pas appuyer sur le bouton");
            //    System.out.println("Joueur clique sur ("+case_a_jouer.i+","+case_a_jouer.j+")");
                return case_a_jouer;
            }else{
                //si on appuie sur le bouton:
                return new caseM(999, 999, 999, 999, null);
            }
        }else{
            int nb_joueur_adverse = 1;
            if(f.joueur_courant == 1){
                nb_joueur_adverse=2;
            }
            if(estVictoire(nb_joueur_adverse)){
           //     System.out.println("Detection victoire humaine");
                caseM resulta1 = completer_victoire(composante_humain, nb_joueur_adverse);
                return resulta1;
            }
            // Si partie est commencée:
//
            if(composante_ia!=null){

              //  System.out.println("nb de caseM dans composante_ia = "+composante_ia.size());
                if(estVictoire_composante_ponts(composante_ia, couleur_joueur_courant())){
                    // S'il existe un chemin qui conduit à la VICTOIRE:
                    caseM completer_pb_casse = caseM_completer_chemin_principal_casse(composante_ia, couleur_joueur_courant());
                    if(completer_pb_casse != null){
                 //           System.out.println("Bob complete le chemin principal cassé");
                            f.matrice[completer_pb_casse.i][completer_pb_casse.j].etat=0;
                            return completer_pb_casse;
                    }
                       // return completer_victoire(f.matrice, couleur_joueur_courant(), L);
                        return completer_victoire(composante_ia,f.joueur_courant);

                }
            }
                // S'il n'existe pas de chemin qui conduit à la VICTOIRE:

            if(composante_ia != null & composante_humain != null){
                System.out.println("composante_ia:--");
                affichage_list(composante_ia);
                System.out.println("--composante_ia fin;");
                System.out.println("composante_humain:--");
                affichage_list(composante_humain);
                System.out.println("--composante_humain fin;");
            }

                caseM completer = null;

                if(composante_ia!=null){
                    completer = caseM_completer_chemin_principal_casse(composante_ia, couleur_joueur_courant());
                }
                if(completer != null){
                    //Si il existe un pont cassé dans un chemin principal:
                 //   System.out.println("Joueur decide de completer le chemin principal cassé");

                     f.matrice[completer.i][completer.j].etat=couleur_joueur_courant();
                        if(!estDefaite(f.joueur_courant) || estVictoire(f.joueur_courant)){
                      //      System.out.println("Bob complete le chemin principal cassé");
                            f.matrice[completer.i][completer.j].etat=0;
                            return completer;
                        }
                     //   System.out.println("Bob sens qu'il va perdre s'il colmplete lechemin cassé");
                        f.matrice[completer.i][completer.j].etat=0;
                }

                        //Cas général:
                    //    System.out.println("Execution de minimax:");

                    int difference; //difference entre le nb de coups de l'adversaire et celui du joueur courant
                    if(f.joueur_courant==1){

                        difference = nbcoup_restantJ2 - nbcoup_restantJ1;

                    }else{
                        difference = nbcoup_restantJ1 - nbcoup_restantJ2;

                    }
                 //   System.out.println("DIFF = "+difference+", nbcoup_restantJ2 = "+nbcoup_restantJ2+", nbcoup_restantJ1 = "+nbcoup_restantJ1);
                    //  System.out.println("JOUEUR NUM COURANT "+joueur_num_cur+" ADV"+joueur_num_adv);

                    if(difference > 0){

 //mode offensif:

                        System.out.println("--Mode Offensif");
                     //   System.out.println("CJC"+couleur_joueur_courant());
                        if(composante_ia==null){
                            ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                            int nb_coups_une_boule;
                            int nb_coups_blanc_potentiel = 1024;
                            caseM case_mem2 = null;
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    if(f.matrice[i][j].etat == 0){

                                        f.matrice[i][j].etat = couleur_joueur_courant();
                                       // int[][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                        liste_une_boule.clear();
                                        liste_une_boule.add(f.matrice[i][j]);
                                        nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                        f.matrice[i][j].etat = 0;


                                        if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                            nb_coups_blanc_potentiel = nb_coups_une_boule;
                                            case_mem2 = f.matrice[i][j];
                                        }
                                        /*if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                            Random r = new Random();
                                            int choix = r.nextInt(2);
                                            if(choix == 1){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                        }*/
                                    }
                                }
                            }
                            return case_mem2;

                        }else{
                            if(composante_humain != null){
                                caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                                if(res != null){
                                    return res;
                                }
                            }


                            int diff_mem = difference; //reinit
                            boolean[][]marquage = new boolean[f.N][f.N];
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    marquage[i][j] = false;
                                }
                            }
                            int nb_coups_tmp;
                            int nb_coups_min = 1024;
                            caseM case_preferable = null;

                            ListIterator<caseM> composante_joueur_courant_it = composante_ia.listIterator();

                            ArrayList<caseM> tmp= (ArrayList<caseM>) composante_ia.clone();
                            ListIterator<caseM> tmpit=tmp.listIterator();
                            while(composante_joueur_courant_it.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_joueur_courant23 = composante_joueur_courant_it.next();
                                if(!est_element_bord(case_libre_composante_joueur_courant23)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant23, couleur_joueur_courant()))){


                                    ArrayList<caseM> ponts_composante1 = pont_caseM_libre(case_libre_composante_joueur_courant23);
                                    ListIterator<caseM> ponts_composante = ponts_composante1.listIterator();
                                    while(ponts_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_pont_composante = ponts_composante.next();
                                        if(marquage[un_pont_composante.i][un_pont_composante.j]==false){
                                            marquage[un_pont_composante.i][un_pont_composante.j]=true;

                                            f.matrice[un_pont_composante.i][un_pont_composante.j].etat = couleur_joueur_courant();

                                            //int[][] floyd_nouveau = remplir_matrice_floyd();
                                            int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_pont_composante.i][un_pont_composante.j].numero_floyd);



                                            tmpit.add(un_pont_composante);

                                                //composante_joueur_courant_it.add(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                            nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                       //composante_joueur_courant.remove(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                            // if(tmpit.hasNext()){
                                                 tmpit.previous();
                                                 tmpit.remove();
                                           // }
                                            f.matrice[un_pont_composante.i][un_pont_composante.j].etat = 0;

                                            if(nb_coups_tmp < nb_coups_min){
                                                nb_coups_min = nb_coups_tmp;
                                                case_preferable = un_pont_composante;

                                            }
                                            /*if(nb_coups_tmp == nb_coups_min){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_pont_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }
                            if(case_preferable != null){
                            return case_preferable;
                            }
                            while(composante_joueur_courant_it.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_joueur_courant23 = composante_joueur_courant_it.next();
                                if(!est_element_bord(case_libre_composante_joueur_courant23)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant23, couleur_joueur_courant()))){


                             ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_joueur_courant23);
                                    ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                    while(successeurs_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_successeur_composante = successeurs_composante.next();
                                        if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false
                                                && !est_caseM_au_milieu_pont_du_chemin_principal_ia(un_successeur_composante)){
                                            marquage[un_successeur_composante.i][un_successeur_composante.j]=true;

                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();


                                        //int [][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_successeur_composante.i][un_successeur_composante.j].numero_floyd);

                                         tmpit.add(un_successeur_composante);
                                        //composante_joueur_courant.add(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                        nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                       // composante_joueur_courant.remove(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                       // if(tmpit.hasNext()){
                                             tmpit.previous();
                                            tmpit.remove();
                                         //   }

                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                            if(nb_coups_tmp < nb_coups_min){
                                                nb_coups_min = nb_coups_tmp;
                                                case_preferable = un_successeur_composante;
                                                System.out.println("cases preferable offensive :  i :"+case_preferable.i+"  j:"+case_preferable.j+"  nb coup :"+nb_coups_tmp);
                                            }
                                         /*   if(nb_coups_tmp == nb_coups_min){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }

                            caseM anti_pont_a_completer = completer_anti_pont_ennemi_noir_a_cote();
                            if(anti_pont_a_completer!=null){
                            //    System.out.println("Bob complete un anti pont!");
                                return anti_pont_a_completer;
                            }

                            System.out.println("Joueur decide Ã  jouer la case prÃ©fÃ©rable--");
                            return case_preferable;
                        }

                    }else{
//mode defensif:
                        System.out.println("--Mode Defensif");
                        if(composante_humain != null){
                            caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                            if(res != null){
                                return res;
                            }
                        }
                        if(composante_ia==null){
                            ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                            int nb_coups_une_boule;
                            int nb_coups_blanc_potentiel = 1024;
                            caseM case_mem2 = null;
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    if(f.matrice[i][j].etat == 0){

                                        f.matrice[i][j].etat = couleur_joueur_courant();
                                        //int[][] floyd_nouveau = remplir_matrice_floyd();
                                        int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                        liste_une_boule.clear();
                                        liste_une_boule.add(f.matrice[i][j]);
                                        nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                        f.matrice[i][j].etat = 0;

                                        if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                            nb_coups_blanc_potentiel = nb_coups_une_boule;
                                            case_mem2 = f.matrice[i][j];
                                        }
                                      /*  if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                            Random r = new Random();
                                            int choix = r.nextInt(2);
                                            if(choix == 1){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                        }*/

                                    }
                                }
                            }
                            return case_mem2;
                        }else{
                            caseM case_preferable = null;
                            ListIterator<caseM> composante_adverse = composante_humain.listIterator();
                            boolean[][]marquage = new boolean[f.N][f.N];
                            for(int i=0; i<f.N; i++){
                                for(int j=0; j<f.N; j++){
                                    marquage[i][j] = false;
                                }
                            }
                            int diff_mem = difference; //reinit
                            while(composante_adverse.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_adverse = composante_adverse.next();
                                if(!est_joker1_selon_bords_selon_couleur(case_libre_composante_adverse,couleur_joueur_adverse())){
                                    ArrayList<caseM> ponts_composante_adverse1 = pont_caseM_libre(case_libre_composante_adverse);
                                    ListIterator<caseM> ponts_composante_adverse = ponts_composante_adverse1.listIterator();
                                    while(ponts_composante_adverse.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_pont_composante_adverse = ponts_composante_adverse.next();
                                        if(marquage[un_pont_composante_adverse.i][un_pont_composante_adverse.j]==false){
                                            marquage[un_pont_composante_adverse.i][un_pont_composante_adverse.j]=true;

                                            f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j].etat = couleur_joueur_courant();
                                            //f.joueur_courant = joueur_num_adv;
                                            int nb1 = nbcoup_restantJ1;
                                            int nb2 = nbcoup_restantJ2;
                                            if(f.joueur_courant==1){
                                                nbcoup_restantJ1=500;
                                                nbcoup_restantJ2=500;
                                            }else{
                                                nbcoup_restantJ2=500;
                                                nbcoup_restantJ1=500;
                                            }
                                            int difference_minimax;
                                            if(f.joueur_courant==1){
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j], objectif_bordJ2);
                                            }else{
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j], objectif_bordJ1);
                                            }
                                            //f.joueur_courant = joueur_num_cur;
                                            nbcoup_restantJ1 = nb1;
                                            nbcoup_restantJ2 = nb2;
                                            f.matrice[un_pont_composante_adverse.i][un_pont_composante_adverse.j].etat = 0;

                                            if(difference_minimax > diff_mem || est_joker1_selon_bords_selon_couleur(un_pont_composante_adverse, couleur_joueur_adverse())){
                                                diff_mem = difference_minimax;
                                                case_preferable = un_pont_composante_adverse;
                                            }
                                           /* if(difference_minimax == diff_mem){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    diff_mem = difference_minimax;
                                                    case_preferable = un_pont_composante_adverse;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }
                            if(case_preferable!=null){
                         //       System.out.print("diff avant minimax = "+difference+", diff apres minimax = "+diff_mem);
                                return case_preferable;
                            }

                            composante_adverse = composante_humain.listIterator();
                         //   System.out.println("passage au seccesseurs");
                            while(composante_adverse.hasNext()){
                                //pour chaque case du chemin principal de l'adversaire:
                                caseM case_libre_composante_adverse = composante_adverse.next();
                                if(!est_joker1_selon_bords_selon_couleur(case_libre_composante_adverse,couleur_joueur_adverse())){
                                    ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_adverse);
                                    ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                    while(successeurs_composante.hasNext()){
                                        //pour chaque pont de cette case:
                                        caseM un_successeur_composante = successeurs_composante.next();
                                        if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false){
                                            marquage[un_successeur_composante.i][un_successeur_composante.j]=true;
                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();

                                            int nb1 = nbcoup_restantJ1;
                                            int nb2 = nbcoup_restantJ2;
                                            if(f.joueur_courant==1){
                                                nbcoup_restantJ1=500;
                                                nbcoup_restantJ2=500;
                                            }else{
                                                nbcoup_restantJ2=500;
                                                nbcoup_restantJ1=500;
                                            }
                                            int difference_minimax;
                                            if(f.joueur_courant==1){
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_successeur_composante.i][un_successeur_composante.j], objectif_bordJ2);
                                            }else{
                                                difference_minimax = _minimax2Ordi2(f.matrice[un_successeur_composante.i][un_successeur_composante.j], objectif_bordJ1);
                                            }
                                            nbcoup_restantJ1 = nb1;
                                            nbcoup_restantJ2 = nb2;
                                            f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                            if( difference_minimax >= diff_mem || est_joker1_selon_bords_selon_couleur(un_successeur_composante, couleur_joueur_adverse())){
                                                diff_mem = difference_minimax;
                                                case_preferable = un_successeur_composante;
                                            }
                                        /*    if(difference_minimax == diff_mem){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    diff_mem = difference_minimax;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }

                            if(case_preferable!=null){
                          //      System.out.print("diff avant minimax = "+difference+", diff apres minimax = "+diff_mem);
                                return case_preferable;
                            }
/*

                            caseM anti_pont_a_completer = completer_pont_ennemi_laison_autre_composante();
                            if(anti_pont_a_completer!=null){
                         //       System.out.println("Bob complete un anti pont2!");
                                return anti_pont_a_completer;
                            }

                            anti_pont_a_completer = completer_anti_pont_ennemi_noir_a_cote();
                            if(anti_pont_a_completer!=null){
                          //      System.out.println("Bob complete un anti pont1!");
                                return anti_pont_a_completer;
                            }

                            anti_pont_a_completer = completer_anti_pont_ennemi_tout_conditions();
                            if(anti_pont_a_completer!=null){
                          //      System.out.println("Bob complete un anti pont!");
                                return anti_pont_a_completer;
                            }
*/
        //si echec -> comme pour le mode offensif:
                            System.out.println("echec->comme pour le mode offensif");
                            /******************************************/
                            if(composante_ia==null){
                                ArrayList<caseM> liste_une_boule = new ArrayList<caseM>();
                                int nb_coups_une_boule;
                                int nb_coups_blanc_potentiel = 1024;
                                caseM case_mem2 = null;
                                for(int i=0; i<f.N; i++){
                                    for(int j=0; j<f.N; j++){
                                        if(f.matrice[i][j].etat == 0){

                                            f.matrice[i][j].etat = couleur_joueur_courant();
                                            //int [][] floyd_nouveau = remplir_matrice_floyd();
                                            int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[i][j].numero_floyd);
                                            liste_une_boule.clear();
                                            liste_une_boule.add(f.matrice[i][j]);
                                            nb_coups_une_boule = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), liste_une_boule, floyd_final3);
                                            f.matrice[i][j].etat = 0;

                                            if(nb_coups_une_boule < nb_coups_blanc_potentiel){
                                                nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                case_mem2 = f.matrice[i][j];
                                            }
                                          /*  if(nb_coups_une_boule == nb_coups_blanc_potentiel){
                                                Random r = new Random();
                                                int choix = r.nextInt(2);
                                                if(choix == 1){
                                                    nb_coups_blanc_potentiel = nb_coups_une_boule;
                                                    case_mem2 = f.matrice[i][j];
                                                }
                                            }*/
                                        }
                                    }
                                }

                                return case_mem2;
                            }else{
                        if(composante_humain != null){
                            caseM res = return_caseM_libre_pont_casse_de_la_liste(composante_humain, couleur_joueur_adverse());
                            if(res != null){
                                return res;
                            }
                        }
                                diff_mem = difference; //reinit
                                for(int i=0; i<f.N; i++){
                                    for(int j=0; j<f.N; j++){
                                        marquage[i][j] = false;
                                    }
                                }
                                int nb_coups_tmp;
                                int nb_coups_min = 1024;
                                case_preferable = null;
                                ArrayList<caseM> tmp= (ArrayList<caseM>) composante_ia.clone();
                                ListIterator<caseM> tmpit=tmp.listIterator();
                                ListIterator<caseM> composante_joueur_courant_it = composante_ia.listIterator();
                                while(composante_joueur_courant_it.hasNext()){
                                    //pour chaque case du chemin principal de l'adversaire:
                                    caseM case_libre_composante_joueur_courant34 =
                                            composante_joueur_courant_it.next();
                                    if(!est_element_bord(case_libre_composante_joueur_courant34)
                                        && !(est_joker1_selon_bords_selon_couleur(case_libre_composante_joueur_courant34, couleur_joueur_courant()))){


                                        ArrayList<caseM> ponts_composante1 = pont_caseM_libre(case_libre_composante_joueur_courant34);
                                        ListIterator<caseM> ponts_composante = ponts_composante1.listIterator();
                                        while(ponts_composante.hasNext()){
                                            //pour chaque pont de cette case:
                                            caseM un_pont_composante = ponts_composante.next();
                                            if(marquage[un_pont_composante.i][un_pont_composante.j]==false){
                                                marquage[un_pont_composante.i][un_pont_composante.j]=true;

                                                f.matrice[un_pont_composante.i][un_pont_composante.j].etat = couleur_joueur_courant();

                                                //int [][] floyd_nouveau = remplir_matrice_floyd();
                                                int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_pont_composante.i][un_pont_composante.j].numero_floyd);


                                                tmpit.add(un_pont_composante);

                                               // composante_joueur_courant.add(f.matrice[un_pont_composante.i][un_pont_composante.j]);
                                                nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(), tmp, floyd_final3);
                                               // composante_joueur_courant.remove(f.matrice[un_pont_composante.i][un_pont_composante.j]);


                                                f.matrice[un_pont_composante.i][un_pont_composante.j].etat = 0;
                                                tmpit.previous();
                                                tmpit.remove();
                                                if(nb_coups_tmp < nb_coups_min){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_pont_composante;
                                                }
                                               /* if(nb_coups_tmp == nb_coups_min){
                                                    Random r = new Random();
                                                    int choix = r.nextInt(2);
                                                    if(choix == 1){
                                                        nb_coups_min = nb_coups_tmp;
                                                        case_preferable = un_pont_composante;
                                                    }
                                                }*/
                                            }
                                        }

                                        ArrayList<caseM> successeurs_composante1 = successeurs_libres(case_libre_composante_joueur_courant34);
                                        ListIterator<caseM> successeurs_composante = successeurs_composante1.listIterator();
                                        while(successeurs_composante.hasNext()){
                                            //pour chaque pont de cette case:
                                            caseM un_successeur_composante = successeurs_composante.next();
                                            if(marquage[un_successeur_composante.i][un_successeur_composante.j]==false
                                                && !est_caseM_au_milieu_pont_du_chemin_principal_ia(un_successeur_composante)){

                                                marquage[un_successeur_composante.i][un_successeur_composante.j]=true;

                                                f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = couleur_joueur_courant();

                                                //int [][] floyd_nouveau = remplir_matrice_floyd();
                                                int[][] floyd_final3 = floyd_warshall_epure(Floyd_coup_courant,f.matrice[un_successeur_composante.i][un_successeur_composante.j].numero_floyd);


                                                tmpit.add(un_successeur_composante);

                                            //composante_joueur_courant.add(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                            nb_coups_tmp = nb_coup_restant_pour_composante_connexe(couleur_joueur_courant(),tmp, floyd_final3);
                                           // composante_joueur_courant.remove(f.matrice[un_successeur_composante.i][un_successeur_composante.j]);
                                            tmpit.previous();
                                            tmpit.remove();

                                                f.matrice[un_successeur_composante.i][un_successeur_composante.j].etat = 0;

                                                if(nb_coups_tmp < nb_coups_min){
                                                    nb_coups_min = nb_coups_tmp;
                                                    case_preferable = un_successeur_composante;
                                                }
                                            /*    if(nb_coups_tmp == nb_coups_min){
                                                    Random r = new Random();
                                                    int choix = r.nextInt(2);
                                                    if(choix == 1){
                                                        nb_coups_min = nb_coups_tmp;
                                                        case_preferable = un_successeur_composante;
                                                    }
                                                }*/
                                            }
                                        }
                                    }
                                }

                          //      System.out.println("Joueur decide à jouer la case préférable--");
                                if(case_preferable==null) {
                                    ArrayList<caseM> L = list_boules(f.matrice, 0);
                                    Random r = new Random();
                                    int i = r.nextInt(L.size());
                                    System.out.println("Je ne joue pas cette fois ci");
                                    return L.get(i);
                                }
                                return case_preferable;
                            }
                            /******************************************/
                        }
                    }


        }
    }

   synchronized private ArrayList<caseM> union_listes(ArrayList<caseM> res, ArrayList<caseM> src){
        // Ajoute les elements de laliste src dans la liste res donné en parametre.
        ListIterator<caseM> srcit = src.listIterator();
        while(srcit.hasNext()){
            res.add(srcit.next());
        }
        return res;
    }
    synchronized private ArrayList<caseM> return_un_chemin_couleur(caseM x){
        boolean[][] marque = new boolean[f.N][f.N];
        for(int i =0; i< f.N; i++){
            for(int j =0; j< f.N; j++){
                marque[i][j] = false;
            }
        }
        return _return_un_chemin_couleur(marque, x);
    }

   private boolean liste_est_marque(boolean[][]m, ArrayList<caseM> L){
       ListIterator<caseM> it = L.listIterator();
       while(it.hasNext()){
           caseM x = it.next();
           if(m[x.i][x.j] == false){
               return false;
           }
       }
       return true;
   }
   synchronized private ArrayList<caseM> _return_un_chemin_couleur(boolean[][] m, caseM x){
        /* Retourne la liste avec le chemin exploré autour de x (x y compris)
         */
        ArrayList<caseM> S = successeurs_couleur(x);
        ArrayList<caseM> P = pont_caseM_couleur(x);
        /*ListIterator<caseM> ptmp=P.listIterator();
        while(ptmp.hasNext()){
            caseM temp=ptmp.next();
            if(est_pont_cassé(temp, x))
                ptmp.remove();
        }*/
        ArrayList<caseM> res = new ArrayList<caseM>();
        if((S.isEmpty() || liste_est_marque(m, S)) && (P.isEmpty() || liste_est_marque(m, P)) ){
            //si on ne peut plus avancer:
            res.add(x);
            return res;
        }else{
            if(S.isEmpty()){
                //Si que les ponts:
                ListIterator<caseM> ponts = P.listIterator();
                ArrayList<caseM> restemp;
                while(ponts.hasNext()){
                    caseM temp = ponts.next();
                    //pour chaque ponts de x regarder s'il n'est pas déjà marqué
                    //et ajouter dans la liste résultat la liste de retour réc.
                    if(m[temp.i][temp.j]==false){
                        m[temp.i][temp.j]=true;
                        restemp = _return_un_chemin_couleur(m, temp);
                        res = union_array(res, restemp);
                    }
                }
                //ajouter x dans la liste résultat et retourner cette liste.
                res.add(x);
                return res;
            }else if(P.isEmpty()){
                //Si que les successeurs:
                ListIterator<caseM> succ = S.listIterator();
                ArrayList<caseM> restemp;
                while(succ.hasNext()){
                    caseM temp = succ.next();
                    //pour chacun des ponts de x regarder s'il n'est pas déjà marqué
                    //et ajouter dans la liste résultat la liste de retour réc.
                    if(m[temp.i][temp.j]==false){
                        m[temp.i][temp.j]=true;
                        restemp = _return_un_chemin_couleur(m, temp);
                        res = union_array(res, restemp);
                    }
                }
                //ajouter x dans la liste résultat et retourner cette liste.
                res.add(x);
                return res;
            }else{
                //si les ponts avec les successeurs:
                //pour les ponts:
                ListIterator<caseM> ponts = P.listIterator();
                ArrayList<caseM> restemp;
                while(ponts.hasNext()){
                    caseM temp = ponts.next();
                    //pour chaque ponts de x regarder s'il n'est pas déjà marqué
                    //et ajouter dans la liste résultat la liste de retour réc.
                    if(m[temp.i][temp.j]==false){
                        m[temp.i][temp.j]=true;
                        restemp = _return_un_chemin_couleur(m, temp);
                        res = union_array(res, restemp);
                    }
                }
                //pour les successeurs:
                ListIterator<caseM> succ = S.listIterator();
                while(succ.hasNext()){
                    caseM temp = succ.next();
                    //pour chacun des ponts de x regarder s'il n'est pas déjà marqué
                    //et ajouter dans la liste résultat la liste de retour réc.
                    if(m[temp.i][temp.j]==false){
                        m[temp.i][temp.j]=true;
                        restemp = _return_un_chemin_couleur(m, temp);
                        res =union_array(res, restemp);
                    }
                }
                //ajouter x dans la liste résultat et retourner cette liste.
                res.add(x);
                return res;
            }
        }
    }

   synchronized private ArrayList<ArrayList<caseM>> return_diff_chemins_couleur(int couleur){
        ArrayList<ArrayList<caseM>> res = new ArrayList<ArrayList<caseM>>();
        boolean[][] marque = new boolean[f.N][f.N];
        for(int i =0; i< f.N; i++){
            for(int j =0; j< f.N; j++){
                marque[i][j] = false;
            }
        }
        ArrayList<caseM> list_boules_couleur1 = list_boules(f.matrice, couleur);
       // System.out.println("Nb de boules sur le plateau couleur "+couleur+" = "+list_boules_couleur1.size());
        ListIterator<caseM> list_boules_couleur = list_boules_couleur1.listIterator();
        while(list_boules_couleur.hasNext()){
            caseM une_boule = list_boules_couleur.next();
            if(marque[une_boule.i][une_boule.j] == false){
                marque[une_boule.i][une_boule.j] = true;
                ArrayList<caseM> element_res = _return_un_chemin_couleur(marque,une_boule);
                res.add(element_res);
            }
        }
        return res;
    }

   synchronized int[][] remplir_matrice_floyd(){
        poids_floyd tmp;
        int[][] Floydres = new int[Floyd.length][Floyd.length];
        for(int i=0;i<Floyd.length;i++){
            for(int j=0;j<Floyd.length;j++){
                pred_floyd[i][j]=-1;
                tmp=arete_appartient_a_matrice_floyd(numero_vers_case[i].case_matrice,numero_vers_case[j].case_matrice);
                if(i==j){
                    Floydres[i][j]=0;
                }
                else if(i!=j && tmp.app==true){
                    Floydres[i][j]=tmp.poids;
                }
                else if(i!=j && tmp.app==false){
                    Floydres[i][j]=1000;
                }

            }
        }
        return Floydres;
    }
   synchronized public int minimum(int a, int b){
        if(a>b)
            return b;
        else
            return a;

    }
   synchronized int[][] floyd_warshall(int[][] Floyd){
        int [][] pred;
        pred=new int [Floyd.length][Floyd.length];
        pred=Floyd;
      //  liaison_floyd [][] liaison=new liaison_floyd[Floyd.length][Floyd.length];
        // init matrice liaison
      /* for(int m=0;m<Floyd.length;m++){
           for(int n=0;n<Floyd.length;n++){
                if(m==n || Floyd[m][n]>500)
                    liaison[m][n]=new liaison_floyd(-1, new ArrayList<caseM>());
                else if(m!=n && Floyd[m][n]<500){
                    ArrayList<caseM> prout=new ArrayList<caseM>();
                    prout.add(numero_vers_case[m].case_matrice);
                    liaison[m][n]=new liaison_floyd(m, prout);
               }
           }
       }*/


        // fin init
      /*  for(int i=0;i<Floyd.length;i++){
                for(int j=0;j<Floyd.length;j++){

                        curr[i][j]=minimum(pred[i][j],pred[i][0]+pred[0][j]);
                        if(pred[i][j]<=(pred[i][0]+pred[0][j]))
                        {}
                        else if(pred[i][j]>(pred[i][0]+pred[0][j])){
                            pred_floyd[i][j]=0;
                          //  liaison[i][j]=liaison[0][j];
                          //  liaison[i][j].list.add(numero_vers_case[0].case_matrice);
                        }
                }
        }
        for(int k=1;k<Floyd.length;k++){ // k=1 car on fait le cas initial en dehors.
            pred=curr;
            for(int i=0;i<Floyd.length;i++){
                for(int j=0;j<Floyd.length;j++){

                         curr[i][j]=minimum(pred[i][j],pred[i][k]+pred[k][j]);
                         if(pred[i][j]<=(pred[i][k]+pred[k][j]))
                        {}
                        else if(pred[i][j]>(pred[i][k]+pred[k][j])){
                            pred_floyd[i][j]=k;
                          //  liaison[i][j]=liaison[k][j];
                          //  liaison[i][j].list.add(numero_vers_case[k].case_matrice);

                         }
                }
            }
        }*/

       // matrice_liaison_courante=liaison;
      for(int k=0;k<Floyd.length;k++){ // k=1 car on fait le cas initial en dehors.

            for(int i=0;i<Floyd.length;i++){
                for(int j=0;j<Floyd.length;j++){



                        if(pred[i][j]>(pred[i][k]+pred[k][j])){

                            pred[i][j]=pred[i][k]+pred[k][j];
                            pred_floyd[i][j]=k;
                          //  liaison[i][j]=liaison[k][j];
                          //  liaison[i][j].list.add(numero_vers_case[k].case_matrice);

                         }
                }
            }
        }
        return pred;

    }
   synchronized int[][] floyd_warshall_epure(int[][] Floyd,int j){
     int [][] Floydc=Floyd.clone();
      for(int k=0;k<Floydc.length;k++){
       for(int i=0;i<Floydc.length;i++){
            if(Floyd[i][j-1]>(Floydc[i][k]+Floydc[k][j-1])){
                Floyd[i][j-1]=Floydc[i][k]+Floydc[k][j-1];
            }
        }
      }
     for(int k=0;k<Floydc.length;k++){
       for(int m=0;m<Floydc.length;m++){
            if(Floydc[j-1][m]>(Floydc[j-1][k]+Floydc[k][m])){
                Floydc[j-1][m]=Floydc[j-1][k]+Floydc[k][m];
            }
        }
      }

      return Floydc;
   }
   synchronized private int estim_manhattan_2_points(caseM x, caseM y){
        /*switch(bord){
            case 1:
                //caseBordy =0;
                caseBordx =f.N/4;
                break;
            case 2:
                //caseBordy =0;
                caseBordx =f.N-1;
                break;
            case 3:
                caseBordy =(3*f.N)/4;
                caseBordx =(3*f.N)/4;
                break;
            case 4:
                caseBordy =f.N-1;
                caseBordx =(3*f.N)/4;
                break;
            case 5:
                caseBordy =f.N-1;
                //caseBordx =0;
                break;
            case 6:
                caseBordy =f.N/4;
                //caseBordx =0;
                break;
        }*/
        return (y.i - x.i) + (y.j - x.j);
    }

   private int estim_manhattan_bord(caseM x, int bord){
       int caseBordx = 0;
       int caseBordy = 0;
       switch(bord){
            case 1:
                //caseBordy =0;
                caseBordx =f.N/4;
                break;
            case 2:
                //caseBordy =0;
                caseBordx =f.N-1;
                break;
            case 3:
                caseBordy =(3*f.N)/4;
                caseBordx =(3*f.N)/4;
                break;
            case 4:
                caseBordy =f.N-1;
                caseBordx =(3*f.N)/4;
                break;
            case 5:
                caseBordy =f.N-1;
                //caseBordx =0;
                break;
            case 6:
                caseBordy =f.N/4;
                //caseBordx =0;
                break;
        }
       return (caseBordx - x.i) + (caseBordy - x.j);
   }

   synchronized private ArrayList<caseM> elements_bord(int bord){
        ArrayList<caseM> elements_bord=new ArrayList<caseM>();
        for(int i=0;i<f.N;i++){
            for(int j=0;j<f.N;j++){
                if(f.matrice[i][j].etat!=-1 && (est_bord_selon_numcote(f.matrice[i][j], bord) || est_joker1_selon_bord(f.matrice[i][j], bord)
                        || est_joker2_selon_bord(f.matrice[i][j], bord) || est_voisin_d_un_bord(f.matrice[i][j], bord))){
                    elements_bord.add(f.matrice[i][j]);
                }
            }
        }

        return elements_bord;
    }
   synchronized private ArrayList<caseM> elements_bord_couleur(int bord,int couleur){
        ArrayList<caseM> elements_bord=new ArrayList<caseM>();
        for(int i=0;i<f.N;i++){
            for(int j=0;j<f.N;j++){
                if((f.matrice[i][j].etat==couleur || f.matrice[i][j].etat==0) && (est_bord_selon_numcote(f.matrice[i][j], bord)
                        || est_joker1_selon_bord(f.matrice[i][j], bord)
                        || est_joker2_selon_bord(f.matrice[i][j], bord)
                        || est_voisin_d_un_bord(f.matrice[i][j], bord))){
                    elements_bord.add(f.matrice[i][j]);
                }
            }
        }

        return elements_bord;
    }
   synchronized private ArrayList<caseM> elements_bord_couleur2(int bord,int couleur){
        ArrayList<caseM> elements_bord=new ArrayList<caseM>();
        for(int i=0;i<f.N;i++){
            for(int j=0;j<f.N;j++){
                if((f.matrice[i][j].etat==couleur) && (est_bord_selon_numcote(f.matrice[i][j], bord)
                        || est_joker1_selon_bord(f.matrice[i][j], bord)
                        || est_joker2_selon_bord(f.matrice[i][j], bord)
                        || est_voisin_d_un_bord(f.matrice[i][j], bord))){
                    elements_bord.add(f.matrice[i][j]);
                }
            }
        }

        return elements_bord;
    }

   synchronized private caseM manhattan_point_bord(caseM x,int bord){
        ArrayList<caseM> elements_bord=elements_bord(bord);
        ListIterator<caseM> IT=elements_bord.listIterator();
        int min=10000;
        int mintmp;
        caseM res = null;
        while(IT.hasNext()){
            caseM tmp=IT.next();
            if((mintmp=estim_manhattan_2_points(x, tmp))<min){
                min=mintmp;
                res=tmp;

            }
        }

        return res;
    }

   synchronized private boolean appartient_a_cases_milieu_pont(caseM x){
        int ac = 1;
        if(x.etat == 1) ac = 2;
        int i=x.i;
        int j=x.j;
        if(i<=f.N-2 && j<=f.N-2 && i>=1 && f.matrice[i-1][j].etat == ac && f.matrice[i+1][j+1].etat==ac && f.matrice[i][j+1].etat==0 )
            return true;
        if(i<=f.N-2 && j>=1 && j<=f.N-2 && f.matrice[i+1][j+1].etat == ac && f.matrice[i][j-1].etat==ac && f.matrice[i+1][j].etat==0 )
            return true;
        if(i<=f.N-2 && j<=f.N-2 && f.matrice[i][j+1].etat == ac && f.matrice[i+1][j].etat==ac && f.matrice[i+1][j+1].etat==0 )
            return true;
        if(i<=f.N-2 && i>=1 && j>=1 && f.matrice[i+1][j].etat == ac && f.matrice[i-1][j-1].etat==ac && f.matrice[i][j-1].etat==0 )
            return true;
        if(i>=1 && j>=1 && f.matrice[i][j-1].etat == ac && f.matrice[i-1][j].etat==ac && f.matrice[i-1][j-1].etat==0 )
            return true;
        if(i>=1 && j<=f.N-2 && j>=1 && f.matrice[i-1][j-1].etat == ac && f.matrice[i][j+1].etat==ac && f.matrice[i-1][j].etat==0 )
            return true;
        return false;
    }

   synchronized private poids_floyd arete_appartient_a_matrice_floyd(caseM x, caseM y){
        /* Si app -> bool = true et poids = poids(x, y)
         *
         */
        poids_floyd res = new poids_floyd(false,1000);
        ArrayList<caseM> pontL=pont_caseM_libre(x);
        ArrayList<caseM> pontstmpcouleur =pont_caseM_couleur(x);
        ArrayList<caseM> ponts1=new ArrayList<caseM>() ;
        ArrayList<caseM> succ=successeurs(x);

        ListIterator<caseM> ponttmpcouleurIT=pontstmpcouleur.listIterator();
        while(ponttmpcouleurIT.hasNext()){
            caseM tmplolz=ponttmpcouleurIT.next();
            if(!est_pont_cassé(tmplolz, x) && !appartient_a_cases_milieu_pont(tmplolz))
                ponts1.add(tmplolz);
        }

        ListIterator<caseM> pontLIT=pontL.listIterator();
        while(pontLIT.hasNext()){
            caseM tmplolz=pontLIT.next();
            if(!est_pont_cassé(tmplolz, x) && !appartient_a_cases_milieu_pont(tmplolz))
                ponts1.add(tmplolz);
        }

        ListIterator<caseM> successeur = succ.listIterator();
        while(successeur.hasNext()){
            caseM tmp2=successeur.next();
            if(!ponts1.contains(tmp2) && !appartient_a_cases_milieu_pont(tmp2))
                ponts1.add(tmp2);
        }// meme liste pour les ponts et successeurs


        ListIterator<caseM> ponts = ponts1.listIterator();
        while(ponts.hasNext()){
            caseM pont_case = ponts.next();
            if(pont_case.i == y.i && pont_case.j == y.j){

                if(pont_case.etat == 0) {
                    res = new poids_floyd(true, 1);
                    return res;
                }

                else if (pont_case.etat!=0 && pont_case.etat == x.etat){
                    res = new poids_floyd(true, 0);
                    return res;
                }
                else if(pont_case.etat != 0 && pont_case.etat !=x.etat){
                    res = new poids_floyd(false, 1000);
                }
           }
        }
        return res;
    }

    private int _poids_caseM_jusque_bord(caseM x, int numcote){
        if(est_bord_selon_numcote(x, numcote) || est_voisin_d_un_bord(x,numcote)){
            return 0;
        }else{
            ArrayList<caseM> P = pont_caseM_libre(x);
            if(!P.isEmpty()){
                ListIterator<caseM> ponts = P.listIterator();
                int min_poids = f.N*f.N;
                while(ponts.hasNext()){
                    caseM pont = ponts.next();
                    if(!case_est_marquee(pont)){
                        marquer_case(pont);
                        int poids_pont = _poids_caseM_jusque_bord(pont, numcote);
                        if(poids_pont != -1){
                            //si on touche le bord:
                            if(poids_pont < min_poids){
                                //si le poids trouvé est plus petit que celui d'avant:
                                min_poids = poids_pont;
                            }
                        }
                    }
                }
                if(min_poids == f.N*f.N){
                    return -1;
                }else{
                    return (1+min_poids);
                }
            }else{
                ArrayList<caseM> S = successeurs_libres(x);
                if(!S.isEmpty()){
                    ListIterator<caseM> suc = S.listIterator();
                    int min_poids = f.N*f.N;
                    while(suc.hasNext()){
                        caseM case_suc = suc.next();
                        if(!case_est_marquee(case_suc)){
                            marquer_case(case_suc);
                            int poids_suc = _poids_caseM_jusque_bord(case_suc, numcote);
                            if(poids_suc != -1){
                                //si on touche le bord:
                                if(poids_suc < min_poids){
                                    //si le poids trouvé est plus petit que celui d'avant:
                                    min_poids = poids_suc;
                                }
                            }
                        }
                    }
                    if(min_poids == f.N*f.N){
                        return -1;
                    }else{
                        return (1+min_poids);
                    }
                }else{
                    //si bloquage:
                    return -1;
                }
            }
        }
    }

     ArrayList<caseM> __poids_caseM_jusque_bord(caseM x, int numcote, ArrayList<caseM> effacer){
        if(est_voisin_d_un_bord(x, numcote)
                || est_bord_selon_numcote(x, numcote)
                || est_joker1_selon_bord(x, numcote)
                || est_joker2_selon_bord(x, numcote)){
            ArrayList<caseM> res = new ArrayList<caseM>();
            res.add(x);
            return res;
        }else{
            System.out.println("ujifoeizfiz : " + x.i + ", " + x.j);
            ArrayList<caseM> P = pont_caseM_libre(x);

          //  int man = estim_manhattan(x, numcote);

            if(!P.isEmpty()){
                ListIterator<caseM> ponts = P.listIterator();
                int min_poids = f.N*f.N;
                ArrayList<caseM> res = new ArrayList<caseM>();
                caseM man_case = manhattan_point_bord(x,numcote);
               // trier_liste_ponts_selon_manhattan(ponts, man_case);

                while(ponts.hasNext()){
                    caseM pont = ponts.next();
                   // if(man2 <= man){
                        f.matrice[pont.i][pont.j].etat = x.etat;

                        effacer.add(pont);
                        ArrayList<caseM> liste_pont = __poids_caseM_jusque_bord(pont, numcote, effacer);

                        if(!liste_pont.isEmpty()){
                            System.out.println("pont = "+x.i+", "+x.j);

                            //si on touche le bord:
                            if(liste_pont.size() < min_poids){
                                System.out.println("ujifoeizfiz : " + x.i + ", " + x.j);
                                //si le poids trouvé est plus petit que celui d'avant:
                                min_poids = liste_pont.size();
                                res.clear();
                                union_listes(res, liste_pont);
                                res.add(x);
                            }
                        }
                   // }

                }
                if(min_poids != f.N*f.N){
                    return res;
                }
            }/*
            ArrayList<caseM> S = successeurs_libres(x);
            ArrayList<caseM> S = successeurs_libres(x);
            if(!S.isEmpty()){
                ArrayList<caseM> liste_suc = null;
                ArrayList<caseM> res = new ArrayList<caseM>();
                ListIterator<caseM> succ = S.listIterator();
                int min_suc = f.N*f.N;
                while(succ.hasNext()){
                    caseM suc = succ.next();
                    //int man2 = estim_manhattan(suc, numcote);
                    //if(man2 <= man){
                        f.matrice[suc.i][suc.j].etat = x.etat;
                        effacer.add(suc);
                        liste_suc = __poids_caseM_jusque_bord(suc, numcote, effacer);
                        f.matrice[suc.i][suc.j].etat = 0;
                        if(liste_suc!=null){
                            //si on touche le bord:
                            if(liste_suc.size() < min_suc){
                                //si le poids trouvé est plus petit que celui d'avant:
                                min_suc = liste_suc.size();
                                res.clear();
                                union_listes(res, liste_suc);
                                res.add(x);
                            }
                        }
                   // }
                }
                if(min_suc != f.N*f.N){
                    return res;
                }
            }*/
            return new ArrayList<caseM>();
        }
    }

    private int poids_caseM_jusque_bord(caseM x, int numcote){
        /* Retourne le nombre de pas da la caseM x jusqu'au numcote
         * Valeur de retour = -1 si on ne touche jamais le bord
         */
        ArrayList<caseM> effacer = new ArrayList<caseM>();
        ArrayList<caseM> L = __poids_caseM_jusque_bord(x, numcote, effacer);
        ListIterator<caseM> e = effacer.listIterator();
        while(e.hasNext()){
            caseM y = e.next();
            f.matrice[y.i][y.j].etat = 0;
        }
        System.out.println("--------------------------------------------");
        AffichageAvecDelai a = new AffichageAvecDelai(f, 200, L);
        System.out.println("depuis la case ("+x.i+", "+x.j+") --> b"+numcote+" ="+L.size());
        System.out.println("--------------------------------------------");
        return L.size();
    }

    private int nb_pas_pour_atteindre_bord(ArrayList<caseM> L, int numcote){
        /* Retourne le nombre de pas minimal pour atteindre numcote.
         * Retourne -1 si a partir de L on ne peut jamais atteindre le bord
         */
        ListIterator<caseM> liste = L.listIterator();
        int res = f.N*f.N; //valeur suffisamment grande
        while(liste.hasNext()){
            caseM case_liste = liste.next();
            int poids_case_liste = poids_caseM_jusque_bord(case_liste, numcote);
            if(poids_case_liste != -1 && poids_case_liste<res){
                res = poids_case_liste;
            }
        }
        if(res==f.N*f.N){
            System.out.println("ICI;");
            return -1;
        }else{
            return res;
        }
    }

    private class liberte_avec_direction{
        int lib;
        int nb_min_pour_atteindre_bord;
        ArrayList<Integer> liste_dir;
        ListIterator<Integer> it;

        public liberte_avec_direction(int lib, int a, ArrayList L){
            liste_dir = (ArrayList<Integer>) copie_list(L);
            this.lib=lib;
            nb_min_pour_atteindre_bord = a;
            it = L.listIterator();
        }
        public int liberte(){
            return lib;
        }
        public int nb_min_pour_atteindre_bord(){
            return nb_min_pour_atteindre_bord;
        }
        public ListIterator it(){
            return it;
        }
        public boolean hasNext(){
            return it.hasNext();
        }
        public int next(){
            return (it.next()).intValue();
        }

    }

   synchronized private liberte_avec_direction liberte_chemin(ArrayList<caseM> L, int couleur){
        /* Pour chacun des buts, analyser le nombre 'restmp' de pas pour
         * atteindre ce but, et memoriser dans la liste 'liste' les bords
         * avec le nombre de pas minimal.
         * 'res' contient la libertée de L, cad la somme des pas jusqu'à
         * tous les buts
         */
        ArrayList liste = new ArrayList();
        int res = 0;
        if(couleur == 1){
            int resBut1, resBut2, resBut3;
            int restmp;
            int resmin = f.N*f.N;
            resBut1 = nb_pas_pour_atteindre_bord(L, 1);
            if(resBut1 != -1){
                resBut2 = nb_pas_pour_atteindre_bord(L, 3);
                if(resBut2 != -1){
                    restmp = resBut1+resBut2;
                    resBut3 = nb_pas_pour_atteindre_bord(L, 5);
                    if(resBut3 != -1){
                        restmp += resBut3;
                        res += restmp;
                        resmin = restmp;
                        liste.add(resBut1);
                        liste.add(resBut2);
                        liste.add(resBut3);
                    }
                }
            }
            resBut1 = nb_pas_pour_atteindre_bord(L, 2);
            if(resBut1 != -1){
                resBut2 = nb_pas_pour_atteindre_bord(L, 4);
                if(resBut2 != -1){
                    restmp = resBut1+resBut2;
                    resBut3 = nb_pas_pour_atteindre_bord(L, 6);
                    if(resBut3 != -1){
                        restmp += resBut3;
                            res += restmp;
                        if(restmp<resmin){
                            resmin = restmp;
                            liste.clear();
                            liste.add(resBut1);
                            liste.add(resBut2);
                            liste.add(resBut3);
                        }
                    }
                }
            }
            return new liberte_avec_direction(res, resmin, liste);

        }else{ //if(couleur == 2){
            int resBut1, resBut2;
            int restmp = f.N*f.N;
            int res_min = restmp;
            resBut1 = nb_pas_pour_atteindre_bord(L, 1);
            if(resBut1 != -1){
                System.out.print("b1 - "+resBut1+"c, ");
                resBut2 = nb_pas_pour_atteindre_bord(L, 4);
                if(resBut2 != -1){
                    System.out.print("b4 - "+resBut2+"c. ");
                    restmp = resBut1+resBut2;
                    res += restmp;
                    res_min = restmp;
                    liste.add(resBut1);
                    liste.add(resBut2);
                    System.out.println("Blanc peut atteindre les bords 1 et 4");
                }
            }
            resBut1 = nb_pas_pour_atteindre_bord(L, 2);
            if(resBut1 != -1){
                resBut2 = nb_pas_pour_atteindre_bord(L, 5);
                if(resBut2 != -1){
                    restmp = resBut1+resBut2;
                    res += restmp;
                    if(restmp < res_min){
                        res_min = restmp;
                        liste.clear();
                        liste.add(resBut1);
                        liste.add(resBut2);
                    }
                }
            }
            resBut1 = nb_pas_pour_atteindre_bord(L, 3);
            if(resBut1 != -1){
                resBut2 = nb_pas_pour_atteindre_bord(L, 6);
                if(resBut2 != -1){
                    restmp = resBut1+resBut2;
                    res += restmp;
                    if(restmp < res_min){
                        res_min = restmp;
                        liste.clear();
                        liste.add(resBut1);
                        liste.add(resBut2);
                    }
                }
            }
            return new liberte_avec_direction(res, res_min, liste);
        }
    }

    public void jouerOrdi_1(){
    
    }
   synchronized public void jouerOrdi_3() {
       System.out.println("------------");
       //afficher_matrice_sous_forme_text(2);
       caseM caseAjouer = minmaxOrdi3();

       if(caseAjouer != null){
         System.out.println("CaseAjouer par ordi2 = ("+caseAjouer.i+", "+caseAjouer.j+")");
         f.outils.changerEtat(caseAjouer, f.joueur_courant);

         if(estVictoire(f.joueur_courant)){
             f.jeu_fini=true;
             ArrayList<caseM> vict = estVictoire_retourne_chemin(f.joueur_courant);
             affichage_list(vict);
             ListIterator<caseM> it = vict.listIterator();
            while(it.hasNext()){
                caseM el = it.next();
                f.matrice[el.i][el.j].etat = 5;
             }
             f.aire.repaint();
             System.out.println("Bob GAGNE");
             new Fen_Fin_partie(f, true, f.joueur_courant);
         }
         if(estDefaite(f.joueur_courant)){
            f.jeu_fini=true;
            System.out.println("Bob PERD tout betement");
            ArrayList<caseM> vict = estVictoire_retourne_chemin(f.joueur_courant);
            affichage_list(vict);
            ListIterator<caseM> it = vict.listIterator();
            while(it.hasNext()){
              caseM el = it.next();
              f.matrice[el.i][el.j].etat = 5;
            }
            f.aire.repaint();
            new Fen_Fin_partie(f, true, f.joueur_courant);
         }


         f.activer_thread_clignotant(caseAjouer);
       }

       f.ecvIA.tour_joueur=true;
       //if(f.partie_commencee){
       if(f.joueur_courant==1){
         f.joueur_courant=2;
       }else{
         f.joueur_courant=1;
       }
       // }
       f.aire.repaint();

       System.out.println("------------");
    }


   public void jouerOrdi_2(){

        System.out.println("------------");
        //afficher_matrice_sous_forme_text(2);
        caseM caseAjouer = minimaxOrdi2();

        if(caseAjouer != null){
            System.out.println("CaseAjouer par ordi2 = ("+caseAjouer.i+", "+caseAjouer.j+")");
            f.outils.changerEtat(caseAjouer, f.joueur_courant);

            if(estVictoire(f.joueur_courant)){
                f.jeu_fini=true;
                ArrayList<caseM> vict = estVictoire_retourne_chemin(f.joueur_courant);
                affichage_list(vict);
                f.arreter_thread_clignotant();
                ListIterator<caseM> it = vict.listIterator();
                while(it.hasNext()){
                    caseM el = it.next();
                    f.matrice[el.i][el.j].etat = 5;
                }
                f.aire.repaint();
               System.out.println("Bob GAGNE");
                new Fen_Fin_partie(f, true, f.joueur_courant);
            }
            if(estDefaite(f.joueur_courant)){
                f.jeu_fini=true;
                System.out.println("Bob PERD tout betement");
                ArrayList<caseM> vict = estVictoire_retourne_chemin(f.joueur_courant);
                f.arreter_thread_clignotant();
                affichage_list(vict);
                ListIterator<caseM> it = vict.listIterator();
                while(it.hasNext()){
                    caseM el = it.next();
                    f.matrice[el.i][el.j].etat = 5;
                }
                f.aire.repaint();
                new Fen_Fin_partie(f, true, f.joueur_courant);
            }


           f.activer_thread_clignotant(caseAjouer);
        }

        f.ecvIA.tour_joueur=true;
      //if(f.partie_commencee){
            if(f.joueur_courant==1){
                f.joueur_courant=2;
            }else{
                f.joueur_courant=1;
            }
      // }
         f.aire.repaint();

        System.out.println("------------");

    }
   synchronized private int plus_court_chemin_case_vers_bord_particulier(int [][] tab,caseM x,int bord){
        // retourne le nombre de pas du plus court chemin d'une case vers un bord specifique.
        int min=500;

        ArrayList<caseM> EB1=elements_bord_couleur(bord,x.etat);
        ListIterator<caseM> EB1IT=EB1.listIterator();

        while(EB1IT.hasNext()){
            caseM element=EB1IT.next();
            if(tab[x.numero_floyd-1][element.numero_floyd-1]<min)
                min=tab[x.numero_floyd-1][element.numero_floyd-1];
        }

        return min;
    }
   synchronized private ArrayList<caseM> plus_court_chemin_case_vers_bord_particulier_retourne_chemin(caseM x,int bord){
        // retourne le  plus court chemin d'une case vers un bord specifique.
        int min=5000;
        ArrayList<caseM> minimum=new ArrayList<caseM>();
        ArrayList<caseM> EB1=elements_bord_couleur(bord,x.etat);
        ListIterator<caseM> EB1IT=EB1.listIterator();

        while(EB1IT.hasNext()){
            caseM element=EB1IT.next();
            ArrayList<caseM> resultat=new ArrayList<caseM>();
            resultat=plus_court_chemin__vers_point_retourne_chemin(x, element,resultat);
            resultat.add(element);
            if(resultat.size()<min){
                minimum=resultat;
                min=resultat.size();
            }
        }
        return minimum;
    }
   synchronized private ArrayList<caseM> plus_court_chemin__vers_point_retourne_chemin(caseM x,caseM y,ArrayList<caseM> resultat){
         /*if(x.numero_floyd==y.numero_floyd){
            resultat.add(x);
            return resultat;
        }
        else if(x.numero_floyd!=y.numero_floyd && matrice_liaison_courante[x.numero_floyd-1][y.numero_floyd-1].indice!=-1){
            return plus_court_chemin__vers_point_retourne_chemin(x,numero_vers_case[matrice_liaison_courante[x.numero_floyd-1][y.numero_floyd-1].indice].case_matrice,resultat);

        }
        else
            return resultat;*/

       // return matrice_liaison_courante[x.numero_floyd-1][y.numero_floyd-1].list;

        int i=x.numero_floyd-1;
        int j=y.numero_floyd-1;

       if(Floyd[i][j]>=500){
         // System.out.println("no path");
           return new ArrayList<caseM>();

       }

        if(pred_floyd[i][j]==-1)
           return resultat;
           // System.out.println("theres an edge from"+i+" to"+j);
        else {

           resultat= plus_court_chemin__vers_point_retourne_chemin(x, numero_vers_case[pred_floyd[i][j]].case_matrice, resultat);
           resultat.add(x);
           resultat= plus_court_chemin__vers_point_retourne_chemin(numero_vers_case[pred_floyd[i][j]].case_matrice,y, resultat);

        }
       return resultat;

    }
   synchronized private  int plus_court_chemin_case_vers_tout_bord(int [][] tab,caseM x){
        // retourne  le nombre de pas du plus court chemin d'une case vers n'importe quel bord
        int min=500;

        ArrayList<caseM> EB1=elements_bord_couleur(1,x.etat);
        ListIterator<caseM> EB1IT=EB1.listIterator();

        while(EB1IT.hasNext()){
            caseM element=EB1IT.next();
            if(tab[x.numero_floyd-1][element.numero_floyd-1]<min)
                min=tab[x.numero_floyd-1][element.numero_floyd-1];
        }


        ArrayList<caseM> EB2=elements_bord_couleur(2,x.etat);
        ListIterator<caseM> EB2IT=EB2.listIterator();

        while(EB2IT.hasNext()){
            caseM element=EB2IT.next();
            if(tab[x.numero_floyd-1][element.numero_floyd-1]<min)
                min=tab[x.numero_floyd-1][element.numero_floyd-1];
        }
        ArrayList<caseM> EB3=elements_bord_couleur(3,x.etat);
        ListIterator<caseM> EB3IT=EB3.listIterator();

        while(EB3IT.hasNext()){
            caseM element=EB3IT.next();
            if(tab[x.numero_floyd-1][element.numero_floyd-1]<min)
                min=tab[x.numero_floyd-1][element.numero_floyd-1];
        }
        ArrayList<caseM> EB4=elements_bord_couleur(4,x.etat);
        ListIterator<caseM> EB4IT=EB4.listIterator();

        while(EB4IT.hasNext()){
            caseM element=EB4IT.next();
            if(tab[x.numero_floyd-1][element.numero_floyd-1]<min)
                min=tab[x.numero_floyd-1][element.numero_floyd-1];
        }
        ArrayList<caseM> EB5=elements_bord_couleur(5,x.etat);
        ListIterator<caseM> EB5IT=EB5.listIterator();

        while(EB5IT.hasNext()){
            caseM element=EB5IT.next();
            if(tab[x.numero_floyd-1][element.numero_floyd-1]<min)
                min=tab[x.numero_floyd-1][element.numero_floyd-1];
        }
        ArrayList<caseM> EB6=elements_bord_couleur(6,x.etat);
        ListIterator<caseM> EB6IT=EB6.listIterator();

        while(EB6IT.hasNext()){
            caseM element=EB6IT.next();
            if(tab[x.numero_floyd-1][element.numero_floyd-1]<min)
                min=tab[x.numero_floyd-1][element.numero_floyd-1];
        }
        return min;
    }

   synchronized public int nb_coup_restant_pour_composante_connexe(int couleur,ArrayList<caseM> L,int tabfloyd[][]){
        ListIterator<caseM> composante_it = L.listIterator();
        int mintmp1=5000,mintmp2=5000,mintmp3=5000,mintmp4=5000,mintmp5=5000,mintmp6=5000;
        int minversUNnoir=5000,minversDEUXnoir=5000,minversTROISnoir=5000,minversQUATREnoir=5000,minversCINQnoir=5000,minversSIXnoir=5000;
        int minversUNblanc=5000,minversDEUXblanc=5000,minversTROISblanc=5000,minversQUATREblanc=5000,minversCINQblanc=5000,minversSIXblanc=5000;
        int MissionUnTroisCinq,MissionDeuxQuatreSix,MissionUnQuatre,MissionDeuxCinq,MissionTroisSix;
        if(couleur==1){
                 while(composante_it.hasNext()){
                     caseM x=composante_it.next();
                     mintmp1=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 1);

                     if(mintmp1<minversUNnoir)
                         minversUNnoir=mintmp1;


                     mintmp2=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 2);
                     if(mintmp2<minversDEUXnoir)
                         minversDEUXnoir=mintmp2;


                     mintmp3=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 3);

                     if(mintmp3<minversTROISnoir)
                         minversTROISnoir=mintmp3;


                     mintmp4=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 4);

                     if(mintmp4<minversQUATREnoir)
                         minversQUATREnoir=mintmp4;


                     mintmp5=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 5);

                     if(mintmp5<minversCINQnoir)
                         minversCINQnoir=mintmp5;


                     mintmp6=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 6);

                     if(mintmp6<minversSIXnoir)
                         minversSIXnoir=mintmp6;
                  }
                 MissionUnTroisCinq=minversUNnoir+minversTROISnoir+minversCINQnoir;
                 MissionDeuxQuatreSix=minversDEUXnoir+minversQUATREnoir+minversSIXnoir;



                 int nbcoupnoirtmp=minimum(MissionUnTroisCinq,MissionDeuxQuatreSix);
                return nbcoupnoirtmp;
            }

            else if(couleur == 2)
            {
                 while(composante_it.hasNext()){
                     caseM x=composante_it.next();
                     mintmp1=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 1);

                     if(mintmp1<minversUNblanc)
                         minversUNblanc=mintmp1;


                     mintmp2=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 2);
                     if(mintmp2<minversDEUXblanc)
                         minversDEUXblanc=mintmp2;


                     mintmp3=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 3);

                     if(mintmp3<minversTROISblanc)
                         minversTROISblanc=mintmp3;


                     mintmp4=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 4);

                     if(mintmp4<minversQUATREblanc)
                         minversQUATREblanc=mintmp4;


                     mintmp5=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 5);

                     if(mintmp5<minversCINQblanc)
                         minversCINQblanc=mintmp5;


                     mintmp6=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 6);

                     if(mintmp6<minversSIXblanc)
                         minversSIXblanc=mintmp6;
                  }
                 MissionUnQuatre=minversUNblanc+minversQUATREblanc;
                 MissionDeuxCinq=minversDEUXblanc+minversCINQblanc;
                 MissionTroisSix=minversTROISblanc+minversSIXblanc;


                 int nbcoupblanctmp=minimum(MissionUnQuatre,minimum(MissionDeuxCinq,MissionTroisSix));
                 return nbcoupblanctmp;

            }else
                return -1;

    }
   public int nombre_coup_jusqua_bord(int couleur,ArrayList<caseM> L,int tabfloyd[][],int bord){
      
        ListIterator<caseM> composante_it = L.listIterator();
        int mintmp;int minimum=5000;

        if(couleur==1){
                while(composante_it.hasNext()){
                     caseM x=composante_it.next();
                     mintmp=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, bord);

                     if(mintmp<minimum)
                         minimum=mintmp;
                }
                return minimum;
        }else if(couleur == 2){
                while(composante_it.hasNext()){
                     caseM x=composante_it.next();
                     mintmp=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, bord);

                     if(mintmp<minimum)
                         minimum=mintmp;
                 }
                 return minimum;
            }else
                return -1;
   }
    synchronized public ArrayList<caseM> composante_connexe_principale(int couleur,int tabfloyd[][]){

        ArrayList<ArrayList<caseM>> R =  return_diff_chemins_couleur(couleur);
//        System.out.println("return_diff_chemins_couleur.size (couleur:"+couleur+") = "+R.size());

        ListIterator<ArrayList<caseM>> E = R.listIterator();

        int resultat=5000;
        int libertetmp=5000;
        int mintmp1=5000,mintmp2=5000,mintmp3=5000,mintmp4=5000,mintmp5=5000,mintmp6=5000;
        int MissionUnQuatre=5000,MissionDeuxCinq=5000,MissionTroisSix=5000,MissionUnTroisCinq=5000,MissionDeuxQuatreSix=5000;
        int minversUNblanc=5000,minversDEUXblanc=5000,minversTROISblanc=5000,minversQUATREblanc=5000,minversCINQblanc=5000,minversSIXblanc=5000;
        int minversUNnoir=5000,minversDEUXnoir=5000,minversTROISnoir=5000,minversQUATREnoir=5000,minversCINQnoir=5000,minversSIXnoir=5000;

        ArrayList<caseM> resultatchemin = null;
        for(int i=0; i<R.size(); i++){
            ArrayList<caseM> composante = E.next();
            ListIterator<caseM> composante_it = composante.listIterator();
            MissionUnQuatre=5000;MissionDeuxCinq=5000;MissionTroisSix=5000;MissionUnTroisCinq=5000;MissionDeuxQuatreSix=5000;
            minversUNblanc=5000;minversDEUXblanc=5000;minversTROISblanc=5000;minversQUATREblanc=5000;minversCINQblanc=5000;minversSIXblanc=5000;
            minversUNnoir=5000;minversDEUXnoir=5000;minversTROISnoir=5000;minversQUATREnoir=5000;minversCINQnoir=5000;minversSIXnoir=5000;

            if(couleur==1){
                 while(composante_it.hasNext()){
                     caseM x=composante_it.next();
                     mintmp1=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 1);

                     if(mintmp1<minversUNnoir)
                         minversUNnoir=mintmp1;


                     mintmp2=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 2);
                     if(mintmp2<minversDEUXnoir)
                         minversDEUXnoir=mintmp2;


                     mintmp3=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 3);

                     if(mintmp3<minversTROISnoir)
                         minversTROISnoir=mintmp3;


                     mintmp4=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 4);

                     if(mintmp4<minversQUATREnoir)
                         minversQUATREnoir=mintmp4;


                     mintmp5=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 5);

                     if(mintmp5<minversCINQnoir)
                         minversCINQnoir=mintmp5;


                     mintmp6=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 6);

                     if(mintmp6<minversSIXnoir)
                         minversSIXnoir=mintmp6;
                  }
                 MissionUnTroisCinq=minversUNnoir+minversTROISnoir+minversCINQnoir;
                 MissionDeuxQuatreSix=minversDEUXnoir+minversQUATREnoir+minversSIXnoir;

                 libertetmp=MissionUnTroisCinq+MissionDeuxQuatreSix;

                 int nbcoupnoirtmp=minimum(MissionUnTroisCinq,MissionDeuxQuatreSix);
                 if(MissionUnTroisCinq<=MissionDeuxQuatreSix && f.joueurDeux.couleur==couleur){
                     objectif_bordJ2[0]=1;
                     objectif_bordJ2[1]=3;
                     objectif_bordJ2[2]=5;
                 }
                else if(MissionDeuxQuatreSix<MissionUnTroisCinq && f.joueurDeux.couleur==couleur){
                    objectif_bordJ2[0]=2;
                     objectif_bordJ2[1]=4;
                     objectif_bordJ2[2]=6;
                }
                 if(MissionUnTroisCinq<=MissionDeuxQuatreSix && f.joueurUn.couleur==couleur){
                     objectif_bordJ1[0]=1;
                     objectif_bordJ1[1]=3;
                     objectif_bordJ1[2]=5;
                 }
                 else if(MissionDeuxQuatreSix<MissionUnTroisCinq && f.joueurDeux.couleur==couleur){
                    objectif_bordJ1[0]=2;
                     objectif_bordJ1[1]=4;
                     objectif_bordJ1[2]=6;
                }
                 if(f.joueurUn.couleur==couleur && nbcoupnoirtmp<nbcoup_restantJ1)
                     nbcoup_restantJ1=nbcoupnoirtmp;
                 if(f.joueurDeux.couleur==couleur && nbcoupnoirtmp<nbcoup_restantJ2)
                     nbcoup_restantJ2=nbcoupnoirtmp;

            }

            else if(couleur == 2)
            {
                 while(composante_it.hasNext()){
                     caseM x=composante_it.next();
                     mintmp1=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 1);

                     if(mintmp1<minversUNblanc)
                         minversUNblanc=mintmp1;


                     mintmp2=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 2);
                     if(mintmp2<minversDEUXblanc)
                         minversDEUXblanc=mintmp2;


                     mintmp3=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 3);

                     if(mintmp3<minversTROISblanc)
                         minversTROISblanc=mintmp3;


                     mintmp4=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 4);

                     if(mintmp4<minversQUATREblanc)
                         minversQUATREblanc=mintmp4;


                     mintmp5=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 5);

                     if(mintmp5<minversCINQblanc)
                         minversCINQblanc=mintmp5;


                     mintmp6=plus_court_chemin_case_vers_bord_particulier(tabfloyd, x, 6);

                     if(mintmp6<minversSIXblanc)
                         minversSIXblanc=mintmp6;
                  }
                 MissionUnQuatre=minversUNblanc+minversQUATREblanc;
                 MissionDeuxCinq=minversDEUXblanc+minversCINQblanc;
                 MissionTroisSix=minversTROISblanc+minversSIXblanc;
                 libertetmp=MissionUnQuatre+MissionDeuxCinq+MissionTroisSix;

                 int nbcoupblanctmp=minimum(MissionUnQuatre,minimum(MissionDeuxCinq,MissionTroisSix));
                 if(MissionUnQuatre<=MissionDeuxCinq && MissionUnQuatre<=MissionTroisSix && f.joueurDeux.couleur==couleur){
                     objectif_bordJ2[0]=1;
                     objectif_bordJ2[1]=4;
                     objectif_bordJ2[2]=0;
                 }
                else if(MissionDeuxCinq<=MissionUnQuatre && MissionDeuxCinq<=MissionTroisSix  && f.joueurDeux.couleur==couleur){
                    objectif_bordJ2[0]=2;
                     objectif_bordJ2[1]=5;
                     objectif_bordJ2[2]=0;

                }
                else if(MissionTroisSix<=MissionUnQuatre && MissionTroisSix<=MissionDeuxCinq  && f.joueurDeux.couleur==couleur){
                    objectif_bordJ2[0]=3;
                    objectif_bordJ2[1]=6;
                    objectif_bordJ2[2]=0;

                }
                if(MissionUnQuatre<=MissionDeuxCinq && MissionUnQuatre<=MissionTroisSix && f.joueurUn.couleur==couleur){
                     objectif_bordJ1[0]=1;
                     objectif_bordJ1[1]=4;
                     objectif_bordJ1[2]=0;
                 }
                else if(MissionDeuxCinq<=MissionUnQuatre && MissionDeuxCinq<=MissionTroisSix  && f.joueurUn.couleur==couleur){
                    objectif_bordJ1[0]=2;
                     objectif_bordJ1[1]=5;
                     objectif_bordJ1[2]=0;

                }
                else if(MissionTroisSix<=MissionUnQuatre && MissionTroisSix<=MissionDeuxCinq  && f.joueurUn.couleur==couleur){
                    objectif_bordJ1[0]=3;
                    objectif_bordJ1[1]=6;
                    objectif_bordJ1[2]=0;

                }
                 if(f.joueurUn.couleur==couleur && nbcoupblanctmp<nbcoup_restantJ1)
                     nbcoup_restantJ1=nbcoupblanctmp;
                 if(f.joueurDeux.couleur==couleur && nbcoupblanctmp<nbcoup_restantJ2)
                     nbcoup_restantJ2=nbcoupblanctmp;
            }

            if(libertetmp<resultat){
                resultat=libertetmp;
                resultatchemin=new ArrayList<caseM>();
                ListIterator<caseM> itlolz=composante.listIterator();
                while(itlolz.hasNext()){
                    caseM tmp5=itlolz.next();
                    resultatchemin.add(tmp5);
                }
            }
        }

      //  System.out.println("LIBERTE DU CHEMIN PRINCIPAL="+resultat);

        return resultatchemin;
    }

   synchronized public ArrayList<caseM> composante_connexe_principal_affiche_chemin_restant(int couleur,int tabfloyd[][]){
        ArrayList<ArrayList<caseM>> R =  return_diff_chemins_couleur(couleur);

        ListIterator<ArrayList<caseM>> E = R.listIterator();

        int resultat=5000;
        int libertetmp=5000;
        ArrayList<caseM> CheminMissionUnQuatre=new ArrayList<caseM>();ArrayList<caseM> CheminMissionDeuxCinq=new ArrayList<caseM>();ArrayList<caseM> CheminMissionTroisSix=new ArrayList<caseM>();
        ArrayList<caseM> CheminMissionUnTroisCinq=new ArrayList<caseM>();ArrayList<caseM> CheminMissionDeuxQuatreSix=new ArrayList<caseM>();
        ArrayList<caseM> minimumtmp1=new ArrayList<caseM>();ArrayList<caseM> minimumtmp2=new ArrayList<caseM>();ArrayList<caseM> minimumtmp3=new ArrayList<caseM>();
        ArrayList<caseM> minimumtmp4=new ArrayList<caseM>();ArrayList<caseM> minimumtmp5=new ArrayList<caseM>();ArrayList<caseM> minimumtmp6=new ArrayList<caseM>();
        ArrayList<caseM> minimumversUNnoir=new ArrayList<caseM>();ArrayList<caseM> minimumversDEUXnoir=new ArrayList<caseM>();ArrayList<caseM> minimumversTROISnoir=new ArrayList<caseM>();
        ArrayList<caseM> minimumversQUATREnoir=new ArrayList<caseM>();ArrayList<caseM> minimumversCINQnoir=new ArrayList<caseM>();ArrayList<caseM> minimumversSIXnoir=new ArrayList<caseM>();
       ArrayList<caseM> minimumversUNblanc=new ArrayList<caseM>();ArrayList<caseM> minimumversDEUXblanc=new ArrayList<caseM>();ArrayList<caseM> minimumversTROISblanc=new ArrayList<caseM>();
       ArrayList<caseM> minimumversQUATREblanc=new ArrayList<caseM>();ArrayList<caseM> minimumversCINQblanc=new ArrayList<caseM>();ArrayList<caseM> minimumversSIXblanc=new ArrayList<caseM>();
       int mintmp1=5000,mintmp2=5000,mintmp3=5000,mintmp4=5000,mintmp5=5000,mintmp6=5000;
        int MissionUnQuatre=5000,MissionDeuxCinq=5000,MissionTroisSix=5000,MissionUnTroisCinq=5000,MissionDeuxQuatreSix=5000;
        int minversUNblanc=5000,minversDEUXblanc=5000,minversTROISblanc=5000,minversQUATREblanc=5000,minversCINQblanc=5000,minversSIXblanc=5000;
        int minversUNnoir=5000,minversDEUXnoir=5000,minversTROISnoir=5000,minversQUATREnoir=5000,minversCINQnoir=5000,minversSIXnoir=5000;

        ArrayList<caseM> resultatchemin = null;
        for(int i=0; i<R.size(); i++){
            ArrayList<caseM> composante = E.next();
            ListIterator<caseM> composante_it = composante.listIterator();
            CheminMissionUnQuatre=new ArrayList<caseM>();CheminMissionDeuxCinq=new ArrayList<caseM>();CheminMissionTroisSix=new ArrayList<caseM>();
            CheminMissionUnTroisCinq=new ArrayList<caseM>();CheminMissionDeuxQuatreSix=new ArrayList<caseM>();
            MissionUnQuatre=5000;MissionDeuxCinq=5000;MissionTroisSix=5000;MissionUnTroisCinq=5000;MissionDeuxQuatreSix=5000;
            minimumversUNnoir=new ArrayList<caseM>();minimumversDEUXnoir=new ArrayList<caseM>();minimumversTROISnoir=new ArrayList<caseM>();
            minimumversQUATREnoir=new ArrayList<caseM>();minimumversCINQnoir=new ArrayList<caseM>();minimumversSIXnoir=new ArrayList<caseM>();
           minimumversUNblanc=new ArrayList<caseM>();minimumversDEUXblanc=new ArrayList<caseM>();minimumversTROISblanc=new ArrayList<caseM>();
           minimumversQUATREblanc=new ArrayList<caseM>();minimumversCINQblanc=new ArrayList<caseM>();minimumversSIXblanc=new ArrayList<caseM>();

            minversUNblanc=5000;minversDEUXblanc=5000;minversTROISblanc=5000;minversQUATREblanc=5000;minversCINQblanc=5000;minversSIXblanc=5000;
            minversUNnoir=5000;minversDEUXnoir=5000;minversTROISnoir=5000;minversQUATREnoir=5000;minversCINQnoir=5000;minversSIXnoir=5000;

            if(couleur==1){
                 while(composante_it.hasNext()){
                     caseM x=composante_it.next();
                     minimumtmp1=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 1);
                     mintmp1=minimumtmp1.size();
                     if(mintmp1<minversUNnoir){
                         minversUNnoir=mintmp1;
                         minimumversUNnoir=minimumtmp1;
                     }

                     minimumtmp2=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 2);
                     mintmp2=minimumtmp2.size();
                     if(mintmp2<minversDEUXnoir){
                         minversDEUXnoir=mintmp2;
                         minimumversDEUXnoir=minimumtmp2;
                     }
                     minimumtmp3=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 3);
                     mintmp3=minimumtmp3.size();

                     if(mintmp3<minversTROISnoir){
                         minversTROISnoir=mintmp3;
                         minimumversTROISnoir=minimumtmp3;
                     }

                     minimumtmp4=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 4);
                     mintmp4=minimumtmp4.size();

                     if(mintmp4<minversQUATREnoir){
                         minversQUATREnoir=mintmp4;
                         minimumversQUATREnoir=minimumtmp4;
                     }


                     minimumtmp5=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 5);
                     mintmp5=minimumtmp5.size();

                     if(mintmp5<minversCINQnoir){
                         minversCINQnoir=mintmp5;
                         minimumversCINQnoir=minimumtmp5;
                     }

                     minimumtmp6=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 6);
                     mintmp6=minimumtmp6.size();

                     if(mintmp6<minversSIXnoir){
                         minversSIXnoir=mintmp6;
                         minimumversSIXnoir=minimumtmp6;
                     }
                  }
                  CheminMissionUnTroisCinq=union_array(minimumversUNnoir,minimumversTROISnoir);
                  CheminMissionUnTroisCinq=union_array(CheminMissionUnTroisCinq,minimumversCINQnoir);
                  CheminMissionDeuxQuatreSix=union_array(minimumversDEUXnoir,minimumversQUATREnoir);
                  CheminMissionDeuxQuatreSix=union_array(CheminMissionDeuxQuatreSix,minimumversSIXnoir);

                 MissionUnTroisCinq=CheminMissionUnTroisCinq.size();
                 MissionDeuxQuatreSix=CheminMissionDeuxQuatreSix.size();


                 libertetmp=MissionUnTroisCinq+MissionDeuxQuatreSix;

                 int nbcoupnoirtmp=minimum(MissionUnTroisCinq,MissionDeuxQuatreSix);
                 if(MissionUnTroisCinq<=MissionDeuxQuatreSix){

                     if(f.joueurUn.couleur==couleur && nbcoupnoirtmp<nbcoup_restantJ1){
                         nbcoup_restantJ1=nbcoupnoirtmp;
                         chemin_restant_J1=CheminMissionUnTroisCinq;

                     }
                     if(f.joueurDeux.couleur==couleur && nbcoupnoirtmp<nbcoup_restantJ2){
                         nbcoup_restantJ2=nbcoupnoirtmp;
                         chemin_restant_J2=CheminMissionUnTroisCinq;


                     }
                 }
                else{
                    if(f.joueurUn.couleur==couleur && nbcoupnoirtmp<nbcoup_restantJ1){
                         nbcoup_restantJ1=nbcoupnoirtmp;
                         chemin_restant_J1=CheminMissionDeuxQuatreSix;

                     }
                     if(f.joueurDeux.couleur==couleur && nbcoupnoirtmp<nbcoup_restantJ2){
                         nbcoup_restantJ2=nbcoupnoirtmp;
                         chemin_restant_J2=CheminMissionDeuxQuatreSix;

                     }

                }

            }

            else if(couleur == 2)
            {
                 while(composante_it.hasNext()){
                     caseM x=composante_it.next();
                     minimumtmp1=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 1);
                     mintmp1=minimumtmp1.size();

                     if(mintmp1<minversUNblanc){
                         minversUNblanc=mintmp1;
                         minimumversUNblanc=minimumtmp1;
                     }

                     minimumtmp2=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 2);
                     mintmp2=minimumtmp2.size();
                     if(mintmp2<minversDEUXblanc){
                         minversDEUXblanc=mintmp2;
                            minimumversDEUXblanc=minimumtmp2;
                     }

                     minimumtmp3=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 3);
                     mintmp3=minimumtmp3.size();

                     if(mintmp3<minversTROISblanc){
                         minversTROISblanc=mintmp3;
                        minimumversTROISblanc=minimumtmp3;
                     }


                    minimumtmp4=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 4);
                     mintmp4=minimumtmp4.size();

                     if(mintmp4<minversQUATREblanc){
                         minversQUATREblanc=mintmp4;
                            minimumversQUATREblanc=minimumtmp4;
                     }


                     minimumtmp5=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 5);
                     mintmp5=minimumtmp5.size();

                     if(mintmp5<minversCINQblanc){
                         minversCINQblanc=mintmp5;
                            minimumversCINQblanc=minimumtmp5;
                     }


                     minimumtmp6=plus_court_chemin_case_vers_bord_particulier_retourne_chemin( x, 6);
                     mintmp6=minimumtmp6.size();

                     if(mintmp6<minversSIXblanc){
                         minversSIXblanc=mintmp6;
                            minimumversSIXblanc=minimumtmp6;
                     }
                  }

                 CheminMissionUnQuatre=union_array(minimumversUNblanc,minimumversQUATREblanc);
                 CheminMissionDeuxCinq=union_array(minimumversDEUXblanc,minimumversCINQblanc);
                 CheminMissionTroisSix=union_array(minimumversTROISblanc,minimumversSIXblanc);
                 MissionUnQuatre=CheminMissionUnQuatre.size();
                 MissionDeuxCinq=CheminMissionDeuxCinq.size();
                 MissionTroisSix=CheminMissionTroisSix.size();
                 libertetmp=MissionUnQuatre+MissionDeuxCinq+MissionTroisSix;

                 int nbcoupblanctmp=minimum(MissionUnQuatre,minimum(MissionDeuxCinq,MissionTroisSix));
                 if(MissionUnQuatre<=MissionDeuxCinq && MissionUnQuatre<=MissionTroisSix){
                     if(f.joueurUn.couleur==couleur && nbcoupblanctmp<nbcoup_restantJ1){
                         nbcoup_restantJ1=nbcoupblanctmp;
                         chemin_restant_J1=CheminMissionUnQuatre;

                     }
                     if(f.joueurDeux.couleur==couleur && nbcoupblanctmp<nbcoup_restantJ2){
                         nbcoup_restantJ2=nbcoupblanctmp;
                         chemin_restant_J2=CheminMissionUnQuatre;

                     }
                }
                else if(MissionDeuxCinq<=MissionUnQuatre && MissionDeuxCinq<=MissionTroisSix){
                    if(f.joueurUn.couleur==couleur && nbcoupblanctmp<nbcoup_restantJ1){
                         nbcoup_restantJ1=nbcoupblanctmp;
                         chemin_restant_J1=CheminMissionDeuxCinq;

                     }
                     if(f.joueurDeux.couleur==couleur && nbcoupblanctmp<nbcoup_restantJ2){
                         nbcoup_restantJ2=nbcoupblanctmp;
                         chemin_restant_J2=CheminMissionDeuxCinq;

                     }

                }
                else if(MissionTroisSix<=MissionUnQuatre && MissionTroisSix<=MissionDeuxCinq){
                    if(f.joueurUn.couleur==couleur && nbcoupblanctmp<nbcoup_restantJ1){
                         nbcoup_restantJ1=nbcoupblanctmp;
                         chemin_restant_J1=CheminMissionTroisSix;

                     }
                     if(f.joueurDeux.couleur==couleur && nbcoupblanctmp<nbcoup_restantJ2){
                         nbcoup_restantJ2=nbcoupblanctmp;
                         chemin_restant_J2=CheminMissionTroisSix;

                     }

                }
            }
            
            if(libertetmp<resultat){
                resultat=libertetmp;
                resultatchemin=composante;
            }
        }
        System.out.println("LIBERTE DU CHEMIN PRINCIPAL="+resultat);

        return resultatchemin;

    }
   public ArrayList<caseM> elargissement_composante_principale_depuis_joker(ArrayList<caseM> L,int couleur){
       ListIterator<caseM> it=L.listIterator();
       int [] tabO;
       if(f.joueurUn.couleur==couleur)
           tabO=objectif_bordJ1;
       else
           tabO=objectif_bordJ2;
       while(it.hasNext()){
           caseM next=it.next();
           if(est_joker1_selon_bord(next, tabO[0]) || est_joker1_selon_bord(next, tabO[1]) || (tabO[2]!=0 && est_joker1_selon_bord(next, tabO[2]))){
             Joker1 tmp=retourne_joker1_depuis_case(next);
             ArrayList<caseM> ponts=pont_caseM_couleur(next);
             ListIterator<caseM> ptsit=ponts.listIterator();
             while(ptsit.hasNext()){
                 caseM p=ptsit.next();
                 if(!L.contains(p)&& p.i==tmp.pont1.i && p.j==tmp.pont1.j && p.etat==tmp.pont1.etat){
                     it.add(p);
                     L=elargir_comp_depuis_pont_joker(L,tmp.pont1);
                 }
                 else if(!L.contains(p) && p.i==tmp.pont2.i && p.j==tmp.pont2.j && p.etat==tmp.pont2.etat){
                     it.add(p);

                 }
                 else if(!L.contains(p) && p.i==tmp.pont3.i && p.j==tmp.pont3.j && p.etat==tmp.pont3.etat){
                     it.add(p);
                     L=elargir_comp_depuis_pont_joker(L,tmp.pont3);
                 }


             }
             // puis elargissement des successeurs
             ArrayList<caseM> suc=successeurs_couleur(next);
             ListIterator<caseM> succ=suc.listIterator();
             while(succ.hasNext()){
                 caseM s=succ.next();
                 if(!L.contains(s)){
                     it.add(s);
                     L=elargir_recursif_depuis_suc(L,s);

                 }

             }
           }
       }
       return L;
   }
   public ArrayList<caseM> elargir_recursif_depuis_suc(ArrayList<caseM> L,caseM s){
       ArrayList<caseM> suc=successeurs_couleur(s);
             ListIterator<caseM> succ=suc.listIterator();
             while(succ.hasNext()){
                 caseM next=succ.next();
                 if(!L.contains(next)){
                     L.add(next);
                     L=elargir_recursif_depuis_suc(L,next);

                 }

             }
       return L;
   }
   public ArrayList<caseM> elargir_comp_depuis_pont_joker(ArrayList<caseM> L,caseM pont){
       ArrayList<caseM> suc=successeurs_couleur(pont);
       ListIterator<caseM> it=suc.listIterator();
       while(it.hasNext()){
           caseM n=it.next();
           if(!L.contains(n))
               L.add(n);
       }
       return L;
   }
   synchronized public void jouer_un_coup_IA(){
        int [][] res=new int[Floyd.length][Floyd.length];

        ArrayList<caseM> cheminPrincipalIA,cheminPrincipalEnnemi;

        int[][] floyd_nouveau = remplir_matrice_floyd();

        res=floyd_warshall(floyd_nouveau);

        cheminPrincipalIA=composante_connexe_principale(f.joueurDeux.couleur,res);
        System.out.println("Il reste "+nbcoup_restantJ2+" coups à jouer au Joueur 2 pour gagner.");
        System.out.println("Joueur 2 ( IA) a un chemin principal de longueur: "+cheminPrincipalIA.size());
        cheminPrincipalEnnemi=composante_connexe_principale(f.joueurUn.couleur, res);
        System.out.println("Il reste "+nbcoup_restantJ1+" coups à jouer au Joueur 1 pour gagner.");
        System.out.println("Joueur 1  a un chemin principal de longueur: "+cheminPrincipalEnnemi.size());
        System.out.println("==========================================");
/*
      /*  cheminPrincipalIA=composante_connexe_principal_affiche_chemin_restant(f.joueurDeux.couleur, res);
        System.out.println("Il reste "+nbcoup_restantJ2+" coups à jouer au Joueur 2 pour gagner.");
        ListIterator<caseM> IT=chemin_restant_J2.listIterator();
        System.out.println("-----chemin-----");
        System.out.println("----------------");
        while(IT.hasNext()){
            caseM next=IT.next();
            System.out.println("i:"+next.i+" j: "+next.j);
        }
        System.out.println("----------------");
        System.out.println("----fin chemin--");

        System.out.println("Joueur 2 ( IA) a un chemin principal de longueur: "+cheminPrincipalIA.size());
        System.out.println("----------------");
        cheminPrincipalEnnemi=composante_connexe_principal_affiche_chemin_restant(f.joueurUn.couleur, res);
        System.out.println("Il reste "+nbcoup_restantJ1+" coups à jouer au Joueur 1 pour gagner.");
        ListIterator<caseM> IT2=chemin_restant_J1.listIterator();
        System.out.println("-----chemin-----");
        System.out.println("----------------");
        while(IT2.hasNext()){
            caseM next=IT2.next();
            System.out.println("i:"+next.i+" j: "+next.j);
        }
        System.out.println("----------------");
        System.out.println("----fin chemin--");
        System.out.println("Joueur 1  a un chemin principal de longueur: "+cheminPrincipalEnnemi.size());
        System.out.println("==========================================");*/
    }
   synchronized public boolean __apresCoup() {
        ArrayList<caseM> list=new ArrayList<caseM>();


        //if((list=f.outils.estVictoire_retourne_chemin(f.joueur_courant))!=null) {

        if(f.partie_commencee && (/*list=*/f.outils.estVictoire(f.joueur_courant))/*!=null*/) {
            System.out.println("Joueur "+f.joueur_courant+" gagne.");

//            System.exit(0);
            f.jeu_fini = true;
            f.aire.repaint();
            ArrayList<caseM> L1 = estVictoire_retourne_chemin(f.joueur_courant);
                affichage_list(L1);
            ListIterator<caseM> L=L1.listIterator();
            System.out.println("Resultat size :"+list.size());
            while(L.hasNext()){
                caseM next=L.next();
                System.out.println("Case i:"+next.i+"j :"+next.j);
                f.matrice[next.i][next.j].etat = 5;
            }
            f.aire.repaint();

            new Fen_Fin_partie(f,true,f.joueur_courant);

            return false;
        }
        else if(f.partie_commencee && (/*list=*/f.outils.estDefaite(f.joueur_courant))/*!=null*/) {
            System.out.println("JOUEUR"+f.joueur_courant+" perd.");
//            System.exit(0);
            f.jeu_fini = true;
            f.aire.repaint();
            ArrayList<caseM> L1 = estVictoire_retourne_chemin(f.joueur_courant);
                affichage_list(L1);
            ListIterator<caseM> L=L1.listIterator();
            System.out.println("Resultat size :"+list.size());
            while(L.hasNext()){
                caseM next=L.next();
                System.out.println("Case i:"+next.i+"j :"+next.j);
                f.matrice[next.i][next.j].etat = 5;
            }
            f.aire.repaint();
            if(f.joueur_courant==1){
                new Fen_Fin_partie(f,false,2);
            } else {
                new Fen_Fin_partie(f,false,1);
            }
            
            return false;
        }

        if(f.joueur_courant==1)
            f.joueur_courant=2;
        else
            f.joueur_courant=1;

        f.aire.repaint();

        return true;
    }


   synchronized public boolean __jouerOrdi_1() {
        jouerOrdi_1();
        return __apresCoup();
    }

   synchronized public boolean __jouerUtil(int x, int y) {
        boolean b = false;
        caseM temp = null;
  
f.outils.afficher_matrice_sous_forme_text(2);
        
        for(int i = 0; i < f.N; i++) {
            if(b == false) {
                for(int j=0;j<f.N;j++) {



                    if(f.matrice[i][j].etat!=-1 && f.matrice[i][j].poly.contains(x, y)) {
                        b = true;
                        temp = f.matrice[i][j];
                        // System.out.println("i : "+(temp.i+1)+"j : "+(temp.j+1));
                    }
                }
            }
        }

        //afficher_matrice_sous_forme_text(2);
        if(temp != null && temp.etat == 0 && !estPossibleClic(temp) ){
            f.ajout_message_erreur("Vous ne pouvez pas encore jouer sur le bord");
        }

        if (temp != null && f.outils.estPossibleClic(temp)) {        // rajouter cette variable dans aireDeDessin
            if(f.mode_j2 == f.MODE_ORDI_2 || f.mode_j1 == f.MODE_ORDI_2 || f.mode_j2 == f.MODE_ORDI_3 || f.mode_j1 == f.MODE_ORDI_3){

                int[][] floyd_nouveau = remplir_matrice_floyd();
                Floyd_coup_courant= floyd_warshall(floyd_nouveau);


                if(composante_ia!=null){
                    ancienne_composante_ia=composante_ia;
                    elargissement_composante_principale_depuis_joker(composante_ia, couleur_joueur_adverse());
                    //affichage_list(composante_ia);
                }


                composante_ia=composante_connexe_principale(couleur_joueur_adverse(), Floyd_coup_courant);

               if(composante_ia!=null){

                    for(int i=0;i<6;i++){
                        if(couleur_joueur_adverse()==1) {
                             ListIterator<caseM> yi=composante_ia.listIterator();
                             boolean rencontre=false;
                             while(yi.hasNext()){
                                caseM next=yi.next();
                                   if(Bord_deja_repare_noirs[i]!=null && Bord_deja_repare_noirs[i].i==next.i && Bord_deja_repare_noirs[i].j==next.j && Bord_deja_repare_noirs[i].etat==next.etat)
                                   { rencontre=true;}


                             }
                             if(rencontre==false)
                                 Bord_deja_repare_noirs[i]=null;
                        }else if(couleur_joueur_adverse()==2){
                            ListIterator<caseM> yi=composante_ia.listIterator();
                             boolean rencontre=false;
                             while(yi.hasNext()){
                                caseM next=yi.next();
                                   if(Bord_deja_repare_blancs[i]!=null && Bord_deja_repare_blancs[i].i==next.i && Bord_deja_repare_blancs[i].j==next.j && Bord_deja_repare_blancs[i].etat==next.etat)
                                   { rencontre=true;}


                             }
                             if(rencontre==false)
                                 Bord_deja_repare_blancs[i]=null;


                        }
                    }
                }
                }



            f.outils.changerEtat(temp,f.joueur_courant);
            if(f.mode_j2 == f.MODE_ORDI_2 || f.mode_j1 == f.MODE_ORDI_2 || f.mode_j2 == f.MODE_ORDI_3 || f.mode_j1 == f.MODE_ORDI_3)  {
                //int[][] floyd_nouveau = remplir_matrice_floyd();
                Floyd_coup_courant= floyd_warshall_epure(Floyd_coup_courant,temp.numero_floyd);
                composante_humain = composante_connexe_principale(couleur_joueur_courant(),Floyd_coup_courant);
            }
            //Creation du thread clignotant
           // f.activer_thread_clignotant(temp);

            //Fin thread clignotant

            //afficher_matrice_sous_forme_text(2);

            __apresCoup();

            return true;
        }
        else {
            if(temp != null && (temp.etat == 1 || temp.etat == 2)){
                f.ajout_message_erreur("Cliquez sur une case libre");
            }
            else{
                f.ajout_message_erreur("Cliquez à l'intérieur du plateau");
            }
            return false;
        }
    }
   synchronized void init_composante_humain(caseM temp){
        Floyd_coup_courant= floyd_warshall_epure(Floyd_coup_courant,temp.numero_floyd);
        composante_humain = composante_connexe_principale(couleur_joueur_courant(),Floyd_coup_courant);
    }
   synchronized public caseM proposer_coup(){
       caseM res = null;
        int[][] floyd_nouveau = remplir_matrice_floyd();
        Floyd_coup_courant = floyd_warshall(floyd_nouveau);


        if (composante_ia != null) {
            ancienne_composante_ia = composante_ia;
            elargissement_composante_principale_depuis_joker(composante_ia, couleur_joueur_courant());
            //affichage_list(composante_ia);
        }


        composante_ia = composante_connexe_principale(couleur_joueur_courant(), Floyd_coup_courant);

        if (composante_ia != null) {

            for (int i = 0; i < 6; i++) {
                if (couleur_joueur_courant() == 1) {
                    ListIterator<caseM> yi = composante_ia.listIterator();
                    boolean rencontre = false;
                    while (yi.hasNext()) {
                        caseM next = yi.next();
                        if (Bord_deja_repare_noirs[i] != null && Bord_deja_repare_noirs[i].i == next.i && Bord_deja_repare_noirs[i].j == next.j && Bord_deja_repare_noirs[i].etat == next.etat) {
                            rencontre = true;
                        }


                    }
                    if (rencontre == false) {
                        Bord_deja_repare_noirs[i] = null;
                    }
                } else if (couleur_joueur_courant() == 2) {
                    ListIterator<caseM> yi = composante_ia.listIterator();
                    boolean rencontre = false;
                    while (yi.hasNext()) {
                        caseM next = yi.next();
                        if (Bord_deja_repare_blancs[i] != null && Bord_deja_repare_blancs[i].i == next.i && Bord_deja_repare_blancs[i].j == next.j && Bord_deja_repare_blancs[i].etat == next.etat) {
                            rencontre = true;
                        }


                    }
                    if (rencontre == false) {
                        Bord_deja_repare_blancs[i] = null;
                    }


                }
            }
        }
        res=minimaxOrdi2();
        

       return res;
   }
   synchronized public caseM proposer_coup_avant_debut(){
       int[][] floyd_nouveau = remplir_matrice_floyd();
        Floyd_coup_courant = floyd_warshall(floyd_nouveau);


        if (composante_ia != null) {
            ancienne_composante_ia = composante_ia;
            elargissement_composante_principale_depuis_joker(composante_ia, couleur_joueur_courant());
            //affichage_list(composante_ia);
        }


        composante_ia = composante_connexe_principale(couleur_joueur_courant(), Floyd_coup_courant);

        if (composante_ia != null) {

            for (int i = 0; i < 6; i++) {
                if (couleur_joueur_courant() == 1) {
                    ListIterator<caseM> yi = composante_ia.listIterator();
                    boolean rencontre = false;
                    while (yi.hasNext()) {
                        caseM next = yi.next();
                        if (Bord_deja_repare_noirs[i] != null && Bord_deja_repare_noirs[i].i == next.i && Bord_deja_repare_noirs[i].j == next.j && Bord_deja_repare_noirs[i].etat == next.etat) {
                            rencontre = true;
                        }


                    }
                    if (rencontre == false) {
                        Bord_deja_repare_noirs[i] = null;
                    }
                } else if (couleur_joueur_courant() == 2) {
                    ListIterator<caseM> yi = composante_ia.listIterator();
                    boolean rencontre = false;
                    while (yi.hasNext()) {
                        caseM next = yi.next();
                        if (Bord_deja_repare_blancs[i] != null && Bord_deja_repare_blancs[i].i == next.i && Bord_deja_repare_blancs[i].j == next.j && Bord_deja_repare_blancs[i].etat == next.etat) {
                            rencontre = true;
                        }


                    }
                    if (rencontre == false) {
                        Bord_deja_repare_blancs[i] = null;
                    }


                }
            }
        }
       caseM res=minimaxOrdi2_proposer_coup();
      return res;

   }
    //faire jouer l'ordinateur :
   synchronized public void __continuerParOrdi() {
        if(f.mode_j1 == f.MODE_ORDI_1) //A
            f.outils.__jouerOrdi_1();
        else if(f.mode_j1 == f.MODE_ORDI_2) //B
            f.outils.jouerOrdi_2();
        else if(f.mode_j1 == f.MODE_ORDI_3) //B
            f.outils.jouerOrdi_3();

        if(f.mode_j2 == f.MODE_ORDI_1) //B
            f.outils.__jouerOrdi_1();
        else if(f.mode_j2 == f.MODE_ORDI_2) //B
            f.outils.jouerOrdi_2();
        else if(f.mode_j2 == f.MODE_ORDI_3) //B
            f.outils.jouerOrdi_3();



       //A et B n'arriveront jamais lors d'une même partie mais il faut quand même tester les deux cas...
    }

    //partie débutée par l'ordinateur :
   synchronized public void __commencerParOrdi() {
        if(f.mode_j1 == f.MODE_ORDI_2){
            f.outils.jouerOrdi_2();
        }
        else if(f.mode_j1 == f.MODE_ORDI_1){
            f.outils.__jouerOrdi_1();
        }
        //si ordinateur, continuer à jouer :
        if(f.mode_j2 == f.MODE_ORDI_1) { //ordi contre ordi :
            while(f.outils.__jouerOrdi_1());
        }
        else if(f.mode_j2 == f.MODE_ORDI_2) { //ordi contre ordi :
            f.jeu_fini=false;
            while(f.jeu_fini==false){
                f.outils.jouerOrdi_2();
            }
        }

    }

}

