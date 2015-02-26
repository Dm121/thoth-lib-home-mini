/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m;

import java.util.Arrays;
import javax.swing.JOptionPane;

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
    
    public static void errorMes(Exception e, String nameMethod){
        String regex = ",";
        JOptionPane.showMessageDialog(null, 
                "Ошибка: (Метод: " + nameMethod + "): \n" +
                        e.getClass().getName() + ": " 
                        + e.getMessage() + "\n" +
                        AdditClass.splitString(regex, 
                                Arrays.toString(e.getStackTrace())), 
                "Ошибка (Error): ", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void warningMes(String mess, String nameMethod){
        JOptionPane.showMessageDialog(null, 
                "(Метод: " + nameMethod + "): \n" +
                        mess, 
                "Предупреждение (Warning): ", JOptionPane.WARNING_MESSAGE);
    }
}
