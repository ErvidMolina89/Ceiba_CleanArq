package co.com.ceiba.mobile.pruebadeingreso.data_source.repositories

import android.content.Context
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.local_db.DBCeiba
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepoSynchronization {

    fun onInsertUserModels(context: Context, usersList: MutableList<UserModels>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(DBCeiba.getInstance(context).usersDao().insertList(usersList))
        }
    }

    fun onInsertPublicationsModels(context: Context, items: MutableList<PublicationsModels>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(DBCeiba.getInstance(context).publicationsDao().insertList(items))
        }
    }

    fun deleteAllUserModels(context: Context) =
        GlobalScope.launch {
            DBCeiba.getInstance(context).usersDao().nukeUserModels()
        }

    fun deleteAllPublicationsModels(context: Context) =
        GlobalScope.launch {
            DBCeiba.getInstance(context).publicationsDao().nukePublications()
        }

    fun getAllUserModels(context: Context)
            = DBCeiba.getInstance(context).usersDao().getUserModels()

    fun getPublicationsModelsForUserID(context: Context, id: Int)
            = DBCeiba.getInstance(context).publicationsDao().getPublicationsModelsForUserID(id)

    fun getAllPublicationsModels(context: Context)
        = DBCeiba.getInstance(context).publicationsDao().getPublicationsModels()
    }