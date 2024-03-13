package src.main.dao;

import src.main.Connection.ConnectionFactory;
import src.main.Model.Client;
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
 * această clasă conține operațiile de baza specifice unei comenzi (findByClientID, findByProdusID, updateByID, insert).
 * Pe lângă aceste metode, se regăsesc și metodele care creează interogările de SELECT, INSERT si UPDATE.
 */

public class OrderDAO extends AbstractDAO<Order>{

    /**
     * Metoda returnează comanda care are id-ul de client ca și id-ul primit ca parametru.
     */

    public List<Order> findByClientID(Integer id) {
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

    public List<Order> findByProductID(Integer id) {
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

    private String createUpdateQuery(String field) {
        String sb = "UPDATE ordersdb.order SET idClient=?, idProduct=?, price=?, quantity=? WHERE " + field + "=?";
        return sb;
    }

    public Order updateByID(String field, Integer value, Integer idClient, Integer idProdus, Double price, Integer quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(field);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idClient);
            statement.setInt(2, idProdus);
            statement.setDouble(3, price);
            statement.setInt(4, quantity);
            statement.setInt(5, value);
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

    private String createInsertQuery() {
        String sb = "INSERT INTO ordersdb.order(idClient, idProduct, price, quantity) VALUES (?,?,?,?)" ;
        return sb;
    }

    public Order insertOrder(Integer idClient, Integer idProdus, Integer quantity, Double price) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idClient);
            statement.setInt(2, idProdus);
            statement.setDouble(3, price);
            statement.setInt(4, quantity);
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