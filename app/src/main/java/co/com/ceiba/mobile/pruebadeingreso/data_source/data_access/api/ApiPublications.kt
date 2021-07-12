package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.api

import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.dto.dto_publications.PublicationsDTO
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POST_USER
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiPublications {
    @GET(GET_POST_USER)
    fun getListPublications(): Call<MutableList<PublicationsDTO>>
}