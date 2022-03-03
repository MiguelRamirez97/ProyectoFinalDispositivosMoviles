package com.migue.proyectofinal.models

import java.io.Serializable

data class Record(
    val player: String,
    val email: String,
    val score: String
) : Serializable
