package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentHobbiesBinding
import com.nutversion1.nutandroidshowcase.databinding.FragmentImdbBinding

class ImdbFragment : Fragment() {
    private lateinit var binding: FragmentImdbBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImdbBinding.inflate(inflater)

        return binding.root
    }
}