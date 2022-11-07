package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.activities.MainActivity
import com.nutversion1.nutandroidshowcase.databinding.FragmentHobbiesBinding
import com.nutversion1.nutandroidshowcase.viewmodels.HobbiesViewModel
import com.nutversion1.nutandroidshowcase.viewmodels.HobbiesViewModel.*


class HobbiesFragment : Fragment() {
    private lateinit var binding: FragmentHobbiesBinding
    private val hobbiesViewModel: HobbiesViewModel by viewModels { Factory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHobbiesBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareViewModels()

        binding.categoryRadioGroup.check(R.id.none_radio_button)

        binding.generateButton.setOnClickListener {
            val radioButton = binding.categoryRadioGroup.findViewById<RadioButton>(binding.categoryRadioGroup.checkedRadioButtonId)

            when(radioButton.tag){
                "none" -> hobbiesViewModel.getRandomHobby()
                else -> hobbiesViewModel.getRandomHobby(radioButton.tag.toString())
            }
        }

        hobbiesViewModel.response.observe(viewLifecycleOwner) {
        }
    }

    private fun prepareViewModels(){
        hobbiesViewModel.response.observe(viewLifecycleOwner) {
            when(it){
                is Response.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is Response.Success -> {
                    (activity as MainActivity).hideLoadingBar()

                    it.getRandomHobbyResponse.run {
                        binding.hobbyText.text = hobby
                        binding.linkText.text = link
                        binding.categoryText.text = category
                    }
                }
                is Response.Error -> {
                    (activity as MainActivity).hideLoadingBar()

                    Toast.makeText(activity, "Error: ${it.errorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}