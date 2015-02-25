/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m;

import java.util.Arrays;

/**
 *Вспомогательные методы
 * @author Sirota Dmitry
 */
public class AdditClass {
    public static String splitString(String regex, String input){
        int i;
        String[] st = input.split(regex);
        StringBuffer sterr = new StringBuffer();
        for(i = 0; i < st.length; i++){
            sterr.append(st[i]);
            sterr.append("\n");
        }
        return sterr.toString();
    }
}
