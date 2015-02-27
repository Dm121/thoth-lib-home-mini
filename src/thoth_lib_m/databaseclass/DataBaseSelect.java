/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.sql.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.InfoSection;

/**
 *Подготовленные запросы Select для извлечения данных из
 * Базы Данных
 * @author Sirota Dmitry
 */
public class DataBaseSelect 
    extends DataBaseHelper{
    
    private final static String section = 
            "select bo_book.*, inv_book.[inv_num], " + 
            "inv_book.[bookcase], inv_book.[bookshelf], " +
            "inv_book.[condition] " +
            "from bo_book, inv_book, section " +
            "where inv_book.[id_book] = bo_book.[id_book] " +
            "and bo_book.[id_section] = section.[id_section] " +
            "and section.[id_section] = ?;";
    
    //private String selectQw;
    
    public DataBaseSelect() throws SQLException{
        super();
        //selectQw = "";
    }
    
    public PreparedStatement getSelectSection(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(section);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "getSelectSection");
            ps = null;
        }
        return ps;
    }
    
    public ResultSet selectBookSection(PreparedStatement ps, InfoSection is){
        ResultSet rs = null;
        try{
            ps.setInt(1, is.getIdSection());
            rs =  ps.executeQuery();
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "selectBookSection");
        }
        return rs;
    }
    
    /**
     *Для одиночных Select запросов.
     * Принимает в качестве параметра строку с запросом.
     * Возвращает результат выполнения запроса типа Select.
     * @param sql
     * @return rs
     */
    public ResultSet selectData(String sql){
        ResultSet rs = null;
        //try-with-resources
        try(Statement s = connect.getConnectionC().createStatement()) {
            rs = s.executeQuery(sql);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "selectData");
        }
        return rs;
    }
}
