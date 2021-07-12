package co.com.ceiba.mobile.pruebadeingreso.di.module

import co.com.ceiba.mobile.pruebadeingreso.di.scope.ApplicationScope
import co.com.ceiba.mobile.pruebadeingreso.view.homeActivity.UserViewModel
import co.com.ceiba.mobile.pruebadeingreso.view.postActivity.PublicationsViewModel
import dagger.Module
import dagger.Provides

@Module
class ModuleViewModels {

    @ApplicationScope
    @Provides
    fun providePublicationsViewModel() = PublicationsViewModel()

    @ApplicationScope
    @Provides
    fun provideUserViewModel() = UserViewModel()
}