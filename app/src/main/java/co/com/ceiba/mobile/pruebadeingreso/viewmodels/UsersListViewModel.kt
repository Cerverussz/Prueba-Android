package co.com.ceiba.mobile.pruebadeingreso.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.models.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UsersListViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val usersListMutableLiveData: MutableLiveData<UIState> = MutableLiveData()

    fun getMessagesLiveData(): LiveData<UIState> = usersListMutableLiveData

    private val subscriptions = CompositeDisposable()

    fun getUsersList() {
        subscriptions.add(
                userRepository.getUsers()
                        .doOnSubscribe {
                            usersListMutableLiveData.postValue(UIState.Loading)
                        }.subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    usersListMutableLiveData.postValue(UIState.Success(it))
                                },
                                onError = {
                                    usersListMutableLiveData.postValue(UIState.Error(it.message
                                            ?: "Error"))
                                },
                                onComplete = {
                                    // do something
                                }
                        ))
    }


}