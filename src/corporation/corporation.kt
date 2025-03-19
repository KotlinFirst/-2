package corporation

fun main() {
//    val sneakers = ShoeCard("Sneakers", 3200, 41)
//    val apple = FoodProductCard("apple", 39, 4)
//    val vacuumCleaner = AppliancesCard("cleaner", 38000, "Xiaomi")
//    sneakers.printInfo()
//    apple.printInfo()
//    vacuumCleaner.printInfo()
    val director1 = Director("Vasya", 48)
    val director2 = Director("John", 39)
    val accountant = Accountant("Nastya", 36)
    val workers = listOf<Worker>(director1,director2,accountant)
    for (worker in workers){
        worker.work()
    }
}