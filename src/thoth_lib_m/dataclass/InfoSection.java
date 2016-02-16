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
    
    /**
     *Конструктор с параметрами. Создаёт объект, хранящий сведения
     * о книжном разделе: id раздела, название раздела и номер (id) над-раздела
     * @param id - id книжного раздела
     * @param des - название раздела
     * @param abId - номер (id) над-раздела
     */
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
    
    /**
     *Возвращает id книжного раздела 
     * @return 
     */
    public int getIdSection(){
        return idSection;        
    }
    
    /**
     *Возращает название книжного раздела 
     * @return 
     */
    public String getDescribe(){
        return describe;
    }
    
    /**
     *Устанавливает название книжного раздела
     * @param des 
     */
    public void setDescribe(String des){
        if(!des.trim().equals("")){
            describe = des;
        }
    }
    
    /**
     *Возвращает номер (id) над-раздела, в рамках которого был
     * создан текущий подраздел
     * @return 
     */
    public int getAboveSection(){
        return aboveSection;
    }
    
    /**
     *Возращает строковое представление объекта, хранящего сведения
     * о книжном разделе
     * @return 
     */
    @Override
    public String toString(){
        return "Раздел \"" + describe + "\" " +
                "(id = " + idSection +")" +
                "(id_above = " + aboveSection + ")";
    }
}
