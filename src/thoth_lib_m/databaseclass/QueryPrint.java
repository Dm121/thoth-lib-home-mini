/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.CopyTable;

/**
 *Формирование запросов выбора данных изданий для печати
 * @author Sirota Dmitry
 */
public class QueryPrint extends DataSearch{
    
    private List<CopyTable> booksPrint;     //Данные изданий на печать
    private String nameS;                   //Наименование раздела на печать
    
    /**
     *Конструктор по умолчанию
     * @throws SQLException
     */
    public QueryPrint() throws SQLException{
        super();
        booksPrint = new ArrayList<>();
        nameS = "";
    }
    
    /**
     *Свойство для получения списка библиотечных изданий
     * @return booksPrint - список библиотечных изданий
     */
    public List<CopyTable> getBooksPrint(){
        return this.booksPrint;
    }
    
    /**
     *Свойство для установки значения списка библиотечных изданий
     * @param cpT - новое значение (список)
     */
    public void setBooksPrint(List<CopyTable> cpT){
        this.booksPrint = cpT;
    }
    
    /**
     *Свойство для получения названия библиотечного раздела,
     * список книг которого выводится на печать
     * @return nameS - название библиотечного раздела
     */
    public String getNameS(){
        return this.nameS;
    }
    
    /**
     *Свойство для установки названия библиотечного раздела,
     * список книг которого выводится на печать
     * @param nameSection - новое название библиотечного раздела
     */
    public void setNameS(String nameSection){
        this.nameS = nameSection;
    }
    
    /**
     *Формирование запроса для выбора данных изданий,
     * хранящихся в библиотечном разделе с идентификатором idSection 
     * @param idSection - идентификтор библиотечного раздела
     * @return str_query - строка запроса Select
     */
    public String listSectionCur(int idSection){
        String str_query = "select distinct bo_book.id_book, " + 
                "bo_book.authors, bo_book.title, bo_book.year, " +
                "inv_book.bookcase, inv_book.bookshelf " +
                "from section " +
                "join bo_book on section.id_section = bo_book.id_section " +
                "join inv_book on bo_book.id_book = inv_book.id_book " +
                "where section.id_section = " + idSection + " " +
                "order by 1, 2, 3;";
        return str_query;
    }
    
    /**
     *Формирование запроса для получения наименования 
     * библиотечного раздела с идентификатором idSection.
     * Из указанного раздела производится выборка данных книг
     * по запросу, сформированному методом listSectionCur(int idSection)
     * @param idSection - идентификатр библиотечного раздела
     * @return str_query - строка запроса Select
     */
    public String nameSection(int idSection){
        String str_query = "select distinct section.describe " +
                "from section " +
                "where section.id_section = " + idSection + ";";
        return str_query;
    }
    
    /**
     *Формирование запроса для выбора данных изданий,
     * хранящихся в библиотеке (во всех разделах)
     * @return str_query - строка запроса Select
     */
    public String listLibrary(){
        String str_query = "select distinct bo_book.id_book, " + 
                "bo_book.authors, bo_book.title, bo_book.year, " +
                "inv_book.bookcase, inv_book.bookshelf " +
                "from bo_book " +
                "join inv_book on bo_book.id_book = inv_book.id_book " +
                "order by 1, 2, 3;";
        return str_query;
    }
    
    //Формирование списка библиотечных изданий из выборки данных запроса
    private List<CopyTable> listBooks(ResultSet rs){
        List<CopyTable> lBs = new ArrayList<>();
        CopyTable cpT;
        //
        try{    
            while(rs.next()){
                cpT = new CopyTable(rs.getInt("id_book"));
                cpT.setAuthorsTable(rs.getString("authors"));
                cpT.setTitleTable(rs.getString("title"));
                cpT.setYearTable(rs.getInt("year"));
                cpT.setBookCaseTable(rs.getString("bookcase"));
                cpT.setBookShelfTable(rs.getString("bookshelf"));
                lBs.add(cpT);
            }
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "QueryPrint.listBooks");
        }
        //
        return lBs;
    }
    
    /**
     *Формирование списка библиотечных изданий, выводимого на печать,
     * для раздела с идентификатором idSection
     * @param idSection - идентификатор библиотечного раздела
     * @return flag - true, если метод выполняется без ошибок, иначе false
     */
    public boolean booksSectionCur(int idSection){
        boolean flag = false;
        //
        try(ResultSet rs = this.selectData(this.listSectionCur(idSection))){
            this.setBooksPrint(this.listBooks(rs));
            flag = true;
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "QueryPrint.booksSectionCur");
        }
        //
        return flag;
    }
    
    /**
     *Формирование списка библиотечных изданий, выводимого на печать,
     * для всей библиотеки
     * @return flag - true, если метод выполняется без ошибок, иначе false
     */
    public boolean booksLibrary(){
        boolean flag = false;
        //
        try(ResultSet rs = this.selectData(this.listLibrary())){
            this.setBooksPrint(this.listBooks(rs));
            flag = true;
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "QueryPrint.booksLibrary");
        }
        //
        return flag;
    }
    
    /**
     *Получение имени библиотечного раздела, список книг которого
     * будет выведен на печать
     * @param idSection - идентификатор библиотечного раздела
     * @return flag - true, если метод выполняется без ошибок, иначе false
     */
    public boolean nameSectionCur(int idSection){
        boolean flag = false;
        //
        try(ResultSet rs = this.selectData(this.nameSection(idSection))){
            this.setNameS(rs.getString("describe"));
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "QueryPrint.nameSectionCur");
        }
        //
        return flag;
    }
}
