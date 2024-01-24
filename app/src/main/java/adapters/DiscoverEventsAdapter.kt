package com.gtappdevelopers.kotlingfgproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.DiscoveryCardView
import com.ipca_project.musenex.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

// on below line we are creating
// a course rv adapter class.
class EventsAdapter(
    // on below line we are passing variables
    // as course list and context
    private val courseList: ArrayList<DiscoveryEventsModal>,
    private val museumList: ArrayList<DiscoveryCardView>,
    private val context: Context
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsAdapter.EventsViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.event_discover_recycler_view,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return EventsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventsAdapter.EventsViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        holder.TextViewName.text = courseList.get(position).EventName



        for(searchMuseum in museumList){
            if (searchMuseum.MuseumId == courseList.get(position).EventLoc) {
                holder.TextViewLoc.text = searchMuseum.MuseumName
            }
        }

        holder.TextViewDateBeg.text = SimpleDateFormat("dd/MM/yyyy").format(courseList.get(position).EventDateBeg)
        holder.TextViewDateEnd.text = SimpleDateFormat("dd/MM/yyyy").format(courseList.get(position).EventDateEnd)
        val galleryItem = courseList.get(position).EventImg
        Picasso.get().load(galleryItem[0]).resize(1920, 1080).centerInside().into(holder.ImageEvent)
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return courseList.size
    }

    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name text view and our image view.
        val TextViewName: TextView = itemView.findViewById(R.id.textView1Name)
        val TextViewLoc: TextView = itemView.findViewById(R.id.textViewLoc)
        val TextViewDateBeg: TextView = itemView.findViewById(R.id.textViewDate)
        val TextViewDateEnd: TextView = itemView.findViewById(R.id.textViewDateEnd)
        val ImageEvent: ImageView = itemView.findViewById(R.id.imageViewEvent)
    }
}
