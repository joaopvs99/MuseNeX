package com.ipca_project.musenex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Onboarding1 : AppCompatActivity() {

    // variables
    private lateinit var buttonNext : Button
    private lateinit var buttonSkip : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding1)

        // start variables
        buttonNext = findViewById(R.id.buttonNext)
        buttonSkip = findViewById(R.id.buttonSkip)

        // button function
        clickOnNextButton()
        clickOnSkipButton()
    }

    // clickOnNextButton function
    private fun clickOnNextButton(){
        buttonNext.setOnClickListener {
            startActivity(Intent(this, Onboarding2::class.java).apply {  })
        }
    }

    // clickOnSkipButton function
    private fun clickOnSkipButton(){
        buttonSkip.setOnClickListener {
            startActivity(Intent(this, Discovery::class.java).apply {  })
        }
    }
}