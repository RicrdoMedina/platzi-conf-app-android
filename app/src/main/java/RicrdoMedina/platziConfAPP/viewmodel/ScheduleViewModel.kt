package RicrdoMedina.platziConfAPP.viewmodel

import RicrdoMedina.platziConfAPP.model.Conference
import RicrdoMedina.platziConfAPP.network.Callback
import RicrdoMedina.platziConfAPP.network.FirestoreService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

//Hereda de ViewModel
class ScheduleViewModel:ViewModel() {
    // Usamos nuestro servicio de FireStore que creamos
    val firestoreService = FirestoreService()
    // Lista de cronogramas con view model
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    // Boolead para controllar la carga de datos en la pantalla
    var isLoading = MutableLiveData<Boolean>()

    //Refresh de datos
    fun refresh () {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        //Obtenemos las conferencias y le pasamos el callback
        firestoreService.getSchedule(object: Callback<List<Conference>>{
            override fun onSuccess(result: List<Conference>?) {
                //Guardamos los datos
                listSchedule.postValue(result)

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