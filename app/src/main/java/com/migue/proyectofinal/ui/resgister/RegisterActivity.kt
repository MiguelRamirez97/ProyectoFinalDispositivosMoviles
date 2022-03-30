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

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        with(registerBinding) {

            registerViewModel.msgDone.observe(this@RegisterActivity) { result ->
                onMsgDoneSubscribe(result)
            }

            registerViewModel.dataValidated.observe(this@RegisterActivity) {
                onDataValidatedSubscribe()
            }



            registerButton.setOnClickListener {
                registerViewModel.validateFields(
                    emailEditText.text.toString(),
                    namePLayerEditText.text.toString(),
                    passwordEditText.text.toString(),
                    repPasswordEditText.text.toString(),
                    selectGenre()
                )
            }
        }
    }
    private fun selectGenre(): Int{
        var genre: Int
        with(registerBinding) {
             genre = when {
                maleRadioButton.isChecked -> 1
                femaleRadioButton.isChecked -> 0
                 else -> 2
             }
        }
        return genre
    }

    private fun onDataValidatedSubscribe() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun onMsgDoneSubscribe(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}