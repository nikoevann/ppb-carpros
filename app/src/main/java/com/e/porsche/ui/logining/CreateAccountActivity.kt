package com.e.porsche.ui.logining

import android.app.Activity
import android.content.Intent
import kotlinx.android.synthetic.main.activity_logining.*

import android.os.Bundle
import com.e.porsche.R
import com.e.porsche.ui.BaseActivity
import com.e.porsche.ui.signin.SignInActivity
import com.e.porsche.ui.singup.SignUpActivity
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class CreateAccountActivity : BaseActivity() {

    var isCreated: Boolean = false
    private val RESULT_SIGN_IN = 156
    private val RESULT_SIGN_UP = 184


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logining)

        setSupportActionBar(toolbar_view_create_acc.toolbar, true, false)

        btn_signin.setOnClickListener {
            startActivityForResult(Intent(this, SignInActivity::class.java), RESULT_SIGN_IN)
            this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        }

        btn_signup.setOnClickListener {
            startActivityForResult(Intent(this, SignUpActivity::class.java), RESULT_SIGN_UP)
            this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            RESULT_SIGN_UP -> {
                if(resultCode == Activity.RESULT_OK) {
                    data?.getBooleanExtra("isSignedUp", false)?.let {
                        isCreated = true
                        onBackPressed()
                    }
                }
            }
            RESULT_SIGN_IN -> {
                data?.getBooleanExtra("isSignedIn", false)?.let {
                    isCreated = true
                    onBackPressed()
                }
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("isCreated", isCreated)
        setResult(Activity.RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }
}
