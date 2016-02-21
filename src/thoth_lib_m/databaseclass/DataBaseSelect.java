/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.sql.*;
import thoth_lib_m.AdditClass;
//simport thoth_lib_m.dataclass.InfoSection;

/**
 *Подготовленные запросы Select для извлечения данных из
 * Базы Данных
 * @author Sirota Dmitry
 */
public class DataBaseSelect 
    extends DataBaseHelper{
    
    private final static String section = 
            "select bo_book.*, inv_book.inv_num, " + 
            "inv_book.bookcase, inv_book.bookshelf, " +
            "inv_book.condition " +
            "from bo_book, inv_book, section " +
            "where inv_book.id_book = bo_book.id_book " +
            "and bo_book.id_section = section.id_section " +
            "and section.id_section = ?;";
    
    //private String selectQw;
    
    private final static String invNumber = 
        "select inv_book.inv_num " + 
        "from inv_book, bo_book " +
        "where bo_book.id_book = inv_book.id_book and " +
        "bo_book.id_book = ?;";
    
    private Statement s = null;
    
    //Конструктор по умолчанию
    public DataBaseSelect() throws SQLException{
        super();
        s = connect.getConnectionC().createStatement();
        //selectQw = "";
    }
    
    /**
     *Получение подготовленного запроса для выборки данных
     * (библиографических и инвентарных) о книгах из базы данных (БД)
     * по номеру раздела, в котором издания располагаются (хранятся)
     */
    public PreparedStatement getSelectSection(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(section);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "getSelectSection");
            ps = null;
        }
        return ps;
    }
    
    /*
    public PreparedStatement getSelectInvNumber(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(invNumber);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "DataBaseSelect.getSelectInvNumber");
        }
        return ps;
    }
    */
    
    /**
     *Выполнение подготовленного запроса по выборке
     * библиографических и инвентарных данных о книгах из БД
     * по номеру раздела, в котором полученные издания хранятся
     */
    public ResultSet selectBooks(PreparedStatement ps, int selectedSection){
        ResultSet rs = null;
        try{
            ps.setInt(1, selectedSection);
            rs =  ps.executeQuery();
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "selectBookS");
        }
        return rs;
    }
    
    /**
     *Для одиночных Select запросов.
     * Принимает в качестве параметра строку с запросом.
     * Возвращает результат выполнения запроса типа Select.
     * @param sql - строка с запросом на выборку данных
     * @return rs - ссылка на объект ResultSet с результатом выборки данных
     */
    public ResultSet selectData(String sql){
        ResultSet rs = null;
        //try-with-resources
        try{
            rs = s.executeQuery(sql);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "selectData");
        }
        return rs;
    }
    
    //
    /**
     *Возвращает объект класса Statement для выполнения запросов.
     * Объект инициализирован при вызове конструктора класса DataBaseSelect.
     * Примечание: к объекту не должен быть применён метод close().
     * @return s - объект класса Statement
     */
    public Statement getS(){
        return this.s;
    }
    //
    
    /**
     *Возвращает объект класса Statement для выполнения запросов.
     * Объект инициализируется при вызове метода.
     * Примечание: метод лучше всего использовать, 
     * если объект класса Statement (s) равен "null",
     * или к нему применён метод close().
     * @return s - объект класса Statement
     */
    public Statement getSNew(){
        try{
            if((s == null) || (s.isClosed())){
                this.s = connect.getConnectionC().createStatement();
            }
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "DataBaseSelect.getSNew");
        }
        return this.s;
    }
    
    /**
     *Закрытие объекта типа Statement
     */
    public void closeS(Statement s){
        try{
            if(s != null){ s.close(); }
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "DataBaseSelect.closeS");
        }
    }
}
