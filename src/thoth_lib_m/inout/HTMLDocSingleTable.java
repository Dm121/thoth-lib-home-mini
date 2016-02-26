/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.inout;

import java.util.List;
import thoth_lib_m.dataclass.CopyTable;

/**
 *Формирование и создание HTML-документа со списком изданий,
 * размещённых в одной таблице, а не в нескольких постранично
 * @author Sirota Dmitry
 */
public class HTMLDocSingleTable extends HTMLDoc{
    
    //Конструктор по умолчанию
    public HTMLDocSingleTable(){
        super();
    }
    
    //CSS-стили для HTML-документа
    @Override
    protected StringBuffer cssDoc(){
        StringBuffer strCSS = new StringBuffer();
        //
        strCSS.append("<style>\n");
	strCSS.append("th{\n");
	strCSS.append("font-size: 12pt;\n");
	strCSS.append("text-align: center;\n");
        strCSS.append("}\n");
        strCSS.append("td{\n");
	strCSS.append("font-size: 12pt;\n");
	strCSS.append("text-align: left;\n");
	strCSS.append("word-break: break-all;\n");
	strCSS.append("}\n");
	strCSS.append("</style>\n");
	strCSS.append("<link rel=\"stylesheet\" type=\"text/css\" " + 
            "href=\"print.css\" media=\"print\" />\n");
        //
        return strCSS;
    }
    
    //Формирование строк таблицы с данными библиотечных изданий
    private StringBuffer dataTable(List<CopyTable> lC){
        StringBuffer dataT = new StringBuffer();
        int i;          //for loop
        //
        for(i = 0; i < lC.size(); i++){
            dataT.append("<tr>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[0]);
            dataT.append("\">");
            dataT.append(lC.get(i).getAuthorsTable());
            dataT.append("</td>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[1]);
            dataT.append("\">");
            dataT.append(lC.get(i).getTitleTable());
            dataT.append("</td>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[2]);
            dataT.append("\">");
            dataT.append(lC.get(i).getYearTable());
            dataT.append("</td>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[3]);
            dataT.append("\">");
            dataT.append(lC.get(i).getBookCaseTable());
            dataT.append("</td>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[4]);
            dataT.append("\">");
            dataT.append(lC.get(i).getBookShelfTable());
            dataT.append("</td>\n");
            //
            dataT.append("</tr>\n");
        }        
        //
        return dataT;
    }
    
    //Формирование страниц с единой таблицей (с данными библиотечных изданий)
    @Override
    protected StringBuffer pagesTableHTML(String nameSection, 
                                        List<CopyTable> lC){
        //
        StringBuffer strPage = new StringBuffer();
        //
        strPage.append("<table border=\"1px\" ");
        strPage.append("width=\"793px\" ");
        strPage.append("cellpadding=\"3\" ");
        strPage.append("cellspacing=\"0\">\n");
        strPage.append(this.captionTable(nameSection));
        strPage.append(this.headerTable());
        strPage.append(this.dataTable(lC));
        strPage.append("</table>\n");
        //
        return strPage;
    }
    
    //Формирование HTML-документа,
    // метод возвращает содержимое формируемого HTML-документа
    // в виде строки (свойство this.getTextDoc())
    @Override
    protected String textHTMLDoc(String nameSection, List<CopyTable> lC){
        this.clearTextDoc();
        //
        this.setTextDoc("<!DOCTYPE html>\n");
        this.setTextDoc("<html>\n");
        this.setTextDoc("<head>\n");
        this.setTextDoc("<meta http-equiv=\"content-type\" ");
        this.setTextDoc("content=\"text/html\" charset=\"utf-8\">\n");
	this.setTextDoc("<title>Thoth_lib_m_");
        this.setTextDoc(nameSection);
        this.setTextDoc("_print</title>\n");
        this.setTextDoc(this.cssDoc());
        this.setTextDoc(this.jsDoc());
        this.setTextDoc("</head>\n");
	this.setTextDoc("<body>\n");
        this.setTextDoc(this.topButton());
        this.setTextDoc(this.pagesTableHTML(nameSection, lC));
        this.setTextDoc(this.bottomButton());
        this.setTextDoc("</body>\n");
        this.setTextDoc("</html>");
        //
        return this.getTextDoc();
    }
    
}
