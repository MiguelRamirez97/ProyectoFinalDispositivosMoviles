package com.migue.proyectofinal.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate
    private lateinit var auth: FirebaseAuth

    fun validateLogin(email: String, password: String) {
        auth = Firebase.auth
        if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
            if (email.isNotEmpty()) {
                if(password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("Login", "signInWithEmail:success")
                                dataValidate.value = true
                            } else {
                                Log.w("Login", "signInWithEmail:failure", task.exception)
                                msg.value = task.exception?.message.toString()
                            }
                        }
                } else {
                    msg.value = "La contraseña no puede estar vacia."
                }
            } else {
                msg.value = "El correo no puede estar vacio."
            }
        } else {
            msg.value = "Email invalido."
        }
    }

    fun recoveryPassword(email: String){
        auth = Firebase.auth
        if (email.isEmpty()){
            msg.value = "Ingrese su correo en el campo email."
        }else{
            auth.sendPasswordResetEmail(email)
            msg.value = "Revisa tu bandeja de entrada."
        }
    }
}