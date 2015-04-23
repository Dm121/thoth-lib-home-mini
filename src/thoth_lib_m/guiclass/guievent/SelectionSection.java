/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.CopyTable;
import thoth_lib_m.dataclass.InfoSection;
import thoth_lib_m.guiclass.TableCopies;
import thoth_lib_m.guiclass.CatalogJFrame;
import thoth_lib_m.guiclass.CatalogJElements;
import thoth_lib_m.guiclass.Section;

/**
 *Просмотр содержимого выбранного библиотечного раздела
 * с возможностью добавления и редактирования данных книг 
 * @author Sirota Dmitry
 */
public class SelectionSection implements ListSelectionListener{
    
    private final TableCopies table;
    private final CatalogJFrame frame;
    private final CatalogJElements elem;
    private Section s;
    private SaveDataButAction saveDataButAction;
    
    public SelectionSection(TableCopies table,
            CatalogJFrame frame, CatalogJElements elem,
            Section s, SaveDataButAction saveDataButAction){
        this.table = table;
        this.frame = frame;
        this.elem = elem;
        this.s = s;
        this.saveDataButAction = saveDataButAction;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e){
        
        int sRowS = 0;
            try {
                Integer selectedIndex = this.s.getSection().getSelectedIndex();
                InfoSection ifS = this.s.getArrayISection(selectedIndex);
                this.frame.setBooks(TableCopies.listBooks(ifS.getIdSection()));
                ArrayList<CopyTable> cpB = TableCopies.listCopies(
                                                this.frame.getBooks());
                this.frame.getTable().getSortTable().clearTable();
                this.frame.getTable().getSortTable().addArrayCopies(cpB);
                this.frame.getTable().getSortTable().setRowsM();
                this.frame.getTable().getCopyTable().repaint();
                //
                this.frame.getTable().getSortTable().sort(0);
                this.frame.getTable().getSortTable().setFlagSort(true);
                //
                if(this.frame.getBooks().size() > 0){
                    TextDataElemBook.getDataBook(this.frame.getBooks().get(0), 
                                                                    this.elem);
                    this.frame.getTable().getCopyTable().
                                                setRowSelectionAllowed(true);
                    this.frame.getTable().getSortTable().getIdBookRecord(sRowS);
                    this.frame.getTable().getCopyTable().
                                                setRowSelectionInterval(0, 0);
                }
                else{
                    TextDataElemBook.emptyDataBook(this.elem);
                }
                //
                this.s.setSelectedS(s.getSection().getSelectedIndex());
                if(this.saveDataButAction != null){
                    this.elem.getButtonsMenu().get(2).removeActionListener(
                                                        this.saveDataButAction);
                }
                this.saveDataButAction = new SaveDataButAction(elem, 
                        s.getArrayISection(s.getSection().
                                        getSelectedIndex()).getIdSection(),
                                                        this.table, this.frame);
                this.elem.getButtonsMenu().get(2).addActionListener(
                                            this.saveDataButAction);
                //
            }catch(Exception err){
                AdditClass.errorMes(err, "CatalogJFrame.createGUI");
            }
        
    }
    
    
}
