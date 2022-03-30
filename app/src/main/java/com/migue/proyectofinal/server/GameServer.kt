package com.migue.proyectofinal.server

import java.io.Serializable
data class GameServer(
    var id: String? = null,
    var namePlayer1: String? = null,
    var namePlayer2: String? = null,
    var scorePlayer1: Int? = null,
    var scorePlayer2: Int? = null,
    var uidPlayer1: String? = null,
    var uidPlayer2: String? = null,
    var winner: String? = null,
    var typeGame: String? = null
): Serializable

