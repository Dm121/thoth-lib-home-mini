/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.inout;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.inout.OutputDoc;
import thoth_lib_m.dataclass.CopyTable;

/**
 *Формирование и создание HTML-документа со списком книг
 * @author Sirota Dmitry
 */
public class HTMLDoc extends DocumentContent{
    
    //Ширина столбцов таблицы с данными библиотечных изданий
    //Элементы массива доступны из метода: 
    // private String getWidthColumns(){...}
    private String[] widthColumns = { 
        "30%", 
        "40%", 
        "10%", 
        "10%", 
        "10%" 
    };  
      
    //Конструктор по умолчанию
    public HTMLDoc(){
        super();
    }
    
    //
    private StringBuffer cssDoc(){
        StringBuffer strCSS = new StringBuffer();
        //
        strCSS.append("<style>\n");
	strCSS.append(".t{\n");
	strCSS.append("margin-top: 20.9px;\n");
	strCSS.append("}\n");
	strCSS.append("th{\n");
	strCSS.append("font-size: 12pt;\n");
	strCSS.append("text-align: center;\n");
        strCSS.append("}\n");
        strCSS.append("td{\n");
	strCSS.append("font-size: 12pt;\n");
	strCSS.append("text-align: left;\n");
	strCSS.append("word-break: break-all;\n");
	strCSS.append("}\n");
	strCSS.append(".num{\n");
	strCSS.append("font-size: 14pt;\n");
	strCSS.append("text-align: center;\n");
	strCSS.append("font-weight: bold;\n");
	strCSS.append("}\n");
	strCSS.append("</style>\n");
	strCSS.append("<link rel=\"stylesheet\" type=\"text/css\" " + 
            "href=\"print.css\" media=\"print\" />\n");
        //
        return strCSS;
    }
    
    //
    private StringBuffer jsDoc(){
        StringBuffer strJS = new StringBuffer();
        //
        strJS.append("<script type=\"text/javascript\">\n");
        strJS.append("function print_(){\n");
	strJS.append("window.print();\n");
	strJS.append("}\n");
	strJS.append("</script>\n");
        //
        return strJS;
    }
    
    //
    private String topButton(){
        StringBuffer strTopBut = new StringBuffer();
        //
        strTopBut.append("<form name=\"formTop\">\n");
	strTopBut.append("<input type=\"button\" ");
        strTopBut.append("name=\"butTop\" ");
        strTopBut.append("value=\"Печать\" ");
        strTopBut.append("class=\"but\" ");
        strTopBut.append("onclick=\"print_();\"></input>\n");
        strTopBut.append("</form>\n");
        //
        return strTopBut.toString();
    }
    
    //
    private String bottomButton(){
        StringBuffer strBottomBut = new StringBuffer();
        //
        strBottomBut.append("<form name=\"formBottom\">\n");
	strBottomBut.append("<input type=\"button\" ");
        strBottomBut.append("name=\"butBottom\" ");
        strBottomBut.append("value=\"Печать\" ");
        strBottomBut.append("class=\"but\" ");
        strBottomBut.append("onclick=\"print_();\"></input>\n");
	strBottomBut.append("</form>\n");
        //
        return strBottomBut.toString();
    }
    
    //
    private String captionTable(String nameSection){
        String captionT = "<caption>" + nameSection + "</caption>\n";
        return captionT;
    }
    
    //
    private String[] getWidthColumns(){
        return this.widthColumns;
    }
    
    //
    private StringBuffer headerTable(){
        StringBuffer strHeaderT = new StringBuffer();
        //
        String[] nameColumns = {
            "Авторы",
            "Название",
            "Год изд.",
            "Шкаф",
            "Полка"
        };
         
        //
        strHeaderT.append("<tr>\n");
        //
	strHeaderT.append("<th width=\"");
        strHeaderT.append(this.getWidthColumns()[0]);
        strHeaderT.append("\">");
        strHeaderT.append(nameColumns[0]);
        strHeaderT.append("</th>\n");
        //
        strHeaderT.append("<th width=\"");
        strHeaderT.append(this.getWidthColumns()[1]);
        strHeaderT.append("\">");
        strHeaderT.append(nameColumns[1]);
        strHeaderT.append("</th>\n");
        //
        strHeaderT.append("<th width=\"");
        strHeaderT.append(this.getWidthColumns()[2]);
        strHeaderT.append("\">");
        strHeaderT.append(nameColumns[2]);
        strHeaderT.append("</th>\n");
        //
        strHeaderT.append("<th width=\"");
        strHeaderT.append(this.getWidthColumns()[3]);
        strHeaderT.append("\">");
        strHeaderT.append(nameColumns[3]);
        strHeaderT.append("</th>\n");
        //
        strHeaderT.append("<th width=\"");
        strHeaderT.append(this.getWidthColumns()[4]);
        strHeaderT.append("\">");
        strHeaderT.append(nameColumns[4]);
        strHeaderT.append("</th>\n");
        //
	strHeaderT.append("</tr>\n");
        //
        return strHeaderT;
    }
    
    //
    private StringBuffer dataTable(int j, int countBooksOnPage, 
                                                    List<CopyTable> lC){
        StringBuffer dataT = new StringBuffer();
        int i;          //for loop
        //
        for(i = 0; i < countBooksOnPage; i++){
            dataT.append("<tr>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[0]);
            dataT.append("\">");
            dataT.append(lC.get(j + i).getAuthorsTable());
            dataT.append("</td>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[1]);
            dataT.append("\">");
            dataT.append(lC.get(j + i).getTitleTable());
            dataT.append("</td>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[2]);
            dataT.append("\">");
            dataT.append(lC.get(j + i).getYearTable());
            dataT.append("</td>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[3]);
            dataT.append("\">");
            dataT.append(lC.get(j + i).getBookCaseTable());
            dataT.append("</td>\n");
            //
            dataT.append("<td width=\"");
            dataT.append(this.getWidthColumns()[4]);
            dataT.append("\">");
            dataT.append(lC.get(j + i).getBookShelfTable());
            dataT.append("</td>\n");
            //
            dataT.append("</tr>\n");
        }        
        //
        return dataT;
    }
    
    //
    private String footerPage(int numPage){
        StringBuffer cellNum = new StringBuffer();
        //
        cellNum.append("<td class=\"num\" height=\"21px\">\n");
        cellNum.append("page: ");
        cellNum.append(numPage);
        cellNum.append("\n</td>\n");
        //
        return cellNum.toString();
    }
    
    //
    private StringBuffer pagesTableHTML(String nameSection, 
                                        List<CopyTable> lC){
        //
        StringBuffer strPage = new StringBuffer();
        //
        int j;
        final int countBooksOnPage = 25;
        int numPage = 1;
        //
        for(j = 0; j < lC.size(); j = j + countBooksOnPage){
            //
            strPage.append("<table border=\"0px\" ");
            strPage.append("width=\"793px\" ");
            if(j > 0){
                strPage.append("class=\"t\" ");
            }
            strPage.append("cellpadding=\"10\" ");
            strPage.append("cellspacing=\"0\">\n");
            strPage.append("<tr>\n");
            strPage.append("<td height=\"1090,519px\" valign=\"top\">\n");
            strPage.append("<table border=\"1px\" ");
            strPage.append("width=\"100%\" ");
            strPage.append("cellpadding=\"3\" ");
            strPage.append("cellspacing=\"0\">\n");    
            strPage.append(this.captionTable(nameSection));
            strPage.append(this.headerTable());
            strPage.append(this.dataTable(j, countBooksOnPage, lC));
            strPage.append("</table>\n");
            strPage.append("</td>\n");
            strPage.append("</tr>\n");
            strPage.append("<tr>\n");
            strPage.append(this.footerPage(numPage));
            //
            numPage++;
            //
            strPage.append("</tr>\n");
            strPage.append("</table>\n");
            //
        }
        //
        return strPage;
    }
    
    //
    private void textHTMLDoc(String nameSection, List<CopyTable> lC){
        this.clearTextDoc();
        //
        this.setTextDoc("<!DOCTYPE html>\n");
        this.setTextDoc("<html>\n");
        this.setTextDoc("<head>\n");
        this.setTextDoc("<meta charset=\"utf-8\">\n");
	this.setTextDoc("<title>Example_print</title>\n");
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
    }
    
    //
    @Override
    public void outputData(String pathToFile){
        
    }
    
    //
    public boolean outputHTML(String nameSection, List<CopyTable> lC,
                    String pathToFile){
        boolean flag = false;
        //
        try{
            this.textHTMLDoc(nameSection, lC);
            this.outputData(nameSection);
            flag = true;
        }
        catch(Exception e){
            AdditClass.errorMes(e, "HTMLDoc.outputHTML(...)");
        }
        //
        return flag;
    }
}
