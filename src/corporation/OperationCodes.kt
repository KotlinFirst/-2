package corporation

enum class OperationCodes(val title:String) {
    EXIT("Выход"),
    REGISTER_NEW_ITEM("Зарегистрировать новый товар"),
    SHOW_ALL_ITEMS("Показать все товары"),
    REMOVE_PRODUCT_TYPE("Удалить карточку"),
    ADD_NEW_EMPLOYEE("Добавить нового сотрудника"),
    SHOW_ALL_EMPLOYEE("Показать всех сотрудников"),
    REMOVE_EMPLOYEE("Удалить сотрудника")
}