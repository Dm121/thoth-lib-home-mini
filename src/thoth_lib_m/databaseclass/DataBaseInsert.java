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
 *Подготовленные запросы Insert для добавления данных
 * в Базу Данных
 * @author Sirota Dmitry
 */
public class DataBaseInsert 
    extends DataBaseHelper{
    
    private final static String sql_insert_book = 
            "insert into bo_book " +
"(id_book, num_volume, authors, title, publisher, place, year, notes, " +
"id_type, id_section) " +
"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final static String sql_insert_inv = 
            "insert into inv_book " +
"(inv_num, bookcase, bookshelf, condition, id_book) " +
"values (?, ?, ?, ?, ?);";
    //
    
    //
    
    //Конструктор по умолчанию
    public DataBaseInsert() throws SQLException{
        super();
    }
    
    /**
     *Получение подготовленного запроса для добавления
     * библиографических данных книги в базу данных (БД)
     */
    public PreparedStatement getInsertBook(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(sql_insert_book);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "getInsertBook");
            ps = null;
        }
        return ps;
    }
    
    /**
     *Получение подготовленного запроса для добавления
     * инвентарных данных об экземпляре книги в БД
     */
    public PreparedStatement getInsertInv(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(sql_insert_inv);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "getInsertInv");
            ps = null;
        }
        return ps;
    }
    
    /**
     *Выполнение подготовленного запроса по добавлению
     * библиографических данных книги в БД
     */
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
            AdditClass.errorMes(e, "insertBook");
        }
        return flag;
    }
    
    /**
     *Выполнение подготовленного запроса по добавлению
     * инвентарных данных об экземпляре книги в БД
     */
    public boolean insertCopy(PreparedStatement ps, Book copyBook){
        boolean flag = false;
        try{
            ps.setInt(1, copyBook.getCopyBook().getInvNum());
            ps.setString(2, copyBook.getCopyBook().getBookCase());
            ps.setString(3, copyBook.getCopyBook().getBookShelf());
            ps.setString(4, copyBook.getCopyBook().getCondition());
            ps.setInt(5, copyBook.getCopyBook().getIdBook());
            ps.executeUpdate();
            flag = true;
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "insertCopy");
        }
        return flag;
    }
}
