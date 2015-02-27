/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import thoth_lib_m.dataclass.*;
/**
 *Таблица для отображения данных о имеющихся экзеплярах книг
 * @author Sirota Dmitry
 */
//implement TableModel; extends AbstractTableModel
public class TableCopiesModel extends DefaultTableModel {
    private Set<TableModelListener> listeners = 
            new HashSet<TableModelListener>();  
    
    private List<CopyTable> copies;
    
    public TableCopiesModel(List<CopyTable> copies){
        this.copies = copies;
    }
    
    @Override
    public void addTableModelListener(TableModelListener listener){
        listeners.add(listener);
    }
    
    @Override
    public void removeTableModelListener(TableModelListener listener){
        listeners.remove(listener);
    }
}
    
