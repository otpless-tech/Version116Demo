package com.anubhav.app

// only if you are not using your custom ui whatsapp button
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.otpless.dto.OtplessResponse
import com.otpless.views.OtplessManager
import com.otpless.views.OtplessWhatsappButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Call this code to firstly register your callback from the sdk
        // Call this code to firstly register your callback from the sdk
        OtplessManager.registerCallback(this, this::onOtplessResult)
        // Add this code If you are using custom deeplink in your manifest
        // else DONT ADD THIS CODE
        // Add this code If you are using custom deeplink in your manifest
        // else DONT ADD THIS CODE
        // if you are using OTPLESSWhastappButton set the Onclick listener
        // and initiate the whatsapp login on click
        // if you are using OTPLESSWhastappButton set the Onclick listener
        // and initiate the whatsapp login on click
        val button = findViewById<View>(R.id.whatsapp_login) as OtplessWhatsappButton
        button.setOnClickListener { v: View? ->
            //initiate whatsapp login by passing your authlink
            // like https://anubhav.authlink.me
            OtplessManager.openOtpless(this, Uri.parse("https://anubhav.authlink.me"))
        }
        // Call this code to handle redirection to the app
        // Call this code to handle redirection to the app
        OtplessManager.verify(this, intent, this::onOtplessResult)
    }

    // Callback method
    private fun onOtplessResult(userDetail: OtplessResponse?) {
        if (userDetail == null || userDetail.waId == null) {
            Toast.makeText(this, "data error", Toast.LENGTH_LONG).show()
            return
        }
        // send this id to your backend to fetch user detail from OTPLESS server
        val waId = userDetail.waId
        Toast.makeText(this, waId, Toast.LENGTH_LONG).show()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        OtplessManager.verify(this, intent, ::onOtplessResult)
    }

    override fun onBackPressed() {
        OtplessManager.onBackPressed(this);
        super.onBackPressed();
    }
}