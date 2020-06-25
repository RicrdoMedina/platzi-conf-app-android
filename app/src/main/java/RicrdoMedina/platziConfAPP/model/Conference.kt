package RicrdoMedina.platziConfAPP.model

import java.io.Serializable
import java.util.*

//Hereda de Serealizable para pasarlo entre activities
class Conference: Serializable {
    lateinit var title: String
    lateinit var description: String
    lateinit var tag: String
    lateinit var datetime: Date
    lateinit var speaker: String
}