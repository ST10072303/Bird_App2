package com.example.bird_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bird_app.databinding.ActivityLocationsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Locations : AppCompatActivity() {

    lateinit var binding: ActivityLocationsBinding
    val metric = true.toString()
    val apikey = "75unmc3na5uv"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        binding.btnMap.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }
        binding.btnSaved.setOnClickListener {
            val intent = Intent(this, MyLocations::class.java)
            startActivity(intent)
        }


        CoroutineScope(Dispatchers.IO).launch {
            val data = api.ebirdRetrofit.getBirdInfo("-25.7459277", "28.1879101", apikey)

            if (data.isSuccessful) {
                val hotspots = data.body()

                val locationNames = hotspots?.map { it.locName } ?: emptyList()

                launch(Dispatchers.Main) {
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@Locations)
                    recyclerView.adapter = LocationAdapter(locationNames)
                }
            } else {
                launch(Dispatchers.Main) {
                    Toast.makeText(this@Locations, data.message()!!, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}