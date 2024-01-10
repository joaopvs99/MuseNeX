package com.ipca_project.musenex

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import model.Museum

class AdapterTest(private val context: Activity, private val title: MutableLiveData<ArrayList<Museum>>) : ArrayAdapter<MutableList<Museum>>(context, R.layout.teste_firebase, title) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.teste_firebase, null, true)

        var test = rowView.findViewById(R.id.textView) as TextView

        //test.text = title[position]

        return rowView
    }
}