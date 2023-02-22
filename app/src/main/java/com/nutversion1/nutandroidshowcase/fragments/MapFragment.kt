package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nutversion1.nutandroidshowcase.databinding.FragmentMapBinding

class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding

//    private lateinit var mapView: MapView
//    private lateinit var googleMap: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMap(savedInstanceState)
    }

    private fun setupMap(savedInstanceState: Bundle?){
        binding.mapView.onCreate(savedInstanceState)

        binding.mapView.getMapAsync{ googleMap ->
//            this.googleMap = googleMap

            Log.d("myDebug", "map ready")


            val latLng = LatLng(13.761925, 100.611228)
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15f)
            googleMap.moveCamera(cameraUpdate)

            val markerOptions = MarkerOptions()
                .position(latLng)
                .title("My Marker")
                .snippet("This is my marker")
            val marker = googleMap.addMarker(markerOptions)

            googleMap.setOnMapClickListener { latLng ->
                Log.d("myDebug", "MapClick: $latLng")

                marker?.position = latLng

                Toast.makeText(requireContext(), "${marker?.position}", Toast.LENGTH_SHORT).show()
            }

            googleMap.setOnMapLongClickListener { latLng ->
                Log.d("myDebug", "MapLongClick: $latLng")
            }

            googleMap.setOnMarkerClickListener { latLng ->
                Log.d("myDebug", "MapMarkerClick: $latLng")
                true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }



}