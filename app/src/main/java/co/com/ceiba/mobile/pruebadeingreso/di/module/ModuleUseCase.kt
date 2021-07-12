package co.com.ceiba.mobile.pruebadeingreso.di.module

import co.com.ceiba.mobile.pruebadeingreso.di.scope.ApplicationScope
import co.com.ceiba.mobile.pruebadeingreso.uses_case.publications.ConsulteForPublicationsUseCase
import co.com.ceiba.mobile.pruebadeingreso.uses_case.users.ConsulteForUserUseCase
import dagger.Module
import dagger.Provides

@Module
class ModuleUseCase {

    @ApplicationScope
    @Provides
    fun provideConsulteForWoeid() = ConsulteForPublicationsUseCase()

    @ApplicationScope
    @Provides
    fun provideConsulteForUser() = ConsulteForUserUseCase()

}