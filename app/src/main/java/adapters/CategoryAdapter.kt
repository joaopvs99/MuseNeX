package com.gtappdevelopers.kotlingfgproject

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.R
import model.Category


class CategoryAdapter (

    private val categoryList: ArrayList<Category>,
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

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        OnClickListener {

        val TextViewName: TextView = itemView.findViewById(R.id.textCategory)
        val CategoryButton: LinearLayout = itemView.findViewById(R.id.categoryButton)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                listener.OnItemClick(position)
            }

            CategoryButton.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border_filled))
            TextViewName.setTextColor(Color.WHITE)
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val TextViewName: TextView = holder.TextViewName
        val CategoryButton: LinearLayout = holder.CategoryButton

       var ClickedPosition = context.getSharedPreferences("PreferencesForTable",Context.MODE_PRIVATE).getInt("positionClicked", -1)
        if (ClickedPosition != position){
            CategoryButton.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border))
            TextViewName.setTextColor(Color.BLACK)
        }
        holder.TextViewName.text = categoryList.get(position).name
    }

    override fun getItemCount(): Int {

        return categoryList.size
    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }
}


