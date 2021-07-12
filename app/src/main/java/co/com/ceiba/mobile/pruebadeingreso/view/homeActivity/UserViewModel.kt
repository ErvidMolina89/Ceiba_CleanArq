package co.com.ceiba.mobile.pruebadeingreso.view.homeActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.data_source.repositories.RepoSynchronization
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels
import co.com.ceiba.mobile.pruebadeingreso.uses_case.publications.ConsulteForPublicationsUseCase
import co.com.ceiba.mobile.pruebadeingreso.uses_case.users.ConsulteForUserUseCase
import co.com.ceiba.mobile.pruebadeingreso.utils.*
import javax.inject.Inject

interface SearchViewModelDelegate {
    fun setMediaUser(list: MutableList<UserModels>)
    fun setFailDataLoad()
    fun navigationToLocation(location: UserModels)
    fun notifyListEmpty()
}

class UserViewModel : ViewModel() {

    @Inject
    lateinit var consulteForSearchUseCase: ConsulteForUserUseCase
    @Inject
    lateinit var consulteForPublicationsUseCase: ConsulteForPublicationsUseCase
    private var delegate: SearchViewModelDelegate? = null
    private val synchronization = RepoSynchronization()

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun validateDB(context: Context) {
        synchronization.getAllUserModels(context).observeForever {
            if (it?.size != 0) {
                delegate?.setMediaUser(it as MutableList<UserModels>)
            } else {
                if(context.isNetworkAvailable()){
                    context.showProgress()
                    callInfoSearch()
                } else {
                    DialogGeneric
                            .getInstance()
                            .withTitle(R.string.internet)
                            .withText(context.getString(R.string.no_internet))
                            .withTextBtnOk(R.string.btn_accept)
                            .withActionBtnOk {

                            }
                    context.showDialogGeneric()
                }
            }
        }
    }

    fun callInfoSearch(){
        consulteForSearchUseCase.invoke(
            {
                if(it.size == 0){
                    delegate?.notifyListEmpty()
                }else{
                    delegate?.setMediaUser(it as MutableList<UserModels>)
                    synchronization.onInsertUserModels(App.getContext() as App, it, ::onSuccessInsertUser)
                }
            },
            {
                delegate?.setFailDataLoad()
                "Fallo algo servicio User".showInlog("Respuesta")
            }
        )
        consulteForPublicationsUseCase.invoke(
                {
                    if(it.size != 0) {
                        synchronization.onInsertPublicationsModels(App.getContext() as App, it  as MutableList<PublicationsModels>, ::onSuccessInsertPublication)
                    }
                },
                {
                    delegate?.setFailDataLoad()
                    "Fallo algo servicio Publicaciones".showInlog("Respuesta")
                }
        )
    }

    fun setDelegate(delegate: SearchViewModelDelegate){
        this.delegate = delegate
    }

    fun showDetailsItemSelect(location: UserModels){
        delegate?.navigationToLocation(location)
    }

    private fun onSuccessInsertUser(list: List<Long>) {
        val result: List<Long> = list
    }

    private fun onSuccessInsertPublication(list: List<Long>) {
        val result: List<Long> = list
    }
}