/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.databaseclass;

import java.sql.*;
import thoth_lib_m.AdditClass;
//import thoth_lib_m.dataclass.Book;

/**
 *Подготовленные запросы Delete для удаления данных
 * из Базы Данных
 * @author Sirota Dmitry
 */
public class DataBaseDelete 
    extends DataBaseHelper{
    
    private final static String sql_delete_book = 
            "delete from bo_book where id_book = ?;";
    private final static String sql_delete_inv = 
            "delete from inv_book where id_book = ?;";
    
	//Конструктор по умолчанию
    public DataBaseDelete() throws SQLException{
        super();
    }
    
	/**
	 *Получение объекта с подготовленным запросом 
	 * для удаления библиографических данных книги  
	 */
    public PreparedStatement getPSBook(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(sql_delete_book);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "DataBaseDelete.getPSBook");
        }
        return ps;
    }
    
	/**
	 *Получение объекта с подготовленным запросом
	 * для удаления инвентарных данных о книге
	 */
    public PreparedStatement getPSInv(){
        PreparedStatement ps = null;
        try{
            ps = connect.getConnectionC().prepareStatement(sql_delete_inv);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "DataBaseDelete.getPSInv");
        }
        return ps;
    }
    
    /**
     *Метод позволяет удалить данные о книге
     * и её экземплярах (T - используется транзакция)
     * @param idBook идентификатор удаляемой книги
     * @return err - результат выполнения метода
     * (true - метод завершился с ошибкой,
     * false - метод выполнился правильно)
     * @throws java.sql.SQLException
     */
    public boolean deleteBookT(int idBook) throws SQLException{
        boolean err = false;
        PreparedStatement ps = null;
        //
        try{
            connect.getConnectionC().setTransactionIsolation(
                                        Connection.TRANSACTION_SERIALIZABLE);
            connect.getConnectionC().setAutoCommit(false);
            //
            //При получении объектов подготовленных запросов
            // типа PreparedStatement используется тоже соединение,
            // полученное с помощью метода connect.getConnection(),
            // что и в данном методе.
            // connect - внутренняя переменная:
            // protected ConnectionSQLiteDB connect;
            // из класса DataBaseHelper
            ps = getPSInv();
            ps.setInt(1, idBook);
            ps.executeUpdate();
            //
            ps = getPSBook();
            ps.setInt(1, idBook);
            ps.executeUpdate();
            //
            connect.getConnectionC().commit();
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "DataBaseDelete.deleteBookT");
            connect.getConnectionC().rollback();
            err = true;
        }
        finally{
            if(ps != null){ this.closeStatement(ps); }
        }
        //
        connect.getConnectionC().setAutoCommit(true);
        //
        return err;
    }
    
    /**
     *Метод позволяет удалить данные о книге
     * и её экземплярах (M - транзакции не используются)
     * @param idBook идентификатор удаляемой книги
     * @return err - результат выполнения метода 
     * (true - метод завершился с ошибкой, 
     * false - метод выполнился правильно)
     * @throws java.sql.SQLException
     */
    public boolean deleteBookM(int idBook) throws SQLException{
        boolean err = false;
        //
        try(PreparedStatement ps = getPSInv()){
            ps.setInt(1, idBook);
            ps.executeUpdate();
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "DataBaseDelete.deleteBook -> getPSInv");
            err = true;
        }
        //
        if(!err){
            try(PreparedStatement ps = getPSBook()){
                ps.setInt(1, idBook);
                ps.executeUpdate();
            }
            catch(SQLException e){
                AdditClass.errorMes(e, 
                                    "DataBaseDelete.deleteBook -> getPSBook");
                err = true;
            }
        }
        //
        return err;
    }
    
    /**
     *Метод для выполнения произвольных запросов на удаление Delete
     * @param sql_delete_query строка запроса на удаление
     * @return err - результат выполнения метода
     * (true - метод завершился с ошибкой,
     * false - метод выполнился правильно)
     */
    public boolean deleteQuery(String sql_delete_query){
        boolean err = false;
        //
        try(Statement s = connect.getConnectionC().createStatement()){
            s.executeUpdate(sql_delete_query);
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "DataBaseDelete.deleteQuery");
            err = true;
        }
        //
        return err;
    }
}
