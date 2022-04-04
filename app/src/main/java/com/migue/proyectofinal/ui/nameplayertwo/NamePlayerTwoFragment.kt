package com.migue.proyectofinal.ui.nameplayertwo

import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.migue.proyectofinal.databinding.FragmentNamePlayerTwoBinding
import com.migue.proyectofinal.ui.localgame.LocalGameFragmentDirections
import com.migue.proyectofinal.ui.onlinegame.OnlineGameFragmentArgs
import java.util.*


class NamePlayerTwoFragment : Fragment() {

    private lateinit var namePlayerTwoBinding: FragmentNamePlayerTwoBinding
    private lateinit var namePlayerTwoViewModel: NamePlayerTwoViewModel
    private val args: NamePlayerTwoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        namePlayerTwoBinding = FragmentNamePlayerTwoBinding.inflate(inflater, container, false)
        namePlayerTwoViewModel = ViewModelProvider(this)[NamePlayerTwoViewModel::class.java]
        return namePlayerTwoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(namePlayerTwoBinding) {

            namePlayerTwoViewModel.dataValidated.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    playLocalGame(result)
                }
            }

            namePlayerTwoViewModel.msgDone.observe(viewLifecycleOwner) { result ->
                message(result)
            }

            acceptButton.setOnClickListener {
                namePlayerTwoViewModel.dataPlayer2(playerTwoTextInputEditText.text.toString())
            }
        }
    }

    private fun playLocalGame(player2: String) {
        var contador = 3
        object : CountDownTimer(6000, 2000) {
            override fun onTick(millisUntilFinished: Long) {
                Toast.makeText(
                    requireContext(),
                    java.lang.String.format(
                        Locale.getDefault(),
                        "La partida comenzara en $contador",
                        millisUntilFinished / 500L
                    ),
                    Toast.LENGTH_SHORT
                ).show()
                contador -= 1
            }

            override fun onFinish() {
                findNavController().navigate(
                    NamePlayerTwoFragmentDirections.actionNamePlayerTwoFragmentToLocalGameFragment(player2, args.player)
                )
            }
        }.start()
    }

    private fun message(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                findNavController().navigate(NamePlayerTwoFragmentDirections.actionNamePlayerTwoFragmentToStartingSelectionFragment())
                true
            } else false
        }
    }
}