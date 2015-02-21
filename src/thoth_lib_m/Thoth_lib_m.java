/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m;

import thoth_lib_m.dataclass.*;
import thoth_lib_m.guiclass.*;
import thoth_lib_m.databaseclass.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.FlowLayout;
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
            CatalogJElements elem = new CatalogJElements();
            ConnectionSQLiteDB sqliteDB = new ConnectionSQLiteDB();
            JPanel testPanel = new JPanel();
            JFrame testView = new JFrame();
            testView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testView.setSize(800, 350);
            sqliteDB.connDB("db/thoth_lhm_sqlite.db");
            testPanel.setLayout(new FlowLayout());
            //testPanel.add();
            JScrollPane scroll = new JScrollPane(
                elem.getPanelBook(sqliteDB.getConnectionC()));
            testView.add(
                    scroll);
            testView.setVisible(true);
            sqliteDB.closeDB(sqliteDB.getConnectionC());
            JOptionPane.showMessageDialog(null, "Success!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getStackTrace(),
                    "Error: UnSucces: ", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
