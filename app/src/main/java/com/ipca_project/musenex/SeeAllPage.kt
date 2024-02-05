package com.ipca_project.musenex

import adapters.AdapterDiscovery
import adapters.AdapterSeeAll
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gtappdevelopers.kotlingfgproject.CategoryAdapter
import com.gtappdevelopers.kotlingfgproject.EventsAdapter
import model.Event
import model.Museum
import model.Piece
import viewModels.DiscoveryViewModel
import viewModels.MuseumViewModel

class SeeAllPage : AppCompatActivity(),
    CategoryAdapter.OnItemClickListener,
    EventsAdapter.OnItemClickListener,
    AdapterDiscovery.OnItemClickListener {

    var tipo: Int = 1

    private lateinit var EventsSeeAllCard: RecyclerView

    //Adapters
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var adapterSeeAll: AdapterSeeAll


    //lists
    private lateinit var EventList: ArrayList<Event>
    private lateinit var MuseumList: ArrayList<Museum>
    private lateinit var PieceList: ArrayList<Piece>

    // dataBase
    var viewModel = DiscoveryViewModel()
    var pieceModel = MuseumViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.see_all)

        viewModel.fetchDiscovery()
        pieceModel.fetchPieces()

        //toolbar settings
        setSupportActionBar(findViewById(R.id.seeAllToolbar))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //call variables
        EventsSeeAllCard = findViewById(R.id.seeAllEvents)

        //Lists
        PieceList = ArrayList()
        MuseumList = ArrayList()
        EventList = ArrayList()

        when (tipo) {

            0 -> {
                getSupportActionBar()?.setTitle("Todos os Eventos")

                viewModel.museums.observe(this, Observer { museums ->
                    MuseumList = ArrayList()
                    for (searchMuseum in museums) {
                        MuseumList.add(
                            Museum(
                                museumId = searchMuseum.museumId,
                                name = searchMuseum.name,
                                location = searchMuseum.location,
                                description = searchMuseum.description,
                                contact = searchMuseum.contact,
                                categoryId = searchMuseum.categoryId,
                                galery = searchMuseum.galery
                            )
                        )
                    }
                })

                viewModel.events.observe(this, Observer { events ->
                    EventList = ArrayList()
                    for (searchEvent in events) {
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

                    val layoutManager = GridLayoutManager(this, 1)
                    EventsSeeAllCard.layoutManager = layoutManager

                    // start adapter
                    eventsAdapter = EventsAdapter(EventList, MuseumList, this, this, 0)

                    // turn adapter to recycleView
                    EventsSeeAllCard.adapter = eventsAdapter

                    eventsAdapter.notifyDataSetChanged()
                }
                )

            }

            1 -> {
                getSupportActionBar()?.setTitle("Todas as Obras")

                PieceList.add(
                    Piece(
                        "ola",
                        "aefd",
                        "ef",
                        "dgfse",
                        "eiusfg",
                        "iuygef",
                        "ewgf",
                        "kjhgf",
                        "igefy"
                    )
                )
                pieceModel.pieces.observe(this, Observer { pieces ->
                    PieceList = ArrayList()
                    for (searchPieces in pieces) {
                        PieceList.add(
                            Piece(
                                pieceId = searchPieces.pieceId,
                                name = searchPieces.name,
                                description = searchPieces.description,
                                authorId = searchPieces.authorId,
                                museumId = searchPieces.museumId,
                                categoryId = searchPieces.categoryId,
                                audio_url = searchPieces.audio_url,
                                foto_url = searchPieces.foto_url,
                                engDescription = searchPieces.engDescription
                            )
                        )
                    }
                    val layoutManager = GridLayoutManager(this, 2)
                    EventsSeeAllCard.layoutManager = layoutManager

                    // start adapter
                    adapterSeeAll = AdapterSeeAll(PieceList)

                    // turn adapter to recycleView
                    EventsSeeAllCard.adapter = adapterSeeAll

                    adapterSeeAll.notifyDataSetChanged()

                })
            }
        }
    }

    override fun onItemClick(position: Int) {

    }

    override fun onItemClickMuseum(position: Int) {

    }

    override fun onItemClickEvents(position: Int) {

    }
}