package com.migue.proyectofinal

import android.app.Application
import androidx.room.Room
import com.migue.proyectofinal.local.PlayerDatabase

class ProyectoFinal : Application() {

    companion object{
        lateinit var database: PlayerDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            PlayerDatabase::class.java,
            "player_db"
        ).allowMainThreadQueries()
            .build()
    }

}