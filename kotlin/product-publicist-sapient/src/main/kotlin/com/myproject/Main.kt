package com.myproject

import com.myproject.domain.Person
import com.myproject.domain.Shirt
import java.math.BigDecimal

fun main() {
    var shirt = Shirt(1, "Shirt", BigDecimal.valueOf(20L), "red")
    println(shirt)

    val person = Person(1, "Makarand")
    println(person)
}
