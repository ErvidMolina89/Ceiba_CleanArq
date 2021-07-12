package co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.publications

import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.api.ApiPublications
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.dto.dto_publications.PublicationsDTO
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.maper.publications.fromDTO
import co.com.ceiba.mobile.pruebadeingreso.data_source.data_access.maper.users.fromDTO
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels
import co.com.ceiba.mobile.pruebadeingreso.utils.showInlog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PublicationsRemoteDataSource {

    @Inject lateinit var apiPublications: ApiPublications

    init {
        (App.getContext() as App)
            .getComponentApplication()
            ?.inject(this)
    }

    fun getConsolidatedPublications( sucess: ((MutableList<PublicationsModels>)-> Unit), fail: (()-> Unit)){
        apiPublications.getListPublications().enqueue(object: Callback<MutableList<PublicationsDTO>>{

            override fun onResponse(
                call: Call<MutableList<PublicationsDTO>>,
                response: Response<MutableList<PublicationsDTO>>
            ) {
                if(response.body() != null){
                    sucess.invoke(response.body()!!.map { it ->
                        return@map PublicationsModels().fromDTO(it)
                    }.toMutableList()
                    )
                }
            }

            override fun onFailure(call: Call<MutableList<PublicationsDTO>>, t: Throwable) {
                fail.invoke()
                "fail: getPublications".showInlog(tag = "PublicationsDataSource", t = t)
            }

        })
    }
}