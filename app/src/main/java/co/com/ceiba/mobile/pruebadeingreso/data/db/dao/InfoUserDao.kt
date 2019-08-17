package co.com.ceiba.mobile.pruebadeingreso.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface InfoUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(users: List<InfoUser>): Completable

    @Query("SELECT * FROM infoUser")
    fun getUsers(): Flowable<List<InfoUser>>
}