package com.nutversion1.nutandroidshowcase.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.adapters.MemeAdapter
import com.nutversion1.nutandroidshowcase.adapters.YoutubeAdapter
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResult
import com.nutversion1.nutandroidshowcase.databinding.FragmentTranslateBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentYoutubeSearchBinding
import com.nutversion1.nutandroidshowcase.viewmodels.YoutubeSearchViewModel

class YoutubeSearchFragment : Fragment(){
    private lateinit var binding: FragmentYoutubeSearchBinding
    private val youtubeSearchViewModel: YoutubeSearchViewModel by viewModels { YoutubeSearchViewModel.Factory()}

    private val youtubeAdapter = YoutubeAdapter(object : YoutubeAdapter.ItemClickListener{
        override fun onClick(position: Int, itemView: View, youtube: YoutubeSearchResult) {
//            Log.d("myDebug", "click $position ${youtube.title} ${youtube.url}")
            openUrl(youtube.url)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoutubeSearchBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.youtubeList.adapter = youtubeAdapter

        binding.searchButton.setOnClickListener {
            val text = binding.inputEditText.text.toString()

            if(text.isNotBlank()){
                youtubeSearchViewModel.youtubeSearch(text)
            }
        }

        youtubeSearchViewModel.youtubeSearchResponse.observe(viewLifecycleOwner) {
            youtubeAdapter.setData(it.results)
        }
    }

    private fun openUrl(url: String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }
}