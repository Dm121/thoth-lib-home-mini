/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.*;
import thoth_lib_m.databaseclass.*;

/**
 *Вывод книг, содержащихся в каждом из библиотечных разделов
 * @author Sirota Dmitry
 */
public class TableCopies {
    private final TableModel modelCopies;
    private final SortFilterModel sorter;
    private final JTable table;
        
    public TableCopies() throws Exception{
        modelCopies = new TableCopiesModel();
        sorter = new SortFilterModel(modelCopies);
        table = new JTable(sorter);
    }
    
    public ArrayList<Book> listBooks(InfoSection ifS)
        throws Exception{
        DataBaseSelect connect = null;
        PreparedStatement ps = null;
        //
        ArrayList<Book> books = new ArrayList<>();
        Book dataBook;
        //
        //Map bookS = new TreeMap<>(); - создать класс для работы с деревом
        //
        try{
            connect = new DataBaseSelect();
            ps = connect.getSelectSection();
            try(ResultSet rs = connect.selectBookSection(ps, ifS);){
                while(rs.next()){
                    dataBook = new Book(rs.getInt("bo_book.id_book"),
                            rs.getInt("bo_book.id_type"),
                            rs.getInt("bo_book.id_section"));
                    dataBook.getMainData().setAuthors(
                                        rs.getString("bo_book.authors"));
                    dataBook.getMainData().setTitle(
                                        rs.getString("bo_book.title"));
                    dataBook.getDateline().setPublisher(
                                        rs.getString("bo_book.publisher"));
                    dataBook.getDateline().setPlace(
                                        rs.getString("bo_book.place"));
                    dataBook.getDateline().setYear(
                                        rs.getInt("bo_book.year"));
                    dataBook.getAdditData().setNumVolume(
                                        rs.getString("bo_book.num_volume"));
                    dataBook.getAdditData().setNotes(
                                        rs.getString("bo_book.notes"));
                    dataBook.specifyCopyBook(rs.getInt("inv_book.inv_num"));
                    dataBook.getCopyBook().setBookCase(
                                        rs.getString("inv_book.bookcase"));
                    dataBook.getCopyBook().setBookShelf(
                                        rs.getString("inv_book.bookshelf"));
                    dataBook.getCopyBook().setCondition(
                                        rs.getString("inv_book.condition"));
                    books.add(dataBook);
                }
            }
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "TableCopies.listBooks");
        }
        finally{
            if(connect != null){
                if(ps != null){ connect.closeStatement(ps); }
                connect.closeConnection();
            }
        }
        
        return books;
    }
}
