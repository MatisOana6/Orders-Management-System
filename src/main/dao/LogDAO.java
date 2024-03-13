package src.main.dao;

import src.main.Connection.ConnectionFactory;
import src.main.Model.Bill;
import src.main.Model.Order;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * This class contains the basic operations specific to a log (findByClientID, findByProductID, updateByID, insert).
 * In addition to these methods, it also includes methods that create SELECT, INSERT, and UPDATE queries.
 */

public class LogDAO<T> extends AbstractDAO<Bill> {
    private LogDAO logDAO;
    private OrderDAO orderDAO;
    private int lastProcessedOrderID;

    public LogDAO() {
        orderDAO = new OrderDAO();
        logDAO = this;
        lastProcessedOrderID = 0; // Initialize with a default value
    }
    /**
     * Generates bills for orders and inserts them into the log table.
     */
    public void generateBillsForOrders() {
        // Retrieve all orders from the table
        List<Order> orders = orderDAO.findAll();

        // Iterate over the orders
        for (Order order : orders) {
            if (order.getId() > lastProcessedOrderID) {
                // Calculate total price
                // Create a Bill object
                Bill bill = new Bill(order.getIdClient(), order.getIdProduct(), order.getQuantity(), order.getPrice());

                // Insert the bill into the Log table
                logDAO.insertBill(bill.idClient(), bill.idProduct(), bill.quantity(), bill.price());

                // Update the last processed order ID
                lastProcessedOrderID = order.getId();
            }
        }
    }

    /**
     * Retrieves all the bills from the log table.
     *
     * @return the list of all bills
     */
    public List<Bill> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Create a list to hold the bills
            List<Bill> bills = new ArrayList<>();

            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve the necessary data from the result set
                int idClient = resultSet.getInt("idClient");
                int idProduct = resultSet.getInt("idProduct");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                // Create a Bill object
                Bill bill = new Bill(idClient, idProduct, quantity, price);

                // Add the bill to the list only if it corresponds to a new order
                if (idProduct > lastProcessedOrderID) {
                    bills.add(bill);
                }
            }

            // Update the last processed order ID

            // Generate bills for the retrieved orders
            generateBillsForOrders();

            // Return the list of bills
            return bills;
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + throwables.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Finds a bill by its ID.
     *
     * @param id the ID of the bill to find
     * @return the found bill or null if not found
     */
    public Bill findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // Get the ID field name
        String idField = new String();
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().contains("id")) {
                idField = field.getName();
                break;
            }
        }
        String query = createSelectQuery(idField);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<Bill> lst = createObjects(resultSet);
            if (resultSet == null || lst == null) {
                System.out.println("e null!!!!!");
            }
            System.out.println("size:::" + lst.size());
            return lst.get(0);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());

        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates a list of Bill objects based on the provided ResultSet.
     *
     * @param resultSet the ResultSet containing the data
     * @return the list of Bill objects
     */
    public List<Bill> createObjects(ResultSet resultSet) {
        List<Bill> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idClient = resultSet.getInt("idClient");
                int idProduct = resultSet.getInt("idProduct");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                Bill instance = new Bill(idClient, idProduct, quantity, price);
                list.add(instance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Finds bills by the client ID.
     *
     * @param id the client ID
     * @return the list of bills for the client ID
     */
    public List<Bill> findByClientID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("idClient");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, getType().getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Finds bills by the product ID.
     *
     * @param id the product ID
     * @return the list of bills for the product ID
     */
    public List<Bill> findByProductID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("idProduct");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, getType().getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createInsertQuery() {
        String sb = "INSERT INTO ordersdb.bill(idClient, idProduct,quantity, price) VALUES (?,?,?,?)";
        return sb;
    }

    /**
     * Inserts a bill into the log table.
     *
     * @param idClient   the client ID
     * @param idProduct  the product ID
     * @param quantity   the quantity
     * @param price      the price
     * @return the inserted bill or null if unsuccessful
     */
    public Bill insertBill(Integer idClient, Integer idProduct, Integer quantity, Double price) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idClient);
            statement.setInt(2, idProduct);
            statement.setInt(3, quantity);
            statement.setDouble(4, price);
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}
