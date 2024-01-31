import java.util.*

class VendingMachine(private val products: ArrayList<Product> = ArrayList()) {
    private var selectedProduct: Product? = null
    private var coinSum: Int = 0

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

    fun insertCoin(coins: List<Coin>) {
        coinSum = coins.map { it.num }.reduce { acc, next -> acc + next }
        println("coinSum $coinSum")

        if (coinSum < selectedProduct!!.price) {
            throw RuntimeException("Please enter more coins")
        }

        if (selectedProduct!!.price < coinSum) {
            returnChange()
        }
    }

    private fun returnChange(): Map<Coin, Int> {
        var excessAmount = coinSum - selectedProduct!!.price
        return Collections.emptyMap()
    }
}