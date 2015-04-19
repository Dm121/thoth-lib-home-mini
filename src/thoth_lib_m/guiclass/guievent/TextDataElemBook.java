/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.Book;
import thoth_lib_m.guiclass.CatalogJElements;

/**
 *Класс для отображения данных книги в текстовых полях 
 * и дополнительной работы с текстовыми полями.
 * @author Sirota Dmitry
 */
public class TextDataElemBook {
    
    public TextDataElemBook(){
        //
    }
    
    public static void emptyDataBook(CatalogJElements elem){
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
        setTextCountBook(elem);
    }
    
    public static void getDataBook(Book book, CatalogJElements elem){
        Book b = book;
        elem.setValIdB(String.valueOf(b.getIdBook()));
        elem.setValTypeEdition(b.getIdTypeBook() - 1);
        elem.getTextBook().get(0).setText(b.getMainData().getAuthors());
        elem.getTextBook().get(1).setText(b.getAdditData().getNumVolume());
        elem.getTextBook().get(2).setText(b.getMainData().getTitle());
        elem.getTextBook().get(3).setText(b.getDateline().getPublisher());
        elem.getTextBook().get(4).setText(b.getDateline().getPlace());
        elem.setValYearValue(b.getDateline().getYear());
        elem.getTextArray().get(0).setText(b.getAdditData().getNotes());
        elem.getTextCopy().get(0).setText(b.getCopyBook().getBookCase());
        elem.getTextCopy().get(1).setText(b.getCopyBook().getBookShelf());
        elem.getTextArray().get(1).setText(b.getCopyBook().getCondition());
        //
        setTextCountBook(elem);
    }
    
    private static void setTextCountBook(CatalogJElements elem){
        final int[] MAX_COUNT_CHAR;
        MAX_COUNT_CHAR = new int[]{20, 120, 200, 250, 400};
        //
        elem.getTextCount().get(0).setText(getTitle(
                elem.getTextCount().get(0).getText()) + 
                (MAX_COUNT_CHAR[3] - 
                            elem.getTextBook().get(0).getText().length()) + 
                "):");
        elem.getTextCount().get(1).setText(getTitle(
                elem.getTextCount().get(1).getText()) + 
                (MAX_COUNT_CHAR[0] - 
                            elem.getTextBook().get(1).getText().length()) + 
                "):");
        elem.getTextCount().get(2).setText(getTitle(
                elem.getTextCount().get(2).getText()) + 
                (MAX_COUNT_CHAR[2] - 
                            elem.getTextBook().get(2).getText().length()) + 
                "):");
        elem.getTextCount().get(3).setText(getTitle(
                elem.getTextCount().get(3).getText()) + 
                (MAX_COUNT_CHAR[1] - 
                            elem.getTextBook().get(3).getText().length()) + 
                "):");
        elem.getTextCount().get(4).setText(getTitle(
                elem.getTextCount().get(4).getText()) + 
                (MAX_COUNT_CHAR[2] - 
                            elem.getTextBook().get(4).getText().length()) + 
                "):");
        elem.getTextCount().get(5).setText(getTitle(
                elem.getTextCount().get(5).getText()) + 
                (MAX_COUNT_CHAR[4] - 
                            elem.getTextArray().get(0).getText().length()) + 
                "):");
        elem.getTextCount().get(6).setText(getTitle(
                elem.getTextCount().get(6).getText()) + 
                (MAX_COUNT_CHAR[0] - 
                            elem.getTextCopy().get(0).getText().length()) + 
                "):");
        elem.getTextCount().get(7).setText(getTitle(
                elem.getTextCount().get(7).getText()) + 
                (MAX_COUNT_CHAR[0] - 
                            elem.getTextCopy().get(1).getText().length()) + 
                "):");
        elem.getTextCount().get(8).setText(getTitle(
                elem.getTextCount().get(8).getText()) + 
                (MAX_COUNT_CHAR[2] - 
                            elem.getTextArray().get(1).getText().length()) + 
                "):");
    }
    
    
    private static String getTitle(String input){
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
