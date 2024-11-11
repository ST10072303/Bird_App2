package com.example.bird_app

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bird_app.databinding.ActivityHomeBinding
import com.example.bird_app.databinding.ActivityRegisterBinding


class Home : AppCompatActivity() {

    private  lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLocation.setOnClickListener {
            val intent = Intent(this, Locations::class.java)
            startActivity(intent)
        }

        binding.btnMap.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }

        val sound1 = MediaPlayer.create(this, R.raw.bird1)


        binding.btn1.setOnClickListener{

            sound1.start()
        }

        val sound2 = MediaPlayer.create(this, R.raw.bird2)


        binding.btn2.setOnClickListener{

            sound2.start()
        }


    }
}
