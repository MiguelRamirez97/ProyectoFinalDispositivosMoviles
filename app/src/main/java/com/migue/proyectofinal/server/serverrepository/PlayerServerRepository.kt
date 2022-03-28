package com.migue.proyectofinal.server.serverrepository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.Player

class PlayerServerRepository {
    val db = Firebase.firestore
    fun savePlayerInServer(uid: String?, email: String, namePlayer: String){
        val player = Player(uid, namePlayer, email)
        uid?.let {uid ->
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
}