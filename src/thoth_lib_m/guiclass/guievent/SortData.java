/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import thoth_lib_m.guiclass.TableCopies;

/**
 *Сортировка данных таблицы по выбранному столбцу
 * @author Sirota Dmitry
 */
public class SortData extends MouseAdapter{
    
    private final TableCopies table;
    
    public SortData(TableCopies table){
        this.table = table;
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        //if(e.getClickCount() < 2) return;
                
        int sRow = 0;
                
        //Поиск столбца, на котором был щелчок
        int tableColumn = this.table.getCopyTable().columnAtPoint(
                                    e.getPoint());
                
        //Преобразование столбца в индекс модели
        int modelColumn = this.table.getCopyTable().
                            convertColumnIndexToModel(tableColumn);
        //
        this.table.getCopyTable().clearSelection();
        this.table.getSortTable().fireTableDataChanged();
        //
        //и выполнение сортировки
        if(!this.table.getSortTable().getFlagSort()){
            sRow = this.table.getSortTable().sort(modelColumn);
            this.table.getSortTable().setFlagSort(true);
        }
        else{
            sRow = this.table.getSortTable().reverseSort(modelColumn);
            this.table.getSortTable().setFlagSort(false);
        }
        if(sRow > -1){
            this.table.getCopyTable().setRowSelectionAllowed(true);
            this.table.getCopyTable().setRowSelectionInterval(sRow, sRow);
        }
                
    }
    
}
