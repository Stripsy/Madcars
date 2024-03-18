package com.example.vehicules

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(),MyCommunicator {

    private var mIsDualPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentBView = findViewById<View>(R.id.fragmentDetail)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE


    }
    override fun displayDetails(vehicule: Vehicule) {

        if (mIsDualPane) { // Tablette
            val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as DetailFragment?
            fragmentB?.displayDetails(vehicule)
        } else { // Telephone
            val intent = Intent(this, DetailActivity::class.java)
             intent.putExtra("nom", vehicule.nom)
                intent.putExtra("prix",vehicule.prixjournalierbase)
                    intent.putExtra("categorie",vehicule.categorieco2)
                intent.putExtra("image",vehicule.image)
            startActivity(intent)
        }
    }

}