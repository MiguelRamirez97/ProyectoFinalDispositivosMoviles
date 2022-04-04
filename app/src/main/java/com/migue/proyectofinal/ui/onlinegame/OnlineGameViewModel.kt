package com.migue.proyectofinal.ui.onlinegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.GameServer
import com.migue.proyectofinal.server.serverrepository.GameServerRespository
import com.migue.proyectofinal.server.serverrepository.PlayerServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OnlineGameViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private var contador2: Int = 0
    private val counter: MutableLiveData<Int> = MutableLiveData()
    val counterDone: LiveData<Int> = counter
    private val searchPlayerFromServer: MutableLiveData<GameServer?> = MutableLiveData()
    val searchPlayerFromServerDone: LiveData<GameServer?> = searchPlayerFromServer
    private val gameServerRespository = GameServerRespository()

    fun animationButton(contador: Int) {
        contador2 = contador + 1
        counter.value = contador2
    }

    fun findUidPlayer(gameServer: GameServer): String {
        auth = Firebase.auth
        return if (gameServer.uidPlayer1?.equals(auth.currentUser?.uid) == true) {
            gameServer.uidPlayer1.toString()
        } else {
            gameServer.uidPlayer2.toString()
        }
    }

    fun updatefinishGame(gameServer: GameServer, uidPlayer: String, contador: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            gameServerRespository.updatefinishGame(
                gameServer,
                uidPlayer,
                contador
            )
            Thread.sleep(3000)
            findGameById(gameServer.id.toString())
        }
    }

    suspend fun findGameById(idGame: String) {
        searchPlayerFromServer.postValue(
            gameServerRespository.findGameById(idGame).first().toObject<GameServer>()
        )
    }

    fun saveWinner(winner: String, idGame: String) {
        gameServerRespository.saveWinner(winner, idGame)
    }
}