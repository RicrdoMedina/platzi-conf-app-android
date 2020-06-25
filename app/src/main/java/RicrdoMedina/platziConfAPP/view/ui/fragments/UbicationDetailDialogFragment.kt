package RicrdoMedina.platziConfAPP.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import RicrdoMedina.platziConfAPP.R
import RicrdoMedina.platziConfAPP.model.Ubication
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*

class UbicationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ubication = Ubication()

        toolbarUbication.title = ubication.name
        toolbarUbication.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close_white_24)
        toolbarUbication.setTitleTextColor(Color.WHITE)
        toolbarUbication.setNavigationOnClickListener {
            dismiss()
        }


        tvUbicationDetailTitle.text = ubication.name
        tvUbicationDetailDirection.text = ubication.address
        tvUbicationDetailPhone.text = ubication.phone
        tvUbicationDetailLink.text = ubication.website

        llPhoneUbication.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)
        }

        llLinkWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(ubication.website)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}