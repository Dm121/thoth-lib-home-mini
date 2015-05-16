/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.databaseclass.DataSearch;

/**
 *Вкладка с формой для поиска изданий, окно "Каталогизатор"
 * @author Sirota Dmitry
 */
public class SearchPane {
    
    private final static int WIDTH_CRITERION = 100;
    private final static int WIDTH_OPTION = 60;
    private final static int HEIGHT_ELEMENTS = 20;
    private final static int COUNT_CHAR = 23;
    //
    private final static String WARNING_KW_ONE = 
                                    "**Это поле обязательно для заполнения.*";
    //
    private final static String[] criteria = {"Авторы", "Название",
        "Год издания", "Шкаф"};
    private final static String[] criteriaOther = {"", 
        "Авторы", "Название", "Год издания", "Шкаф"};
    private final static String[] optionsFirst = {"", "NOT"};
    private final static String[] options = {"AND", "OR", "AND NOT"};
    //
    private final Font fontCombo = new Font("Calibri", Font.BOLD, 14);
    private final Font fontText = new Font("Calibri", Font.PLAIN, 14);
    //
    private List<JComboBox> cr;
    private List<JTextField> keyWords;
    private List<JComboBox> op;
    //
    private CatalogJFrame frame;
    //
    
    public SearchPane(){
        this.initialElements();
        this.frame = null;
    }
    
    public SearchPane(CatalogJFrame frame){
        this.initialElements();
        this.frame = frame;
    }
    
    private void initialElements(){
        this.cr = new ArrayList<>();
        this.cr.add(new JComboBox(criteria));
        this.cr.add(new JComboBox(criteriaOther));
        this.cr.add(new JComboBox(criteriaOther));
        this.cr.get(0).setSize(WIDTH_CRITERION, HEIGHT_ELEMENTS);
        this.cr.get(1).setSize(WIDTH_CRITERION, HEIGHT_ELEMENTS);
        this.cr.get(2).setSize(WIDTH_CRITERION, HEIGHT_ELEMENTS);
        this.cr.get(0).setFont(fontCombo);
        this.cr.get(1).setFont(fontCombo);
        this.cr.get(2).setFont(fontCombo);
        //
        this.keyWords = new ArrayList<>();
        this.keyWords.add(new JTextField(COUNT_CHAR));
        this.keyWords.add(new JTextField(COUNT_CHAR));
        this.keyWords.add(new JTextField(COUNT_CHAR));
        this.keyWords.get(0).setFont(fontText);
        this.keyWords.get(1).setFont(fontText);
        this.keyWords.get(2).setFont(fontText);
        this.keyWords.get(0).setText(WARNING_KW_ONE);
        //
        this.op = new ArrayList<>();
        this.op.add(new JComboBox(optionsFirst));
        this.op.add(new JComboBox(options));
        this.op.add(new JComboBox(options));
        this.op.get(0).setSize(WIDTH_OPTION, HEIGHT_ELEMENTS);
        this.op.get(1).setSize(WIDTH_OPTION, HEIGHT_ELEMENTS);
        this.op.get(2).setSize(WIDTH_OPTION, HEIGHT_ELEMENTS);
        this.op.get(0).setFont(fontCombo);
        this.op.get(1).setFont(fontCombo);
        this.op.get(2).setFont(fontCombo);
    }
    
    public List<JComboBox> getCriteria(){
        return this.cr;
    }
    
    public List<JTextField> getKeyWords(){
        return this.keyWords;
    }
    
    public List<JComboBox> getOptions(){
        return this.op;
    }
    
    public void setCatalogJFrame(CatalogJFrame frame){
        this.frame = frame;
    }
    
    public JPanel getPanelSearch(){
        
        
        
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbcc = new GridBagConstraints();
        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(gbl);
        //
        //
        //
        this.getKeyWords().get(0).addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent e) {
                if(SearchPane.this.getKeyWords().get(0).getText()
                    .trim().equals(WARNING_KW_ONE)){
                    SearchPane.this.getKeyWords().get(0)
                                                    .setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(SearchPane.this.getKeyWords().get(0).getText()
                                                            .trim().equals("")){
                    SearchPane.this.getKeyWords().get(0)
                                                    .setText(WARNING_KW_ONE);
                    SearchPane.this.getKeyWords().get(0)
                    .setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                }
                else{
                    SearchPane.this.getKeyWords().get(0)
                    .setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                }
            }
            
        });
        //
        ImageIcon imgSearch = new ImageIcon("img/book_search_but.png");
        JButton buttonSearch = new JButton("Искать", imgSearch);
        buttonSearch.setSize(100, 40);
        buttonSearch.setToolTipText("Искать книгу");
        //
        //event
        buttonSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                DataSearch ds = null;
                try{
                    ds = new DataSearch();
                    //
                    AdditClass.infoMes(ds.selectQuery(SearchPane.this), 
                            "SearchPane.getPanelSearch");
                    //
                }
                catch(SQLException err){
                    AdditClass.errorMes(err, "SearchPane.getPanelSearch");
                }
                finally{
                   if(ds.getConnectionDBH() != null){
                       ds.closeConnection();
                   }
                }
            }
        });
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
        gbl.setConstraints(this.getOptions().get(0), gbcc);
        panelSearch.add(this.getOptions().get(0));
        
        gbcc.gridx = 1;
        gbl.setConstraints(this.getCriteria().get(0), gbcc);
        panelSearch.add(this.getCriteria().get(0));
        
        gbcc.gridx = 2;
        gbl.setConstraints(this.getKeyWords().get(0), gbcc);
        panelSearch.add(this.getKeyWords().get(0));
        
        gbcc.gridy = 1;
        gbcc.gridx = 0;
        gbl.setConstraints(this.getOptions().get(1), gbcc);
        panelSearch.add(this.getOptions().get(1));
        
        gbcc.gridx = 1;
        gbl.setConstraints(this.getCriteria().get(1), gbcc);
        panelSearch.add(this.getCriteria().get(1));
        
        gbcc.gridx = 2;
        gbl.setConstraints(this.getKeyWords().get(1), gbcc);
        panelSearch.add(this.getKeyWords().get(1));
        
        gbcc.gridy = 2;
        gbcc.gridx = 0;
        gbl.setConstraints(this.getOptions().get(2), gbcc);
        panelSearch.add(this.getOptions().get(2));
        
        gbcc.gridx = 1;
        gbl.setConstraints(this.getCriteria().get(2), gbcc);
        panelSearch.add(this.getCriteria().get(2));
        //
        gbcc.gridx = 2;
        gbl.setConstraints(this.getKeyWords().get(2), gbcc);
        panelSearch.add(this.getKeyWords().get(2));
        //
        gbcc.insets = new Insets(5, 155, 0, 0);
        gbcc.gridy = 3;
        gbcc.gridx = 2;
        gbl.setConstraints(buttonSearch, gbcc);
        panelSearch.add(buttonSearch);
        //
        return panelSearch;
    }
    
}
