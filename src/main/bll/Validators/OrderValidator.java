package src.main.bll.Validators;
import src.main.Model.Order;

import javax.swing.*;

/**
 *  The class in charge of validating the objects of type Order.
 *
 */
public class OrderValidator implements Validator<Order> {

    public void validate(Order order) {
        if (order.getQuantity() <= 0) {
            JOptionPane.showMessageDialog(null, "The quantity is not valid!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Quantity is not valid");
        }
    }
}