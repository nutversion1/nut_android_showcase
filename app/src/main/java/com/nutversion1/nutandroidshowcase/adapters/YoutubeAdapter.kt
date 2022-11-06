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
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResult

class YoutubeAdapter(val itemClickListener: ItemClickListener) : RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>(){
    private val youtubes = mutableListOf<YoutubeSearchResult>()

    fun setData(youtubes: List<YoutubeSearchResult>){
        this.youtubes.clear()
        this.youtubes.addAll(youtubes)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_youtube_item, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        holder.bindData(youtubes[position])
    }

    override fun getItemCount(): Int {
        return youtubes.size
    }

    inner class YoutubeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val videoImage: ImageView by lazy {
            itemView.findViewById(R.id.video_image)
        }

        private val videoTitle: TextView by lazy {
            itemView.findViewById(R.id.video_title)
        }

        init {
            itemView.setOnClickListener{
                itemClickListener.onClick(absoluteAdapterPosition, itemView, youtubes[absoluteAdapterPosition])
            }
        }

        fun bindData(youtube: YoutubeSearchResult){
            videoTitle.text = youtube.title

            Glide.with(itemView.context)
                .load(youtube.thumbnail.url)
                .fitCenter()
                .into(videoImage)
        }
    }

    interface ItemClickListener{
        fun onClick(position: Int, itemView: View, youtube: YoutubeSearchResult)
    }
}