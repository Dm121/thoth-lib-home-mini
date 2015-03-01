/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thoth_lib_m.guiclass;

import java.awt.Color;
      import java.awt.Component;
      import javax.swing.JLabel;
      import javax.swing.JTable;
      import javax.swing.border.LineBorder;
      import javax.swing.table.TableCellRenderer;


/**
 *Рисовальщик для столбцов, содержащих текстовые данные
 * @author Subbotin B.P., Sirota Dmitry
 * @see http://www.sbp-program.ru
 */
public class StringRenderer extends JLabel
        implements TableCellRenderer{
    
    private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table,
               Object value, boolean isSelected, boolean hasFocus, 
                                                int row, int column)
        {
            setFont(table.getFont());
            setOpaque(true);
            if(isSelected)
            {
                setBackground(new Color(184,207,229));
            }
            else
            {
                setBackground(Color.white);
            }

            if (hasFocus) 
            {
                setBorder(new LineBorder(new Color(99, 130, 191)));
            }
            else
            {
            setBorder(new LineBorder(null, 0));
            }
            if((column == 3) || (column == 4))
            {
                setHorizontalAlignment(JLabel.RIGHT);	
            }
            else
            {
                setHorizontalAlignment(JLabel.LEFT);
          } 
          setText((value == null) ? "" : " "+value);
          return this;
        }
}
