/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.List;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.InfoSection;
import thoth_lib_m.dataclass.Book;
import thoth_lib_m.dataclass.CopyTable;
import thoth_lib_m.guiclass.guievent.*;

/**
 *Главное окно модуля "Каталогизатор"
 * @author Sirota Dmitry
 */
public class CatalogJFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 650;
    private final TableCopies table;
    //int numRow;
    private ArrayList<Book> books;
    private final JTabbedPane tabbedPane;
    private final CatalogJElements elem;
    private SaveDataButAction saveDataButAction;
    private DelDataButAction delDataButAction;
               
    public CatalogJFrame() throws Exception{
        super("Каталогизатор");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.books = TableCopies.listBooks(1);
        ArrayList<CopyTable> cpB = TableCopies.listCopies(this.books);
        table = new TableCopies(cpB);
        //
        table.getSortTable().sort(0);
        table.getSortTable().setFlagSort(true);
        //
        tabbedPane = new JTabbedPane(
                    JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        elem = new CatalogJElements();
        saveDataButAction = null;
        delDataButAction = null;
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
        //JPanel mainPanel = new JPanel();
        //GridBagLayout gbl = new GridBagLayout();
        //GridBagConstraints gbcc = new GridBagConstraints();
        this.setLayout(new BorderLayout());
        
        Section s = new Section();
         
        this.getTabbedPane().addTab("Библиографическое описание", 
                elem.getPanelBook(c));
        this.getTabbedPane().addTab("Данные книги", elem.getPanelCopy());
        this.getTabbedPane().addTab("Поиск", elem.getPanelSearch());
        this.getDataBook(this.getBooks().get(0));       //1
        this.getTable().getCopyTable().setRowSelectionAllowed(true);
        this.getTable().getSortTable().getIdBookRecord(sRow);
        this.getTable().getCopyTable().setRowSelectionInterval(0, 0);
        //
        /*
        gbcc.anchor = GridBagConstraints.NORTHWEST;
        gbcc.fill = GridBagConstraints.NONE;
        gbcc.gridheight = 1;
        gbcc.gridwidth = GridBagConstraints.REMAINDER;
        gbcc.gridx = 0;
        gbcc.gridy = 0;
        gbcc.insets = new Insets(0, 0, 0, 0);
        gbcc.ipadx = 0;
        gbcc.ipady = 0;
        gbcc.weightx = 0.0;
        gbcc.weighty = 0.0;
        */
        //
        Box boxMain = Box.createVerticalBox();
        boxMain.setAlignmentX(Box.LEFT_ALIGNMENT);
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1,1));
        menuPanel.add(elem.createMenu(this));
        JPanel menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new GridLayout(1,1));
        menuButtonPanel.add(elem.createButtonMenu());
        boxMain.add(menuPanel);
        boxMain.add(menuButtonPanel);
        //event for Button of Menu - ButtonMenu
        NewButAction newButAction = new NewButAction(elem, 
            this.getTable().getCopyTable(), this.getTabbedPane());
        elem.getButtonsMenu().get(0).addActionListener(newButAction);
        //
        
        //
        
        //
                
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.setSize(this.getWidth(), TABLE_HEIGHT);
        //
        //Class extends MouseAdapter
        //MouseListener ml = new Class();
        //.addMouseListener(ml);
        //TableCopies table
        MouseListener ml = new SortData(this.table); 
        table.getCopyTable().getTableHeader().addMouseListener(ml);
        //
        //
        //Class implements ListSelectionListener
        //method: @Override public void valueChanged(ListSelectionEvent e){...}
        //TableCopies table, CatalogJElements elem, CatalogJFrame frame (this)
        //DelDataButAction delDataButAction
        //elem.getButonsMenu().get(1)
        SelectionTableRow str = new SelectionTableRow(this.table,
                            this, this.elem, this.delDataButAction);
        ListSelectionModel lsm = table.getCopyTable().getSelectionModel();
                lsm.addListSelectionListener(str);
        //
                
        JScrollPane scroll = new JScrollPane(table.getCopyTable());
        tablePanel.add(scroll);
        //event for JTextField of panelBook
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
        //
                
        Box boxAddit = Box.createHorizontalBox();
        s.getScrollSection().setSize(SECTION_WIDTH, SECTION_HEIGHT);
        s.addDataList(c);
        //
        //Class implements ListSelectionListener
        //method: @Override public void valueChanged(ListSelectionEvent e){...}
        //TableCopies table
        //CatalogJFrame frame (this)
        //CatalogJElements elem
        //Section s
        //SaveDataButAction saveDataButAction
        SelectionSection selS = new SelectionSection(
            this.table, this, this.elem, s, this.saveDataButAction);
        s.getSection().addListSelectionListener(selS);
        //
        boxAddit.add(new JScrollPane(s.getScrollSection()));
        boxAddit.add(new JScrollPane(tabbedPane));
        //
        this.saveDataButAction = new SaveDataButAction(elem, 
                        s.getArrayISection(s.getSection().
                                        getSelectedIndex()).getIdSection(),
                                                            this.table, this);
        this.elem.getButtonsMenu().get(2).addActionListener(
                                            this.saveDataButAction);
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
    
    public ArrayList<Book> getBooks(){
        return this.books;
    }
    
    public void setBooks(ArrayList<Book> books){
        this.books = books;
    }
    
    public JTabbedPane getTabbedPane(){
        return this.tabbedPane;
    }
    
    //*
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
    //*/
    
    //
                    /*
                    this.elem.setValIdB(String.valueOf(b.getIdBook()));
                    this.elem.setValTypeEdition(b.getIdTypeBook() - 1);
                    this.elem.getTextBook().get(0).setText(
                                        b.getMainData().getAuthors());
                    this.elem.getTextBook().get(1).setText(
                                        b.getAdditData().getNumVolume());
                    this.elem.getTextBook().get(2).setText(
                                        b.getMainData().getTitle());
                    this.elem.getTextBook().get(3).setText(
                                        b.getDateline().getPublisher());
                    this.elem.getTextBook().get(4).setText(
                                        b.getDateline().getPlace());
                    this.elem.setValYearValue(
                                        b.getDateline().getYear());
                    this.elem.getTextArray().get(0).setText(
                                        b.getAdditData().getNotes());
                    this.elem.getTextCopy().get(0).setText(
                                        b.getCopyBook().getBookCase());
                    this.elem.getTextCopy().get(1).setText(
                                        b.getCopyBook().getBookShelf());
                    this.elem.getTextArray().get(1).setText(
                                        b.getCopyBook().getCondition());
                        this.setTextCountBook();
                    */
                    //
                    //
                    /*
                    this.elem.setValIdB("");
                    this.elem.setValTypeEdition(0);
                    this.elem.getTextBook().get(0).setText("");
                    this.elem.getTextBook().get(1).setText("");
                    this.elem.getTextBook().get(2).setText("");
                    this.elem.getTextBook().get(3).setText("");
                    this.elem.getTextBook().get(4).setText("");
                    this.elem.setValYearValue(2015);
                    this.elem.getTextCopy().get(0).setText("");
                    this.elem.getTextCopy().get(1).setText("");
                    this.elem.getTextArray().get(0).setText("");
                    this.elem.getTextArray().get(1).setText("");
                    //
                    this.setTextCountBook();
                    */
                    //
}
