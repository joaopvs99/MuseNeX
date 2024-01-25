package com.ipca_project.musenex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gtappdevelopers.kotlingfgproject.DiscoverEventsAdapter
import model.Event
import model.Museum
import viewModels.DiscoveryViewModel

// testar layouts em construção ainda sem classe criada...

class testedelayouts : AppCompatActivity() {

    var teste : Int = 1

    private lateinit var EventsSeeAllCard: RecyclerView

    //Adapters
    private lateinit var eventsAdapter: DiscoverEventsAdapter


    //lists
    private lateinit var EventList: ArrayList<Event>
    private lateinit var MuseumList: ArrayList<Museum>

    // dataBase
    var viewModel = DiscoveryViewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.see_all)

        viewModel.fetchDiscovery()

        //toolbar settings
        setSupportActionBar(findViewById(R.id.seeAllToolbar))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //call variables
        EventsSeeAllCard = findViewById(R.id.seeAllEvents)

        when(teste){

            0 -> {
                getSupportActionBar()?.setTitle("Todos os Eventos")

                viewModel.events.observe(this, Observer { events ->
                    EventList = ArrayList()
                    for (searchEvent in events){
                        EventList.add(
                            Event(
                                eventId = searchEvent.eventId,
                                name = searchEvent.name,
                                date_event_beg = searchEvent.date_event_beg,
                                date_event_end = searchEvent.date_event_end,
                                description = searchEvent.description,
                                museumId = searchEvent.museumId,
                                galeryEvent = searchEvent.galeryEvent,
                                categoryId = searchEvent.categoryId
                            )
                        )

                    }

                    val layoutManager = GridLayoutManager(this, 2)
                    EventsSeeAllCard.layoutManager = layoutManager

                    // start adapter
                    eventsAdapter = DiscoverEventsAdapter(EventList, MuseumList,this)

                    // turn adapter to recycleView
                    EventsSeeAllCard.adapter = eventsAdapter

                    eventsAdapter.notifyDataSetChanged()
                    }
                )
            }

            1 -> { getSupportActionBar()?.setTitle("Todas as Obras")}
        }




































    }
}