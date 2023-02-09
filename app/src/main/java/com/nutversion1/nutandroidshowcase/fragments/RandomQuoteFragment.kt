package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.nutversion1.nutandroidshowcase.activities.MainActivity
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ResponseResult
import com.nutversion1.nutandroidshowcase.api.responses.GetRandomQuoteResponse
import com.nutversion1.nutandroidshowcase.database.RandomQuoteDatabase
import com.nutversion1.nutandroidshowcase.databinding.FragmentRandomQuoteBinding
import com.nutversion1.nutandroidshowcase.mapper.RandomQuoteMapper
import com.nutversion1.nutandroidshowcase.repository.RandomQuoteRepository
import com.nutversion1.nutandroidshowcase.ui.RandomQuoteUi
import com.nutversion1.nutandroidshowcase.viewmodels.RandomQuoteViewModel


class RandomQuoteFragment : Fragment() {
    private lateinit var binding: FragmentRandomQuoteBinding

    private val randomQuoteViewModel: RandomQuoteViewModel by viewModels {
        val randomQuoteDb = Room.databaseBuilder(
            requireContext(),
            RandomQuoteDatabase::class.java, "random_quote_db")
            .fallbackToDestructiveMigration()
            .build()

        val randomQuoteRepository = RandomQuoteRepository(
            ApiManager.getRandomQuoteService(),
            randomQuoteDb.randomQuoteDao(),
            RandomQuoteMapper(),
        )

        RandomQuoteViewModel.Factory(randomQuoteRepository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomQuoteBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareViewModels()

        randomQuoteViewModel.getRandomQuote()

        binding.nextButton.setOnClickListener {
            randomQuoteViewModel.getRandomQuote()
        }
    }

    private fun prepareViewModels(){
        randomQuoteViewModel.responseResult.observe(viewLifecycleOwner) {
            when(it){
                is ResponseResult.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is ResponseResult.Success<*> -> {
                    (activity as MainActivity).hideLoadingBar()

                    when(it.response){
                        is RandomQuoteUi -> {
                            it.response.run{
                                binding.contentText.text = text
                                binding.nameText.text = credit
                            }
                        }
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