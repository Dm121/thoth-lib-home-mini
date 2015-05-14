/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent.section_event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.databaseclass.ConnectionSQLiteDB;
import thoth_lib_m.guiclass.Section;

/**
 *Событие для кнопки "Удалить раздел"
 * @author Sirota Dmitry
 */
public class DelSecButAction implements ActionListener{
    
    private Section s;
    private ConnectionSQLiteDB connect;
    
    private final static String sql_conn = "db/thoth_lhm_sqlite.db";
    
    public DelSecButAction(Section s){
        this.s = s;
        this.connect = new ConnectionSQLiteDB();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
       try{
            this.connect.connDB(sql_conn);
            this.s.delItemList(this.connect.getConnectionC(),
                                        this.s.getSection().getSelectedIndex());
        }
        catch(SQLException err){
            AdditClass.errorMes(err, "DelSecButAction.actionPerformed");
        }
        finally{
            if(this.connect.getConnectionC() != null){
                this.connect.closeDB(this.connect.getConnectionC());
            }
        } 
    }
}
