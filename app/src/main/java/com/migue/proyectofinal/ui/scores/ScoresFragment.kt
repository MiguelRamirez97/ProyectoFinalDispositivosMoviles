package com.migue.proyectofinal.ui.scores

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.migue.proyectofinal.R
import com.migue.proyectofinal.databinding.FragmentScoresBinding
import com.migue.proyectofinal.server.GameServer

class ScoresFragment : Fragment() {

    private lateinit var scoresBinding: FragmentScoresBinding
    private lateinit var scoresViewModel: ScoresViewModel
    private lateinit var scoresAdapter: ScoresAdapter
    private var gamesList:ArrayList<GameServer> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        scoresBinding = FragmentScoresBinding.inflate(inflater, container, false)
        scoresViewModel = ViewModelProvider(this)[ScoresViewModel::class.java]
        return scoresBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scoresAdapter = ScoresAdapter(gamesList, onItemClicked = {})

        scoresBinding.gamesRecyclerView.apply {
            layoutManager =  LinearLayoutManager(this@ScoresFragment.requireContext())
            adapter = scoresAdapter
            setHasFixedSize(false)
        }
        scoresViewModel.getGames()

        scoresViewModel.loadGamesDone.observe(viewLifecycleOwner) { result ->
            onLoadGamesDoneSubscribe(result)
        }

        scoresViewModel.msgDone.observe(viewLifecycleOwner) { result ->
            onMsgDoneSubscribe(result)
        }
    }

    private fun onLoadGamesDoneSubscribe(gameList: ArrayList<GameServer>?) {
        gameList?.let { scoresAdapter.appendItems(it) }
    }

    private fun onMsgDoneSubscribe(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}