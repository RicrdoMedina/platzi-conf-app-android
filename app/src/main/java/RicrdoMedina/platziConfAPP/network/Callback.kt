package RicrdoMedina.platziConfAPP.network

import java.lang.Exception

//<T> indicamos que es dinamico lo que recibira por parametro
interface Callback<T> {
    fun onSuccess (result: T?)

    fun onFailed(exception: Exception)
}