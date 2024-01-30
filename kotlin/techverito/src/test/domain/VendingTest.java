package domain;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static domain.Coin.*;
import static domain.Type.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VendingTest {
    private Vending vending;

    @Before
    public void setUp() {
        vending = new Vending();
        vending.addProduct(new Product(COKE, 10));
        vending.addProduct(new Product(CHIPS, 20));
        vending.addProduct(new Product(COOKIES, 15));
    }

    @Test
    public void selectProduct() {
        assertEquals(COKE, vending.selectProduct(COKE).getType());
    }

    @Test
    public void dispenseProduct() {
    }

    @Test
    public void when_insertedCoinsAreLessThanPrice_then_exception() {
        vending.selectProduct(COKE);
        List<Coin> coins = List.of(FIVE);
        assertThrows(RuntimeException.class, () -> vending.insertCoin(coins));
    }

    @Test
    public void when_insertedCoinsAreMoreThanPrice_then_coinsAreReturned() {
        vending.selectProduct(COKE);
        List<Coin> coins = List.of(TEN, TWENTY_FIVE);
        assertEquals(1, vending.insertCoin(coins).get(TWENTY_FIVE));
    }

    @Test
    public void when_insertedCoinsAreMoreThanPrice_then_coinsAreReturne() {
        vending.selectProduct(COKE);
        List<Coin> coins = List.of(TEN, TWENTY_FIVE);
        assertEquals(1, vending.insertCoin(coins).get(TWENTY_FIVE));
    }
}