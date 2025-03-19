package corporation

open class ProductCard(
    val name:String,
    val price:Int
) {
    open fun printInfo(){
        print("name: $name, price: $price. ")
    }
}