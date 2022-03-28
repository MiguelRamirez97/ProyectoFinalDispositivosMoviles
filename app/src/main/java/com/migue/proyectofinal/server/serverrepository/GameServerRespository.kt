package com.migue.proyectofinal.server.serverrepository

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.GameServer
import com.migue.proyectofinal.server.PlayerServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class GameServerRespository {
    val db = Firebase.firestore
    fun saveGame(id: String?, uidPlayer: String?, namePlayer: String?) {
        val game = GameServer(id = id, uidPlayer1 = uidPlayer, namePlayer1 = namePlayer)
        id?.let { id ->
            db.collection("games")
                .document(id)
                .set(game)
                .addOnSuccessListener { documentReference ->
                    Log.d("Game", "Juego creado.")
                }
                .addOnFailureListener { e ->
                    Log.w("Game", "Error creando el juego", e)
                }
        }
    }

    suspend fun findGame(): QuerySnapshot? {
        return withContext(Dispatchers.IO) {

            db.collection("games")
                .whereEqualTo("namePlayer2", null)
                .get()
                .await()

        }
    }
}