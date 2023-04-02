package com.e.porsche.ui.carService

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import com.e.porsche.R
import com.e.porsche.managers.CarsUtil
import com.e.porsche.managers.DateTimeUtils
import com.e.porsche.managers.FieldsValidatorUtil
import com.e.porsche.managers.UserManager
import com.e.porsche.models.MyCar
import com.e.porsche.models.MyService
import com.e.porsche.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_car_service.*
import kotlinx.android.synthetic.main.layout_my_service.*
import kotlinx.android.synthetic.main.layout_my_service.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import java.util.*
import kotlin.collections.ArrayList

class CarServiceActivity : BaseActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private var dateTime: Date? = null
    private var selectedCar: MyCar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_service)

        setSupportActionBar(toolbar_view_car_service.toolbar, true, false)

        rb_my.setOnClickListener {radioPressed() }
        rb_create.setOnClickListener { radioPressed() }



        etv_service_name?.initBuilder(hintString = "Nama",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT)
        etv_service_name?.onFocusChanged { if(!it) {checkUsername()} }


        etv_service_phone?.initBuilder(hintString = "Telepon",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_NUMBER)
        etv_service_phone?.onFocusChanged { if(!it) {checkPhone()} }

        etv_service_email?.initBuilder(hintString = "E-mail",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT)
        etv_service_email?.onFocusChanged { if(!it) {checkEmail()} }


        etv_service_car_model?.initBuilder(hintString = "Model",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT,
            focusable = false)
        etv_service_car_model?.onActionPressed { checkModel() }
        etv_service_car_model?.onFocusChanged { if(!it) {checkModel()} }


        etv_service_car_vin?.initBuilder(hintString = "Datang",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT)
        etv_service_car_vin?.onFocusChanged { if(!it) {checkVin()} }

        etv_service_car_mileage?.initBuilder(hintString = "Jarak tempuh mobil",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT)
        etv_service_car_mileage?.onFocusChanged { if(!it) {checkMileage()} }

        etv_service_car_plate?.initBuilder(hintString = "Nomor Plat",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT)
        etv_service_car_plate?.onFocusChanged { if(!it) {checkPlate()} }

        etv_service_car_diler?.initBuilder(hintString = "Dealer Mobil",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT,
            focusable = false)
        etv_service_car_diler?.onActionPressed { checkDiler() }
        etv_service_car_diler?.onFocusChanged { if(!it) {checkDiler()} }

        etv_service_car_date?.initBuilder(hintString = "Tanggal service mobil",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT,
            focusable = false)
        etv_service_car_date?.onActionPressed { checkDate() }
        etv_service_car_date?.onFocusChanged { if(!it) {checkDate()} }

        etv_service_car_time?.initBuilder(hintString = "Waktu service mobil",
            colorRes = R.color.gray,
            inputType = InputType.TYPE_CLASS_TEXT,
            focusable = false)
        etv_service_car_time?.onActionPressed { checkTime() }
        etv_service_car_time?.onFocusChanged { if(!it) {checkTime()} }

        etv_service_car_model.editTextPressed { modelsPressed() }

        etv_service_car_diler.editTextPressed { dilerPressed() }

        etv_service_car_date.editTextPressed { datePressed() }

        etv_service_car_time.editTextPressed { timePressed() }

        btn_create_service.setOnClickListener {
            if(isFieldValid()) {
                UserManager.addUserService(MyService(etv_service_car_model.text!!,
                    etv_service_car_vin.text!!,
                    etv_service_car_mileage.text!!,
                    etv_service_car_plate.text!!,
                    etv_service_car_diler.text!!,
                    etv_service_car_date.text!!,
                    etv_service_car_time.text!!))


                etv_service_name.text = ""
                etv_service_phone.text = ""
                etv_service_email.text = ""
                etv_service_car_vin.text = ""
                etv_service_car_mileage.text = ""
                etv_service_car_plate.text = ""
                etv_service_car_diler.text = ""
                etv_service_car_date.text = ""
                etv_service_car_time.text = ""

                rb_my.performClick()

                updateServices()
            }
        }

        UserManager.currentUser?.email?.let {
            etv_service_email.text = it
        }
        UserManager.currentUser?.name?.let {
            etv_service_name.text = it
        }


        updateServices()
    }
    private fun updateServices() {
        ll_my_services.removeAllViews()

        val services = UserManager.getMyServices()

        for(serv in services) {
            val v = LayoutInflater.from(this).inflate(R.layout.layout_my_service, null, false)

            v.tv_service_car_model.text = serv.model
            v.tv_service_car_vin.text = serv.vin
            v.tv_service_car_plate.text = serv.plate
            v.tv_service_car_mileage.text = serv.mileage
            v.tv_service_car_date.text = serv.date
            v.tv_service_car_time.text = serv.time
            v.tv_service_car_diler.text = serv.diler

            ll_my_services.addView(v)
        }
    }


    private fun timePressed() {
        //            hideSoftKeyboard(this, it)
        val calendar = Calendar.getInstance()
        dateTime?.let { calendar.time = it }

        val timePickerDialog = TimePickerDialog(this,
            R.style.DatePickerRounded,
            this,
            calendar.get(Calendar.HOUR),
            calendar.get(Calendar.MINUTE), true)
        timePickerDialog.show()
    }

    private fun datePressed() {
        //            hideSoftKeyboard(this, it)
        val calendar = Calendar.getInstance()
        dateTime?.let { calendar.time = it }

        val datePickerDialog = DatePickerDialog(this,
            R.style.DatePickerRounded,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }

    private fun dilerPressed() {
        val dilers = CarsUtil.dilers
        AlertDialog.Builder(this)
            .setTitle("Dealer")
            .setItems(dilers) {dialog, which ->
                etv_service_car_diler.text = dilers[which]
                dialog.dismiss()
            }
            .show()
    }
    private fun modelsPressed() {
        val myCars = UserManager.getUserCars()
        val carsString = ArrayList<String>()

        for(car in myCars) {
            carsString.add(car.model)
        }

        AlertDialog.Builder(this)
            .setTitle("Dealer")
            .setItems(carsString.toTypedArray()) {dialog, which ->
                selectedCar = myCars[which]

                etv_service_car_model.text = selectedCar!!.model
                etv_service_car_plate.text = selectedCar!!.plate
                etv_service_car_vin.text = selectedCar!!.vin
                dialog.dismiss()
            }
            .show()
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        dateTime = DateTimeUtils.rawToDate(dateTime, p1, p2, p3)
        val date = DateTimeUtils.prepareDateForSelling(dateTime!!)
        etv_service_car_date.text = date
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        dateTime = DateTimeUtils.rawToTime(dateTime, p1, p2)
        val date = DateTimeUtils.prepareTimeForSelling(dateTime!!)
        etv_service_car_time.text = date
    }

    private fun radioPressed(){
        if(rb_my.isChecked) {
            ll_my_services.visibility = View.VISIBLE
            ll_create_services.visibility = View.GONE
        } else {
            ll_my_services.visibility = View.GONE
            ll_create_services.visibility = View.VISIBLE
        }
    }

    private fun isFieldValid() : Boolean {
        var isValid = true
        if (checkUsername()) isValid = false
        if (checkPhone()) isValid = false
        if (checkEmail()) isValid = false
        if (checkModel()) isValid = false
        if (checkVin()) isValid = false
        if (checkMileage()) isValid = false
        if (checkPlate()) isValid = false
        if (checkDiler()) isValid = false
        if (checkDate()) isValid = false
        if (checkTime()) isValid = false
        return isValid
    }

    private fun checkUsername() : Boolean {
        etv_service_name.setError(FieldsValidatorUtil.isNameValid(etv_service_name.text, this))
        return etv_service_name.hasError
    }

    private fun checkPhone() : Boolean {
        etv_service_phone.setError(FieldsValidatorUtil.isValid(etv_service_phone.text, this))
        return etv_service_phone.hasError
    }

    private fun checkEmail() : Boolean {
        etv_service_email.setError(FieldsValidatorUtil.isEmailValid(etv_service_email.text, this))
        return etv_service_email.hasError
    }

    private fun checkModel() : Boolean {
        return if(etv_service_car_model.hasText) {
            etv_service_car_model.setError(FieldsValidatorUtil.isValid(etv_service_car_model.text, this))
            etv_service_car_model.hasError
        } else { false }
    }

    private fun checkVin() : Boolean {
        etv_service_car_vin.setError(FieldsValidatorUtil.isEmailValid(etv_service_car_vin.text, this))
        return etv_service_car_vin.hasError
    }

    private fun checkMileage() : Boolean {
        etv_service_car_mileage.setError(FieldsValidatorUtil.isEmailValid(etv_service_car_mileage.text, this))
        return etv_service_car_mileage.hasError
    }

    private fun checkPlate() : Boolean {
        etv_service_car_plate.setError(FieldsValidatorUtil.isEmailValid(etv_service_car_plate.text, this))
        return etv_service_car_plate.hasError
    }

    private fun checkDiler() : Boolean {
        return if(etv_service_car_diler.hasText) {
            etv_service_car_diler.setError(FieldsValidatorUtil.isValid(etv_service_car_diler.text, this))
            etv_service_car_diler.hasError
        } else { false }
    }

    private fun checkDate() : Boolean {
        return if(etv_service_car_date.hasText) {
            etv_service_car_date.setError(FieldsValidatorUtil.isValid(etv_service_car_date.text, this))
            etv_service_car_date.hasError
        } else { false }
    }

    private fun checkTime() : Boolean {
        return if(etv_service_car_time.hasText) {
            etv_service_car_time.setError(FieldsValidatorUtil.isValid(etv_service_car_time.text, this))
            etv_service_car_time.hasError
        } else { false }
    }
}
