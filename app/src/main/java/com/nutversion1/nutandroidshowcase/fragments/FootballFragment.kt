package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.adapters.FootballTeamHeaderAdapter
import com.nutversion1.nutandroidshowcase.adapters.FootballTeamListAdapter
import com.nutversion1.nutandroidshowcase.api.responses.GetLeagueTableResponse
import com.nutversion1.nutandroidshowcase.databinding.FragmentFootballBinding
import com.nutversion1.nutandroidshowcase.viewmodels.FootballViewModel


class FootballFragment : Fragment(), FootballTeamListAdapter.ItemClickListener {
    private lateinit var binding: FragmentFootballBinding
    private val footballViewModel: FootballViewModel by viewModels { FootballViewModel.Factory()}

    private lateinit var footballTeamHeaderAdapter: FootballTeamHeaderAdapter
    private val footballTeamListAdapter = FootballTeamListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFootballBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        footballTeamHeaderAdapter = FootballTeamHeaderAdapter(layoutInflater)

        binding.teamList.adapter =  footballTeamListAdapter //ConcatAdapter(footballTeamHeaderAdapter, footballTeamListAdapter)

        footballViewModel.leagueTableResponse.observe(viewLifecycleOwner) {
            footballTeamListAdapter.setData(it)
            binding.teamList.scrollToPosition(0)
        }

        footballViewModel.getPremierLeagueTable()

        binding.leagueTab.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                footballViewModel.run{
                    when(tab?.position){
                        0 -> getPremierLeagueTable()
                        1 -> getLaligaTable()
                        2 -> getSerieATable()
                        3 -> getBundesligaTable()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun onClick(position: Int, itemView: View, leagueTableResponse: GetLeagueTableResponse) {
        Log.d("myDebug", "click ${leagueTableResponse.name}")
//            openTeamInfo("")
    }

    private fun openTeamInfo(team: String){

    }
}