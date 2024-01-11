package com.ipca_project.musenex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import viewModels.DiscoveryViewModel

// kinda inutil esta main mas n sei se vai ser usada por isso...


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding1)
        viewModel.museums.observe(this, Observer { museums ->
            Toast. makeText(applicationContext,museums[0].toString(),Toast. LENGTH_SHORT)
        })
        viewModel.events.observe(this, Observer { events ->
            Toast. makeText(applicationContext,"Entrei aqui!!",Toast. LENGTH_SHORT)
        })
        viewModel.fetchDiscovery()
    }

    var viewModel = DiscoveryViewModel()
}