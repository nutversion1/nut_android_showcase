package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.adapters.MemeAdapter
import com.nutversion1.nutandroidshowcase.databinding.FragmentProgrammingMemesBinding
import com.nutversion1.nutandroidshowcase.viewmodels.ProgrammingMemesViewModel


class ProgrammingMemesFragment : Fragment() {
    private lateinit var binding: FragmentProgrammingMemesBinding
    private val programmingMemesViewModel: ProgrammingMemesViewModel by viewModels {ProgrammingMemesViewModel.Factory()}

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

        programmingMemesViewModel.getMemes()

        binding.refreshButton.setOnClickListener {
            programmingMemesViewModel.getMemes()
            binding.memeList.scrollToPosition(0)
        }

        programmingMemesViewModel.getMemesResponse.observe(viewLifecycleOwner) {
            memeAdapter.setData(it)
        }
    }

}