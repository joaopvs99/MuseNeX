package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.DiscoveryCardView
import com.ipca_project.musenex.R

class AdapterDiscovery(

    // variables
    private val museumList: ArrayList<DiscoveryCardView>,
    private val context: Context
) : RecyclerView.Adapter<AdapterDiscovery.MuseumViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MuseumViewHolder {

        // layout inflate
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_style,
            parent, false
        )

        // return viewHolder
        return MuseumViewHolder(itemView)
    }

    // put data on textView
    override fun onBindViewHolder(holder: MuseumViewHolder, position: Int) {
        holder.cardTextView.text = museumList.get(position).museumName
    }

    // return list size
    override fun getItemCount(): Int {
        return museumList.size
    }

    // start TextView
    class MuseumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTextView: TextView = itemView.findViewById(R.id.TextViewCard)
    }
}