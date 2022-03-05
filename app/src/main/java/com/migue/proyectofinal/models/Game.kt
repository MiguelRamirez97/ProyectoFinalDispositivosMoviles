package com.migue.proyectofinal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types.NULL

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = NULL,
    @ColumnInfo(name = "namePlayer1")var namePlayer1: String = "",
    @ColumnInfo(name = "namePlayer2")var namePlayer2: String = "",
    @ColumnInfo(name = "scorePlayer1")var scorePlayer1: Int = 0,
    @ColumnInfo(name = "scorePlayer2")var scorePlayer2: Int = 0,
    @ColumnInfo(name = "idPlayer1")var idPlayer1: Int = 0,
    @ColumnInfo(name = "idPlayer2")var idPlayer2: Int = 0,
    @ColumnInfo(name = "winner")var winner: String = "",
    @ColumnInfo(name = "typeGame")var typeGame: String = ""
) : Serializable

