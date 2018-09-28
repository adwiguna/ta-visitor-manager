/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitorgui;
import Bfish.*;
import javax.swing.JOptionPane;
import tools.LogReport;
/**
 *
 * @author Anggun Dwiguna
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        String a = JOptionPane.showInputDialog(null, "Masukkan teks");
//        String b = JOptionPane.showInputDialog(null, "Masukkan kunci");
//        new Blowfish(a, b).getDecrypt();

//        new LoginPage().setVisible(true);
        
        HomePage hm = new HomePage("MASTER");
        hm.setVisible(true);
        new serialmonitor.ReadyPage().setVisible(true);
        
    }
}
