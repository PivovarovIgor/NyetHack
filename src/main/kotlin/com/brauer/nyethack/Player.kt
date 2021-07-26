package com.brauer.nyethack

import java.io.File
import java.util.*

class Player(
    _name: String,
    var healthPoints: Int = 100,
    val isBlessed: Boolean,
    private val isImmortal: Boolean
) {

    var name = _name
        get() = field.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }.let { "$it of $hometown" }
        private set(value) {
            field = value.trim()
        }

    val hometown by lazy { selectHometown() }

    init {
        require(healthPoints > 0) { "healthPoint must be greater then zero." }
        require(name.isNotBlank()) { "Player must have a name." }
    }

    constructor(name: String) : this(
        name,
        isBlessed = true,
        isImmortal = false
    ) {
        if (name.lowercase() == "kar") {
            healthPoints = 40
        }
    }

    private fun selectHometown() = File("data/towns.txt")
            .readText()
            .split("\n")
            .shuffled()
            .first()


    fun castFireball(numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (X$numFireballs)")

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus(): String =
        when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly!"
            } else {
                "has some minor wounds."
            }
            in 15..74 -> "looks pretty hurt."
            else -> "is in awful conditional!"
        }

}