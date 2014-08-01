
package fenetres;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;


public class Fen_Dessin_regle extends JComponent {
	public Toolkit tool;
	public Image image;
	public String img_path;
	public Dimension screen;
	public Dimension dim_img;
	public BufferedImage img;
	public Fen_Regle f;
    
    public Fen_Dessin_regle(String s,Dimension h, Fen_Regle fen){
    dim_img = h;
    img_path = s;    
    tool = Toolkit.getDefaultToolkit();
    screen = tool.getScreenSize(); 
    f = fen;
    
    
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D drawable = (Graphics2D) g;
        drawable.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        image = tool.getImage(img_path);
        drawable.setColor(Color.white);

        drawable.fillRect(0, 0, dim_img.width+120, dim_img.height);
        drawable.drawImage(image, 60, 0, dim_img.width-120, dim_img.height, this);
        int a = f.position+1;
        String b = new String();
        b =a+"/5";
        drawable.setColor(Color.blue);
        Font ff = new Font(null, Font.PLAIN, 17);
        drawable.setFont(ff);
        drawable.drawString(b, dim_img.width-50, dim_img.height-50);
    }
    
}
