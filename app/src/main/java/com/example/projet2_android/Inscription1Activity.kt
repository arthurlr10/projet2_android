package com.example.projet2_android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONException
import org.json.JSONObject

class Inscription1Activity : BaseActivity() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var nomEditText: EditText
    private lateinit var prenomEditText: EditText
    private lateinit var adresseEditText: EditText
    private lateinit var codePostalEditText: EditText
    private lateinit var villeEditText: EditText
    private lateinit var carteFideliteEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription1)
        //setHeaderTitle("Création de compte")

        sharedPref = getSharedPreferences("inscription", MODE_PRIVATE)

        nomEditText = findViewById(R.id.nom_edittext)
        prenomEditText = findViewById(R.id.prenom_edittext)
        adresseEditText = findViewById(R.id.adresse_edittext)
        codePostalEditText = findViewById(R.id.code_postal_edittext)
        villeEditText = findViewById(R.id.ville_edittext)
        carteFideliteEditText = findViewById(R.id.carte_fidelite_edittext)

        val inscriptionButton = findViewById<Button>(R.id.inscription_button)
        inscriptionButton.setOnClickListener {
            val nom = nomEditText.text.toString()
            val prenom = prenomEditText.text.toString()
            val adresse = adresseEditText.text.toString()
            val codePostal = codePostalEditText.text.toString()
            val ville = villeEditText.text.toString()
            val carteFidelite = carteFideliteEditText.text.toString()

            with(sharedPref.edit()) {
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
            startActivity(intent)
            finish()
        }

        val scanQRCodeButton = findViewById<Button>(R.id.scan_qrcode_button)
        scanQRCodeButton.setOnClickListener {
            startQRCodeScan()
        }

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

    private fun startQRCodeScan() {
        val intent = IntentIntegrator(this).apply {
            setOrientationLocked(false)
            setBeepEnabled(false)
            setPrompt("Scannez le QR Code")
        }
        intent.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (            result.contents == null) {
                Toast.makeText(this, "Scan annulé", Toast.LENGTH_LONG).show()
            } else {
                fillFormWithQRCodeData(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun fillFormWithQRCodeData(qrCodeData: String) {
        try {
            val json = JSONObject(qrCodeData)
            val firstName = json.getString("firstName")
            val lastName = json.getString("lastName")
            val email = json.getString("email")
            val address = json.getString("address")
            val zipcode = json.getString("zipcode")
            val city = json.getString("city")
            val cardRef = json.getString("cardRef")

            nomEditText.setText(lastName)
            prenomEditText.setText(firstName)
            adresseEditText.setText(address)
            codePostalEditText.setText(zipcode)
            villeEditText.setText(city)
            carteFideliteEditText.setText(cardRef)

        } catch (e: JSONException) {
            Toast.makeText(this, "QR Code invalide", Toast.LENGTH_LONG).show()
        }
    }
}

