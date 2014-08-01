package unlurlol;

import java.util.Stack;


public class Pile_objet_save{
   
    public Stack pile;
    public Stack refaire;

    public Pile_objet_save(){
        pile = new Stack<Objet_save>();
        refaire = new Stack<Objet_save>();
    }
    
    public void empiler(Objet_save e){
        pile.push(e);
    }
    public Objet_save depiler(){
        try {
            Objet_save a = (Objet_save) pile.pop();
            return a;
        } catch (Exception e) {
            return null;
        }
        
        
    }
    public Objet_save prendre_dernier(){
        try {
            Objet_save a = (Objet_save) pile.peek();
            return a;
        } catch (Exception e) {
            return null;
        }
    }
   

}
