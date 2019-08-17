package co.com.ceiba.mobile.pruebadeingreso.models.repository

import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import io.reactivex.Completable
import io.reactivex.Flowable

class UserRepositoryImp(private val userDao: UserRepository) : UserRepository {

    override fun insertUsers(users: List<InfoUser>): Completable = userDao.insertUsers(users)

    override fun getUsers(): Flowable<List<InfoUser>> = userDao.getUsers()


}