/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.sql.*;
import thoth_lib_m.AdditClass;
/**
 *Подготовленный запрос Update для изменения книжного раздела, 
 * в котором находится издание
 * @author Sirota Dmitry
 */
public class ChangeSectionUpdate 
                    extends DataBaseHelper{
    
    private final static String sql_section = "update bo_book "
                                                + "set id_section=? "
                                                + "where id_book=?;";
    
	//Конструктор по умолчанию
    public ChangeSectionUpdate() throws SQLException{
        super();
    }
    
	/**
	 *Метод для выполнения запроса по изменению раздела
	 * для указанного издания
	 */
    public boolean changeSection(int idBook, int idSection){
        boolean flag = false;
        //
        try(PreparedStatement ps = 
                this.connect.getConnectionC().prepareStatement(sql_section)){
            ps.setInt(1, idSection);
            ps.setInt(2, idBook);
            ps.executeUpdate();
            flag = true;
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "ChangeSectionUpdate.changeSection");
        }
        //
        return flag;
    }
}
