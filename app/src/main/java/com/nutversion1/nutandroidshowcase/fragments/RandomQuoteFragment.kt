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
import com.nutversion1.nutandroidshowcase.databinding.FragmentRandomQuoteBinding


class RandomQuoteFragment : Fragment() {
    private lateinit var binding: FragmentRandomQuoteBinding
    private val viewModel: MyViewModel by viewModels {MyViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomQuoteBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRandomQuote()
        
        binding.nextButton.setOnClickListener {
            viewModel.getRandomQuote()
        }

        viewModel.randomQuote.observe(viewLifecycleOwner) {
            binding.contentText.text = it.content
            binding.nameText.text = it.originator.name
        }
    }
}