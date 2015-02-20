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
    
    public MainData(){
        this.authors = "";
        this.title = "Нет назвния";
    }
    
    public MainData(String authors, String title){
        this.authors = authors;
        this.title = title;
    }
    
    public String getAuthors(){
        return this.authors;
    }
    
    public void setAuthors(String authors){
        this.authors = authors;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    @Override
    public String toString(){
        return "Authors of book: " + this.authors + ".\n" +
                "Title of book: " + this.title + ".";
    }
}
