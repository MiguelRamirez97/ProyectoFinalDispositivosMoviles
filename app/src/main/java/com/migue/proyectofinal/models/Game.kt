package com.migue.proyectofinal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types.NULL

@Entity(tableName = "table_games")
data class Game(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "namePlayer1")val namePlayer1: String,
    @ColumnInfo(name = "namePlayer2")val namePlayer2: String,
    @ColumnInfo(name = "scorePlayer1")val scorePlayer1: Int,
    @ColumnInfo(name = "scorePlayer2")val scorePlayer2: Int,
    @ColumnInfo(name = "idPlayer1")val idPlayer1: Int,
    @ColumnInfo(name = "idPlayer2")val idPlayer2: Int,
    @ColumnInfo(name = "winner")val winner: String,
    @ColumnInfo(name = "typeGame")val typeGame: String
) : Serializable{
    constructor() : this(NULL,"","",0,0,NULL,NULL,"","")
}

