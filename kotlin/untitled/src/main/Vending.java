
import java.util.*;

public class Vending {
    private List<Product> products = new ArrayList<>();
    private Product selectedProduct;
    private List<Coin> coins;
    private Map<Coin, Integer> coinInventory = new HashMap<>(Map.of(Coin.ONE, 10, Coin.FIVE, 10, Coin.TEN, 10, Coin.TWENTY_FIVE, 10));
    private int coinSum;

    //main.Product chip = new main.Product(CHIPS, 10)
    // Machine machine = new Machine([COKE,CHIPS],chip,[ONE,TEN],{ONE=10,TEN=10},25)

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Product selectProduct(Type type) {
        selectedProduct = this.products.stream().filter(p -> p.getType() == type)
                .findFirst().get();
        return selectedProduct;
    }

    public Optional<Product> dispenseProduct() {
        return this.products.stream().filter(p -> p.getType() == selectedProduct.getType())
                .findFirst();
    }

    public Map<Coin, Integer> insertCoin(List<Coin> coins) {
        this.coins = new ArrayList<>();
        this.coins.addAll(coins);
        int price = selectedProduct.getPrice();


        Integer coinSum = coins.stream()
                .map(coin -> coin.num)
                .reduce(0, Integer::sum);

        this.coinSum = coinSum;
        if (coinSum < price) {
            throw new RuntimeException("Please enter more coins");
        }
        if (selectedProduct.getPrice() < coinSum) {
            return giveOutChange();
        }
        return null;
    }

    private Map<Coin, Integer> giveOutChange() {
        int excessAmount = coinSum - selectedProduct.getPrice();
        Map<Coin, Integer> returnCoins = new HashMap();

        //
        if (coinInventory.get(Coin.TWENTY_FIVE) * 25 > coinSum) {
            int TwentyFiveCoinCount = excessAmount / Coin.TWENTY_FIVE.num;
            excessAmount = excessAmount % Coin.TWENTY_FIVE.num * TwentyFiveCoinCount;
            coinInventory.computeIfPresent(Coin.TWENTY_FIVE, (k, v) -> v - TwentyFiveCoinCount);
            returnCoins.put(Coin.TWENTY_FIVE, TwentyFiveCoinCount);
        }
        return returnCoins;
    }

}
