package logicsim;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.Dimension;
import java.awt.Toolkit;


/* this code prints out the truth table in the proper table format
from the arraylist containing the bool values
originally created for logicsim
Written By: Bodhisattya Dutta
*/

public class TruthTeller{

  JFrame f=new JFrame();

   void tellTruth(Map<String, ArrayList<Boolean> > ttmap){

     int total_cols = ttmap.size(), total_rows = 0;
    String[] columnNames = new String[total_cols];
    System.out.println("tt size: "+total_cols );
    int i = -1, comb_num = 0;
    for (Map.Entry<String, ArrayList<Boolean> > entry : ttmap.entrySet()){
     columnNames[++i] = entry.getKey();
     total_rows = entry.getValue().size();
   }

   Object[][] col_data = new Object[total_rows][total_cols];
   String[] col_arr = new String[total_cols];

   for(comb_num = 0 ; comb_num < total_rows; comb_num++){
     i = 0;
     for (Map.Entry<String, ArrayList<Boolean> > entry : ttmap.entrySet()){
       if(entry.getValue().get(comb_num) == true){
       col_data[comb_num][i] = "1";
     }
       else{
          col_data[comb_num][i] = "0";
        }
        i++;
     }
   }

   for( i = 0; i < columnNames.length / 2; i++){
       String temp = columnNames[i];
       columnNames[i] = columnNames[columnNames.length - i - 1];
       columnNames[columnNames.length - i - 1] = temp;
      }
     for(i = 0; i < col_data.length; i++){
       for(int q = 0; q < col_data[i].length / 2; q++){
         Object temp = col_data[i][q];
         col_data[i][q] = col_data[i][col_data[i].length - q - 1];
         col_data[i][col_data[i].length - q - 1] = temp;
        }
      }
   JTable table = new JTable(col_data, columnNames);
   DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    table.setDefaultRenderer(String.class, centerRenderer);
   table.setBounds(30,40,200,300);
    JScrollPane sp=new JScrollPane(table);
    f.add(sp);
    f.setSize(total_cols*100, total_rows*30);
    f.setVisible(true);
    for(int x=0;x<total_cols;x++){
         table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);

 } // tellTruth ends
} //class ends
