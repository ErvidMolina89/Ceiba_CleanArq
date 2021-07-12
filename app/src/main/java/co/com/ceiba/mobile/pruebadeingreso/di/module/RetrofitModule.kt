package co.com.ceiba.mobile.pruebadeingreso.di.module

import co.com.ceiba.mobile.pruebadeingreso.di.scope.ApplicationScope
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.URL_BASE
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.api.ApiPublications
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.api.ApiUser
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule {

    @ApplicationScope
    @Provides
    fun providesGsonConverterFactory() = GsonConverterFactory.create()!!

    @ApplicationScope
    @Provides
    fun providesHttpLoginInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @ApplicationScope
    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    @ApplicationScope
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @ApplicationScope
    @Provides
    fun provideApiPublications(retrofit: Retrofit) = retrofit.create(ApiPublications::class.java)

    @ApplicationScope
    @Provides
    fun provideApiSearch(retrofit: Retrofit) = retrofit.create(ApiUser::class.java)
}