package com.nutversion1.nutandroidshowcase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nutversion1.nutandroidshowcase.R

class FootballTeamHeaderAdapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : RecyclerView.ViewHolder(inflater.inflate(R.layout.view_football_team_header, parent, false)) {

        }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {}

    override fun getItemCount() = 1
}