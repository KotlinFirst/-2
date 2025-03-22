package corporation.product

class ShoeCard(
    name: String,
    price: Int,
    val size: Int
) : ProductCard(name, price, type = ProductType.SHOE) {
    override fun printInfo() {
        super.printInfo()
        println("size $size")
    }
}