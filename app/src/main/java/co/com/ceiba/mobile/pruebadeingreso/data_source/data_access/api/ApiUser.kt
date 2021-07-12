package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.api

import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_USERS
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.dto.dto_users.UserDTO
import retrofit2.Call
import retrofit2.http.GET

interface ApiUser {
    @GET(GET_USERS)
    fun getListUser(): Call<MutableList<UserDTO>>
}