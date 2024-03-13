package src.main.bll;

import src.main.dao.ClientDAO;
import src.main.Model.Client;
import src.main.bll.Validators.ClientValidator;
import src.main.bll.Validators.Validator;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Metodele acestei clase se folosesc de obiectul ClientDAO prin care se apeleaza metodele din clasa respectiva.
 * Clasa are ca și câmpuri o listă de validatori, și un obiect de ClientDAO.
 * Clasa are metodele menționate și în clientDAO. În plus, metodele din ClientBLL verifică cu validatorii
 * clienții care au fost inserați/modificați.
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new ClientValidator());

        clientDAO = new ClientDAO();
    }

    /**
     * Finds a client by ID.
     *
     * @param id the ID of the client to find
     * @return the found client
     * @throws NoSuchElementException if the client with the specified ID is not found
     */
    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }

    /**
     * Finds all clients.
     *
     * @return a list of all clients
     */
    public List<Client> findAll() {
        List<Client> result = clientDAO.findAll();
        return result;
    }

    /**
     * Finds clients by name.
     *
     * @param name the name of the clients to find
     * @return a list of clients with the specified name
     * @throws NoSuchElementException if no clients with the specified name are found
     */
    public List<Client> findByName(String name) {
        List<Client> result = clientDAO.findByName(name);
        if (result == null) {
            throw new NoSuchElementException("The student with name =" + name + " was not found!");
        }
        return result;
    }

    /**
     * Finds clients by email.
     *
     * @param email the email of the clients to find
     * @return a list of clients with the specified email
     */
    public List<Client> findByEmail(String email) {
        Client c = clientDAO.findByEmail(email);
        List<Client> result = new ArrayList<>();
        result.add(c);
        return result;
    }

    /**
     * Deletes a client by ID.
     *
     * @param id the ID of the client to delete
     */
    public void deleteByID(int id) {
        clientDAO.delete("ID", id);
    }

    /**
     * Deletes clients by name.
     *
     * @param name the name of the clients to delete
     */
    public void deleteByName(String name) {
        clientDAO.deleteByNameOrMail("name", name);
    }

    /**
     * Deletes clients by email.
     *
     * @param email the email of the clients to delete
     */
    public void deleteByMail(String email) {
        clientDAO.deleteByNameOrMail("email", email);
    }

    /**
     * Inserts a new client.
     *
     * @param name  the name of the client to insert
     * @param email the email of the client to insert
     */
    public void insert(String name, String email) {
        Client client = new Client(name, email);
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.insert(name, email);
    }

    /**
     * Updates a client by ID.
     *
     * @param value the ID of the client to update
     * @param name  the new name of the client
     * @param email the new email of the client
     */
    public void updateByID(int value, String name, String email) {
        Client client = new Client(name, email);
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.updateByID("ID", value, name, email);
    }

    /**
     * Updates clients by name.
     *
     * @param value the name of the clients to update
     * @param name  the new name to set for the clients
     * @param email the new email to set for the clients
     */
    public void updateByName(String value, String name, String email) {
        Client client = new Client(name, email);
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.updateByNameOrEmail("name", value, name, email);
    }

    /**
     * Updates clients by email.
     *
     * @param value the email of the clients to update
     * @param name  the new name to set for the clients
     * @param email the new email to set for the clients
     */
    public void updateByEmail(String value, String name, String email) {
        Client client = new Client(name, email);
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.updateByNameOrEmail("email", value, name, email);
    }
}
