package src.main.bll;

import src.main.Model.Bill;
import src.main.Model.Product;
import src.main.bll.Validators.LogValidator;
import src.main.bll.Validators.Validator;
import src.main.dao.LogDAO;
import src.main.dao.OrderDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LogBLL {
    private List<Validator<Bill>> validators;
    private LogDAO logDAO;
    private OrderDAO orderDAO;

    public LogBLL() {
        validators = new ArrayList<Validator<Bill>>();
        validators.add(new LogValidator());
        logDAO = new LogDAO();
    }

    /**
     * Finds all bills.
     *
     * @return a list of all bills
     */
    public List<Bill> findAll() {
        //LogDAO dao = new LogDAO();
        List<Bill> result = logDAO.findAll();
        return result;
    }

    /**
     * Finds a bill by ID.
     *
     * @param id the ID of the bill to find
     * @return the found bill
     * @throws NoSuchElementException if the bill with the specified ID is not found
     */
    public Bill findBillById(int id) {
        Bill bill = logDAO.findById(id);
        if (bill == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return bill;
    }

    /**
     * Inserts a new bill.
     *
     * @param idClient   the ID of the client
     * @param idProduct  the ID of the product
     * @param quantity   the quantity of the product
     * @return true if the bill is successfully inserted, false otherwise
     */
    public boolean insertBill(int idClient, int idProduct, int quantity) {
        ProductBLL bllProduct = new ProductBLL();
        Product p = bllProduct.findProdusById(idProduct);
        if (quantity > p.getQuantity()) {
            return false;
        } else {
            Double price = p.getPrice() * quantity;
            Integer newQuantity = p.getQuantity() - quantity;
            if (newQuantity > 0) {
                bllProduct.updateById(p.getId(), p.getName(), newQuantity, p.getPrice());
            }
            if (newQuantity == 0) {
                bllProduct.deleteByID(p.getId());
            }
            Bill o = new Bill(idClient, idProduct, quantity, price);
            for (Validator<Bill> v : validators) {
                v.validate(o);
            }
            logDAO.insertBill(idClient, idProduct, quantity, price);
            return true;
        }
    }

    /**
     * Finds bills by client ID.
     *
     * @param id the ID of the client
     * @return a list of bills associated with the specified client ID
     * @throws NoSuchElementException if no bills with the specified client ID are found
     */
    public List<Bill> findBillByClientId(int id) {
        List<Bill> result = new ArrayList<>();
        result = logDAO.findByClientID(id);
        if (result == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return result;
    }

    /**
     * Finds bills by product ID.
     *
     * @param id the ID of the product
     * @return a list of bills associated with the specified product ID
     * @throws NoSuchElementException if no bills with the specified product ID are found
     */
    public List<Bill> findBillByProductId(int id) {
        List<Bill> result = new ArrayList<>();
        result = logDAO.findByProductID(id);
        if (result == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return result;
    }
}
