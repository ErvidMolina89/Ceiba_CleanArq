package co.com.ceiba.mobile.pruebadeingreso.view.postActivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.EXTRA_USER
import co.com.ceiba.mobile.pruebadeingreso.utils.hiddenProgress
import co.com.ceiba.mobile.pruebadeingreso.utils.showInlog
import co.com.ceiba.mobile.pruebadeingreso.utils.showProgress
import co.com.ceiba.mobile.pruebadeingreso.view.homeActivity.SearchViewModelDelegate
import co.com.ceiba.mobile.pruebadeingreso.view.homeActivity.UserRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_post.*
import javax.inject.Inject

class PostActivity : AppCompatActivity()  {
    @Inject
    lateinit var postListViewModel: PublicationsViewModel
    private var adapterRecyclerView: PublicationsRecyclerViewAdapter = PublicationsRecyclerViewAdapter(this, emptyList<PublicationsModels>().toMutableList())

    override fun onCreate(savedInstanceState: Bundle?) {
        (App.getContext() as App).getComponentApplication()?.inject(this)
        super.onCreate(savedInstanceState)
        postListViewModel.setDelegate(ResponseViewModel())
        setContentView(R.layout.activity_post)
        postListViewModel.user.value = intent.extras?.get(EXTRA_USER) as UserModels
        onStyleRecycler()
        listener()
        postListViewModel.getListPublications(this@PostActivity)
        this@PostActivity.showProgress()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun dataContentCard(user: UserModels){
        name.text   = user.name
        phone.text  = user.phone
        email.text  = user.email
    }

    private fun listener(){
        adapterRecyclerView.onClickListener {

        }
        postListViewModel.user.observeForever {
            dataContentCard(it)
        }
    }

    private fun onStyleRecycler() {
        recyclerViewPostsResults!!.let {
            it.layoutManager  = LinearLayoutManager(this@PostActivity)

            this@PostActivity.adapterRecyclerView = PublicationsRecyclerViewAdapter(
                    this,
                    emptyList<PublicationsModels>().toMutableList()
            )
            recyclerViewPostsResults.adapter = adapterRecyclerView
        }
    }


    inner class ResponseViewModel : PublicationsViewModelDelegate {
        override fun setMediaQuery(list: MutableList<PublicationsModels>) {
            adapterRecyclerView.setData(list)
            this@PostActivity.hiddenProgress()
        }

        override fun notifyListEmpty() {
            adapterRecyclerView.setData(emptyList<PublicationsModels>().toMutableList())
            this@PostActivity.hiddenProgress()
        }

    }
}