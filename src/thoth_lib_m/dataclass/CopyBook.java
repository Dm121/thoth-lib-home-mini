/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

/**
 *Данные об экземпляре книги: состояние, 
 * местоположение книги (номер шкафа и номер полки) 
 * @author Sirota Dmitry
 */
public class CopyBook extends FieldID{
    private int invNum;
    private String condition;
    private String bookCase;
    private String bookShelf;
    
    public CopyBook(int idBook, int invNum){
        super(idBook);
        this.invNum = invNum;
        this.condition = "";
        this.bookCase = "-";
        this.bookShelf = "-";
    }
    
    public CopyBook(int idBook, int invNum,
            String condition, String bookCase, String bookShelf){
        super(idBook);
        this.invNum = invNum;
        this.condition = condition;
        this.bookCase = bookCase;
        this.bookShelf = bookShelf;
    }
    
    public int getInvNum(){
        return invNum;
    }
    
    public String getCondition(){
        return this.condition;
    }
    
    public void setCondition(String condition){
        this.condition = condition;
    }
    
    public String getBookCase(){
        return this.bookCase;
    }
    
    public void setBookCase(String bookCase){
        this.bookCase = bookCase;
    }
    
    public String getBookShelf(){
        return this.bookShelf;
    }
    
    public void setBookShelf(String bookShelf){
        this.bookShelf = bookShelf;
    }
    
    @Override
    public String toString(){
        return "[id = " + idBook + "] " +
                "[inventory_number = " + this.invNum + "]\n" +
                "BookCase: " + this.bookCase + ".\n" +
                "BookShelf: " + this.bookShelf + ".\n" +
                "Condition: " + this.condition + ".";
    }
}
