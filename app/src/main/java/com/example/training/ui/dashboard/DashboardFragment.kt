package com.example.training.ui.dashboard

import android.Manifest
import android.Manifest.permission.WRITE_CALENDAR
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.training.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class DashboardFragment : Fragment(){

    private lateinit var mMap: MapView
    private val position = LatLng(-33.920455, 18.466941)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val permission = arrayListOf<String>()
        permission[0] = WRITE_CALENDAR
        if (container != null) {
            if(ContextCompat.checkSelfPermission(container.context, WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED)
            {
                this.activity?.let { ActivityCompat.requestPermissions(it,
                    arrayOf(permission.toString()), "ASFSADASFSADSADASDA") }
            }

        }
        val view = LayoutInflater.from(container?.context).inflate(R.layout.fragment_dashboard, container, false)
        mMap = view.findViewById(R.id.mapView)
        with(mMap){
            onCreate(null)

            getMapAsync{
                MapsInitializer.initialize(context)
                setMapLocation(it)
            }
        }

        return view

    }


    private fun setMapLocation(map: GoogleMap){
        with(map){
//            isMyLocationEnabled = true
            Log.d("TAG", isMyLocationEnabled.toString())
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13f))
            addMarker(MarkerOptions().position(position))
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }
    override fun onResume() {
        super.onResume()
        mMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMap.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMap.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMap.onLowMemory()
    }

}