package com.migue.proyectofinal.ui.onlinegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnlineGameViewModel : ViewModel() {

    private var contador2: Int = 0
    private val counter: MutableLiveData<Int> = MutableLiveData()
    val counterDone: LiveData<Int> = counter

    fun animacionBoton(contador: Int) {
        contador2 = contador + 1
        counter.value = contador2
    }
}