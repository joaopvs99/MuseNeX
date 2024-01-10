package com.ipca_project.musenex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterDiscovery(
    // on below line we are passing variables
    // as course list and context
    private val museumList: ArrayList<DiscoveryCardView>,
    private val context: Context
) : RecyclerView.Adapter<AdapterDiscovery.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDiscovery.CourseViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.course_rv_item,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterDiscovery.CourseViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        holder.courseNameTV.text = museumList.get(position).courseName
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return museumList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name text view and our image view.
        val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourse)
    }
}