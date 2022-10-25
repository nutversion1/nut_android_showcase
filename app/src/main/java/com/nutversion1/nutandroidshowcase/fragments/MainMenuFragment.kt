package com.nutversion1.nutandroidshowcase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.nutversion1.nutandroidshowcase.R

class MainMenuFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.fun_with_api_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_fun_with_api_fragment_action)
        )

        view.findViewById<Button>(R.id.about_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.go_to_about_fragment_action)
        )
    }
}