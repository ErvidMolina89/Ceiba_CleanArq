package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels

@Dao
abstract class UsersDao : BaseDao<UserModels> {
    @Query("SELECT * FROM UserModels")
    abstract fun getUserModels(): LiveData<List<UserModels>>

    @Query("DELETE FROM UserModels")
    abstract fun nukeUserModels()
}