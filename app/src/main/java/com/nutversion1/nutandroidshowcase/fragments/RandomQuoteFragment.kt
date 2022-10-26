package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R


class RandomQuoteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.generate_button)?.setOnClickListener {
            val viewModel: MyViewModel by viewModels {MyViewModel.Factory()}
            viewModel.getRandomQuote()
        }

        val viewModel: MyViewModel by viewModels {MyViewModel.Factory()}
        viewModel.randomQuote.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.content_text)?.text = it.content
            view.findViewById<TextView>(R.id.name_text)?.text = it.originator.name
        }
    }
}