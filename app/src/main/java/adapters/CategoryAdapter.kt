package com.gtappdevelopers.kotlingfgproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.DiscoveryCardView
import com.ipca_project.musenex.DiscoveryCategoryView
import com.ipca_project.musenex.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat


class CategoryAdapter (

    private val categoryList: ArrayList<DiscoveryCategoryView>,
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.categories_recycle_view,
            parent, false
        )
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.TextViewName.text = categoryList.get(position).name

    }

    override fun getItemCount(): Int {

        return categoryList.size
    }


    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        OnClickListener {

        val TextViewName: TextView = itemView.findViewById(R.id.textCategory)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                listener.OnItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }
}


