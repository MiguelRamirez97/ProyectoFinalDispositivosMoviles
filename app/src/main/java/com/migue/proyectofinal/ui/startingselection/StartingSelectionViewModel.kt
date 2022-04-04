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

class StartingSelectionViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private val playerRepository = PlayerServerRepository()
    private val gameServerRespository = GameServerRespository()

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val foundGameFromServer: MutableLiveData<GameServer?> = MutableLiveData()
    val foundGameFromServerDone: LiveData<GameServer?> = foundGameFromServer
    private val searchPlayerFromServer: MutableLiveData<PlayerServer?> = MutableLiveData()
    val searchPlayerFromServerDone: LiveData<PlayerServer?> = searchPlayerFromServer
    private val waitingGameFromServer: MutableLiveData<GameServer?> = MutableLiveData()
    val waitingGameFromServerDone: LiveData<GameServer?> = waitingGameFromServer
    private val gameFoundFromServer: MutableLiveData<GameServer?> = MutableLiveData()
    val gameFoundFromServerDone: LiveData<GameServer?> = gameFoundFromServer

    //    fun playQuickGame() {
//        auth = Firebase.auth
//        GlobalScope.launch(Dispatchers.IO) {
//            var gameServer: GameServer? = findSyncGame()
//            val playerServer: PlayerServer? = findPlayer()
//            var gameServer2: GameServer? = null
//            if (gameServer != null && gameServer.uidPlayer1 != auth.currentUser?.uid) {
//                updateGame(gameServer, playerServer)
//            } else {
//                if (gameServer?.uidPlayer1 != auth.currentUser?.uid) {
//                    gameServer2 = findSyncGame()
//                    if (gameServer2 != null && gameServer2.uidPlayer1 == auth.currentUser?.uid) {
//                        waitingGameFromServer.postValue(gameServer2)
//                    }
//                } else {
//                    msg.postValue("Volveremos a buscar un oponente para tu partida.")
//                    if(gameServer?.namePlayer2 != null){
//                        waitingGameFromServer.postValue(gameServer)
//                    }
//                }
//            }
//        }
//    }
    fun playQuickGame() {
        GlobalScope.launch(Dispatchers.IO) {
            val gameServer: GameServer? = findSyncGame()
            val playerServer: PlayerServer? = findPlayer()
            if (gameServer != null) {
                if (gameServer.uidPlayer1 != playerServer?.uid) {
                    updateGame(gameServer, playerServer)
                } else {
                    msg.postValue("Volveremos a buscar un oponente para tu partida.")
                    waitingGameFromServer.postValue(gameServer)
                }
            } else {
                createGame(playerServer)
                var gameCreated = findSyncGame()
                msg.postValue("¡¡Se ha creado una partida, espera unos momentos a que otro jugador sé una a ella!!")
                waitingGameFromServer.postValue(gameCreated)            }
        }
    }


//    private suspend fun findGameAgain(uid: String?): GameServer? {
//        auth = Firebase.auth
//        val gameServer = uid?.let { gameServerRespository.findGameAgain(it) }
//        return if (gameServer?.isEmpty == false) {
//            gameServer?.documents?.first()?.toObject<GameServer>()
//        } else {
//            null
//        }
//    }

    private suspend fun updateGame(gameServer: GameServer, playerServer: PlayerServer?) {
        gameServerRespository.updateGame(gameServer, playerServer)
        var gameFound = gameServer.id?.let { gameServerRespository.findGameById(it) }
        if (gameFound?.isEmpty == true) {
            foundGameFromServer.postValue(null)
        } else {
            var gameFound1: GameServer? = gameFound?.first()?.toObject<GameServer>()
            if (gameFound1?.namePlayer2 != null) {
                foundGameFromServer.postValue(gameFound?.first()?.toObject<GameServer>())
            } else {
                foundGameFromServer.postValue(null)
            }
        }
    }

    private suspend fun createGame(playerServer: PlayerServer?) {
        gameServerRespository.saveQuickGame(playerServer?.uid, playerServer?.name)
    }

    private suspend fun findSyncGame(): GameServer? {
        auth = Firebase.auth
        val gameServer = gameServerRespository.findSyncQuickGame()
        return if (gameServer?.isEmpty == false) {
            gameServer?.documents?.first()?.toObject<GameServer>()
        } else {
            null
        }
    }

    private suspend fun findPlayer(): PlayerServer? {
        auth = Firebase.auth
        val playerServer = playerRepository.findPlayerInServer(auth.currentUser?.uid)
        return if (playerServer?.isEmpty == false) {
            playerServer?.documents?.first()?.toObject<PlayerServer>()
        } else {
            null
        }
    }

    fun playLocalGame() {
        auth = Firebase.auth
        GlobalScope.launch(Dispatchers.IO) {
            val playerServer: PlayerServer? = findPlayer()
            searchPlayerFromServer.postValue(playerServer)
        }
    }

    suspend fun findCurrentGame(idGame: String) {
        auth = Firebase.auth

            val gameServer = findCurrentGameInServer(idGame)
            if (gameServer != null) {
                gameFoundFromServer.postValue(gameServer)
            }

    }

    private suspend fun findCurrentGameInServer(idGame: String): GameServer? {
        var gameServer = gameServerRespository.findCurrentGame(idGame)
        msg.postValue(gameServer?.documents?.first()?.toObject<GameServer>()?.uidPlayer1.toString()
                + " separacion "
        +msg.postValue(gameServer?.documents?.first()?.toObject<GameServer>()?.uidPlayer2.toString()))
        return if (gameServer?.isEmpty == false) {
            if (gameServer.documents.first()
                    .toObject<GameServer>()?.uidPlayer2 != null
            ) {
                gameServer.documents?.first().toObject<GameServer>()
            } else {
                null
            }
        } else {
            null
        }
    }
}