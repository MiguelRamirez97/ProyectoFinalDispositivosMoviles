package com.migue.proyectofinal.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.migue.proyectofinal.local.Player
import com.migue.proyectofinal.repository.PlayerRepository

class LoginViewModel : ViewModel() {

    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg
    private val playerRepository = PlayerRepository()

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate

    fun validateLogin(email: String, password: String) {
        if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
            if (email.isNotEmpty()) {
                if(password.isNotEmpty()) {
                    val player : Player = findPlayer(email,password)
                    if(player == null){
                        msg.value = "Credenciales incorrectas"
                    }else{
                        dataValidate.value = true
                    }
                } else {
                    msg.value = "La contraseña no puede estar vacia"
                }
            } else {
                msg.value = "El correo no puede estar vacio"
            }
        } else {
            msg.value = "Email invalido"
        }
    }

    fun findPlayer(email: String, password: String) : Player{
        return playerRepository.findPlayer(email,password)
    }


}