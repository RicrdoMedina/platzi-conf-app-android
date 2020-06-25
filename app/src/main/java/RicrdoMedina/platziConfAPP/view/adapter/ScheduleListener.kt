package RicrdoMedina.platziConfAPP.view.adapter

import RicrdoMedina.platziConfAPP.model.Conference

interface ScheduleListener {
    fun onConferenceClicked (conference: Conference, position: Int) {

    }
}