package com.e.porsche.ui.myCars

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import com.e.porsche.R
import com.e.porsche.managers.FieldsValidatorUtil
import com.e.porsche.managers.UserManager
import com.e.porsche.models.MyCar
import com.e.porsche.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_my_cars.*
import kotlinx.android.synthetic.main.layout_my_car.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class MyCarsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cars)

        setSupportActionBar(toolbar_view_my_cars.toolbar, true, false)

        rb_my_cars.setOnClickListener {radioPressed() }
        rb_create_create.setOnClickListener { radioPressed() }

        etv_my_car_model?.initBuilder(hintString = "Модель",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT)
        etv_my_car_model?.onFocusChanged { if(!it) { checkModel() } }

        etv_my_car_vin?.initBuilder(hintString = "VIN",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT)
        etv_my_car_vin?.onFocusChanged { if(!it) { checkVin() } }


        etv_my_car_plate?.initBuilder(hintString = "Державний номер",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT)
        etv_my_car_plate?.onFocusChanged { if(!it) { checkPlate() } }

        btn_create_service.setOnClickListener {
            if(isFieldValid()) {
                UserManager.addUserCar(
                    MyCar(etv_my_car_model.text!!,
                    etv_my_car_vin.text!!,
                    etv_my_car_plate.text!!))

                etv_my_car_model.text = ""
                etv_my_car_vin.text = ""
                etv_my_car_plate.text = ""

                rb_my_cars.performClick()
                updateMyCars()
            }
        }

        updateMyCars()

    }

    private fun updateMyCars() {
        ll_my_cars.removeAllViews()
        val cars = UserManager.getUserCars()

        for(car in cars) {
            val v = LayoutInflater.from(this).inflate(R.layout.layout_my_car, null, false)

            v.tv_my_car_model.text = car.model
            v.tv_my_car_vin.text = car.vin
            v.tv_my_car_plate.text = car.plate

            ll_my_cars.addView(v)
        }

    }

    private fun radioPressed(){
        if(rb_my_cars.isChecked) {
            ll_my_cars.visibility = View.VISIBLE
            ll_create_cars.visibility = View.GONE
        } else {
            ll_my_cars.visibility = View.GONE
            ll_create_cars.visibility = View.VISIBLE
        }
    }

    private fun isFieldValid() : Boolean {
        var isValid = true
        if (checkModel()) isValid = false
        if (checkVin()) isValid = false
        if (checkPlate()) isValid = false
        return isValid
    }

    private fun checkModel() : Boolean {
        etv_my_car_model.setError(FieldsValidatorUtil.isEmailValid(etv_my_car_model.text, this))
        return etv_my_car_model.hasError
    }

    private fun checkVin() : Boolean {
        etv_my_car_vin.setError(FieldsValidatorUtil.isEmailValid(etv_my_car_vin.text, this))
        return etv_my_car_vin.hasError
    }

    private fun checkPlate() : Boolean {
        etv_my_car_plate.setError(FieldsValidatorUtil.isEmailValid(etv_my_car_plate.text, this))
        return etv_my_car_plate.hasError
    }
}
