package com.example.training.ui.dashboard

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.WRITE_CALENDAR
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions


class DashboardFragment : Fragment(){
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var mMap: MapView
    private var position = LatLng(-33.920455, 18.466941)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = LayoutInflater.from(container?.context).inflate(R.layout.fragment_dashboard, container, false)
        mMap = view.findViewById(R.id.mapView)
        with(mMap){
            onCreate(null)

            getMapAsync{
                MapsInitializer.initialize(context)
                setMapLocation(it,container)
            }
        }

        return view

    }


    private fun setMapLocation(map: GoogleMap, container: ViewGroup?){
        with(map){
            if (container != null) {
                if (ContextCompat.checkSelfPermission(container.context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(container.context as Activity, arrayOf(ACCESS_FINE_LOCATION), 0)
                }
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(container.context)
                fusedLocationClient.lastLocation.addOnSuccessListener {location ->
                    Log.d("TAG",location.toString())
                    isMyLocationEnabled = true
                    position = LatLng(location.latitude,location.longitude)
                    moveCamera(CameraUpdateFactory.newLatLngZoom(position, 18f))
                    mapType = GoogleMap.MAP_TYPE_NORMAL
                    setMapStyle(MapStyleOptions(resources.getString(R.string.style_json)))
                }



            }

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