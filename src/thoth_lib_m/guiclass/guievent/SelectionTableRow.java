/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import thoth_lib_m.dataclass.Book;
import thoth_lib_m.guiclass.TableCopies;
import thoth_lib_m.guiclass.CatalogJFrame;
import thoth_lib_m.guiclass.CatalogJElements;
/**
 *Просмотр данных выбранной книги с возможностью их последующего удаления   
 * @author Sirota Dmitry
 */
public class SelectionTableRow implements ListSelectionListener{
    
    private final TableCopies table;
    private final CatalogJFrame frame;
    private final CatalogJElements elem;
    private DelDataButAction delDataButAction;
    private ChangeSecButAction changeSecButAction;
    
    public SelectionTableRow(TableCopies table, 
            CatalogJFrame frame, CatalogJElements elem,
            DelDataButAction delDataButAction,
            ChangeSecButAction changeSecButAction){
        
        this.table = table;
        this.frame = frame;
        this.elem = elem;
        this.delDataButAction = delDataButAction;
        this.changeSecButAction = changeSecButAction;
        
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e){
        
        int i;      //for loop
        int numRow = this.table.getSortTable().getIdBookRecord(
                this.table.getCopyTable().getSelectedRow());
        if(numRow > -1){
             for(i = 0; i < this.frame.getBooks().size(); i++){
                if(numRow == this.frame.getBooks().get(i).getIdBook()){
                    //
                    Book b = this.frame.getBooks().get(i);
                    TextDataElemBook.getDataBook(b, this.elem);
                }
            }
        }
        //
        if(this.delDataButAction != null){
            this.elem.getButtonsMenu().get(1).removeActionListener(
                                                        delDataButAction);
        }
        this.delDataButAction = new DelDataButAction(
                this.elem, this.frame, 
            this.table.getCopyTable().getSelectedRow(), this.table);
        this.elem.getButtonsMenu().get(1).addActionListener(delDataButAction);
        //
        //AdditClass.infoMes("" + numRow + "");
        //
        if(this.changeSecButAction != null){
            this.elem.getButtonsMenu().get(3).removeActionListener(
                                                        changeSecButAction);
        }
        this.changeSecButAction = new ChangeSecButAction(this.frame, 
            this.frame.getTable().getSortTable().getIdBookRecord(
                    this.frame.getTable().getCopyTable().getSelectedRow()));
        this.elem.getButtonsMenu().get(3).addActionListener(changeSecButAction);
        //
        
    }
    
}
