package com.nutversion1.nutandroidshowcase.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nutversion1.nutandroidshowcase.databinding.FragmentBlurDetectionBinding

class BlurDetectionFragment : Fragment() {
    private lateinit var binding: FragmentBlurDetectionBinding

    companion object{
        const val SELECT_PICTURE_REQUEST_CODE = 200
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBlurDetectionBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chooseImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, SELECT_PICTURE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE_REQUEST_CODE) {
            Log.d("myDebug", "requestCode: $requestCode - resultCode: $resultCode - data: $data")

            if (requestCode == SELECT_PICTURE_REQUEST_CODE ) {
                val selectedImageUri = data?.data

                selectedImageUri?.let {
                    binding.previewImage.setImageURI(it)
                    binding.sharpnessText.text = "Sharpness: 99%"
                }
            }
        }
    }
}