package com.e.porsche.ui.constructor.constModels

import com.e.porsche.models.ConstructorColor


class ConstSett(val mainTitle: String? = null,
                val title: String,
                val subsett: ArrayList<ConstSubSett>,
                var isOpened: Boolean = false) : ConstCar(ConstSettType.CONST_SETT)

class ConstColors(val mainTitle: String? = null,
                  var title: String,
                  val colors: ArrayList<SubColors>,
                  var isOpened: Boolean = false) : ConstCar(ConstSettType.CONST_COLORS)


class SubColors(val title: String,
                val c1: ConstructorColor,
                val c2: ConstructorColor,
                val c3: ConstructorColor,
                val c4: ConstructorColor)

class ConstSubSett(val title: String,
                   val price: String)

abstract class ConstCar(val type: ConstSettType)

enum class ConstSettType(val value: Int) {
    CONST_SETT(0),
    CONST_COLORS(1)
}
