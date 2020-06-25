package RicrdoMedina.platziConfAPP.viewmodel

import RicrdoMedina.platziConfAPP.model.Speaker
import RicrdoMedina.platziConfAPP.network.Callback
import RicrdoMedina.platziConfAPP.network.FirestoreService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

//Hereda de ViewModel
class SpeakerViewModel:ViewModel() {
    // Usamos nuestro servicio de FireStore que creamos
    val firestoreService = FirestoreService()
    // Lista de cronogramas con view model
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    // Boolead para controllar la carga de datos en la pantalla
    var isLoading = MutableLiveData<Boolean>()

    //Refresh de datos
    fun refresh () {
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase() {
        //Obtenemos las conferencias y le pasamos el callback
        firestoreService.getSpeakers(object: Callback<List<Speaker>>{
            override fun onSuccess(result: List<Speaker>?) {
                //Guardamos los datos
                listSpeaker.postValue(result)

                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    //Controllar que finalizo el proceso
    fun processFinished() {
        isLoading.value = true
    }
}