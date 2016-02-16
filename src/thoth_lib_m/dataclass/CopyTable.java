/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

/**
 *Данные для заполнения таблицы "Книги"
 * @author Sirota Dmitry
 */
public class CopyTable extends FieldID{
    private String authors;
    private String title;
    private int year;
    private String bC;
    private String bSh;
    
    /**
     *Конструктр с параметрами. Создаёт экземпляр записи,
     * помещаемой в таблицу для просмора основных данных и выбора издания
     * @param idBookRec - id издания
     */
    public CopyTable(int idBookRec){
        super(idBookRec);
        authors = "";
        title = "";
        year = 2015;
        bC = "-";
        bSh = "-";
    }
    
    /**
     *Возвращает строку с авторами издания
     * @return 
     */
    public String getAuthorsTable(){
        return this.authors;
    }
    
    /**
     *Устанавливает строку, содержащую имена авторов издания
     * @param authors 
     */
    public void setAuthorsTable(String authors){
        this.authors = authors;
    }
    
    /**
     * Возвращает строку с названием издания
     * @return 
     */
    public String getTitleTable(){
        return this.title;
    }
    
    /**
     *Устанавливает название издания 
     * @param title 
     */
    public void setTitleTable(String title){
        this.title = title;
    }
    
    /**
     *Возвращает значение года издания
     * @return 
     */
    public int getYearTable(){
        return this.year;
    }
    
    /**
     *Устанавливает значение года издания
     * @param year 
     */
    public void setYearTable(int year){
        this.year = year;
    }
    
    /**
     *Возвращает номер книжного шкафа, в котором хранится экземпляр издания
     * @return 
     */
    public String getBookCaseTable(){
        return this.bC;
    }
    
    /**
     *Устанавливает номер книжного щкафа, в котором хранится экземпляр издания
     * @param bookCase 
     */
    public void setBookCaseTable(String bookCase){
        this.bC = bookCase;
    }
    
    /**
     *Возвращает номер полки книжного шкафа,
     * на которой располагаетя экземпляр издания 
     * @return 
     */
    public String getBookShelfTable(){
        return this.bSh;
    }
    
    /**
     *Устанавливает номер полки книжного шкафа,
     * на которой располагается экземпляр издания
     * @param bookShelf 
     */
    public void setBookShelfTable(String bookShelf){
        this.bSh = bookShelf;
    }
    
    /**
     *Возвращает строковое представление записи в таблице
     * для просмотра основных сведений и выбора издания
     * @return 
     */
    @Override
    public String toString(){
        return "Field 1 (authors): " + this.authors + "\n" +
                "Field 2 (title): " + this.title + "\n" +
                "Field 3 (year): " + this.year + "\n" +
                "Field 4 (bookcase): " + this.bC + "\n" +
                "Field 5 (bookshelf): " + this.bSh + "\n" +
                "Field 6 (idBook): " + this.idBook + ".";
    }
}
