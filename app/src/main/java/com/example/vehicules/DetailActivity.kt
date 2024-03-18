package com.example.vehicules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nom =intent.getStringExtra("nom")
        val prix =intent.getStringExtra("prix")
        val categorie =intent.getStringExtra("categorie")
        val image =intent.getStringExtra("image")
        val vehicule = Vehicule(0,nom.toString(),image.toString(), prix.toString(),
            categorie.toString()
        )


        val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as DetailFragment?
        fragmentB?.displayDetails(vehicule)
    }
}