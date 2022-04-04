package com.migue.proyectofinal.ui.onlinegame

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.migue.proyectofinal.R
import com.migue.proyectofinal.databinding.FragmentOnlineGameBinding
import com.migue.proyectofinal.server.GameServer
import com.migue.proyectofinal.server.serverrepository.GameServerRespository
import com.migue.proyectofinal.ui.localgame.LocalGameFragmentDirections

class OnlineGameFragment : Fragment() {

    private var contador = 0
    private lateinit var onlineGameBinding: FragmentOnlineGameBinding
    private lateinit var onlineGameViewModel: OnlineGameViewModel
    private val args: OnlineGameFragmentArgs by navArgs()
    private var uidPlayer: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onlineGameBinding = FragmentOnlineGameBinding.inflate(inflater, container, false)
        onlineGameViewModel = ViewModelProvider(this)[OnlineGameViewModel::class.java]
        return onlineGameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(onlineGameBinding) {
            object : CountDownTimer(5000, 1) {
                override fun onTick(millisUntilFinished: Long) {
                    uidPlayer = onlineGameViewModel.findUidPlayer(args.gameServer)
                    if (args.gameServer.uidPlayer1.equals(uidPlayer)) {
                        ("golpea a: " + args.gameServer.namePlayer2).also {
                            buttonOnlineGame.text = it
                        }
                    } else {
                        ("golpea a: " + args.gameServer.namePlayer1).also {
                            buttonOnlineGame.text = it
                        }
                    }
                    ("Puntuación: $contador").also { textViewCounter.text = it }

                    buttonOnlineGame.setOnClickListener {
                        onlineGameViewModel.animationButton(contador)
                    }

                    onlineGameViewModel.counterDone.observe(viewLifecycleOwner) { result ->
                        onCounterDoneSubscribe(result)
                    }
                }

                override fun onFinish() {
                    finalizarPartida(contador, args.gameServer)
                }
            }.start()

            onlineGameViewModel.searchPlayerFromServerDone.observe(viewLifecycleOwner){result ->
                resultDialog(result)

            }
        }
    }

    private fun resultDialog(gameServer: GameServer?) {
        var winner: String = ""
        val scorePlayer1 = gameServer?.scorePlayer1
        val scorePlayer2 = gameServer?.scorePlayer2
            if (scorePlayer1 != null) {
                if ( scorePlayer1 > scorePlayer2!!) {
                    winner = gameServer.namePlayer1.toString()
                }else{
                    winner = gameServer.namePlayer2.toString()
                }
            }

            val builder = AlertDialog.Builder(requireContext())
            with(builder)
            {
                setTitle("Partida Terminada")
                setMessage(
                    gameServer?.namePlayer1.toString() + " obtuvo " + gameServer?.scorePlayer1 + " puntos.\n" +
                            gameServer?.namePlayer2.toString() + " obtuvo " + gameServer?.scorePlayer2 + " puntos.\n" +
                            "El ganador es: " + winner
                )
                setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int -> }
                setOnDismissListener { findNavController().navigate(OnlineGameFragmentDirections.actionOnlineGameFragmentToStartingSelectionFragment()) }
                setCancelable(false)
                show()
            }
        onlineGameViewModel.saveWinner(winner, gameServer?.id.toString())
    }

    private fun finalizarPartida(contador: Int, gameServer: GameServer) {
        onlineGameViewModel.updatefinishGame(
            gameServer,
            uidPlayer,
            contador
        )
    }

    private fun onCounterDoneSubscribe(result: Int) {
        with(onlineGameBinding) {
            contador = result
            ("Puntuación: $result").also { textViewCounter.text = it }
            if (contador % 2 == 0) {
                imageViewGameOnline.setImageResource(R.drawable.batman_robin_cachetada_alta_web_590x340)
            } else {
                imageViewGameOnline.setImageResource(R.drawable.batman_robin_girada)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                findNavController().navigate(OnlineGameFragmentDirections.actionOnlineGameFragmentToStartingSelectionFragment())
                true
            } else false
        }
    }
}