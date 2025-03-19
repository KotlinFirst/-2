package corporation

open class ProductCard(
    val name:String,
    val price:Int,
    val type:ProductType
) {
    open fun printInfo(){
        print("name: $name, price: $price, Product Type: ${type.title} ")
    }
}