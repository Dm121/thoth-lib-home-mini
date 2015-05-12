/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.databaseclass.ConnectionSQLiteDB;
//import thoth_lib_m.guiclass.Section;

/**
 *Окно для выбора раздела, в который будет перенесено издание
 * @author Sirota Dmitry
 */

public class SelectionSectionFrame extends JDialog {
    
    private final int DEFAULT_WIDTH = 200;
    private final int DEFAULT_HEIGHT = 240;
    
    private boolean resultDialog;
    private int idSelectedSection;
    
    public SelectionSectionFrame(JFrame owner){
        super(owner, "Выберете книжный раздел: ", true);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public boolean getResultDialog(){
        return this.resultDialog;
    }
    
    public int getIdSelectedSection(){
        return this.idSelectedSection;
    }
    
    public void createGUI(){
        final int WIDTH_OK = 120;
        final int WIDTH_CANCEL = 240;
        final int HEIGHT_BUT = 40;
        //
        Section s = new Section();
        try{
            ConnectionSQLiteDB connect = new ConnectionSQLiteDB();
            if(!connect.connDB("db/thoth_lhm_sqlite.db")){
                s.addDataList(connect.getConnectionC());
                connect.closeDB(connect.getConnectionC());
            }
        }
        catch(SQLException e){
            AdditClass.errorMes(e, "SelectionSectionFrame.createGUI");
        }
        Font fontBut = new Font("Serif", Font.PLAIN, 14);
        //
        Box box = Box.createVerticalBox();
        //
        JPanel pSection = new JPanel();
        pSection.setLayout(new GridLayout(1,1));
        JPanel pButtons = new JPanel();
        pButtons.setLayout(new FlowLayout());
        //
        JButton oKBut = new JButton("OK");
        oKBut.setSize(WIDTH_OK, HEIGHT_BUT);
        oKBut.setFont(fontBut);
        JButton cancelBut = new JButton("Cancel");
        cancelBut.setSize(WIDTH_CANCEL, HEIGHT_BUT);
        cancelBut.setFont(fontBut);
        //
        pSection.add(new JScrollPane(s.getScrollSection()));
        pButtons.add(oKBut);
        pButtons.add(cancelBut);
        box.add(pSection);
        box.add(Box.createVerticalStrut(4));
        box.add(pButtons);
        this.add(box);
        //
        oKBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectionSectionFrame.this.idSelectedSection = 
                        s.getArrayISection(
                            s.getSection().getSelectedIndex()).getIdSection();
                SelectionSectionFrame.this.resultDialog = true;
                SelectionSectionFrame.this.setVisible(false);
            }
        });
        //
        cancelBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectionSectionFrame.this.idSelectedSection = -1;
                SelectionSectionFrame.this.resultDialog = false;
                SelectionSectionFrame.this.setVisible(false);
            }
        });
        //
        this.setVisible(true);
    }
}
