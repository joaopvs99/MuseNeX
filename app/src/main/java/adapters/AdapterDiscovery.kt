package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.R
import com.squareup.picasso.Picasso
import model.Museum

class AdapterDiscovery(

    // variables
    private val museumList: ArrayList<Museum>,
    private val context: Context
) : RecyclerView.Adapter<AdapterDiscovery.MuseumViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MuseumViewHolder {

        // layout inflate
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_museum_style,
            parent, false
        )

        // return viewHolder
        return MuseumViewHolder(itemView)
    }

    class MuseumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTextView: TextView = itemView.findViewById(R.id.textViewCard)
        val cardImageView: ImageView = itemView.findViewById(R.id.imageViewMuseum)
    }

    // put data on textView
    override fun onBindViewHolder(holder: MuseumViewHolder, position: Int) {
        holder.cardTextView.text = museumList.get(position).name
        val galleryItem = museumList.get(position).galery
        Picasso.get().load(galleryItem[0]).resize(1920, 1080).centerInside().into(holder.cardImageView)
    }

    // return list size
    override fun getItemCount(): Int {
        return museumList.size
    }
}