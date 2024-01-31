package com.ipca_project.musenex

import adapters.AdapterDiscovery
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.gtappdevelopers.kotlingfgproject.CategoryAdapter
import com.gtappdevelopers.kotlingfgproject.EventsAdapter
import model.Category
import model.Event
import model.Museum
import viewModels.DiscoveryViewModel

open class Discovery : AppCompatActivity(),
    CategoryAdapter.OnItemClickListener,
    EventsAdapter.OnItemClickListener,
    AdapterDiscovery.OnItemClickListener {

    // Variables
    private lateinit var MuseumCard: RecyclerView
    private lateinit var EventsCard: RecyclerView
    private lateinit var CategoryCard: RecyclerView
    private lateinit var museumAdapter: AdapterDiscovery
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var linearForSearch: LinearLayout
    private var clickedPosition: Int = 0

    // Lists
    private lateinit var MuseumList: ArrayList<Museum>
    private lateinit var EventList: ArrayList<Event>
    private lateinit var CategoryList: ArrayList<Category>

    // connect data base
    var viewModel = DiscoveryViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discovery_page)

        // start data base
        viewModel.fetchDiscovery()

        //Lists
        MuseumList = ArrayList()
        EventList = ArrayList()

        // start layout components
        EventsCard = findViewById(R.id.DiscoveryEventsList)
        MuseumCard = findViewById(R.id.recyclerViewDiscovery)
        CategoryCard = findViewById(R.id.DiscoveryCategoriesList)
        linearForSearch = findViewById(R.id.linearForSearch)
        setSupportActionBar(findViewById(R.id.toolBar))

        //Category Buttons
        viewModel.categories.observe(this, Observer { categories ->
            CategoryList = ArrayList()
            CategoryList.add(Category("1000000", "Todas"))
            for (searchCategory in categories) {
                CategoryList.add(
                    Category(
                        categoryId = searchCategory.categoryId,
                        name = searchCategory.name
                    )
                )
            }

            val mLayoutManager = LinearLayoutManager(applicationContext)
            mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            CategoryCard.layoutManager = mLayoutManager

            // start adapter
            categoryAdapter = CategoryAdapter(CategoryList, this, this)

            // turn adapter to recycleView
            CategoryCard.adapter = categoryAdapter

            // notify adapter about data changes
            categoryAdapter.notifyDataSetChanged()
        })

        val mLayoutManager = LinearLayoutManager(applicationContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        EventsCard.layoutManager = mLayoutManager
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(EventsCard)

        val sharedPreference = getSharedPreferences("PreferencesForTable", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt("positionClicked", 0)
        editor.apply()

        fetchAll()
    }

    fun fetchAll() {
        // Horizontal Cards building (Events)
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
        })

        // Vertical Cards Building (Museums)
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

        // start adapter
        eventsAdapter = EventsAdapter(EventList, MuseumList, this, this, 1)

        // turn adapter to recycleView
        EventsCard.adapter = eventsAdapter

        // notify adapter about data changes
        eventsAdapter.notifyDataSetChanged()


        // Define gridLayout
        val layoutManager = GridLayoutManager(this, 2)
        MuseumCard.layoutManager = layoutManager

        // start adapter
        museumAdapter = AdapterDiscovery(MuseumList, EventList, this, this)

        // turn adapter to recycleView
        MuseumCard.adapter = museumAdapter

        // notify adapter about data changes
        museumAdapter.notifyDataSetChanged()

    }

    // button function
    override fun onItemClick(position: Int) {
        clickedPosition = position

        val sharedPreference = getSharedPreferences("PreferencesForTable", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt("positionClicked", position)
        editor.apply()
        if (position != 0) {
            viewModel.museums.observe(this, Observer { museums ->
                MuseumList = ArrayList()
                for (searchMuseum in museums) {
                    if (searchMuseum.categoryName == CategoryList[position].name) {
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
                }

                // start adapter
                museumAdapter = AdapterDiscovery(MuseumList, EventList, this, this)

                // turn adapter to recycleView
                MuseumCard.adapter = museumAdapter

                museumAdapter.notifyDataSetChanged()
            })

            viewModel.events.observe(this, Observer { events ->
                EventList = ArrayList()
                for (searchEvent in events) {
                    if (searchEvent.categoryName == CategoryList[position].name) {
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
                }

                // start adapter
                eventsAdapter = EventsAdapter(EventList, MuseumList, this, this, 1)

                // turn adapter to recycleView
                EventsCard.adapter = eventsAdapter
                // notify adapter about data changes
                eventsAdapter.notifyDataSetChanged()
            })
        } else {
            fetchAll()
        }
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

    override fun onItemClickMuseum(position: Int) {

    }

    override fun onItemClickEvents(position: Int) {

    }


}
