package com.nutversion1.nutandroidshowcase.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.databinding.FragmentTranslateBinding

class TranslateFragment : Fragment() {
    private lateinit var binding: FragmentTranslateBinding
    private val viewModel: MyViewModel by viewModels { MyViewModel.Factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranslateBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.translateButton.setOnClickListener {
            val request = DetectLanguageRequest(
                text = binding.inputEditText.text.toString(),
            )

            viewModel.detectLanguage(request)
        }

        viewModel.detectLanguageResponse.observe(viewLifecycleOwner) {
            val source = it.data?.detections?.firstOrNull()?.language
            val target = when(source){
                "en" -> "th"
                "th" -> "en"
                else -> "error"
            }

            val request = TranslateRequest(
                text = binding.inputEditText.text.toString(),
                source = source.toString(),
                target = target.toString(),
            )

            viewModel.translate(request)
        }

        viewModel.translateResponse.observe(viewLifecycleOwner) {
            binding.resultText.text = it.data?.translations?.translatedText
        }
    }
}