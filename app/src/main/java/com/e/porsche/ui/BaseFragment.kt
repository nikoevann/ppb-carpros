package com.e.porsche.ui

import androidx.fragment.app.Fragment

open class BaseFragment() : Fragment() {

    val contextMain: MainActivity
        get() { return activity as MainActivity }



}
