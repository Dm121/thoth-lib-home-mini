/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

import javax.swing.JOptionPane;
/**
 *Совокупность данных о книге
 * @author Sirota Dmitry
 */
public class Book extends FieldID{
    private int idTypeBook;
    private int idSection;
    
    MainData mainData;		//Сведения об авторах и названии
    Dateline dateline;		//Сведения об издателе, месте и годе издания
    AdditData additData;	//Дополнительные сведения
    CopyBook copy;			//Сведения об экземпляре издания
    
    private boolean flagNewBook;
    
    /**
     *Конструктор с параметрами. Создаёт экземпляр книги 
     * с указанным id, типом издания, секцией расположения
     * @param idBook - id издания
     * @param idType - тип издания
     * @param idSection - id раздела (секции) расположения
     */
    public Book(int idBook, int idType, int idSection){
        super(idBook);
        this.idTypeBook = idType;
        this.idSection = idSection;
        this.mainData = new MainData();
        this.dateline = new Dateline();
        this.additData = new AdditData();
        this.flagNewBook = true;
    }
    
    /**
     *Возвращает тип издания
     */
    public int getIdTypeBook(){
        return this.idTypeBook;
    }
    
    /**
     *Устанавливает тип издания
     */
    public void setTypeBook(int idType){
        this.idTypeBook = idType;
    }
    
    /**
     *Возвращает id раздела (секции) расположения
     */
    public int getIdSection(){
        return this.idSection;
    }
    
    /**
     *Устанавливает id раздела (секции) расположения
     */
    public void setIdSection(int idSection){
        this.idSection = idSection;
    }
    
    /**
     *Возвращает объект с основными сведениями об издании:
     * авторами и названием издания
     */
    public MainData getMainData(){
        return this.mainData;
    }
    
    /**
     *Возвращает объект со сведениями об издателе,
     * месте и годе издания 
     */
    public Dateline getDateline(){
        return this.dateline;
    }
    
    /**
     *Возвращает объект с дополнительными сведениями об издании:
     * номером тома и примечаниями
     */
    public AdditData getAdditData(){
        return this.additData;
    }
    
    /**
     *Инициализирует запись в инвентарной книге
     * @param invNum - номер записи об экземпляре издания
     * в инвентарной книге
     */
    public void specifyCopyBook(int invNum){
        this.copy = new CopyBook(this.idBook, invNum);
        //
        //JOptionPane.showMessageDialog(null, "Экземпляр книги указан.");
        //
    }
    
    /**
     *Возвращает объект со сведениями об экземпляре издания
     */
    public CopyBook getCopyBook(){
        if(this.copy == null){
            JOptionPane.showMessageDialog(null, 
                    "Экземпляр книги не указан." + 
                            " Вызовите метод: specifyCopyBook.",
                    "Предупреждение (Warning): ",
                    JOptionPane.WARNING_MESSAGE);
        }
        return this.copy;
    }
    
    /**
     *Возвращает значение поля this.flagNewBook 
     * @return 
     */
    public boolean getStatusRecord(){
        return this.flagNewBook;
    }
    
    /**
     *Устанавливает значение пля this.flagNewBook
     * @param flag 
     */
    public void setStatusRecord(boolean flag){
        this.flagNewBook = flag;
    }
    
    /**
     *Возвращает строку с информацией об издании
     * @return 
     */
    @Override
    public String toString(){
        String copyNull;
        if(this.copy == null){
            copyNull = "Экземпляр книги не указан.";
        }
        else { copyNull = this.copy.toString(); }
        return "Book in database: " + this.flagNewBook + ".\n" +
                "[id of book = " + this.idBook + "]\n" + 
                "Type of Book: " + this.idTypeBook + ".\n" +
                "Number of Section: " + this.idSection + ".\n" +
                this.mainData.toString() + "\n" +
                this.dateline.toString() + "\n" +
                this.additData.toString() + "\n" +
                copyNull;
    }
}
