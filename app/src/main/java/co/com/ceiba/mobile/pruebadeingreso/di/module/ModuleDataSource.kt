package co.com.ceiba.mobile.pruebadeingreso.di.module

import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.publications.PublicationsRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.users.UserRemoteDataSource
import co.com.ceiba.mobile.pruebadeingreso.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ModuleDataSource {

    @ApplicationScope
    @Provides
    fun providesPublicationsRemoteDataSource() = PublicationsRemoteDataSource()

    @ApplicationScope
    @Provides
    fun providesUserRemoteDataSource() = UserRemoteDataSource()
}