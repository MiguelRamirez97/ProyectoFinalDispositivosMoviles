package com.migue.proyectofinal.server.serverrepository

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class GameServerRepository {
    val db = Firebase.firestore

    suspend fun searchGame() : QuerySnapshot {
        return withContext(Dispatchers.IO){
            db.collection("games").whereEqualTo("namePlayer2",null).get().await()
        }
    }
}