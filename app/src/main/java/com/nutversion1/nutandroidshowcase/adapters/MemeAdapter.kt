package com.nutversion1.nutandroidshowcase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.api.responses.GetMemesResponse

class MemeAdapter : RecyclerView.Adapter<MemeAdapter.MemeViewHolder>(){
    private val memes = mutableListOf<GetMemesResponse>()

    fun setData(memes: List<GetMemesResponse>){
        this.memes.clear()
        this.memes.addAll(memes)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_meme_item, parent, false)
        return MemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        holder.bindData(memes[position])
    }

    override fun getItemCount(): Int {
        return memes.size
    }

    class MemeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView by lazy {
            itemView.findViewById(R.id.title_text)
        }

        private val memeImage: ImageView by lazy {
            itemView.findViewById(R.id.meme_image)
        }

        fun bindData(meme: GetMemesResponse){
            titleText.text = meme.title

            Glide.with(itemView.context)
                .load(meme.link)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(memeImage)
        }
    }
}

