
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VendingMachine {
    public static void main(String[] args) {
        List<Product> products = List.of(new Product(Type.COKE, 10),
                new Product(Type.CHIPS, 20));
        Vending vending = new Vending();

        addProducts(products, vending);
        vending.selectProduct(Type.COKE);

        List<Coin> coins = new ArrayList<>(List.of(Coin.TEN, Coin.TWENTY_FIVE));
        Map<Coin, Integer> coinIntegerMap = vending.insertCoin(coins);
        System.out.println("returned Coins :" + coinIntegerMap);

        Optional<Product> product = vending.dispenseProduct();

        product.ifPresent(System.out::println);
    }

    public static void addProducts(List<Product> products, Vending machine){
        for(Product p : products) {
            machine.addProduct(p);
        }
    }
}
