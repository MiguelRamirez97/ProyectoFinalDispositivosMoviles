package com.migue.proyectofinal.ui.startingselection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.migue.proyectofinal.R
import com.migue.proyectofinal.databinding.FragmentStartingSelectionBinding

class StartingSelectionFragment : Fragment() {

    private lateinit var startingSelectionViewModel: StartingSelectionViewModel
    private lateinit var startingSelectionBinding: FragmentStartingSelectionBinding
    //private val args: StartingSelectionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_starting_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}