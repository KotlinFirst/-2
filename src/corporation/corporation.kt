package corporation

import corporation.worker.Accountant
import corporation.worker.Director
import corporation.worker.Worker

fun main() {
//    val sneakers = ShoeCard("Sneakers", 3200, 41)
//    val apple = FoodProductCard("apple", 39, 4)
//    val vacuumCleaner = AppliancesCard("cleaner", 38000, "Xiaomi")
//    sneakers.printInfo()
//    apple.printInfo()
//    vacuumCleaner.printInfo()
    val accountant = Accountant(0,"Nasty", 36)
    val workers = listOf<Worker>(accountant)
    for (worker in workers){
        worker.work()
    }
}