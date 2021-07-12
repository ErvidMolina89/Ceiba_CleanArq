package co.com.ceiba.mobile.pruebadeingreso.view.postActivity

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.data_source.repositories.RepoSynchronization
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels

interface PublicationsViewModelDelegate {
    fun setMediaQuery(list: MutableList<PublicationsModels>)
    fun notifyListEmpty()
}
class PublicationsViewModel : ViewModel() {

    private var delegate: PublicationsViewModelDelegate? = null
    private val synchronization = RepoSynchronization()
    val user : MutableLiveData<UserModels> = MutableLiveData()

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun getListPublications(context: Context){
        synchronization.getPublicationsModelsForUserID(context, user.value?.id!!).observeForever {
            if (it?.size != 0) {
                delegate?.setMediaQuery(it as MutableList<PublicationsModels>)
            } else {
                delegate?.notifyListEmpty()
            }
        }
    }

    fun setDelegate(delegate: PublicationsViewModelDelegate){
        this.delegate = delegate
    }
}