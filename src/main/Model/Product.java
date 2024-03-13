package src.main.Model;

/**
 * Această clasă este utilizată pentru a stoca datele referitoare la un produs, date existente în baza de date.
 * Atributele clasei sunt: ID, name, quantity, price. Clasa conține contructori, gettere și settere
 */


public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Product() {
    }

    public Product( String name, Integer quantity, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}