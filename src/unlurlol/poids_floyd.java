/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;

public class poids_floyd{
        public boolean app;
        public int poids;
        public poids_floyd(boolean a, int p){
            app = a;
            poids = p;
        }
        public boolean appartient(){
            return app;
        }
        public int poids(){
            return poids;
        }
    }