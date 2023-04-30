import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projet2_android.R
import com.example.projet2_android.Store
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fetchStoresAndAddMarkers()
    }

    private fun fetchStoresAndAddMarkers() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = "https://www.ugarit.online/epsi/stores.json"
            val jsonResponse = URL(url).readText()
            val gson = Gson()
            val jsonObject = gson.fromJson(jsonResponse, JsonObject::class.java)
            val storesJsonArray = jsonObject.getAsJsonArray("stores")
            val storesListType = object : TypeToken<List<Store>>() {}.type
            val stores: List<Store> = gson.fromJson(storesJsonArray, storesListType)

            withContext(Dispatchers.Main) {
                addMarkersToMap(stores)
            }
        }
    }

    private fun addMarkersToMap(stores: List<Store>) {
        for (store in stores) {
            val position = LatLng(store.latitude, store.longitude)
            mMap.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(store.name)
                    .snippet(store.description)
            )
        }
        // Zoom sur la France
        val franceLatLng = LatLng(46.603354, 1.888334)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(franceLatLng, 5.0f))
    }
}