/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

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
        
        JMenuBar catalogMenu = new JMenuBar();
        catalogMenu.setFont(font);
        
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem saveMenu = new JMenu("Сохранить изменения");
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
        final int WIDTH_LABEL = 180;
        final int WIDTH_TEXT = 240;
        final int HEIGHT_ELEMENTS = 25;
        final int HEIGHT_TEXTAREA = 100;
        final int COUNT_CHAR = 50;
        final int INDENT_ELEMENTS = 5;
        //JScrollPane scroll;
        //JScrollPane scrollBox;
        JPanel panelScroll = new JPanel();
        Box boxPanel = Box.createVerticalBox();
        JPanel panelBook = new JPanel();
        panelBook.setLayout(null);
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
        //scrollBox = new JScrollPane(typeEdition);
        //scrollBox.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //scrollBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
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
        JTextArea infoText = new JTextArea(4, COUNT_CHAR);
        infoText.setFont(fontText);
        infoText.setSize(2 * WIDTH_TEXT + 10, HEIGHT_TEXTAREA);
        //
        //event
        //
        typeTitle.setLocation(INDENT_ELEMENTS, 5);
        typeEdition.setLocation(INDENT_ELEMENTS, 5 + HEIGHT_ELEMENTS);
        authorsTitle.setLocation(INDENT_ELEMENTS + WIDTH_TEXT + 10, 5);
        authorsText.setLocation(INDENT_ELEMENTS + WIDTH_TEXT + 10, 
                authorsTitle.getLocation().y + HEIGHT_ELEMENTS);
        numVTitle.setLocation(INDENT_ELEMENTS, 
                authorsText.getLocation().y + HEIGHT_ELEMENTS + 5);
        numVText.setLocation(INDENT_ELEMENTS, 
                numVTitle.getLocation().y + HEIGHT_ELEMENTS);
        titleTitle.setLocation(INDENT_ELEMENTS + WIDTH_TEXT + 10, 
                authorsText.getLocation().y + HEIGHT_ELEMENTS + 5);
        titleText.setLocation(INDENT_ELEMENTS + WIDTH_TEXT + 10, 
                titleTitle.getLocation().y + HEIGHT_ELEMENTS);
        publisherTitle.setLocation(INDENT_ELEMENTS, 
               titleText.getLocation().y + HEIGHT_ELEMENTS + 5);
        publisherText.setLocation(INDENT_ELEMENTS, 
                publisherTitle.getLocation().y + HEIGHT_ELEMENTS);
        placeTitle.setLocation(INDENT_ELEMENTS, 
                publisherText.getLocation().y + HEIGHT_ELEMENTS + 5);
        placeText.setLocation(INDENT_ELEMENTS, 
                placeTitle.getLocation().y + HEIGHT_ELEMENTS);
        yearTitle.setLocation(INDENT_ELEMENTS + WIDTH_TEXT + 10, 
                publisherText.getLocation().y + HEIGHT_ELEMENTS + 5);
        yearValue.setLocation(INDENT_ELEMENTS + WIDTH_TEXT + 10, 
                yearTitle.getLocation().y + HEIGHT_ELEMENTS);
        infoTitle.setLocation(INDENT_ELEMENTS, 
                placeText.getLocation().y + HEIGHT_ELEMENTS + 5);
        infoText.setLocation(INDENT_ELEMENTS, 
                infoTitle.getLocation().y + HEIGHT_ELEMENTS);
        //
        panelBook.add(typeTitle);
        panelBook.add(typeEdition);
        panelBook.add(authorsTitle);
        panelBook.add(authorsText);
        panelBook.add(numVTitle);
        panelBook.add(numVText);
        panelBook.add(titleTitle);
        panelBook.add(titleText);
        panelBook.add(publisherTitle);
        panelBook.add(publisherText);
        panelBook.add(placeTitle);
        panelBook.add(placeText);
        panelBook.add(yearTitle);
        panelBook.add(yearValue);
        panelBook.add(infoTitle);
        panelBook.add(infoText);
        //
        boxPanel.add(panelBook);
        panelScroll.add(boxPanel);
        //scroll = new JScrollPane(panelScroll);
        //scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return panelScroll;
    }
    
}
