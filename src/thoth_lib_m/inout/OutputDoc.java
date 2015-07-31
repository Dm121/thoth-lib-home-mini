/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.inout;

import java.util.Calendar;
import java.io.*;
import thoth_lib_m.AdditClass;

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
        String mess;
        String nameMethod = "OutputDoc.createDir(String nameDirectory)";
        //
        File dir = new File(nameDirectory);
        //
        try{
            //...а если каталог существует, но нет css-файла
            if(!dir.exists()){
                if(dir.mkdir()){
                    mess = "Каталог " + dir.getName() +" успешно создан.";
                    AdditClass.infoMes(mess, nameMethod);
                    absolutePath = dir.getAbsolutePath();
                }
                else{
                    mess = "Не получилось создать каталог " + 
                                                        dir.getName() + ".\n" +
                            "Возможно, что каталог уже существует.";
                    AdditClass.infoMes(mess, nameMethod);
                }
            }
            //else - получить абсолютный путь к каталогу, если он уже существует
            else{
                absolutePath = dir.getAbsolutePath();
            }
        }
        catch(Exception e){
            AdditClass.errorMes(e, nameMethod);
            absolutePath = "";
        }
        //
        return absolutePath;
    }
    
    /**
     *Часть _Дата_Время имени нового файла со списком библиотечных изданий
     * @return partName - часть имени файла типа _Дата_Время
     */
        protected String nameDateTime(){
        String partName;
        //
        Calendar curDateTime = Calendar.getInstance();
        //
        partName = "_" + curDateTime.get(Calendar.DAY_OF_MONTH) + 
                (curDateTime.get(Calendar.MONTH) + 1) +
                curDateTime.get(Calendar.YEAR) + "_" +
                curDateTime.get(Calendar.HOUR_OF_DAY) +
                curDateTime.get(Calendar.MINUTE) +
                curDateTime.get(Calendar.SECOND);
        //
        return partName;
    }
    
    /**
     *Запись строки output в файл с именем nameFile
     * @param output - исходная строка для записи
     * @param nameFile - имя файла
     * @return flag - true, если ошибок не было, иначе false
     * @throws java.io.IOException
     */
    public boolean outputFile(String output, String nameFile)
            throws IOException{
        boolean flag = false;
        int i;  //for loop
        //
        String[] printStr = output.split("\n");
        String mess;
        String nameMethod = "OutputDoc.outputFile(String output, " + 
                "String nameFile)";
        File f = new File(nameFile);
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        //
        if(f.exists()){
            mess = "Файл " + f.getName() + " уже существует " + 
                    "и не будет перезаписан."; 
            AdditClass.infoMes(mess, nameMethod);
        }
        else{
            try{
                fw = new FileWriter(f);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);
                //
                for(i = 0; i < printStr.length; i++){
                    pw.println(printStr[i]);
                }
                //
                flag = true;
            }
            catch(IOException e){
                AdditClass.errorMes(e, nameMethod);
            }
            finally{
                if(pw != null){
                    pw.close();
                }
            }
        }
        //
        return flag;
    }
}
