package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.databinding.FragmentTranslateBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentYoutubeSearchBinding

class YoutubeSearchFragment : Fragment() {
    private lateinit var binding: FragmentYoutubeSearchBinding
    private val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoutubeSearchBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            val text = binding.inputEditText.text.toString()

            if(text.isNotBlank()){
                viewModel.youtubeSearch(text)
            }
        }

        viewModel.youtubeSearchResponse.observe(viewLifecycleOwner) {
            binding.urlText.text = if(it.results.isNotEmpty()) it.results.random().url else "result..."
        }
    }
}