/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import thoth_lib_m.AdditClass;
import thoth_lib_m.dataclass.CopyTable;
import thoth_lib_m.guiclass.CatalogJFrame;
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
    
    public PrintButAction(Section s, CatalogJFrame frame){
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
                            //
                            try{
                                this.openDirOrFile(this.getPathToHTMLFile());
                            }
                            catch(IOException err){
                                AdditClass.errorMes(err, nameMethod);
                            }
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
                            //
                            try{
                                this.openDirOrFile(this.getPathToHTMLFile());
                            }
                            catch(IOException err){
                                AdditClass.errorMes(err, nameMethod);
                            }
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
                case 3:{
                    //task2 - to print result of search query
                    if(this.createDirAndCSS()){
                        mess = "Текущий результат";
                        List<CopyTable> listBooks = this.getCurrentListTable();
                        this.shellSort(listBooks);
                        if(!this.getPathToHTMLFile().trim().equals("")){
                            htmlDoc.outputData(this.getPathToHTMLFile(), mess, 
                                                                    listBooks);
                            //((CatalogJFrame)this.frame).
                            //getTable().getListCopyTable()
                            //
                            //opening of directory
                            try{
                                this.openDirOrFile(this.getPathToHTMLFile());
                            }
                            catch(IOException err){
                                AdditClass.errorMes(err, nameMethod);
                            }
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
                            "в html-файл.\n" +
                            "2: Экспорт списка книг всей библиотеки " +
                            "в html-файл.\n" +
                            "3: Экспорт текущего (например, полученного " +
                            "в результате поискового запроса) списка книг " +
                            "в html-файл.\n" +
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
    
    /**
     *Открывает файл (или каталог) с помощью ассоциированного с ним приложения
     * @param pathTo - путь к файлу или каталогу
     * @throws IOException 
     */
    private void openDirOrFile(String pathTo) throws IOException{
        File dir;
        Desktop desk;
        //
        try{
            dir = new File(pathTo);
            desk = Desktop.getDesktop();
            desk.open(dir);
        }
        catch(IOException err){
            throw new IOException("Ошибка при открытии окна для " +
                                    "отображения файлов " + 
                                    "со списками изданий.\n(" + 
                                    err.getMessage() + ")\n");
        }
    }
    
    /**
     *Создаёт список из текущих данных, содержащихся в таблице 
     * @return Список из текущих данных, содержащихся в таблице
     */
    private List<CopyTable> getCurrentListTable(){
        int i;      //for loop
        List<CopyTable> listBooks = new ArrayList<CopyTable>();
        CopyTable copy;
        JTable table = ((CatalogJFrame)this.frame).getTable().getCopyTable();
        //
        for(i = 0; i < table.getRowCount(); i++){
            copy = new CopyTable((i + 1));
            copy.setAuthorsTable(table.getValueAt(i, 0).toString());
            copy.setTitleTable(table.getValueAt(i, 1).toString());
            copy.setYearTable(Integer.valueOf(table.getValueAt(i, 2).
                                                                toString()));
            copy.setBookCaseTable(table.getValueAt(i, 3).toString());
            copy.setBookShelfTable(table.getValueAt(i, 4).toString());
            listBooks.add(copy);
        }
        //
        return listBooks;
    }
    
    /**
     *Из книги Роберта Лафоре(-а) "Структуры данных и алгоритмы Java",
     * 2-ое издание, 2013 год, ПИТЕР (стр. 306) - Сортировка Шелла
     * @param listBooks - Список из объектов типа CopyTable, которые сортируются
     * по автору
     */
    private void shellSort(List<CopyTable> listBooks){
        int inner, outer;
        CopyTable temp;
        int h = 1;                      //вычисление исходного значения h
        while(h <= listBooks.size() / 3){
            h = h * 3 + 1;              //(1, 4, 13, 40, 121, ...)
        }
        while(h > 0){                   //последовательное уменьшение h до 1
            //h-сортировка файла
            for(outer = h; outer < listBooks.size(); outer++){
                temp = listBooks.get(outer);
                inner = outer;
                //
                while((inner > (h - 1)) && (
                        listBooks.get(inner - h).getAuthorsTable().
                        compareToIgnoreCase(temp.getAuthorsTable()) >= 0)){
                    listBooks.set(inner, listBooks.get(inner - h));
                    inner = inner - h;
                }
                listBooks.set(inner, temp);
            }
            h = (h - 1) / 3;
        }
    }
}
