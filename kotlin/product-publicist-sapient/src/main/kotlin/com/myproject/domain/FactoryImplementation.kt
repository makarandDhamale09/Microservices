package com.myproject.domain

import java.lang.RuntimeException

interface Item

class ItemA : Item

class ItemB : Item

class ItemFactory {
    companion object {
        fun getItem(itemType: String): Item {
            return when (itemType) {
                "itemA" -> ItemA()
                "itemB" -> ItemB()
                else -> {
                    throw RuntimeException("The item type is incorrect")
                }
            }
        }
    }
}

interface MotorVehicle {
    fun build()
}

class MotorCycle : MotorVehicle {
    override fun build() {
        println("Build MotorCycle...!!")
    }
}

class Car : MotorVehicle {
    override fun build() {
        println("Build Car..!!")
    }
}

fun main() {
    val item = ItemFactory.getItem("itemA")
    println("Item is ItemA : ${item is ItemA}")
}
