package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.databinding.FragmentRandomQuoteBinding
import com.nutversion1.nutandroidshowcase.viewmodels.RandomQuoteViewModel


class RandomQuoteFragment : Fragment() {
    private lateinit var binding: FragmentRandomQuoteBinding
    private val randomQuoteViewModel: RandomQuoteViewModel by viewModels {RandomQuoteViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomQuoteBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        randomQuoteViewModel.getRandomQuote()
        
        binding.nextButton.setOnClickListener {
            randomQuoteViewModel.getRandomQuote()
        }

        randomQuoteViewModel.randomQuote.observe(viewLifecycleOwner) {
            binding.contentText.text = it.content
            binding.nameText.text = it.originator.name
        }
    }
}