package com.migue.proyectofinal.server.serverrepository

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.PlayerServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PlayerServerRepository {
    val db = Firebase.firestore
    fun savePlayerInServer(uid: String?, email: String, namePlayer: String) {
        val player = PlayerServer(uid, namePlayer, email)
        uid?.let { uid ->
            db.collection("players")
                .document(uid)
                .set(player)
                .addOnSuccessListener { documentReference ->
                    Log.d("Registro", "Usuario agregado.")
                }
                .addOnFailureListener { e ->
                    Log.w("Registro", "Error agregando el usuario", e)
                }
        }
    }

    suspend fun findPlayerInServer(uid: String?): QuerySnapshot? {
        return withContext(Dispatchers.IO) {
            uid?.let { uid ->
                db.collection("players")
                    .whereEqualTo("uid", uid)
                    .get()
                    .await()
            }
        }
    }
}