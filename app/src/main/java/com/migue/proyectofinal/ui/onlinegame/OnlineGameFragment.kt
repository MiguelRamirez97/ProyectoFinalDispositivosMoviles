package com.migue.proyectofinal.ui.onlinegame

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.migue.proyectofinal.R
import com.migue.proyectofinal.databinding.FragmentOnlineGameBinding

class OnlineGameFragment : Fragment() {

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
            buttonOnlineGame.text = "golpea a: "+args.game.namePlayer1
        }
    }
}