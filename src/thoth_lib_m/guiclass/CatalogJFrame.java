/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.awt.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.*;
import java.sql.*;
import thoth_lib_m.dataclass.InfoSection;

/**
 *Главное окно модуля "Каталогизатор"
 * @author Sirota Dmitry
 */
public class CatalogJFrame extends JFrame{
    final int DEFAULT_WIDTH = 800;
    final int DEFAULT_HEIGHT = 650;
    
    public CatalogJFrame(){
        super("Каталогизатор");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public void createGUI(Connection c)
        throws SQLException{
        final int TABLE_HEIGHT = 300;
        final int SECTION_WIDTH = 150;
        final int SECTION_HEIGHT = 100;
        final int MENU_HEIGHT = 25;
        //JPanel mainPanel = new JPanel();
        //GridBagLayout gbl = new GridBagLayout();
        //GridBagConstraints gbcc = new GridBagConstraints();
        this.setLayout(new BorderLayout());
        CatalogJElements elem = new CatalogJElements();
        Section s = new Section();
        JTabbedPane tabbedPane = new JTabbedPane(
                    JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        
        tabbedPane.addTab("Библиографическое описание", elem.getPanelBook(c));
        tabbedPane.addTab("Данные книги", elem.getPanelCopy());
        tabbedPane.addTab("Поиск", elem.getPanelSearch());
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
        
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.setSize(this.getWidth(), TABLE_HEIGHT);
        JScrollPane scroll = new JScrollPane(tablePanel);
                
        Box boxAddit = Box.createHorizontalBox();
        s.getScrollSection().setSize(SECTION_WIDTH, SECTION_HEIGHT);
        s.addDataList(c);
        s.getSection().addListSelectionListener((ListSelectionEvent e) -> {
            Integer selectedIndex = s.getSection().getSelectedIndex();
            InfoSection ifS = s.getArrayISection(selectedIndex);
        });
        boxAddit.add(new JScrollPane(s.getScrollSection()));
        boxAddit.add(new JScrollPane(tabbedPane));
        
        
        this.add(boxMain, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(boxAddit, BorderLayout.SOUTH);
        //this.pack();
    }
    
    public void setShow(boolean visible){
        //this.setLocationRelativeTo(null);
        this.setVisible(visible);
    }
}
