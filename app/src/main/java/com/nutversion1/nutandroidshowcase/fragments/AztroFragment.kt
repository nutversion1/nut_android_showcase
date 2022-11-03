package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentAztroBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentHobbiesBinding

class AztroFragment : Fragment() {
    private lateinit var binding: FragmentAztroBinding
    private val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAztroBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.categoryRadioGroup.check(R.id.none_radio_button)

        binding.generateButton.setOnClickListener {
            viewModel.fetchHoroscopeInformation("cancer", "today")
//            val radioButton = binding.categoryRadioGroup.findViewById<RadioButton>(binding.categoryRadioGroup.checkedRadioButtonId)
//
//            val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}
//
//            when(radioButton.tag){
//                "none" -> viewModel.getRandomHobby()
//                else -> viewModel.getRandomHobby(radioButton.tag.toString())
//            }
        }

        val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}
        viewModel.fetchHoroscopeInformationResponse.observe(viewLifecycleOwner) {
            binding.infoText.text =
                """
                    ${it.description}
                    
                    Date Range: ${it.dateRange}
                    Current Date: ${it.currentDate}
                    Compatibility: ${it.compatibility}
                    Mood: ${it.mood}
                    Color: ${it.color}
                    Lucky Number: ${it.luckyNumber}
                    Lucky Time: ${it.luckyTime}
                """.trimIndent()
        }
    }
}