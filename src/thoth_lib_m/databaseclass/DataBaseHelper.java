/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import thoth_lib_m.AdditClass;

/**
 *Абстрактный класс для установки соединения
 * при выполнении подготовленных запросов типа Insert, Update, Select и Delete
 * @author Sirota Dmitry
 */
public abstract class DataBaseHelper {
    
    //
    protected ConnectionSQLiteDB connect;
    //
    
	//Конструктор по умолчанию
    public DataBaseHelper() throws SQLException{
        connect = new ConnectionSQLiteDB();
        connect.connDB("db/thoth_lhm_sqlite.db");
    }
    
    //
	/**
	 *Получение соединения
	 */
    public Connection getConnectionDBH(){
        return connect.getConnectionC();
    }
    //
    
	/**
	 *Закрытие Statement -> изменить аргумент на Statement
	 */
    public void closeStatement(PreparedStatement ps){
        if(ps != null){
            try{
                ps.close();
            }
            catch(SQLException e){
                AdditClass.errorMes(e, "closeStatement");
            }
        }
    }
    
	/**
	 *Закрытие соединения
	 */
    public void closeConnection(){
        if(connect.getConnectionC() != null){
            connect.closeDB(connect.getConnectionC());
        }
    }
}
