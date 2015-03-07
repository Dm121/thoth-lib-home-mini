/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import thoth_lib_m.AdditClass;
import thoth_lib_m.guiclass.CatalogJElements;
/**
 *Событие для кнопки "Создать новую запись"
 * @author Sirota Dmitry
 */
public class NewButAction implements ActionListener{
    
    private CatalogJElements elem;
    private JTable table;
    private JTabbedPane tab;
    
    public NewButAction(CatalogJElements elem, JTable table, 
            JTabbedPane tab){
        this.elem = elem;
        this.table = table;
        this.tab = tab;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
                elem.setValIdB("");
                elem.setValTypeEdition(0);
                elem.getTextBook().get(0).setText("");
                elem.getTextBook().get(1).setText("");
                elem.getTextBook().get(2).setText("");
                elem.getTextBook().get(3).setText("");
                elem.getTextBook().get(4).setText("");
                elem.setValYearValue(2015);
                elem.getTextCopy().get(0).setText("");
                elem.getTextCopy().get(1).setText("");
                elem.getTextArray().get(0).setText("");
                elem.getTextArray().get(1).setText("");
                table.clearSelection();
                setTextCountBook();
                tab.setSelectedIndex(0);
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
}
