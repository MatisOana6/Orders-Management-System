package src.main.bll;

import src.main.Model.Order;
import src.main.Model.Product;
import src.main.bll.Validators.OrderValidator;
import src.main.dao.OrderDAO;
import src.main.bll.Validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private List<Validator<Order>> validators;
    private OrderDAO orderDAO;

    public OrderBLL() {
        validators = new ArrayList<>();
        validators.add(new OrderValidator());
        orderDAO = new OrderDAO();
    }

    /**
     * Finds an order by ID.
     *
     * @param id the ID of the order to find
     * @return the found order
     * @throws NoSuchElementException if the order with the specified ID is not found
     */
    public Order findOrderById(int id) {
        Order order = orderDAO.findById(id);
        if (order == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return order;
    }

    /**
     * Finds all orders.
     *
     * @return a list of all orders
     */
    public List<Order> findAll() {
        OrderDAO dao = new OrderDAO();
        List<Order> result = orderDAO.findAll();
        return result;
    }

    /**
     * Finds orders by client ID.
     *
     * @param id the ID of the client
     * @return a list of orders associated with the specified client ID
     * @throws NoSuchElementException if no orders with the specified client ID are found
     */
    public List<Order> findOrderByClientId(int id) {
        List<Order> result = new ArrayList<>();
        result = orderDAO.findByClientID(id);
        if (result == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return result;
    }

    /**
     * Finds orders by product ID.
     *
     * @param id the ID of the product
     * @return a list of orders associated with the specified product ID
     * @throws NoSuchElementException if no orders with the specified product ID are found
     */
    public List<Order> findOrderByProductId(int id) {
        List<Order> result = new ArrayList<>();
        result = orderDAO.findByProductID(id);
        if (result == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return result;
    }

    /**
     * Deletes an order by ID.
     *
     * @param id the ID of the order to delete
     */
    public void delete(int id) {
        OrderDAO dao = new OrderDAO();
        dao.delete("id", id);
    }

    /**
     * Deletes orders by client ID.
     *
     * @param id the ID of the client
     */
    public void deleteByClientID(int id) {
        OrderDAO dao = new OrderDAO();
        dao.delete("idClient", id);
    }

    /**
     * Updates an order by ID.
     *
     * @param value    the value to match the ID
     * @param idClient the ID of the client
     * @param idProdus the ID of the product
     * @param price    the price of the order
     * @param quantity the quantity of the order
     */
    public void updateByID(Integer value, Integer idClient, Integer idProdus, Double price, Integer quantity) {
        Order o = new Order(idClient, idProdus, price, quantity);
        for (Validator<Order> v : validators) {
            v.validate(o);
        }
        orderDAO.updateByID("id", value, idClient, idProdus, price, quantity);
    }

    /**
     * Inserts an order.
     *
     * @param idClient  the ID of the client
     * @param idProduct the ID of the product
     * @param quantity  the quantity of the order
     * @return true if the order was inserted successfully, false otherwise
     */
    public boolean insertOrder(Integer idClient, Integer idProduct, Integer quantity) {
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
            Order o = new Order(idClient, idProduct, quantity);
            for (Validator<Order> v : validators) {
                v.validate(o);
            }
            orderDAO.insertOrder(idClient, idProduct, quantity, price);

            return true;
        }
    }
}
