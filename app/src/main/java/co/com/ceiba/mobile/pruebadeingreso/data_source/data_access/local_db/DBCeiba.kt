package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.local_db.daos.*
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels

@Database(entities = arrayOf(
    UserModels::class,
    PublicationsModels::class
), version = 1)
abstract class DBCeiba : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun publicationsDao(): PublicationsDao

    companion object {
        private const val nameDB = "CeibaDB"
        @Volatile
        private var INSTANCE: DBCeiba? = null

        fun getInstance(context: Context): DBCeiba =
                INSTANCE ?: synchronized(this) {
                    buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room
                    .databaseBuilder(context.applicationContext, DBCeiba::class.java, nameDB)
                    .build()
    }
}