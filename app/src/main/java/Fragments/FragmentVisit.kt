import android.content.Context
import android.graphics.Rect
import org.osmdroid.config.Configuration
import android.location.Geocoder
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.CameraPosition
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import com.ipca_project.musenex.R
import model.Museum
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import java.io.IOException
import org.osmdroid.views.overlay.Marker

class FragmentVisit : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var museum: Museum
    private lateinit var contactTV: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.visit_recycler_view, container, false)
        museum = arguments?.getSerializable("museum") as Museum

        // Inicializa o SupportMapFragment
        Configuration.getInstance().load(context, androidx.preference.PreferenceManager.getDefaultSharedPreferences(context))


        mapView = view.findViewById(R.id.maps)
        mapView.isClickable = true
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)
        mapView.getLocalVisibleRect(Rect())
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.DEFAULT_TILE_SOURCE)

        addMarkerToAddress(museum.location)

        contactTV = view.findViewById(R.id.textViewContact)
        contactTV.text = "Contactos:\n\n" + museum.contact.toString()

        return view
    }

    private fun addMarkerToAddress(address: String) {
        val geoCoder = Geocoder(requireContext())
        try {
            val locationList = geoCoder.getFromLocationName(address, 1)
            if (locationList != null) {
                if (locationList.isNotEmpty()) {
                    val latitude = locationList[0].latitude
                    val longitude = locationList[0].longitude

                    // Set a default map center (e.g., latitude: 0, longitude: 0)
                    mapView.controller.setCenter(org.osmdroid.util.GeoPoint(latitude, longitude))
                    mapView.controller.setZoom(20.0)

                    // Add a marker at a specific location
                    val marker = Marker(mapView)
                    marker.position = org.osmdroid.util.GeoPoint(latitude, longitude)
                    marker.title = address
                    mapView.overlays.add(marker)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            showToast()
        }
    }
    private fun showToast() {
        var text = "Erro ao tentar obter a localização. Verifique a conexão de rede."
        var duration = Toast.LENGTH_SHORT
        var toast = Toast.makeText(requireContext(),text,duration)
        toast.show()

    }
}
