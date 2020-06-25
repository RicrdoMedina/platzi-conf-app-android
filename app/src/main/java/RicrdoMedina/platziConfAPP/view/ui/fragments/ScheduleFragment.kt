package RicrdoMedina.platziConfAPP.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import RicrdoMedina.platziConfAPP.R
import RicrdoMedina.platziConfAPP.model.Conference
import RicrdoMedina.platziConfAPP.view.adapter.ScheduleAdapter
import RicrdoMedina.platziConfAPP.view.adapter.ScheduleListener
import RicrdoMedina.platziConfAPP.viewmodel.ScheduleViewModel
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

//Implementa el ScheduleListener
class ScheduleFragment : Fragment(), ScheduleListener {
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    //
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        //Obtener los datos
        viewModel.refresh()

        scheduleAdapter = ScheduleAdapter(this)

        //RecyclerView de fragment_schedule
        rvSchedule.apply {
            //Como se van a mostrar las vistas en el RecyclerView, usando linear layout (Una elemento debajo de otro)
            layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }

        observeViewModel()
    }

    fun observeViewModel () {
        viewModel.listSchedule.observe(this, Observer<List<Conference>> {schedules ->
            //Actualizar datos
            scheduleAdapter.updateData(schedules)
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            //rlBaseSchedule es el id de ProgressBar en fragment_schedule
            if (it != null) {
                rlBaseSchedule.visibility = View.INVISIBLE
            }
        })
    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        //bundle guarda el objeto conference
        val bundle = bundleOf("conference" to conference)
        //Llamamos a nuestro navigation y le decimos a que fragment ir y le pasamos el objeto conference
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }


}