package com.ipca_project.musenex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Onboarding2 : AppCompatActivity() {


    private lateinit var getStarted : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding2)

        getStarted = findViewById(R.id.buttonGetStarted)


        clickOnGetStartedButton()
    }
    private fun clickOnGetStartedButton(){
        getStarted.setOnClickListener {
            startActivity(Intent(this, Discovery::class.java).apply {  })
        }
    }
}