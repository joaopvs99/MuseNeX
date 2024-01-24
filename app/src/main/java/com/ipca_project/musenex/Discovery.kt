package com.ipca_project.musenex

import adapters.AdapterDiscovery
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.gtappdevelopers.kotlingfgproject.CategoryAdapter
import com.gtappdevelopers.kotlingfgproject.DiscoveryEventsModal
import com.gtappdevelopers.kotlingfgproject.EventsAdapter
import model.Event
import viewModels.DiscoveryViewModel

open class Discovery : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    // Variables
    private lateinit var MuseumCard: RecyclerView
    private lateinit var EventsCard: RecyclerView
    private lateinit var CategoryCard: RecyclerView
    private lateinit var museumAdapter: AdapterDiscovery
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var linearForSearch : LinearLayout
    private var clickedPosition: Int = 0

    // Lists
    private lateinit var MuseumList: ArrayList<DiscoveryCardView>
    private lateinit var EventList: ArrayList<DiscoveryEventsModal>
    private lateinit var CategoryList: ArrayList<DiscoveryCategoryView>

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
        CategoryCard = findViewById(R.id.DiscoveryCategoriesList)
        linearForSearch = findViewById(R.id.linearForSearch)
        setSupportActionBar(findViewById(R.id.toolBar))

        //Category Buttons
        viewModel.categories.observe(this, Observer { categories ->
            CategoryList = ArrayList()
            for (searchCategory in categories){
                CategoryList.add(DiscoveryCategoryView(searchCategory.name))
            }

            val mLayoutManager = LinearLayoutManager(applicationContext)
            mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            CategoryCard.layoutManager = mLayoutManager

            // start adapter
            categoryAdapter = CategoryAdapter(CategoryList,this, this)

            // turn adapter to recycleView
            CategoryCard.adapter = categoryAdapter

            // notify adapter about data changes
            categoryAdapter.notifyDataSetChanged()
        })

        // Horizontal Cards building (Events)
        viewModel.events.observe(this, Observer { events ->
            EventList = ArrayList()
            for (searchEvent in events){
                EventList.add(DiscoveryEventsModal(
                    EventName = searchEvent.name,
                    EventDateBeg = searchEvent.date_event_beg,
                    EventDateEnd = searchEvent.date_event_end,
                    EventLoc = searchEvent.museumId,
                    EventImg = searchEvent.galeryEvent)
                )

            }

            val mLayoutManager = LinearLayoutManager(applicationContext)
            mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            EventsCard.layoutManager = mLayoutManager
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(EventsCard)

            // start adapter
            eventsAdapter = EventsAdapter(EventList, MuseumList,this)

            // turn adapter to recycleView
            EventsCard.adapter = eventsAdapter
            eventsAdapter.setOnClickListener(object : EventsAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {

                    /*
                    //send data
                    val intent = Intent(this@Discovery,MuseumPageActivity::class.java)
                    intent.putExtra("Museu",MuseumList[1])
                    val filteredEvents: List<Event> = events.filter { it.museumId == museums[1].museumId }
                    intent.putExtra("Events", ArrayList(filteredEvents))
                    startActivity(intent)
                */
                }

            })


            // notify adapter about data changes
            eventsAdapter.notifyDataSetChanged()
        })

        // Vertical Cards Building (Museums)
        viewModel.museums.observe(this, Observer { museums ->
            MuseumList = ArrayList()
            for (searchMuseum in museums){
                MuseumList.add(DiscoveryCardView(
                    MuseumId = searchMuseum.museumId,
                    MuseumName = searchMuseum.name,
                    MuseumImg = searchMuseum.galery))
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
    }

    // button function
    override fun OnItemClick(position: Int) {
        clickedPosition = position

        

        val sharedPreference = getSharedPreferences("PreferencesForTable", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt("positionClicked", position)
        editor.apply()

        // Vertical Cards Building (Museums)
        viewModel.museums.observe(this, Observer { museums ->
            MuseumList = ArrayList()
            for (searchMuseum in museums){

                Toast.makeText(this, searchMuseum.categoryName, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, CategoryList[position].name, Toast.LENGTH_SHORT).show()

                if (searchMuseum.categoryName == CategoryList[position].name) {
                    MuseumList.add(
                        DiscoveryCardView(
                            MuseumId = searchMuseum.museumId,
                            MuseumName = searchMuseum.name,
                            MuseumImg = searchMuseum.galery
                        )
                    )
                }
            }

            // start adapter
            museumAdapter = AdapterDiscovery(MuseumList, this)

            // turn adapter to recycleView
            MuseumCard.adapter = museumAdapter
            museumAdapter.notifyDataSetChanged()
        })

        viewModel.events.observe(this, Observer { events ->
            EventList = ArrayList()
            for (searchEvent in events){
                if (searchEvent.categoryName == CategoryList[position].name){
                    EventList.add(DiscoveryEventsModal(
                        EventName = searchEvent.name,
                        EventDateBeg = searchEvent.date_event_beg,
                        EventDateEnd = searchEvent.date_event_end,
                        EventLoc = searchEvent.museumId,
                        EventImg = searchEvent.galeryEvent))
                }
            }
            // start adapter
            eventsAdapter = EventsAdapter(EventList, MuseumList,this)

            // turn adapter to recycleView
            EventsCard.adapter = eventsAdapter
            // notify adapter about data changes
            eventsAdapter.notifyDataSetChanged()
        })

        categoryAdapter.notifyDataSetChanged()
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

}
