/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Toolkit;
import javax.swing.JTextField;

/**
 *
 * @author vanreach
 */
public class CheckData {
    public static int checktext(JTextField... txt){
        for(JTextField t:txt){
            String st=t.getText();
            st=st.trim();
            if(st.length()==0){
                t.grabFocus();
                Toolkit.getDefaultToolkit().beep();
                return 0;
            }
        }
        return 1;
    }
    public static void input(){
        
    } 
}
