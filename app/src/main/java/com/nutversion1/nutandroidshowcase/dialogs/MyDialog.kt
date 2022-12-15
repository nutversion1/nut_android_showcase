package com.nutversion1.nutandroidshowcase.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.nutversion1.nutandroidshowcase.R

class MyDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view?.viewTreeObserver?.addOnWindowFocusChangeListener { hasFocus ->
            Log.d("myDebug", "onWindowFocusChange dialog: $hasFocus")

            if(hasFocus){
                activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            }else{
                activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        Log.d("myDebug", "dialog resume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("myDebug", "dialog pause")

        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

    }
}