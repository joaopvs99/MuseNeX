package com.ipca_project.musenex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import model.Event
import model.Museum
import viewModels.DiscoveryViewModel

// kinda inutil esta main mas n sei se vai ser usada por isso...


class MainActivity : AppCompatActivity() {

    var museums: ArrayList<Museum> = arrayListOf()
    var events: ArrayList<Event> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding1)
        viewModel.museums.observe(this, Observer { museums ->
            if (events.isNotEmpty()) {
                val intent = Intent(this,MuseumPageActivity::class.java)
                intent.putExtra("Museu",museums[0])
                val filteredEvents: List<Event> = events.filter { it.museumId == museums[0].museumId }
                intent.putExtra("Events", ArrayList(filteredEvents))
                startActivity(intent)
            }
            this.museums = museums
        })
        viewModel.events.observe(this, Observer { events ->
            if (museums.isNotEmpty()) {
                val intent = Intent(this,MuseumPageActivity::class.java)
                intent.putExtra("Museu",museums[0])
                val filteredEvents: List<Event> = events.filter { it.museumId == museums[0].museumId }
                intent.putExtra("Events", ArrayList(filteredEvents))
                startActivity(intent)
            }
            this.events = events
        })
        viewModel.fetchDiscovery()
    }

    var viewModel = DiscoveryViewModel()
}