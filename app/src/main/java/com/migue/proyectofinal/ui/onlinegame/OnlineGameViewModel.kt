package com.migue.proyectofinal.ui.onlinegame

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

class OnlineGameViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private val playerRepository = PlayerServerRepository()
    private var contador2: Int = 0
    private val counter: MutableLiveData<Int> = MutableLiveData()
    val counterDone: LiveData<Int> = counter
    private val searchPlayerFromServer: MutableLiveData<PlayerServer?> = MutableLiveData()
    val searchPlayerFromServerDone: LiveData<PlayerServer?> = searchPlayerFromServer

    fun animationButton(contador: Int) {
        contador2 = contador + 1
        counter.value = contador2
    }

    fun cargarDatos(){
        GlobalScope.launch(Dispatchers.IO) {
        findPlayer()
        }
    }

    suspend fun findPlayer() {
        auth = Firebase.auth
            val playerServer = playerRepository.findPlayerInServer(auth.currentUser?.uid)
            if (playerServer?.isEmpty == false) {
                searchPlayerFromServer.value =
                    playerServer?.documents?.first()?.toObject<PlayerServer>()
            } else {
                null
            }
    }
}