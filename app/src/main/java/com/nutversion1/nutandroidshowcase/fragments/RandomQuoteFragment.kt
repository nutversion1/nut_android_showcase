package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.activities.MainActivity
import com.nutversion1.nutandroidshowcase.databinding.FragmentRandomQuoteBinding
import com.nutversion1.nutandroidshowcase.viewmodels.RandomQuoteViewModel
import com.nutversion1.nutandroidshowcase.viewmodels.RandomQuoteViewModel.*


class RandomQuoteFragment : Fragment() {
    private lateinit var binding: FragmentRandomQuoteBinding
    private val randomQuoteViewModel: RandomQuoteViewModel by viewModels { Factory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomQuoteBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareViewModels()

        binding.nextButton.setOnClickListener {
            randomQuoteViewModel.getRandomQuote()
        }

        randomQuoteViewModel.getRandomQuote()
    }

    private fun prepareViewModels(){
        randomQuoteViewModel.responseResult.observe(viewLifecycleOwner) {
            when(it){
                is ResponseResult.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is ResponseResult.Success -> {
                    (activity as MainActivity).hideLoadingBar()

                    it.getRandomQuoteResponse.run {
                        binding.contentText.text = content
                        binding.nameText.text = originator.name
                    }
                }
                is ResponseResult.Error -> {
                    (activity as MainActivity).hideLoadingBar()

                    Toast.makeText(activity, "Error: ${it.errorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}