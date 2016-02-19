/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import thoth_lib_m.AdditClass;
import thoth_lib_m.databaseclass.DataBaseInsert;
import thoth_lib_m.dataclass.InfoSection;

/**
 *Класс для заполнения информацией о книжных разделах
 * соответствующей таблицы Базы Данных (БД) - для тестирования (for testing)
 * @author Sirota Dmitry
 */
public class SectionDataInsert extends DataBaseInsert{
    
    private final static String SQL_INSERT_SECTION = 
            "INSERT INTO section (id_section, describe, above_section) " +
            "VALUES (?, ?, ?)";
    
    public SectionDataInsert()throws SQLException{
        super();
    }
    
    public PreparedStatement getInsertSection(){
        PreparedStatement ps = null;
        //
        String nameMethod = "getInsertSection()";        
        //
        try{
            ps = this.getConnectionDBH().prepareStatement(SQL_INSERT_SECTION);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, SectionDataInsert.class.getName() + 
                                                                    nameMethod);
            ps = null;                        
        }
        //
        return ps;
    }
    
    public boolean insertSection(PreparedStatement ps, InfoSection section){
        boolean flag = false;
        //
        String nameMethod = "insertSection(PreparedStatement, InfoSection)";
        //
        try{
            ps.setInt(1, section.getIdSection());
            ps.setString(2, section.getDescribe());
            ps.setInt(3, section.getAboveSection());
            ps.executeUpdate();
            flag = true;
        }
        catch(SQLException e){
            AdditClass.errorMes(e, SectionDataInsert.class.getName() + 
                                                                    nameMethod);
        }
        //
        return flag;
    }
    
}
