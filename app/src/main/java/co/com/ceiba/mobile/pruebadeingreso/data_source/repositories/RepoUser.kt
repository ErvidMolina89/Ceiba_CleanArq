package co.com.ceiba.mobile.pruebadeingreso.data_source.repositories

import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.users.UserRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels
import javax.inject.Inject

class RepoUser {

    @Inject
    lateinit var searchRemoteDataSource: UserRemoteDataSource

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun consulteUserQuery(sucess: ((MutableList<UserModels>)-> Unit), fail: (()-> Unit)){
        searchRemoteDataSource.getListUser(sucess, fail)
    }

}