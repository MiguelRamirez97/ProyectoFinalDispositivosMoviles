package com.migue.proyectofinal.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.migue.proyectofinal.databinding.ActivitySplashBinding
import com.migue.proyectofinal.ui.login.LoginActivity
import java.util.*
import kotlin.concurrent.timerTask

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private  lateinit var  splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val timer = Timer()
        timer.schedule(
            timerTask {
            goToLoginActivity()
        },1500
        )
    }

    private fun goToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}