package files

import corporation.OperationCodes
import java.io.File

fun main() {
    val file2 = File("to-do_list.txt")
    val operationCodes = files.OperationCodes.entries
    while (true) {
        print("Введите код операции: ")
        for ((index, operation) in operationCodes.withIndex()) {
            print("$index - ${operation.title}")
            if (index < operationCodes.size - 1) {
                print(", ")
            } else print(": \n")

        }
        val operationCode = operationCodes[readln().toInt()]
        when (operationCode) {
            files.OperationCodes.EXIT -> break
            files.OperationCodes.ADD_NEW_ITEM -> {
                println("Вводи ужеж")
                file2.appendText("${readln()}\n")
            }

            files.OperationCodes.SHOW_ALL_ITEMS -> {
                val content = file2.readText().trim()
                val items = content.split("\n")
                for ((index, item) in items.withIndex()) {
                    println("$index - $item")
                }
            }
        }
    }
//    while (true) {
//        println("Валяй")
//        val variable = readln()
//        if (variable == "0") break
//        if (variable == "1") {
//            print(file2.readText())
//        }
//        else file2.appendText("$variable\n")
//    }
}