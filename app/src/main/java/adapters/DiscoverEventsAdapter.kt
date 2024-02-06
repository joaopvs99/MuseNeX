package com.gtappdevelopers.kotlingfgproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.EventActivity
import com.ipca_project.musenex.R
import com.squareup.picasso.Picasso
import model.Event
import model.Museum
import java.text.SimpleDateFormat

class EventsAdapter(
    private val eventList: ArrayList<Event>,
    private val museumList: ArrayList<Museum>,
    private val context: Context,
    private val listener: OnItemClickListener,
    private val type: Int
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsAdapter.EventsViewHolder {

        var itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.event_discover_recycler_view,
            parent, false
        )
        if (type == 0) {
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.see_all_recycler_view,
                parent, false
            )
        }

        return EventsViewHolder(itemView)
    }

    inner class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        // on below line we are initializing our course name text view and our image view.

        val TextViewName: TextView = itemView.findViewById(R.id.textView1Name)
        val TextViewLoc: TextView = itemView.findViewById(R.id.textViewLoc)
        val TextViewDateBeg: TextView = itemView.findViewById(R.id.textViewDate)
        val TextViewDateEnd: TextView = itemView.findViewById(R.id.textViewDateEnd)
        val ImageEvent: ImageView = itemView.findViewById(R.id.imageViewEvent)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClickEvents(position)
                val intent = Intent(context, EventActivity::class.java)
                intent.putExtra("Events", eventList[position])
                context.startActivity(intent)

            }
        }

    }

    override fun onBindViewHolder(holder: EventsAdapter.EventsViewHolder, position: Int) {

        // on below line we are setting data to our text view and our image view.
        holder.TextViewName.text = eventList.get(position).name
        for (searchMuseum in museumList) {
            if (searchMuseum.museumId == eventList.get(position).museumId) {
                holder.TextViewLoc.text = searchMuseum.name
            }
        }

        holder.TextViewDateBeg.text =
            SimpleDateFormat("dd/MM/yyyy").format(eventList.get(position).date_event_beg)
        holder.TextViewDateEnd.text =
            SimpleDateFormat("dd/MM/yyyy").format(eventList.get(position).date_event_end)
        val galleryItem = eventList.get(position).galeryEvent
        Picasso.get().load(galleryItem[0]).resize(1920, 1080).centerInside().into(holder.ImageEvent)
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return eventList.size
    }

    interface OnItemClickListener {
        fun onItemClickEvents(position: Int)
    }

}
