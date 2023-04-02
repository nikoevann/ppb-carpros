package com.e.porsche.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.e.porsche.R

abstract class BaseActivity: AppCompatActivity() {

    private lateinit var progressDialog: AlertDialog

    fun setSupportActionBar(toolbar: Toolbar, isHomeAsUpEnebled: Boolean, showTitle: Boolean){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isHomeAsUpEnebled)
        supportActionBar?.setDisplayShowTitleEnabled(showTitle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.progressDialog = AlertDialog.Builder(this)
            .setView(R.layout.dialog_progress)
            .setCancelable(false)
            .create()
    }

    fun setTitle(toolbar: Toolbar, title: String) {
        toolbar.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> { this.onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showProgressDialog(){ progressDialog.show() }
    fun hideProgressDialog(){ progressDialog.dismiss() }


    fun hideSoftKeyboard(activity: Activity, view: View) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}
