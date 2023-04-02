package com.e.porsche.ui.salon

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.porsche.R
import com.e.porsche.models.Model
import com.e.porsche.ui.BaseFragment
import com.e.porsche.ui.constructor.CarConstActivity
import com.e.porsche.ui.modelDetails.ModelDetailsActivity
import kotlinx.android.synthetic.main.fragment_salon.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class SalonFragment : BaseFragment(), SalonAdapter.SalonItemClickListener{

    private lateinit var homeViewModel: SalonViewModel
    private lateinit var adapter: SalonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(SalonViewModel::class.java)
        return inflater.inflate(R.layout.fragment_salon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_view_salon.toolbar.inflateMenu(R.menu.salon_menu)
        toolbar_view_salon.toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.nav_const) {
                startActivity(Intent(context, CarConstActivity::class.java))
                activity?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            }
            false
        }
        toolbar_view_salon.toolbar.title = "Салон"

        this.adapter = SalonAdapter(this)
//        rv_models.layoutManager = GridLayoutManager(context, 2)
        rv_models.layoutManager = LinearLayoutManager(context)
        rv_models.adapter = adapter
        rv_models.setHasFixedSize(true)

        homeViewModel.carModelsLiveData.observe(this, Observer {
            adapter.replaceAll(it)
        })
        homeViewModel.loadCarModels()
    }

    override fun onItemClicked(model: Model) {
        if(model.name == "718" || model.name == "911") {
            startActivity(ModelDetailsActivity.newInstance(contextMain, model))
            activity?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        }
    }
}