package co.com.ceiba.mobile.pruebadeingreso.models.repository

import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserRepository {

    fun insertUsers(users: List<InfoUser>): Completable

    fun getUsers(): Single<List<InfoUser>>

    fun getUsersDB(): Flowable<List<InfoUser>>

}