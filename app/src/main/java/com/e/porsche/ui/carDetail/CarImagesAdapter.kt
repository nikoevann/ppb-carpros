package com.e.porsche.ui.carDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.e.porsche.R
import kotlinx.android.synthetic.main.item_car_image.view.*

class CarImagesAdapter() : PagerAdapter() {

    private lateinit var context: Context
    private val dataArray = ArrayList<Int>()

    fun replaceAll(arr: ArrayList<Int>) {
        dataArray.clear()
        if(arr.isNotEmpty()) { dataArray.addAll(arr) }
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        this.context = container.context
        val page = LayoutInflater.from(context).inflate(R.layout.item_car_image, container, false)
        val item = dataArray[position]
        page.iv_car_image.setImageDrawable(ContextCompat.getDrawable(context, item))
        container.addView(page)
        return page
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getCount(): Int { return dataArray.size }
}
