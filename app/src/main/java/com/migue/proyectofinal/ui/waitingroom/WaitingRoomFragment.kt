package com.migue.proyectofinal.ui.waitingroom

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.migue.proyectofinal.R
import com.migue.proyectofinal.databinding.FragmentWaitingRoomBinding

class WaitingRoomFragment : Fragment() {

    private lateinit var waitingRoomBinding: FragmentWaitingRoomBinding
    private lateinit var waitingRoomViewModel: WaitingRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        waitingRoomBinding = FragmentWaitingRoomBinding.inflate(inflater, container, false)
        waitingRoomViewModel = ViewModelProvider(this).get(WaitingRoomViewModel::class.java)
        return waitingRoomBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(waitingRoomBinding){
            buttonPlayGame.setOnClickListener {
                findNavController().navigate(WaitingRoomFragmentDirections.actionWaitingRoomFragmentToOnlineGameFragment2())
            }
        }
    }

}