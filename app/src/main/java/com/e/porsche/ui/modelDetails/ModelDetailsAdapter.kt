package com.e.porsche.ui.modelDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.e.porsche.R
import com.e.porsche.models.Car
import com.e.porsche.models.ModelDetailItem
import com.e.porsche.models.ModelDetailItemType
import kotlinx.android.synthetic.main.item_model_cars.view.*
import kotlinx.android.synthetic.main.item_model_title.view.*

class ModelDetailsAdapter(val listener: ModelDetailClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private val dataArray = ArrayList<ModelDetailItem>()

    fun replaceAll(arr: ArrayList<ModelDetailItem>) {
        dataArray.clear()
        if(arr.isNotEmpty()) {
            dataArray.addAll(arr)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return dataArray[position].type.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        val viewHolder: RecyclerView.ViewHolder
        if(viewType == ModelDetailItemType.TYPE_CARS.value) {
            viewHolder = ModelCars(LayoutInflater.from(context).inflate(R.layout.item_model_cars, parent, false))
        } else {
            viewHolder = ModelTitle(LayoutInflater.from(context).inflate(R.layout.item_model_title, parent, false))
        }

        return viewHolder
    }

    override fun getItemCount(): Int { return dataArray.size }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataArray[position]
        when(item.type) {
            ModelDetailItemType.TYPE_CARS -> {
                if(item.car1 != null) {
                    (holder as ModelCars).card1.visibility = View.VISIBLE
                    (holder as ModelCars).image1.setImageDrawable(ContextCompat.getDrawable(context, item.car1!!.image))
                    (holder as ModelCars).name1.text = item.car1!!.name
                    (holder as ModelCars).price1.text = item.car1!!.price
                } else {
                    (holder as ModelCars).card1.visibility = View.INVISIBLE
                }

                if(item.car2 != null) {
                    (holder as ModelCars).card2.visibility = View.VISIBLE
                    (holder as ModelCars).image2.setImageDrawable(ContextCompat.getDrawable(context, item.car2!!.image))
                    (holder as ModelCars).name2.text = item.car2!!.name
                    (holder as ModelCars).price2.text = item.car2!!.price
                } else {
                    (holder as ModelCars).card2.visibility = View.INVISIBLE
                }
                holder.card1.setOnClickListener { item.car1?.let { listener.onModelPressed(it) } }
                holder.card2.setOnClickListener { item.car2?.let{ listener.onModelPressed(it) } }
            }

            ModelDetailItemType.TYPE_TITLE -> {
                (holder as ModelTitle).title.text = item.title
            }
        }
    }

    class ModelTitle(v: View) : RecyclerView.ViewHolder(v) {
        val title = v.tv_model_title
    }
    class ModelCars(v: View) : RecyclerView.ViewHolder(v) {
        val card1 = v.cv_car1
        val card2 = v.cv_car2
        val name1 = v.tv_name1
        val name2 = v.tv_name2
        val price1 = v.tv_price1
        val price2 = v.tv_price2
        val image1 = v.iv_image1
        val image2 = v.iv_image2
    }

    interface ModelDetailClickListener {
        fun onModelPressed(car: Car)
    }
}
