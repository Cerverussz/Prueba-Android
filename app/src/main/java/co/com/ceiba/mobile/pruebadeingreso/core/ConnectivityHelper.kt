package co.com.ceiba.mobile.pruebadeingreso.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


class ConnectivityHelper {

    fun isConnectedToNetwork(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}