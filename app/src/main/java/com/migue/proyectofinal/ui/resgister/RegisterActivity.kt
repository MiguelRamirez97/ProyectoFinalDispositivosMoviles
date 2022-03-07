package com.migue.proyectofinal.ui.resgister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.migue.proyectofinal.databinding.ActivityRegisterBinding
import com.migue.proyectofinal.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        with(registerBinding){

            registerViewModel.msgDone.observe(this@RegisterActivity, {result ->
                onMsgDoneSubscribe(result)
            })

            registerViewModel.dataValidated.observe(this@RegisterActivity, {result ->
                onDataValidatedSubscribe(result)
            })

            registerButton.setOnClickListener {
                registerViewModel.validateFields(emailEditText.text.toString(), namePLayerEditText.text.toString(),passwordEditText.text.toString(), repPasswordEditText.text.toString())
            }
        }
    }

    private fun onDataValidatedSubscribe(result: Boolean) {
        with(registerBinding) {
            val email = emailEditText.text.toString()
            val namePlayer = namePLayerEditText.text.toString()
            val password = passwordEditText.text.toString()
            val repPassword = repPasswordEditText.text.toString()

            //guardar en base de datos
            registerViewModel.savePlayer(email, namePlayer, password)

            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun onMsgDoneSubscribe(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}