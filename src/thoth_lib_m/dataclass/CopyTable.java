/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

/**
 *Данные для заполнения таблицы "Книги"
 * @author Sirota Dmitry
 */
public class CopyTable extends FieldID{
    private String authors;
    private String title;
    private int year;
    private String bC;
    private String bSh;
    
    public CopyTable(int idBookRec){
        super(idBookRec);
        authors = "";
        title = "";
        year = 2015;
        bC = "-";
        bSh = "-";
    }
    
    public String getAuthorsTable(){
        return this.authors;
    }
    
    public void setAuthorsTable(String authors){
        this.authors = authors;
    }
    
    public String getTitleTable(){
        return this.title;
    }
    
    public void setTitleTable(String title){
        this.title = title;
    }
    
    public int getYearTable(){
        return this.year;
    }
    
    public void setYearTable(int year){
        this.year = year;
    }
    
    public String getBookCaseTable(){
        return this.bC;
    }
    
    public void setBookCaseTable(String bookCase){
        this.bC = bookCase;
    }
    
    public String getBookShelfTable(){
        return this.bSh;
    }
    
    public void setBookShelfTable(String bookShelf){
        this.bSh = bookShelf;
    }
    
    @Override
    public String toString(){
        return "Field 1 (authors): " + this.authors + "\n" +
                "Field 2 (title): " + this.title + "\n" +
                "Field 3 (year): " + this.year + "\n" +
                "Field 4 (bookcase): " + this.bC + "\n" +
                "Field 5 (bookshelf): " + this.bSh + "\n" +
                "Field 6 (idBook): " + this.idBook + ".";
    }
}
