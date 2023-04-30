package com.example.projet2_android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InscriptionActivity : BaseActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        setHeaderTitle("Création de compte")

        sharedPref = getSharedPreferences("inscription", Context.MODE_PRIVATE)

        val nomEditText = findViewById<EditText>(R.id.nom_edittext)
        val prenomEditText = findViewById<EditText>(R.id.prenom_edittext)
        val adresseEditText = findViewById<EditText>(R.id.adresse_edittext)
        val codePostalEditText = findViewById<EditText>(R.id.code_postal_edittext)
        val villeEditText = findViewById<EditText>(R.id.ville_edittext)
        val carteFideliteEditText = findViewById<EditText>(R.id.carte_fidelite_edittext)

        val inscriptionButton = findViewById<Button>(R.id.inscription_button)
        inscriptionButton.setOnClickListener {
            val nom = nomEditText.text.toString()
            val prenom = prenomEditText.text.toString()
            val adresse = adresseEditText.text.toString()
            val codePostal = codePostalEditText.text.toString()
            val ville = villeEditText.text.toString()
            val carteFidelite = carteFideliteEditText.text.toString()

            with (sharedPref.edit()) {
                putString("nom", nom)
                putString("prenom", prenom)
                putString("adresse", adresse)
                putString("code_postal", codePostal)
                putString("ville", ville)
                putString("carte_fidelite", carteFidelite)
                apply()
            }

            Toast.makeText(this, "Inscription réussie!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent) // Démarrer l'activité HomeActivity
            finish() // Terminer l'activité InscriptionActivity
        }

        // Récupérer les données d'inscription si elles existent
        val nom = sharedPref.getString("nom", "")
        val prenom = sharedPref.getString("prenom", "")
        val adresse = sharedPref.getString("adresse", "")
        val codePostal = sharedPref.getString("code_postal", "")
        val ville = sharedPref.getString("ville", "")
        val carteFidelite = sharedPref.getString("carte_fidelite", "")

        nomEditText.setText(nom)
        prenomEditText.setText(prenom)
        adresseEditText.setText(adresse)
        codePostalEditText.setText(codePostal)
        villeEditText.setText(ville)
        carteFideliteEditText.setText(carteFidelite)
    }
}


