package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentAztroBinding
import com.nutversion1.nutandroidshowcase.viewmodels.AztroViewModel

class AztroFragment : Fragment() {
    private lateinit var binding: FragmentAztroBinding
    private val aztroViewModel: AztroViewModel by viewModels { AztroViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAztroBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signRadioGroup.check(R.id.aries_radio_button)
        binding.dayRadioGroup.check(R.id.today_radio_button)

        binding.generateButton.setOnClickListener {
            val selectedSignButton = binding.signRadioGroup.findViewById<RadioButton>(binding.signRadioGroup.checkedRadioButtonId)
            val selectedDayButton = binding.dayRadioGroup.findViewById<RadioButton>(binding.dayRadioGroup.checkedRadioButtonId)

            val sign = selectedSignButton.tag.toString()
            val day = selectedDayButton.tag.toString()

            aztroViewModel.fetchHoroscopeInformation(sign, day)
        }

        aztroViewModel.fetchHoroscopeInformationResponse.observe(viewLifecycleOwner) {
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