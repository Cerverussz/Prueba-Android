package co.com.ceiba.mobile.pruebadeingreso.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.remote.api.ApiService
import co.com.ceiba.mobile.pruebadeingreso.entities.UserPosts
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UserPostsAdapter
import co.com.ceiba.mobile.pruebadeingreso.viewmodels.UserPostsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_post.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostActivity : AppCompatActivity() {

    // ViewModel
    private val userPostsViewModel: UserPostsViewModel by viewModel()

    // Adapter
    private lateinit var userPostsAdapter: UserPostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setupUserInfo()
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        userPostsAdapter = UserPostsAdapter()
        recyclerViewPostsResults.apply {
            layoutManager = LinearLayoutManager(this@PostActivity, RecyclerView.VERTICAL, false)
            adapter = userPostsAdapter
        }
    }

    private fun setupUserInfo() {
        intent?.extras?.let {
            name.text = it.getString("name", "Jhon doe")
            phone.text = it.getString("phone", "1-770-736-8031 x56442")
            email.text = it.getString("email", "jhondoe@email.com")
        }
    }

    @SuppressLint("CheckResult")
    private fun setupObserver() {
        val serviceAPI: ApiService = get()

        val idUser = intent?.extras?.getInt("id", 0)
        serviceAPI.getUserPosts(idUser!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                        onSuccess = { data ->
                            val userPosts = data as MutableList<UserPosts>
                            userPostsAdapter.setData(userPosts)
                        },
                        onError = {
                            Log.i(TAG, it.message ?: "Error")
                        }
                )
    }

    companion object {
        const val TAG = "PostActivity"
    }

}
