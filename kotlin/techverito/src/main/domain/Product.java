package domain;

public class Product {
    private Type type;
    private int price;

    public Product(Type type, int price) {
        this.type = type;
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "domain.Product{" +
                "type=" + type +
                ", price=" + price +
                '}';
    }
}
