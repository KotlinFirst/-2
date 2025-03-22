package corporation.worker

import corporation.*
import corporation.product.*
import java.io.File

class Accountant(
    id: Int,
    name: String,
    age: Int
) : Worker(id, name, age, position = ProfessionsList.ACCOUNTANT) {

    val fileProductType = File("Product_Type.txt")
    val fileEmployee = File("Employee_List.txt")
    override fun work() {
        super.work()
        while (true) {
            println("Enter the operation code.")
            val operationCodes = OperationCodes.entries
            for ((index, nameOperation) in operationCodes.withIndex()) {
                print("$index - ${nameOperation.title}")
                if (index < operationCodes.size - 1) {
                    print(", ")
                } else print(": ")
            }
            val operationIndex = readln().toInt()
            val operationCode = operationCodes[operationIndex]

            when (operationCode) {
                OperationCodes.EXIT -> break
                OperationCodes.REGISTER_NEW_ITEM -> registerNewItem()
                OperationCodes.SHOW_ALL_ITEMS -> showAllItems()
                OperationCodes.REMOVE_PRODUCT_TYPE -> removeProductType()
                OperationCodes.ADD_NEW_EMPLOYEE -> addNewEmployee()
                OperationCodes.SHOW_ALL_EMPLOYEE -> showAllEmployee()
                OperationCodes.REMOVE_EMPLOYEE -> removeEmployee()
            }
        }
    }

    // Работа с сотрудниками
    private fun addNewEmployee() {
        val professionsList = ProfessionsList.entries
        println("Введите код профессии: ")
        for ((index, profession) in professionsList.withIndex()) {
            print("$index - ${profession.title}")
            if (index < professionsList.size - 1) {
                print(", ")
            } else print(": ")
        }
        val indexProfession = readln().toInt()
        val profession = professionsList[indexProfession]
        println("Введите id: ")
        val id = readln().toInt()
        println("Введите имя")
        val name = readln()
        println("Введите возраст")
        val age = readln().toInt()
        val employeeCard = when (profession) {
            ProfessionsList.DIRECTOR -> Director(id, name, age)
            ProfessionsList.ACCOUNTANT -> Accountant(id, name, age)
        }
        saveEmployeeCardToFile(employeeCard)

    }

    private fun showAllEmployee() {
        for (worker in loadWorkerList()) {
            worker.work()
        }
    }

    private fun loadWorkerList(): MutableList<Worker> {
        val workerList = mutableListOf<Worker>()
        if (workerList.isEmpty()) return workerList
        val content = fileEmployee.readText().trim()
        val employeeList = content.split("\n")
        for (employee in employeeList) {
            val properties = employee.split("%")
            val id = properties[0].toInt()
            val name = properties[1]
            val age = properties[2].toInt()
            val position = ProfessionsList.valueOf(properties.last())

            val worker = when (position) {
                ProfessionsList.DIRECTOR -> Director(id, name, age)
                ProfessionsList.ACCOUNTANT -> Accountant(id, name, age)
            }
            workerList.add(worker)
        }
        return workerList
    }

    private fun removeEmployee() {
        val workerList = loadWorkerList()
        print("Введите ID сотрудника, которого нужно удалить: ")
        val id = readln().trim().toInt()
        for (worker in workerList) {
            if (worker.id == id) {
                workerList.remove(worker)
                break
            }
        }
        fileEmployee.writeText(" ")
        for (worker in workerList) {
            saveEmployeeCardToFile(worker)
        }
    }

    private fun saveEmployeeCardToFile(worker: Worker) {
        fileEmployee.appendText("${worker.id}%${worker.name}%${worker.age}%${worker.position}\n")
    }
//    Ниже работа с карточками товаров

    fun registerNewItem() {
        val productTypes = ProductType.entries
        println("Enter the product type:")
        for ((index, type) in productTypes.withIndex()) {
            print("$index - ${type.title}")
            if (index < productTypes.size - 1) {
                print(", ")
            } else print(": ")
        }
        val productTypeIndex = readln().toInt()
        val productType = productTypes[productTypeIndex]
        println("Enter the name:")
        val name = readln()
        println("Enter the price:")
        val price = readln().toInt()
        val card = when (productType) {
            ProductType.FOOD -> {
                println("Enter the shelfLife:")
                val shelfLifeFood = readln().toInt()
                FoodProductCard(name, price, shelfLifeFood)
            }

            ProductType.APPLIANCE -> {
                println("Enter the brand:")
                val brand = readln()

                AppliancesCard(name, price, brand)
            }

            ProductType.SHOE -> {
                println("Enter the size:")
                val size = readln().toInt()
                ShoeCard(name, price, size)
            }
        }
        saveProductCardToFile(card)
    }

    fun showAllItems() {
        val productCards = loadAllCards()
        for (productCard in productCards) {
            productCard.printInfo()
        }
    }

    fun removeProductType() {
        val cards = loadAllCards()
        print("Вводи имя карточки, которую нужно удалить:")
        val name = readln()
        for (card in cards) {
            if (name == card.name) {
                cards.remove(card)
                break
            }
        }
        fileProductType.writeText("")
        for (card in cards) {
            saveProductCardToFile(card)
        }

    }

    fun saveProductCardToFile(productCard: ProductCard) {
        fileProductType.appendText("${productCard.name}%${productCard.price}%")
        when (productCard) {
            is FoodProductCard -> fileProductType.appendText("${productCard.shelfLife}%")
            is ShoeCard -> fileProductType.appendText("${productCard.size}% ")
            is AppliancesCard -> fileProductType.appendText("${productCard.brand}%")
        }
        fileProductType.appendText("${productCard.type}")

    }

    fun loadAllCards(): MutableList<ProductCard> {

        val cards = mutableListOf<ProductCard>()
        if (cards.isEmpty()) {
            return cards
        }
        val content = fileProductType.readText().trim()
        val cardsAsString = content.split("\n")
        for (card in cardsAsString) {
            val properties = card.split("%")
            val name = properties[0]
            val price = properties[1].toInt()
            val type = properties.last()
            val productType = ProductType.valueOf(type)
            val productCard = when (productType) {
                ProductType.FOOD -> {
                    val shelfLifeFood = properties[2]
                    FoodProductCard(name, price, shelfLifeFood.toInt())
                }

                ProductType.APPLIANCE -> {
                    val brand = properties[2]
                    AppliancesCard(name, price, brand)
                }

                ProductType.SHOE -> {
                    val size = properties[2]
                    ShoeCard(name, price, size.toInt())
                }
            }
            productCard.printInfo()
            cards.add(productCard)
        }
        return cards
    }


}

