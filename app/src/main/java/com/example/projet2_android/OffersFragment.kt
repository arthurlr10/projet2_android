import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet2_android.Offer
import com.example.projet2_android.R
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class OffersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var offerAdapter: OfferAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_offers, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchOffers()
        return view
    }

    private fun fetchOffers() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = "https://www.ugarit.online/epsi/offers.json"
            val jsonResponse = URL(url).readText()
            val gson = Gson()
            val jsonObject = gson.fromJson(jsonResponse, JsonObject::class.java)
            val offersJsonArray = jsonObject.getAsJsonArray("items")
            val offersListType = object : TypeToken<List<Offer>>() {}.type
            val offers: List<Offer> = gson.fromJson(offersJsonArray, offersListType)

            withContext(Dispatchers.Main) {
                offerAdapter = OfferAdapter(offers)
                recyclerView.adapter = offerAdapter
            }
        }
    }



}
