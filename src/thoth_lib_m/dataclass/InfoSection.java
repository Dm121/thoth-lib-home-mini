/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import java.io.Serializable;
/**
 *Сведения о книжных разделах
 * @author Sirota Dmitry
 */
public class InfoSection implements Serializable {
    private int idSection;
    private String describe;
    private int aboveSection;
    
    public InfoSection(int id, String des, int abId){
        this.idSection = id;
        if(des.trim().equals("")){
            describe = "Новый раздел";
        }
        else{
            this.describe = des;
        }
        this.aboveSection = abId;
    }
    
    public int getIdSection(){
        return idSection;        
    }
    
    public String getDescribe(){
        return describe;
    }
    
    public void setDescribe(String des){
        if(!des.trim().equals("")){
            describe = des;
        }
    }
    
    public int getAboveSection(){
        return aboveSection;
    }
    
    @Override
    public String toString(){
        return "Раздел \"" + describe + "\" " +
                "(id = " + idSection +")" +
                "(id_above = " + aboveSection + ")";
    }
}
