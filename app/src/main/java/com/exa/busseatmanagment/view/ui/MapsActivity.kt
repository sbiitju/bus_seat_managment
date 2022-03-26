package com.exa.busseatmanagment.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.databinding.ActivityMapsBinding
import com.exa.busseatmanagment.model.data_class.LatLon

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var latLng: LatLon
    private lateinit var mMap: GoogleMap
private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMapsBinding.inflate(layoutInflater)
     setContentView(binding.root)
        latLng= LatLon(intent.getStringExtra("lat"),intent.getStringExtra("lon"))

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(latLng.lat.toDouble(), latLng.lon.toDouble())
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16f))
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.run {
            putString("Test","Test")
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,SelectBusActivity::class.java))
        finish()
    }
}










