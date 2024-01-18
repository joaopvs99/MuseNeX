package com.gtappdevelopers.kotlingfgproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.DiscoveryCardView
import com.ipca_project.musenex.DiscoveryCategoryView
import com.ipca_project.musenex.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

// on below line we are creating
// a course rv adapter class.
class CategoryAdapter(
    // on below line we are passing variables
    // as course list and context
    private val categoryList: ArrayList<DiscoveryCategoryView>,
    private val context: Context
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.categories_recycle_view,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.TextViewName.text = categoryList.get(position).name
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return categoryList.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name text view and our image view.
        val TextViewName: TextView = itemView.findViewById(R.id.textCategory)

    }
}
