/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.dataclass;

/**
 *Данные об экземпляре книги: состояние, 
 * местоположение книги (номер шкафа и номер полки) 
 * @author Sirota Dmitry
 */
public class CopyBook extends FieldID{
    private int invNum;
    private String condition;
    private String bookCase;
    private String bookShelf;
    
    /**
     *Конструктор с параметрами. Создаёт объект со сведениями
     * об экземпляре книги с id idBook, хранящимися в виде записи
     * инвентарной книги с номером invNum.
     * @param idBook - id книги
     * @param invNum - номер записи в инвентарной книге со сведениями
     * об экземпляре издания
     */
    public CopyBook(int idBook, int invNum){
        super(idBook);
        this.invNum = invNum;
        this.condition = "";
        this.bookCase = "-";
        this.bookShelf = "-";
    }
    
    /**
     *Конструктор с параметрами. Создаёт объект со сведениями
     * об экземпляре книги с id idBook, хранящимися в виде записи
     * инвентарной книги с номером invNum, и содержащими данные о состоянии,
     * обозначении книжного шкафа и полки расположения.
     * @param idBook - id книги
     * @param invNum - номер записи в инвентарной книге со сведениями
     * об экземпляре издания
     * @param condition - состояние издания
     * @param bookCase - обозначение книжного шкафа
     * @param bookShelf - обозначение полки книжного шкафа
     */
    public CopyBook(int idBook, int invNum,
            String condition, String bookCase, String bookShelf){
        super(idBook);
        this.invNum = invNum;
        this.condition = condition;
        this.bookCase = bookCase;
        this.bookShelf = bookShelf;
    }
    
    /**
     *Возвращает номер записи в инвентарной книге
     * со сведениями об экземпляре издания
     * @return 
     */
    public int getInvNum(){
        return invNum;
    }
    
    /**
     *Возвращает строку со сведениями о состоянии экземпляра издания
     * @return 
     */
    public String getCondition(){
        return this.condition;
    }
    
    /**
     *Устанавливает строку со сведениями об экземпляре издания
     * @param condition 
     */
    public void setCondition(String condition){
        this.condition = condition;
    }
    
    /**
     *Возвращает обозначение книжного шкафа, в котором хранится
     * экземпляр издания
     * @return 
     */
    public String getBookCase(){
        return this.bookCase;
    }
    
    /**
     *Устанавливает обозначение книжного шкафа, в которм хранится
     * экземпляр издания
     * @param bookCase 
     */
    public void setBookCase(String bookCase){
        this.bookCase = bookCase;
    }
    
    /**
     *Возвращает обозначение полки книжного шкафа,
     * на которой располагается экземпляр издания
     * @return 
     */
    public String getBookShelf(){
        return this.bookShelf;
    }
    
    /**
     *Устанавливает обозначение полки книжного шкафа,
     * на которой располагается экземпляр издания
     * @param bookShelf 
     */
    public void setBookShelf(String bookShelf){
        this.bookShelf = bookShelf;
    }
    
    /**
     *Возвращает строковое представление объекта, хранящего сведения
     * об экземпляре издания
     * @return 
     */
    @Override
    public String toString(){
        return "[id = " + idBook + "] " +
                "[inventory_number = " + this.invNum + "]\n" +
                "BookCase: " + this.bookCase + ".\n" +
                "BookShelf: " + this.bookShelf + ".\n" +
                "Condition: " + this.condition + ".";
    }
}
