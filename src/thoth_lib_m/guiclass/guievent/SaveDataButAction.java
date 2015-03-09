/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.databaseclass.*;
import thoth_lib_m.guiclass.*;
import thoth_lib_m.dataclass.Book;
import thoth_lib_m.dataclass.CopyTable;

/**
 *Событие для кнопки "Добавить книгу"
 * @author Sirota Dmitry
 */
public class SaveDataButAction implements ActionListener{
    
    private CatalogJFrame frame;
    private CatalogJElements elem;
    private final int selctedSection;
    private TableCopies table;
    
    //
    //Создание книги в текущем разделе
    //
    public SaveDataButAction(CatalogJElements elem,
            int selected, TableCopies t, CatalogJFrame f){
        this.frame = f;
        this.elem = elem;
        this.selctedSection = selected;
        this.table = t;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(this.elem.getIdBook().getText().trim().equals("")){
            insertData(this.elem, this.selctedSection, this.table, this.frame);
        }
        else{
            updateData(this.elem, this.selctedSection, this.table, this.frame);
        }
    }
    
    private void insertData(CatalogJElements elem,
            int selected, TableCopies t, CatalogJFrame f){
        final String sqlOne = "select Max(id_book) from bo_book;";
        final String sqlTwo = "select Max(inv_num) from inv_book;";
        Book b = null;
        CopyTable ctb = null;
        DataBaseInsert dbInsert = null;
        DataBaseSelect dbSelect = null;
        PreparedStatement ps = null;
        //
        try{
            dbSelect = new DataBaseSelect();
            dbInsert = new DataBaseInsert();
            //
            try(ResultSet rs = dbSelect.selectData(sqlOne)){
                if(rs.next()){
                b = new Book((rs.getInt(1) + 1),
                                (elem.getValTypeEdition() + 1), selected);
                b.getMainData().setAuthors(elem.getTextBook().get(0).getText());
                b.getAdditData().setNumVolume(
                                        elem.getTextBook().get(1).getText());
                b.getMainData().setTitle(elem.getTextBook().get(2).getText());
                b.getDateline().setPublisher(
                                        elem.getTextBook().get(3).getText());
                b.getDateline().setPlace(elem.getTextBook().get(4).getText());
                b.getDateline().setYear(elem.getValYearValue());
                b.getAdditData().setNotes(elem.getTextArray().get(0).getText());
                }
            }
            catch(Exception errOne){
                AdditClass.errorMes(errOne, "SaveDataButAction.insertData");
            }
            //
            try(ResultSet rs = dbSelect.selectData(sqlTwo)){
                if((b != null) && (rs.next())){
                    b.specifyCopyBook((rs.getInt(1) + 1));
                    b.getCopyBook().setBookCase(
                                        elem.getTextCopy().get(0).getText());
                    b.getCopyBook().setBookShelf(
                                        elem.getTextCopy().get(1).getText());
                    b.getCopyBook().setCondition(
                                        elem.getTextArray().get(1).getText());
                }
            }
            catch(Exception errTwo){
                AdditClass.errorMes(errTwo, "SaveDataButAction.insertData");
            }
            finally{
                if(dbSelect.getS() != null){
                    dbSelect.closeS(dbSelect.getS());
                }
            }
            //
            try{
                dbInsert.getConnectionDBH().setTransactionIsolation(
                                        Connection.TRANSACTION_SERIALIZABLE);
                dbInsert.getConnectionDBH().setAutoCommit(false);
                ps = dbInsert.getInsertBook();
                dbInsert.insertBook(ps, b);
                ps = dbInsert.getInsertInv();
                dbInsert.insertCopy(ps, b);
                dbInsert.getConnectionDBH().commit();
            }
            catch(SQLException errThree){
                AdditClass.errorMes(errThree, "SaveDataButAction.insertData");
                dbInsert.getConnectionDBH().rollback();
            }
            finally{
                if(ps != null){
                    dbInsert.closeStatement(ps);
                }
            }
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "SaveDataButAction.insertData");
        }
        finally{
            if(dbInsert != null){ dbInsert.closeConnection(); }
            if(dbSelect != null){ dbSelect.closeConnection(); }
        }
        //
        try{
            if(b != null){
                ctb = new CopyTable(b.getIdBook());
                ctb.setAuthorsTable(b.getMainData().getAuthors());
                ctb.setTitleTable(b.getMainData().getTitle());
                ctb.setYearTable(b.getDateline().getYear());
                ctb.setBookCaseTable(b.getCopyBook().getBookCase());
                ctb.setBookShelfTable(b.getCopyBook().getBookShelf());
                t.getSortTable().addRow(ctb);
                t.getSortTable().getIdBookRecord(
                                    (t.getSortTable().getRowsLength() - 1));
                t.getCopyTable().setRowSelectionAllowed(true);
                t.getCopyTable().setRowSelectionInterval(
                        t.getSortTable().getRowsLength() - 1, 
                        t.getSortTable().getRowsLength() - 1);
                f.getBooks().add(b);
            }
        }
        catch(Exception e){
            AdditClass.errorMes(e, "SaveDataButAction.insertData");
        }
    }
    
    private void updateData(CatalogJElements elem,
            int selected, TableCopies t, CatalogJFrame frame){
        int i;   //for loop
        final String invNumber = 
            "select inv_book.inv_num " + 
            "from inv_book, bo_book " +
            "where bo_book.id_book = inv_book.id_book and " +
            "bo_book.id_book = ";   //Указать idBook + ";"
        String mess = "";
        Book b = null;
        CopyTable ctb = null;
        DataBaseUpdate dbUpdate = null;
        DataBaseSelect dbSelect = null;
        PreparedStatement ps = null;
        //
        try{
        //
        try{
            dbUpdate = new DataBaseUpdate();
            dbSelect = new DataBaseSelect();
            b = new Book(Integer.parseInt(elem.getIdBook().getText().trim()),
                    (elem.getValTypeEdition() + 1), selected);
            b.getMainData().setAuthors(elem.getTextBook().get(0).getText());
            b.getAdditData().setNumVolume(elem.getTextBook().get(1).getText());
            b.getMainData().setTitle(elem.getTextBook().get(2).getText());
            b.getDateline().setPublisher(elem.getTextBook().get(3).getText());
            b.getDateline().setPlace(elem.getTextBook().get(4).getText());
            b.getDateline().setYear(elem.getValYearValue());
            b.getAdditData().setNotes(elem.getTextArray().get(0).getText());
        }
        catch(NumberFormatException | SQLException errOne){
            AdditClass.errorMes(errOne, "SaveDataButAction.updateData");
        }
        //
        if((b != null) && (dbSelect != null)){
            try(ResultSet rs = dbSelect.selectData(invNumber 
                                                    + b.getIdBook() + ";")){
                b.specifyCopyBook(rs.getInt(1));
                b.getCopyBook().setBookCase(
                                        elem.getTextCopy().get(0).getText());
                b.getCopyBook().setBookShelf(
                                        elem.getTextCopy().get(1).getText());
                b.getCopyBook().setCondition(
                                        elem.getTextArray().get(1).getText());
            }
            catch(Exception errTwo){
                AdditClass.errorMes(errTwo, "SaveDataButAction.updateData");
            }
            finally{
                dbSelect.closeS(dbSelect.getS());
            }
            //
            try{
                dbUpdate.getConnectionDBH().setTransactionIsolation(
                                        Connection.TRANSACTION_SERIALIZABLE);
                dbUpdate.getConnectionDBH().setAutoCommit(false);
                ps = dbUpdate.getUpdateBook();
                dbUpdate.updateBook(ps, b);
                ps = dbUpdate.getUpdateInv();
                dbUpdate.updateInv(ps, b);
                dbUpdate.getConnectionDBH().commit();
                /*
                ps = dbUpdate.getUpdateBook();
                if(ps != null){
                    dbUpdate.updateBook(ps, b);
                }
                else{
                    mess = "Получено значение null из метода " +
                                "getUpdateBook() для ps.";
                    AdditClass.warningMes(mess, "SaveDataButAction.updateData");
                }
                ps = dbUpdate.getUpdateInv();
                if(ps != null){
                    dbUpdate.updateInv(ps, b);
                }
                else{
                    mess = "Получено значение null из метода " + 
                                "getUpdateInv() для ps.";
                    AdditClass.warningMes(mess, "SaveDataButAction.updateData");
                }
                */
            }
            catch(SQLException errThree){
                AdditClass.errorMes(errThree, "SaveDataButAction.updateData");
                dbUpdate.getConnectionDBH().rollback();
            }
            finally{
                if(ps != null){ dbUpdate.closeStatement(ps); }
                if(dbSelect != null){ dbSelect.closeConnection(); }
                if(dbUpdate != null){ dbUpdate.closeConnection(); }
            }
            
            try{
                ctb = new CopyTable(b.getIdBook());
                ctb.setAuthorsTable(b.getMainData().getAuthors());
                ctb.setTitleTable(b.getMainData().getTitle());
                ctb.setYearTable(b.getDateline().getYear());
                ctb.setBookCaseTable(b.getCopyBook().getBookCase());
                ctb.setBookShelfTable(b.getCopyBook().getBookShelf());
                t.getSortTable().setIArray(
                                    t.getCopyTable().getSelectedRow(), ctb);
                t.getSortTable().getIdBookRecord(
                                    t.getCopyTable().getSelectedRow());
                t.getCopyTable().setRowSelectionAllowed(true);
                t.getCopyTable().setRowSelectionInterval(
                        t.getCopyTable().getSelectedRow(), 
                        t.getCopyTable().getSelectedRow());
                for(i = 0; i < frame.getBooks().size(); i++){
                    if(b.getIdBook() == frame.getBooks().get(i).getIdBook()){
                        frame.getBooks().set(i, b);
                        break;
                    }
                }
            }
            catch(Exception errFour){
                AdditClass.errorMes(errFour, "SaveDataButAction.updateData");
            }
        }
        //
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "SaveDataButAction.updateData");
        }
    }
}
