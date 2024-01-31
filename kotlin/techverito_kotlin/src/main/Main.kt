import Coin.FIVE
import Coin.TEN
import Type.*

fun main() {
    val machine = VendingMachine()

    //Adding products to the Vending machine
    val products = listOf(Product(COKE, 10), Product(CHIPS, 15), Product(COOKIES, 20))
    addProducts(machine,products)

    //select a Product
    machine.selectProduct(COKE)

    //insertCoins and return change
    machine.insertCoin(listOf(FIVE))

    //dispense Product
    machine.dispenseProduct()
}

fun addProducts(machine: VendingMachine, products: List<Product>){
    products.forEach { p -> machine.addProducts(p) }
}