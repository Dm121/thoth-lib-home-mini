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
    
    public AdditData(){
        this.numVolume = "";
        this.notes = "";
    }
    
    public AdditData(String num, String notes){
        this.numVolume = num;
        this.notes = notes;
    }
    
    public String getNumVolume(){
        return this.numVolume;
    }
    
    public void setNumVolume(String num){
        this.numVolume = num;
    }
    
    public String getNotes(){
        return this.notes;
    }
    
    public void setNotes(String notes){
        this.notes = notes;
    }
    
    @Override
    public String toString(){
        return "Number of volume: " + this.numVolume + ".\n" +
                "Notes to book: " + this.notes + ".";
    }
}
