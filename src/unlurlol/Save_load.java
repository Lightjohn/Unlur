/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unlurlol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import fenetres.Fen_jeu;

/**
 *
 * @author adri
 */
public class Save_load {

Fen_jeu f;
    
public Save_load(Fen_jeu fen) throws FileNotFoundException, IOException
    {
        f = fen;
    }


public void Sauver() throws FileNotFoundException, IOException
{
File fichier = new File("Sauvegardes/");
JFileChooser choix = new JFileChooser(fichier.getAbsolutePath());
choix.setApproveButtonText("Choix du fichier");

	if (choix.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            {
            FileWriter fw = new FileWriter(choix.getSelectedFile());
            BufferedWriter br = new BufferedWriter(fw);

            if(f.mode_IA != null)
                {
                    br.write(f.mode_IA.JcIA+"\n");  //variable a recuperer
                    br.write(f.mode_IA.DifficulteIA1+"\n");  //variable a recuperer
                    br.write(f.mode_IA.DifficulteIA2+"\n");  //variable a recuperer
                }
             if(f.mode_IA == null)
                {
                  br.write("false\n");  //variable a recuperer
                  br.write("99\n");  //variable a recuperer
                  br.write("99\n");  //variable a recuperer
                }

              br.write(f.N+"\n");   //on met la taille de la matrice
              br.write(f.joueur_courant+"\n");//on met le joueur courant
              br.write(f.nom_joueur1+"\n");//on met le nom du joueur 1
              if(f.mode_IA == null){br.write(f.nom_joueur2+"\n");}//on met le nom du joueur 2
              else{br.write("Luxo\n");}
              
              br.write(f.joueurUn.couleur+"\n");//couleur du joueur 1
              br.write(f.joueurDeux.couleur+"\n");//couleur du joueur 2

              for(int i = 0 ; i < f.N ; i++)    //on met la matrice
              {for(int j = 0 ; j < f.N ; j++)
                  {br.write(f.matrice[i][j].etat+"\n");}};

                br.flush();
        	br.close();
            }          
}

public void Charger() throws FileNotFoundException, IOException
{
File fichier = new File("Sauvegardes/");
JFileChooser choix = new JFileChooser(fichier.getAbsolutePath());
//System.out.println(fichier.getAbsolutePath());
choix.setApproveButtonText("Charger une partie");

	if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
              FileReader fr = new FileReader(choix.getSelectedFile());
              BufferedReader br = new BufferedReader(fr);
            
         boolean jcia_tmp = Boolean.valueOf(br.readLine());
         int diff1 = Integer.valueOf(br.readLine());
         int diff2 = Integer.valueOf(br.readLine());

         int N_tmp = Integer.valueOf(br.readLine());   //taille de la matrice
         int joueur_courant_tmp = Integer.valueOf(br.readLine());  //joueur courant
         String nom_j1_tmp = new String(br.readLine());   //nom joueur 1
         String nom_j2_tmp = new String(br.readLine());   //nom joueur 2
         int couleur_j1_tmp = Integer.valueOf(br.readLine()); //couleur du joueur 1
         int couleur_j2_tmp = Integer.valueOf(br.readLine()); //couleur du joueur 2

         int [][] mat_tmp = new int[N_tmp][N_tmp];

        for(int i = 0 ; i < N_tmp; i++)    //on met la matrice
              {
                for(int j = 0 ; j < N_tmp ; j++)
                  {
                      System.out.println(i+" "+j);
                      mat_tmp[i][j] = Integer.valueOf(br.readLine());
                  }
              }
              Fen_jeu ff;
          if(jcia_tmp == false){ff = new Fen_jeu(null,false,N_tmp,null);}
          else {ff = new Fen_jeu(null,false,N_tmp,new VariablesIA(jcia_tmp,diff1,diff2));}
              ff.run();
              f.f.dispose();
              ff.N = N_tmp;
              ff.joueur_courant = joueur_courant_tmp;
              ff.nom_joueur1 = nom_j1_tmp;
              ff.nom_joueur2 = nom_j2_tmp;
              ff.lab_j1.setText(nom_j1_tmp);
              ff.lab_j2.setText(nom_j2_tmp);
              ff.joueurUn.couleur = couleur_j1_tmp;
              ff.joueurDeux.couleur = couleur_j2_tmp;
              ff.activer_tuto = false;
              // System.out.println("Coucou");
              
              if(ff.joueurUn.couleur == 2 || ff.joueurDeux.couleur == 2)
              {
                 ff.partie_commencee = true;
                 ff.choixnoir.setEnabled(false);
              }
              
              for(int i = 0 ; i < ff.N; i++)    //on met la matrice
              {
                for(int j = 0 ; j < ff.N ; j++)
                  {
                      ff.matrice[i][j].etat = mat_tmp[i][j];
                      ff.matrice[i][j].couleur = mat_tmp[i][j];
                  }
              }
        }
      /******specif 6x6*****/
        if(f.N == 11)
            {f.abandonner_j1.setBounds(70, 220, 110, 20);
            f.abandonner_j2.setBounds(70, 370, 110, 20);
            f.choixnoir.setBounds(705, 320, 150, 80);
            f.undo.setBounds(705,135,70,70);
            f.redo.setBounds(785,135,70,70);
            f.coup.setBounds(730,210,100,100);
            f.lab_j1.setBounds(20,130,200,40);
            f.lab_j2.setBounds(20, 290,200,40);
            f.commentaire.setBounds(0,20,900,30);
            f.f.setSize(900, 550);}


/******specif 8x8*****/
        else if(f.N == 15) {
            f.abandonner_j1.setBounds(50, 275, 110, 20);
            f.abandonner_j2.setBounds(50, 425, 110, 20);
            f.choixnoir.setBounds(795, 370, 150, 100);
            f.undo.setBounds(790,190,70,70);
            f.redo.setBounds(880,190,70,70);
            f.coup.setBounds(820,270,100,100);
           f.lab_j1.setBounds(2, 190,200,40);
           f.lab_j2.setBounds(2, 345,200,40);
           f.commentaire.setBounds(0,20,975,30);
           f.f.setSize(975, 650);
        }
}
    


}
