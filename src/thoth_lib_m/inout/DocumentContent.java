/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.inout;

/**
 *Абстрактный класс для формирования содержимого на запись в файл 
 * @author Sirota Dmitry
 */
 abstract class DocumentContent {
    
    protected final StringBuffer textDoc;   //Содержимое файла
    
    //Конструктор по умолчанию
    public DocumentContent(){
        textDoc = new StringBuffer();
    }
    
    /**
     *Свойство для получения содержимого файла
     * @return строка с содержимым файла
     */
        public String getTextDoc(){
        return this.textDoc.toString();
    }
    
    /**
     *Свойство для добавления строкового содержимого в файл
     * @param appendStr - строка с добавляемой информацией
     */
    public void setTextDoc(String appendStr){
        this.textDoc.append(appendStr);
    }
    
    /**
     *Свойство для добавления текстового содержимого 
     * из объекта класса StringBuffer в файл 
     * @param appendSB - объект класса StringBuffer с добавляемой
     * информацией
     */
    public void setTextDoc(StringBuffer appendSB){
        this.textDoc.append(appendSB);
    }
    
    /**
     *Удаление всего содержимого из строки
     */
    public void clearTextDoc(){
        int first = 0;
        int last = this.textDoc.length() - 1;
        this.textDoc.delete(first, last);
    }
    
    /*
    /**
     *Абстрактный метод для записи содержимого в файл
     * @param pathToFile - путь к файлу
     
    public abstract void outputData(String pathToFile);
    */
}
