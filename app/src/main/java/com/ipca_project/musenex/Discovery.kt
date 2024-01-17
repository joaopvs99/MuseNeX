package com.ipca_project.musenex

import adapters.AdapterDiscovery
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gtappdevelopers.kotlingfgproject.DiscoveryEventsModal
import com.gtappdevelopers.kotlingfgproject.EventsAdapter
import viewModels.DiscoveryViewModel

open class Discovery : AppCompatActivity() {

    // Variables
    private lateinit var MuseumCard: RecyclerView
    private lateinit var EventsCard: RecyclerView
    private lateinit var museumAdapter: AdapterDiscovery
    private lateinit var eventsAdapter: EventsAdapter
    //private lateinit var historyButton: LinearLayout
    //private lateinit var historyButtonText: TextView
    private lateinit var linearForSearch : LinearLayout


    // Lists
    private lateinit var MuseumList: ArrayList<DiscoveryCardView>
    private lateinit var EventList: ArrayList<DiscoveryEventsModal>

    // connect data base
    var viewModel = DiscoveryViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discovery_page)

        // start data base
        viewModel.fetchDiscovery()

        // start layout components
        EventsCard = findViewById(R.id.DiscoveryEventsList)
        MuseumCard = findViewById(R.id.recyclerViewDiscovery)
        //historyButton = findViewById(R.id.buttonHistory)
        //historyButtonText = findViewById(R.id.textHistory)
        linearForSearch = findViewById(R.id.linearForSearch)
        setSupportActionBar(findViewById(R.id.toolBar))

        // Add museum names
        viewModel.museums.observe(this, Observer { museums ->
            MuseumList = ArrayList()
            for (searchMuseum in museums){
                MuseumList.add(DiscoveryCardView(searchMuseum.museumId, searchMuseum.name))
            }

            // Define gridLayout
            val layoutManager = GridLayoutManager(this, 2)
            MuseumCard.layoutManager = layoutManager

            // start adapter
            museumAdapter = AdapterDiscovery(MuseumList, this)

            // turn adapter to recycleView
            MuseumCard.adapter = museumAdapter

            // notify adapter about data changes
            museumAdapter.notifyDataSetChanged()
        })

        // carousel building
        viewModel.events.observe(this, Observer { events ->
            EventList = ArrayList()
            for (searchEvent in events){
                EventList.add(DiscoveryEventsModal(EventName = searchEvent.name, EventDate = searchEvent.date_event_beg, EventLoc = searchEvent.museumId, EventImg = searchEvent.galeryEvent))
            }

            val mLayoutManager = LinearLayoutManager(applicationContext)
            mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            EventsCard.layoutManager = mLayoutManager

            // start adapter
            eventsAdapter = EventsAdapter(EventList, MuseumList,this)

            // turn adapter to recycleView
            EventsCard.adapter = eventsAdapter

            // notify adapter about data changes
            eventsAdapter.notifyDataSetChanged()
        })





        // button function
        //clickOnHistoryButton()
    }

    // menu inflate
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Show search zone
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> if (linearForSearch.isVisible)
                linearForSearch.visibility = View.GONE
            else
                linearForSearch.visibility = View.VISIBLE
        }
        return super.onOptionsItemSelected(item)
    }

    // clickOnHistoryButton function
    /*fun clickOnHistoryButton(){
        historyButton.setOnClickListener {
            historyButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_clicked))
            historyButtonText.setTextColor(Color.WHITE)
        }
    }*/
}
