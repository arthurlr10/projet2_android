package com.example.projet2_android

import OffersFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView

class FragmentsActivity : AppCompatActivity() {

    private lateinit var fragmentContainerView: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        fragmentContainerView = findViewById(R.id.fragment_container_view)

        // Récupérer l'extra de l'Intent pour déterminer quel fragment doit être affiché
        val fragmentName = intent.getStringExtra("fragment")

        when (fragmentName) {
            "cartes" -> {
                val cartesFragment = CartesFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, cartesFragment)
                    .commit()
            }
            "offres" -> {
                val offresFragment = OffersFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, offresFragment)
                    .commit()
            }
            "magasins" -> {
                val magasinsFragment = MagasinsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, magasinsFragment)
                    .commit()
            }
        }
    }
}

