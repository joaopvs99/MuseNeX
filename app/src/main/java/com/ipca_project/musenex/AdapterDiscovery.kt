package com.ipca_project.musenex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterDiscovery(

    // variables
    private val museumList: ArrayList<DiscoveryCardView>,
    private val context: Context
) : RecyclerView.Adapter<AdapterDiscovery.CourseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDiscovery.CourseViewHolder {

        // layout inflate
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_style,
            parent, false
        )

        // return viewHolder
        return CourseViewHolder(itemView)
    }

    // put data on textView
    override fun onBindViewHolder(holder: AdapterDiscovery.CourseViewHolder, position: Int) {
        holder.cardTextView.text = museumList.get(position).museumName
    }

    // return list size
    override fun getItemCount(): Int {
        return museumList.size
    }

    // start TextView
    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTextView: TextView = itemView.findViewById(R.id.TextViewCard)
    }
}