package com.e.porsche.ui.salon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.porsche.managers.CarsUtil
import com.e.porsche.models.Model

class SalonViewModel : ViewModel() {

    val carModelsLiveData = MutableLiveData<ArrayList<Model>>()

    fun loadCarModels() {
        carModelsLiveData.value = CarsUtil.getModelsCars()
    }
}