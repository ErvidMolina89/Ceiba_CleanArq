package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.users

import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.api.ApiUser
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.dto.dto_users.UserDTO
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.maper.users.fromDTO
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels
import co.com.ceiba.mobile.pruebadeingreso.utils.showInlog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource {

    @Inject lateinit var apiUser: ApiUser

    init {
        (App.getContext() as App)
            .getComponentApplication()
            ?.inject(this)
    }

    fun getListUser(sucess: ((MutableList<UserModels>)-> Unit), fail: (()-> Unit)){
        apiUser.getListUser().enqueue(object: Callback<MutableList<UserDTO>>{

            override fun onResponse(
                call: Call<MutableList<UserDTO>>,
                response: Response<MutableList<UserDTO>>
            ) {
                if(response.body() != null){
                    sucess.invoke(
                    response.body()!!.map { it ->
                        return@map UserModels().fromDTO(it)
                    }.toMutableList()
                    )
                }
            }

            override fun onFailure(call: Call<MutableList<UserDTO>>, t: Throwable) {
                fail.invoke()
                "fail: getUser".showInlog(tag = "UserDataSource", t = t)
            }

        })
    }
}