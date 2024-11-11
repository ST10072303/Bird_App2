package com.example.bird_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bird_app.databinding.ActivityMyLocationsBinding
import kotlin.collections.Map

class MyLocations : AppCompatActivity() {

    private lateinit var binding: ActivityMyLocationsBinding
    private lateinit var locationList: MutableList<Location>
    private lateinit var locationAdapter: LocationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        binding.btnMap.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }
        binding.btnLocation.setOnClickListener {
            val intent = Intent(this, Locations::class.java)
            startActivity(intent)
        }


        locationList = mutableListOf()

       /* locationAdapter = LocationAdapter(locationList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = locationAdapter


        binding.btnAddLocation.setOnClickListener {
            val locationName = binding.txtLocation.text.toString()

            if (locationName.isNotEmpty()) {

                locationList.add(Location(locationName))
                locationAdapter.notifyItemInserted(locationList.size - 1)

                // Clear the EditText after adding
                binding.txtLocation.text.clear()
            } else {
                Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show()
            }
        }*/
    }
}