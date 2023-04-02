package com.e.porsche.ui.accessories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.porsche.managers.CarsUtil

class AccessoriesViewModel : ViewModel() {

    val accessories = MutableLiveData<ArrayList<Int>>()


    fun loadAccess() {
        accessories.value = CarsUtil.getAccessories()
    }
}