package com.migue.proyectofinal.ui.startinggroup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.migue.proyectofinal.databinding.FragmentStartingGroupBinding

class StartingGroupFragment : Fragment() {

    private lateinit var startingGroupViewModel: StartingGroupViewModel
    private lateinit var startingGroupBinding: FragmentStartingGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        startingGroupBinding = FragmentStartingGroupBinding.inflate(inflater, container,false)
        startingGroupViewModel = ViewModelProvider(this)[StartingGroupViewModel::class.java]
        return startingGroupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(startingGroupBinding){

            createRoomButton.setOnClickListener {
                findNavController().navigate(StartingGroupFragmentDirections.actionStartingGroupFragmentToWaitingRoomFragment())
            }

            enterTheRoomButton.setOnClickListener {
                if(codeRoomEditText.text?.isNotEmpty() == true){
                    findNavController().navigate(StartingGroupFragmentDirections.actionStartingGroupFragmentToWaitingRoomFragment())
                }else{
                    Toast.makeText(requireContext(), "Debe ingresar el codigo de la partida", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}