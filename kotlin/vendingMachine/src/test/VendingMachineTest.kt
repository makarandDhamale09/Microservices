import Coin.*
import Type.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class VendingMachineTest {
    private var vending = VendingMachine()

    @Before
    fun setUp() {
        val products = mutableListOf(Product(CHIPS, 10), Product(COKE, 15), Product(COOKIES, 20))
        products.forEach { vending.addProducts(it) }
    }

    @Test
    fun addProducts() {
        assertEquals(3, vending.getProducts().size)
    }

    @Test
    fun selectProduct() {
        val selectedProduct = vending.selectProduct(CHIPS)
        assertNotNull(selectedProduct)
    }

    @Test
    fun dispenseProduct() {
        vending.selectProduct(CHIPS)
        val dispenseProduct = vending.dispenseProduct()
        assertEquals(CHIPS, dispenseProduct!!.type)
    }

    @Test
    fun when_insertedCoinsAreLessThanPrice_then_throwsRunTimeException() {
        vending.selectProduct(CHIPS)
        assertThrows<RuntimeException> { vending.insertCoin(listOf(FIVE, ONE)) }
    }

    @Test
    fun when_insertedCoinsAreEqualToPrice_then_NoCoinsAreReturned() {
        vending.selectProduct(CHIPS)
        val returnedCoins = vending.insertCoin(listOf(FIVE, FIVE))
        assertEquals(0, returnedCoins.size)
    }

    @Test
    fun when_insertedCoinsAreMoreThanPrice_then_coinsAreReturned() {
        vending.selectProduct(CHIPS)
        val returnedCoins = vending.insertCoin(listOf(TEN, TWENTY_FIVE))
        assertEquals(1, returnedCoins[TWENTY_FIVE])
    }
}