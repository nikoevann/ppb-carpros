package com.e.porsche.ui.constructor

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.porsche.R
import com.e.porsche.models.ConstructorColor
import com.e.porsche.ui.BaseActivity
import com.e.porsche.ui.carDetail.CarImagesAdapter
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_constructor.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class CarConstActivity : BaseActivity(), View.OnClickListener, CarConstAdapter.ColorListener{

    private var currentFilter: Filter = Filter.CONST_1
    private lateinit var imagesAdapter: CarImagesAdapter
    private lateinit var viewModel: CarConstViewModel
    private lateinit var constAdapter: CarConstAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constructor)

        setSupportActionBar(toolbar_view_constructor.toolbar, true, false)

        imagesAdapter = CarImagesAdapter()
        vp_car_const_images.adapter = imagesAdapter
        tl_images_const_indicators.setupWithViewPager(vp_car_const_images, true)

        constAdapter = CarConstAdapter(this)
        val layManager = LinearLayoutManager(this)
        rv_const_sett.layoutManager = layManager
        rv_const_sett.adapter = constAdapter
        val divider = DividerItemDecoration(rv_const_sett.context, layManager.orientation)
        rv_const_sett.addItemDecoration(divider)



        btn_const_1.setOnClickListener(this)
        btn_const_2.setOnClickListener(this)
        btn_const_3.setOnClickListener(this)
        btn_const_4.setOnClickListener(this)
        btn_const_5.setOnClickListener(this)

        viewModel = ViewModelProviders.of(this).get(CarConstViewModel::class.java)
        viewModel.apply {
            currentCarImages.observe(this@CarConstActivity, Observer {
                imagesAdapter.replaceAll(it)
            })
            constSett.observe(this@CarConstActivity, Observer {
                constAdapter.replaceAll(it)
            })
            loadData()
        }
    }

    override fun colorPressed(color: ConstructorColor) {
        viewModel.setCurrentCarColor(color)
    }

    override fun onClick(p0: View?) {
        resetDateButtons()

        (p0 as MaterialButton).let {
            it.setTextColor(ContextCompat.getColor(this, R.color.white))
            it.background.setTint(ContextCompat.getColor(this, R.color.blue))

            when (p0.id) {
                R.id.btn_const_1 -> {
                    currentFilter = Filter.CONST_1
                    rv_const_sett.scrollToPosition(0)
                }
                R.id.btn_const_2 -> {
                    currentFilter = Filter.CONST_2
                    rv_const_sett.scrollToPosition(2)
                }
                R.id.btn_const_3 -> {
                    currentFilter = Filter.CONST_3
                    rv_const_sett.scrollToPosition(3)
                }
                R.id.btn_const_4 -> {
                    currentFilter = Filter.CONST_4
                    rv_const_sett.scrollToPosition(6)
                }
                R.id.btn_const_5 -> {
                    currentFilter = Filter.CONST_5
                }
            }
        }
    }

    private fun resetDateButtons() {
        btn_const_1.setTextColor(ContextCompat.getColor(this, R.color.white))
        btn_const_2.setTextColor(ContextCompat.getColor(this, R.color.white))
        btn_const_3.setTextColor(ContextCompat.getColor(this, R.color.white))
        btn_const_4.setTextColor(ContextCompat.getColor(this, R.color.white))
        btn_const_5.setTextColor(ContextCompat.getColor(this, R.color.white))

        btn_const_1.background.setTint(ContextCompat.getColor(this, R.color.gray))
        btn_const_2.background.setTint(ContextCompat.getColor(this, R.color.gray))
        btn_const_3.background.setTint(ContextCompat.getColor(this, R.color.gray))
        btn_const_4.background.setTint(ContextCompat.getColor(this, R.color.gray))
        btn_const_5.background.setTint(ContextCompat.getColor(this, R.color.gray))
    }

    enum class Filter(val value: Int) {
        CONST_1(0),
        CONST_2(1),
        CONST_3(2),
        CONST_4(3),
        CONST_5(4)
    }
}
