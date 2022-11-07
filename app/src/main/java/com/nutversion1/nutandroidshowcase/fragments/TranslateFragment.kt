package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.databinding.FragmentTranslateBinding
import com.nutversion1.nutandroidshowcase.viewmodels.TranslateViewModel

class TranslateFragment : Fragment() {
    private lateinit var binding: FragmentTranslateBinding
    private val translateViewModel: TranslateViewModel by viewModels { TranslateViewModel.Factory()}

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

            translateViewModel.detectLanguage(request)
        }

        translateViewModel.detectLanguageResponse.observe(viewLifecycleOwner) {
            if(binding.inputEditText.text.isBlank()){
                return@observe
            }

            val source = it.data?.detections?.firstOrNull()?.language
            val target = when(source){
                "en" -> "th"
                "th" -> "en"
                else -> "th"
            }

            val request = TranslateRequest(
                text = binding.inputEditText.text.toString(),
                source = source.toString(),
                target = target.toString(),
            )

            translateViewModel.translate(request)
        }

        translateViewModel.translateResponse.observe(viewLifecycleOwner) {
            binding.resultText.text = it.data?.translations?.translatedText
        }
    }
}