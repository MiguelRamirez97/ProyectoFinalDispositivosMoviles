package com.migue.proyectofinal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types.NULL

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = NULL,
    @ColumnInfo(name = "idPlayer")val idPlayer: Int = 0,
    @ColumnInfo(name = "player")val player: String = "",
    @ColumnInfo(name = "email")val email: String = "",
    @ColumnInfo(name = "score")val score: Int = 0
) :  Serializable
