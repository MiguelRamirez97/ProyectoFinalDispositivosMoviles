package com.migue.proyectofinal.ui.nameplayertwo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.migue.proyectofinal.databinding.FragmentNamePlayerTwoBinding

class NamePlayerTwoFragment : Fragment() {

    private lateinit var namePlayerTwoBinding: FragmentNamePlayerTwoBinding
    private lateinit var namePlayerTwoViewModel: NamePlayerTwoViewModel

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

        with(namePlayerTwoBinding){

//            namePlayerTwoViewModel.dataValidated.observe(viewLifecycleOwner){result ->
//                if(result != null){
//                    findNavController().navigate(NamePlayerTwoFragmentDirections.actionNamePlayerTwoFragmentToLocalGameFragment(result))
//            }}

            namePlayerTwoViewModel.msgDone.observe(viewLifecycleOwner){result ->
                Toast.makeText(requireContext(),result,Toast.LENGTH_SHORT).show()
            }

            acceptButton.setOnClickListener {
                namePlayerTwoViewModel.dataPlayer2(playerTwoTextInputEditText.text.toString())
            }
        }
    }


}