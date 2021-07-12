package co.com.ceiba.mobile.pruebadeingreso.uses_case.users

import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.data_source.repositories.RepoUser
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels
import javax.inject.Inject

class ConsulteForUserUseCase {

    @Inject
    lateinit var repoUser: RepoUser

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun invoke(sucess: ((MutableList<UserModels>)-> Unit), fail: (()-> Unit)) {
        repoUser.consulteUserQuery(sucess, fail)
    }

}