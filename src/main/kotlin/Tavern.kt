fun main() {
    var beverage = readLine()
//    beverage = null
    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("I can't do that without crashing - beverage was null!")
    }
    val beverageServed = beverage ?: "Buttered Ale"
    println(beverageServed)
}