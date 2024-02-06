package com.ipca_project.musenex

import adapters.DotsIndicatorDecoration
import adapters.GalleryAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import io.github.glailton.expandabletextview.ExpandableTextView
import model.Event
import viewModels.AnalyticsViewModel

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val event = intent.getSerializableExtra("Events") as? Event

        if (event != null) {
            val analyticsViewModel = AnalyticsViewModel()
            analyticsViewModel.sendEvent("ScreenEvent", event.name)
            //appBar
            setSupportActionBar(findViewById(R.id.my_toolbar))
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()?.setTitle(event.name)

            val recyclerView = findViewById<RecyclerView>(R.id.eventsList)
            val galleryAdapter = GalleryAdapter(event.galeryEvent)
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = galleryAdapter
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
            recyclerView.addItemDecoration(
                DotsIndicatorDecoration(
                    colorInactive = ContextCompat.getColor(this, R.color.lightGrey),
                    colorActive = ContextCompat.getColor(this, R.color.black)
                )
            )

            val expandedTextView = findViewById<ExpandableTextView>(R.id.expand_tv)
            expandedTextView.text = event.description

            val btnBooking = findViewById<Button>(R.id.btnBook)

            btnBooking.setOnClickListener {
                val intent = Intent(this, BookingActivity::class.java)
                startActivity(intent)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}