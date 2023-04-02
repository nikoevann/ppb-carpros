package com.e.porsche.managers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable



class ImageManager {


    fun encodeToBase64(drawable: Drawable) : String {
        return encodeToBase64(drawableToBitmap(drawable))
    }

    fun encodeToBase64(bitmap: Bitmap) : String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun decodeToBase64(image: String) : Bitmap {
        val decodeByte = Base64.decode(image, 0)
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.size)
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        var bitmap: Bitmap? = null

        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }

        if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            bitmap = Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
}
