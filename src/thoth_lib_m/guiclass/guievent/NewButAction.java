/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import thoth_lib_m.guiclass.CatalogJElements;
/**
 *Событие для кнопки "Создать новую запись"
 * @author Sirota Dmitry
 */
public class NewButAction implements ActionListener{
    
    CatalogJElements elem;
    JTable table;
    
    public NewButAction(CatalogJElements elem, JTable table){
        this.elem = elem;
        this.table = table;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
                elem.setValIdB("");
                elem.setValTypeEdition(0);
                elem.getTextBook().get(0).setText("");
                elem.getTextBook().get(1).setText("");
                elem.getTextBook().get(2).setText("");
                elem.getTextBook().get(3).setText("");
                elem.getTextBook().get(4).setText("");
                elem.setValYearValue(2015);
                elem.getTextCopy().get(0).setText("");
                elem.getTextCopy().get(1).setText("");
                elem.getTextArray().get(0).setText("");
                elem.getTextArray().get(1).setText("");
                table.clearSelection();
    }
}
