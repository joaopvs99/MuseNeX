package com.ipca_project.musenex

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.Museum
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import viewModels.DiscoveryViewModel

open class Discovery : AppCompatActivity() {
    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: AdapterDiscovery
    lateinit var courseList: ArrayList<DiscoveryCardView>
    private lateinit var historyButton: LinearLayout
    private lateinit var linearForSearch : LinearLayout
    //private lateinit var museumList : ListView

    var viewModel = DiscoveryViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discovery_page)

        // base de dados

        viewModel.fetchDiscovery()

        viewModel.events.observe(this, Observer { events ->
            Toast.makeText(applicationContext, "Entrei aqui!!", Toast.LENGTH_SHORT)
        })

        viewModel.museums.observe(this, Observer { museums ->

        })

            //-----------------------------------------------------------------------------------------------

        courseRV = findViewById(R.id.idTVCourse)




        //----------------------------------------------------------------------------------------------------
        historyButton = findViewById(R.id.buttonHistory)
        //museumList = findViewById(R.id.listViewTest)
        linearForSearch = findViewById(R.id.linearForSearch)
        setSupportActionBar(findViewById(R.id.toolBar))
        val carousel: ImageCarousel = findViewById(R.id.carousel)


        //val myArrayList = ArrayList<Museum>()
        viewModel.museums.observe(this, Observer { museums ->
            for (searchMuseum in museums){
                courseList.add(DiscoveryCardView(searchMuseum.name))
            }

            // on below line we are initializing our list
            courseList = ArrayList()

            val layoutManager = GridLayoutManager(this, 2)

            courseRV.layoutManager = layoutManager

            // on below line we are initializing our adapter
            courseRVAdapter = AdapterDiscovery(courseList, this)

            // on below line we are setting
            // adapter to our recycler view.
            courseRV.adapter = courseRVAdapter

            // on below line we are adding data to our list

            // on below line we are notifying adapter
            // that data has been updated.
            courseRVAdapter.notifyDataSetChanged()
            //val myListAdapterName = AdapterTest(this, courseList)
            //museumList.adapter = myListAdapterName
        })



        val list = mutableListOf<CarouselItem>()
        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080"

            )
        )
        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
            )
        )
        carousel.addData(list)


        //clickOnHistoryButton()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> if (linearForSearch.isVisible)
                linearForSearch.visibility = View.GONE
            else
                linearForSearch.visibility = View.VISIBLE

        }

        return super.onOptionsItemSelected(item)
    }

    /*fun clickOnHistoryButton(){
        historyButton.setOnClickListener {
            viewModel.museums.observe(this, Observer { museums ->




            for (searchMuseum in museums){
                    Toast.makeText(applicationContext, searchMuseum.name, Toast.LENGTH_SHORT).show()
                }

            })
        }*/
    }
