package com.migue.proyectofinal.models

import java.io.Serializable

data class Game(
    val namePlayer1: String,
    val namePlayer2: String,
    val socrePlayer1: String,
    val scorePlayer2: String,
    val winner: String,
    val typeGame: String
) : Serializable

