/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.CopyTable;
import thoth_lib_m.dataclass.Book;
//import thoth_lib_m.guiclass.CatalogJFrame;

/**
 *Формирование списка найденных книг
 * @author Sirota Dmitry
 */
public class TableSearch extends TableCopies{
    
    public TableSearch(List<CopyTable> list) throws Exception{
        super(list);
    }
    
    public static List<Book> bookSearch(ResultSet rs){
        List<Book> books = new ArrayList<>();
        Book dataBook;
        //
        try{
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
        catch(SQLException err){
            AdditClass.errorMes(err, "TableSearch.bookSearch");
        }
        //
        return books;
    }
    
    public static void fillTableSearch(CatalogJFrame frame, 
                            List<CopyTable> cpB){
        //int sRowS = 0;
        //
        try{
            frame.getTable().getSortTable().clearTable();
            frame.getTable().getSortTable().addArrayCopies(cpB);
            frame.getTable().getSortTable().setRowsM();
            frame.getTable().getCopyTable().repaint();
            //
            frame.getTable().getSortTable().sort(0);
            frame.getTable().getSortTable().setFlagSort(true);
            //
            if(frame.getBooks().size() > 0){
                TextDataElemBook.getDataBook(frame.getBooks().get(0), 
                                                    frame.getElem());
                frame.getTable().getCopyTable().setRowSelectionAllowed(true);
                //frame.getTable().getSortTable().getIdBookRecord(sRowS);
                frame.getTable().getCopyTable().setRowSelectionInterval(0, 0);
            }
            else{
                TextDataElemBook.emptyDataBook(frame.getElem());
            }
        }
        catch(Exception err){
            AdditClass.errorMes(err, "TableSearch.fillTableSearch");
        }
    }
    
}
