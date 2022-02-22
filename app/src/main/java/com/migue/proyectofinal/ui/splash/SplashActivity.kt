package com.migue.proyectofinal.ui.splash

import android.content.Intent
import androidx.navigation.fragment.findNavController
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.migue.proyectofinal.databinding.ActivitySplashBinding
import com.migue.proyectofinal.ui.login.LoginActivity
import com.migue.proyectofinal.ui.login.LoginFragment
import com.migue.proyectofinal.ui.login.LoginFragmentDirections
import com.migue.proyectofinal.ui.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

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
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}