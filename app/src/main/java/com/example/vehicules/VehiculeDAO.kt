package com.example.vehicules

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VehiculeDAO {


    @Query("SELECT * FROM favorite ORDER BY id DESC")
    fun getAllFavourite():List<Vehicule>?


    @Query("SELECT * FROM favorite WHERE nom=:nom")
    fun checkFav(nom:String):Vehicule?

    @Insert
    fun insertFav(vehicule: Vehicule?)

    @Query("DELETE FROM favorite WHERE nom=:nom")
    fun deleteFav(nom: String)
}