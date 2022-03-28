package com.migue.proyectofinal.ui.startingselection

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.PlayerServer
import com.migue.proyectofinal.server.serverrepository.GameServerRespository
import com.migue.proyectofinal.server.serverrepository.PlayerServerRepository
import com.google.firebase.firestore.ktx.toObject
import com.migue.proyectofinal.server.GameServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartingSelectionViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth
    private val playerRepository = PlayerServerRepository()
    private val gameServerRespository = GameServerRespository()
    fun playGame() {
        GlobalScope.launch(Dispatchers.IO) {
            val gameServer : GameServer? = findGame()
            val playerServer: PlayerServer? = findPlayer()
            if (gameServer != null) {
                updateGame(gameServer,playerServer)
            }else{
                createGame(playerServer)
            }
        }
    }

    private fun updateGame(gameServer: GameServer, playerServer: PlayerServer?) {
        println("si funciona update")
    }

    private fun createGame(playerServer: PlayerServer?){
        println("si funciona create")
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