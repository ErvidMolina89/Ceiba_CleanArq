package co.com.ceiba.mobile.pruebadeingreso.data_source.repositories

import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.publications.PublicationsRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels
import javax.inject.Inject

class RepoPublications {

    @Inject
    lateinit var publicationsRemoteDataSource: PublicationsRemoteDataSource

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun consultePublications(sucess: ((MutableList<PublicationsModels>)-> Unit), fail: (()-> Unit)){
        publicationsRemoteDataSource.getConsolidatedPublications(sucess, fail)
    }

}