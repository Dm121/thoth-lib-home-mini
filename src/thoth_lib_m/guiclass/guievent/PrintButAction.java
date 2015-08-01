/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;
import thoth_lib_m.AdditClass;
import thoth_lib_m.guiclass.Section;
import thoth_lib_m.guiclass.ExportWin;
import thoth_lib_m.databaseclass.QueryPrint;
import thoth_lib_m.inout.*;

/**
 *Событие для кнопки "Печать списка книг" (печать и экспорт)
 * @author Sirota Dmitry
 */
public class PrintButAction implements ActionListener{
    
    private final JFrame frame;
    private Section s;
    private String pathToHTMLFile;
    
    public PrintButAction(Section s, JFrame frame){
        super();
        this.s = s;
        this.frame = frame;
        this.pathToHTMLFile = "";
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        //
        QueryPrint qp = null;
        HTMLDoc htmlDoc = new HTMLDoc();
        //
        String mess;
        String nameMethod = "PrintButAction.actionPerformed(ActionEvent e)";
        //
        ExportWin exportFrame = new ExportWin(this.frame);
        exportFrame.createGUI();
        if(!exportFrame.getResClose()){
            exportFrame.setResultDialog(-1);
        }
        //
        try{
            switch(exportFrame.getResultDialog()){
                case 1:{
                    if(this.createDirAndCSS()){
                        qp = new QueryPrint();
                        qp.nameSectionCur(s.getArrayISection(
                            s.getSection().getSelectedIndex()).getIdSection());
                        qp.booksSectionCur(s.getArrayISection(
                            s.getSection().getSelectedIndex()).getIdSection());
                        if(!this.getPathToHTMLFile().trim().equals("")){
                            htmlDoc.outputData(this.getPathToHTMLFile(), 
                                    qp.getNameS(), qp.getBooksPrint());
                        }
                        else{
                            mess = "Не удалось получить путь к каталогу " +
                                    "для создаваемого файла";
                            AdditClass.infoMes(mess, nameMethod);
                        }
                    }
                    else{
                        mess = "Не удалось создать каталог и/или css-файл " + 
                                "для создаваемых html-файлов";
                        AdditClass.infoMes(mess, nameMethod);
                    }
                    break;
                }
                case 2:{
                    if(this.createDirAndCSS()){
                        mess = "Вся Библиотека";
                        qp = new QueryPrint();
                        qp.booksLibrary();
                        if(!this.getPathToHTMLFile().trim().equals("")){
                            htmlDoc.outputData(this.getPathToHTMLFile(), 
                                    mess, qp.getBooksPrint());
                        }
                        else{
                            mess = "Не удалось получить путь к каталогу " +
                                    "для создаваемого файла";
                            AdditClass.infoMes(mess, nameMethod);
                        }
                    }
                    else{
                        mess = "Не удалось создать каталог и/или css-файл " + 
                                "для создаваемых html-файлов";
                        AdditClass.infoMes(mess, nameMethod);
                    }
                    break;
                }
                case -1:{
                    mess = "Отмена операции \"Экспорт/Печать\"";
                    AdditClass.infoMes(mess, nameMethod);
                    break;
                }
                default:{
                    mess = "Выход за границы допустимых значений.\n" +
                            "Допустимые значения: \n" +
                            "1: Экспорт списка книг текущего раздела " + 
                            " в html-файл.\n" +
                            "2: Экспорт списка книг всей библиотеки " +
                            " в html-файл.\n" +
                            "-1: Отмена операции \"Экспорт/Печать\".";
                    AdditClass.infoMes(mess, nameMethod);
                    break;
                }
            }
        }
        catch(SQLException err){
            AdditClass.errorMes(err, nameMethod);
        }
        finally{
            if(qp != null){
                if(qp.getConnectionDBH() != null){
                    qp.closeConnection();
                }
            }
        }
        //
    }
    
    //Свойство для получения пути к создаваемому html-файлу
    public String getPathToHTMLFile(){
        return this.pathToHTMLFile;
    }
    
    //Свойство для установки пути к создаваемому html-файлу
    private void setPathToHTMLFile(String path){
        this.pathToHTMLFile = path;
    }
    
    /**
     *Метод создаёт каталог для хранения html-файлов
     * со списками библиотечных изданий
     * и css-файл со стилями для печати html-документов 
     * @return flag - true, если ошибок при выполнении метода нет, иначе false 
     //если каталог и css-файл
     //не существовали и были созданы, иначе false
     */
    private boolean createDirAndCSS(){
        boolean flag = false;
        String nameDirectory = "listBooks";
        String pathToFile;
        String mess;
        String nameMethod = "PrintButAction.createDirAndCSS" + 
                                    "(String nameDirectory)";
        //
        OutputDoc inout = new OutputDoc();
        DocumentContent cssDoc = new CSSDoc();
        File dir = new File(nameDirectory);
        //
        //...а если каталог существует, но css-файл не создан
        //if(!dir.exists()){
        pathToFile = inout.createDir(nameDirectory);
            //вынести код для создания css-файла в отдельный метод
            //предусмотреть, когда метод возвращает true, а когда false,
            // если метод будет возвращать значение
            if(!pathToFile.trim().equals("")){
                
                if(!(new File(pathToFile + 
                        File.separator + "print.css").exists())){
                    if(((CSSDoc)cssDoc).
                            outputData(pathToFile + File.separator)){
                        this.setPathToHTMLFile(pathToFile + File.separator);
                        flag = true;
                    }
                }
                else{
                    this.setPathToHTMLFile(pathToFile + File.separator);
                    flag = true;
                }
            }
            else{
                mess = "Произошла ошибка: не удалось получить " +
                            "путь к каталогу для создаваемого файла.";
                AdditClass.warningMes(mess, nameMethod);
            }
        //}
        //else { flag = false; }
        //
        return flag;
    }
}
