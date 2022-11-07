package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentHobbiesBinding
import com.nutversion1.nutandroidshowcase.viewmodels.HobbiesViewModel


class HobbiesFragment : Fragment() {
    private lateinit var binding: FragmentHobbiesBinding
    private val hobbiesViewModel: HobbiesViewModel by viewModels { HobbiesViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHobbiesBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryRadioGroup.check(R.id.none_radio_button)

        binding.generateButton.setOnClickListener {
            val radioButton = binding.categoryRadioGroup.findViewById<RadioButton>(binding.categoryRadioGroup.checkedRadioButtonId)

            when(radioButton.tag){
                "none" -> hobbiesViewModel.getRandomHobby()
                else -> hobbiesViewModel.getRandomHobby(radioButton.tag.toString())
            }
        }

        hobbiesViewModel.getRandomHobbyResponse.observe(viewLifecycleOwner) {
            binding.hobbyText.text = it.hobby
            binding.linkText.text = it.link
            binding.categoryText.text = it.category
        }
    }
}