class Product(val type: Type, val price: Int) {
    override fun toString(): String {
        return "Product(type=$type, price=$price)"
    }
}

enum class Type {
    COKE, CHIPS, COOKIES
}

enum class Coin(val num: Int) {
    ONE(1), TWO(2), FIVE(5), TEN(10), TWENTY_FIVE(25)
}