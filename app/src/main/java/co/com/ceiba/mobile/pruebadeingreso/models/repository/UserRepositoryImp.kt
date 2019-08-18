package co.com.ceiba.mobile.pruebadeingreso.models.repository

import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.InfoUserDao
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.data.remote.api.ApiService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class UserRepositoryImp(private val serviceAPI: ApiService, private val userDao: InfoUserDao) : UserRepository {

    override fun getUsersDB(): Flowable<List<InfoUser>> = userDao.getUsers()

    override fun insertUsers(users: List<InfoUser>): Completable = userDao.insertUsers(users)

    override fun getUsers(): Single<List<InfoUser>> {
        return serviceAPI.getUsersList().flatMap {
            insertUsers(it)
            Single.just(it)
        }
    }


}