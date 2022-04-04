package com.migue.proyectofinal.ui.nameplayertwo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.PlayerServer
import com.migue.proyectofinal.server.serverrepository.PlayerServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            dataValidate.value = player2
        }
    }


}