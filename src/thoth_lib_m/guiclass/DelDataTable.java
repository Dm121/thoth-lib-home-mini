/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import thoth_lib_m.AdditClass;
import thoth_lib_m.guiclass.guievent.TextDataElemBook;

/**
 *Действия с таблицей при удалении или переносе из одного раздела 
 * в другой данных книги
 * @author Sirota Dmitry
 */

public class DelDataTable {
    
    private final CatalogJFrame frame;
    
    public DelDataTable(CatalogJFrame frame){
        this.frame = frame;
    }
    
    public CatalogJFrame getFrame(){
        return this.frame;
    }
    
    public void deleteDataT(){
        
        if(this.getFrame().getTable().getSortTable().getRowCount() > 0){
            this.delData(this.getFrame());
            this.selectRow(this.getFrame());
        }
        else{
            AdditClass.infoMes("Текущий раздел пуст.");
        }
        
    }
    
    private void delData(CatalogJFrame frame){
        int idSelectedBook;     //id книги, соответствующей выделенной строке
        int selectedNumRow;     //номер выделенной строки таблицы
        int i;                  //for loop
        try{
        idSelectedBook = frame.getTable().getSortTable().getIdBookRecord(
                            frame.getTable().getCopyTable().getSelectedRow());
        selectedNumRow = frame.getTable().getCopyTable().getSelectedRow();
        frame.getTable().getSortTable().removeAtArray(selectedNumRow);
            frame.getTable().getCopyTable().repaint();
            for(i = 0; i < frame.getBooks().size(); i++){
                if(idSelectedBook == frame.getBooks().get(i).getIdBook()){
                    frame.getBooks().remove(i);
                    break;
                }
            }
        }
        catch(Exception e) { 
            AdditClass.errorMes(e, "DelDataTable.delData");
        }
    }
    
    private void selectRow(CatalogJFrame frame){
        int i;                      //for loop
        int idPrevBook;             //id предыдущей книги
        int selectedNumRow;         //номер выделенной строки таблицы
        selectedNumRow = frame.getTable().getCopyTable().getSelectedRow();
        try{
            if(((selectedNumRow == 0) && (frame.getBooks().size() > 1)) || 
                                            (frame.getBooks().size() == 1)){
                frame.getTable().getCopyTable().setRowSelectionAllowed(true);
                frame.getTable().getCopyTable().setRowSelectionInterval(0, 0);
                //
                //получить idBook
                idPrevBook = frame.getTable().getSortTable().getIdBookRecord(0);
                //перебрать массив books (getBooks)
                for(i = 0; i < frame.getBooks().size(); i++){
                    if(idPrevBook == frame.getBooks().get(i).getIdBook()){
                        //загрузить данные нужного издания
                        TextDataElemBook.getDataBook(
                                frame.getBooks().get(i), frame.getElem());
                        break;
                    }
                }
                frame.getTabbedPane().setSelectedIndex(0);
                //
            }
            if((frame.getBooks().size() > 1) && (selectedNumRow > 0)){
                frame.getTable().getCopyTable().setRowSelectionAllowed(true);
                frame.getTable().getCopyTable().setRowSelectionInterval(
                        selectedNumRow - 1, 
                        selectedNumRow - 1);
                //
                //получить idBook
                idPrevBook = frame.getTable().getSortTable().getIdBookRecord(
                                                    selectedNumRow - 1);
                //перебрать массив books (getBooks)
                for(i = 0; i < frame.getBooks().size(); i++){
                    if(idPrevBook == frame.getBooks().get(i).getIdBook()){
                        //загрузить данные нужного издания
                        TextDataElemBook.getDataBook(
                                frame.getBooks().get(i), frame.getElem());
                        break;
                    }
                }
                frame.getTabbedPane().setSelectedIndex(0);
                //
            }
            if(frame.getBooks().size() < 1){
                frame.getTable().getCopyTable().clearSelection();
                TextDataElemBook.emptyDataBook(frame.getElem());
                frame.getTabbedPane().setSelectedIndex(0);
            }
        }
        catch(Exception e){
            AdditClass.errorMes(e, "DelDataTable.selectRow");
        }
    }
}
