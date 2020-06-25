package RicrdoMedina.platziConfAPP.view.adapter

import RicrdoMedina.platziConfAPP.R
import RicrdoMedina.platziConfAPP.model.Conference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//Hereda de RecyclerView.Adapter
//Clase interna ViewHolder
class ScheduleAdapter (val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    //Elementos graficos que se van a mostrar
    var listConference = ArrayList<Conference>()

    //Obtener archivo que vamos a usar\
    //Donde parent es el que contiene a la vista
    //attchRoot si queremos enlazar con otra vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    //Funcion inline
    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conference

        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = simpleDateFormat.format(conference.datetime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        //Escuchar click
        holder.itemView.setOnClickListener{
            scheduleListener.onConferenceClicked(conference, position)
        }
    }

    fun updateData(data: List<Conference>) {
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    //Clase interna
    //Hereda de  RecyclerView.ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Un itemView contendra todo el elemento
        //Obtenemos los elementos a los que le agregaremos informacion por id
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
    }
}