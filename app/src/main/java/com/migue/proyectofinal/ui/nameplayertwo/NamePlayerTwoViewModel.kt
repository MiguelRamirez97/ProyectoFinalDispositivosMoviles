package com.migue.proyectofinal.ui.nameplayertwo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NamePlayerTwoViewModel : ViewModel() {

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg

    private val dataValidate: MutableLiveData<String?> = MutableLiveData()
    val dataValidated: LiveData<String?> = dataValidate

    fun dataPlayer2(player2 : String) {
        if(player2.isEmpty()){
            dataValidate.value = null
            msg.value = "Debe ingresar nombre del segundo jugador"
        }else{
            msg.value = "Esta opción aún se encuentra en desarrollo"
        }
    }
}