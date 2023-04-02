package com.e.porsche.ui.modelDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.porsche.managers.CarsUtil
import com.e.porsche.models.*

class ModelDetailsViewModel: ViewModel() {

    val modelItems = MutableLiveData<ArrayList<ModelDetailItem>>()

    fun loadItems(model: Model) {
        val cars = CarsUtil.getCarsByModel(model)

        val arr = ArrayList<ModelDetailItem>()

        var type:CarType? = null
        for(index in 0 until cars.lastIndex step 2) {
            val item1 = cars[index]

            if(item1.type != type) {
                type = item1.type
                val modelTitle = ModelDetailItem(ModelDetailItemType.TYPE_TITLE)
                modelTitle.title = item1.type.string
                arr.add(modelTitle)

                val modelDetailItem = ModelDetailItem(ModelDetailItemType.TYPE_CARS)
                modelDetailItem.car1 = item1
                if(index+1 <= cars.lastIndex) {
                    modelDetailItem.car2 = cars[index+1]
                }
                arr.add(modelDetailItem)
            } else {
                val modelDetailItem = ModelDetailItem(ModelDetailItemType.TYPE_CARS)
                modelDetailItem.car1 = item1
                if(index+1 <= cars.lastIndex) {
                    modelDetailItem.car2 = cars[index+1]
                }
                arr.add(modelDetailItem)
            }
        }

        modelItems.value = arr

    }


}
