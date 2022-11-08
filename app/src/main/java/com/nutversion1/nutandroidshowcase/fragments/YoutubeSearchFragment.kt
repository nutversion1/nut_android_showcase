package com.nutversion1.nutandroidshowcase.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.activities.MainActivity
import com.nutversion1.nutandroidshowcase.adapters.YoutubeAdapter
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResult
import com.nutversion1.nutandroidshowcase.databinding.FragmentYoutubeSearchBinding
import com.nutversion1.nutandroidshowcase.viewmodels.YoutubeSearchViewModel
import com.nutversion1.nutandroidshowcase.viewmodels.YoutubeSearchViewModel.*

class YoutubeSearchFragment : Fragment(){
    private lateinit var binding: FragmentYoutubeSearchBinding
    private val youtubeSearchViewModel: YoutubeSearchViewModel by viewModels { YoutubeSearchViewModel.Factory()}

    private val youtubeAdapter = YoutubeAdapter(object : YoutubeAdapter.ItemClickListener{
        override fun onClick(position: Int, itemView: View, youtube: YoutubeSearchResult) {
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

        prepareViewModels()

        binding.youtubeList.adapter = youtubeAdapter

        binding.searchButton.setOnClickListener {
            val text = binding.inputEditText.text.toString()

            if(text.isNotBlank()){
                youtubeSearchViewModel.youtubeSearch(text)
            }
        }
    }

    private fun prepareViewModels(){
        youtubeSearchViewModel.responseResult.observe(viewLifecycleOwner) {
            when(it){
                is ResponseResult.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is ResponseResult.Success -> {
                    (activity as MainActivity).hideLoadingBar()

                    youtubeAdapter.setData(it.response.results)
                    binding.youtubeList.scrollToPosition(0)
                }
                is ResponseResult.Error -> {
                    (activity as MainActivity).hideLoadingBar()

                    Toast.makeText(activity, "Error: ${it.errorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun openUrl(url: String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }
}