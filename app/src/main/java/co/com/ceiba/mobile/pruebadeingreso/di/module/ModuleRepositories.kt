package co.com.ceiba.mobile.pruebadeingreso.di.module

import co.com.ceiba.mobile.pruebadeingreso.data_source.repositories.RepoPublications
import co.com.ceiba.mobile.pruebadeingreso.data_source.repositories.RepoUser
import co.com.ceiba.mobile.pruebadeingreso.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ModuleRepositories {

    @ApplicationScope
    @Provides
    fun providesRepositoriesPublications() = RepoPublications()

    @ApplicationScope
    @Provides
    fun providesRepositoriesUser() = RepoUser()
}