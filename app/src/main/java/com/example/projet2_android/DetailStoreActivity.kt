package com.example.projet2_android

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class DetailStoreActivity : BaseActivity() {

    private lateinit var storeImageView: ImageView
    private lateinit var addressTextView: TextView
    private lateinit var zipcodeTextView: TextView
    private lateinit var descriptionTextView: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_store)

        storeImageView = findViewById(R.id.store_image)
        addressTextView = findViewById(R.id.store_address)
        zipcodeTextView = findViewById(R.id.store_zipcode)
        descriptionTextView = findViewById(R.id.store_description)

        val store = intent.getSerializableExtra("store") as Store
        displayStoreDetails(store)
    }

    private fun displayStoreDetails(store: Store) {
        Glide.with(this)
            .load(store.pictureStore)
            .into(storeImageView)

        addressTextView.text = store.address
        zipcodeTextView.text = store.zipcode
        descriptionTextView.text = store.description
    }
}
