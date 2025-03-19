package corporation

open class Worker(
    val name: String,
    val age: Int
) {
    open fun work(){
        println("name $name, age $age")
    }
}