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
    public abstract boolean connDB(String paramConnection);
    public abstract boolean connCacheDB(CachedRowSet rowset, String paramConn);
    public abstract boolean closeDB(Connection c);
    //public abstract Connection getConnection();
}
