package co.com.ceiba.mobile.pruebadeingreso.di.component

import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.publications.PublicationsRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.users.UserRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.data_source.repositories.RepoPublications
import co.com.ceiba.mobile.pruebadeingreso.data_source.repositories.RepoUser
import co.com.ceiba.mobile.pruebadeingreso.di.module.*
import co.com.ceiba.mobile.pruebadeingreso.uses_case.publications.ConsulteForPublicationsUseCase
import co.com.ceiba.mobile.pruebadeingreso.uses_case.users.ConsulteForUserUseCase
import co.com.ceiba.mobile.pruebadeingreso.view.homeActivity.MainActivity
import co.com.ceiba.mobile.pruebadeingreso.view.homeActivity.UserViewModel
import co.com.ceiba.mobile.pruebadeingreso.view.postActivity.PostActivity
import co.com.ceiba.mobile.pruebadeingreso.view.postActivity.PublicationsViewModel
import dagger.Component

@Component(modules = [
    ModuleApplication::class,
    RetrofitModule::class,
    ModuleDataSource::class,
    ModuleUseCase::class,
    ModuleRepositories::class,
    ModuleViewModels::class
])
interface ComponentApplication {

    //DataSource publications
    fun inject(publicationsRemoteDataSource: PublicationsRemoteDataSource)

    //Repositories publications
    fun inject(repoPublications: RepoPublications)

    //Use Case publications
    fun inject(consulteForPublicationsUseCase: ConsulteForPublicationsUseCase)

    //View Models publications
    fun inject(publicationsViewModel: PublicationsViewModel)

    //View Models search
    fun inject(searchViewModel: UserViewModel)

    //DataSource search
    fun inject(searchRemoteDataSource: UserRemoteDataSource)

    //Repositories search
    fun inject(repoUser: RepoUser)

//    Use Case search
    fun inject(consulteForUserUseCase: ConsulteForUserUseCase)

    //MainActivity
    fun inject(mainActivity: MainActivity)

    //PostActivity
    fun inject(postActivity: PostActivity)

}