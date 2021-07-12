package co.com.ceiba.mobile.pruebadeingreso.view.homeActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels

class UserRecyclerViewAdapter  (
    val context : Context?,
    private var mValues: MutableList<UserModels>
) : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    private var listener: ((UserModels)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: UserModels = mValues[position]

        holder.textview_title.text = item.name
        holder.textview_phone.text = item.phone
        holder.textView_email.text = item.email

        setListeners(holder,item)
    }

    private fun setListeners(holder : ViewHolder, item : UserModels){

        holder.btn_view_post
            .setOnClickListener {
                listener?.invoke(item)
            }
    }

    override fun getItemCount(): Int = mValues.size

    fun setData(listSearch : MutableList<UserModels>){
        this.mValues = listSearch
        notifyDataSetChanged()
    }

    fun onClickListener(listener : (UserModels)-> Unit){
        this.listener = listener
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val textview_title   : TextView = mView.findViewById(R.id.name)
        val textview_phone   : TextView = mView.findViewById(R.id.phone)
        val textView_email   : TextView = mView.findViewById(R.id.email)
        val btn_view_post    : TextView = mView.findViewById(R.id.btn_view_post)

    }
}