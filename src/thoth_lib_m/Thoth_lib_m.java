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
import javax.swing.table.*;
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
            ConnectionSQLiteDB sqliteDB = new ConnectionSQLiteDB();
            sqliteDB.connDB("db/thoth_lhm_sqlite.db");
            //
            DefaultTableModel myModel = new DefaultTableModel();
            JTable table = new JTable(myModel);
            TableRowSorter<TableModel> sorter = 
                    new TableRowSorter<TableModel>(table.getModel());
            
            //
            CatalogJFrame window = new CatalogJFrame();
            window.createGUI(sqliteDB.getConnectionC());
            window.setShow(true);
            //
            sqliteDB.closeDB(sqliteDB.getConnectionC());
            JOptionPane.showMessageDialog(null, "Success!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getStackTrace(),
                    "Error: UnSucces: ", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
