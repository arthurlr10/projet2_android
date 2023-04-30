package com.example.projet2_android

import MapFragment
import OffersFragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class HomeActivity : AppCompatActivity() {

    private lateinit var offresFragment: OffersFragment
    private lateinit var MapFragment: MapFragment
    private lateinit var CarteFragment: CarteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Ajouter le fragment initial
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CarteFragment())
            .commit()

        // Initialiser les fragments
        offresFragment = OffersFragment()
        MapFragment = MapFragment()
        CarteFragment = CarteFragment()

        // Récupérer les références des boutons
        val offresButton = findViewById<Button>(R.id.offres_button)
        val magasinsButton = findViewById<Button>(R.id.magasins_button)
        val cartesButton = findViewById<Button>(R.id.cartes_button)

        // Ajouter les écouteurs d'événements pour les boutons
        offresButton.setOnClickListener { remplacerFragment(offresFragment) }
        magasinsButton.setOnClickListener { remplacerFragment(MapFragment) }
        cartesButton.setOnClickListener { remplacerFragment(CarteFragment) }
    }

    private fun remplacerFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}







