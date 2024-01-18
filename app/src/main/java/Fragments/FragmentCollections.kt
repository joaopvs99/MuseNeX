package Fragments

import adapters.EventAdapter
import adapters.PieceAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.ipca_project.musenex.R
import model.Event
import model.Piece

class FragmentCollections: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.collection_view, container, false)

        val receivedData = arguments?.getSerializable("events") as ArrayList<Event>
        val receivedPieces = arguments?.getSerializable("pieces") as ArrayList<Piece>

        // Configurar RecyclerView para eventos
        val eventsRecyclerView: RecyclerView = view.findViewById(R.id.eventsList)
        val eventsLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        eventsRecyclerView.layoutManager = eventsLayoutManager
        val eventsAdapter = EventAdapter(receivedData)
        eventsRecyclerView.adapter = eventsAdapter
        var snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(eventsRecyclerView)

        // Configurar RecyclerView para obras
        val artworksRecyclerView: RecyclerView = view.findViewById(R.id.piecesView)
        val artworksLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        artworksRecyclerView.layoutManager = artworksLayoutManager
        val artworksAdapter = PieceAdapter(requireContext(), receivedPieces)
        artworksRecyclerView.adapter = artworksAdapter
        snapHelper.attachToRecyclerView(artworksRecyclerView)

        return view
    }
}
