package com.example.gpstest

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationListenerCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_gps = findViewById<Button>(R.id.btn_gps);
        var txtGPS = findViewById<TextView>(R.id.txtGPS);

        btn_gps.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View) {
                val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager;

                val locationListener = object : LocationListenerCompat{
                    override fun onLocationChanged(location: Location) {

                    }
                    public fun onStatusChanged(provider:String,status:Int, extras:Bundle){}


                    public override fun onProviderEnabled(provider:String){}


                    public override fun onProviderDisabled(provider:String){}
                };
                val activity = this as Activity;
                var permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener)

            }
        })

        var permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)

            }
        }
    }
}