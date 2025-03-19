package corporation

class FoodProductCard(
    name: String,
    price: Int,
    val shelfLife: Int
) : ProductCard(name, price) {
    override fun printInfo() {
        super.printInfo()
        println("shelf life $shelfLife")
    }
}