package com.exa.busseatmanagment.view.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.databinding.ActivitySelectBusActivityBinding
import com.exa.busseatmanagment.model.data_class.LatLon
import com.exa.busseatmanagment.model.data_class.SeatModel
import com.exa.busseatmanagment.utill.CommonListener
import com.exa.busseatmanagment.viewmodel.SelectBusViewModel
import com.facebook.CallbackManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SelectBusActivity : AppCompatActivity(),CommonListener{
    lateinit var viewModel: SelectBusViewModel
    lateinit var seatlist:ArrayList<SeatModel>
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var progressBar: ProgressBar
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null
    private val firebaseAuth: FirebaseAuth? = null
    private var latLon: LatLon? = null
    var binding: ActivitySelectBusActivityBinding? =null
    private lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seatlist= ArrayList()
        var seatModel=SeatModel("Abc","0","Available",resources.getColor(R.color.white))
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        seatlist.add(seatModel)
        binding= ActivitySelectBusActivityBinding.inflate(layoutInflater)
        viewModel= ViewModelProvider(this)[SelectBusViewModel::class.java]
        setContentView(binding!!.root)
        binding!!.model=viewModel
        viewModel.commonListener=this
    }

    override fun onResume() {
        super.onResume()
        var fromto=resources.getStringArray(R.array.fromto)
        var bustype=resources.getStringArray(R.array.bustype)
        var timeArray=resources.getStringArray(R.array.time)
        var busRoute=resources.getStringArray(R.array.busroute)
        var fromtoAdapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,fromto)
        var bustypeAdapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,bustype)
        var timeAdapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,timeArray)
        var busrouteAdapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,busRoute)
        binding?.departure?.setAdapter(fromtoAdapter)
        binding?.type?.setAdapter(bustypeAdapter)
        binding?.time?.setAdapter(timeAdapter)
        binding?.route?.setAdapter(busrouteAdapter)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onSuccess(msg: String, value:Int) {
        val sdf = SimpleDateFormat("dd-M-yyyy")
        val currentDate = sdf.format(Date())
        var ref=msg+currentDate
        var databaseReference= ref?.let { FirebaseDatabase.getInstance().getReference(it) }
        if (value==0){databaseReference.child("seat").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var intent=Intent(this@SelectBusActivity,MainActivity::class.java)
                    intent.putExtra("reference",ref)
//                    intent.putExtra("list",seatlist)
                    startActivity(intent)
                    finish()
                }else{
                    databaseReference.child("seat").setValue(seatlist).addOnCompleteListener(OnCompleteListener {
                        if(it.isSuccessful){
                            var intent=Intent(this@SelectBusActivity,MainActivity::class.java)
                            intent.putExtra("reference",ref)
//                    intent.putExtra("list",seatlist)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@SelectBusActivity,"Failed",Toast.LENGTH_LONG)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })}else{
            getlocation(ref)

        }


    }

    override fun onFailed(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onNavigate(s: String) {
        val sdf = SimpleDateFormat("dd-M-yyyy")
        val currentDate = sdf.format(Date())
        var ref=s+currentDate
        FirebaseDatabase.getInstance().getReference(ref).child("location").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()){
                var latLon=snapshot.getValue<LatLon>()
                var intent=Intent(this@SelectBusActivity,MapsActivity::class.java)
                intent.putExtra("lat",latLon?.lat)
                intent.putExtra("lon",latLon?.lon)
                startActivity(intent)
            }else{
                Toast.makeText(this@SelectBusActivity, "No Data found", Toast.LENGTH_SHORT).show()
            }
            }

            override fun onCancelled(error: DatabaseError) {
            }


        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun getlocation(msg: String) {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val lat = location.latitude.toString()
                val lon = location.longitude.toString()
                latLon = LatLon(lat, lon)
                val uid: String = auth.getCurrentUser()!!.getUid()
                latLon?.let {
                    shareLatLon(it) }
                val databaseReference = FirebaseDatabase.getInstance().getReference(msg)
                databaseReference.child("location").setValue(latLon).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this@SelectBusActivity, "Success", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 10
            )
            return
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                config()
            }
        }
    }

    private fun shareLatLon(latLon: LatLon) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun config() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            return
        }
        locationListener?.let {
            locationManager?.requestLocationUpdates(
                "gps",
                10000, 0f, it
            )
        }
    }


}