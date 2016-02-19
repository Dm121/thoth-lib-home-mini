/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.InfoSection;

/**
 *Класс предназначен для работы с библиотечными разделами
 * @author Sirota Dmitry
 */
public class Section {
    private final int nVisible = 8;
    private final DefaultListModel listModel;
    private final JList section;
    private final JScrollPane scrollSection;
    private String sql;
    private final List<InfoSection> infoSection;
    private int selectedS;
    
    /*
    catch(Exception ex){
                JOptionPane.showMessageDialog(null, 
                    "Error: \n" + ex.getClass().getName() + 
                            "(method: addDataList):" + ex.getMessage(), 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
                err = true;
            }
    */
    
    public Section(){
        this.infoSection = new ArrayList();
        Font fontItems = new Font("Verdana", Font.BOLD, 12);
        listModel = new DefaultListModel(); 
        section = new JList(listModel);
        section.setVisibleRowCount(nVisible);
        section.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        section.setFont(fontItems);
        scrollSection = new JScrollPane(section);
        selectedS = 1;
    }
    
    public boolean addDataList(Connection c) throws SQLException{
        Statement sqw = null;           //для организации запросов
        ResultSet rs = null;            //результат запроса
        boolean err = false;            //для подтверждения ошибки
        if(c == null){
            JOptionPane.showMessageDialog(null, 
                    "Подключение к Базе Данных не установлено.", 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
            err = true;
        }
        else{
            try{
                sqw = c.createStatement();
                sql = "SELECT * FROM section;";
                rs = sqw.executeQuery(sql);
                while(rs.next()){
                    infoSection.add(new InfoSection(rs.getInt("id_section"), 
                            rs.getString("describe"), rs.getInt("above_section")));
                    if(rs.getInt("above_section") >= 1){
                        listModel.addElement("-->" + rs.getString("describe"));
                    }
                    else{
                        listModel.addElement(rs.getString("describe"));
                    }
                }
                section.setSelectedIndex(0);
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, 
                    "Error: \n" + e.getClass().getName() + 
                            "(method: addDataList):" + e.getMessage(), 
                    "Ошибка работы с БД (Error DataBase): ", JOptionPane.ERROR_MESSAGE);
                err = true;
            }
            finally{
                if(rs != null){ rs.close(); }
                if(sqw != null){ sqw.close(); }
            }
        }
        return err;
    }
    
    public boolean insertItemList(Connection c) throws SQLException{
        Statement sqwi = null;           //для организации запросов
        ResultSet rs = null;            //результат запроса
        String nameSection = "";        //название нового раздела
        boolean err = false;            //для подтверждения ошибки
        if(c == null){
            JOptionPane.showMessageDialog(null, 
                    "Подключение к Базе Данных не установлено.", 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
            err = true;
        }
        else{
            try{
                nameSection = (String)JOptionPane.showInputDialog("Введите название нового раздела: ");
            if(nameSection != null){
                if(nameSection.trim().equals("")){
                    nameSection = "Новый раздел";
                }
                sqwi = c.createStatement();
                sql = "SELECT MAX(id_section) FROM section;";
                rs = sqwi.executeQuery(sql);
                
                sql = "INSERT INTO section (id_section, describe, above_section) " +
                        "VALUES (" + (rs.getInt(1) + 1) + ", '" + nameSection +
                        "', 1)";
                            
                infoSection.add(new InfoSection(rs.getInt(1) + 1, 
                            nameSection, 1));
                
                sqwi.executeUpdate(sql);
                               
                
                nameSection = "-->" + nameSection;
                listModel.addElement(nameSection);
                //
                //Выбрать самый последний элемент
                section.setSelectedIndex(listModel.size() - 1);
                //
            }
            }
            catch(SQLException e){
                String regex = ",";
                JOptionPane.showMessageDialog(null, 
                    "Error: \n" + e.getClass().getName() + 
                            "(method: insertItemList):" + e.getMessage() + "\n" +
                            AdditClass.splitString(regex, 
                                    Arrays.toString(e.getStackTrace())), 
                    "Ошибка работы с БД (Error DataBase): ", JOptionPane.ERROR_MESSAGE);
                err = true;
            }
            finally{
                if(rs != null){ rs.close(); }
                if(sqwi != null){ sqwi.close(); }
            }
        }
        return err;
    }
    
    public boolean delItemList(Connection c, Integer selectedIndex) throws SQLException{
        Statement sqwd = null;          //для организации запросов
        ResultSet rs = null;            //результат запроса
        int id;                         //для определения идентификатора раздела
        int yesConfirm;                 //для подтверждения удаления
        boolean err = false;            //для подтверждения ошибки
        id = infoSection.get(selectedIndex).getIdSection();
        if(id == 1){
            JOptionPane.showMessageDialog(null, 
                    "Выбран корневой элемент.", 
                    "Предупреждение (Warning)", JOptionPane.WARNING_MESSAGE);
            err = true;
            return err;
        }
        if(c == null){
            JOptionPane.showMessageDialog(null, 
                    "Подключение к Базе Данных не установлено.", 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
            err = true;
        }
        else{
            if(listModel.getSize() != infoSection.size()){
                JOptionPane.showMessageDialog(null, 
                        "Что-то пошло не так: невозможно удалить элемент: \n" +
                                "число пунктов списка не равно количеству " + 
                                "идентификаторов разделов: " +
                                listModel.getSize() + "!=" + infoSection.size() + ".", 
                        "Ошибка программы (Error): ", JOptionPane.ERROR_MESSAGE);
                err = true;
            }
            else{
                try{
                    sqwd = c.createStatement();
                    sql = "select COUNT(id_book) " +
                        "from bo_book, section " +
                        "where bo_book.id_section = section.id_section and " +
                        "section.id_section = " + id + ";";
                    rs = sqwd.executeQuery(sql);
                    if(rs.getInt(1) <= 0){
                        yesConfirm = JOptionPane.showConfirmDialog(null, 
                                "Удалить раздел?",
                                "Подтверждение на удаление",
                                JOptionPane.YES_NO_OPTION);
                        if(yesConfirm == JOptionPane.YES_OPTION){
                            sql = "delete from section " +
                                    "where section.id_section = " +
                                    id + ";";
                            sqwd.executeUpdate(sql);
                            listModel.remove(selectedIndex);
                            infoSection.remove(infoSection.get(selectedIndex));
                            section.setSelectedIndex(selectedIndex - 1);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, 
                                "Невозможно удалить раздел.\n " + 
                                "Чтобы удалить раздел, он должен быть пустым.", 
                                "Предупреждение (Warning)", JOptionPane.WARNING_MESSAGE);
                        err = true;
                    }
                }
                catch(SQLException e){
                    JOptionPane.showMessageDialog(null, 
                    "Error: \n" + e.getClass().getName() + 
                            "(method: delItemList):" + e.getMessage(), 
                    "Ошибка работы с БД (Error DataBase): ", JOptionPane.ERROR_MESSAGE);
                    err = true;
                }
                finally{
                    if(rs != null){ rs.close(); }
                    if(sqwd != null){ sqwd.close(); }
                }
            }
        }
        return err;
    }
    
    public boolean renameSection(Connection c, Integer selectedIndex)
    throws SQLException{
        Statement sqwu = null;          //для организации запросов
        //ResultSet rs = null;            //результат запроса
        int id;                         //для определения идентификатора раздела
        //int oKRename;                   //для подтверждения переименования
        String newName;                 //новое имя раздела
        boolean err = false;            //для подтверждения ошибки
        //
        id = infoSection.get(selectedIndex).getIdSection();
        if(id == 1){
            JOptionPane.showMessageDialog(null, 
                    "Выбран корневой элемент.", 
                    "Предупреждение (Warning)", JOptionPane.WARNING_MESSAGE);
            err = true;
            return err;
        }
        if(c == null){
            JOptionPane.showMessageDialog(null, 
                    "Подключение к Базе Данных не установлено.", 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
            err = true;
        }
        else{
            if(listModel.getSize() != infoSection.size()){
                JOptionPane.showMessageDialog(null, 
                        "Что-то пошло не так: невозможно переименовать элемент: \n" +
                                "число пунктов списка не равно количеству " + 
                                "идентификаторов разделов: " +
                                listModel.getSize() + "!=" + infoSection.size() + ".", 
                        "Ошибка программы (Error): ", JOptionPane.ERROR_MESSAGE);
                err = true;
            }
            else{
                try{
                    newName = (String)JOptionPane.showInputDialog(null, 
                        (String)section.getSelectedValue(), 
                        "Укажите новое название элемента: ", 
                        JOptionPane.QUESTION_MESSAGE);
                if(newName != null){
                    sqwu = c.createStatement();
                    sql = "update section " +
                        "set describe = '" + newName + "' " +
                        "where id_section = " + id +";";
                    //
                    JOptionPane.showMessageDialog(null, newName + "\n" + id);
                    //
                    sqwu.executeUpdate(sql);
                    infoSection.get(selectedIndex).setDescribe(newName);
                    listModel.removeElementAt(selectedIndex);
                    newName = "-->" + newName;
                    listModel.add(selectedIndex, newName);
                    section.setSelectedIndex(selectedIndex);
                }
                }
                catch(SQLException e){
                    String regex = ",";
                    JOptionPane.showMessageDialog(null, 
                    "Error: \n" + e.getClass().getName() + 
                            "(method: renameSection):" + e.getMessage() + "\n" +
                            AdditClass.splitString(regex, 
                                    Arrays.toString(e.getStackTrace())), 
                    "Ошибка работы с БД (Error DataBase): ", JOptionPane.ERROR_MESSAGE);
                    err = true;
                }
                finally{
                    if(sqwu != null){ sqwu.close(); }
                }
            }
        }
        //
        return err;
    }
    
    /**
     *Возвращает номер текущего элемента списка.
     * Значение устанавливается вручную
     * (пример: <p>"s.setSelectedS(s.getSection().getSelectedIndex())",
     * где s - объект класса Section).
     * @return selectedS - номер текущего элемента списка
     */
    public int getSelectedS(){
        return selectedS;
    }
    
    /**
     *Устанавливает номер текущего элемента списка
     * (пример: <p>"s.setSelectedS = s.getSection.getSelectedIndex()",
     * где s - объект класса Section).
     * @param selected - значение номера текущего элемента списка
     */
    public void setSelectedS(int selected){
        selectedS = selected;
    }
    
    
    public InfoSection getArrayISection(int index){
        if((index > -1) && (index < this.infoSection.size())){
            return infoSection.get(index);
        }
        else{ return this.infoSection.get(0); }
    }
    
    public JList getSection(){
        return section;
    }
    
    public JScrollPane getScrollSection(){
        return scrollSection;
    }
}
