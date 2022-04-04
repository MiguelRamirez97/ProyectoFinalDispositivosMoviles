package com.migue.proyectofinal.ui.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObjects
import com.migue.proyectofinal.server.GameServer
import com.migue.proyectofinal.server.serverrepository.GameServerRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScoresViewModel : ViewModel() {

    private val gameServerRespository = GameServerRespository()
    private val loadGames : MutableLiveData<List<GameServer>> = MutableLiveData()
    val loadGamesDone : LiveData<List<GameServer>> = loadGames

    fun getGames() {
        GlobalScope.launch(Dispatchers.IO){
            val gamesList : QuerySnapshot = gameServerRespository.getGames()
            loadGames.postValue(gamesList.toObjects<GameServer>())
        }
    }
}