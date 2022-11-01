package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentAboutBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentRandomQuoteBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(inflater)

        return binding.root
    }
}