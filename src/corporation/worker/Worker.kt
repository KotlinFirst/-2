package corporation.worker

open class Worker(
    val id: Int,
    val name: String,
    val age: Int,
    val position: ProfessionsList
) {
    open fun work(){
        println("id $id, name $name, age $age, position ${position.title}")
    }
}