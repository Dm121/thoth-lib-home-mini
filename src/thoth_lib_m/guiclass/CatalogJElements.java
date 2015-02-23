/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

//import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;
//import thoth_lib_m.databaseclass.*;

/**
 *Основные элементы окна модуля "Каталогизатор"
 * @author Sirota Dmitry
 */
public class CatalogJElements {
    
    public CatalogJElements(){
        
    }
        
    public JMenuBar createMenu(JFrame frame){
        Font font = new Font("Calibri", Font.PLAIN, 12);
        final int HEIGHT_MENU = 25;
        
        JMenuBar catalogMenu = new JMenuBar();
        catalogMenu.setFont(font);
        catalogMenu.setSize(frame.getSize().width, HEIGHT_MENU);
        
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem saveMenu = new JMenuItem("Сохранить изменения");
        JMenuItem exitMenu = new JMenuItem("Выход");
        fileMenu.add(saveMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenu);
        catalogMenu.add(fileMenu);
        
        exitMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //frame.setVisible(false);
                System.exit(0);
                //
            }
        });
        
        return catalogMenu;
    }
    
    public JToolBar createButtonMenu(){
        //int i; //for loop
        JToolBar catalogButton = new JToolBar("Основные операции");
        catalogButton.setFloatable(false);
        
        ImageIcon addIcon = new ImageIcon(getClass().getResource("img/book_add.png"));
        JButton addButton = new JButton(addIcon);
        addButton.setToolTipText("Добавить книгу");
        catalogButton.add(addButton);
        
        ImageIcon delIcon = new ImageIcon(getClass().getResource("img/book_delete.png"));
        JButton delButton = new JButton(delIcon);
        delButton.setToolTipText("Удалить книгу");
        catalogButton.add(delButton);
        
        ImageIcon saveIcon = new ImageIcon(getClass().getResource("img/book_save.png"));
        JButton saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Сохранить все изменения");
        catalogButton.add(saveButton);
        catalogButton.addSeparator();
        
        /*
        //Изменить раздел
        ImageIcon changeIcon = new ImageIcon(getClass().getResource("img/section_folder_blue_cha.png"));
        JButton changeButton = new JButton(changeIcon);
        changeButton.setToolTipText("Изменить раздел");
        catalogButton.add(changeButton);
        catalogButton.addSeparator();
        */
        
        ImageIcon printIcon = new ImageIcon(getClass().getResource("img/list_printer.png"));
        JButton printButton = new JButton(printIcon);
        printButton.setToolTipText("Печать списка книг");
        catalogButton.add(printButton);
        catalogButton.addSeparator();
        
        /*
        ImageIcon folderIcon = new ImageIcon(getClass().getResource("img/section_folder_blue_two.png"));
        JButton newSectionButton = new JButton(folderIcon);
        newSectionButton.setToolTipText("Добавить раздел");
        catalogButton.add(newSectionButton);
                
        ImageIcon folderDelIcon = new ImageIcon(getClass().getResource("img/section_folder_blue_del.png"));
        JButton delSectionButton = new JButton(folderDelIcon);
        delSectionButton.setToolTipText("Удалить раздел");
        catalogButton.add(delSectionButton);
                
        //Переименовать раздел
        ImageIcon renameIcon = new ImageIcon(getClass().getResource("img/section_folder_blue_ren.png"));
        JButton renameButton = new JButton(renameIcon);
        renameButton.setToolTipText("Переименовать раздел");
        catalogButton.add(renameButton);
        catalogButton.addSeparator();
        */
        
        //event for buttons        
        
        return catalogButton;
    }
    
    public JComboBox getTypeBook(Connection c)
    throws SQLException{
        Font fontText = new Font("Calibri", Font.PLAIN, 14);
        JComboBox values = null;
        Statement s = null;
        ResultSet rs = null;
        String sql = "select typebook.describe from typebook;";
        if(c == null){
            JOptionPane.showMessageDialog(null,
                    "Невозможно подключиться к Базе Данных.\n" +
                            "Метод: getTypeBook.",
                    "Ошибка (Error): ",
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                values = new JComboBox();
                values.setFont(fontText);
                s = c.createStatement();
                rs = s.executeQuery(sql);
                while(rs.next()){
                    values.addItem(rs.getString("describe"));
                }
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,
                        "Ошибка при формировании списка типов изданий.\n" +
                                "Метод: getTypeBook: " + e.getMessage() + 
                                ".\n" + Arrays.toString(e.getStackTrace()),
                        "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
                values = null;
            }
            finally{
                if(rs != null) { rs.close(); }
                if(s != null) { s.close(); }
            }
        }
        return values;
    }
    
    public JPanel getPanelBook(Connection c) throws SQLException{
        Font fontLabel = new Font("Calibri", Font.BOLD, 14);
        Font fontText = new Font("Calibri", Font.PLAIN, 14);
        final int WIDTH_LABEL = 240;
        final int WIDTH_TEXT = 240;
        final int HEIGHT_ELEMENTS = 25;
        final int HEIGHT_TEXTAREA = 100;
        final int COUNT_CHAR = 23;
        final int COUNT_CHAR_TA = 47;
        JScrollPane scroll;
        //Dimension maxSize = new Dimension(4, 48);
        JPanel panelBook = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbcc = new GridBagConstraints();
        panelBook.setLayout(gbl);
        //
        JLabel typeTitle = new JLabel("Тип издания:");
        typeTitle.setFont(fontLabel);
        typeTitle.setSize(90, HEIGHT_ELEMENTS);
        JComboBox typeEdition = this.getTypeBook(c);
        if(typeEdition == null){
            typeEdition = new JComboBox();
            typeEdition.addItem("Данные не получены");
        }
        typeEdition.setSize(WIDTH_TEXT, HEIGHT_ELEMENTS);
        //
        JLabel authorsTitle = new JLabel("Авторы:");
        authorsTitle.setFont(fontLabel);
        authorsTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextField authorsText = new JTextField(COUNT_CHAR);
        authorsText.setFont(fontText);
        authorsText.setSize(WIDTH_TEXT, HEIGHT_ELEMENTS);
        //
        JLabel numVTitle = new JLabel("Номер тома:");
        numVTitle.setFont(fontLabel);
        numVTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextField numVText = new JTextField(COUNT_CHAR);
        numVText.setFont(fontText);
        numVText.setSize(WIDTH_TEXT, HEIGHT_ELEMENTS);
        //
        JLabel titleTitle = new JLabel("Название:");
        titleTitle.setFont(fontLabel);
        titleTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextField titleText = new JTextField(COUNT_CHAR);
        titleText.setFont(fontText);
        titleText.setSize(WIDTH_TEXT, HEIGHT_ELEMENTS);
        //
        JLabel publisherTitle = new JLabel("Издатель:");
        publisherTitle.setFont(fontLabel);
        publisherTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextField publisherText = new JTextField(COUNT_CHAR);
        publisherText.setFont(fontText);
        publisherText.setSize(WIDTH_TEXT, HEIGHT_ELEMENTS);
        //
        JLabel placeTitle = new JLabel("Место издания:");
        placeTitle.setFont(fontLabel);
        placeTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextField placeText = new JTextField(COUNT_CHAR);
        placeText.setFont(fontText);
        placeText.setSize(WIDTH_TEXT, HEIGHT_ELEMENTS);
        //
        JLabel yearTitle = new JLabel("Год издания:");
        yearTitle.setFont(fontLabel);
        yearTitle.setSize(90, HEIGHT_ELEMENTS);
        SpinnerNumberModel yearModel =
                new SpinnerNumberModel(2015, 40, 9999, 1);
        JSpinner yearValue =  new JSpinner(yearModel);
        yearValue.setFont(fontText);
        yearValue.setSize(90, HEIGHT_ELEMENTS);
        //
        JLabel infoTitle = new JLabel("Содержание и примечания:");
        infoTitle.setFont(fontLabel);
        infoTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextArea infoText = new JTextArea(4, COUNT_CHAR_TA);
        infoText.setFont(fontText);
        //infoText.setMaximumSize(maxSize);
        //infoText.setAutoscrolls(true);
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);
        //infoText.setSize(2 * WIDTH_TEXT + 10, HEIGHT_TEXTAREA);
        scroll = new JScrollPane(infoText);
        //
        //event
        //
        gbcc.anchor = GridBagConstraints.NORTHWEST;
        gbcc.fill = GridBagConstraints.NONE;
        gbcc.gridheight = 1;
        gbcc.gridwidth = 1;
        gbcc.gridx = 0;
        gbcc.gridy = 0;
        gbcc.insets = new Insets(5, 10, 0, 0);
        gbcc.ipadx = 0;
        gbcc.ipady = 0;
        gbcc.weightx = 0.0;
        gbcc.weighty = 0.0;
        //
        gbl.setConstraints(typeTitle, gbcc);
        panelBook.add(typeTitle);
        
        gbcc.gridx = 1;
        gbl.setConstraints(authorsTitle, gbcc);
        panelBook.add(authorsTitle);
        
        gbcc.gridx = 0;
        gbcc.gridy = 1;
        gbcc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(typeEdition, gbcc);
        panelBook.add(typeEdition);
        
        gbcc.gridx = 1;
        gbl.setConstraints(authorsText, gbcc);
        panelBook.add(authorsText);
        
        gbcc.gridx = 0;
        gbcc.gridy = 2;
        gbcc.insets = new Insets(5, 10, 0, 0);
        gbl.setConstraints(numVTitle, gbcc);
        panelBook.add(numVTitle);
        
        gbcc.gridx = 1;
        gbl.setConstraints(titleTitle, gbcc);
        panelBook.add(titleTitle);
        
        gbcc.gridx = 0;
        gbcc.gridy = 3;
        gbcc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(numVText, gbcc);
        panelBook.add(numVText);
        
        gbcc.gridx = 1;
        gbl.setConstraints(titleText, gbcc);
        panelBook.add(titleText);
        
        gbcc.gridx = 0;
        gbcc.gridy = 4;
        gbcc.insets = new Insets(5, 10, 0, 0);
        gbl.setConstraints(publisherTitle, gbcc);
        panelBook.add(publisherTitle);
        
        gbcc.gridy = 5;
        gbcc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(publisherText, gbcc);
        panelBook.add(publisherText);
        
        gbcc.gridx = 0;
        gbcc.gridy = 6;
        gbcc.insets = new Insets(5, 10, 0, 0);
        gbl.setConstraints(placeTitle, gbcc);
        panelBook.add(placeTitle);
        
        gbcc.gridx = 1;
        gbl.setConstraints(yearTitle, gbcc);
        panelBook.add(yearTitle);
        
        gbcc.gridx = 0;
        gbcc.gridy = 7;
        gbcc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(placeText, gbcc);
        panelBook.add(placeText);
        
        gbcc.gridx = 1;
        gbl.setConstraints(yearValue, gbcc);
        panelBook.add(yearValue);
        
        gbcc.gridx = 0;
        gbcc.gridy = 8;
        gbcc.insets = new Insets(5, 10, 0, 0);
        gbl.setConstraints(infoTitle, gbcc);
        panelBook.add(infoTitle);
        
        gbcc.gridy = 9;
        gbcc.gridwidth = 2;
        gbcc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(scroll, gbcc);
        panelBook.add(scroll);
        
        return panelBook;
    }
    
    public JPanel getPanelCopy(){
        Font fontLabel = new Font("Calibri", Font.BOLD, 14);
        Font fontText = new Font("Calibri", Font.PLAIN, 14);
        final int WIDTH_LABEL = 240;
        final int HEIGHT_ELEMENTS = 25;
        final int COUNT_CHAR = 23;
        final int COUNT_CHAR_TA = 47;
        JScrollPane scroll;
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbcc = new GridBagConstraints();
        JPanel panelCopy = new JPanel();
        panelCopy.setLayout(gbl);
        //
        JLabel bCTitle = new JLabel("Шкаф:");
        bCTitle.setFont(fontLabel);
        bCTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextField bCText = new JTextField(COUNT_CHAR);
        bCText.setFont(fontText);
        //
        JLabel bShTitle = new JLabel("Полка:");
        bShTitle.setFont(fontLabel);
        bShTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextField bShText = new JTextField(COUNT_CHAR);
        bShText.setFont(fontText);
        //
        JLabel condTitle = new JLabel("Состояние книги:");
        condTitle.setFont(fontLabel);
        condTitle.setSize(WIDTH_LABEL, HEIGHT_ELEMENTS);
        JTextArea condText = new JTextArea(4, COUNT_CHAR_TA);
        condText.setFont(fontText);
        condText.setLineWrap(true);
        condText.setWrapStyleWord(true);
        scroll = new JScrollPane(condText);
        //
        //event
        //
        gbcc.anchor = GridBagConstraints.NORTHWEST;
        gbcc.fill = GridBagConstraints.NONE;
        gbcc.gridheight = 1;
        gbcc.gridwidth = 1;
        gbcc.gridx = 0;
        gbcc.gridy = 0;
        gbcc.insets = new Insets(5, 10, 0, 0);
        gbcc.ipadx = 0;
        gbcc.ipady = 0;
        gbcc.weightx = 0.0;
        gbcc.weighty = 0.0;
        //
        gbl.setConstraints(bCTitle, gbcc);
        panelCopy.add(bCTitle);
        
        gbcc.gridx = 1;
        gbl.setConstraints(bShTitle, gbcc);
        panelCopy.add(bShTitle);
        
        gbcc.gridy = 1;
        gbcc.gridx = 0;
        gbcc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(bCText, gbcc);
        panelCopy.add(bCText);
        
        gbcc.gridx = 1;
        gbl.setConstraints(bShText, gbcc);
        panelCopy.add(bShText);
        
        gbcc.gridy = 2;
        gbcc.gridx = 0;
        gbcc.insets = new Insets(5, 10, 0, 0);
        gbl.setConstraints(condTitle, gbcc);
        panelCopy.add(condTitle);
        
        gbcc.gridy = 3;
        gbcc.gridwidth = 2;
        gbcc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(scroll, gbcc);
        panelCopy.add(scroll);
        //
        return panelCopy;
    }
    
    public JPanel getPanelSearch(){
        Font fontCombo = new Font("Calibri", Font.BOLD, 14);
        Font fontText = new Font("Calibri", Font.PLAIN, 14);
        final int WIDTH_CRITERION = 100;
        final int WIDTH_OPTION = 60;
        final int HEIGHT_ELEMENTS = 20;
        final int COUNT_CHAR = 23;
        String[] criteria = {"Авторы", "Название",
         "Год издания", "Шкаф"};
        String[] options = {"AND", "OR", "AND NOT"};
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbcc = new GridBagConstraints();
        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(gbl);
        //
        JComboBox[] cr = {
            new JComboBox(criteria),
            new JComboBox(criteria),
            new JComboBox(criteria)};
        cr[0].setSize(WIDTH_CRITERION, HEIGHT_ELEMENTS);
        cr[1].setSize(WIDTH_CRITERION, HEIGHT_ELEMENTS);
        cr[2].setSize(WIDTH_CRITERION, HEIGHT_ELEMENTS);
        cr[0].setFont(fontCombo);
        cr[1].setFont(fontCombo);
        cr[2].setFont(fontCombo);
        //
        JTextField[] keyWords = {
            new JTextField(COUNT_CHAR),
            new JTextField(COUNT_CHAR),
            new JTextField(COUNT_CHAR)};
        keyWords[0].setFont(fontText);
        keyWords[1].setFont(fontText);
        keyWords[2].setFont(fontText);
        //
        JComboBox[] op = {
            new JComboBox(options),
            new JComboBox(options)};
        op[0].setSize(WIDTH_OPTION, HEIGHT_ELEMENTS);
        op[1].setSize(WIDTH_OPTION, HEIGHT_ELEMENTS);
        op[0].setFont(fontCombo);
        op[1].setFont(fontCombo);
        //
        ImageIcon imgSearch = new ImageIcon("img/book_search_but.png");
        JButton buttonSearch = new JButton("Искать", imgSearch);
        buttonSearch.setSize(100, 40);
        buttonSearch.setToolTipText("Искать книгу");
        //
        //event
        //
        gbcc.anchor = GridBagConstraints.NORTHWEST;
        gbcc.fill = GridBagConstraints.NONE;
        gbcc.gridheight = 1;
        gbcc.gridwidth = 1;
        gbcc.gridx = 0;
        gbcc.gridy = 0;
        gbcc.insets = new Insets(5, 10, 0, 0);
        gbcc.ipadx = 0;
        gbcc.ipady = 0;
        gbcc.weightx = 0.0;
        gbcc.weighty = 0.0;
        //
        gbl.setConstraints(cr[0], gbcc);
        panelSearch.add(cr[0]);
        
        gbcc.gridx = 1;
        gbl.setConstraints(keyWords[0], gbcc);
        panelSearch.add(keyWords[0]);
        
        gbcc.gridx = 2;
        gbl.setConstraints(op[0], gbcc);
        panelSearch.add(op[0]);
        
        gbcc.gridy = 1;
        gbcc.gridx = 0;
        gbl.setConstraints(cr[1], gbcc);
        panelSearch.add(cr[1]);
        
        gbcc.gridx = 1;
        gbl.setConstraints(keyWords[1], gbcc);
        panelSearch.add(keyWords[1]);
        
        gbcc.gridx = 2;
        gbl.setConstraints(op[1], gbcc);
        panelSearch.add(op[1]);
        
        gbcc.gridy = 2;
        gbcc.gridx = 0;
        gbl.setConstraints(cr[2], gbcc);
        panelSearch.add(cr[2]);
        
        gbcc.gridx = 1;
        gbl.setConstraints(keyWords[2], gbcc);
        panelSearch.add(keyWords[2]);
        //
        gbcc.gridy = 3;
        gbcc.gridx = 2;
        gbl.setConstraints(buttonSearch, gbcc);
        panelSearch.add(buttonSearch);
        //
        return panelSearch;
    }
}
