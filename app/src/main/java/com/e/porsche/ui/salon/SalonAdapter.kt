package com.e.porsche.ui.salon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.e.porsche.R
import com.e.porsche.models.Model
import kotlinx.android.synthetic.main.item_salon.view.*

class SalonAdapter(val listener: SalonItemClickListener): RecyclerView.Adapter<SalonAdapter.SalonItemViewHolder>() {

    private lateinit var context: Context
    private val dataArray = ArrayList<Model>()

    fun replaceAll(arr: ArrayList<Model>) {
        dataArray.clear()
        if(arr.isNotEmpty()) {
            dataArray.addAll(arr)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalonItemViewHolder {
        this.context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_salon, parent, false)
        return SalonItemViewHolder(view)
    }

    override fun getItemCount(): Int { return dataArray.size }

    override fun onBindViewHolder(holder: SalonItemViewHolder, position: Int) {
        val item = dataArray[position]
        holder.image.setImageDrawable(ContextCompat.getDrawable(context, item.imageID))
        holder.name.text = item.name
        holder.itemView.setOnClickListener {
            listener.onItemClicked(item)
        }

//        if(!item.isAnimated) {
//            holder.card.animate()
//                .setDuration(1000L * position)
//                .alpha(1f)
//                .withEndAction { holder.card.isClickable = true }
//                .start()
//            item.isAnimated = true
//        } else {
//            holder.card.apply {
//                alpha = 1f
//                isClickable = true
//            }
//        }

    }

    class SalonItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image = v.iv_model
        val name = v.tv_model_name
//        val card = v.cv_salon
    }

    interface SalonItemClickListener {
        fun onItemClicked(model: Model)
    }
}
