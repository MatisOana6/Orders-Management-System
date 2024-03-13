package src.main.bll;

import src.main.Model.Product;
import src.main.dao.ProductDAO;
import src.main.bll.Validators.ProductValidator;
import src.main.bll.Validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The methods of this class use the ProductDAO object to invoke methods from that class.
 * The class has a list of validators and a ProductDAO object as fields.
 * The methods in ProductBLL also validate the products that have been inserted/modified using validators.
 */
public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO produsDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductValidator());
        produsDAO = new ProductDAO();
    }

    /**
     * Finds a product by ID.
     *
     * @param id the ID of the product to find
     * @return the found product
     * @throws NoSuchElementException if the product with the specified ID is not found
     */
    public Product findProdusById(int id) {
        Product produs = produsDAO.findById(id);
        if (produs == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return produs;
    }

    /**
     * Finds all products.
     *
     * @return a list of all products
     */
    public List<Product> findAll() {
        List<Product> result = produsDAO.findAll();
        return result;
    }

    /**
     * Finds a product by name.
     *
     * @param name the name of the product to find
     * @return the found product
     * @throws NoSuchElementException if the product with the specified name is not found
     */
    public Product findByName(String name) {
        Product p = produsDAO.findByName(name);
        if (p == null) {
            throw new NoSuchElementException("The product with name =" + name + " was not found!");
        }
        return p;
    }

    /**
     * Finds products by price.
     *
     * @param price the price of the products to find
     * @return a list of products with the specified price
     * @throws NoSuchElementException if no products with the specified price are found
     */
    public List<Product> findByPrice(Double price) {
        List<Product> p = produsDAO.findByPrice(price);
        if (p == null) {
            throw new NoSuchElementException("The product with price =" + price + " was not found!");
        }
        return p;
    }

    /**
     * Finds products by quantity.
     *
     * @param quantity the quantity of the products to find
     * @return a list of products with the specified quantity
     * @throws NoSuchElementException if no products with the specified quantity are found
     */
    public List<Product> findByQuantity(Integer quantity) {
        List<Product> p = produsDAO.findByQuantity(quantity);
        if (p == null) {
            throw new NoSuchElementException("The product with quantity =" + quantity + " was not found!");
        }
        return p;
    }

    /**
     * Deletes a product by name.
     *
     * @param name the name of the product to delete
     */
    public void deleteByName(String name) {
        ProductDAO dao = new ProductDAO();
        dao.deleteByName("name", name);
    }

    /**
     * Deletes a product by ID.
     *
     * @param id the ID of the product to delete
     */
    public void deleteByID(Integer id) {
        ProductDAO dao = new ProductDAO();
        dao.delete("ID", id);
    }

    /**
     * Deletes products by price.
     *
     * @param price the price of the products to delete
     */
    public void deleteByPrice(Double price) {
        ProductDAO dao = new ProductDAO();
        dao.deleteByPrice("price", price);
    }

    /**
     * Deletes products by quantity.
     *
     * @param quantity the quantity of the products to delete
     */
    public void deleteByQuantity(int quantity) {
        ProductDAO dao = new ProductDAO();
        dao.delete("quantity", quantity);
    }

    /**
     * Inserts a new product.
     *
     * @param name     the name of the product
     * @param quantity the quantity of the product
     * @param price    the price of the product
     */
    public void insert(String name, int quantity, double price) {
        Product produs = new Product(name, quantity, price);
        for (Validator<Product> v : validators) {
            v.validate(produs);
        }
        produsDAO.insert(name, quantity, price);
    }

    /**
     * Updates a product by ID.
     *
     * @param value    the value to update
     * @param name     the name of the product
     * @param quantity the quantity of the product
     * @param price    the price of the product
     */
    public void updateById(int value, String name, int quantity, double price) {
        Product p = new Product(name, quantity, price);
        for (Validator<Product> v : validators) {
            v.validate(p);
        }
        produsDAO.updateByIDOrQuantity("ID", value, name, quantity, price);
    }

    /**
     * Updates a product by quantity.
     *
     * @param value    the value to update
     * @param name     the name of the product
     * @param quantity the quantity of the product
     * @param price    the price of the product
     */
    public void updateByQuantity(int value, String name, int quantity, double price) {
        Product p = new Product(name, quantity, price);
        for (Validator<Product> v : validators) {
            v.validate(p);
        }
        produsDAO.updateByIDOrQuantity("quantity", value, name, quantity, price);
    }

    /**
     * Updates a product by name.
     *
     * @param value    the value to update
     * @param name     the name of the product
     * @param quantity the quantity of the product
     * @param price    the price of the product
     */
    public void updateByName(String value, String name, int quantity, double price) {
        Product p = new Product(name, quantity, price);
        for (Validator<Product> v : validators) {
            v.validate(p);
        }
        produsDAO.updateByName("name", value, name, quantity, price);
    }

    /**
     * Updates a product by price.
     *
     * @param value    the value to update
     * @param name     the name of the product
     * @param quantity the quantity of the product
     * @param price    the price of the product
     */
    public void updateByPrice(double value, String name, int quantity, double price) {
        Product p = new Product(name, quantity, price);
        for (Validator<Product> v : validators) {
            v.validate(p);
        }
        produsDAO.updateByPrice("price", value, name, quantity, price);
    }
}
