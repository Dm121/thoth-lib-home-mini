/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.*;
//import javax.swing.event.TableModelListener;
//import javax.swing.JOptionPane;
import javax.swing.table.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.*;
/**
 *Таблица для отображения данных о имеющихся экзеплярах книг
 * @author Sirota Dmitry
 */
//implement TableModel; extends AbstractTableModel
public class TableCopiesModel extends DefaultTableModel {
    
    int i;      //for loop
    
    private List<CopyTable> copies;
    
    private String[] columnNames = {
            "Авторы",
            "Название",
            "Год",
            "Шкаф",
            "Полка"
        };
    
    public TableCopiesModel() throws Exception{
        super();
        this.copies = new ArrayList<CopyTable>();
        this.setColumnCount(this.columnNames.length);
        this.setColumnIdentifiers(this.columnNames);    //меняем заголовки столбцов
    }
    
    public TableCopiesModel(List<CopyTable> copies) throws Exception{
        super();
        this.copies = copies;
        this.setColumnCount(this.columnNames.length);
        this.setColumnIdentifiers(this.columnNames);    //меняем заголовки столбцов
        this.addArray(this.copies);
    }
    
    private void addArray(List<CopyTable> copies){
        if(copies.size() > 0){
            for(i = 0; i < copies.size(); i++){
                Vector<Object> newRow = new Vector<Object>();
                newRow.add(copies.get(i).getAuthorsTable());
                newRow.add(copies.get(i).getTitleTable());
                newRow.add(copies.get(i).getYearTable());
                newRow.add(copies.get(i).getBookCaseTable());
                newRow.add(copies.get(i).getBookShelfTable());
                this.addRow(newRow);
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
     *Добавление массива из данных об экземплярах книг
     * @param copies - массив, содержащий сведения о книге
     * @throws Exception
     */
    public void addArrayCopies(List<CopyTable> copies) throws Exception{
        if(copies.size() > 0){
            for(i = 0; i < copies.size(); i++){
                Vector<Object> newRow = new Vector<Object>();
                newRow.add(copies.get(i).getAuthorsTable());
                newRow.add(copies.get(i).getTitleTable());
                newRow.add(copies.get(i).getYearTable());
                newRow.add(copies.get(i).getBookCaseTable());
                newRow.add(copies.get(i).getBookShelfTable());
                this.addRow(newRow);
                this.copies.add(copies.get(i));
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
     */
    public void addRow(CopyTable copy) throws Exception{
        Vector<Object> newRow = new Vector<Object>();
        newRow.add(copy.getAuthorsTable());
        newRow.add(copy.getTitleTable());
        newRow.add(copy.getYearTable());
        newRow.add(copy.getBookCaseTable());
        newRow.add(copy.getBookShelfTable());
        this.addRow(newRow);
        this.copies.add(copy);
    }
    
    @Override
    public int getRowCount(){
        return this.copies.size();
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
    
    public int getIdRec(int rowIndex){
        CopyTable copy = this.copies.get(rowIndex);
        return copy.getIdBook();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column){
        //
    }
}
    
