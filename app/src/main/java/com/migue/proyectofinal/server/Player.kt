package com.migue.proyectofinal.server

import java.io.Serializable

data class Player(
    var uid: String? = null,
    var name: String? = null,
    var email: String? = null,
): Serializable
