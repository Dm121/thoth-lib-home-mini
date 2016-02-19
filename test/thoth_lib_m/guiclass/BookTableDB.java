/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.List;
import java.util.ArrayList;
import thoth_lib_m.dataclass.Book;

/**
 *Класс для подготовки к тестированию. Необходим для заполнения
 * таблицы Базы Данных (БД) bo_book перед тестированием
 * @author Sirota Dmitry
 */
public class BookTableDB {
    
    private final static int NSIZE = 20;
    //
    private final int idBook[] = new int[NSIZE];
    private final String numVolume[] = new String[]{
        "",
        "",
        "1",
        "выпуск 163",
        "Т. 1",
        "1",
        "1",
        "2",
        "",
        "3",
        "",
        "",
        "",
        "2/(256)",
        "",
        "",
        "",
        "",
        "",
        ""
    };
    private final String authors[] = new String[]{
        "Дж.Р.Р.Толкин",
        "",
        "Gray Jhon",
        "",
        "Некрасов Н.А.",
        "Hello and Goodbye",
        "Tom Bombadil",
        "\"Hello\" and 'Goodbye'",
        "Hello and Goodbye",
        "Tim Beroen",
        "А.П. Чехов",
        "М.А.Булгаков",
        "Л.Д.Ландау",
        "",
        "Капица",
        "А.А.Пинский, Г.Ю.Граковский",
        "Микоян",
        "В.А.Петров",
        "Довлатов",
        "example_author"
    };
    private final String title[] = new String[]{
        "Хоббит или туда и обратно",
        "Новый Завет и Псалтирь",
        "testbook",
        "Косово поле – 1389",
        "Стихи и рассказы",
        "BrT",
        "Book of North",
        "Истории Тома Бомбадила",
        "Толкин и его миры",
        "Book of West and East",
        "Рассказы",
        "Мастер и Маргарита",
        "О будущем науки в XXI веке",
        "Российская газета",
        "Физика и другие тайны Вселенной",
        "Физика: Учебник / Под общ. ред. Ю.И.Дика, Н.С.Пурышевой",
        "О Самолётостроении",
        "Hang",
        "Уроки чтения",
        "example_title"
    };
    private final String publisher[] = new String[]{
        "Северо-Запад",
        "",
        "S&S",
        "DeAGOSTINI",
        "МКЗ",
        "Syst&K",
        "S&S",
        "S&S",
        "psWorld",
        "Sert",
        "Лениздат",
        "ЛенИздат",
        "Издательство АН СССР",
        "РоссГазета",
        "Из-тво АН СССР",
        "ФОРУМ, ИНФРА-М",
        "",
        "",
        "Дрофа",
        ""
    };
    private final String place[] = new String[]{
        "СПб",
        "",
        "London",
        "М.",
        "Л.",
        "Paris",
        "London",
        "London",
        "London",
        "London",
        "Л.",
        "Л.",
        "М.",
        "М.",
        "М., Л., Минск, Киев, Харьков",
        "М.",
        "",
        "",
        "СПб.",
        ""
    };
    private final int year[] = new int[]{
        1993,
        2012,
        1993,
        2014,
        1997,
        2015,
        1997,
        1987,
        2012,
        2008,
        1965,
        1989,
        1967,
        2015,
        1969,
        2006,
        2015,
        2015,
        2009,
        2015
    };
    private final String notes[] = new String[]{
        "Фэнтези о путешествии хоббита Бильбо Бэггинса. Книга издана к столетию Толкина 1892-1992",
        "Текст Священного Писания",
        "Test book for example",
        "Серия \"100 битв, которые изменили мир. Продолжение\"",
        "for test",
        "Книга о книге",
        "1 Book of Series",
        "Сайт содержит стихотворения Толкина о приключениях Тома Бомбадила.",
        "Жизнеописание Дж.Р.Р.Толкина и анализ его литературных произведений (Сильмариллион, Хоббит или туда и обратно, Властелин Колец). К 120-летию Толкина. [Режим доступа]:https://www.worldsOfTolkien.com/index.html.",
        "Book about people of West and East",
        "Рассказы Антона Павловича Чехова",
        "Рукописи не горят. Одно из известнейших произведений Михаила Афанасьевича Булгакова.",
        "О взглядах на развитие науки будущего.",
        "В.В.Путин встретился с Б.Обамой по поводу урегулирования военных конфликтов в мире.",
        "О задачах Физики как науки и о загадках Вселенной, которые с её (Физики) помощью можно решить",
        "2-е изд., испр.; 560 с.: ил. - (Профессиональное образование)",
        "",
        "",
        "Сборник рассказов",
        "Example_book",
    };
    private final int idType[] = new int[]{
        1,
        1,
        4,
        2,
        4,
        2,
        1,
        5,
        5,
        1,
        4,
        1,
        3,
        2,
        1,
        1,
        1,
        1,
        1,
        1
    };
    private final int idSection[] = new int[]{
        1,
        1,
        9,
        1,
        9,
        1,
        1,
        1,
        1,
        1,
        11,
        11,
        3,
        2,
        3,
        3,
        3,
        2,
        11,
        9
    };
    //
    private final int invNum[] = new int[NSIZE];
    private final String bookCase[] = new String[]{
        "1",
        "4",
        "5",
        "3",
        "1",
        "1",
        "1",
        "1",
        "4",
        "1",
        "4",
        "4",
        "2",
        "4",
        "2",
        "4",
        "2",
        "4",
        "3",
        "1"
    };
    private final String bookShelf[] = new String[]{
        "2",
        "5",
        "3",
        "2",
        "2",
        "2",
        "1",
        "3",
        "3",
        "2",
        "5",
        "3",
        "3",
        "5",
        "3",
        "3",
        "1",
        "3",
        "2",
        "2"
    };
    private final String condition[] = new String[]{
        "Хорошее. Твёрдый переплёт. Порвана бумажная обложка",
        "Хорошее",
        "The best Book for Test – Good",
        "Хорошее. Весь в пыли",
        "The Best",
        "Good. The Best.",
        "Good",
        "Режим доступа: www.tbtolkin.ru",
        "Хорошее. Сайт развивается.",
        "Good.",
        "Хорошее. Твёрдый Переплёт.",
        "Хорошее.",
        "Статья из сборника научных трудов. Состояние бумаги хорошее.",
        "Новая газета, прочная бумага",
        "Твёрдый переплёт. Дополнительная бумажная обложка.",
        "Хорошее состояние",
        "Новая бумага",
        "",
        "",
        "Good",
    };
    //
    private final List<Book> boBook;
    
    public BookTableDB(){
        int i;      //for loop
        this.boBook = new ArrayList<Book>();
        for(i = 0; i < this.idBook.length; i++){
            this.idBook[i] = i + 1;
            this.invNum[i] = this.idBook[i];
        }
    }
    
    public List<Book> getBoBook(){
        return this.boBook;
    }
    
    public void formBookData(){
        int i;      //for loop
        Book b;
        for(i = 0; i < BookTableDB.NSIZE; i++){
            b = new Book(this.idBook[i], this.idType[i], this.idSection[i]);
            b.getMainData().setAuthors(this.authors[i]);
            b.getMainData().setTitle(this.title[i]);
            b.getDateline().setPublisher(this.publisher[i]);
            b.getDateline().setPlace(this.place[i]);
            b.getDateline().setYear(this.year[i]);
            b.getAdditData().setNumVolume(this.numVolume[i]);
            b.getAdditData().setNotes(this.notes[i]);
            //
            b.specifyCopyBook(this.invNum[i]);
            b.getCopyBook().setBookCase(this.bookCase[i]);
            b.getCopyBook().setBookShelf(this.bookShelf[i]);
            b.getCopyBook().setCondition(this.condition[i]);
            //
            this.getBoBook().add(b);
        }
    }
}
