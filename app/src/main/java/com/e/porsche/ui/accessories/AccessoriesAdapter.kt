package com.e.porsche.ui.accessories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.e.porsche.R
import kotlinx.android.synthetic.main.item_accessories.view.*

class AccessoriesAdapter: RecyclerView.Adapter<AccessoriesAdapter.AccessoriesViewHolder>() {

    private lateinit var context: Context
    private val dataArray = ArrayList<Int>()

    fun replaceAll(arr: ArrayList<Int>) {
        dataArray.clear()
        if(arr.isNotEmpty()) {
            dataArray.addAll(arr)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccessoriesViewHolder {
        this.context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_accessories, parent, false)
        return AccessoriesViewHolder(view)
    }

    override fun getItemCount(): Int { return dataArray.size }

    override fun onBindViewHolder(holder: AccessoriesViewHolder, position: Int) {
        val item = dataArray[position]
        holder.image.setImageDrawable(ContextCompat.getDrawable(context, item))
    }

    class AccessoriesViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val image = v.iv_access
    }
}
