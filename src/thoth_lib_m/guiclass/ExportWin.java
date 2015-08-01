/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
//import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *Окно экспорта и печати
 * @author Sirota Dmitry
 */
public class ExportWin extends JDialog {
    
    private static final int WIDTH_DEFAULT = 200;
    private static final int HEIGHT_DEFAULT = 150;
    //
    private int resultDialog;
    private boolean resClose;
    
    //Конструктор с параметрами
    public ExportWin(JFrame frame){
        super(frame, "Печать/Экспорт", true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(WIDTH_DEFAULT, HEIGHT_DEFAULT);
        this.resultDialog = -1;
        this.resClose = false;
    }
    
    //Свойство для получения значения выбора параметра экспорта/печати
    public int getResultDialog(){
        return this.resultDialog;
    }
    
    //Свойство для установки значения параметра экспорта/выбора
    public void setResultDialog(int res){
        this.resultDialog = res;
    }
    
    //Свойство для получения значения закрытия (выхода из) окна экспорта/выбора
    public boolean getResClose(){
        return this.resClose;
    }
    
    //Свойство для установки значения закрытия (выхода из) окна экспорта/выбора 
    public void setResClose(boolean resB){
        this.resClose = resB;
    }
    
    //Создание интерфейса и вывод окна
    public void createGUI(){
        Font fontP = new Font("Arial", Font.PLAIN, 12);
        Font fontBut = new Font("Arial", Font.BOLD, 13);
        //
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbcc = new GridBagConstraints();
        //
        JPanel panelExport = new JPanel();
        panelExport.setLayout(gbl);
        //
        JButton exportBut = new JButton("Экспорт");
        exportBut.setFont(fontBut);
        JButton cancelBut = new JButton("Отмена");
        cancelBut.setFont(fontBut);
        //
        exportBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ExportWin.this.setResClose(true);           //1
                ExportWin.this.setVisible(false);
            }
        });
        //
        cancelBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ExportWin.this.setResultDialog(-1);
                ExportWin.this.setVisible(false);
            }
        });
        //
        ButtonGroup bg  = new ButtonGroup();
        Box boxRBut = Box.createVerticalBox();
        JRadioButton rButS = new JRadioButton("Текущий раздел");
        rButS.setFont(fontP);
        rButS.setSelected(true);
        this.setResultDialog(1);
        JRadioButton rButL = new JRadioButton("Вся библиотека");
        rButL.setFont(fontP);
        bg.add(rButS);
        bg.add(rButL);
        boxRBut.add(rButS);
        boxRBut.add(rButL);
        boxRBut.setBorder(new TitledBorder("Экспорт в HTML:"));
        //
        rButS.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ExportWin.this.setResultDialog(1);
            }
        });
        //
        rButL.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ExportWin.this.setResultDialog(2);
            }
        });
        //
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
        //
        gbl.setConstraints(boxRBut, gbcc);
        panelExport.add(boxRBut);
        //
        gbcc.gridx = 1;
        gbcc.gridy = 1;
        gbcc.gridwidth = 1;
        //
        gbl.setConstraints(exportBut, gbcc);
        panelExport.add(exportBut);
        //
        gbcc.gridx = 2;
        //
        gbl.setConstraints(cancelBut, gbcc);
        panelExport.add(cancelBut);
        //
        this.add(panelExport);
        this.setResClose(false);
        this.setVisible(true);
    }
    
}
