package com.migue.proyectofinal.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDao {

    @Insert
    fun save(player:Player)

    @Query("SELECT * FROM table_players WHERE email like :email AND password like :password")
    fun findPlayer(email: String, password: String) : Player
}