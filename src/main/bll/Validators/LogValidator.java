package src.main.bll.Validators;

import src.main.Model.Bill;
import src.main.Model.Order;

import javax.swing.*;

public class LogValidator implements Validator<Bill>{
    public void validate(Bill bill) {
        if (bill.quantity() <= 0) {
            JOptionPane.showMessageDialog(null, "The quantity is not valid!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Quantity is not valid");
        }
    }
}
