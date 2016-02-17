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
    /**
     *Разделяет входную строку input на подстроки,
     * используя символы-разделители из шаблона regex,
     * и затем объединяет в единый текст, в котором
     * все полученные подстроки разделены символом новой строки
     * @param regex - шаблон с символами-разделителями
     * @param input - входная строка
     * @return sterr - объект класса StringBuffer,
     * преобразованный в строку с использованием метода toString() 
     */
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
    
    /**
     *Возвращает сообщение с текстом исключения
     * @param e - передаваемое исключение
     * @param nameMethod - имя метода, в котором было
     * выброшено исключение
     */
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
    
    /**
     *Возвращает сообщение mess с предупреждением
     * @param mess - сообщение с предупреждением 
     */
    public static void warningMes(String mess){
        JOptionPane.showMessageDialog(null, mess, 
                "Предупреждение (Warning): ", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     *Возвращает сообщение mess с предупреждением
     * @param mess - сообщение с предупреждением
     * @param nameMethod - имя метода, из которого было
	 * вызвано (выведено) сообщение
     */
    public static void warningMes(String mess, String nameMethod){
        JOptionPane.showMessageDialog(null, 
                "(Метод: " + nameMethod + "): \n" +
                        mess, 
                "Предупреждение (Warning): ", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     *Возвращает информационное сообщение mess
     * @param mess - строка с информационным сообщением
     */
    public static void infoMes(String mess){
        JOptionPane.showMessageDialog(null, mess, 
                "Информация (Information): ", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     *Возвращает информационное сообщение mess
     * @param mess - строка с информационным сообщением
     * @param nameMethod - имя метода, из которого было
	 * вызвано (выведено) сообщение
     */
    public static void infoMes(String mess, String nameMethod){
        JOptionPane.showMessageDialog(null, 
                "(Метод: " + nameMethod + "): \n" +
                        mess, 
                "Информация (Information): ", JOptionPane.INFORMATION_MESSAGE);
    }
}
