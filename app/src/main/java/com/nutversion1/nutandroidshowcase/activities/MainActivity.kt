package com.nutversion1.nutandroidshowcase.activities

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.nutversion1.nutandroidshowcase.databinding.ActivityMainBinding
import com.nutversion1.nutandroidshowcase.dialogs.MyDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
//        Log.d("myDebug", "resume")
        super.onResume()

        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    override fun onPause() {
//        Log.d("myDebug", "pause")
        super.onPause()

        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

//        Log.d("myDebug", "current focus: ${window.currentFocus}")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        Log.d("myDebug", "onWindowFocusChanged: $hasFocus")
        super.onWindowFocusChanged(hasFocus)

        if(hasFocus){
            window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }else{
            window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        }


    }

    fun showLoadingBar(){
        binding.loadingBar.visibility = View.VISIBLE
    }

    fun hideLoadingBar(){
        binding.loadingBar.visibility = View.GONE
    }

//    override fun onBackPressed() {
//        val dialog = MyDialog()
//        dialog.show(supportFragmentManager, "MyDialog")
//    }

//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            return
//        }
//
//        doubleBackToExitPressedOnce = true
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
//
//        Handler(Looper.getMainLooper()).postDelayed(
//            { doubleBackToExitPressedOnce = false },
//            2000)
//    }

    private fun fetchFcmRegistrationToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("myDebug", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("myDebug", "token: $token")
        })
    }
}