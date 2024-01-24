package com.ipca_project.musenex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import model.Event
import model.Museum
import viewModels.DiscoveryViewModel

class MainActivity : AppCompatActivity() {
    //Declaração de variáveis
    var museums: ArrayList<Museum> = arrayListOf()
    var events: ArrayList<Event> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //View Model Museus
        viewModel.museums.observe(this, Observer { museums ->
            if (events.isNotEmpty()) {
                val intent = Intent(this,MuseumPageActivity::class.java)
                intent.putExtra("Museu",museums[1])
                val filteredEvents: List<Event> = events.filter { it.museumId == museums[1].museumId }
                intent.putExtra("Events", ArrayList(filteredEvents))
                startActivity(intent)
            }
            this.museums = museums
        })

        //View Model Eventos
        viewModel.events.observe(this, Observer { events ->
            if (museums.isNotEmpty()) {
                val intent = Intent(this,MuseumPageActivity::class.java)
                intent.putExtra("Museu",museums[1])
                val filteredEvents: List<Event> = events.filter { it.museumId == museums[1].museumId }
                intent.putExtra("Events", ArrayList(filteredEvents))
                startActivity(intent)
            }
            this.events = events
        })
        viewModel.fetchDiscovery()

    }
    var viewModel = DiscoveryViewModel()
}