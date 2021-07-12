package co.com.ceiba.mobile.pruebadeingreso.view.postActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.models.publications.PublicationsModels

class PublicationsRecyclerViewAdapter  (
    val context : Context?,
    private var mValues: MutableList<PublicationsModels>
) : RecyclerView.Adapter<PublicationsRecyclerViewAdapter.ViewHolder>() {

    private var listener: ((PublicationsModels)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: PublicationsModels = mValues[position]

        holder.textview_title.text = item.title
        holder.textview_body.text = item.body

        setListeners(holder,item)
    }

    private fun setListeners(holder : ViewHolder, item : PublicationsModels){
        holder.mView
            .setOnClickListener {
                listener?.invoke(item)
            }
    }

    override fun getItemCount(): Int = mValues.size

    fun setData(listSearch : MutableList<PublicationsModels>){
        this.mValues = listSearch
        notifyDataSetChanged()
    }

    fun onClickListener(listener : (PublicationsModels)-> Unit){
        this.listener = listener
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val textview_title   : TextView = mView.findViewById(R.id.title)
        val textview_body    : TextView = mView.findViewById(R.id.body)

    }
}