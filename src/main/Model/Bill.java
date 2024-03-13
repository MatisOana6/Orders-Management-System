package src.main.Model;

public record Bill(int idClient, int idProduct,int quantity, double price) {

    @Override
    public int idClient() {
        return idClient;
    }

    @Override
    public int idProduct() {
        return idProduct;
    }

    @Override
    public int quantity() {
        return quantity;
    }

    @Override
    public double price() {
        return price;
    }
}
