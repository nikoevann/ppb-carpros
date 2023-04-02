package com.e.porsche.managers

import android.content.Context
import com.e.porsche.R

object FieldsValidatorUtil{

    private val USERNAME_MIN_LENGTH = 3

    private val BIO_MIN_LENGTH = 10
    private val BIO_MAX_LENGTH = 30

    fun isEmpty(value: String, context: Context): String? {
        return if(value.isEmpty() || value.isBlank()){
            "Поле пусте"
        }else null
    }

    fun isValid(value: String?, context: Context): String? {
        return if(value != null){
            isEmpty(value, context)
        }else{
            "Поле пусте"
        }
    }

    fun isNameValid(value: String?, context: Context): String? {
        return if(value != null){
            return isEmpty(value, context) ?:
//            if(value.length >= USERNAME_MIN_LENGTH) { null
//            } else { getErrorMinLength(context, USERNAME_MIN_LENGTH) }
            null
        }else{
            "Поле пусте"
        }
    }

    fun isEmailValid(value: String?, context: Context): String? {
        return if(value != null){
            return isEmpty(value, context)
        }else{
            "Поштова адреса погано введена"
        }
    }

    fun isPassValid(value: String?, context: Context): String? {
        return if(value != null){
            return isEmpty(value, context)
        }else{
            "Пароль не відповідає вимогам"
        }
    }

//    fun isBioValid(value: String?, context: Context) : String? {
//        return if(value != null){
//            return isEmpty(value, context)  ?:
//            if(value.length >= BIO_MIN_LENGTH)
//                return if(value.length <= BIO_MAX_LENGTH) null
//                else getErrorMaxLength(context, BIO_MAX_LENGTH)
//            else getErrorMinLength(context, BIO_MIN_LENGTH)
//        } else { context.getString(R.string.empty_field_message) }
//
//    }

//    private fun getErrorMinLength(context: Context, value: Int): String{
//        return "${context.getString(R.string.general_minimumlengthis)} ${value}"
//    }
//
//    private fun getErrorMaxLength(context: Context, value: Int): String{
//        return "${context.getString(R.string.general_maximumlengthis)} ${value}"
//    }
//
//    fun isUserNameValid(title: String?, context: Context): String?{
//        return if(title != null){
//            return isEmpty(title, context) ?:
//            if(title.length > Limits.USER_NAME_LENGTH){
//                context.getString(R.string.empty_field_error)
//            }else{
//                null
//            }
//        }else context.getString(R.string.empty_field_error)
//    }

    //Patterns.EMAIL_ADDRESS.matcher("").matches()
}