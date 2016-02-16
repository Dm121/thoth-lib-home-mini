/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import java.io.Serializable;
/**
 *Сведения об авторах и названии книги
 * @author Sirota Dmitry
 */
public class MainData implements Serializable{
    private String authors;
    private String title;
    
    /**
     *Конструткор по умолчанию
     */
    public MainData(){
        this.authors = "";
        this.title = "Нет назвния";
    }
    
    /**
     *Конструктор с параметрами. Создаёт объект, хранящий сведения
     * об авторах и названии издания
     * @param authors - строка с именами авторов издания
     * @param title - строка с названием издания
     */
    public MainData(String authors, String title){
        this.authors = authors;
        this.title = title;
    }
    
    /**
     *Возвращает строку с именами авторов издания
     * @return 
     */
    public String getAuthors(){
        return this.authors;
    }
    
    /**
     *Устанавливает строку с именами авторов издания
     * @param authors 
     */
    public void setAuthors(String authors){
        this.authors = authors;
    }
    
    /**
     *Возвращает строку с названием издания
     * @return 
     */
    public String getTitle(){
        return this.title;
    }
    
    /**
     *Устанавливает строку с названием издания
     * @param title 
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     *Возвращает строковое представление объекта
     * (сведения об авторах и название издания)
     * @return 
     */
    @Override
    public String toString(){
        return "Authors of book: " + this.authors + ".\n" +
                "Title of book: " + this.title + ".";
    }
}
