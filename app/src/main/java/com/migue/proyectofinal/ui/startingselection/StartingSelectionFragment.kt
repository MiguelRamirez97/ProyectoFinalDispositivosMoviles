package com.migue.proyectofinal.ui.startingselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.migue.proyectofinal.databinding.FragmentStartingSelectionBinding
import com.migue.proyectofinal.server.GameServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StartingSelectionFragment : Fragment() {

    private lateinit var startingSelectionViewModel: StartingSelectionViewModel
    private lateinit var startingSelectionBinding: FragmentStartingSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        startingSelectionBinding =
            FragmentStartingSelectionBinding.inflate(inflater, container, false)
        startingSelectionViewModel = ViewModelProvider(this)[StartingSelectionViewModel::class.java]
        return startingSelectionBinding.root
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(startingSelectionBinding) {

            startingSelectionViewModel.msgDone.observe(viewLifecycleOwner) { result ->
                onMsgDoneSubscribe(result)
            }

            startingSelectionViewModel.foundGameFromServerDone.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    findNavController().navigate(
                        StartingSelectionFragmentDirections.actionStartingSelectionFragmentToOnlineGameFragment(
                            result
                        )
                    )
                }
            }

            startingSelectionViewModel.waitingGameFromServerDone.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    startingSelectionViewModel.findCurrentGame(result.id.toString())
                }
            }

            startingSelectionViewModel.gameFoundFromServerDone.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    findNavController().navigate(
                        StartingSelectionFragmentDirections.actionStartingSelectionFragmentToOnlineGameFragment(
                            result
                        )
                    )
                }
            }

            startingSelectionViewModel.searchPlayerFromServerDone.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    findNavController().navigate(
                        StartingSelectionFragmentDirections.actionStartingSelectionFragmentToNamePlayerTwoFragment(
                            result
                        )
                    )
                }
            }

            groupGameButton.setOnClickListener {
                findNavController().navigate(
                    StartingSelectionFragmentDirections.actionStartingSelectionFragmentToStartingGroupFragment()
                )
            }

            localGameButton.setOnClickListener {
                startingSelectionViewModel.playLocalGame()
            }

            quickGameButton.setOnClickListener {
                startingSelectionViewModel.playQuickGame()
            }
        }
    }


    private fun onMsgDoneSubscribe(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}