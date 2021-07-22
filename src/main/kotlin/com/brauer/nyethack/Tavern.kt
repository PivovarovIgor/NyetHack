package com.brauer.nyethack

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"
const val WIDTH_OF_MENU = 10
const val AMOUNT_ITEMS_OF_MENU = 3

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-data.txt")
    .readText()
    .trimEnd()
    .split("\n")
val patronGold = mutableMapOf<String, Double>()

fun main() {

    println("*** Welcome to $TAVERN_NAME ***")
    println()

    menuList
        .map {
            it.split(',')
        }.filter {
            it.size == AMOUNT_ITEMS_OF_MENU
        }.map {
            val (type, name, price) = it
            Triple(name, price, type)
        }.run {
            val padding = (maxOfOrNull {
                it.first.length
            } ?: 0) + (maxOfOrNull {
                it.second.length
            } ?: 0) + WIDTH_OF_MENU
            groupBy { it.third }
                .forEach { groupItem ->
                    println("~[${groupItem.key}]~".let {
                        it.padStart(padding / 2 + it.length / 2, ' ')
                    })
                    groupItem.value.forEach { item ->
                        println("${item.first}${item.second.padStart(padding - item.first.length, '.')}")
                    }
                }
        }

    println()

    println(patronList)
    patronList.remove("Eli")
    patronList.add("Alex")
    patronList.add(0, "Alex")
    patronList += "Reginald"
    patronList[0] = "Alexis"
    println(patronList)

    (0..9).forEach { _ ->
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <= 9 && menuList.isNotEmpty()) {
        placeOrder(uniquePatrons.shuffled().first(), menuList.shuffled().first())
        orderCount++
    }

    displayPatronBalance()
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price;
}

fun displayPatronBalance() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name")}"
    } else {
        "$patronName says: Thanks for the $name."
    }
    println(phrase)
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }