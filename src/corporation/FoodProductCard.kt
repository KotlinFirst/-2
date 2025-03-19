package corporation

class FoodProductCard(
    name: String,
    price: Int,
    val shelfLife: Int
) : ProductCard(name, price, type = ProductType.FOOD) {
    override fun printInfo() {
        super.printInfo()
        println("shelf life $shelfLife")
    }
}