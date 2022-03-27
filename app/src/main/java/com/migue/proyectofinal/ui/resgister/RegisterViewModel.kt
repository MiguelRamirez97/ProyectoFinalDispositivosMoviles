package com.migue.proyectofinal.ui.resgister

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.repository.PlayerRepository

class RegisterViewModel : ViewModel() {

    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg
    private val playerRepository = PlayerRepository()

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate

    private lateinit var auth: FirebaseAuth

    fun validateFields(email: String, namePlayer: String, password: String, repPassword: String) {
        auth = Firebase.auth
        if(email.isNotEmpty() && namePlayer.isNotEmpty() && password.isNotEmpty() && repPassword.isNotEmpty()){
            if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                if(password == repPassword){
                    if(password.length > 5){
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener() { task ->
                                if (task.isSuccessful) {
                                    Log.d("Register", "createUserWithEmail:success")
                                    dataValidate.value = true
                                } else {
                                    Log.w("Register", "createUserWithEmail:failure", task.exception)
                                    msg.value = task.exception?.message.toString()
                                }
                            }
                    }else
                        msg.value = "La contraseña debe contener al menos 6 caracteres"
                }else
                    msg.value = "Las contraseñas deben coincidir"
            }else
                msg.value = "Email invalido"
        }else
            msg.value = "Debe llenar todos los campos"
    }

    fun savePlayer(email: String, namePlayer: String, password: String) {
        playerRepository.save(email, namePlayer, password)
    }
}