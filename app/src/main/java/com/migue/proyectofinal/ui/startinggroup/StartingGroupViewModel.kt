package com.migue.proyectofinal.ui.startinggroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartingGroupViewModel : ViewModel() {
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg

    private val dataValidate: MutableLiveData<String?> = MutableLiveData()
    val dataValidated: LiveData<String?> = dataValidate

    fun revisarCodigo(codigo : String) {
        if(codigo.isEmpty()){
            dataValidate.value = null
            msg.value = "Debe ingresar el codigo de la partida"
        }else{
            dataValidate.value = ""
        }
    }
}