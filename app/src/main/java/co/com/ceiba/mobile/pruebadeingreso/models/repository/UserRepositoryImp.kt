package co.com.ceiba.mobile.pruebadeingreso.models.repository

import android.annotation.SuppressLint
import android.util.Log
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.InfoUserDao
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.data.remote.api.ApiService
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UserRepositoryImp(private val serviceAPI: ApiService, private val userDao: InfoUserDao) : UserRepository {

    override fun getUsers(): Single<List<InfoUser>> {
        return serviceAPI.getUsersList()
            .flatMap {
                insertUsers(it)
                Single.just(it)
            }
    }

    @SuppressLint("CheckResult")
    override fun insertUsers(users: List<InfoUser>) {
        userDao.insertUsers(users)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    Log.i(TAG, "onComplete")
                },
                onError = {
                    Log.i(TAG, it.message ?: "Ah ocurrido un error")
                }
            )
    }

    override fun getUsersDB(): Flowable<List<InfoUser>> = userDao.getUsers()

    companion object {
        const val TAG = "UserRepositoryImp"
    }
}