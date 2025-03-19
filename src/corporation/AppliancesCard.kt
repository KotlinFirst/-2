package corporation

class AppliancesCard(
    name: String,
    price: Int,
    val brand: String,
) : ProductCard(name, price) {
    override fun printInfo() {
        super.printInfo()
        println("brand $brand ")
    }
}