import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class VendingTest {
    private Vending machine;

    @BeforeEach
    void setUp() {
        machine = new Vending();
        machine.addProduct(new Product(Type.CHIPS, 10));
    }


    @Test
    void selectProduct() {
        Assertions.assertEquals(Type.CHIPS, machine.selectProduct(Type.CHIPS).getType());
    }

}