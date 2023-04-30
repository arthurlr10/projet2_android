import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projet2_android.Offer
import com.example.projet2_android.R

class OfferAdapter(private val offers: List<Offer>) : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    class OfferViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.offer_name)
        val description: TextView = view.findViewById(R.id.offer_description)
        val picture: ImageView = view.findViewById(R.id.offer_picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offers[position]
        holder.name.text = offer.name
        holder.description.text = offer.description
        Glide.with(holder.picture.context).load(offer.picture_url).into(holder.picture)
    }

    override fun getItemCount() = offers.size
}
