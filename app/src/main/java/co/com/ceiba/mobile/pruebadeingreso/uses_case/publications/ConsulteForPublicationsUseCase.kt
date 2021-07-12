package co.com.ceiba.mobile.pruebadeingreso.uses_case.publications

import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.data_source.repositories.RepoPublications
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels
import javax.inject.Inject

class ConsulteForPublicationsUseCase {

    @Inject
    lateinit var repoPublications: RepoPublications

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun invoke(sucess: ((MutableList<PublicationsModels>)-> Unit), fail: (()-> Unit)) {
        repoPublications.consultePublications(sucess, fail)
    }

}