package co.com.ceiba.mobile.pruebadeingreso.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.models.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UsersListViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val usersListDBMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val usersListAPIMutableLiveData: MutableLiveData<UIState> = MutableLiveData()

    fun getUsersListDBLiveData(): LiveData<UIState> = usersListDBMutableLiveData
    fun getUsersListAPILiveData(): LiveData<UIState> = usersListAPIMutableLiveData

    private val subscriptions = CompositeDisposable()

    fun getUsersListDB() {
        subscriptions.add(
            userRepository.getUsersDB()
                .doOnSubscribe {
                    usersListDBMutableLiveData.postValue(UIState.Loading)
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        usersListDBMutableLiveData.postValue(UIState.Success(it))
                    },
                    onError = {
                        usersListDBMutableLiveData.postValue(
                            UIState.Error(
                                it.message
                                    ?: "Error"
                            )
                        )
                    }
                ))
    }

    fun getUsersListAPI() {
        subscriptions.add(userRepository.getUsers()
            .doOnSubscribe {
                usersListAPIMutableLiveData.postValue(UIState.Loading)
            }.subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { data ->
                    usersListAPIMutableLiveData.postValue(UIState.Success(data))
                },
                onError = {
                    usersListAPIMutableLiveData.postValue(
                        UIState.Error(
                            it.message
                                ?: "Error"
                        )
                    )
                }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}