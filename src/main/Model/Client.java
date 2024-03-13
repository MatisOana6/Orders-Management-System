package src.main.Model;

/**
 * Această clasă este utilizată pentru a stoca datele referitoare la un client, date
 * existente în baza de date. Atributele clasei sunt: ID, name, email.
 * Clasa conține constructori, gettere și settere, și metoda toString, care ajută la afișarea clienților din baza de date.
 */

public class Client {
    private int id;
    private String name;
    private String email;

    public Client() {
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Client(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}