/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.Book;
import thoth_lib_m.dataclass.CopyTable;
import thoth_lib_m.dataclass.InfoSection;
import thoth_lib_m.guiclass.guievent.*;
import thoth_lib_m.guiclass.guievent.section_event.*;

/**
 *Главное окно модуля "Каталогизатор"
 * @author Sirota Dmitry
 */
public class CatalogJFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 850;
    private static final int DEFAULT_HEIGHT = 680;
    private final TableCopies table;
    private List<Book> books;
    private List<CopyTable> cpB;
    private final JTabbedPane tabbedPane;
    private final CatalogJElements elem;
    private final SearchPane sp; 
    private SaveDataButAction saveDataButAction;
    private DelDataButAction delDataButAction;
               
    public CatalogJFrame() throws Exception{
        super("Домашняя библиотека v1.2.0.0 - Каталогизатор");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.books = TableCopies.listBooks(1);
        this.setListBookTable(TableCopies.listCopies(this.books));
        this.table = new TableCopies(this.cpB);
        //
        this.table.getSortTable().sort(0);
        this.table.getSortTable().setFlagSort(true);
        //
        this.tabbedPane = new JTabbedPane(
                    JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        this.elem = new CatalogJElements();
        this.sp = new SearchPane(this);
        saveDataButAction = null;
        delDataButAction = null;
    }
    
    private List<CopyTable> getListBookTable(){
        return this.cpB;
    }
    
    private void setListBookTable(List<CopyTable> listBookTable){
        this.cpB = listBookTable;
    }
    
    public void createGUI(Connection c)
        throws SQLException, Exception{
        final int TABLE_HEIGHT = 300;
        final int SECTION_WIDTH = 150;
        final int SECTION_HEIGHT = 100;
        final int MENU_HEIGHT = 25;
        final int[] MAX_CHAR;
        MAX_CHAR = new int[]{20, 120, 200, 250, 400};
        int sRow = 0;
        int numRow;
        this.setLayout(new BorderLayout());
        
        Section s = new Section();
         
        this.getTabbedPane().addTab("Библиографическое описание", 
                this.elem.getPanelBook(c));
        this.getTabbedPane().addTab("Данные книги", this.elem.getPanelCopy());
        this.getTabbedPane().addTab("Поиск", this.sp.getPanelSearch());
        numRow = this.getTable().getSortTable().getIdBookRecord(sRow);
        for(sRow = 0; sRow < this.getBooks().size(); sRow++){
            if(numRow == this.getBooks().get(sRow).getIdBook()){
                TextDataElemBook.getDataBook(
                                this.getBooks().get(sRow), this.elem);       //1
                break;
            }
        }
        this.getTable().getCopyTable().setRowSelectionAllowed(true);
        this.getTable().getCopyTable().setRowSelectionInterval(0, 0);
        //
        Box boxMain = Box.createVerticalBox();          //North
        boxMain.setAlignmentX(Box.LEFT_ALIGNMENT);
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1,1));
        menuPanel.add(this.elem.createMenu(this));
        JPanel menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new GridLayout(1,1));
        menuButtonPanel.add(this.elem.createButtonMenu());
        boxMain.add(menuPanel);
        boxMain.add(menuButtonPanel);
        //
        JPanel tablePanel = new JPanel();               //Center
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.setSize(this.getWidth(), TABLE_HEIGHT);
        //
        JScrollPane scroll = new JScrollPane(this.table.getCopyTable());
        tablePanel.add(scroll);
        //                
        Box boxAddit = Box.createHorizontalBox();       //South
        s.getScrollSection().setSize(SECTION_WIDTH, SECTION_HEIGHT);
        s.addDataList(c);
        //
        boxAddit.add(new JScrollPane(s.getScrollSection()));
        boxAddit.add(new JScrollPane(this.tabbedPane));
        //events
        //events for Button of Menu - ButtonMenu
        NewButAction newButAction = new NewButAction(elem, 
            this.getTable().getCopyTable(), this.getTabbedPane());
        this.getElem().getButtonsMenu().get(0).addActionListener(newButAction);
        //
        //Class implements ListSelectionListener
        //method: @Override public void valueChanged(ListSelectionEvent e){...}
        //TableCopies table, CatalogJElements elem, CatalogJFrame frame (this)
        //DelDataButAction delDataButAction
        //elem.getButonsMenu().get(1)
        //
        this.delDataButAction = new DelDataButAction(this.getElem(), this,
                                                            this.getTable());
        this.getElem().getButtonsMenu().get(1).addActionListener(
                                            this.delDataButAction);
        //
        this.saveDataButAction = new SaveDataButAction(this.getElem(), s,
                                                            this.table, this);
        this.getElem().getButtonsMenu().get(2).addActionListener(
                                            this.saveDataButAction);
        //
        ChangeSecButAction chs = new ChangeSecButAction(this);
        this.getElem().getButtonsMenu().get(3).addActionListener(chs);
        //
        PrintButAction printButAction = new PrintButAction(s, 
                                                        CatalogJFrame.this);
        this.getElem().getButtonsMenu().get(4).addActionListener(
                                                        printButAction);
        //
        AddSecButAction addSection = new AddSecButAction(s);
        this.elem.getButtonsMenu().get(5).addActionListener(addSection);
        //
        List<ActionListener> delAndRen = new ArrayList<>();
            delAndRen.add(new DelSecButAction(s));
            delAndRen.add(new RenameSecButAction(s));
        this.elem.getButtonsMenu().get(6).addActionListener(delAndRen.get(0));
        this.elem.getButtonsMenu().get(7).addActionListener(delAndRen.get(1));
        //
        //Other events
        //
        //Class implements ListSelectionListener
        //method: @Override public void valueChanged(ListSelectionEvent e){...}
        //TableCopies table
        //CatalogJFrame frame (this)
        //CatalogJElements elem
        //Section s
        //SaveDataButAction saveDataButAction
        //
        SelectionSection selS = new SelectionSection(
                                    this.table, this, this.elem, s, delAndRen);
        s.getSection().addListSelectionListener(selS);
        //
        SelectionTableRow str = new SelectionTableRow(this.table,
                                                            this, this.elem);
        ListSelectionModel lsm = this.table.getCopyTable().getSelectionModel();
                lsm.addListSelectionListener(str);
        //
        //Class extends MouseAdapter
        //MouseListener ml = new Class();
        //.addMouseListener(ml);
        //TableCopies table
        MouseListener ml = new SortData(this.table); 
        this.table.getCopyTable().getTableHeader().addMouseListener(ml);
        //events for JTextField-s and JTextArea-s of panelBook
        TFCountAction textF1 = 
                new TFCountAction(this.elem.getTextBook().get(0), MAX_CHAR[3],
                                        this.elem.getTextCount().get(0));
        this.elem.getTextBook().get(0).addKeyListener(textF1);
        TFCountAction textF2 = 
                new TFCountAction(this.elem.getTextBook().get(1), MAX_CHAR[0], 
                                        this.elem.getTextCount().get(1));
        this.elem.getTextBook().get(1).addKeyListener(textF2);
        TFCountAction textF3 = 
                new TFCountAction(this.elem.getTextBook().get(2), MAX_CHAR[2], 
                                        this.elem.getTextCount().get(2));
        this.elem.getTextBook().get(2).addKeyListener(textF3);
        TFCountAction textF4 = 
                new TFCountAction(this.elem.getTextBook().get(3), MAX_CHAR[1], 
                                        this.elem.getTextCount().get(3));
        this.elem.getTextBook().get(3).addKeyListener(textF4);
        TFCountAction textF5 = 
                new TFCountAction(this.elem.getTextBook().get(4), MAX_CHAR[2], 
                                        this.elem.getTextCount().get(4));
        this.elem.getTextBook().get(4).addKeyListener(textF5);
        //
        TACountAction textA1 = 
                new TACountAction(this.elem.getTextArray().get(0), MAX_CHAR[4],
                                        this.elem.getTextCount().get(5));
        this.elem.getTextArray().get(0).addKeyListener(textA1);
        //
        TFCountAction textF6 = 
                new TFCountAction(this.elem.getTextCopy().get(0), MAX_CHAR[0], 
                                        this.elem.getTextCount().get(6));
        this.elem.getTextCopy().get(0).addKeyListener(textF6);
        TFCountAction textF7 = 
                new TFCountAction(this.elem.getTextCopy().get(1), MAX_CHAR[0], 
                                        this.elem.getTextCount().get(7));
        this.elem.getTextCopy().get(1).addKeyListener(textF7);
        //
        TACountAction textA2 = 
                new TACountAction(this.elem.getTextArray().get(1), MAX_CHAR[2],
                                        this.elem.getTextCount().get(8));
        this.elem.getTextArray().get(1).addKeyListener(textA2);
        //adding all elements on main window (frame)
        //
        this.add(boxMain, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(boxAddit, BorderLayout.SOUTH);
        //this.pack();
    }
    
    public void setShow(boolean visible){
        //this.setLocationRelativeTo(null);
        this.setVisible(visible);
    }
    
    public TableCopies getTable(){
        return table;
    }
    
    public List<Book> getBooks(){
        return this.books;
    }
    
    public void setBooks(List<Book> books){
        this.books = books;
    }
    
    public CatalogJElements getElem(){
        return this.elem;
    }
    
    public JTabbedPane getTabbedPane(){
        return this.tabbedPane;
    }
    
    //*
    /*
    public void getDataBook(Book book){
        Book b = book;
        this.elem.setValIdB(String.valueOf(b.getIdBook()));
        this.elem.setValTypeEdition(b.getIdTypeBook() - 1);
        this.elem.getTextBook().get(0).setText(b.getMainData().getAuthors());
        this.elem.getTextBook().get(1).setText(b.getAdditData().getNumVolume());
        this.elem.getTextBook().get(2).setText(b.getMainData().getTitle());
        this.elem.getTextBook().get(3).setText(b.getDateline().getPublisher());
        this.elem.getTextBook().get(4).setText(b.getDateline().getPlace());
        this.elem.setValYearValue(b.getDateline().getYear());
        this.elem.getTextArray().get(0).setText(b.getAdditData().getNotes());
        this.elem.getTextCopy().get(0).setText(b.getCopyBook().getBookCase());
        this.elem.getTextCopy().get(1).setText(b.getCopyBook().getBookShelf());
        this.elem.getTextArray().get(1).setText(b.getCopyBook().getCondition());
        //
        this.setTextCountBook();
    }
    
    private void setTextCountBook(){
        final int[] MAX_COUNT_CHAR;
        MAX_COUNT_CHAR = new int[]{20, 120, 200, 250, 400};
        //
        this.elem.getTextCount().get(0).setText(this.getTitle(
                this.elem.getTextCount().get(0).getText()) + 
                (MAX_COUNT_CHAR[3] - 
                        this.elem.getTextBook().get(0).getText().length()) + 
                "):");
        this.elem.getTextCount().get(1).setText(this.getTitle(
                this.elem.getTextCount().get(1).getText()) + 
                (MAX_COUNT_CHAR[0] - 
                        this.elem.getTextBook().get(1).getText().length()) + 
                "):");
        this.elem.getTextCount().get(2).setText(this.getTitle(
                this.elem.getTextCount().get(2).getText()) + 
                (MAX_COUNT_CHAR[2] - 
                        this.elem.getTextBook().get(2).getText().length()) + 
                "):");
        this.elem.getTextCount().get(3).setText(this.getTitle(
                this.elem.getTextCount().get(3).getText()) + 
                (MAX_COUNT_CHAR[1] - 
                        this.elem.getTextBook().get(3).getText().length()) + 
                "):");
        this.elem.getTextCount().get(4).setText(this.getTitle(
                this.elem.getTextCount().get(4).getText()) + 
                (MAX_COUNT_CHAR[2] - 
                        this.elem.getTextBook().get(4).getText().length()) + 
                "):");
        this.elem.getTextCount().get(5).setText(this.getTitle(
                this.elem.getTextCount().get(5).getText()) + 
                (MAX_COUNT_CHAR[4] - 
                        this.elem.getTextArray().get(0).getText().length()) + 
                "):");
        this.elem.getTextCount().get(6).setText(this.getTitle(
                this.elem.getTextCount().get(6).getText()) + 
                (MAX_COUNT_CHAR[0] - 
                        this.elem.getTextCopy().get(0).getText().length()) + 
                "):");
        this.elem.getTextCount().get(7).setText(this.getTitle(
                this.elem.getTextCount().get(7).getText()) + 
                (MAX_COUNT_CHAR[0] - 
                        this.elem.getTextCopy().get(1).getText().length()) + 
                "):");
        this.elem.getTextCount().get(8).setText(this.getTitle(
                this.elem.getTextCount().get(8).getText()) + 
                (MAX_COUNT_CHAR[2] - 
                        this.elem.getTextArray().get(1).getText().length()) + 
                "):");
    }
    
    
    private String getTitle(String input){
        String title = "";
        try{
            String regPattern = "^[a-zA-z0-9а-яА-Я \t]+\\ \\(";
            Pattern pattern = Pattern.compile(regPattern, 
                                                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if(matcher.find()){
                int start = matcher.start();
                int end = matcher.end();
                title = input.substring(start, end);
            }
        }
        catch(Exception e){
            AdditClass.errorMes(e, "TFCountAction.getTitle");
        }
        return title;
    }
    */
}
