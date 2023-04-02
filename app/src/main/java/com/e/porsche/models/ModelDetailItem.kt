package com.e.porsche.models

class ModelDetailItem {

    var type: ModelDetailItemType
    var title: String = ""

    var car1: Car? = null
    var car2: Car? = null

    constructor(type: ModelDetailItemType) {
        this.type = type
    }
}

enum class ModelDetailItemType(val value: Int) {
    TYPE_TITLE(0), TYPE_CARS(1)
}
