package co.com.ceiba.mobile.pruebadeingreso.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserPosts
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface InfoUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<InfoUser>): Completable

    @Query("SELECT * FROM infoUser")
    fun getUsers(): Flowable<List<InfoUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserPost(userPosts: List<UserPosts>): Completable

    @Query("SELECT * FROM userPosts WHERE userPosts.userId = :id")
    fun getUserPosts(id: Int): Flowable<List<UserPosts>>
}