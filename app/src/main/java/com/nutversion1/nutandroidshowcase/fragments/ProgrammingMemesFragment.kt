package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.adapters.MemeAdapter
import com.nutversion1.nutandroidshowcase.databinding.FragmentProgrammingMemesBinding


class ProgrammingMemesFragment : Fragment() {
    private lateinit var binding: FragmentProgrammingMemesBinding
    private val viewModel: MyViewModel by viewModels {MyViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgrammingMemesBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memeAdapter = MemeAdapter()
        binding.memeList.adapter = memeAdapter

        viewModel.getMemes()

        binding.refreshButton.setOnClickListener {
            val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}
            viewModel.getMemes()
            binding.memeList.scrollToPosition(0)
        }

        viewModel.getMemesResponse.observe(viewLifecycleOwner) {
            memeAdapter.setData(it)
        }
    }

}