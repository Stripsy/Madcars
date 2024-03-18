package com.example.vehicules

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
            getVehicules(view)
        val  switch :Switch = view.findViewById(R.id.switchBtn);
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {
                getRecord(view)
            }else{
                getVehicules(view)
            }

        }
    return view
    }
    fun getRecord(rootView: View)
    {
        val room = RoomDbApp.getDatabase(requireContext()).vehiculeDao()
        val list = room.getAllFavourite()
        val recyclerView : RecyclerView = rootView.findViewById(R.id.recyclerView)
        val adapter = Adapter(list,context,false)
        recyclerView.adapter = adapter
    }
    private fun getVehicules(view:View) {
        val vehicule = WebResponse.webService.getHeadLines()
        vehicule.enqueue(object : Callback<List<Vehicule>> {
            override fun onResponse(call: Call<List<Vehicule>>, response: Response<List<Vehicule>>) {
                val veh = response.body()
                if (veh!=null)
                {

                    loadInRecyclerView(veh,view)

                }
            }

            override fun onFailure(call: Call<List<Vehicule>>, t: Throwable) {

            }
        })
    }

    private fun loadInRecyclerView(veh: List<Vehicule>,rootView :View) {

        val recyclerView  = rootView.findViewById(R.id.recyclerView) as RecyclerView
        val adapter = Adapter(veh,context,true)
        recyclerView.adapter = adapter


    }


}