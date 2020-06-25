package RicrdoMedina.platziConfAPP.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import RicrdoMedina.platziConfAPP.R
import RicrdoMedina.platziConfAPP.model.Conference
import RicrdoMedina.platziConfAPP.model.Speaker
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.tvDetailConferenceDescription
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*
import java.text.SimpleDateFormat


class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarSpeaker.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close_white_24)
        toolbarSpeaker.setTitleTextColor(Color.WHITE)
        toolbarSpeaker.setNavigationOnClickListener {
            dismiss()
        }

        val speaker = arguments?.getSerializable("speaker") as Speaker

        toolbarSpeaker.title = speaker.name
        tvDetailSpeakerName.text = speaker.name
        tvDetailSpeakerJobTile.text = speaker.jobtitle
        tvDetailSpeakerJob.text = speaker.workplace
        tvDetailConferenceDescription.text = speaker.biography

        Glide.with(view.context)
            .load(speaker.image)
            .into(ciAvatarSpeaker)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }


}