package co.com.ceiba.mobile.pruebadeingreso.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.viewmodels.UIState
import co.com.ceiba.mobile.pruebadeingreso.viewmodels.UsersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val usersLisTViewModel: UsersListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersLisTViewModel.getUsersList()
    }

    private fun setupHandlers() {
        usersLisTViewModel.getMessagesLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "Loading...")
                }
                is UIState.Success<*> -> {
                    val data = status.data as MutableList<InfoUser>

                }
                is UIState.Error -> {
                    Log.i(TAG, status.message)
                }
            }
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
