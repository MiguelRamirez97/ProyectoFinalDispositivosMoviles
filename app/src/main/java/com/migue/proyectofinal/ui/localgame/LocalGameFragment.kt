package com.migue.proyectofinal.ui.localgame

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.migue.proyectofinal.R
import com.migue.proyectofinal.databinding.FragmentLocalGamesBinding

class LocalGameFragment : Fragment() {

    private lateinit var localGamesBinding: FragmentLocalGamesBinding
    private lateinit var localGameViewModel: LocalGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        localGamesBinding = FragmentLocalGamesBinding.inflate(inflater, container, false)
        localGameViewModel = ViewModelProvider(this).get(LocalGameViewModel::class.java)
        return localGamesBinding.root
    }

}