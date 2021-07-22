package com.brauer.nyethack

fun main() {

    val player = Player()
    player.castFireball()
    printPlayerStatus(player)
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

