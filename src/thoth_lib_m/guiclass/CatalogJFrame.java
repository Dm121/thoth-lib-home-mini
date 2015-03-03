/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.*;
import java.sql.*;
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
    private TableCopies table;
    //int numRow;
    private ArrayList<Book> books;
    private JTabbedPane tabbedPane;
    private CatalogJElements elem;
               
    public CatalogJFrame() throws Exception{
        super("Каталогизатор");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.books = TableCopies.listBooks(1);
        ArrayList<CopyTable> cpB = TableCopies.listCopies(this.books);
        table = new TableCopies(cpB);
        tabbedPane = new JTabbedPane(
                    JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        elem = new CatalogJElements();
    }
    
    public void createGUI(Connection c)
        throws SQLException, Exception{
        final int TABLE_HEIGHT = 300;
        final int SECTION_WIDTH = 150;
        final int SECTION_HEIGHT = 100;
        final int MENU_HEIGHT = 25;
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
        this.getDataBook(this.getBooks().get(0));
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
            this.getTable().getCopyTable());
        elem.getButtonsMenu().get(0).addActionListener(newButAction);
        
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.setSize(this.getWidth(), TABLE_HEIGHT);
        table.getCopyTable().getTableHeader().addMouseListener(
                                                new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                //if(e.getClickCount() < 2) return;
                
                int sRow = 0;
                
                //Поиск столбца, на котором был щелчок
                int tableColumn = table.getCopyTable().columnAtPoint(
                                            e.getPoint());
                
                //Преобразование столбца в индекс модели
                int modelColumn = table.getCopyTable().
                                    convertColumnIndexToModel(tableColumn);
                //
                table.getCopyTable().clearSelection();
                table.getSortTable().fireTableDataChanged();
                //
                //и выполнение сортировки
                if(!table.getSortTable().getFlagSort()){
                    sRow = table.getSortTable().sort(modelColumn);
                    table.getSortTable().setFlagSort(true);
                }
                else{
                    sRow = table.getSortTable().reverseSort(modelColumn);
                    table.getSortTable().setFlagSort(false);
                }
                if(sRow > -1){
                    table.getCopyTable().setRowSelectionAllowed(true);
                    table.getCopyTable().setRowSelectionInterval(sRow, sRow);
                }
            }
        });
        
        ListSelectionModel lsm = table.getCopyTable().getSelectionModel();
                lsm.addListSelectionListener((ListSelectionEvent e) -> {
            int i;      //for loop
            int numRow = table.getSortTable().getIdBookRecord(
                    table.getCopyTable().getSelectedRow());
            if(numRow > -1){
                for(i = 0; i < this.getBooks().size(); i++){
                    if(numRow == this.getBooks().get(i).getIdBook()){
                        //
                        Book b = this.getBooks().get(i);
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
                    }
                }
            }
            //
            //AdditClass.infoMes("" + numRow + "");
            //
        });
                
        JScrollPane scroll = new JScrollPane(table.getCopyTable());
        tablePanel.add(scroll);
                
        Box boxAddit = Box.createHorizontalBox();
        s.getScrollSection().setSize(SECTION_WIDTH, SECTION_HEIGHT);
        s.addDataList(c);
        s.getSection().addListSelectionListener((ListSelectionEvent e) -> {
            int sRowS = 0;
            try {
                Integer selectedIndex = s.getSection().getSelectedIndex();
                InfoSection ifS = s.getArrayISection(selectedIndex);
                this.setBooks(TableCopies.listBooks(ifS.getIdSection()));
                ArrayList<CopyTable> cpB = TableCopies.listCopies(
                                                this.getBooks());
                this.getTable().getSortTable().clearTable();
                this.getTable().getSortTable().addArrayCopies(cpB);
                this.getTable().getSortTable().setRowsM();
                this.getTable().getCopyTable().repaint();
                if(this.getBooks().size() > 0){
                    this.getDataBook(this.getBooks().get(0));
                    this.getTable().getCopyTable().setRowSelectionAllowed(true);
                    this.getTable().getSortTable().getIdBookRecord(sRowS);
                    this.getTable().getCopyTable().
                                                setRowSelectionInterval(0, 0);
                }
                else{
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
                }
            }catch(Exception err){
                AdditClass.errorMes(err, "CatalogJFrame.createGUI");
            }
        });
        boxAddit.add(new JScrollPane(s.getScrollSection()));
        boxAddit.add(new JScrollPane(tabbedPane));
        
        
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
    }
}
