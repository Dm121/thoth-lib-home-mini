/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.util.Arrays;
import java.sql.*;
import javax.swing.JOptionPane;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.Book;

/**
 *Подготовленные запросы Insert для добавления данных
 * в Базу Данных
 * @author Sirota Dmitry
 */
public class DataBaseInsert {
    private final static String sql_insert_book = 
            "insert into bo_book " +
"(id_book, num_volume, authors, title, publisher, place, year, notes, " +
"id_type, id_section) " +
"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final static String sql_insert_inv = 
            "insert into inv_book " +
"(inv_num, bookcase, bookshelf, condition, id_book) " +
"values (?, ?, ?, ?, ?);";
    private ConnectionSQLiteDB connect;
    
    public DataBaseInsert() throws SQLException{
        connect = new ConnectionSQLiteDB();
        connect.connDB("db/thoth_lhm_sqlite.db");
    }
    
    public PreparedStatement getInsertBook(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(sql_insert_book);
        }
        catch(SQLException e){
            String regex = ",";
            JOptionPane.showMessageDialog(null, 
                    "Ошибка: (Метод: getInsertBook): \n" +
                            e.getClass().getName() + ": " 
                            + e.getMessage() + "\n" +
                            AdditClass.splitString(regex, 
                                    Arrays.toString(e.getStackTrace())), 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
            ps = null;
        }
        return ps;
    }
    
    public PreparedStatement getInsertInv(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(sql_insert_inv);
        }
        catch(SQLException e){
            String regex = ",";
            JOptionPane.showMessageDialog(null, 
                    "Ошибка: (Метод: getInsertInv): \n" +
                            e.getClass().getName() + ": " 
                            + e.getMessage() + "\n" +
                            AdditClass.splitString(regex, 
                                    Arrays.toString(e.getStackTrace())), 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
            ps = null;
        }
        return ps;
    }
    
    public boolean insertBook(PreparedStatement ps, Book book){
        boolean flag = false;
        try{
            ps.setInt(1, book.getIdBook());
            ps.setString(2, book.getAdditData().getNumVolume());
            ps.setString(3, book.getMainData().getAuthors());
            ps.setString(4, book.getMainData().getTitle());
            ps.setString(5, book.getDateline().getPublisher());
            ps.setString(6, book.getDateline().getPlace());
            ps.setInt(7, book.getDateline().getYear());
            ps.setString(8, book.getAdditData().getNotes());
            ps.setInt(9, book.getIdTypeBook());
            ps.setInt(10, book.getIdSection());
            ps.executeUpdate();
            flag = true;
        }
        catch(SQLException e){
            String regex = ",";
            JOptionPane.showMessageDialog(null, 
                    "Ошибка: (Метод: insertBook): \n" +
                            e.getClass().getName() + ": " 
                            + e.getMessage() + "\n" +
                            AdditClass.splitString(regex, 
                                    Arrays.toString(e.getStackTrace())), 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }
    
    public boolean insertCopy(PreparedStatement ps, Book copyBook){
        boolean flag = false;
        //
        return flag;
    }
}
