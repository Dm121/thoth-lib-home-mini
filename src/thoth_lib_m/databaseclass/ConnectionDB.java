/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.sql.Connection;
//import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *Абстрактный класс для работы с соединением с Базой Даных
 * @author Sirota Dmitry
 */
public abstract class ConnectionDB {
	/**
	 *Создание подключения, true - удачно, иначе - false
	 */
    public abstract boolean connDB(String paramConnection);
    
	/**
	 *"Попытка" автоматического создания соединения
	 * и получения необходимого набора строк - ???? - использется ли
	 */
	public abstract boolean connCacheDB(CachedRowSet rowset, String paramConn);
    
	/**
	 *Закрытие соединения
	 */
	public abstract boolean closeDB(Connection c);
    
	/**
	 *Получение соединения
	 */
	//public abstract Connection getConnectionC();
}
