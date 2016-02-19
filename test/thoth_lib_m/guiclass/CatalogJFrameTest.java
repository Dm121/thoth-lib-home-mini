/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.sql.Connection;
import java.util.List;
import javax.swing.JTabbedPane;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import thoth_lib_m.dataclass.Book;
import thoth_lib_m.databaseclass.ConnectionDB;
import thoth_lib_m.databaseclass.ConnectionSQLiteDB;

/**
 *Тесты для GUI и Database
 * @author 1
 */
public class CatalogJFrameTest {
    
    public CatalogJFrameTest() {
    }
    
    @Before
    public void setUp(){
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        ConnectionDB sqliteDB = new ConnectionSQLiteDB();
        sqliteDB.connDB("db/thoth_lhm_sqlite.db");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of createGUI method, of class CatalogJFrame.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateGUI() throws Exception {
        System.out.println("createGUI");
        Connection c = null;
        CatalogJFrame instance = new CatalogJFrame();
        instance.createGUI(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShow method, of class CatalogJFrame.
     * @throws java.lang.Exception
     */
    //@Test
    public void testSetShow() throws Exception{
        System.out.println("setShow");
        boolean visible = false;
        CatalogJFrame instance = new CatalogJFrame();
        instance.setShow(visible);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTable method, of class CatalogJFrame.
     * @throws java.lang.Exception
     */
    //@Test
    public void testGetTable() throws Exception{
        System.out.println("getTable");
        CatalogJFrame instance = new CatalogJFrame();
        TableCopies expResult = null;
        TableCopies result = instance.getTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBooks method, of class CatalogJFrame.
     * @throws java.lang.Exception
     */
    //@Test
    public void testGetBooks() throws Exception {
        System.out.println("getBooks");
        CatalogJFrame instance = new CatalogJFrame();
        List<Book> expResult = null;
        List<Book> result = instance.getBooks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBooks method, of class CatalogJFrame.
     * @throws java.lang.Exception
     */
    //@Test
    public void testSetBooks() throws Exception{
        System.out.println("setBooks");
        List<Book> books = null;
        CatalogJFrame instance = new CatalogJFrame();
        instance.setBooks(books);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElem method, of class CatalogJFrame.
     * @throws java.lang.Exception
     */
    //@Test
    public void testGetElem() throws Exception{
        System.out.println("getElem");
        CatalogJFrame instance = new CatalogJFrame();
        CatalogJElements expResult = null;
        CatalogJElements result = instance.getElem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTabbedPane method, of class CatalogJFrame.
     * @throws java.lang.Exception
     */
    //@Test
    public void testGetTabbedPane() throws Exception{
        System.out.println("getTabbedPane");
        CatalogJFrame instance = new CatalogJFrame();
        JTabbedPane expResult = null;
        JTabbedPane result = instance.getTabbedPane();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
