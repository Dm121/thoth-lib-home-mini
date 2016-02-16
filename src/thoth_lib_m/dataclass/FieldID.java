/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import java.io.Serializable;
/**
 *Абстрактный класс для работы с полем id (id_book) издания
 * (id_book - первичный ключ (PK) для книги в базе данных (БД),
 * таблица bo_book)
 * @author Sirota Dmitry
 */
public abstract class FieldID implements Serializable{
    protected int idBook;
    
	/**
	 *Конструктор с параметрами
	 * @param id - id (id_book) нового издания
	 */
    public FieldID(int id){
        this.idBook = id;
    }
    
	/**
	 *Возвращает id (id_book) издания
	 * @return idBook - id (id_book) издания
	 */
    public int getIdBook(){
        return idBook;
    }
}
