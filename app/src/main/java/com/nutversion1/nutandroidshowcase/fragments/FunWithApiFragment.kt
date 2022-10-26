package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.nutversion1.nutandroidshowcase.R

class FunWithApiFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fun_with_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.random_quote_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_random_quote_fragment_action)
        )

        view.findViewById<Button>(R.id.hobbies_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_hobbies_fragment_action)
        )

        view.findViewById<Button>(R.id.numbers_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_numbers_fragment_action)
        )

        view.findViewById<Button>(R.id.imdb_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_imdb_fragment_action)
        )

        view.findViewById<Button>(R.id.programming_memes_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_programming_memes_fragment_action)
        )
    }
}