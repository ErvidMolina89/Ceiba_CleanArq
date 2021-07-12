package co.com.ceiba.mobile.pruebadeingreso.utils

import android.util.Log

fun String.showInlog(tag: String = "Log", t: Throwable? = null) :String {
    Log.e(tag, this, t)
    return this
}