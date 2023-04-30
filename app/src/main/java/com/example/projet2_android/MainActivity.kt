package com.example.projet2_android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeaderTitle("Création de compte")

        // Vérifier si l'utilisateur est inscrit
        val sharedPref = getSharedPreferences("inscription", Context.MODE_PRIVATE)
        val isUserRegistered = sharedPref.getString("nom", null) != null

        // Si l'utilisateur est inscrit, démarrez HomeActivity et terminez MainActivity
        if (isUserRegistered) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        val scanButton = findViewById<Button>(R.id.scan_button)
        scanButton.setOnClickListener {
            val intent = Intent(this, Inscription1Activity::class.java)
            startActivity(intent)
        }

        val registerButton = findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener {
            val intent = Intent(this, InscriptionActivity::class.java)
            startActivity(intent)
        }
    }
}
