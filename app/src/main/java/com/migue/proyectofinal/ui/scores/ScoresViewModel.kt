package com.migue.proyectofinal.ui.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.GameServer
import com.migue.proyectofinal.server.PlayerServer
import com.migue.proyectofinal.server.serverrepository.GameServerRespository
import com.migue.proyectofinal.server.serverrepository.PlayerServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScoresViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private val playerRepository = PlayerServerRepository()
    private val gameServerRespository = GameServerRespository()
    private val loadGames : MutableLiveData<ArrayList<GameServer>> = MutableLiveData()
    val loadGamesDone : LiveData<ArrayList<GameServer>> = loadGames
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg

    fun getGames() {
        GlobalScope.launch(Dispatchers.IO){
            val playerServer: PlayerServer? = findPlayer()
            val gamesList : QuerySnapshot? = gameServerRespository.getGamesUidPlayerOne(playerServer?.uid.toString())
            if(gamesList?.isEmpty == true){
                val gamesList2 : QuerySnapshot? = gameServerRespository.getGamesUidPlayerTwo(playerServer?.uid.toString())
                if(gamesList2?.isEmpty == true){
                    msg.postValue("Usted no tiene historial de partidas")
                }else{
                    loadGames.postValue(gamesList2?.toObjects<GameServer>() as ArrayList<GameServer>)
                }
            }else{
                val gamesList2 : QuerySnapshot? = gameServerRespository.getGamesUidPlayerTwo(playerServer?.uid.toString())
                if(gamesList2?.isEmpty == true){
                    loadGames.postValue(gamesList?.toObjects<GameServer>() as ArrayList<GameServer>)
                }
                val gamesArrayList = gamesList?.toObjects<GameServer>() as ArrayList<GameServer>
                val gamesArrayList2 = gamesList2?.toObjects<GameServer>() as ArrayList<GameServer>
                gamesArrayList.addAll(gamesArrayList2)
                loadGames.postValue(gamesArrayList)
            }
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