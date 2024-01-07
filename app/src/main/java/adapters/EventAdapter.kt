package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.ipca_project.musenex.R
import com.squareup.picasso.Picasso
import model.Event

internal class EventAdapter (private var eventList: ArrayList<Event>) :
    RecyclerView.Adapter<EventAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.imageView)
        var textView: TextView = view.findViewById(R.id.textView2)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_recycler_view, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val galleryItem = eventList[position].galeryEvent[0]
        Picasso.get().load(galleryItem).resize(120, 120).centerInside().into(holder.image)
        holder.textView.text = eventList[position].name
    }
    override fun getItemCount(): Int {
        return eventList.size
    }
}