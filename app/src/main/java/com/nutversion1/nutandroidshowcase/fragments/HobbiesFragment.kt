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


class HobbiesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hobbies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RadioGroup>(R.id.category_radio_group).check(R.id.none_radio_button)

        view.findViewById<RadioGroup>(R.id.category_radio_group).setOnClickListener{
            Log.d("myDebug", "click: ${it.tag}")
        }

        view.findViewById<Button>(R.id.generate_button)?.setOnClickListener {
            val radioGroup = view.findViewById<RadioGroup>(R.id.category_radio_group)
            val radioButton = radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            Log.d("myDebug", "click: ${radioButton.tag}")

            val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}

            when(radioButton.tag){
                "none" -> viewModel.getRandomHobby()
                else -> viewModel.getRandomHobby(radioButton.tag.toString())
            }
        }

        val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}
        viewModel.getRandomHobbyResponse.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.hobby_text)?.text = it.hobby
            view.findViewById<TextView>(R.id.link_text)?.text = it.link
            view.findViewById<TextView>(R.id.category_text)?.text = it.category
        }
    }
}