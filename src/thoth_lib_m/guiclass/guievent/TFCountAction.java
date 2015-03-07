/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.util.regex.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import thoth_lib_m.AdditClass;

/**
 *Событие для полей типа JTextField для регулирования 
 * количества введённых символов
 * @author Sirota Dmitry
 */
public class TFCountAction implements KeyListener{
    
    private JTextField textField;
    private final int maxCountChar;
    private JLabel l;
    
    public TFCountAction(JTextField text, int countChar, JLabel l){
        this.textField = text;
        this.maxCountChar = countChar;
        this.l = l;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        StringBuffer titleL = new StringBuffer();
        StringBuffer textL = new StringBuffer();
        textL.append(this.textField.getText());
        if(textL.length() > this.maxCountChar){
            textL.delete(this.maxCountChar, textL.length());
            this.textField.setText(textL.toString());
        }
        if(this.l != null){
            titleL.append(getTitle(this.l.getText()));
            titleL.append((this.maxCountChar - textL.length()));
            titleL.append("):");
            l.setText(titleL.toString());
        }
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
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
}
