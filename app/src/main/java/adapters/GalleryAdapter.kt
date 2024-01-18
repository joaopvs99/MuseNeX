package adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ipca_project.musenex.R
import com.squareup.picasso.Picasso
import android.view.LayoutInflater
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
internal class GalleryAdapter(private var galleryList: Array<String>) :
    RecyclerView.Adapter<GalleryAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.imageView)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.galery_cell, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val galleryItem = galleryList[position]
        Picasso.get().load(galleryItem).into(holder.image)
    }
    override fun getItemCount(): Int {
        return galleryList.size
    }
}