package com.ipca_project.musenex

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import processing.android.PFragment

class Page3D : FragmentActivity() {

    private var sketch: Sketch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        open3DVisualization()
    }

    private fun open3DVisualization() {
        // Check if the sketch is already running to avoid multiple instances
        if (sketch == null) {
            sketch = Sketch()
            val fragment = PFragment(sketch)

            fragment.setView(findViewById(android.R.id.content), this)

            // Replace the button with the 3D visualization fragment
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}