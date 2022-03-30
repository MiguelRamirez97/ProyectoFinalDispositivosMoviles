package com.migue.proyectofinal.server

import java.io.Serializable

data class PlayerServer(
    var uid: String? = null,
    var name: String? = null,
    var email: String? = null,
    var urlPicture: String? = null
): Serializable
