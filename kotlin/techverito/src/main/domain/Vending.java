package domain;

import java.util.*;

public class Vending {
    private final List<Product> products = new ArrayList<>();
    private final Map<Coin, Integer> coinInventory = new HashMap<>(Map.of(Coin.ONE, 10, Coin.FIVE, 10, Coin.TEN, 10, Coin.TWENTY_FIVE, 10));
    private Product selectedProduct;
    private int coinSum;

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Product selectProduct(Type type) {
        this.products.stream().filter(p -> p.getType() == type)
                .findFirst().ifPresent(p -> selectedProduct = p);
        return selectedProduct;
    }

    public Optional<Product> dispenseProduct() {
        return this.products.stream()
                .filter(p -> p.getType() == selectedProduct.getType())
                .findFirst();
    }

    public Map<Coin, Integer> insertCoin(List<Coin> coins) {
        int price = selectedProduct.getPrice();

        int coinSum = coins.stream()
                .map(Coin::getNum)
                .reduce(0, Integer::sum);

        this.coinSum = coinSum;
        if (coinSum < price) {
            throw new RuntimeException("Please enter more coins");
        }
        if (selectedProduct.getPrice() < coinSum) {
            return returnChange();
        }
        return Collections.emptyMap();
    }

    private Map<Coin, Integer> returnChange() {
        int excessAmount = coinSum - selectedProduct.getPrice();
        Map<Coin, Integer> returnCoins = new HashMap<>();

        //check for highest denomination then give the coins
        if (coinInventory.get(Coin.TWENTY_FIVE) * 25 > coinSum) {
            int coins_25 = excessAmount / Coin.TWENTY_FIVE.getNum();
            excessAmount = excessAmount % Coin.TWENTY_FIVE.getNum() * coins_25;
            coinInventory.computeIfPresent(Coin.TWENTY_FIVE, (k, v) -> v - coins_25);
            returnCoins.put(Coin.TWENTY_FIVE, coins_25);
        }
        return returnCoins;
    }

}
