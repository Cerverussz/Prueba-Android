package co.com.ceiba.mobile.pruebadeingreso.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
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

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val serviceAPI: ApiService = get()

        setContentView(R.layout.activity_main)
        setupUI()
        setupHandlers()

        serviceAPI.getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                        onNext = { data ->
                            usersLisTViewModel.insertUsersAPI(data)
                            Log.i(TAG, "--- $data")
                        },
                        onError = {
                            Log.i(TAG, it.message ?: "Error")
                        },
                        onComplete = {
                            usersLisTViewModel.getUsersListDB()
                        }
                )
    }

    private fun setupUI() {
        usersListAdapter = UsersListAdapter {infoUser ->
            Intent(this@MainActivity, PostActivity::class.java).run {
                putExtra("infoUser", infoUser)
                startActivity(this)
            }
        }
        recyclerViewSearchResults.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = usersListAdapter
        }
    }

    private fun setupHandlers() {


        usersLisTViewModel.getUsersListDBLiveData().observe(this, Observer { status ->
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
                        //TODO: add view empty list
                    }
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
