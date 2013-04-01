/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import javax.swing.*;
import java.awt.*;

public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void changeCardIcon(String image, JLabel label) {
        //label = new JLabel(new ImageIcon(getClass().getResource(image)));
        ImageIcon icon = new ImageIcon(getClass().getResource(image));
        label.setIcon(icon);
    }
    
    
}
