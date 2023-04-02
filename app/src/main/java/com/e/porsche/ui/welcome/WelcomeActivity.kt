package com.e.porsche.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.e.porsche.ui.MainActivity

class WelcomeActivity : AppCompatActivity() {

    private val handler = Handler()
    private val splashRunnable = Runnable { startActivity() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_welcome)
        performDelayedStartActivity()
    }

    private fun startActivity(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(splashRunnable)
    }

    private fun performDelayedStartActivity() {
        handler.postDelayed(splashRunnable, MIN_SPLASH_TIME)
    }

    companion object {
        private const val MIN_SPLASH_TIME = 1000L
    }
}
