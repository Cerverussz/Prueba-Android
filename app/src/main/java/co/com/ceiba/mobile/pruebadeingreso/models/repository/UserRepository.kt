package co.com.ceiba.mobile.pruebadeingreso.models.repository

import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserRepository {

    fun insertUsers(users: List<InfoUser>): Completable

    @Query("SELECT * FROM infoUser")
    fun getUsers(): Flowable<List<InfoUser>>

}