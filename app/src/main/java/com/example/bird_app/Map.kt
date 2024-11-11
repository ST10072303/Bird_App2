package com.example.bird_app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.example.bird_app.api.ebirdRetrofit
import com.example.bird_app.databinding.ActivityMapBinding
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Map : AppCompatActivity(),PermissionsListener{

    private lateinit var binding: ActivityMapBinding
    lateinit var mapView : MapView
    lateinit var permissionsManager: PermissionsManager
    private lateinit var pointAnnotationManager: PointAnnotationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapboxOptions.accessToken = "pk.eyJ1Ijoic3QxMDEwMzcwNCIsImEiOiJjbTE5YWJ1cjcwM3UyMmpyMTU4dXoxb2IxIn0.3SGuVJBtoxgfraWYUUqrtQ"

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        binding.btnLocation.setOnClickListener {
            val intent = Intent(this, Locations::class.java)
            startActivity(intent)
        }

        mapView =findViewById(R.id.mapView)

        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            mapView.mapboxMap.setCamera(
                CameraOptions.Builder()
                    .center(Point.fromLngLat(28.218, -25.732))
                    .pitch(0.0)
                    .zoom(12.0)
                    .bearing(180.0)
                    .build()
            )

            mapView.location.enabled = true

            CoroutineScope(Dispatchers.IO).launch {
                val data = ebirdRetrofit.getBirdInfo("-25.732", "28.218","75unmc3na5uv")

                Log.v("appDebug",data.raw().toString())

                if (data.isSuccessful)

                {     val locationList = data.body()

                    launch(Dispatchers.Main) {
                        pointAnnotationManager=mapView.annotations.createPointAnnotationManager()
                        locationList?.forEach{obse->

                            val pointAnnotationOptions = PointAnnotationOptions()
                                .withPoint(Point.fromLngLat(obse.lng,obse.lat))

                                .withIconImage(getDrawable(R.drawable.red_marker)!!.toBitmap())

                            pointAnnotationManager.create(pointAnnotationOptions)

                        }
                    }
                }
                else
                {
                    Log.v("appDebug","Something went wrong")
                }
            }

        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this)
        }

        Log.v("appdebug",mapView.mapboxMap.cameraState.zoom.toString())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {

    }

    override fun onPermissionResult(granted: Boolean) {
    }

}
