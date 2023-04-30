package com.example.projet2_android

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NomFragment : Fragment() {

    private lateinit var nomPrenomTextView: TextView
    private lateinit var sharedPref: SharedPreferences

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
    }

}
