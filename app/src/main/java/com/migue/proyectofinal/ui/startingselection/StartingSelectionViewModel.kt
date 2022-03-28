package com.migue.proyectofinal.ui.startingselection

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.Game
import com.migue.proyectofinal.server.serverrepository.GameServerRepository
import com.migue.proyectofinal.server.serverrepository.PlayerServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StartingSelectionViewModel : ViewModel() {

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg
    private val gameServerRepository = GameServerRepository()
    private val playerServerRepository = PlayerServerRepository()
    private lateinit var auth: FirebaseAuth

    private val searchGameFromServer : MutableLiveData<Game?> = MutableLiveData()
    val searchGameFromServerDone: LiveData<Game?> = searchGameFromServer

    fun playGame(){
        GlobalScope.launch(Dispatchers.IO){
            searchGame()
        }
    }

    private suspend fun searchGame(){
        GlobalScope.launch(Dispatchers.IO){
            if(gameServerRepository.searchGame().isEmpty){
                searchGameFromServer.postValue(null)
            }else{
                searchGameFromServer.postValue(gameServerRepository.searchGame().first().toObject<Game>())
            }
        }
    }
}