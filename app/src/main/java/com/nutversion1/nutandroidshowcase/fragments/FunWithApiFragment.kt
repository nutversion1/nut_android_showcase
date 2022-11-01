package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentFunWithApiBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentProgrammingMemesBinding

class FunWithApiFragment : Fragment() {
    private lateinit var binding: FragmentFunWithApiBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFunWithApiBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.randomQuoteButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_random_quote_fragment_action)
        )

        binding.hobbiesButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_hobbies_fragment_action)
        )

        binding.numbersButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_numbers_fragment_action)
        )

        binding.imdbButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_imdb_fragment_action)
        )

        binding.programmingMemesButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_programming_memes_fragment_action)
        )
    }
}