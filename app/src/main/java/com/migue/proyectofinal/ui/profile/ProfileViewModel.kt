package com.migue.proyectofinal.ui.profile

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

class ProfileViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private val playerRepository = PlayerServerRepository()

    private val searchProfileFromServer : MutableLiveData<PlayerServer?> = MutableLiveData()
    val searchProfileFromServerDone: LiveData<PlayerServer?> = searchProfileFromServer

    fun findPlayer() {
        GlobalScope.launch(Dispatchers.IO) {
            auth = Firebase.auth
            val playerServer = playerRepository.findPlayerInServer(auth.currentUser?.uid)
            if (playerServer?.isEmpty == false) {
                searchProfileFromServer.postValue(playerServer.documents.first()?.toObject<PlayerServer>())
            } else {
                searchProfileFromServer.postValue(null)
            }
        }
    }
}