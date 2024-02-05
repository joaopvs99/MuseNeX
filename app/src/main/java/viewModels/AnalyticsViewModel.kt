package viewModels

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class AnalyticsViewModel {

    private val db = Firebase.firestore

    fun sendEvent(eventName: String, property: String?) {

        val data = hashMapOf(
            "screen" to eventName,
            "parameter" to property,
        )

        db.collection("Analytics")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}