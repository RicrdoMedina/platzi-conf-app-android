package RicrdoMedina.platziConfAPP.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import RicrdoMedina.platziConfAPP.R
import RicrdoMedina.platziConfAPP.model.Conference
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

//Hereda de DialogFragment
class ScheduleDeteilDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Usar y personalizar el tool bar
        //toolbarConference es el id del tool bar
        // ContextCompat.getDrawable para asignar un titulo
        // R.drawable.ic_close_white_24 icono para cerrar
        toolbarConference.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close_white_24)
        //Color del titulo del dialogo
        toolbarConference.setTitleTextColor(Color.WHITE)
        //Cerrar el dialogo
        toolbarConference.setNavigationOnClickListener {
            dismiss()
        }


        //Acedemos al objeto conference
        val conference = arguments?.getSerializable("conference") as Conference

        //Asignamos el titulo al tool bar
        toolbarConference.title = conference.title
        tvItemScheduleConferenceTitle.text = conference.title

        //Setemos los valores
        val pattern = "dd/MM/YYYY hh:mm a"
        val simpleDF = SimpleDateFormat(pattern)
        val date = simpleDF.format(conference.datetime)
        tvDetailConferenceHour.text = date
        tvDetailConferenceSpeaker.text = conference.speaker
        tvDetailConferenceTag.text = conference.tag
        tvDetailConferenceDescription.text = conference.description
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Asignamos los estilos que creamos en el styles.xml
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onStart() {
        super.onStart()

        //Le asignamos el alto y el ancho al dialogo
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}