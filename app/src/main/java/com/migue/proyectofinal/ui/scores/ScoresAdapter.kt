package com.migue.proyectofinal.ui.scores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.migue.proyectofinal.R
import com.migue.proyectofinal.databinding.CardViewItemGameBinding
import com.migue.proyectofinal.server.GameServer

class ScoresAdapter (private val gamesList: ArrayList<GameServer>,
                     private val onItemClicked: (GameServer) -> Unit
) : RecyclerView.Adapter<ScoresAdapter.ScoreViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScoresAdapter.ScoreViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_game, parent, false)
        return ScoreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScoresAdapter.ScoreViewHolder, position: Int) {
        val game = gamesList[position]
        holder.bindGame(game)
        holder.itemView.setOnClickListener{ onItemClicked(gamesList[position]) }
    }

    fun appendItems(newList: ArrayList<GameServer>){
        gamesList.clear()
        gamesList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = gamesList.size

    class ScoreViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        private val binding = CardViewItemGameBinding.bind(itemView)

        fun bindGame(game: GameServer){
            with(binding){
                gameIdTextView.text = "Id Partida: "+game.id
                namePlayerOneTextView.text = "NombreJugador1: "+game.namePlayer1
                namePlayerTwoTextView.text = "NombreJugador2: "+game.namePlayer2
                scorePlayerOneTextView.text = "Puntaje: "+game.scorePlayer1
                scorePlayerTwoTextView.text = "Puntaje: "+game.scorePlayer2
                winnerTextView.text = "Ganador: "+game.winner
            }

        }
    }

}