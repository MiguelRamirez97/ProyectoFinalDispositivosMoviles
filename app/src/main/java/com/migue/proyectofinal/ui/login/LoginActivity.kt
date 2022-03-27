package com.migue.proyectofinal.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.migue.proyectofinal.databinding.ActivityLoginBinding
import com.migue.proyectofinal.ui.bottom.BottomActivity
import com.migue.proyectofinal.ui.resgister.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginBinding.registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        with(loginBinding) {

            loginViewModel.msgDone.observe(this@LoginActivity) { result ->
                onMsgDondeSubscribe(result)
            }

            loginViewModel.dataValidated.observe(this@LoginActivity) {
                onDataValidatedSubscribe()
            }

            singInButton.setOnClickListener {
                loginViewModel.validateLogin(
                    emailEditText.text.toString(),
                    passwordEditTextLogin.text.toString()
                )
            }
        }
    }

    private fun onDataValidatedSubscribe() {
            val intent = Intent(this@LoginActivity, BottomActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
    }

    private fun onMsgDondeSubscribe(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}
