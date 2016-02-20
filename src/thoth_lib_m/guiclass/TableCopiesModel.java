/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.*;
import javax.swing.table.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.*;

/**
 *Модель таблицы для работы с данными экземпляров книг,
 * загружаемыми из Базы Данных для каждого раздела
 * @author Sirota Dmitry
 */
public class TableCopiesModel extends AbstractTableModel{
    int i;      //for loop
    
    private List<CopyTable> copies;
    
    private String[] columnNames = {
            "Авторы",
            "Название",
            "Год",
            "Шкаф",
            "Полка"
        };
    
    private Vector<Object> typeValColumn;
    
    public TableCopiesModel(List<CopyTable> copies){
        super();
        this.copies = copies;
        this.typeValColumn = new Vector<>();
        this.typeValColumn.add(0, String.class);
        this.typeValColumn.add(1, String.class);
        this.typeValColumn.add(2, Integer.class);
        this.typeValColumn.add(3, String.class);
        this.typeValColumn.add(4, String.class);
    }
    
    /**
     *Добавление массива из данных об экземплярах книг
     * @param copies - массив, содержащий сведения о книге
     * @throws java.lang.Exception
     */
    public void addArrayCopies(List<CopyTable> copies) throws Exception{
        if(copies.size() > 0){
            for(i = 0; i < copies.size(); i++){
                /*
                Vector<Object> newRow = new Vector<Object>();
                newRow.add(copies.get(i).getAuthorsTable());
                newRow.add(copies.get(i).getTitleTable());
                newRow.add(copies.get(i).getYearTable());
                newRow.add(copies.get(i).getBookCaseTable());
                newRow.add(copies.get(i).getBookShelfTable());
                this.addRow(newRow);
                */
                this.copies.add(copies.get(i));
                this.fireTableDataChanged();
            }
        }
        else{
            String mess = "Данный раздел является пустым.";
            //
            AdditClass.infoMes(mess, "TableCopiesModel.addArray");
            //
        }
    }
    
    /**
     *Добавляет сведения об экземпляре "copy" в таблицу
     * @param copy
     * @throws java.lang.Exception
     */
    public void addRow(CopyTable copy) throws Exception{
        /*
        Vector<Object> newRow = new Vector<Object>();
        newRow.add(copy.getAuthorsTable());
        newRow.add(copy.getTitleTable());
        newRow.add(copy.getYearTable());
        newRow.add(copy.getBookCaseTable());
        newRow.add(copy.getBookShelfTable());
        this.addRow(newRow);
        */
        this.copies.add(copy);
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount(){
        /*
        if((this.copies != null) || (this.copies.isEmpty())){
            return 0;
        }
        */
        return this.copies.size();
    }
    
    @Override
    public int getColumnCount(){
        return this.columnNames.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return this.columnNames[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        CopyTable copy = this.copies.get(rowIndex);
        switch(columnIndex){
            case 0: return copy.getAuthorsTable();
            case 1: return copy.getTitleTable();
            case 2: return copy.getYearTable();
            case 3: return copy.getBookCaseTable();
            case 4: return copy.getBookShelfTable();
        }
        return "";
    }
    
    public CopyTable getIArray(int rowIndex){
        return this.copies.get(rowIndex);
    }
    
    public void setIArray(int rowIndex, CopyTable copy){
        this.copies.set(rowIndex, copy);
        this.fireTableDataChanged();
    }
    
    public void removeAtArray(int rowIndex){
        this.copies.remove(rowIndex);
        this.fireTableDataChanged();
    }
    
    public int getIdRec(int rowIndex){
        CopyTable copy = this.copies.get(rowIndex);
        return copy.getIdBook();
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        Class<?> c = Object.class;
        try{
            c = (Class<?>)this.typeValColumn.get(columnIndex);
        }
        catch(Exception e){
            AdditClass.errorMes(e, "TableCopiesModel.getColumnClass");
        }
        return c;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column){
        //
    }
    
    public void clearTable(){
        this.copies.clear();
        this.fireTableDataChanged();
    }
    
    //Возвращает список с данными изданий, содержащимися в таблице JTable (*1)
    public List<CopyTable> getCopies(){
        return this.copies;
    }
}
