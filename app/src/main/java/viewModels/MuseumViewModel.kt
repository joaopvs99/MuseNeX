package viewModels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.firestore
import com.ipca_project.musenex.R
import model.Piece

class MuseumViewModel {
    private val db = Firebase.firestore

    var pieces = MutableLiveData<ArrayList<Piece>>()

     fun fetchPieces() {
        var aux = arrayListOf<Piece>()

        db.collection("Obras")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    aux.add(
                        Piece(
                        document.id,
                        document.data["nome"].toString(),
                        document.data["descricao"].toString(),
                        document.data["autor_id"].toString(),
                        document.data["museu_id"].toString(),
                        document.data["categoria_id"].toString(),
                        document.data["audioUrl"].toString(),
                        document.data["foto_url"].toString()
                    )
                    )
                }
                pieces.value = aux
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}