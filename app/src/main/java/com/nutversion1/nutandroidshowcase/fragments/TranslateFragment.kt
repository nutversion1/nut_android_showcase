package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.nutversion1.nutandroidshowcase.activities.MainActivity
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.databinding.FragmentTranslateBinding
import com.nutversion1.nutandroidshowcase.viewmodels.TranslateViewModel
import com.nutversion1.nutandroidshowcase.viewmodels.YoutubeSearchViewModel

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

        prepareViewModels()

        binding.translateButton.setOnClickListener {
            if(binding.inputEditText.text.isNotBlank()){
                val request = DetectLanguageRequest(
                    text = binding.inputEditText.text.toString(),
                )

                translateViewModel.detectLanguage(request)
            }
        }
    }

    private fun prepareViewModels(){
        translateViewModel.detectLanguageResponseResult.observe(viewLifecycleOwner) {
            when(it){
                TranslateViewModel.DetectLanguageResponseResult.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is TranslateViewModel.DetectLanguageResponseResult.Success -> {
                    (activity as MainActivity).hideLoadingBar()

                    if(binding.inputEditText.text.isBlank()){
                        return@observe
                    }

                    val source = it.response.data?.detections?.firstOrNull()?.language
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
                is TranslateViewModel.DetectLanguageResponseResult.Error -> {
                    (activity as MainActivity).hideLoadingBar()

                    Toast.makeText(activity, "Error: ${it.errorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }

        translateViewModel.translateResponseResult.observe(viewLifecycleOwner) {
            when (it) {
                TranslateViewModel.TranslateResponseResult.Loading -> {
                    (activity as MainActivity).showLoadingBar()
                }
                is TranslateViewModel.TranslateResponseResult.Success -> {
                    (activity as MainActivity).hideLoadingBar()

                    binding.resultText.text = it.response.data?.translations?.translatedText
                }
                is TranslateViewModel.TranslateResponseResult.Error -> {
                    (activity as MainActivity).hideLoadingBar()

                    Toast.makeText(activity, "Error: ${it.errorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}