package com.e.porsche.ui.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import com.e.porsche.R
import com.e.porsche.managers.FieldsValidatorUtil
import com.e.porsche.managers.UserManager
import com.e.porsche.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.layout_empty_toolbar.view.*

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setSupportActionBar(toolbar_view_sign_in.toolbar, true, false)

        initView()
    }

    private fun initView(){
        btn_sign_in.setOnClickListener { signInPressed() }
        bt_forgot_pass.setOnClickListener { forgotPassPressed() }

        etv_sign_in_email.initBuilder(hintString = "Email",
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            imeOption = EditorInfo.IME_ACTION_NEXT)
        etv_sign_in_email?.onFocusChanged { hasFocus -> if(!hasFocus) checkEmail() }
        etv_sign_in_email?.onActionPressed { checkEmail() }

        etv_sign_in_password.initBuilder(hintString = "Kata sandi",
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD,
            imeOption = EditorInfo.IME_ACTION_DONE)
        etv_sign_in_password?.onFocusChanged { hasFocus -> if(!hasFocus) checkPass() }
        etv_sign_in_password?.onActionPressed { signInPressed() }
    }

    /*     Button Actions     */

    private fun signInPressed(){
        if(isFieldsValid()) {
            showProgressDialog()

            UserManager.getUpUser()
            hideProgressDialog()

            if(UserManager.currentUser != null) {
                setResultAndFinish()
            } else {
                onBackPressed()
            }
        }
    }

    private fun setResultAndFinish() {
        val intent = Intent()
        intent.putExtra("isSignedIn", true)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun forgotPassPressed(){

    }


    private fun isFieldsValid(): Boolean{
        var isValid = true

        if(checkEmail()) isValid = false
        if(checkPass()) isValid = false

        return  isValid
    }


    private fun checkEmail(): Boolean{
        etv_sign_in_email.setError(FieldsValidatorUtil.isEmailValid(etv_sign_in_email.text.toString(), this))
        return etv_sign_in_email.hasError
    }

    private fun checkPass(): Boolean{
        etv_sign_in_password.setError(FieldsValidatorUtil.isPassValid(etv_sign_in_password.text.toString(), this))
        return etv_sign_in_password.hasError
    }

}