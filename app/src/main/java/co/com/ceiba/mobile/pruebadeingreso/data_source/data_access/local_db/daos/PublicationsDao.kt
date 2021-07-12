package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels

@Dao
abstract class PublicationsDao : BaseDao<PublicationsModels> {
    @Query("SELECT * FROM PublicationsModels")
    abstract fun getPublicationsModels(): LiveData<List<PublicationsModels>>

    @Query("SELECT * FROM PublicationsModels P WHERE P.UserID = :id")
    abstract fun getPublicationsModelsForUserID(id: Int): LiveData<List<PublicationsModels>>

    @Query("DELETE FROM PublicationsModels")
    abstract fun nukePublications()
}