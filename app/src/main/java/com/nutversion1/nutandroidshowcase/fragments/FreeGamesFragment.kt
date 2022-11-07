package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.adapters.FreeGameListAdapter
import com.nutversion1.nutandroidshowcase.adapters.MemeAdapter
import com.nutversion1.nutandroidshowcase.adapters.YoutubeAdapter
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGamesResponse
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResult
import com.nutversion1.nutandroidshowcase.databinding.FragmentFreeGamesBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentProgrammingMemesBinding
import com.nutversion1.nutandroidshowcase.viewmodels.FreeGamesViewModel

class FreeGamesFragment : Fragment() {
    private lateinit var binding: FragmentFreeGamesBinding
    private val freeGamesViewModel: FreeGamesViewModel by viewModels { FreeGamesViewModel.Factory()}

    private val freeGameListAdapter = FreeGameListAdapter(object : FreeGameListAdapter.ItemClickListener{
        override fun onClick(position: Int, itemView: View, freeGame: GetFreeGamesResponse) {
            Log.d("myDebug", "click $position ${freeGame.title} ${freeGame.id}")
            openFreeGameDetailFragment(freeGame.id)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFreeGamesBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.freeGameList.adapter = freeGameListAdapter

        binding.platformRadioGroup.check(R.id.all_platform_radio_button)
        binding.categoryRadioGroup.check(R.id.all_category_radio_button)

        fetchFreeGames()

        binding.searchButton.setOnClickListener {
            fetchFreeGames()
            binding.freeGameList.scrollToPosition(0)
        }

        freeGamesViewModel.getFreeGamesResponse.observe(viewLifecycleOwner) {
            Log.d("myDebug", "GetFreeGameResponse: ${it?.size}")

            freeGameListAdapter.setData(it)
        }
    }

    private fun fetchFreeGames(){
        val platform = when(binding.platformRadioGroup.checkedRadioButtonId){
            R.id.all_platform_radio_button -> null
            R.id.pc_platform_radio_button -> "pc"
            R.id.browser_platform_radio_button -> "browser"
            else -> null
        }

        val category = when(binding.categoryRadioGroup.checkedRadioButtonId){
            R.id.all_category_radio_button -> null
            R.id.action_category_radio_button-> "action"
            R.id.shooting_category_radio_button -> "shooting"
            R.id.strategy_category_radio_button -> "strategy"
            R.id.sports_category_radio_button -> "sports"
            R.id.racing_category_radio_button -> "racing"
            R.id.card_category_radio_button -> "card"
            R.id.mmorpg_category_radio_button -> "mmorpg"
            else -> null
        }

        freeGamesViewModel.getFreeGames(
            platform = platform,
            category = category,
            sortBy = "release-date",
        )
    }

    private fun openFreeGameDetailFragment(id: Int){
        val bundle = Bundle()
        bundle.putInt("id", id)

        findNavController().navigate(R.id.go_to_free_game_detail_fragment_action, bundle)
    }
}