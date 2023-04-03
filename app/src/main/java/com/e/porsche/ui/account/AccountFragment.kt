package com.e.porsche.ui.account

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.e.porsche.R
import com.e.porsche.managers.UserManager
import com.e.porsche.ui.BaseFragment
import com.e.porsche.ui.MainActivity
import com.e.porsche.ui.carService.CarServiceActivity
import com.e.porsche.ui.myCars.MyCarsActivity
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.layout_account_item.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class AccountFragment : BaseFragment() {

    private lateinit var notificationsViewModel: AccountViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(AccountViewModel::class.java)
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eiv_profile_image.setSmallView()

        toolbar_view_account.toolbar.title = "Kantor"

        UserManager.currentUser?.let {
            eiv_profile_image.loadImage(it.getImageBitmap())
            tv_profile_name.text = it.name
            tv_profile_email.text = it.email
        }

        inc_setting_1.setOnClickListener {
            startActivity(Intent(context, MyCarsActivity::class.java))
            activity?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        }
        inc_setting_1.iv_setting_icon.setImageDrawable(ContextCompat.getDrawable(contextMain, R.drawable.ic_my_cars))
        inc_setting_1.tv_setting_title.text = "Koleksi Mobil"

        inc_setting_2.setOnClickListener {
            startActivity(Intent(context, CarServiceActivity::class.java))
            activity?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        }
        inc_setting_2.iv_setting_icon.setImageDrawable(ContextCompat.getDrawable(contextMain, R.drawable.ic_service))
        inc_setting_2.tv_setting_title.text = "Layanan"

        btn_logout.setOnClickListener {
            UserManager.removeCurrentUser()
            (activity as MainActivity).logoutPressed()}

        btn_logout.setOnLongClickListener {
            val arr = arrayOf("Keluar", "Hapus semua data")
            AlertDialog.Builder(contextMain)
                .setTitle("Masuk")
                .setItems(arr) {dialog, which ->
                    when(which) {
                        0 -> { btn_logout.performClick() }
                        1 -> {
                            UserManager.removeAllData()
                            (activity as MainActivity).logoutPressed()
                        }
                    }
                }
                .show()
            false
        }

    }

}