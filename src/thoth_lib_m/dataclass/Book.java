/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import javax.swing.JOptionPane;
/**
 *Совокупность данных о книге
 * @author Sirota Dmitry
 */
public class Book extends FieldID{
    private int idTypeBook;
    private int idSection;
    
    MainData mainData;
    Dateline dateline;
    AdditData additData;
    CopyBook copy;
    
    public Book(int idBook, int idType, int idSection){
        super(idBook);
        this.idTypeBook = idType;
        this.idSection = idSection;
        this.mainData = new MainData();
        this.dateline = new Dateline();
        this.additData = new AdditData();
    }
    
    public int getIdTypeBook(){
        return this.idTypeBook;
    }
    
    public void setTypeBook(int idType){
        this.idTypeBook = idType;
    }
    
    public int getIdSection(){
        return this.idSection;
    }
    
    public void setIdSection(int idSection){
        this.idSection = idSection;
    }
    
    public MainData getMainData(){
        return this.mainData;
    }
    
    public Dateline getDateline(){
        return this.dateline;
    }
    
    public AdditData getAdditData(){
        return this.additData;
    }
    
    public void specifyCopyBook(int invNum){
        this.copy = new CopyBook(this.idBook, invNum);
        //
        JOptionPane.showMessageDialog(null, "Экземпляр книги указан.");
        //
    }
    
    public CopyBook getCopyBook(){
        if(this.copy == null){
            JOptionPane.showMessageDialog(null, 
                    "Экземпляр книги не указан." + 
                            " Вызовите метод: specifyCopyBook.",
                    "Предупреждение (Warning): ",
                    JOptionPane.WARNING_MESSAGE);
        }
        return this.copy;
    }
    
    @Override
    public String toString(){
        String copyNull;
        if(this.copy == null){
            copyNull = "Экземпляр книги не указан.";
        }
        else { copyNull = this.copy.toString(); }
        return "[id of book = " + this.idBook + "]\n" + 
                "Type of Book: " + this.idTypeBook + ".\n" +
                "Number of Section: " + this.idSection + ".\n" +
                this.mainData.toString() + "\n" +
                this.dateline.toString() + "\n" +
                this.additData.toString() + "\n" +
                copyNull;
    }
}
