package src.main.bll.Validators;

import src.main.Model.Product;

import javax.swing.*;

/**
 *  The class in charge of validating the objects of type Product.
 *
 */

public class ProductValidator implements Validator<Product> {

    public ProductValidator(){}

    public void validate(Product p) {
        if (p.getQuantity() <= 0) {
            JOptionPane.showMessageDialog(null, "The quantity is not valid!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Quantity is not valid");
        }
    }
}