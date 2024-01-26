package com.ipca_project.musenex

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.braintreepayments.cardform.view.CardForm


class BookingActivity : AppCompatActivity() {
    private lateinit var btnBooking: Button
    private lateinit var containerView: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_page)

        //AppBar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle("Reservar")

        //Payment Card
        val cardForm = findViewById<View>(R.id.card_form) as CardForm
        cardForm.cardRequired(true)
            .expirationRequired(true)
            .cvvRequired(true)
            .cardholderName(CardForm.FIELD_REQUIRED)
            .postalCodeRequired(true)
            .mobileNumberRequired(true)
            .mobileNumberExplanation("SMS is required on this number")
            .actionLabel("Purchase")
            .setup(this)

        containerView = findViewById(R.id.linearLayout)

        containerView.setOnClickListener  {
            hideKeyboard()
        }

        //Booking Button
        btnBooking = findViewById(R.id.btnBook)

        btnBooking.setOnClickListener {
            hideKeyboard()
            Toast.makeText(this,"Reservado com sucesso",Toast.LENGTH_LONG).show()
            finish()
        }

    }
    fun hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
