package com.e.porsche.ui.modelDetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.porsche.R
import com.e.porsche.models.Car
import com.e.porsche.models.Model
import com.e.porsche.ui.BaseActivity
import com.e.porsche.ui.carDetail.CarDetailActivity
import com.e.porsche.ui.constructor.CarConstActivity
import kotlinx.android.synthetic.main.activity_model_details.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class ModelDetailsActivity : BaseActivity(), ModelDetailsAdapter.ModelDetailClickListener {

    companion object {
        private val MODEL_KEY = "Model_Key"

        @JvmStatic
        fun newInstance(context: Context, model: Model) : Intent{
            return Intent(context, ModelDetailsActivity::class.java).apply {
                putExtra(MODEL_KEY, model)
            }
        }
    }

    private lateinit var viewModel: ModelDetailsViewModel
    private lateinit var adapter: ModelDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_details)

        setSupportActionBar(toolbar_view_model_details.toolbar, true, true)

        adapter = ModelDetailsAdapter(this)
        rv_models_car.layoutManager = LinearLayoutManager(this)
        rv_models_car.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(ModelDetailsViewModel::class.java)
        viewModel.modelItems.observe(this, Observer {
            adapter.replaceAll(it)
        })
        (intent?.extras?.get(MODEL_KEY) as? Model)?.let {
            viewModel.loadItems(it)
            setTitle(toolbar_view_model_details.toolbar, it.name)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.salon_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.nav_const -> {
                startActivity(Intent(this@ModelDetailsActivity, CarConstActivity::class.java))
                this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onModelPressed(car: Car) {
        startActivity(CarDetailActivity.newInstance(this, car))
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}
