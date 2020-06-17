package com.maxfin.kvartirkatest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.maxfin.kvartirkatest.presenters.ITownView
import com.maxfin.kvartirkatest.presenters.TownPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ITownView {

    private var id: String? = ""
    val presenter = TownPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLocation()

    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED && isGoogleApiAvailable()){
            val fusedLocationClient: FusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.locationAvailability
                .addOnSuccessListener { locationAvailability ->
                    if (locationAvailability.isLocationAvailable) {
                        val locationTask: Task<Location> =
                            fusedLocationClient.lastLocation
                        locationTask.addOnCompleteListener { task ->
                            val location = task.result
                            val longitude = location?.longitude.toString()
                            val latitude = location?.latitude.toString()
                            presenter.getTown(longitude, latitude)
                        }
                    }
                    else
                       presenter.getTown("55.753215", "37.622504")
                }
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 42
            )
        }
    }

    private fun isGoogleApiAvailable(): Boolean {
        val queryResult =
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        if (queryResult == ConnectionResult.SUCCESS) {
            return true
        }
        return false
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            42 -> {
                if (!grantResults.isNotEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Вы отключили местоположение", Toast.LENGTH_SHORT)
                        .show()
                } else
                    getLocation()
                return
            }
        }
    }

    override fun setTown(data: com.maxfin.kvartirkatest.model.Location?) {
        townName.text = data?.name
        id = data?.id
    }

    fun onClickButton(view: View) {
        val intent = Intent(this, FlatsActivity::class.java)
        intent.putExtra("idTown", id)
        startActivity(intent)
    }
}