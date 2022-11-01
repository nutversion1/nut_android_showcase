package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentFunWithApiBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentHobbiesBinding


class HobbiesFragment : Fragment() {
    private lateinit var binding: FragmentHobbiesBinding

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

            val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}

            when(radioButton.tag){
                "none" -> viewModel.getRandomHobby()
                else -> viewModel.getRandomHobby(radioButton.tag.toString())
            }
        }

        val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}
        viewModel.getRandomHobbyResponse.observe(viewLifecycleOwner) {
            binding.hobbyText.text = it.hobby
            binding.linkText.text = it.link
            binding.categoryText.text = it.category
        }
    }
}