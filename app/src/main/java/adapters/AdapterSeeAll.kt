package adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.EventActivity
import com.ipca_project.musenex.PiecePageActivity
import com.ipca_project.musenex.R
import com.squareup.picasso.Picasso
import model.Event
import model.Piece

class AdapterSeeAll(

    private var context: Context,
    private var pieceList: ArrayList<Piece>,
    private var eventList: ArrayList<Event>

) : RecyclerView.Adapter<AdapterSeeAll.SeeAllViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SeeAllViewHolder {

        var itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.event_recycler_view, parent, false
        )

        return SeeAllViewHolder(itemView)
    }

    inner class SeeAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val TextViewName: TextView = itemView.findViewById(R.id.textView2)
        val ImagePiece: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            /*val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClickEvents(position)
                val intent = Intent(context, MuseumPageActivity::class.java)
                intent.putExtra("Museu", museumList[position])
                val filteredEvents: List<Event> =
                    eventList.filter { it.museumId == museumList[position].museumId }
                intent.putExtra("Events", ArrayList(filteredEvents))
                context.startActivity(intent)
            }*/
        }

    }


    override fun onBindViewHolder(holder: AdapterSeeAll.SeeAllViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        if(pieceList.isNotEmpty()) {
            holder.TextViewName.text = pieceList.get(position).name
            val galleryItem = pieceList[position].foto_url
            Picasso.get().load(galleryItem).resize(500, 500).centerInside().into(holder.ImagePiece)

            holder.itemView.setOnClickListener {
                val intent = Intent(context, PiecePageActivity::class.java)
                intent.putExtra("piece", pieceList[position])
                context.startActivity(intent)
            }
        }
        if(eventList.isNotEmpty()) {
            holder.TextViewName.text = eventList.get(position).name
            val galleryItem = eventList[position].galeryEvent[0]
            Picasso.get().load(galleryItem).resize(500, 500).centerInside().into(holder.ImagePiece)

            holder.itemView.setOnClickListener {
                val intent = Intent(context, EventActivity::class.java)
                intent.putExtra("Events", eventList[position])
                context.startActivity(intent)
            }
        }


    }

    override fun getItemCount(): Int {
        if(pieceList.isNotEmpty()) {
            return  pieceList.size
        }
        return eventList.size
    }

    interface OnItemClickListener {
        fun onItemClickEvents(position: Int)
    }

}