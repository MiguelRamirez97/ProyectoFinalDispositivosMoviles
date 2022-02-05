package com.migue.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.migue.proyectofinal.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        with(registerBinding){
            registerButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val namePlayer = namePLayerEditText.text.toString()
                val password = passwordEditText.text.toString()
                val repPassword = repPasswordEditText.text.toString()

                if(email.isNotEmpty() && namePlayer.isNotEmpty() && password.isNotEmpty() && repPassword.isNotEmpty()){
                    if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                        if(password == repPassword){
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("email", email)
                            intent.putExtra("nombre", namePlayer)
                            intent.putExtra("password", password)
                            startActivity(intent)
                        }else
                            Toast.makeText(applicationContext, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
                    }else
                        Toast.makeText(applicationContext, "Email invalido", Toast.LENGTH_SHORT).show()
                }else
                    Toast.makeText(applicationContext, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}