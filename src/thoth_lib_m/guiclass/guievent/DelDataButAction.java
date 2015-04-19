/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import thoth_lib_m.AdditClass;
import thoth_lib_m.databaseclass.*;
import thoth_lib_m.guiclass.*;
import thoth_lib_m.dataclass.Book;
import thoth_lib_m.dataclass.CopyTable;
/**
 *Удаление данных о книге из Базы Данных
 * @author Sirota Dmitry
 */
public class DelDataButAction implements ActionListener{
    
    private final CatalogJElements elem;
    private final CatalogJFrame frame;
    private final int selectedRow;
    private final TableCopies table;
    
    public DelDataButAction(CatalogJElements elem, CatalogJFrame frame,
                    int selectedRow, TableCopies table){
        this.elem = elem;
        this.frame = frame;
        this.selectedRow = selectedRow;
        this.table = table;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        //
        try{
            deleteData(this.elem, this.frame, this.selectedRow, this.table);
        }
        catch(Exception err){
            AdditClass.errorMes(err, "DelDataButAction.actionPerformed");
        }
        //
    }
    
    private void deleteData(CatalogJElements elem, CatalogJFrame frame,
                    int selectedRow, TableCopies table)
                            throws Exception{
        DataBaseDelete dbDelete = null;
        int i;      //for loop
        int idPrevBook;
        int selDataDel;
        int idSelectedBook;
        //int selectedIndex;
        int selectedNumRow;
        idSelectedBook = table.getSortTable().getIdBookRecord(selectedRow);
        //
        //selectedIndex = table.getSortTable().getRowIndex(selectedRow);
        //
        selectedNumRow = selectedRow;
        //
        selDataDel = JOptionPane.showConfirmDialog(frame, 
                "Удалить данные выбранного издания?", 
                "Подтверждение на удаление: ", 
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if((selDataDel == JOptionPane.YES_OPTION) 
                                        && (frame.getBooks().size() > 0)){
            try{
                dbDelete = new DataBaseDelete();
                dbDelete.deleteBookT(idSelectedBook);
            }
            catch(SQLException e){
                AdditClass.errorMes(e, "DelDataButAction.deleteData");
            }
            table.getSortTable().removeAtArray(selectedNumRow);
            table.getCopyTable().repaint();
            for(i = 0; i < frame.getBooks().size(); i++){
                if(idSelectedBook == frame.getBooks().get(i).getIdBook()){
                    frame.getBooks().remove(i);
                    break;
                }
            }
            //
            //один элемент, первый, удаление -> ошибка
            //
            try{
            if(((selectedNumRow == 0) && (frame.getBooks().size() > 1)) || 
                                            (frame.getBooks().size() == 1)){
                table.getCopyTable().setRowSelectionAllowed(true);
                table.getCopyTable().setRowSelectionInterval(0, 0);
                //
                //получить idBook
                idPrevBook = table.getSortTable().getIdBookRecord(0);
                //перебрать массив books (getBooks)
                for(i = 0; i < frame.getBooks().size(); i++){
                    if(idPrevBook == frame.getBooks().get(i).getIdBook()){
                        //загрузить данные нужного издания
                        TextDataElemBook.getDataBook(
                                frame.getBooks().get(i), elem);
                        break;
                    }
                }
                frame.getTabbedPane().setSelectedIndex(0);
                //
            }
            if((frame.getBooks().size() > 1) && (selectedNumRow > 0)){
                table.getCopyTable().setRowSelectionAllowed(true);
                table.getCopyTable().setRowSelectionInterval(
                        selectedNumRow - 1, 
                        selectedNumRow - 1);
                //
                //получить idBook
                idPrevBook = table.getSortTable().getIdBookRecord(
                                                    selectedNumRow - 1);
                //перебрать массив books (getBooks)
                for(i = 0; i < frame.getBooks().size(); i++){
                    if(idPrevBook == frame.getBooks().get(i).getIdBook()){
                        //загрузить данные нужного издания
                        TextDataElemBook.getDataBook(
                                frame.getBooks().get(i), elem);
                        break;
                    }
                }
                frame.getTabbedPane().setSelectedIndex(0);
                //
            }
            if(frame.getBooks().size() < 1){
                table.getCopyTable().clearSelection();
                TextDataElemBook.emptyDataBook(elem);
                frame.getTabbedPane().setSelectedIndex(0);
            }
            }
            catch(Exception e){
                AdditClass.errorMes(e, "DelDataButAction.deleteData");
            }
        }
        else{
            AdditClass.infoMes("Удаление данных отменено. " +
                        "Возможные причины: \n" +
                        "- удаление отменено пользователем;\n" + 
                        "- раздел пуст.");
        }
    }
    
}
