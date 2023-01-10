package com.nutversion1.nutandroidshowcase.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.nutversion1.nutandroidshowcase.databinding.FragmentBlurDetectionBinding
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.MatOfDouble
import org.opencv.imgproc.Imgproc
import java.text.DecimalFormat
import kotlin.math.pow

class BlurDetectionFragment : Fragment() {
    companion object{
        const val PICK_IMAGE_REQUEST_CODE = 1001
        private const val BLUR_THRESHOLD = 500
        const val BLURRED_IMAGE = "BLURRED IMAGE"
        const val NOT_BLURRED_IMAGE = "NOT BLURRED IMAGE"
    }

    private lateinit var binding: FragmentBlurDetectionBinding
    private lateinit var sourceMatImage: Mat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBlurDetectionBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!OpenCVLoader.initDebug()) {
            Log.d("myDebug", "Internal OpenCV library not found. Using OpenCV Manager for initialization")
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, context, mLoaderCallback)
        } else {
            Log.d("yDebug", "OpenCV library found inside package. Using it!")
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }

        binding.chooseImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST_CODE) {
            Log.d("myDebug", "requestCode: $requestCode - resultCode: $resultCode - data: $data")

            val selectedImageUri = data?.data

            selectedImageUri?.let {
                binding.previewImage.setImageURI(it)

                val score = getSharpnessScoreFromOpenCV(binding.previewImage.drawToBitmap())
                showScoreFromOpenCV(score)
            }

        }
    }

    private val mLoaderCallback = object : BaseLoaderCallback(context) {
        override fun onManagerConnected(status: Int) {
            when (status) {
                LoaderCallbackInterface.SUCCESS -> {
                    sourceMatImage = Mat()
                }
                else -> {
                    super.onManagerConnected(status)
                }
            }
        }
    }

    private fun getSharpnessScoreFromOpenCV(bitmap: Bitmap): Double {
        val destination = Mat()
        val matGray = Mat()
        Utils.bitmapToMat(bitmap, sourceMatImage)
        Imgproc.cvtColor(sourceMatImage, matGray, Imgproc.COLOR_BGR2GRAY)
        Imgproc.Laplacian(matGray, destination, 3)
        val median = MatOfDouble()
        val std = MatOfDouble()
        Core.meanStdDev(destination, median, std)

        return DecimalFormat("0.00").format(std.get(0, 0)[0].pow(2.0)).toDouble()
    }

    private fun showScoreFromOpenCV(score: Double) {
        when (score < BLUR_THRESHOLD) {
            true -> {
                binding.sharpnessText.text = "$BLURRED_IMAGE\n\nScore: $score\n\n[Threshold: $BLUR_THRESHOLD]"
                binding.sharpnessText.setTextColor(Color.RED)
            }
            false -> {
                binding.sharpnessText.text = "$NOT_BLURRED_IMAGE\n\nScore: $score\n\n[Threshold: $BLUR_THRESHOLD]"
                binding.sharpnessText.setTextColor(Color.BLUE)
            }
        }
    }

}