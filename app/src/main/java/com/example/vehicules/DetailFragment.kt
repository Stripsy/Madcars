package com.example.vehicules

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    var image : ImageView? = null
    var tvNom : TextView? = null
    var tvPrix : TextView? = null
    var tvC : TextView? = null
    var vehicule:Vehicule? = null
    var bool = false
    var name = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

         image = view.findViewById(R.id.img)
         tvNom = view.findViewById(R.id.tvNom)
         tvPrix = view.findViewById(R.id.tvPrix)
         tvC = view.findViewById(R.id.tvCategorie)
        val btnFav = view.findViewById<Button>(R.id.btnFav)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)
        val room = RoomDbApp.getDatabase(requireContext()).vehiculeDao()

        btnFav.setOnClickListener {
            if (bool)
            {
            val h = room.checkFav(name)
            if (h != null){

                Toast.makeText(context,"Déjà existant dans les favoris",Toast.LENGTH_SHORT).show()
            }
            else{
                room.insertFav(vehicule)
                Toast.makeText(context,"Ajouté aux favoris>",Toast.LENGTH_SHORT).show()
            }



            }
        }
        btnDelete.visibility = View.VISIBLE
        btnDelete.setOnClickListener{
            if (bool)
            {
                room.deleteFav(name)
                Toast.makeText(context,"Bien supprimé",Toast.LENGTH_SHORT).show()

            }

        }
    return view
    }
    @SuppressLint("SetTextI18n")
    fun displayDetails(vehicule: Vehicule) {

        val url ="http://s519716619.onlinehome.fr/exchange/madrental/images/${vehicule.image}"
        Picasso.get().load(url).placeholder(R.drawable.loading).into(image)
        tvNom?.text  = vehicule.nom
        name = vehicule.nom
        tvPrix?.text  = vehicule.prixjournalierbase+" €/jour"
        tvC?.text  = "categorie CO2: ${vehicule.categorieco2}"
        this.vehicule = vehicule
        bool = true


    }


}