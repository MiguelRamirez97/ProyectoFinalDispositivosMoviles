package com.migue.proyectofinal.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.migue.proyectofinal.databinding.ActivityLoginBinding
import com.migue.proyectofinal.ui.bottom.BottomActivity
import com.migue.proyectofinal.ui.main.MainActivity
import com.migue.proyectofinal.ui.resgister.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginBinding.registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        with(loginBinding) {
            singInButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditTextLogin.text.toString()

                if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                    if (email.isNotEmpty()) {//if (email == emailReceivedLogin && email.isNotEmpty()) {
                        if(password.isNotEmpty()) {//if(password == passwordReceivedLogin && password.isNotEmpty()) {
                            val intent = Intent(this@LoginActivity, BottomActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "La contraseña es incorrecta.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "La cuenta no se encuentra registrada.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Email invalido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}