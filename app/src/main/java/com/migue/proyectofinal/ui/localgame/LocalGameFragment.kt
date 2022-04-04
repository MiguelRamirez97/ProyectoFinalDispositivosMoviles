package com.migue.proyectofinal.ui.localgame

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.migue.proyectofinal.databinding.FragmentLocalGamesBinding
import kotlin.Int
import kotlin.Long
import kotlin.also
import kotlin.getValue
import kotlin.with


class LocalGameFragment : Fragment() {

    private var contadorPlayerOne = 0
    private var contadorPlayerTwo = 0
    private lateinit var localGameBinding: FragmentLocalGamesBinding
    private lateinit var localGameViewModel: LocalGameViewModel
    private val args: LocalGameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        localGameBinding = FragmentLocalGamesBinding.inflate(inflater, container, false)
        localGameViewModel = ViewModelProvider(this)[LocalGameViewModel::class.java]
        return localGameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(localGameBinding) {
            object : CountDownTimer(5000, 1) {
                override fun onTick(millisUntilFinished: Long) {
                    ("golpea a: " + args.namePlayer2).also { buttonPlayerOne.text = it }
                    ("golpea a: " + args.player1.name).also { buttonPlayerTwo.text = it }
                    ("Puntuación: $contadorPlayerOne").also { textViewPlayerOne.text = it }
                    ("Puntuación: $contadorPlayerTwo").also { textViewPlayerTwo.text = it }

                    buttonPlayerOne.setOnClickListener {
                        localGameViewModel.animationButtonPlayerOne(contadorPlayerOne)
                    }

                    buttonPlayerTwo.setOnClickListener {
                        localGameViewModel.animationButtonPlayerTwo(contadorPlayerTwo)
                    }

                    localGameViewModel.counterPlayerOneDone.observe(viewLifecycleOwner) { result ->
                        onCounterDoneSubscribePlayerOne(result)
                    }

                    localGameViewModel.counterPlayerTwoDone.observe(viewLifecycleOwner) { result ->
                        onCounterDoneSubscribePlayerTwo(result)
                    }
                }
                override fun onFinish() {
                    finalizarPartida(contadorPlayerOne,contadorPlayerTwo)
                }
            }.start()
        }
    }

    private fun finalizarPartida(contadorPlayerOne: Int, contadorPlayerTwo: Int) {
        var winner = ""
        if(contadorPlayerOne > contadorPlayerTwo){
            winner = args.player1.name.toString()
        }else{
            winner = args.namePlayer2
        }

        localGameViewModel.saveGame(args.player1.name.toString(), args.player1.uid.toString(), args.namePlayer2,contadorPlayerOne, contadorPlayerTwo, winner)

        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Partida Terminada")
            setMessage(args.player1.name + " obtuvo " + contadorPlayerOne + " puntos.\n" +
                    args.namePlayer2 + " obtuvo " + contadorPlayerTwo + " puntos.\n" +
            "El ganador es: " + winner)
            setPositiveButton("OK"){ dialogInterface: DialogInterface, i: Int -> }
            setOnDismissListener{findNavController().navigate(LocalGameFragmentDirections.actionLocalGameFragmentToStartingSelectionFragment())}
            setCancelable(false)
            show()
        }
    }

    private fun onCounterDoneSubscribePlayerOne(result: Int) {
        with(localGameBinding) {
            contadorPlayerOne = result
            "Puntuación: $result".also { textViewPlayerOne.text = it }
            if (contadorPlayerOne % 2 == 0) {
                imagePlayerOne.setImageResource(com.migue.proyectofinal.R.drawable.batman_robin_cachetada_alta_web_590x340)
            } else {
                imagePlayerOne.setImageResource(com.migue.proyectofinal.R.drawable.batman_robin_girada)
            }
        }
    }

    private fun onCounterDoneSubscribePlayerTwo(result: Int) {
        with(localGameBinding) {
            contadorPlayerTwo = result
            "Puntuación: $result".also { textViewPlayerTwo.text = it }
            if (contadorPlayerTwo % 2 == 0) {
                imagePlayerTwo.setImageResource(com.migue.proyectofinal.R.drawable.batman_robin_cachetada_alta_web_590x340)
            } else {
                imagePlayerTwo.setImageResource(com.migue.proyectofinal.R.drawable.batman_robin_girada)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                findNavController().navigate(LocalGameFragmentDirections.actionLocalGameFragmentToStartingSelectionFragment())
                true
            } else false
        }
    }

}