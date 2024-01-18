package viewModels

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import model.Category
import model.Event
import model.Museum
import model.Piece
import java.sql.Timestamp

class DiscoveryViewModel {

    private val db = Firebase.firestore

    var museums = MutableLiveData<ArrayList<Museum>>()
    var categories = MutableLiveData<ArrayList<Category>>()
    var categoriesaux = arrayListOf<Category>()
    var events = MutableLiveData<ArrayList<Event>>()


    fun fetchDiscovery() {
        fetchCategories()
        fetchCategoryAux()
    }

    private fun fetchCategories() {
        db.collection("Categorias")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    categoriesaux.add(
                        Category(
                            document.id,
                            document.data["nome"].toString()
                        )
                    )
                }

                fetchMuseums()
                fetchEvents()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    private fun fetchCategoryAux() {
        var aux = arrayListOf<Category>()
        db.collection("Categorias")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    aux.add(Category(document.id,
                        document.data["nome"].toString()
                    ))
                }
                categories.value = aux
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

   private fun fetchMuseums() {
        var aux = arrayListOf<Museum>()
        db.collection("Museus")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val gallery: List<String> = document.data["galeria"] as List<String>
                    aux.add(Museum(document.id,
                       document.data["nome"].toString() ,
                        document.data["localizacao"].toString(),
                        document.data["descricao"].toString(),
                        document.data["contacto"].toString().toInt(),
                        document.data["categoria_id"].toString(),
                        gallery.toTypedArray()
                        ))
                }
                museums.value = transformMuseums(aux)
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    private fun fetchEvents(){
        var aux = arrayListOf<Event>()
        db.collection("Eventos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val galleryEvent: List<String> = document.data["galeria_evento"] as List<String>
                    val timestampStartEvent = document.data["data_evento_inicial"] as com.google.firebase.Timestamp
                    val startDate = timestampStartEvent.toDate()
                    val timestampEndEvent = document.data["data_evento_final"] as com.google.firebase.Timestamp
                    val endDate = timestampEndEvent.toDate()
                    aux.add(Event(document.id,
                        document.data["nome"].toString(),
                        startDate,
                        endDate,
                        document.data["descricao"].toString(),
                        document.data["museu_id"].toString(),
                        galleryEvent.toTypedArray(),
                        document.data["categoria_id"].toString()
                    ))
                }
                events.value = transformEvents(aux)
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
    private fun transformMuseums(museums: ArrayList<Museum>): ArrayList<Museum>
    {
        for (museum in museums) {
            var categoryName: String =
                categoriesaux.filter { s -> s.categoryId == museum.categoryId }.single().name
            museum.categoryName = categoryName
        }
        return museums
    }
    private fun transformEvents(events: ArrayList<Event>): ArrayList<Event>
    {
        for (event in events) {
            var categoryName: String =
                categoriesaux.filter { s -> s.categoryId == event.categoryId }.single().name
            event.categoryName = categoryName
        }
        return events
    }
}