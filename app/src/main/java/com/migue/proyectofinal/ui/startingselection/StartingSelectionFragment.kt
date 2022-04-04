package com.migue.proyectofinal.ui.startingselection

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.migue.proyectofinal.databinding.FragmentStartingSelectionBinding
import com.migue.proyectofinal.server.GameServer
import com.migue.proyectofinal.server.PlayerServer
import com.migue.proyectofinal.ui.nameplayertwo.NamePlayerTwoFragmentDirections
import java.util.*

class StartingSelectionFragment : Fragment() {

    private lateinit var startingSelectionViewModel: StartingSelectionViewModel
    private lateinit var startingSelectionBinding: FragmentStartingSelectionBinding

    var gameServerfind: GameServer? = null

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

            startingSelectionViewModel.searchGameFromServerDone.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    findNavController().navigate(
                        StartingSelectionFragmentDirections.actionStartingSelectionFragmentToOnlineGameFragment(
                            result
                        )
                    )
                }
            }

            startingSelectionViewModel.searchGameAgainFromServerDone.observe(viewLifecycleOwner) { result ->
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
                    waitingGame(result)
                }
            }

            startingSelectionViewModel.gameFoundFromServerDone.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    gameServerfind = result
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
                //startingSelectionViewModel.playQuickGame()
            }
        }
    }

    private fun waitingGame(gameServer: GameServer) {
        //startingSelectionViewModel.findCurrentGame(gameServer.id.toString())
//        gameServerfind = gameServer
//        if (gameServer != null) {
            onMsgDoneSubscribe("Encontro")
//        } else {
//            onMsgDoneSubscribe("No encontro")
//        }
    }

    private fun onMsgDoneSubscribe(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}