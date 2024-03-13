package src.main.Model;

/**
 * Această clasă este utilizată pentru a stoca datele referitoare la o comandă, date existente în baza de date.
 * Atributele clasei sunt: ID, idClient, idProdus, price, quantity. Clasa conține constructori, gettere și settere.
 */

public class Order {
    private int id;
    private int idClient;
    private int idProduct;
    private double price;
    private int quantity;

    public Order() {
    }

    public Order(int idClient, int idProduct, double price, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.price = price;
        this.quantity = quantity;
    }

    public Order(int idClient, int idProduct, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idProduct=" + idProduct +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}