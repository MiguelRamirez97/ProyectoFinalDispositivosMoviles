package com.migue.proyectofinal.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PlayerDao {

    @Insert
    fun save(player:Player)
}