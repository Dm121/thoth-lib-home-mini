/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import java.io.Serializable;

/**
 *Сведения о номере тома и примечания
 * @author Sirota Dmitry
 */
public class AdditData implements Serializable{
    private String numVolume;
    private String notes;
    
    /**
     *Конструктор по умолчанию
     */
    public AdditData(){
        this.numVolume = "";
        this.notes = "";
    }
    
    /**
     *Конструктор с параметрами. Создаёт объект, хранящий сведения о
     * номере тома и примечания к изданию
     * @param num - номер тома
     * @param notes - примечания к изданию
     */
    public AdditData(String num, String notes){
        this.numVolume = num;
        this.notes = notes;
    }
    
    /**
     *Возвращает строку с обозначением тома/выпуска (номер)
     * @return 
     */
    public String getNumVolume(){
        return this.numVolume;
    }
    
    /**
     *Устанавливает обозначение тома/выпуска (номер)
     * @param num 
     */
    public void setNumVolume(String num){
        this.numVolume = num;
    }
    
    /**
     *Возвращает строку с примечаниями к изданию
     * @return 
     */
    public String getNotes(){
        return this.notes;
    }
    
    /**
     *Устанавливает строку с примечаниями к изданию
     * @param notes 
     */
    public void setNotes(String notes){
        this.notes = notes;
    }
    
    /**
     *Строковое представление объекта со сведениями
     * о номере тома и примечаниями
     * @return 
     */
    @Override
    public String toString(){
        return "Number of volume: " + this.numVolume + ".\n" +
                "Notes to book: " + this.notes + ".";
    }
}
