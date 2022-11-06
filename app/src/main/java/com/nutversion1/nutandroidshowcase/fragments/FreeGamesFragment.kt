package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.adapters.FreeGameListAdapter
import com.nutversion1.nutandroidshowcase.adapters.MemeAdapter
import com.nutversion1.nutandroidshowcase.databinding.FragmentFreeGamesBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentProgrammingMemesBinding

class FreeGamesFragment : Fragment() {
    private lateinit var binding: FragmentFreeGamesBinding
    private val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFreeGamesBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val freeGameListAdapter = FreeGameListAdapter()
        binding.freeGameList.adapter = freeGameListAdapter

        binding.platformRadioGroup.check(R.id.all_platform_radio_button)
        binding.categoryRadioGroup.check(R.id.all_category_radio_button)

        fetchFreeGames()

        binding.searchButton.setOnClickListener {
            fetchFreeGames()
            binding.freeGameList.scrollToPosition(0)
        }

        viewModel.getFreeGamesResponse.observe(viewLifecycleOwner) {
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

        viewModel.getFreeGames(
            platform = platform,
            category = category,
            sortBy = "release-date",
        )
    }
}