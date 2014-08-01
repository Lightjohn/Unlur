package unlurlol;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 


public class Reseau implements interface_Reseau{
   
    int port =8100;
    ServerSocket listener;
    PrintStream print;
    
    public Reseau()
    {
       
    
    }
 

    @Override
    public void creer_socket() {
        
        try {
            listener = new ServerSocket(port);
            /*System.out.println("Serveur en ecoute sur le port : " +
                               listener.getLocalPort());*/
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @Override
    public void rejoindre_socket(String ip) {
        Socket sock = null ;
        String tmp;
        boolean fin = false;
        Scanner s = new Scanner(System.in);
        try {
          //  System.out.println("Conection a "+ip+":"+port);
            InetAddress addr = InetAddress.getByName(ip);
            sock = new Socket(addr, port);
            print = new PrintStream(sock.getOutputStream());

            print.print("comm deb0");
  
        } catch (Exception e) {
           System.out.println(e);
           
        }
    }

    @Override
    public void envoyer_case(int i, int j, int joueur_courant) {
        String envoi = new String("case");
        String x,y;
        if(i>9)
         x=String.valueOf(i);
        else
            x="0"+String.valueOf(i);
        if(j>9)
         y=String.valueOf(j);
        else
            y="0"+String.valueOf(j);
        envoi = envoi+" "+x+" "+y+" "+joueur_courant;
        print.print(envoi);

    }

    @Override
    public void envoyer_bool(boolean prendrenoir) {
        
        String envoi = new String("bool");
        if(prendrenoir)
            envoi = envoi + " true";

        else
            envoi = envoi + " false";
        //System.out.println(envoi);
        print.print(envoi);
    }
    public void envoyer_size(int taille) {
        print.print("size "+taille);
    }
    @Override
    public void envoyer_texte(String s) {
        print.print("text "+s);
    }
    @Override
    public boolean get_ip(){

       String ip;
       InetAddress host=null;
       try {
           host = InetAddress.getLocalHost();
           print.print("getp "+parse_host(host));
       } catch (UnknownHostException ex) {
           Logger.getLogger(Reseau.class.getName()).log(Level.SEVERE, null, ex);
           
       }catch(NullPointerException nl){
           System.out.println("Impossible de se connecter.");
           
           return false;
           
       }
       return true;
       
      
   }
    public String affiche_ip(){
       InetAddress host=null;
       try {
           host = InetAddress.getLocalHost();
       } catch (UnknownHostException ex) {
           Logger.getLogger(Reseau.class.getName()).log(Level.SEVERE, null, ex);
       }

       String ip = parse_host(host);
       //System.out.println("Votre addresse IP est :"+parse_host(host));
       return ip;

    }


    
    @Override
    public void envoyer_Fin_connexion() {
        print.print("comm end0");
        fin_connexion();
    }

    @Override
    public void envoyer_Victoire()
    {
        print.print("comm vict");
        fin_connexion();
    }

    public void envoyer_Defaite()
    {
        print.print("comm defe");
        fin_connexion();
    }
    
    public void envoyer_Nom(String s)
    {
        print.print("name "+s);
    }
    
    @Override
    public void envoyer_Abandon()
    {
        print.print("comm aban");
        fin_connexion();
    }
    
    @Override
    public String attendre_reception() {
        String tmp;
        String fin = new String();
        boolean fin1 = true;
        while(true && fin1){
        
            try {
            Socket client ;
            client = listener.accept();

            InputStream in = client.getInputStream();
            byte [] buffer = new byte [1024];
            int number;

            while ((number = in.read(buffer)) != -1) {
                //System.out.write(buffer, 0, number);
                tmp = new String(buffer, 1024);
                fin.concat(tmp);
            }
        } catch (IOException ex) {
            System.out.println("deco");
        }
        
        
        
        }
        return fin;
        
    }
    
    @Override
    public void fin_connexion()
    {
        try {
         
            if(print!=null)
                print.close();
            if(listener!=null){
                listener.close();
                System.out.println("Connexion terminée.");
            }
        } catch (Exception e) {
          
            System.out.println("Erreur à la fermeture des sockets");
        }
       
    }
    
    public String parse_host(InetAddress s)
    {
        String tmp = ""+s;
        String [] tmp1;
        tmp1= tmp.split("/");
        //System.out.println(tmp1[1]);
        return tmp1[1];
    }

    

   
    
    
}
