/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.sql.*;
import java.util.Arrays;
import javax.sql.rowset.*;
import javax.swing.JOptionPane;
import thoth_lib_m.AdditClass;

/**
 *Класс для работы с соединением с Базами Данных, 
 * созданными в СУБД SQLite
 * @author Sirota Dmitry
 */
public class ConnectionSQLiteDB extends ConnectionDB{
    Connection c;	//не private или protected
    boolean err;	//аналогично
    
	//Конструктор по умолчанию
    public ConnectionSQLiteDB(){
        c = null;
        err = true;
    }
    
    @Override
    public boolean connDB(String paramConnection){
        try{
        Class.forName("org.sqlite.JDBC");		//создание объекта драйвера СУБД
        c = DriverManager.getConnection("jdbc:sqlite:" + paramConnection);
        err = false;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, 
                    "Ошибка: невозможно подключиться к Базе Данных.\n" +
                            "Метод: connDB: " + e.getMessage(), 
                            "Ошибка (Error)", 
                            JOptionPane.ERROR_MESSAGE);
            err = true;
        }
        catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, 
                    "Ошибка: невозможно найти классы "  +
                            "для подключения к Базе Данных, " +
                            "(например, \"org.sqlite.JDBC\").\n" +
                            "Метод: connDB: " + e.getMessage(), 
                            "Ошибка (Error)", 
                            JOptionPane.ERROR_MESSAGE);
            err = true;
        }
        return err;
    }
    
    //
    @Override
    public boolean connCacheDB(CachedRowSet rowset, String paramConn){
        err = false;
        try{
            rowset.setUrl("jdbc:sqlite:" + paramConn);
            rowset.setUsername("");
            rowset.setPassword("");
        }
        catch(SQLException e){
            String regex = ",";
            JOptionPane.showMessageDialog(null, 
                    "Ошибка: Метод: connCacheDB: " + e.getMessage() + ".\n" +
                            AdditClass.splitString(regex, 
                                    Arrays.toString(e.getStackTrace())), 
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
            err = true;
        }
        return err;
    }
    //
    
    @Override
    public boolean closeDB(Connection c){
        err = false;
        try{
            if(c != null) { c.close(); } 
            //
            /*
            JOptionPane.showMessageDialog(null, 
                    "Соединение с Базой Данных закрыто.\n" +
                    "Нажмите \"OK\"");
            */
            //
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,
                    "Ошибка при завершении соединения с Базой Данных.\n" +
                            "Метод: closeDB: " + e.getMessage(),
                    "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
            err = true;
        }
        return err;
    }
    
    //@Override
    public Connection getConnectionC(){
        return c;
    }
    
    //@Override
    
}
