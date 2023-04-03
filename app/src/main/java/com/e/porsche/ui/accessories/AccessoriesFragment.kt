package com.e.porsche.ui.accessories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.porsche.R
import com.e.porsche.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_accessories.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class AccessoriesFragment : BaseFragment() {

    private lateinit var dashboardViewModel: AccessoriesViewModel
    private lateinit var adapter: AccessoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(AccessoriesViewModel::class.java)

        return inflater.inflate(R.layout.fragment_accessories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_view_accessories.toolbar.title = "Aksesoris"

        adapter = AccessoriesAdapter()
        rv_access.layoutManager = LinearLayoutManager(contextMain)
        rv_access.adapter = adapter


        dashboardViewModel.accessories.observe(this, Observer {
            adapter.replaceAll(it)
        })
        dashboardViewModel.loadAccess()
    }
}