package com.migue.proyectofinal.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.migue.proyectofinal.ui.resgister.RegisterActivity
import com.migue.proyectofinal.databinding.ActivityLoginBinding
import com.migue.proyectofinal.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        var emailReceivedLogin: String? = ""
        var passwordReceivedLogin: String? = ""
        var namePlayerReceivedLogin: String? = ""

        val credentials = intent.extras

        if (credentials != null) {
            emailReceivedLogin = credentials.getString("email")
            passwordReceivedLogin = credentials.getString("password")
            namePlayerReceivedLogin = credentials.getString("name")
        }

        loginBinding.registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        with(loginBinding) {
            singInButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditTextLogin.text.toString()

                if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                    if (email == emailReceivedLogin && email.isNotEmpty()) {
                        if(password == passwordReceivedLogin && password.isNotEmpty()) {
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("email", emailReceivedLogin)
                            intent.putExtra("password", passwordReceivedLogin)
                            intent.putExtra("name", namePlayerReceivedLogin)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
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