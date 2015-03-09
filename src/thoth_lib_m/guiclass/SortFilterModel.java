/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.List;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.table.*;
//import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.CopyTable;
/**
 *Сортировка для строк основной таблицы,
 * содержащей сведения об экземплярах
 * @author Sirota Dmitry
 */
public class SortFilterModel extends AbstractTableModel{
    
    private TableCopiesModel model = null;
    private int sortColumn;
    private Row[] rows;
    private boolean flagSort;
    
    public SortFilterModel(TableCopiesModel m){
        this.model = m;
        this.flagSort = false;
        setRows();
    }
    
    private void setRows(){
        int i; //for loop
        if(this.model != null){
            rows = new Row[model.getRowCount()];
            for(i = 0; i < rows.length ; i++){
                rows[i] = new Row();
                rows[i].index = i;
            }
        }
    }
    
    public void setRowsM(){
        setRows();
    }
    
    private Row[] copyAddArray(int count, Row[] array){
        int i; //for loop
        Row[] r = new Row[array.length + count];
        for(i = 0; i < array.length; i++){
            r[i] = array[i];
        }
        for(i = array.length; i < (array.length + count); i++){
            r[i] = new Row();
            r[i].index = i;
        }
        return r;
    }
    
    /**
     *Сортировка строк "от Хорстманна Кея и Корнелла Гари" 
     * @param numColumn - Столбец, значения которого подлежат сортировке
     * @return sRow - номер выделенной строки
     */
    public int sort(int numColumn){
        int i; //for loop
        int sRow = -1;
        this.sortColumn = numColumn;
        //
        Arrays.sort(rows);
        //
        fireTableDataChanged();
        for(i = 0; i < this.rows.length; i++){
            if(this.rows[i].selected == true){
                sRow = i;
            }
        }
        return sRow;
    }
    
    /**
     *Сортировка строк в обратном порядке
     * @param numColumn - Столбец, значения которого подлежат сортировке
     * @return sRow - номер выделенной строки
     */
    public int reverseSort(int numColumn){
        int i; //for loop
        int sRow = -1;
        this.sortColumn = numColumn;
        //
        Arrays.sort(rows, Collections.reverseOrder());
        //
        fireTableDataChanged();
        for(i = 0; i < this.rows.length; i++){
            if(this.rows[i].selected == true){
                sRow = i;
            }
        }
        return sRow;
    }
    
    public boolean getFlagSort(){
        return this.flagSort;
    }
    
    public void setFlagSort(boolean flag){
        this.flagSort = flag;
    }
    
    /*
    public Row[] getRows(){
        return this.rows;
    }
    */
    
    public int getRowsLength(){
        return this.rows.length;
    }
    
    /**
     *Получение значений из ячеек таблицы
     * @param row - индекс строки
     * @param column - индекс столбца
     * @return объект типа Object, null - если в таблице нет строк
     */
    @Override
    public Object getValueAt(int row, int column){
        if((row > -1) && (column > -1)){
            return model.getValueAt(this.rows[row].index, column);
        }
        else { return null; }
    }
    
    @Override
    public boolean isCellEditable(int row, int column){
        return model.isCellEditable(this.rows[row].index, column);
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column){
        model.setValueAt(aValue, this.rows[row].index, column);
    }
    
    @Override
    public int getRowCount() { return model.getRowCount(); }
    
    @Override
    public int getColumnCount() { return model.getColumnCount();  }
    
    @Override
    public String getColumnName(int column){
        return model.getColumnName(column);
    }
    
    @Override
    public Class getColumnClass(int column){
        return model.getColumnClass(column);
    }
    
    /**
     *Получает id книги
     * @param row - индекс строки
     * @return id книги, -1 - если в таблице нет строк
     */
    public int getIdBookRecord(int row){
        int i; //for loop
        if((model instanceof TableCopiesModel) && (row > -1)){
            for(i = 0; i < this.rows.length; i++){
                if(i != row) { this.rows[i].selected = false; }
                else{ this.rows[i].selected = true; }
            }
            //AdditClass.infoMes("" + row);
            return ((TableCopiesModel)model).getIdRec(this.rows[row].index);
        }
        else { return -1; }
    }
    
    public void addArrayCopies(List<CopyTable> copies) throws Exception{
        if(model instanceof TableCopiesModel){
            ((TableCopiesModel)model).addArrayCopies(copies);
        }
        this.rows = this.copyAddArray(copies.size(), rows);
    }
    
    public void addRow(CopyTable copy) throws Exception{
        if(model instanceof TableCopiesModel){
            ((TableCopiesModel)model).addRow(copy);
        }
        this.rows = this.copyAddArray(1, rows);
    }
    
    public CopyTable getIArray(int row) throws Exception{
        CopyTable copy = new CopyTable(-1);
        if(model instanceof TableCopiesModel){
            copy = ((TableCopiesModel)model).getIArray(row);
        }
        return copy;
    }
    
    public void setIArray(int row, CopyTable copy) throws Exception{
        if(model instanceof TableCopiesModel){
            ((TableCopiesModel)model).setIArray(row, copy);
        }
    }
    
    public void clearTable(){
        if(model instanceof TableCopiesModel){
            ((TableCopiesModel)model).clearTable();
        }
    }
    
    private class Row implements Comparable<Row>{
        public int index;
        public boolean selected;
        
        @Override
        public int compareTo(Row other){
            Object a = model.getValueAt(index, sortColumn);
            Object b = model.getValueAt(other.index, sortColumn);
            if(a instanceof Comparable){
                return ((Comparable) a).compareTo(b);
            }
            else{
                return a.toString().compareToIgnoreCase(b.toString());
            }
        }
    }
}
