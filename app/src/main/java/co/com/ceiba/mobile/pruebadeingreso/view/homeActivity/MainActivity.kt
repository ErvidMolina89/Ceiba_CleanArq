package co.com.ceiba.mobile.pruebadeingreso.view.homeActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.base.App
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints
import co.com.ceiba.mobile.pruebadeingreso.utils.*
import co.com.ceiba.mobile.pruebadeingreso.view.postActivity.PostActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel
    private var listUsers: MutableList<UserModels> = emptyList<UserModels>().toMutableList()
    private var adapter: UserRecyclerViewAdapter = UserRecyclerViewAdapter(this, emptyList<UserModels>().toMutableList())

    override fun onCreate(savedInstanceState: Bundle?) {
        (App.getContext() as App).getComponentApplication()?.inject(this)
        super.onCreate(savedInstanceState)
        userViewModel.setDelegate(ResponseViewModel())
        setContentView(R.layout.activity_main)
        userViewModel.validateDB(this@MainActivity)
        onStyleRecycler()
        listenerEditTextSearch()
        listenerRecycler()
        this@MainActivity.hiddenProgress()
    }

    override fun onStart(){super.onStart()}

    private fun filterListUser (valor: String) :MutableList<UserModels>{
        val list = listUsers.filter {
            return@filter it.name?.toLowerCase()?.contains(valor.toLowerCase())!!
        }
        if(list.size ==  0){
            include_list_empty.visibility = View.VISIBLE
        } else{
            if(include_list_empty.visibility == View.VISIBLE){
                include_list_empty.visibility = View.GONE
            }
        }
        return list.toMutableList()
    }

    private fun hideKeyboard(){
        val imm: InputMethodManager? = this@MainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0)
    }

    private fun listenerEditTextSearch(){
        editTextSearch?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.setData(filterListUser(p0.toString()))
            }

        })
    }

    private fun listenerRecycler(){
        adapter.onClickListener {
            userViewModel.showDetailsItemSelect(it)
        }
    }

    private fun onStyleRecycler() {
        recyclerViewSearchResults!!.let {
            it.layoutManager  = LinearLayoutManager(this)
            this@MainActivity.adapter = UserRecyclerViewAdapter(
                    this,
                    emptyList<UserModels>().toMutableList()
            )
            recyclerViewSearchResults.adapter = this@MainActivity.adapter
        }
    }

    inner class ResponseViewModel : SearchViewModelDelegate {
        override fun setMediaUser(list: MutableList<UserModels>) {
            adapter.setData(list)
            listUsers = list.toMutableList()
            if(include_list_empty.visibility == View.VISIBLE){
                include_list_empty.visibility = View.GONE
            }
            this@MainActivity.hiddenProgress()
            hideKeyboard()
        }

        override fun setFailDataLoad() {
            "Fallo".showInlog("Respuesta Fallida")
            this@MainActivity.hiddenProgress()
        }

        override fun navigationToLocation(location: UserModels) {
            val intent = Intent(this@MainActivity, PostActivity::class.java)
            intent.putExtra(Endpoints.EXTRA_USER, location)
            startActivity(intent)
        }

        override fun notifyListEmpty() {
            adapter.setData(emptyList<UserModels>().toMutableList())
            this@MainActivity.hiddenProgress()
            include_list_empty.visibility = View.VISIBLE
            hideKeyboard()
        }
    }
}