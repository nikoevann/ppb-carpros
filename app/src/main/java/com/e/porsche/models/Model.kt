package com.e.porsche.models

import java.io.Serializable

class Model(val name: String, val imageID: Int) : Serializable {
    var isAnimated = false
}
