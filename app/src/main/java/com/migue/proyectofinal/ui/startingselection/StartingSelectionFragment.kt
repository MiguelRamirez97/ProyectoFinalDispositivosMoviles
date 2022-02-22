package com.migue.proyectofinal.ui.startingselection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.migue.proyectofinal.databinding.FragmentStartingSelectionBinding

class StartingSelectionFragment : Fragment() {

    private lateinit var startingSelectionViewModel: StartingSelectionViewModel
    private lateinit var startingSelectionBinding: FragmentStartingSelectionBinding
    //private val args: StartingSelectionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        startingSelectionBinding = FragmentStartingSelectionBinding.inflate(inflater, container, false)
        startingSelectionViewModel = ViewModelProvider(this)[StartingSelectionViewModel::class.java]
        return startingSelectionBinding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        with(startingSelectionBinding){
//            findNavController().navigate(
//                StartingSelectionFragmentDirections.actionStartingSelectionFragmentToStartingGroupFragment()
//            )
//        }
//    }
}