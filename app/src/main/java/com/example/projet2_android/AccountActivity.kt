package com.example.projet2_android

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AccountActivity : BaseActivity() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var nomEditText: EditText
    private lateinit var prenomEditText: EditText
    private lateinit var adresseEditText: EditText
    private lateinit var codePostalEditText: EditText
    private lateinit var villeEditText: EditText
    private lateinit var carteFideliteEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setHeaderTitle("Compte")
        showBack()

        sharedPref = getSharedPreferences("inscription", Context.MODE_PRIVATE)

        nomEditText = findViewById(R.id.nom_edittext)
        prenomEditText = findViewById(R.id.prenom_edittext)
        adresseEditText = findViewById(R.id.adresse_edittext)
        codePostalEditText = findViewById(R.id.code_postal_edittext)
        villeEditText = findViewById(R.id.ville_edittext)
        carteFideliteEditText = findViewById(R.id.carte_fidelite_edittext)

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

        val modifierButton = findViewById<Button>(R.id.modifier_button)
        modifierButton.setOnClickListener {
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

            Toast.makeText(this, "Profil modifié!", Toast.LENGTH_SHORT).show()
            finish() // Terminer l'activité ModificationProfilActivity
        }
    }
}
