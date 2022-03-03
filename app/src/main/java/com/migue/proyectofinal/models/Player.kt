package com.migue.proyectofinal.models

import java.io.Serializable

data class Player(
    val namePlayer: String,
    val emailPlayer: String,
    val password: String,
    val bestScore: String
) : Serializable
