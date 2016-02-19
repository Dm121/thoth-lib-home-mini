/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass.guievent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import thoth_lib_m.AdditClass;
import thoth_lib_m.guiclass.*;
import thoth_lib_m.databaseclass.ChangeSectionUpdate;

/**
 *Событие для кнопки "Изменить раздел"
 * @author Sirota Dmitry
 */
public class ChangeSecButAction implements ActionListener{
    
    private final CatalogJFrame frame;
    //private int idSelectBook;
    //private Connection c;
    //private final Section s;
    
    public ChangeSecButAction(CatalogJFrame frame){
        super();
        //this.s = s;
        //this.c = c;
        this.frame = frame;
        //this.idSelectBook = idSelectBook;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        int idSelectBook = this.frame.getTable().getSortTable().getIdBookRecord(
                        this.frame.getTable().getCopyTable().getSelectedRow());
        //
        ChangeSectionUpdate chsupd;
        DelDataTable ddt = new DelDataTable(this.frame);
        SelectionSectionFrame selSecFrame = 
                                        new SelectionSectionFrame(this.frame);
        selSecFrame.createGUI();
        if(selSecFrame.getResultDialog() && 
                (selSecFrame.getIdSelectedSection() > 0)){
            try{
                chsupd = new ChangeSectionUpdate();
                if(chsupd.changeSection(idSelectBook, 
                                        selSecFrame.getIdSelectedSection())){
                    ddt.deleteDataT();
                }
            }
            catch(SQLException err){
                AdditClass.errorMes(err, "ChangeSecButAction.actionPerformed");
            }
        }
        else{ AdditClass.infoMes("Была нажата кнопка \"Cancel\""); }
    }
}
