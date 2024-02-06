package com.ipca_project.musenex

import adapters.AdapterDiscovery
import adapters.AdapterSeeAll
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    var tipo: Int = -1

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

        var Intent1: Intent = getIntent()

        tipo = Intent1.getIntExtra("tipo", -1)


        when (tipo) {

            0 -> {
                getSupportActionBar()?.setTitle("Todos os Eventos")

                var Intent1: Intent
                Intent1 = getIntent()

                var receivedEvent =
                    Intent1.getSerializableExtra("receivedEvents") as ArrayList<Piece>

                val layoutManager = GridLayoutManager(this, 2)
                EventsSeeAllCard.layoutManager = layoutManager

                // start adapter
                adapterSeeAll = AdapterSeeAll(receivedEvent)

                // turn adapter to recycleView
                EventsSeeAllCard.adapter = adapterSeeAll

                adapterSeeAll.notifyDataSetChanged()

            }

            1 -> {
                getSupportActionBar()?.setTitle("Todas as Obras")

                var Intent1: Intent
                Intent1 = getIntent()

                var receivedPieces =
                    Intent1.getSerializableExtra("receivedPieces") as ArrayList<Piece>

                val layoutManager = GridLayoutManager(this, 2)
                EventsSeeAllCard.layoutManager = layoutManager

                // start adapter
                adapterSeeAll = AdapterSeeAll(receivedPieces)

                // turn adapter to recycleView
                EventsSeeAllCard.adapter = adapterSeeAll

                adapterSeeAll.notifyDataSetChanged()


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