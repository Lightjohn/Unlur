/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unlurlol;

import java.io.IOException;
import javax.swing.*;
import javax.swing.ImageIcon;

/**
 *
 * @author desgrour
 */
public class ToolBar extends JToolBar{
    
    JToolBar t;
    ImageIcon img_redo, img_undo, img_coup;
    JButton undo, redo, coup;

    public ToolBar() throws IOException{
//        t = new JToolBar();
     //   setPreferredSize(new Dimension(350, 70));
//        add(t, BorderLayout.PAGE_START);

        img_undo = new ImageIcon("images/Undo.png");
        undo = new JButton();
        undo.setIcon(img_undo);
        undo.setFocusPainted(false);
        undo.setBorderPainted(false);
        undo.setContentAreaFilled(false);
        undo.setToolTipText("Annuler le coup");
        undo.setBounds(10, 0, 50, 50);
        add(undo);

        img_redo = new ImageIcon("images/Redo.png");
        redo = new JButton();
        redo.setIcon(img_redo);
        redo.setFocusPainted(false);
        redo.setBorderPainted(false);
        redo.setContentAreaFilled(false);
        redo.setToolTipText("Refaire le dernier coup");
        redo.setBounds(70, 0, 50, 50);
        add(redo);

        img_coup = new ImageIcon("images/Coup.png");
        coup = new JButton();
        coup.setIcon(img_coup);
        coup.setFocusPainted(false);
        coup.setBorderPainted(false);
        coup.setContentAreaFilled(false);
        coup.setToolTipText("Proposition");
        coup.setBounds(190, 0, 50, 50);
        add(coup);

    }

}
