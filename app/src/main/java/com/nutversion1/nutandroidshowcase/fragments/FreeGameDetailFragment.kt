package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.nutversion1.nutandroidshowcase.activities.MainActivity
import com.nutversion1.nutandroidshowcase.api.ResponseResult
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGameDetailResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGamesResponse
import com.nutversion1.nutandroidshowcase.databinding.FragmentFreeGameDetailBinding
import com.nutversion1.nutandroidshowcase.viewmodels.FreeGamesViewModel

class FreeGameDetailFragment : Fragment() {
    private lateinit var binding: FragmentFreeGameDetailBinding
    private val freeGamesViewModel: FreeGamesViewModel by viewModels { FreeGamesViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFreeGameDetailBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareViewModels()

        fetchDetail()
    }

    private fun prepareViewModels(){
        freeGamesViewModel.getFreeGameDetailResponseResult.observe(viewLifecycleOwner) {
            when(it){
                ResponseResult.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is ResponseResult.Success<*> -> {
                    (activity as MainActivity).hideLoadingBar()

                    (it.response as GetFreeGameDetailResponse).run {
                        binding.titleText.text = title
                        binding.shortDescriptionText.text = shortDescription
                        binding.descriptionText.text = description
                        binding.gameUrlText.text = "GAME URL: $gameUrl"
                        binding.genreText.text = "GENRE: $genre"
                        binding.platformText.text = "PLATFORM: $platform"
                        binding.developerText.text = "DEVELOPER: $developer"
                        binding.publisherText.text = "PUBLISHER: $publisher"
                        binding.releaseDateText.text = "RELEASE DATE: $releaseDate"

                        view?.let { v ->
                            Glide.with(v)
                                .load(thumbnail)
                                .fitCenter()
                                .into(binding.titleImage)
                        }
                    }
                }
                is ResponseResult.Error -> {
                    (activity as MainActivity).hideLoadingBar()

                    Toast.makeText(activity, "Error: ${it.errorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun fetchDetail() {
        val id = arguments?.getInt(FREE_GAME_ID_TAG)

        id?.let { id ->
            freeGamesViewModel.getFreeGameDetail(id)
        }
    }
}