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
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import viewModels.DiscoveryViewModel

open class Discovery : AppCompatActivity() {

    private lateinit var historyButton: LinearLayout
    private lateinit var linearForSearch : LinearLayout
    private lateinit var museumList : ListView
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
        historyButton = findViewById(R.id.buttonHistory)
        museumList = findViewById(R.id.listViewTest)
        linearForSearch = findViewById(R.id.linearForSearch)
        setSupportActionBar(findViewById(R.id.toolBar))
        val carousel: ImageCarousel = findViewById(R.id.carousel)


        val myArrayList = ArrayList<String>()
        myArrayList.add("ola")

        val myListAdapterName = AdapterTest(this, viewModel.museums)
        museumList.adapter = myListAdapterName

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
