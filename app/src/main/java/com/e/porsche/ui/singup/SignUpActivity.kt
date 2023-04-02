package com.e.porsche.ui.singup

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.view.inputmethod.EditorInfo
import com.e.porsche.R
import com.e.porsche.managers.*
import com.e.porsche.models.CurrentUser
import com.e.porsche.ui.BaseActivity
import com.e.porsche.views.edit_image_view.EditImageDialogBaseClickListener
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.layout_empty_toolbar.view.*
import java.io.ByteArrayOutputStream

class SignUpActivity : BaseActivity() {

    private val RESULT_CAMERA = 15
    private val RESULT_GALARY = 25

    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setSupportActionBar(this.toolbar_view_sign_up.toolbar, true, false)
                initView()
    }

    private fun initView(){
        btn_sing_up.setOnClickListener { signUpPressed() }
        eiv_sign_up_avatar.setBigView()
//        eiv_sign_up_avatar.setAddImageView()
        eiv_sign_up_avatar.setDialogBaseCliclListener(object : EditImageDialogBaseClickListener {
            override fun onGalaryPressed() {
                if(Injector.permissionManager.isPermissionForGalleryGranted(this@SignUpActivity)) {
                    openGallery()
                }
            }

            override fun onCameraPressed() {
                if(Injector.permissionManager.isPermissionCameraWriteStorageGranted(this@SignUpActivity)) {
                    openCamera()
                }
            }
        })

        etv_sign_up_name.initBuilder(hintString = "Iм'я",
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_CLASS_TEXT,
            imeOption = EditorInfo.IME_ACTION_NEXT)
        etv_sign_up_name?.onFocusChanged { hasFocus -> if(!hasFocus) checkUserName() }
        etv_sign_up_name?.onActionPressed { checkUserName() }

        etv_sign_up_email.initBuilder(hintString = "Поштова адреса",
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            imeOption = EditorInfo.IME_ACTION_NEXT)
        etv_sign_up_email?.onFocusChanged { hasFocus -> if(!hasFocus) checkEmail() }
        etv_sign_up_email?.onActionPressed { checkEmail() }

        etv_sign_up_password.initBuilder(hintString = "Пароль",
            colorRes = R.color.gray,
            dimenRes = R.dimen.edit_text_view_radius,
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD,
            imeOption = EditorInfo.IME_ACTION_DONE)
        etv_sign_up_password?.onFocusChanged { hasFocus -> if(!hasFocus) checkPass() }
        etv_sign_up_password?.onActionPressed { signUpPressed() }
    }


    private fun signUpPressed(){
        if(isFieldsValid()) {
            showProgressDialog()

            UserManager.setUpUser(CurrentUser(
                etv_sign_up_email.text!!,
                etv_sign_up_name.text!!,
                etv_sign_up_password.text!!,
                Injector.imageManager.encodeToBase64(eiv_sign_up_avatar.getImage())
            ))
            setResultAndFinish()

        }
    }

    private fun setResultAndFinish() {
        val intent = Intent()
        intent.putExtra("isSignedUp", true)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RESULT_GALARY -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data?.let {
                        val contentURI = it
                        startCropperActivity(contentURI)
                    }
                }
            }

            RESULT_CAMERA -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.extras?.let {
                        val bitmap = it.get("data") as Bitmap
                        val uri = getBitmapUri(bitmap, contentResolver)
                        startCropperActivity(uri)
                    }
                }
            }

            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                data?.let {
                    val result: CropImage.ActivityResult = CropImage.getActivityResult(data);
                    if (resultCode == Activity.RESULT_OK) {
                        val resultUri = result.getUri();
                        setImage(resultUri)

                    } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                        //Timber.i("Crop image error = ${result.error}")
                    }else{}
                }
            }

        }
    }

    private fun setImage(uri: Uri){
        this.uri = uri
        eiv_sign_up_avatar.loadImage(uri)
    }

    private fun startCropperActivity(uri: Uri) {
        CropImage.activity(uri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1, 1)
            .start(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionManager.PERMISSION_CAMERA,
            PermissionManager.PERMISSION_STORAGE_FOR_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    // MessageUtil.showToast("Permission for camera is not allowed")
                }
            }
            PermissionManager.PERMISSION_STORAGE_FOR_GALLERY -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    //MessageUtil.showToast(getString(R.string.perrmission_for_galary))
                }
            }
        }
    }

    private fun openGallery() {
        if(!Injector.permissionManager.isPermissionForGalleryGranted(this)) return

        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, RESULT_GALARY)
            }
        }
    }

    private fun openCamera() {
        if(!Injector.permissionManager.isPermissionCameraWriteStorageGranted(this)) return

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, RESULT_CAMERA)
            }
        }
    }


    private fun isFieldsValid(): Boolean{
        var isValid = true

        if(checkUserName()) isValid = false
        if(checkEmail()) isValid = false
        if(checkPass()) isValid = false

        return  isValid
    }

    private fun checkUserName(): Boolean{
        etv_sign_up_name.setError(FieldsValidatorUtil.isNameValid(etv_sign_up_name.text.toString(), this))
        return etv_sign_up_name.hasError
    }

    private fun checkEmail(): Boolean{
        etv_sign_up_email.setError(FieldsValidatorUtil.isEmailValid(etv_sign_up_email.text.toString(), this))
        return etv_sign_up_email.hasError
    }

    private fun checkPass(): Boolean{
        etv_sign_up_password.setError(FieldsValidatorUtil.isPassValid(etv_sign_up_password.text.toString(), this))
        return etv_sign_up_password.hasError
    }

    fun getBitmapUri(bitmap: Bitmap, contentResolver: ContentResolver): Uri {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteArrayOutputStream());
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "itemPhoto", null);
        return  Uri.parse(path)
    }
}
