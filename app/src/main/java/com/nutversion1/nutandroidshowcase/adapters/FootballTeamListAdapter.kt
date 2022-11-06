package com.nutversion1.nutandroidshowcase.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.api.responses.GetLeagueTableResponse

class FootballTeamListAdapter(val itemClickListener: ItemClickListener) : RecyclerView.Adapter<FootballTeamListAdapter.FootballTeamViewHolder>(){
    private val teams = mutableListOf<GetLeagueTableResponse>()

    fun setData(teams: List<GetLeagueTableResponse>){
        this.teams.clear()
        this.teams.addAll(teams)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballTeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_football_team_item, parent, false)
        return FootballTeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: FootballTeamViewHolder, position: Int) {
        holder.bindData(teams[position])
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    inner class FootballTeamViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{
                itemClickListener.onClick(adapterPosition, itemView, teams[adapterPosition])
            }
        }

        private val positionText by lazy {
            itemView.findViewById<TextView>(R.id.position_text)
        }

        private val teamNameText by lazy {
            itemView.findViewById<TextView>(R.id.team_name_text)
        }

        private val teamImage by lazy {
            itemView.findViewById<ImageView>(R.id.team_image)
        }

        private val playedText by lazy {
            itemView.findViewById<TextView>(R.id.played_text)
        }

        private val winsText by lazy {
            itemView.findViewById<TextView>(R.id.wins_text)
        }

        private val drawsText by lazy {
            itemView.findViewById<TextView>(R.id.draws_text)
        }

        private val lossesText by lazy {
            itemView.findViewById<TextView>(R.id.losses_text)
        }

        private val goalsDifferenceText by lazy {
            itemView.findViewById<TextView>(R.id.goals_difference_text)
        }

        private val pointsText by lazy {
            itemView.findViewById<TextView>(R.id.points_text)
        }

        fun bindData(team: GetLeagueTableResponse){
            team.run {
                positionText.text = position
                teamNameText.text = name
                playedText.text = played
                winsText.text = won
                drawsText.text = tie
                lossesText.text = loosed
                goalsDifferenceText.text = goalDifference
                pointsText.text = points
            }

            Glide.with(itemView.context)
                .load(team.squadLogo)
                .fitCenter()
                .into(teamImage)
        }
    }

    interface ItemClickListener{
        fun onClick(position: Int, itemView: View, leagueTableResponse: GetLeagueTableResponse)
    }
}