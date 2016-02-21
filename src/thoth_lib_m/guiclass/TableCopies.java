/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.*;
import java.sql.*;
import java.awt.Font;
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
    private final TableCopiesModel modelCopies;
    private final SortFilterModel sorter;
    private final JTable table;
        
    public TableCopies(List<CopyTable> list) throws Exception{
        Font font = new Font("Calibri", Font.PLAIN, 16);
        this.modelCopies = new TableCopiesModel(list);
        this.sorter = new SortFilterModel(this.modelCopies);
        this.table = new JTable(this.sorter);
        this.table.setFont(font);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(380);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(80);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(60);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(60);
        this.table.setDefaultRenderer(String.class, new StringRenderer());
        this.table.getTableHeader().setReorderingAllowed(false);
    }
    
    public static ArrayList<Book> listBooks(int selectedSection)
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
            try(ResultSet rs = connect.selectBooks(ps, selectedSection);){
                while(rs.next()){
                    dataBook = new Book(rs.getInt("id_book"),
                            rs.getInt("id_type"),
                            rs.getInt("id_section"));
                    dataBook.getMainData().setAuthors(
                                        rs.getString("authors"));
                    dataBook.getMainData().setTitle(
                                        rs.getString("title"));
                    dataBook.getDateline().setPublisher(
                                        rs.getString("publisher"));
                    dataBook.getDateline().setPlace(
                                        rs.getString("place"));
                    dataBook.getDateline().setYear(
                                        rs.getInt("year"));
                    dataBook.getAdditData().setNumVolume(
                                        rs.getString("num_volume"));
                    dataBook.getAdditData().setNotes(
                                        rs.getString("notes"));
                    dataBook.specifyCopyBook(rs.getInt("inv_num"));
                    dataBook.getCopyBook().setBookCase(
                                        rs.getString("bookcase"));
                    dataBook.getCopyBook().setBookShelf(
                                        rs.getString("bookshelf"));
                    dataBook.getCopyBook().setCondition(
                                        rs.getString("condition"));
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
    
    public static ArrayList<CopyTable> listCopies(List<Book> books){
        
        int i; //for loop
        ArrayList<CopyTable> copies = new ArrayList<>();
        CopyTable copyRec;
        
        for(i = 0; i < books.size(); i++){
            copyRec = new CopyTable(
                    books.get(i).getIdBook());
            copyRec.setAuthorsTable(books.get(i).getMainData().getAuthors());
            copyRec.setTitleTable(books.get(i).getMainData().getTitle());
            copyRec.setYearTable(books.get(i).getDateline().getYear());
            copyRec.setBookCaseTable(
                books.get(i).getCopyBook().getBookCase());
            copyRec.setBookShelfTable(
                books.get(i).getCopyBook().getBookShelf());
            copies.add(copyRec);
        }
        
        return copies;
    }
    
    public SortFilterModel getSortTable(){
        return this.sorter;
    }
    
    public JTable getCopyTable(){
        return this.table;
    }
    
    //Возвращает список с данными изданий, содержащимися в таблице JTable
    // (this.table) для печати (экспорта данных)
    /*
    public List<CopyTable> getListCopyTable(){
        return this.modelCopies.getCopies();
    }
    */
}
