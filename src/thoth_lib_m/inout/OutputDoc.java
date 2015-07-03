/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.inout;

import java.io.*;

/**
 *Запись (экспорт) данных библиотечных изданий в файл
 * @author Sirota Dmitry
 */
public class OutputDoc {
    
    //Конструктор по умолчанию
    public OutputDoc(){
        
    }
    
    /**
     *Создание каталога, в котором будут размещены файлы
     * со списками библиотечных изданий
     * @param nameDirectory - имя создаваемого катлога
     * @return absolutePath - путь, включающий имя существующего/
     * созданного каталога
     */
    public String createDir(String nameDirectory){
        String absolutePath = "";
        //
        
        //
        return absolutePath;
    }
    
    //Часть _Дата_Время  имени нового файла со списком библиотечных изданий
    private String nameDateTime(){
        String partName = "";
        //
        
        //
        return partName;
    }
    
    /**
     *Запись строки output в файл с именем nameFile
     * @param output - исходная строка для записи
     * @param nameFile - имя файла
     * @return flag - true, если ошибок не было, иначе false
     */
    public boolean outputFile(String output, String nameFile){
        boolean flag = false;
        //
        
        //
        return flag;
    }
}
