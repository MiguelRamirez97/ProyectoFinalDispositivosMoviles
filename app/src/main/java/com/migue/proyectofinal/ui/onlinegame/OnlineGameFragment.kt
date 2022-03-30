package com.migue.proyectofinal.ui.onlinegame

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.migue.proyectofinal.R
import com.migue.proyectofinal.databinding.FragmentOnlineGameBinding

class OnlineGameFragment : Fragment(){

    private var contador = 0
    private lateinit var onlineGameBinding: FragmentOnlineGameBinding
    private lateinit var onlineGameViewModel: OnlineGameViewModel
    private val args: OnlineGameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onlineGameBinding = FragmentOnlineGameBinding.inflate(inflater, container, false)
        onlineGameViewModel = ViewModelProvider(this).get(OnlineGameViewModel::class.java)
        return onlineGameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(onlineGameBinding){
            buttonOnlineGame.text = "golpea a: "+args.gameServer.namePlayer1
            textViewCounter.text = "Puntuación: "+contador

            buttonOnlineGame.setOnClickListener{
                onlineGameViewModel.animacionBoton(contador)
            }

            onlineGameViewModel.counterDone.observe(viewLifecycleOwner){result ->
                onCounterDoneSubscribe(result)
            }

        }
    }

    private fun onCounterDoneSubscribe(result: Int) {
        with(onlineGameBinding){
            contador = result
            textViewCounter.text = "Puntuación: "+result
            if(contador%2 ==0){
                imageViewGameOnline.setImageResource(R.drawable.batman_robin_cachetada_alta_web_590x340)
            }else{
                imageViewGameOnline.setImageResource(R.drawable.batman_robin_girada)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { v, keyCode, event ->
            if (event.action === KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                findNavController().navigate(OnlineGameFragmentDirections.actionOnlineGameFragmentToStartingSelectionFragment())
                true
            } else false
        }
    }

}