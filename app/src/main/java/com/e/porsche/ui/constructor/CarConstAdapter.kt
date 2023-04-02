package com.e.porsche.ui.constructor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.e.porsche.R
import com.e.porsche.models.ConstructorColor
import com.e.porsche.ui.constructor.constModels.ConstCar
import com.e.porsche.ui.constructor.constModels.ConstColors
import com.e.porsche.ui.constructor.constModels.ConstSett
import com.e.porsche.ui.constructor.constModels.ConstSettType
import kotlinx.android.synthetic.main.item_const_color_list.view.*
import kotlinx.android.synthetic.main.item_const_colors.view.*
import kotlinx.android.synthetic.main.item_const_sett.view.*
import kotlinx.android.synthetic.main.item_const_sub_color.view.*
import kotlinx.android.synthetic.main.item_const_sub_sett.view.*
import kotlinx.android.synthetic.main.layout_acc_item_1.view.*

class CarConstAdapter(val colorListener : ColorListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private val dataArr = ArrayList<ConstCar>()

    private var openedID: Int? = null

    fun replaceAll(arr: ArrayList<ConstCar>) {
        dataArr.clear()
        if(arr.isNotEmpty()) {
            dataArr.addAll(arr)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return dataArr[position].type.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        return when(viewType) {
            ConstSettType.CONST_COLORS.value -> ConstColorsVH(LayoutInflater.from(context).inflate(R.layout.item_const_colors, parent, false))
            ConstSettType.CONST_SETT.value -> ConstSettVH(LayoutInflater.from(context).inflate(R.layout.item_const_sett, parent, false))
            else  -> ConstSettVH(LayoutInflater.from(context).inflate(R.layout.item_const_sett, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return dataArr.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataArr[position]
        when(item.type.value) {
            ConstSettType.CONST_COLORS.value -> {
                val newItem = item as ConstColors
                val settHolder = holder as ConstColorsVH
                settHolder.sett_icon.visibility = View.GONE
                settHolder.title.text = newItem.title


                if(newItem.mainTitle != null) {
                    settHolder.mainTitleContainer.visibility = View.VISIBLE
                    settHolder.mainTitle.text = newItem.mainTitle

                    val layParam = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        layParam.setMargins(0, context.resources.getDimension(R.dimen.card_const_margin_top).toInt(),0,0)
                        settHolder.card.layoutParams = layParam

                } else {
                    settHolder.mainTitleContainer.visibility = View.GONE
                }

                settHolder.hidden_view.removeAllViews()
                for(sub in newItem.colors) {
                    val v = LayoutInflater.from(context)
                        .inflate(R.layout.item_const_color_list, null, false)

//                    v.sub_check
//                    v.sub_info

                    v.tv_color_type_title.text = sub.title
                    v.inc_color_1.siv_color.background.setTint(ContextCompat.getColor(context, sub.c1.color))
                    v.inc_color_2.siv_color.background.setTint(ContextCompat.getColor(context, sub.c2.color))
                    v.inc_color_3.siv_color.background.setTint(ContextCompat.getColor(context, sub.c3.color))
                    v.inc_color_4.siv_color.background.setTint(ContextCompat.getColor(context, sub.c4.color))

                    v.inc_color_1.siv_color.setOnClickListener { colorListener.colorPressed(sub.c1) }
                    v.inc_color_2.siv_color.setOnClickListener { colorListener.colorPressed(sub.c2) }
                    v.inc_color_3.siv_color.setOnClickListener { colorListener.colorPressed(sub.c3) }
                    v.inc_color_4.siv_color.setOnClickListener { colorListener.colorPressed(sub.c4) }

                    settHolder.hidden_view.addView(v)
                }


                settHolder.arrow.setOnClickListener {
                    settHolder.arrow.isClickable = false
                    if(newItem.isOpened) {
                        newItem.isOpened = false
                        settHolder.arrow.animate()
                            .rotationBy(180f)
                            .setDuration(250L)
                            .withEndAction {
                                settHolder.hidden_view.visibility = View.GONE
                                settHolder.arrow.isClickable = true
                            }
                            .start()
                    } else {
                        newItem.isOpened = true
                        settHolder.arrow.animate()
                            .rotationBy(-180f)
                            .setDuration(250L)
                            .withEndAction {
                                settHolder.hidden_view.visibility = View.VISIBLE
                                settHolder.arrow.isClickable = true
                            }
                            .start()
                    }
                }
            }
            ConstSettType.CONST_SETT.value -> {
                val newItem = item as ConstSett
                val settHolder = holder as ConstSettVH
                settHolder.sett_icon.visibility = View.GONE
                settHolder.title.text = newItem.title

                if(newItem.mainTitle != null) {
                    settHolder.mainTitleContainer.visibility = View.VISIBLE
                    settHolder.mainTitle.text = newItem.mainTitle

                    val layParam = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    layParam.setMargins(0, context.resources.getDimension(R.dimen.card_const_margin_top).toInt(),0,0)
                    settHolder.card.layoutParams = layParam
                } else {
                    settHolder.mainTitleContainer.visibility = View.GONE
                }
                settHolder.hidden_view.removeAllViews()
                for(sub in newItem.subsett) {
                    val v = LayoutInflater.from(context).inflate(R.layout.item_const_sub_sett, null, false)
//                    v.sub_check
//                    v.sub_info


                    v.sub_title.text = sub.title
                    v.sub_price.text = sub.price

                    settHolder.hidden_view.addView(v)
                }

                settHolder.arrow.setOnClickListener {
                    settHolder.arrow.isClickable = false
                    if(newItem.isOpened) {
                        newItem.isOpened = false
                        settHolder.arrow.animate()
                            .rotationBy(180f)
                            .setDuration(250L)
                            .withEndAction {
                                settHolder.hidden_view.visibility = View.GONE
                                settHolder.arrow.isClickable = true
                            }
                            .start()
                    } else {
                        newItem.isOpened = true
                        settHolder.arrow.animate()
                            .rotationBy(-180f)
                            .setDuration(250L)
                            .withEndAction {
                                settHolder.hidden_view.visibility = View.VISIBLE
                                settHolder.arrow.isClickable = true
                            }
                            .start()
                    }
                }
            }
        }
    }


    class ConstColorsVH(v: View) : RecyclerView.ViewHolder(v) {
        val card = v.card_const_color
        val hidden_view = v.ll_hiden_color_view
        val sett_icon = v.iv_setting_icon
        val title = v.tv_setting_title
        val arrow = v.iv_setting_arrow
        val colorSelected = v.tv_color_selected
        val colorSelectedInfo = v.iv_color_info
        val colorSelectedPrice = v.tv_color_price
        val mainTitleContainer = v.ll_color_main_title
        val mainTitle = v.tv_color_setting_title

    }

    class ConstSettVH(v: View) : RecyclerView.ViewHolder(v) {
        val card = v.card_const_sett
        val hidden_view = v.ll_hiden_view
        val sett_icon = v.iv_setting_icon
        val title = v.tv_setting_title
        val arrow = v.iv_setting_arrow
        val mainTitleContainer = v.ll_sett_main_title
        val mainTitle = v.tv_sett_setting_title
    }

    interface ColorListener {
        fun colorPressed(color: ConstructorColor)
    }


}
