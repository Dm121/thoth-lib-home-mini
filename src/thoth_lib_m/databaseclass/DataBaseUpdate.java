/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.sql.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.Book;
/**
 *Подготовленные запросы Update для изменения данных
 * в Базе Данных
 * @author Sirota Dmitry
 */
public class DataBaseUpdate
    extends DataBaseHelper{
    
    private final static String sql_update_book = 
            "update bo_book " +
"set num_volume = ?,  authors = ?, title = ?, " +
"publisher = ?, place = ?, year = ?, " +
"notes = ?, id_type = ? " +
"where id_book = ?;";
    private final static String sql_update_inv = 
            "update inv_book " +
"set bookcase = ?, bookshelf = ?, condition = ? " +
"where id_book = ? and inv_num = ?;";
    //
    
    //
    
    public DataBaseUpdate() throws SQLException{
        super();
    }
    
    public PreparedStatement getUpdateBook(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(sql_update_book);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "getUpdateBook");
            ps = null;
        }
        return ps;
    }
    
    public PreparedStatement getUpdateInv(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(sql_update_inv);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "getUpdateInv");
            ps = null;
        }
        return ps;
    }
    
    public boolean updateBook(PreparedStatement ps, Book book){
        boolean flag = false;
        try{
            ps.setString(1, book.getAdditData().getNumVolume());
            ps.setString(2, book.getMainData().getAuthors());
            ps.setString(3, book.getMainData().getTitle());
            ps.setString(4, book.getDateline().getPublisher());
            ps.setString(5, book.getDateline().getPlace());
            ps.setInt(6, book.getDateline().getYear());
            ps.setString(7, book.getAdditData().getNotes());
            ps.setInt(8, book.getIdTypeBook());
            ps.setInt(9, book.getIdBook());
            ps.executeUpdate();
            flag = true;
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "updateBook");
        }
        return flag;
    }
    
    public boolean updateInv(PreparedStatement ps, Book book){
        boolean flag = false;
        try{
            ps.setString(1, book.getCopyBook().getBookCase());
            ps.setString(2, book.getCopyBook().getBookShelf());
            ps.setString(3, book.getCopyBook().getCondition());
            ps.setInt(4, book.getCopyBook().getIdBook());   //.getIdBook()
            ps.setInt(5, book.getCopyBook().getInvNum());
            ps.executeUpdate();
            flag = true;
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "updateInv");
        }
        return flag;
    }
}
