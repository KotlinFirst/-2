package corporation

enum class OperationCodes(val title:String) {
    EXIT("Выход"),
    REGISTER_NEW_ITEM("Зарегистрировать новый товар"),
    SHOW_ALL_ITEMS("Показать все товары"),
    REMOVE_PRODUCT_TYPE("Удалить карточку")
}