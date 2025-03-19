package corporation

class ShoeCard(
    name: String,
    price: Int,
    val size: Int
) : ProductCard(name, price) {
    override fun printInfo() {
        super.printInfo()
        println("size $size")
    }
}