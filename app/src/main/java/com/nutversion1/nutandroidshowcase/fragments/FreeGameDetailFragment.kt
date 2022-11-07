package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.nutversion1.nutandroidshowcase.databinding.FragmentFreeGameDetailBinding
import com.nutversion1.nutandroidshowcase.viewmodels.FreeGamesViewModel

class FreeGameDetailFragment : Fragment() {
    private lateinit var binding: FragmentFreeGameDetailBinding
    private val freeGamesViewModel: FreeGamesViewModel by viewModels { FreeGamesViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFreeGameDetailBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchDetail()

        freeGamesViewModel.getFreeGameDetailResponse.observe(viewLifecycleOwner) {
            it.run {
                binding.titleText.text = title
                binding.shortDescriptionText.text = shortDescription
                binding.descriptionText.text = description
                binding.gameUrlText.text = "GAME URL: $gameUrl"
                binding.genreText.text = "GENRE: $genre"
                binding.platformText.text = "PLATFORM: $platform"
                binding.developerText.text = "DEVELOPER: $developer"
                binding.publisherText.text = "PUBLISHER: $publisher"
                binding.releaseDateText.text = "RELEASE DATE: $releaseDate"

                Glide.with(view)
                    .load(thumbnail)
                    .fitCenter()
                    .into(binding.titleImage)
            }
        }
    }

    private fun fetchDetail() {
        val id = arguments?.getInt("id")

        id?.let { id ->
            freeGamesViewModel.getFreeGameDetail(id)
        }
    }
}