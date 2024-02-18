package com.myproject.domain

import java.math.BigDecimal

open class Product(val id: Int, var name: String, var price: BigDecimal, var color: String)

class Shirt(id: Int, name: String, price: BigDecimal, color: String) : Product(id, name, price, color) {
    override fun toString(): String {
        return "Shirt(id=$id, name=$name, price=$price, color=$color)"
    }
}

data class Person(val id: Int, val name: String)
