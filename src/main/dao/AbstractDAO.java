package src.main.dao;

import src.main.Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aceasta clasa contine operatiile de baza pentru fiecare tabel (findById, findAll, delete).
 * Pe lângă aceste metode, se regăsesc și metodele care creează interogările de SELECT și DELETE.
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    public final Class<T> type;

    public Class<T> getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append("`" + type.getSimpleName().toLowerCase() + "`");
        sb.append(" WHERE `" + field + "` = ?");
        return sb.toString();
    }


    public String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("`" + type.getSimpleName().toLowerCase() + "`");
        return sb.toString();
    }

    public String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append("`" + type.getSimpleName().toLowerCase() + "`");
        sb.append(" WHERE `" + field + "` = ?");
        return sb.toString();
    }


    /**
     *Aceasta metoda returneaza o lista de obiecte ce contine toate rândurile unui tabel.
     * Prima dată se realizează conexiunea la baza de date, se creează un statement cu textul interogării,
     * și dacă rezultatul nu e null, acesta se returnează
     */
    public List<T> findAll(){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
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
     Această metodă returnează obiectul din baza de date cu ID-ul primit ca și argument.
     Prima dată se realizează conexiunea la baza de date, se creează un statement cu textul interogării,
     și dacă rezultatul nu e null, acesta se returnează.
     */

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // get id field name
        String idField = "";
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
            List<T> lst = createObjects(resultSet);
            if (lst.isEmpty()) {
                // Handle the case when the result set is empty
                return null; // or throw an exception
            }
           // System.out.println("size:::" + lst.size());
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
     *
     Acesastă metodă creează lista de rezultat, conținând elemente de tipul obiectului care se află în resultSet.
     */

    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(String field, int value) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(field);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, value);// ensures that the value parameter is properly set in the prepared statement,
            // allowing for the correct deletion of records based on the specified field and value.
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}