package com.brauer.nyethack

fun main() {

    val player = Player("Madrigal")
    println("player 1 created")
    player.castFireball()
    printPlayerStatus(player)

    val player2 = Player("Kar")
    player2.castFireball()
    printPlayerStatus(player2)

    ////named arguments
//    com.brauer.nyethack.printPlayerStatus(
//        isBlessed = false,
//        name = "NONE",
//        auraColor = "black",
//        healthStatus = "some health status"
//    )

}

private fun printPlayerStatus(player: Player) {
    println("Aura: ${player.auraColor()} (Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
}

