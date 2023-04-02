package com.e.porsche.models

import android.graphics.Bitmap
import com.e.porsche.managers.ImageManager
import com.e.porsche.managers.Injector

class CurrentUser(val email: String,
                  val name: String,
                  val password: String,
                  val imageBase64: String) {

    fun getImageBitmap() : Bitmap {
        return Injector.imageManager.decodeToBase64(imageBase64)
    }


}
