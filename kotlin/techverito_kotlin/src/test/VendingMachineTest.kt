import Type.*
import org.junit.Before
import org.junit.Test

class VendingMachineTest {
    private var vending = VendingMachine()

    @Before
    fun setUp() {
        val products = mutableListOf(Product(CHIPS, 10), Product(COKE, 15), Product(COOKIES, 20))
        products.forEach { vending.addProducts(it) }
    }

    @Test
    fun addProducts() {
        //assertEquals(3, v)
    }
}