package com.example.bird_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bird_app.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()


        binding.btnLogin.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show()

            }
        }
    }

}
