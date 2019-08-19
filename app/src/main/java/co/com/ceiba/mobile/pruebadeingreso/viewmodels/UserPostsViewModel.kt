package co.com.ceiba.mobile.pruebadeingreso.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.models.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UserPostsViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val userPostsDBMutableLiveData: MutableLiveData<UIState> = MutableLiveData()

    fun getUserPostsDBLiveData(): LiveData<UIState> = userPostsDBMutableLiveData

    private val subscriptions = CompositeDisposable()

    fun getUserPostsDB(id: Int) {
        subscriptions.add(
            userRepository.getUserPostsDB(id)
               .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        userPostsDBMutableLiveData.postValue(UIState.Success(it))
                    },
                    onError = {
                        userPostsDBMutableLiveData.postValue(UIState.Error(it.message ?: "Error"))
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}