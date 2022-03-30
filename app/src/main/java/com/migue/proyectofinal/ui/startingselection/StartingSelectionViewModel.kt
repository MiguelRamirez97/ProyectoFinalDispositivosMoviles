package com.migue.proyectofinal.ui.startingselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.serverrepository.PlayerServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.migue.proyectofinal.server.PlayerServer
import com.migue.proyectofinal.server.serverrepository.GameServerRespository
import com.migue.proyectofinal.server.GameServer
import java.util.*

class StartingSelectionViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private val playerRepository = PlayerServerRepository()
    private val gameServerRespository = GameServerRespository()

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg

    private val searchGameFromServer : MutableLiveData<GameServer?> = MutableLiveData()
    val searchGameFromServerDone: LiveData<GameServer?> = searchGameFromServer

    fun playGame() {
        GlobalScope.launch(Dispatchers.IO) {
            val gameServer : GameServer? = findGame()
            val playerServer: PlayerServer? = findPlayer()
            if (gameServer != null) {
                updateGame(gameServer,playerServer)
            }else{
                createGame(playerServer)
                msg.postValue("¡¡Se ha creado una partida, espera unos momentos a que otro jugador sé una a ella!!")
            }
        }
    }

    private suspend fun updateGame(gameServer: GameServer, playerServer: PlayerServer?) {
        gameServerRespository.updateGame(gameServer, playerServer)
        var gameFound = gameServer.id?.let { gameServerRespository.findGameById(it) }
        if(gameFound?.isEmpty == true){
                searchGameFromServer.postValue(null)
            }else{
                var gameFound1 : GameServer? = gameFound?.first()?.toObject<GameServer>()
                if(gameFound1?.namePlayer2 != null){
                    searchGameFromServer.postValue(gameFound?.first()?.toObject<GameServer>())
                }
                else{
                    searchGameFromServer.postValue(null)
                }
            }
    }

    private suspend fun createGame(playerServer: PlayerServer?){
        gameServerRespository.saveGame(playerServer?.uid,playerServer?.name)
    }

    private suspend fun findGame(): GameServer? {
        val gameServer = gameServerRespository.findGame()
        return if(gameServer?.isEmpty == false){
            gameServer?.documents?.first()?.toObject<GameServer>()
        }else{
            null
        }
    }

    private suspend fun findPlayer(): PlayerServer? {
        auth = Firebase.auth
        val playerServer = playerRepository.findPlayerInServer(auth.currentUser?.uid)
        return if(playerServer?.isEmpty == false){
            playerServer?.documents?.first()?.toObject<PlayerServer>()
        }else{
            null
        }
    }
}