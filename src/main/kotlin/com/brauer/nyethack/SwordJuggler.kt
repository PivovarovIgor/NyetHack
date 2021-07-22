package com.brauer.nyethack

fun main() {
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = 2
    }

    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    println("You juggle $swordsJuggling swords!")
}

fun proficiencyCheck(swordsJuggling: Int?) {
    //swordsJuggling ?: throw com.brauer.nyethack.UnskilledSwordJugglerException()
    checkNotNull(swordsJuggling) { "com.brauer.nyethack.Player cannot juggle swords" }
}

class UnskilledSwordJugglerException() : IllegalStateException("com.brauer.nyethack.Player cannot juggle swords")