package com.ipca_project.musenex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Onboarding1 : AppCompatActivity() {

    private lateinit var buttonNext : Button
    private lateinit var buttonSkip : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding1)

        buttonNext = findViewById(R.id.buttonNext)
        buttonSkip = findViewById(R.id.buttonSkip)

        clickOnNextButton()
        clickOnSkipButton()
    }

    private fun clickOnNextButton(){
        buttonNext.setOnClickListener {
            startActivity(Intent(this, Onboarding2::class.java).apply {  })
        }
    }

    private fun clickOnSkipButton(){
        buttonSkip.setOnClickListener {
            startActivity(Intent(this, Discovery::class.java).apply {  })
        }
    }
}