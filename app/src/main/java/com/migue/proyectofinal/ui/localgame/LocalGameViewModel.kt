package com.migue.proyectofinal.ui.localgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.migue.proyectofinal.server.serverrepository.GameServerRespository

class LocalGameViewModel : ViewModel() {

    private var contadorPlayerOne2: Int = 0
    private var contadorPlayerTwo2: Int = 0
    private val counterPlayerOne: MutableLiveData<Int> = MutableLiveData()
    private val counterPlayerTwo: MutableLiveData<Int> = MutableLiveData()
    val counterPlayerOneDone: LiveData<Int> = counterPlayerOne
    val counterPlayerTwoDone: LiveData<Int> = counterPlayerTwo
    private val gameServerRespository = GameServerRespository()

    fun animationButtonPlayerOne(contador: Int) {
        contadorPlayerOne2 = contador + 1
        counterPlayerOne.value = contadorPlayerOne2
    }

    fun animationButtonPlayerTwo(contador: Int) {
        contadorPlayerTwo2 = contador + 1
        counterPlayerTwo.value = contadorPlayerTwo2
    }

    fun saveGame(namePlayerOne: String, uidPlayerOne: String, namePlayertwo: String, contadorPlayerOne: Int, contadorPlayerTwo: Int, winner: String) {
        gameServerRespository.saveLocalGame(namePlayerOne, uidPlayerOne, namePlayertwo, contadorPlayerOne, contadorPlayerTwo, winner)
    }
}