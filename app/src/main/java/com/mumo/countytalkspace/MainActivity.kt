package com.mumo.countytalkspace

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN = 123
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        if(shouldStartSignIn()){
            startSignIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN){
            if(resultCode != Activity.RESULT_OK && shouldStartSignIn()){
                startSignIn()
            }
        }
    }


    private fun startSignIn() {
        val intent = AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(Collections.singletonList(
                AuthUI.IdpConfig.EmailBuilder().build()
            ))
            .setIsSmartLockEnabled(false, true)
            .build()
        startActivityForResult(intent, RC_SIGN)
    }

    private  fun shouldStartSignIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser == null
//                viewModel
    }

}
