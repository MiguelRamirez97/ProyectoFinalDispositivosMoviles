package com.migue.proyectofinal.ui.onlinegame

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.migue.proyectofinal.R

class OnlineGameFragment : Fragment() {

    companion object {
        fun newInstance() = OnlineGameFragment()
    }

    private lateinit var viewModel: OnlineGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_online_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OnlineGameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}