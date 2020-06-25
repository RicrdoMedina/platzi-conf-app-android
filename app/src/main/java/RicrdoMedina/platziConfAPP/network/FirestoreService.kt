package RicrdoMedina.platziConfAPP.network

import RicrdoMedina.platziConfAPP.model.Conference
import RicrdoMedina.platziConfAPP.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"

class FirestoreService {
    //Obtenemos la intancia a la bd
    val firebaseFirestore = FirebaseFirestore.getInstance()
    //Persistir los datos para modo offline
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    // Soporte para persistir los datos offline
    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSpeakers (callback: Callback<List<Speaker>>) {
        //Obtenemos la collection de speakers
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME).orderBy("category").get().addOnSuccessListener { result ->
            for (doc in result) {
                val list = result.toObjects(Speaker::class.java)
                callback.onSuccess(list)
                break
            }
        }
    }

    fun getSchedule(callback: Callback<List<Conference>>) {
        //Obtener la collection de conferencias
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME).get().addOnSuccessListener { result ->
            for (doc in result) {
                val list = result.toObjects(Conference::class.java)
                callback.onSuccess(list)
                break
            }
        }
    }
}