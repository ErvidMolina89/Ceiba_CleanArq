@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package co.com.ceiba.mobile.pruebadeingreso.utils

import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//region dialogos
fun Context.showDialogGeneric(){
    if(!isAValidContextToDisplayMessage(this)){ return }
    if(this !is AppCompatActivity){
        getContextValid(this).showDialogGeneric()
        return
    }
    runOnUiThread {
        DialogGeneric.getInstance().showDialogue(supportFragmentManager, TagsDialogue.DialogGeneric.getTags())
    }
}

private fun isAValidContextToDisplayMessage(contex: Context) : Boolean{
    return contex is AppCompatActivity || contex is ContextWrapper
}

private fun getContextValid(contex : Context) : Context{
    var EndContex : Context = contex
    while((EndContex !is AppCompatActivity) && (EndContex is ContextWrapper)){
        EndContex = (EndContex as ContextWrapper).baseContext
    }
    return EndContex
}

fun Context.showProgress(){
    if(this !is AppCompatActivity){
        return
    }else{
        runOnUiThread {
            ProgressBarPersonalized
                .getInstance()
                .show(supportFragmentManager,"progressbar")
        }

        GlobalScope.launch {
            delay(30_000)
            hiddenProgress()
        }
    }
}

fun Context.hiddenProgress(){
    if(this !is AppCompatActivity){
        return
    }else{
        runOnUiThread {
            ProgressBarPersonalized
                .getInstance()
                .dismiss()

        }
    }
}

fun Context.isNetworkAvailable(): Boolean{
    try {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    } catch (e: Exception) {
        return false
    }
}
