/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import thoth_lib_m.AdditClass;
import thoth_lib_m.guiclass.SearchPane;

/**
 *Формирование запроса на поиск библиотечных изданий
 * @author Sirota Dmitry
 */
public class DataSearch extends DataBaseSelect{
    
    /**
     *Конструктор по умолчанию
     */
    public DataSearch() throws SQLException{
        super();
    }
    
    /**
     *Метод для формирования поискового запроса
     * из данных, полученных из элементов вкладки "Поиск"
     */
    public String selectQuery(SearchPane sp){
        int i;      //for loop
        StringBuffer strQuery = new StringBuffer();
        //
        strQuery.append("select distinct bo_book.*, inv_book.inv_num, "
                + "inv_book.bookcase, inv_book.bookshelf, " 
                + "inv_book.condition ");
        strQuery.append("from bo_book join inv_book ");
        strQuery.append("on bo_book.id_book = inv_book.id_book where ");
        //
        if(sp.getOptions().get(0).getSelectedIndex() == 1){
            strQuery.append("not");
        }
        else{ strQuery.append(""); }
        switch(sp.getCriteria().get(0).getSelectedIndex()){
            case 0: {
                strQuery.append(" bo_book.authors like '%");
                strQuery.append(sp.getKeyWords().get(0).getText());
                strQuery.append("%'");
                break;
            }
            case 1: {
                strQuery.append(" bo_book.title like '%");
                strQuery.append(sp.getKeyWords().get(0).getText());
                strQuery.append("%'");
                break;
            }
            case 2: {
                strQuery.append(" bo_book.year like '");
                strQuery.append(sp.getKeyWords().get(0).getText());
                strQuery.append("'");
                break;
            }
            case 3: {
                strQuery.append(" inv_book.bookcase like '%");
                strQuery.append(sp.getKeyWords().get(0).getText());
                strQuery.append("%'");
                break;
            }
            default: {
                AdditClass.warningMes(
                                    "Выход за диапазон возможных значений - 0.", 
                                    "DataSearch.slectQuery");
                break;
            }
        }
        //
        for(i = 1; i < 3; i++){
            if((sp.getCriteria().get(i).getSelectedIndex() != 0) 
                    && (!sp.getKeyWords().get(i).getText().trim().equals(""))){
                switch(sp.getOptions().get(i).getSelectedIndex()){
                    case 0: {strQuery.append(" and"); break;}
                    case 1: {strQuery.append(" or"); break;}
                    case 2: {strQuery.append(" and not"); break;}
                    default: {
                        AdditClass.warningMes(
                                    "Выход за диапазон возможных значений - 1.", 
                                    "DataSearch.slectQuery");
                        break;
                    }
                }
                switch(sp.getCriteria().get(i).getSelectedIndex()){
                    case 0: { strQuery.append(""); break; }
                    case 1: {
                        strQuery.append(" bo_book.authors like '%");
                        strQuery.append(sp.getKeyWords().get(i).getText());
                        strQuery.append("%'");
                        break;
                    }
                    case 2: {
                        strQuery.append(" bo_book.title like '%");
                        strQuery.append(sp.getKeyWords().get(i).getText());
                        strQuery.append("%'");
                        break;
                    }
                    case 3: {
                        strQuery.append(" bo_book.year like '");
                        strQuery.append(sp.getKeyWords().get(i).getText());
                        strQuery.append("'");
                        break;
                    }
                    case 4: {
                        strQuery.append(" inv_book.bookcase like '%");
                        strQuery.append(sp.getKeyWords().get(i).getText());
                        strQuery.append("%'");
                        break;
                    }
                    default: {
                        AdditClass.warningMes(
                                    "Выход за диапазон возможных значений - 2.", 
                                    "DataSearch.slectQuery");
                        break;
                    }
                }
            }  
        }
        strQuery.append(";");
        //
        return strQuery.toString();
    }
    
    /**
     *Закрытие объекта ResultSet с результатами запроса после их обработки
     */
    public void closeResultSet(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }
        catch(SQLException err){
            AdditClass.errorMes(err, "DataSearch.closeResultSet");
        }
    }
}
