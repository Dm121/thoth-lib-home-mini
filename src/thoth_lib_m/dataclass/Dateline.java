/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *Сведения об издателе, месте издания и годе издания
 * @author Sirota Dmitry
 */
public class Dateline implements Serializable{
    private String publisher;
    private String place;
    private int year;
    //not used
    String filterYear = "Значения года варьируются от 40 до 9999.\n" +
            "Вы ввели значение: " + this.year + ".\n" +
            "Поэтому будет использовано значение по умолчанию: 2015.";
    String filterYearTwo = "Значения года варьируются от 40 до 9999.\n" +
            "Вы ввели значение: " + this.year + ".\n" +
            "Поэтому текущее значение: " + this.year + 
            " - изменено не будет.";
    
    /**
     *Конструткор по умолчанию
     */
    public Dateline(){
        this.publisher = "";
        this.place = "";
        this.year = 2015;
    }
    
    /**
     *Конструктор с параметрами. Создаёт объект, хранящий сведения об
     * издателе, месте и годе издания.
     * @param publisher - наименование издателя
     * @param place - место издания
     * @param year - год издания
     */
    public Dateline(String publisher, String place, int year){
        this.publisher = publisher;
        this.place = place;
        if((year >= 40) && (year < 10000)){
            this.year = year;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Значение года может варьироваться от 40 до 9999.\n" +
            "Вы ввели значение: " + year + ".\n" +
            "Поэтому будет использовано значение по умолчанию: 2015.", 
                    "Предупреждение (Warning): ",
                    JOptionPane.WARNING_MESSAGE);
            this.year = 2015;
        }
    }
    
    /**
     *Возвращает строку с наименованием издателя
     * @return 
     */
    public String getPublisher(){
        return this.publisher;
    }
    
    /**
     *Устанавливает наименования издателя
     * @param publisher 
     */
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    
    /**
     *Возвращает строку с местом издания
     * @return 
     */
    public String getPlace(){
        return this.place;
    }
    
    /**
     *Устанавливает место издания
     * @param place 
     */
    public void setPlace(String place){
        this.place = place;
    }
    
    /**
     *Возвращает год издания
     * @return 
     */
    public int getYear(){
        return this.year;
    }
    
    /**
     *Устанавливает год издания
     * @param year 
     */
    public void setYear(int year){
        if((year >= 40) && (year < 10000)){
            this.year = year;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Значение года может варьироваться от 40 до 9999.\n" +
            "Вы ввели значение: " + year + ".\n" +
            "Поэтому текущее значение: " + this.year + 
            " - изменено не будет.", "Предупреждение (Warning): ",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     *Возвращает строковое представление объекта, хранящего сведения об
     * издателе, месте и годе издания
     * @return 
     */
    @Override
    public String toString(){
        return "Name of publisher: " + this.publisher + ".\n" +
                "Place of publication: " + this.place + ".\n" +
                "Year of publication: " + this.year + ".";
    }
}
