package com.nutversion1.nutandroidshowcase.fragments

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.nutversion1.nutandroidshowcase.R
import com.nutversion1.nutandroidshowcase.databinding.FragmentMainMenuBinding


class MainMenuFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding

    private val bluetoothManager by lazy {
        requireContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainMenuBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.funWithApiButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_fun_with_api_fragment_action)
        )

        binding.aboutButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_about_fragment_action)
        )

        binding.blurDetectionButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_blur_detection_fragment_action)
        )

        binding.mapButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_map_fragment_action)
        )


        //
        binding.test1Button.setOnClickListener {
            Toast.makeText(context, "test 1 click", Toast.LENGTH_SHORT).show()

//            openFlashLight(true)
            openBluetooth(!bluetoothManager.adapter.isEnabled)
        }

        binding.test2Button.setOnClickListener {
            Toast.makeText(context, "test 2 click", Toast.LENGTH_SHORT).show()

//            openFlashLight(false)
            openBluetooth(false)
        }
    }

    private fun openFlashLight(isOpened: Boolean){
//        val isFlashAvailable = context?.packageManager?.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)

        val cameraManager = requireContext().getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraID = cameraManager.cameraIdList.firstOrNull()

//        Log.d("myDebug", "isFlashAvailable: $isFlashAvailable $cameraID")

        cameraID?.let {
            cameraManager.setTorchMode(it, isOpened)

        }
    }


    private fun openBluetooth(isOpened: Boolean){
        val bluetoothManager = requireContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_CONNECT), 299)
                activityResultLauncher.launch(arrayOf(Manifest.permission.BLUETOOTH_CONNECT))
            }else{
                when(isOpened){
                    true -> bluetoothManager.adapter.enable()
                    false -> bluetoothManager.adapter.disable()
                }
            }
        }else{
            when(isOpened){
                true -> bluetoothManager.adapter.enable()
                false -> bluetoothManager.adapter.disable()
            }
        }
    }

    private var activityResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()){ result ->
                var allAreGranted = true
                for(b in result.values) {
                    allAreGranted = allAreGranted && b
//                    Log.d("myDebug", "b: $b")
                }

                if(allAreGranted) {
                    openBluetooth(!bluetoothManager.adapter.isEnabled)
                }
        }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        Log.d("myDebug", "requestCode:$requestCode, permissions:${permissions.firstOrNull()}, granted:${grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED}")
//    }
//

}