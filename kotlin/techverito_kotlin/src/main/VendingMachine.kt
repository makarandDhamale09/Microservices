import Coin.*
import java.util.*

class VendingMachine(private val products: ArrayList<Product> = ArrayList()) {
    private var selectedProduct: Product? = null
    private var coinSum: Int = 0
    private val coinInventory = mutableMapOf(ONE to 10, FIVE to 10, TEN to 10, TWENTY_FIVE to 10)

    fun addProducts(product: Product) {
        products.add(product)
    }

    fun selectProduct(type: Type) {
        selectedProduct = products.find { it.type == type }
        println("SelectedProduct is $selectedProduct")
    }

    fun dispenseProduct(): Product? {
        val dispenseProduct = products.find { it.type == selectedProduct?.type }
        println("Dispensing Product $dispenseProduct")
        return dispenseProduct
    }

    fun insertCoin(coins: List<Coin>): Map<Coin, Int> {
        coinSum = coins.map { it.num }.reduce { acc, next -> acc + next }
        println("coinSum $coinSum")

        if (coinSum < selectedProduct!!.price) {
            throw RuntimeException("Please enter more coins")
        }

        if (selectedProduct!!.price < coinSum) {
            return returnChange()
        }
        return Collections.emptyMap()
    }

    private fun returnChange(): Map<Coin, Int> {
        var excessAmount = coinSum - selectedProduct!!.price
        val returnCoins = HashMap<Coin, Int>()

        //check for highest denomination then give the coins
        if (coinInventory.getValue(TWENTY_FIVE) * 25 > coinSum) {
            val coins_25 = excessAmount / TWENTY_FIVE.num
            excessAmount = excessAmount % TWENTY_FIVE.num * coins_25
            coinInventory.computeIfPresent(TWENTY_FIVE) { key, value -> value - coins_25 }
            returnCoins.put(TWENTY_FIVE,coins_25)
        }
        return returnCoins
    }
}