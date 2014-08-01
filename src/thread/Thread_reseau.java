package thread;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import fenetres.Fen_Abandon;
import fenetres.Fen_Fin_partie;
import fenetres.Fen_jeu;






public class Thread_reseau implements Runnable {
    
    int port = 8100;
    ServerSocket listener;
    PrintStream print;
    Socket client ;
    InputStream in;
    Fen_jeu f;
    boolean running = true;


    public Thread_reseau(Fen_jeu fen)
    {
      f=fen;
    }

    @Override
    public void run() {
        
        try {
             try {
                 listener = new ServerSocket(port);
             } catch (Exception e) {
                 listener = new ServerSocket(port+1);
             }

            //System.out.println("Serveur en ecoute sur le port : " +
            //                   listener.getLocalPort());

            if(f.est_serveur)
                f.commentaire.setText("Votre adresse IP est "+f.res.affiche_ip());
        
            client = listener.accept();
            String tmp;
            String fin = new String();
            while(running){
                in = client.getInputStream();
                byte [] buffer = new byte [1024];
                int number;

                while ((number = in.read(buffer)) != -1) {
                    
                  //  System.out.write(buffer, 0, number);
                    tmp = new String(buffer, 0, number);
                    
                    System.out.println(tmp);
                    if(tmp.length() >3)
                        System.out.println(tmp);
                        analyse_commande(tmp);
                        //f.ajout_message_erreur(tmp);
                    
                    
                    
   
                }
                
                
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void fin_thread()
    {
        
        System.out.println("Fermeture des sockets");
        try {
            print.close();
            in.close();
            client.close();
            listener.close();
            
        } catch (Exception e) {
            System.out.println("Erreur listener");}
       
    
    }
    
    public void analyse_commande(String s) throws FileNotFoundException, IOException
    {
       // System.out.println(s);
        String tmp="";
        //tmp=tmp+s.charAt(0)+s.charAt(1)+s.charAt(2)+s.charAt(3)+s.charAt(4)+s.charAt(5);
        tmp = s.substring(0, 4);
      //  System.out.println(tmp+"");


        if(tmp.compareTo("case") == 0){
           int i,j,j_courant=0;
           i = Integer.parseInt(s.substring(6, 7))
                        + Integer.parseInt(s.substring(5, 6))*10;
           j = Integer.parseInt(s.substring(9, 10))
                        + Integer.parseInt(s.substring(8, 9))*10;
           j_courant=Integer.parseInt(s.substring(11, 12)) ;
           if(!f.partie_commencee){
            if(j_courant==1){
             f.matrice[i][j].etat=f.joueurUn.couleur;
             f.matrice[i][j].couleur=f.joueurUn.couleur;
             //f.joueur_courant=1;
            }
             else{
                   //f.joueur_courant=2;
                   f.matrice[i][j].etat=f.joueurDeux.couleur;
                   f.matrice[i][j].couleur=f.joueurDeux.couleur;
             }
             f.Reseau_a_joue=true;
             f.choixnoir.setEnabled(true);
            }
            else{
               if(j_courant==1){
                 f.matrice[i][j].etat=f.joueurDeux.couleur;
                 f.matrice[i][j].couleur=f.joueurDeux.couleur;
                 //f.joueur_courant=1;
               }
                else{
                   //f.joueur_courant=2;
                   f.matrice[i][j].etat=f.joueurUn.couleur;
                   f.matrice[i][j].couleur=f.joueurDeux.couleur;
             }  
             f.Reseau_a_joue=true;
             //System.out.println("joueur courant avant "+f.joueur_courant );
             
             


            }
           if(f.doit_clignote){
                f.arreter_thread_clignotant();
                f.activer_thread_clignotant(f.matrice[i][j]);
           }
           
           
           
           if(f.joueur_courant == 1){
                 f.joueur_courant = 2;
             }else{
                 f.joueur_courant = 1;
             }
             //System.out.println("joueur courant apres "+f.joueur_courant );
           f.aire.repaint();
        }
        
        if(tmp.compareTo("text") == 0){
            f.tchat.add(f.nom_joueur2+": "+s.substring(5, s.length()));
            //System.out.println(s.substring(5, s.length()) +"\n");
            
            //f.ajout_message_erreur(s.substring(5, s.length()));
        }
        
        if(tmp.compareTo("name") == 0){
            f.nom_joueur2 = s.substring(5, s.length());
            f.lab_j2.setText(s.substring(5, s.length()));
            f.aire.repaint();
            if(f.est_serveur){
                f.res.envoyer_Nom(f.nom_joueur1);
            }
        }
        
        if(tmp.compareTo("bool") == 0){
            if(s.substring(5, s.length()).compareTo("true")==0){
                //changer booleen partie commence a true
                f.partie_commencee=true;
                f.choixnoir.setEnabled(false);
                
                if(f.joueur_courant==2){
                     f.joueurDeux.couleur=2;
                     f.joueur_courant=2;
                 }
                 else{
                      f.joueurUn.couleur=2;
                      f.joueur_courant=1;
                 }
                
                
                
                f.Reseau_a_joue=true;
                
            }
            
           // System.out.println(s.substring(5, s.length()));
          
        }

        if(tmp.compareTo("getp") == 0){
            try {
               // System.out.println("getp reçu");
                String ip = s.substring(5,s.length());
              //  System.out.println("je me co à: "+ip);
                Thread.sleep(1000);
                f.res.rejoindre_socket(ip);
                f.Reseau_a_joue=true;
                f.choixnoir.setEnabled(true);
                Thread.sleep(50);
                f.res.envoyer_size(f.N);
            } catch (InterruptedException ex) {
                Logger.getLogger(Thread_reseau.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if(tmp.compareTo("size")==0){
            String taille=s.substring(5,7);
            //f.N=Integer.parseInt(taille);
           int taillint=Integer.parseInt(taille);
           if(taillint==15){

             Fen_jeu f2 = null;
                try {
                    f2 = new Fen_jeu(f.res, false, taillint,null);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Thread_reseau.class.getName()).log(Level.SEVERE, null, ex);
                } 
            f2.run();
            f.tchat.fen.dispose();
            f.f.dispose();
            f=f2;
            }
            
        }
        
        if(tmp.compareTo("comm") == 0)
        {
            if(s.substring(5, s.length()).compareTo("deb0")==0)
            {

                if(f.est_serveur){
                    f.ajout_message_erreur("Un joueur vient de se connecter\n");
                }
                else{
                    f.ajout_message_erreur("Connexion au serveur réussi\n");
                }
            }
            
            
            if(s.substring(5, s.length()).compareTo("end0")==0)
            {
                running = false;
                fin_thread();
                f.res.fin_connexion();
            }

            if(s.substring(5, s.length()).compareTo("vict")==0)
            {
                //comptabilise la victoire
                new Fen_Fin_partie(f, false, f.joueur_courant);
                f.Reseau_a_joue = false;
                System.out.println("Vous avez perdu");
                f.res.fin_connexion();
            }
            if(s.substring(5, s.length()).compareTo("aban")==0)
            {
                f.Reseau_a_joue = false;
                new Fen_Abandon(f, 2); 
                //comptabilise la victoire
                System.out.println("L'autre joueur à abandonne");
                f.ajout_message_erreur("Le joueur adverse à abandonné");
                f.res.fin_connexion();
            }

            if(s.substring(5, s.length()).compareTo("defe")==0)
            {
                f.Reseau_a_joue = false;
                new Fen_Fin_partie(f, true, f.joueur_courant);
                //comptabilise la victoire
                System.out.println("Vous avez gagne");
                f.res.fin_connexion();
            }
 
        }
        f.aire.repaint();
    }
}
