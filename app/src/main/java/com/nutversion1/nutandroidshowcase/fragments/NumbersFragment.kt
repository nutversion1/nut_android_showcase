package com.nutversion1.nutandroidshowcase.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R


class NumbersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_numbers, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RadioGroup>(R.id.category_radio_group).check(R.id.date_radio_button)

        view.findViewById<Button>(R.id.generate_button)?.setOnClickListener {
            val radioGroup = view.findViewById<RadioGroup>(R.id.category_radio_group)
            val radioButton = radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}

            when(radioButton.tag){
                "date" -> viewModel.getRandomDateFact()
                "math" -> viewModel.getRandomMathFact()
                "trivia" -> viewModel.getRandomTriviaFact()
                "year" -> viewModel.getRandomYearFact()
            }
        }

        val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}
        viewModel.getNumbersResponse.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.content_text)?.text =
                """
                   Number: ${it.number}
                   
                   Fact: ${it.text}  
                   
                   Year: ${it.year} 
                   
                   Date: ${it.date} 
                """.trimIndent()
        }
    }
}