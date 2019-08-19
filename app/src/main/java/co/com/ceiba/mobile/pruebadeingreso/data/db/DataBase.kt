package co.com.ceiba.mobile.pruebadeingreso.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.InfoUserDao
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserPosts

@Database(entities = [InfoUser::class, UserPosts::class], version = 4)
abstract class DataBase: RoomDatabase() {
    abstract fun infoUserDAO(): InfoUserDao
}