/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m;

import thoth_lib_m.dataclass.*;
import javax.swing.JOptionPane;
/**
 *
 * @author 1
 */
public class Thoth_lib_m {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
        Book b1 = new Book(1, 2, 1);
        JOptionPane.showMessageDialog(null, b1.toString());
        b1.specifyCopyBook(3);
        JOptionPane.showMessageDialog(null, b1.toString());
        b1.getMainData().setAuthors("Pushkin A.S.");
        b1.getMainData().setTitle("Captain's daughter");
        b1.getDateline().setYear(1949);
        b1.getAdditData().setNumVolume("1");
        JOptionPane.showMessageDialog(null, b1.toString());
        b1.getCopyBook().setBookCase("2");
        JOptionPane.showMessageDialog(null, b1.getCopyBook().getBookCase());
        JOptionPane.showMessageDialog(null, "Success!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getStackTrace(),
                    "Error: UnSucces: ", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
