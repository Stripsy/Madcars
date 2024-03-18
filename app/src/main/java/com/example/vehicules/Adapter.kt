package com.example.vehicules

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(private val dataSet: List<Vehicule>?, val context: Context?, val bool: Boolean) :

    RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNom: TextView = view.findViewById(R.id.tvNom)
        val tvPrix: TextView = view.findViewById(R.id.tvPrix)
        val tvCategorie: TextView = view.findViewById(R.id.tvCategorie)
        val img: ImageView = view.findViewById(R.id.img)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val vehicule = dataSet?.get(position)
        val image = vehicule?.image
       val url ="http://s519716619.onlinehome.fr/exchange/madrental/images/$image"
        Picasso.get().load(url).placeholder(R.drawable.loading).into(viewHolder.img)
        viewHolder.tvNom.text = vehicule?.nom
        viewHolder.tvPrix.text = vehicule?.prixjournalierbase+" €/jour"
        viewHolder.tvCategorie.text = "categorie CO2: ${vehicule?.categorieco2}"
        viewHolder.itemView.setOnClickListener {

            val myCommunicator = context as MyCommunicator
            myCommunicator.displayDetails(vehicule!!)
        }


    }

    override fun getItemCount(): Int {
      return dataSet?.size!!
    }
}