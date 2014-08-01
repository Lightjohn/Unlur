package unlurlol;


interface interface_Reseau {

    public void creer_socket();

    public void rejoindre_socket(String ip);

    public void envoyer_case(int i,int j,int joueur_courant);

    public void envoyer_bool(boolean prendrenoir);
    
    public void envoyer_texte(String s);
    
    public void envoyer_Fin_connexion();

    public void envoyer_Victoire();

    public void envoyer_Abandon();
    
    public String attendre_reception();

    public boolean get_ip();
    
    public void fin_connexion();

}
