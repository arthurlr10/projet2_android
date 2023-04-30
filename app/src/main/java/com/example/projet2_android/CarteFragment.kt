package com.example.projet2_android

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarteFragment : Fragment() {

    private lateinit var nomPrenomTextView: TextView
    private lateinit var sharedPref: SharedPreferences
    private lateinit var carteFideliteTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nom, container, false)
        nomPrenomTextView = view.findViewById(R.id.nom_prenom_textview)
        sharedPref = requireActivity().getSharedPreferences("inscription", Context.MODE_PRIVATE)
        return view
    }

    override fun onResume() {
        super.onResume()
        val nom = sharedPref.getString("nom", "")
        val prenom = sharedPref.getString("prenom", "")
        nomPrenomTextView.text = "$nom $prenom"
        if (::carteFideliteTextView.isInitialized) {
            val carteFidelite = sharedPref.getString("carte_fidelite", "")
            carteFideliteTextView.text = carteFidelite
            if (carteFidelite != null) {
                generateBarcode(carteFidelite)
            }
        }
    }
    private fun generateBarcode(number: String): Bitmap? {
        val writer = MultiFormatWriter()
        val bitMatrix = writer.encode(number, BarcodeFormat.CODE_128, 1200, 600)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    }


}
