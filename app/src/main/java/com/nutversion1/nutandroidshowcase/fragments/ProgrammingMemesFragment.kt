package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentAboutBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentProgrammingMemesBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentRandomQuoteBinding


class ProgrammingMemesFragment : Fragment() {
    private lateinit var binding: FragmentProgrammingMemesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgrammingMemesBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.generateButton.setOnClickListener {
            val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}
            viewModel.getMemes()
        }

        val viewModel: MyViewModel by viewModels {MyViewModel.Factory()}
        viewModel.getMemesResponse.observe(viewLifecycleOwner) {
            Log.d("myDebug", "total: ${it.size}")
        }
    }

}