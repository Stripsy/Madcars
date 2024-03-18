package com.example.vehicules

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Vehicule::class], version = 1, exportSchema = false)
abstract class RoomDbApp : RoomDatabase() {

    abstract fun vehiculeDao(): VehiculeDAO

    companion object {

        @Volatile
        private var INSTANCE: RoomDbApp? = null

        fun getDatabase(context: Context): RoomDbApp {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDbApp::class.java,
                    "fav"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
