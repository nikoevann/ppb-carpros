package com.e.porsche.managers

class StorageManager : PreferenceHelper() {
    companion object {
        private const val USER_KEY = "user_key"
        private const val USER_SERVICES = "user_services"
        private const val USER_CARS = "user_cars"
    }


    fun setUser(username: String){
        storeValue(Injector.application!!, USER_KEY, username)
    }

    fun getUser(): String? = getStringValue(Injector.application!!, USER_KEY)



    fun setUserServices(image: String) {
        storeValue(Injector.application!!, USER_SERVICES, image)
    }

    fun getUserServices() : String? = getStringValue(Injector.application!!, USER_SERVICES)

    fun setUserCars(cars: String) {
        storeValue(Injector.application!!, USER_CARS, cars)
    }

    fun getUserCars() : String? = getStringValue(Injector.application!!, USER_CARS)

    fun removeUser() {
        removeValue(Injector.application!!, USER_KEY)
    }



    fun removeAllData() {
        removeValue(Injector.application!!, USER_CARS)
        removeValue(Injector.application!!, USER_KEY)
        removeValue(Injector.application!!, USER_SERVICES)
    }


}
