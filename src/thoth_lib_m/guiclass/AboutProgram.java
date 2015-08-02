/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 *Окно "О Программе"
 * @author Sirota Dmitry
 */
public class AboutProgram extends JDialog{
    
    private static final int WIDTH_DEFAULT = 550;
    private static final int HEIGHT_DEFAULT = 550;
    private static final int COUNT_COLUMNS_TA = 36;
    private static final int COUNT_ROWS_TA = 8;
    //
    private JFrame frame;
            
    //Конструктор по умолчанию
    public AboutProgram(JFrame frame){
        super(frame, "О программе Thoth_lib_m v1.0.0", true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(WIDTH_DEFAULT, HEIGHT_DEFAULT);
        this.setResizable(false);
    }
    
    //Создание интерфейса окна "О Программе"
    public void createGUI(){
        //
        //final int WIDTH_TA = 400;
        //final int HEIGHT_TA = 60;
        //
        Font fontAbout = new Font("Arial", Font.PLAIN, 12);
        Font fontBut = new Font("Arial", Font.BOLD, 13);
        //
        String aboutProgram = "Программа \"Библиотека Thoth_lib_m\": \n" +
                                "  Версия: v1.0.0 \n" +
                                "  Лицензия: \n" + 
                "     1. Программу разрешено копировать и запускать " +
                "на неограниченном количестве компьютеров.\n" +
                "     2. Программу разрешено изменять и распространять в " +
                "изменённом виде только с сохранением имени первоначального " +
                "автора Sirota Dmitry (Россия, НИУ ИТМО).\n" +
                "     3. Первоначальный автор не несёт ответственности за " +
                "любой возможный или причинённый ущерб кому-либо или " +
                "чему-либо в результате использования данной программы " +
                "(оригинала) или изменённой кем-либо или чем-либо её копии.\n" +
                                "  Мы помогаем Вам подниматься вверх\n" +
                                "  Все права защищены, 2015";
        //
        String description = "АБИС \"Thoth_lib_m v1.0.0\"";
        //
        ImageIcon imgThoth = new ImageIcon(getClass().getResource("img/Thoth/img1_2.jpg"));
        imgThoth.setDescription(description);
        JLabel imgLabel = new JLabel(imgThoth);
        //
        //JLabel aboutLabel = new JLabel(aboutProgram);
        JTextArea aboutTA = new JTextArea(COUNT_ROWS_TA, COUNT_COLUMNS_TA);
        aboutTA.setFont(fontAbout);
        aboutTA.setText(aboutProgram);
        aboutTA.setSelectionStart(0);
        aboutTA.setSelectionEnd(0);
        aboutTA.setWrapStyleWord(true);
        aboutTA.setLineWrap(true);
        aboutTA.setEnabled(false);
        aboutTA.setDisabledTextColor(Color.BLACK);
        aboutTA.setAutoscrolls(false);
        JScrollPane scroll = new JScrollPane(aboutTA);
        //
        JButton oKBut = new JButton("OK");
        oKBut.setFont(fontBut);
        //
        oKBut.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                AboutProgram.this.setVisible(false);
            }
            
        });
        //
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbcc = new GridBagConstraints();
        //
        this.setLayout(gbl);
        //
        gbcc.anchor = GridBagConstraints.NORTH;
        gbcc.fill = GridBagConstraints.NONE;
        gbcc.gridheight = 1;
        gbcc.gridwidth = GridBagConstraints.REMAINDER;
        gbcc.gridx = GridBagConstraints.RELATIVE;
        gbcc.gridy = GridBagConstraints.RELATIVE;
        gbcc.insets = new Insets(10, 5, 0, 5);
        gbcc.ipadx = 0;
        gbcc.ipady = 0;
        gbcc.weightx = 0.0;
        gbcc.weighty = 0.0;
        //
        gbl.setConstraints(imgLabel, gbcc);
        this.add(imgLabel);
        //
        gbcc.insets = new Insets(0, 0, 0, 0);
        gbcc.gridheight = 5;
        //
        gbl.setConstraints(scroll, gbcc);
        this.add(scroll);
        //
        gbcc.gridheight = 1;
        //
        gbl.setConstraints(oKBut, gbcc);
        this.add(oKBut);
        //
        AboutProgram.this.setVisible(true);
        //
        //Расположить элементы в окне:
        // - изображение;
        // - текст (О Программе);
        // - кнопка \"OK\"
        //Добавить в главное окно в меню \"Файл\" пункт \"О Программе\"
        //Добавить событие вызова окна \"О Программе\" 
        //в \"Файл\"->\"О Программе\"
        //скрыть пункт \"Cохранить изменения\", событие SaveDataButAction
    }    
}
