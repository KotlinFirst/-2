package corporation

import java.io.File

class Accountant(
    name: String,
    age: Int
) : Worker(name, age) {

    val items = mutableListOf<ProductCard>()
    val file = File("Product_Type.txt")
    override fun work() {

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
            }
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
        file.writeText("")
        for (card in cards){
            saveProductCardToFile(card)
        }

    }

    fun saveProductCardToFile(productCard: ProductCard) {
        file.appendText("${productCard.name}%")
        file.appendText("${productCard.price}%")
        when (productCard) {
            is FoodProductCard -> file.appendText("${productCard.shelfLife}%${ProductType.FOOD}\n")
            is ShoeCard -> file.appendText("${productCard.size}% ${ProductType.SHOE}\n")
            is AppliancesCard -> file.appendText("${productCard.brand}%${ProductType.APPLIANCE}\n")
        }
    }

    fun loadAllCards(): MutableList<ProductCard> {

        val cards = mutableListOf<ProductCard>()
        val content = file.readText().trim()
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

    fun showAllItems() {
        val content = file.readText().trim()
        if (content.isEmpty()) return
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
        }
    }


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
        file.appendText("$name%")
        println("Enter the price:")
        val price = readln().toInt()
        file.appendText("$price%")
        ProductType.valueOf("FOOD")
        when (productType) {
            ProductType.FOOD -> {
                println("Enter the shelfLife:")
                val shelfLifeFood = readln().toInt()
                file.appendText("$shelfLifeFood%")
            }

            ProductType.APPLIANCE -> {
                println("Enter the brand:")
                val brand = readln()
                file.appendText("$brand%")
            }

            ProductType.SHOE -> {
                println("Enter the size:")
                val size = readln().toInt()
                file.appendText("$size%")
            }

        }
        file.appendText("$productType\n")
    }
}

