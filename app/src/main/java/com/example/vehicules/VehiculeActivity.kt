package com.example.vehicules

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class VehiculeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicule)
        val intent = intent
        val bool = intent.getBooleanExtra("bool", false)
        val nom = intent.getStringExtra("nom")
        val prix = intent.getStringExtra("prix")
        val categorie = intent.getStringExtra("categorie")
        val image = intent.getStringExtra("image")


        val tvNom: TextView = findViewById(R.id.tvNom)
        val tvPrix: TextView = findViewById(R.id.tvPrix)
        val tvCategorie: TextView = findViewById(R.id.tvCategorie)
        val img: ImageView = findViewById(R.id.img)

        tvNom.text = nom
        tvPrix.text = "$prix €/jour"
        tvCategorie.text = "categorie CO2: $categorie"
        val url = "http://s519716619.onlinehome.fr/exchange/madrental/images/$image"
        Picasso.get().load(url).placeholder(R.drawable.vehicule).into(img)




        if (bool) {
            val btnFav: Switch = findViewById(R.id.btnFav)
            btnFav.visibility = View.VISIBLE
            val room = RoomDbApp.getDatabase(application).vehiculeDao()
            val h = room.checkFav(nom.toString())
            if (h != null){
                btnFav.isChecked = true
                btnFav.text = "Already exists in fav"
            }
            val vehicule =
                Vehicule(0, nom.toString(), image.toString(), prix.toString(), categorie.toString())
            btnFav.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                {

                    if (h == null) {

                        Toast.makeText(this, "Ajouté aux favoris", Toast.LENGTH_SHORT).show()
                        room.insertFav(vehicule)
                    } else {
                        Toast.makeText(this, "Existe déjà dans les favoris", Toast.LENGTH_SHORT).show()
                    }

                }
            }

        }
        else{
            val btnDel: Button = findViewById(R.id.btnDelete)
            btnDel.visibility = View.VISIBLE
            val room = RoomDbApp.getDatabase(application).vehiculeDao()
            btnDel.setOnClickListener {
                room.deleteFav(nom.toString())
                Toast.makeText(this, "Supprimé", Toast.LENGTH_SHORT).show()
                finish()
            }
        }


    }

}