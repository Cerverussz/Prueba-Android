package co.com.ceiba.mobile.pruebadeingreso.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

object Utilities {


    /**
     * Dismiss All Dialogs
     */
    fun dismissAllDialogs(manager: FragmentManager) {
        val fragments = manager.fragments

        for (fragment in fragments) {
            if (fragment is DialogFragment) {
                fragment.dismissAllowingStateLoss()
            }

            val childFragmentManager = fragment.childFragmentManager
            if (childFragmentManager != null)
                dismissAllDialogs(childFragmentManager)
        }
    }

    /**
     * Hide Keyboard
     *
     * @param activity
     */
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isAcceptingText) {
            if (activity.currentFocus != null) {
                imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            }
        }
    }

    /**
     * Show Keyboard
     *
     * @param activity
     * @param editText
     */
    fun showKeyboard(activity: Activity, editText: View) {
        editText.requestFocus()
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    /**
     * Decode HTML
     */
    fun decodeHtml(text: String): String {
        return text.replace("&amp;", "&")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&quot;", "\"")
            .replace("%20", " ")
            .replace("&#039;", "'")
    }

//    private fun isOnlineNet(): Boolean? {
//
//        try {
//            val p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es")
//
//            val test = p.waitFor()
//            return test == 0
//
//        } catch (e: Exception) {
//            // TODO Auto-generated catch block
//            e.printStackTrace()
//        }
//
//        return false
//    }
//
//    private fun isNetworkAvailable(): Boolean {
//        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE)
//        return if (connectivityManager is ConnectivityManager) {
//            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
//            networkInfo?.isConnected ?: false
//        } else false
//    }
}