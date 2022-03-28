package com.migue.proyectofinal.ui.startingselection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.migue.proyectofinal.databinding.FragmentStartingSelectionBinding
import androidx.navigation.fragment.findNavController
import com.migue.proyectofinal.server.Player

class StartingSelectionFragment : Fragment() {

    private lateinit var startingSelectionViewModel: StartingSelectionViewModel
    private lateinit var startingSelectionBinding: FragmentStartingSelectionBinding
    private val args: StartingSelectionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        startingSelectionBinding =
            FragmentStartingSelectionBinding.inflate(inflater, container, false)
        startingSelectionViewModel = ViewModelProvider(this)[StartingSelectionViewModel::class.java]
        return startingSelectionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(startingSelectionBinding) {

            startingSelectionViewModel.msgDone.observe(viewLifecycleOwner) { result ->
                onMsgDoneSubscribe(result)
            }

            startingSelectionViewModel.searchGameFromServerDone.observe(viewLifecycleOwner) {result ->
                if(result != null){
                    findNavController().navigate(
                        StartingSelectionFragmentDirections.actionStartingSelectionFragmentToOnlineGameFragment(result)
                    )
                }
            }

            groupGameButton.setOnClickListener {
                findNavController().navigate(
                    StartingSelectionFragmentDirections.actionStartingSelectionFragmentToStartingGroupFragment()
                )
            }

            localGameButton.setOnClickListener {
                findNavController().navigate(
                    StartingSelectionFragmentDirections.actionStartingSelectionFragmentToNamePlayerTwoFragment()
                )
            }

            quickGameButton.setOnClickListener {
                startingSelectionViewModel.playGame()
            }
        }
    }

    private fun onMsgDoneSubscribe(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }


}