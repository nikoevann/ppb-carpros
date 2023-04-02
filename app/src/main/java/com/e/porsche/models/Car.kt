package com.e.porsche.models

import java.io.Serializable

class Car(val type: CarType,
          val zh: String,
          val wd: String,
          val fuel: String,
          val co2: String,
          val name: String,
          val price: String,
          val image: Int,
          val hp: String,
          val speed: String,
          val images: ArrayList<Int>) : Serializable

enum class CarType(val string: String) {
    CAR_718_CAYMAN("718 Cayman"),
    CAR_718_BOXSTER("718 Boxster"),
    CAR_718_GTS("718 GTS"),
    CAR_911_CARRERA_S("911 Carrera S"),
    CAR_911_GT3("911 GT3")
}
