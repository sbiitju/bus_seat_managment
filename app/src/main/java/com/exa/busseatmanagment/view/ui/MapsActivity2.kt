package com.exa.busseatmanagment.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.databinding.ActivityMaps2Binding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaps2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(23.8796, 90.2726)
        val sydney1 = LatLng(23.8474, 90.2575)
        val sydney2 = LatLng(23.8396, 90.2326)
        val sydney3 = LatLng(23.8596, 90.2426)
        val sydney4 = LatLng(23.8796, 90.2726)
        val sydney5 = LatLng(23.8796, 90.2726)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(sydney1).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(sydney2).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(sydney3).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(sydney4).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(sydney5).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16f))
        mMap.uiSettings.isMapToolbarEnabled=true
        mMap.uiSettings.isZoomControlsEnabled=true
    }
}