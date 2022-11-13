package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.nutversion1.nutandroidshowcase.activities.MainActivity
import com.nutversion1.nutandroidshowcase.adapters.FootballTeamHeaderAdapter
import com.nutversion1.nutandroidshowcase.adapters.FootballTeamListAdapter
import com.nutversion1.nutandroidshowcase.api.ResponseResult
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
    ): View {
        binding = FragmentFootballBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareViewModels()

        footballTeamHeaderAdapter = FootballTeamHeaderAdapter(layoutInflater)

        binding.teamList.adapter =  footballTeamListAdapter //ConcatAdapter(footballTeamHeaderAdapter, footballTeamListAdapter)

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

    private fun prepareViewModels(){
        footballViewModel.responseResult.observe(viewLifecycleOwner) {
            when(it){
                is ResponseResult.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is ResponseResult.Success<*> -> {
                    (activity as MainActivity).hideLoadingBar()

                    footballTeamListAdapter.setData(it.response as List<GetLeagueTableResponse>)
                    binding.teamList.scrollToPosition(0)
                }
                is ResponseResult.Error -> {
                    (activity as MainActivity).hideLoadingBar()

                    Toast.makeText(activity, "Error: ${it.errorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onClick(position: Int, itemView: View, leagueTableResponse: GetLeagueTableResponse) {
        Log.d("myDebug", "click ${leagueTableResponse.name}")
//            openTeamInfo("")
    }

    private fun openTeamInfo(team: String){

    }
}