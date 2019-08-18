package co.com.ceiba.mobile.pruebadeingreso.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.core.ConnectivityHelper
import co.com.ceiba.mobile.pruebadeingreso.core.onChange
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.data.remote.api.ApiService
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UsersListAdapter
import co.com.ceiba.mobile.pruebadeingreso.viewmodels.UIState
import co.com.ceiba.mobile.pruebadeingreso.viewmodels.UsersListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.progressDialog
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val usersLisTViewModel: UsersListViewModel by viewModel()

    private lateinit var usersListAdapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()

        setupHandler()
        checkNetwork()

        setupUsersSearch()
    }

    private fun setupUI() {
        usersListAdapter = UsersListAdapter { infoUser ->
            Intent(this@MainActivity, PostActivity::class.java).run {
                putExtra("id", infoUser.id)
                putExtra("name", infoUser.name)
                putExtra("phone", infoUser.phone)
                putExtra("email", infoUser.email)
                startActivity(this)
            }
        }
        recyclerViewSearchResults.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = usersListAdapter
        }
    }

    private fun setupHandler() {
        usersLisTViewModel.getUsersListDBLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "Loading...")
                }
                is UIState.Success<*> -> {
                    val data = status.data as MutableList<InfoUser>
                    if (data.count() != 0) {
                        usersListAdapter.setData(data)
                    } else {
                        empty_view.visibility = VISIBLE
                    }
                }
                is UIState.Error -> {
                    empty_view.visibility = VISIBLE
                    Log.i(TAG, status.message)
                }
            }
        })

        usersLisTViewModel.getUsersListAPILiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    progressBar.visibility = VISIBLE
                    Log.i(TAG, "Loading...")
                }
                is UIState.Success<*> -> {
                    progressBar.visibility = GONE
                    val data = status.data as MutableList<InfoUser>
                    if (data.count() != 0) {
                        usersListAdapter.setData(data)
                    } else {
                        empty_view.visibility = VISIBLE
                    }
                }
                is UIState.Error -> {
                    Log.i(TAG, status.message)
                }
            }
        })
    }

    private fun setupUsersSearch() {
        editTextSearch.onChange { text ->
            val filteredModelList = filter(usersListAdapter.getData(), text)
            if (filteredModelList.isNotEmpty()) {
                empty_view.visibility = GONE
            } else {
                empty_view.visibility = VISIBLE
            }
            usersListAdapter.animateTo(filteredModelList)
            recyclerViewSearchResults.scrollToPosition(0)
        }
    }

    private fun filter(models: ArrayList<InfoUser>, query: String): ArrayList<InfoUser> {
        val arrayQuery = query.toLowerCase().trim()
        val wordsArray = arrayQuery.split(" ")
        val filterList = ArrayList<InfoUser>()

        for (model in models) {
            val patient = model.name.toLowerCase()
            for (word in wordsArray) {
                if ((patient.contains(word) && !filterList.contains(model))) {
                    filterList.add(model)
                }
            }
        }
        return filterList
    }

    private fun checkNetwork() {
        if (ConnectivityHelper().isConnectedToNetwork(this@MainActivity)) {
            usersLisTViewModel.getUsersListAPI()
        } else {
            usersLisTViewModel.getUsersListDB()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
