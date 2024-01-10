package com.ipca_project.musenex

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdapterTest(private val context: Activity, private val title: ArrayList<String>) : ArrayAdapter<String>(context, R.layout.teste_firebase, title) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.teste_firebase, null, true)

        var test = rowView.findViewById(R.id.textView) as TextView

        test.text = title[position]

        return rowView
    }
}