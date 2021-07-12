package co.com.ceiba.mobile.pruebadeingreso.di.module

import android.app.Application
import co.com.ceiba.mobile.pruebadeingreso.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ModuleApplication (val application: Application) {

    @ApplicationScope
    @Provides
    fun providesApplication() = application

    @ApplicationScope
    @Provides
    fun providesContext() = application.applicationContext

}