/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import java.io.Serializable;
/**
 *
 * @author 1
 */
public abstract class FieldID implements Serializable{
    protected int idBook;
    
    public FieldID(int id){
        this.idBook = id;
    }
    
    public int getIdBook(){
        return idBook;
    }
}
