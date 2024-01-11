package model

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

class Museum(museumId: String, name: String, location: String, description: String, contact: Int, categoryId: String, galery: Array<String>):
    Serializable {
    var museumId = museumId
    var name = name
    var location = location
    var description = description
    var contact = contact
    var categoryId = categoryId
    var galery = galery
    var categoryName = ""
}

