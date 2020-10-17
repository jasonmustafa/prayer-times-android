package com.example.prayertimes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)
        val locationInputEditText = findViewById<EditText>(R.id.locationInput)

        button.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val api = "QBftqmjmAnP809R2yGqhGgXuXGAtq8m0"
            val locationInput = locationInputEditText.text
            val url =
                "https://www.mapquestapi.com/geocoding/v1/address?key=$api&location=$locationInput"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val lat = response.getJSONArray("results").getJSONObject(0)
                        .getJSONArray("locations").getJSONObject(0).getJSONObject("displayLatLng").getDouble("lat")
                    val lon = response.getJSONArray("results").getJSONObject(0)
                        .getJSONArray("locations").getJSONObject(0).getJSONObject("displayLatLng").getDouble("lng")

                    println("lat: $lat, lon: $lon")
                },
                { error ->
                    textView.text = "That didn't work!"
                    println(error)
                }
            )

            queue.add(jsonObjectRequest)
        }
    }
}
