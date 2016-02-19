/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.util.List;
import java.util.ArrayList;
import thoth_lib_m.dataclass.InfoSection;

/**
 *Класс для подготовки к тестированию. Необходим для заполнения данными
 * соответствующей таблицы Базы Данных (БД) section перед тестированием
 * @author Sirota Dmitry
 */
public class SectionTableDB {
    
    private final static int NSIZE = 7;
    //
    private final int idSection[] = new int[]{
        1,
        2,
        3,
        9,
        10,
        11,
        12,
    };
    private final String describe[] = new String[]{
        "Библиотека",
        "Российская пресса и литература",
        "Наука XXI",
        "Example_Section",
        "Новый раздел",
        "Классика и фантастика",
        "Книги о Санкт-Петербурге"
    };
    private final int aboveSection[] = new int[NSIZE];
    //
    private List<InfoSection> section;
    
    public SectionTableDB(){
        int i;      //for loop
        this.aboveSection[0] = 0;
        for(i = 1; i < SectionTableDB.NSIZE; i++){
            this.aboveSection[i] = 1;
        }
        this.section = new ArrayList<InfoSection>();
    }
    
    public List<InfoSection> getSection(){
        return this.section;
    }
    
    public void formSectionData(){
        int i;      //for loop
        InfoSection iS;
        for(i = 0; i < SectionTableDB.NSIZE; i++){
            iS = new InfoSection(this.idSection[0],
                                    this.describe[0],
                                    this.aboveSection[0]);
            this.getSection().add(iS);
        }
    }
}
