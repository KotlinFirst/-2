package corporation.worker

class Director(
    id:Int,
    name: String,
    age: Int
) : Worker (id,name, age, position = ProfessionsList.DIRECTOR ) {
    override fun work() {
        super.work()
        println("I drink coffee")
    }
}