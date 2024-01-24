package com.ipca_project.musenex


import FragmentVisit
import adapters.GalleryAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import Fragments.FragmentCollections
import adapters.DotsIndicatorDecoration
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import model.Event
import model.Museum
import model.Piece
import viewModels.MuseumViewModel

class MuseumPageActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var expandedTextView : TextView
    private lateinit var collectionButton : Button
    private lateinit var visitButton : Button
    private lateinit var viewModel: MuseumViewModel
    private lateinit var pieces: ArrayList<Piece>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_page)

        val museum = intent.getSerializableExtra("Museu") as Museum
        val event = intent.getSerializableExtra("Events") as ArrayList<Event>

        //appBar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setTitle(museum.name)

        //ViewPieces
        viewModel = MuseumViewModel()
        viewModel.pieces.observe(this, Observer { pieces ->
            this.pieces= ArrayList(pieces.filter { it.museumId == museum.museumId })

            val fragment = FragmentCollections()
            val bundle = Bundle()
            bundle.putSerializable("events", event)
            bundle.putSerializable("pieces", this.pieces)
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        })
        viewModel.fetchPieces()

        //Image RecyclerView
        recyclerView = findViewById(R.id.eventsList)
        val galleryAdapter = GalleryAdapter(museum.galery)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = galleryAdapter
        recyclerView.addItemDecoration(
            DotsIndicatorDecoration(
                colorInactive = ContextCompat.getColor(this, R.color.lightGrey),
                colorActive = ContextCompat.getColor(this, R.color.black)
            )
        )

        //Expanded TextView
        expandedTextView = findViewById(R.id.expand_tv)
        expandedTextView.text = museum.description

        //FragmentsViews
        collectionButton = findViewById(R.id.buttonCollections)
        visitButton = findViewById(R.id.buttonVisit)

        // Set initial selection
        setButtonSelected(collectionButton)

        collectionButton.setOnClickListener {

            setButtonSelected(collectionButton)
            setButtonUnselected(visitButton)
            val fragment = FragmentCollections()
            val bundle = Bundle()
            bundle.putSerializable("events", event)
            bundle.putSerializable("pieces", pieces)
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

        visitButton.setOnClickListener {
            setButtonSelected(visitButton)
            setButtonUnselected(collectionButton)
            val fragment = FragmentVisit()
            val bundle = Bundle()
            bundle.putSerializable("museum", museum)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setButtonSelected(button: Button) {
        button.setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    private fun setButtonUnselected(button: Button) {
        button.setTextColor(ContextCompat.getColor(this, R.color.lightGrey))
    }

}