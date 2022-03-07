package com.migue.proyectofinal.repository

import com.migue.proyectofinal.ProyectoFinal
import com.migue.proyectofinal.local.Player
import com.migue.proyectofinal.local.PlayerDao

import java.sql.Types.NULL

class PlayerRepository {
    fun save(email: String, namePlayer: String, password: String) {
        val player = Player(
            id = NULL,
            name = namePlayer,
            email = email,
            password = password
        )

        val playerDao : PlayerDao = ProyectoFinal.database.PlayerDao()
        playerDao.save(player)
    }

    fun findPlayer(email: String,password: String) : Player{
        val playerDao : PlayerDao = ProyectoFinal.database.PlayerDao()
        val player = playerDao.findPlayer(email,password)
        return player
    }
}