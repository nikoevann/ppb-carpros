package com.e.porsche.managers

import com.e.porsche.models.CurrentUser
import com.e.porsche.models.MyCar
import com.e.porsche.models.MyService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object UserManager {

    var currentUser: CurrentUser? = null

    val isUserLogged: Boolean// = true
        get() { return currentUser != null }

    fun removeAllData() {
        Injector.storageManager.removeAllData()
        currentUser = null
    }

    fun removeCurrentUser() {
        Injector.storageManager.removeUser()
        currentUser = null
    }

    fun setUpUser(currentUser: CurrentUser) {
        this.currentUser = currentUser
        Injector.storageManager.setUser(Gson().toJson(currentUser).toString())
    }

    fun getUpUser() {
        Injector.storageManager.getUser()?.let {
            this.currentUser = Gson().fromJson(it, CurrentUser::class.java)
        }
    }

    fun setUserCars(arr: ArrayList<MyCar>) {
        Injector.storageManager.setUserCars(Gson().toJson(arr).toString())
    }

    fun getUserCars() : ArrayList<MyCar> {
        val arr = ArrayList<MyCar>()
        Injector.storageManager.getUserCars()?.let {
            val cars: ArrayList<MyCar>? = Gson().fromJson(it, object : TypeToken<ArrayList<MyCar>>(){}.type)
            if(!cars.isNullOrEmpty()) { arr.addAll(cars) }
        }
        return arr
    }

    fun addUserCar(car: MyCar) {
        val cars = getUserCars()
        cars.add(car)
        setUserCars(cars)
    }

    fun setMyServices(arr: ArrayList<MyService>) {
        Injector.storageManager.setUserServices(Gson().toJson(arr).toString())
    }

    fun getMyServices() : ArrayList<MyService> {
        val arr = ArrayList<MyService>()
        Injector.storageManager.getUserServices()?.let {
            val services: ArrayList<MyService>? = Gson().fromJson(it, object : TypeToken<ArrayList<MyService>>(){}.type)
            if(!services.isNullOrEmpty()) { arr.addAll(services) }
        }
        return arr
    }

    fun addUserService(service: MyService) {
        val ser = getMyServices()
        ser.add(service)
        setMyServices(ser)
    }





}
