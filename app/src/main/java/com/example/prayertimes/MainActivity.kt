package com.example.prayertimes

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://www.mapquestapi.com/geocoding/v1/address?key=WRn78VXQHov2sCg3LVxZqHHSH2XQ58n2&location=Washington,DC"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    textView.text = "Response: %s".format(response.toString())
                },
                { error ->
                    textView.text = "That didn't work!"
                    println(error)
                }
            )

            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest)
        }
    }
}
