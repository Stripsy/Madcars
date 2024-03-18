package com.example.vehicules

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Vehicule (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int,
    @ColumnInfo(name = "nom") var nom:String,
    @ColumnInfo(name = "image")var image:String,
    @ColumnInfo(name = "prixjournalierbase") var prixjournalierbase :String,
    @ColumnInfo(name = "categorieco2")  var categorieco2:String)