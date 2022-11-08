package com.nutversion1.nutandroidshowcase.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.activities.MainActivity
import com.nutversion1.nutandroidshowcase.databinding.FragmentNumbersBinding
import com.nutversion1.nutandroidshowcase.viewmodels.NumbersViewModel
import com.nutversion1.nutandroidshowcase.viewmodels.NumbersViewModel.ResponseResult
import com.nutversion1.nutandroidshowcase.viewmodels.RandomQuoteViewModel


class NumbersFragment : Fragment() {
    private lateinit var binding: FragmentNumbersBinding
    private val numbersViewModel: NumbersViewModel by viewModels { NumbersViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumbersBinding.inflate(inflater)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryRadioGroup.check(R.id.date_radio_button)

        view.findViewById<Button>(R.id.generate_button)?.setOnClickListener {
            val radioButton = binding.categoryRadioGroup.findViewById<RadioButton>(binding.categoryRadioGroup.checkedRadioButtonId)

            when(radioButton.tag){
                "date" -> numbersViewModel.getRandomDateFact()
                "math" -> numbersViewModel.getRandomMathFact()
                "trivia" -> numbersViewModel.getRandomTriviaFact()
                "year" -> numbersViewModel.getRandomYearFact()
            }
        }

        numbersViewModel.responseResult.observe(viewLifecycleOwner) {
            when(it){
                is ResponseResult.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is ResponseResult.Success -> {
                    (activity as MainActivity).hideLoadingBar()

                    it.getNumbersResponse.run {
                        binding.contentText.text =
                            """ 
                               Number: $number
                               
                               Fact: $text  
                               
                               Year: $year 
                               
                               Date: $date 
                            """.trimIndent()
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