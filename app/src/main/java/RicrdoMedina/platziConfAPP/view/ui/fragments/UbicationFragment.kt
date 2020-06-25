package RicrdoMedina.platziConfAPP.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import RicrdoMedina.platziConfAPP.R
import RicrdoMedina.platziConfAPP.model.Ubication
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.ContextMenu
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.type.LatLng


class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Cargar mapa en la pantalla
        //Le pasamos el id del mapa
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        //Sincronizar el contenido
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val ubication = Ubication()

        val zoom = 16f
        val centerMap = com.google.android.gms.maps.model.LatLng(ubication.latitude, ubication.longitude)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val centerMark = com.google.android.gms.maps.model.LatLng(ubication.latitude, ubication.longitude)
        val makerOptions = MarkerOptions()

        makerOptions.position(centerMark)
        makerOptions.title("PlatziConf 2019")

        val bitmapDraw = context?.applicationContext?.let {
            ContextCompat.getDrawable(it, R.drawable.logo_platzi)
        } as BitmapDrawable

        val smallMaker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150, 150, false)
        makerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMaker))

        googleMap?.addMarker(makerOptions)

        googleMap?.setOnMarkerClickListener(this)
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.custom_map))
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }
}