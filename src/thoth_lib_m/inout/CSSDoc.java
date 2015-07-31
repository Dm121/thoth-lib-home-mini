/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.inout;

import java.io.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.inout.OutputDoc;

/**
 *Формирование и создание документа print.css с описанием стилей для 
 * HTML-документа при выводе последнего на печать
 * @author Sirota Dmitry
 */
public class CSSDoc extends DocumentContent{
    
    //Конструктор по умолчанию
    public CSSDoc(){
        super();
    }
    
    /**
     *Формирование содержимого файла print.css
     * @return строка с содержимым
     */
    public String textPrintCSS(){
        String appendStr = ".but {display: none;}";
        this.setTextDoc(appendStr);
        return this.getTextDoc();
    }
    
    /**
     *Запись содержимого в файл print.css 
     * @param pathToFile - полный путь к файлу print.css
     * @return flag - true, если метод выполняется без ошибок, иначе false
     */
    //@Override
    public boolean outputData(String pathToFile){
        boolean flag = false;
        //
        OutputDoc inout = new OutputDoc();
        String absoluteNameFile = pathToFile + "print.css";
        String nameMethod = "CSSDoc.outputData(String pathToFile)";
        try{
            inout.outputFile(this.textPrintCSS(), absoluteNameFile);
            flag = true;
        }
        catch(IOException e){
            AdditClass.errorMes(e, nameMethod);
        }
        this.clearTextDoc();
        //
        return flag;
    }
}
