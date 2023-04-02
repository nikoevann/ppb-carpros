package com.e.porsche.ui.carDetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.e.porsche.R
import com.e.porsche.models.Car
import com.e.porsche.ui.BaseActivity
import com.e.porsche.ui.constructor.CarConstActivity
import kotlinx.android.synthetic.main.activity_car_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class CarDetailActivity : BaseActivity() {

    companion object {

        private val CAR_KEY = "Car_Key"
        @JvmStatic
        fun newInstance(context: Context, car: Car) : Intent {
            return Intent(context, CarDetailActivity::class.java).apply {
                putExtra(CAR_KEY, car)
            }
        }

    }

    private lateinit var adapter: CarImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        setSupportActionBar(toolbar_view_car_details.toolbar, true, true)

        adapter = CarImagesAdapter()
        vp_car_images.adapter = adapter
        tl_images_indicators.setupWithViewPager(vp_car_images, true)



        (intent?.extras?.get(CAR_KEY) as? Car)?.let {
            adapter.replaceAll(it.images)
            setTitle(toolbar_view_car_details.toolbar, it.name)
            tv_fuel_value.text = it.fuel
            tv_co2_value.text = it.co2
            tv_power_value.text = it.hp
            tv_wd_value.text = it.wd
            tv_zh_value.text = it.zh
            tv_speed_value.text = it.speed
            tv_price_value.text = it.price
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.salon_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.nav_const -> {
                startActivity(Intent(this@CarDetailActivity, CarConstActivity::class.java))
                this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
