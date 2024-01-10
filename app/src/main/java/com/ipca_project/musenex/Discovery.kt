package com.ipca_project.musenex

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.Museum
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import viewModels.DiscoveryViewModel

open class Discovery : AppCompatActivity() {

    // Variaveis
    private lateinit var courseRV: RecyclerView
    private lateinit var courseRVAdapter: AdapterDiscovery
    private lateinit var historyButton: LinearLayout
    private lateinit var historyButtonText: TextView
    private lateinit var linearForSearch : LinearLayout
    private lateinit var carousel: ImageCarousel

    // Listas
    private lateinit var courseList: ArrayList<DiscoveryCardView>

    // Ligação base de dados
    var viewModel = DiscoveryViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discovery_page)

        // Iniciar Base de dados
        viewModel.fetchDiscovery()

        // Iniciar componentes do layout
        courseRV = findViewById(R.id.recyclerViewDiscovery)
        historyButton = findViewById(R.id.buttonHistory)
        historyButtonText = findViewById(R.id.textHistory)
        linearForSearch = findViewById(R.id.linearForSearch)
        setSupportActionBar(findViewById(R.id.toolBar))
        carousel = findViewById(R.id.carousel)

        // Adicionar nome dos museus a lista
        viewModel.museums.observe(this, Observer { museums ->
            courseList = ArrayList()
            for (searchMuseum in museums){
                courseList.add(DiscoveryCardView(searchMuseum.name))
            }

            // Definir gridLayout
            val layoutManager = GridLayoutManager(this, 2)
            courseRV.layoutManager = layoutManager

            // Iniciar o adapter
            courseRVAdapter = AdapterDiscovery(courseList, this)

            // focar adapter na recycleView
            courseRV.adapter = courseRVAdapter

            // Notificar o adapter sobre a alteração dos dados
            courseRVAdapter.notifyDataSetChanged()
        })

        // Criação do carousel
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

        // Funções de butões
        clickOnHistoryButton()
    }

    // dar inflate ao menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Mostrar zona de pesquisa
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> if (linearForSearch.isVisible)
                linearForSearch.visibility = View.GONE
            else
                linearForSearch.visibility = View.VISIBLE
        }
        return super.onOptionsItemSelected(item)
    }

    //função de clicar no botão history
    fun clickOnHistoryButton(){
        historyButton.setOnClickListener {
            historyButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_clicked))
            historyButtonText.setTextColor(Color.WHITE)
        }
    }
}
