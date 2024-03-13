package src.main.dao;

import src.main.Connection.ConnectionFactory;
import src.main.Model.Client;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;

/**
 * This class contains the specific operations for a client (findByName, findByEmail, deleteByNameOrMail, insert, updateByID, updateByNameOrMail).
 * In addition to these methods, it also includes methods that create SELECT, INSERT, and UPDATE queries.
 */
public class ClientDAO extends AbstractDAO<Client> {

    /**
     * Retrieves a list of clients with the given name.
     *
     * @param name the name to search for
     * @return the list of clients with the given name
     */
    public List<Client> findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
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
     * Retrieves the client with the given email.
     *
     * @param email the email to search for
     * @return the client with the given email
     */
    public Client findByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("email");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
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
     * Deletes a client by name or email.
     *
     * @param field the field (name or email) to search for
     * @param value the value of the field to be deleted
     */
    public void deleteByNameOrMail(String field, String value) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(field);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates the INSERT query.
     *
     * @return the generated INSERT query
     */
    private String createInsertQuery() {
        String sb = "INSERT INTO client (name, email) VALUES (?, ?)";
        return sb;
    }

    /**
     * Inserts a new client into the database with the given name and email.
     *
     * @param name  the name of the client
     * @param email the email of the client
     * @return the inserted client
     */
    public Client insert(String name, String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
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

    /**
     * Creates the UPDATE query.
     *
     * @param field the field to be updated
     * @return the generated UPDATE query
     */
    private String createUpdateQuery(String field) {
        String sb = "UPDATE client SET name=?, email=? WHERE " + field + "=?";
        return sb;
    }

    /**
     * Updates a client by ID.
     *
     * @param field the field (ID) to search for
     * @param value the value of the field to be updated
     * @param name  the new name for the client
     * @param email the new email for the client
     * @return the updated client
     */
    public Client updateByID(String field, Integer value, String name, String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(field);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, value);
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

    /**
     * Updates a client by name or email.
     *
     * @param field the field (name or email) to search for
     * @param value the value of the field to be updated
     * @param name  the new name for the client
     * @param email the new email for the client
     * @return the updated client
     */
    public Client updateByNameOrEmail(String field, String value, String name, String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(field);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, value);
            //update the records based on the specified field, value, and the new name and email values.
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
