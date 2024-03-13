package src.main.Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

public class Reflection {

    /**
     * This method displays the rows of data from a list in a JTable using reflection.
     * The list parameter contains the data to be displayed.
     *
     * @param list The list containing the data to be displayed.
     * @param <T>  The type of the data in the list.
     */
    public static <T> void getRows(List<T> list) {

        if (list == null) {
            System.out.println("List is null");
            return;
        }

        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        Boolean firstIteration = true;
        for (Object object : list) {
            String data[] = new String[100];
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(object);
                    if (firstIteration == true)
                        model.addColumn(field.getName());
                    data[i] = value.toString();
                    i++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(data);
            i = 0;
            firstIteration = false;
        }
        JTable j = new JTable(model);
        JScrollPane sp = new JScrollPane(j);
        j.setBackground(new Color(255, 240, 245));
        JDialog d = new JDialog();
        d.add(sp);
        d.setSize(600, 400);
        d.setVisible(true);
    }

}
