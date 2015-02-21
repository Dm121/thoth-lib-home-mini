/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *Сведения о издателе, месте издания и годе издания
 * @author Sirota Dmitry
 */
public class Dateline implements Serializable{
    private String publisher;
    private String place;
    private int year;
    String filterYear = "Значения года варьируются от 40 до 9999.\n" +
            "Вы ввели значение: " + this.year + ".\n" +
            "Поэтому будет использовано значение по умолчанию: 2015.";
    String filterYearTwo = "Значения года варьируются от 40 до 9999.\n" +
            "Вы ввели значение: " + this.year + ".\n" +
            "Поэтому текущее значение: " + this.year + 
            " - изменено не будет.";
    
    public Dateline(){
        this.publisher = "";
        this.place = "";
        this.year = 2015;
    }
    
    public Dateline(String publisher, String place, int year){
        this.publisher = publisher;
        this.place = place;
        if((year >= 40) && (year < 10000)){
            this.year = year;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    filterYear, "Предупреждение (Warning): ",
                    JOptionPane.WARNING_MESSAGE);
            this.year = 2015;
        }
    }
    
    public String getPublisher(){
        return this.publisher;
    }
    
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    
    public String getPlace(){
        return this.place;
    }
    
    public void setPlace(String place){
        this.place = place;
    }
    
    public int getYear(){
        return this.year;
    }
    
    public void setYear(int year){
        if((year >= 40) && (year < 10000)){
            this.year = year;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    filterYearTwo, "Предупреждение (Warning): ",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @Override
    public String toString(){
        return "Name of publisher: " + this.publisher + ".\n" +
                "Place of publication: " + this.place + ".\n" +
                "Year of publication: " + this.year + ".";
    }
}
