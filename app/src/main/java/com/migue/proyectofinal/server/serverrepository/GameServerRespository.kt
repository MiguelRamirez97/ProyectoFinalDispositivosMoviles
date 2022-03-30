package com.migue.proyectofinal.server.serverrepository

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.GameServer
import com.migue.proyectofinal.server.PlayerServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class GameServerRespository {
    val db = Firebase.firestore
    suspend fun saveGame(uidPlayer: String?, namePlayer: String?) {
        val documentGame = db.collection("games").document()
        val game = GameServer(id = documentGame.id, uidPlayer1 = uidPlayer, namePlayer1 = namePlayer)
        db.collection("games")
            .document(documentGame.id)
            .set(game)
            .addOnSuccessListener {
                Log.d("Game", "Juego creado.")
            }
            .addOnFailureListener { e ->
                Log.w("Game", "Error creando el juego", e)
            }.await()
    }

    suspend fun findGame(): QuerySnapshot? {
        return withContext(Dispatchers.IO) {
            db.collection("games")
                .whereEqualTo("namePlayer2", null)
                .get()
                .await()
        }
    }

    suspend fun updateGame(gameServer: GameServer, playerServer: PlayerServer?) {
        db.collection("games")
            .document(gameServer.id.toString()).update(
                mapOf(
                    "uidPlayer2" to playerServer?.uid, "namePlayer2" to playerServer?.name
                )
            ).await()
    }

    suspend fun findGameById(id: String): QuerySnapshot {
        return withContext(Dispatchers.IO) {
            db.collection("games")
                .whereEqualTo("id", id)
                .get()
                .await()
        }
    }
}