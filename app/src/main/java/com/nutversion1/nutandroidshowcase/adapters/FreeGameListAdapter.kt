package com.nutversion1.nutandroidshowcase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGameDetailResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGamesResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetMemesResponse
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResult

class FreeGameListAdapter(val itemClickListener: ItemClickListener) : RecyclerView.Adapter<FreeGameListAdapter.FreeGameViewHolder>() {
    private val freeGames = mutableListOf<GetFreeGamesResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreeGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_free_game_item, parent, false)
        return FreeGameViewHolder(view)
    }

    override fun onBindViewHolder(holder: FreeGameViewHolder, position: Int) {
        holder.bindData(freeGames[position])
    }

    override fun getItemCount(): Int {
        return freeGames.size
    }

    fun setData(freeGames: List<GetFreeGamesResponse>){
        this.freeGames.clear()
        this.freeGames.addAll(freeGames)

        notifyDataSetChanged()
    }

    inner class FreeGameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView by lazy {
            itemView.findViewById(R.id.title_text)
        }

        private val posterImage: ImageView by lazy {
            itemView.findViewById(R.id.poster_image)
        }

        init {
            itemView.setOnClickListener{
                itemClickListener.onClick(absoluteAdapterPosition, itemView, freeGames[absoluteAdapterPosition])
            }
        }

        fun bindData(freeGame: GetFreeGamesResponse){
            titleText.text = freeGame.title

            Glide.with(itemView.context)
                .load(freeGame.thumbnail)
                .fitCenter()
                .into(posterImage)
        }
    }

    interface ItemClickListener{
        fun onClick(position: Int, itemView: View, freeGame: GetFreeGamesResponse)
    }
}