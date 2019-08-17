package co.com.ceiba.mobile.pruebadeingreso.di

import androidx.room.Room
import co.com.ceiba.mobile.pruebadeingreso.data.db.DataBase
import org.koin.dsl.module

val roomModule = module {
    single { Room.databaseBuilder(get(), DataBase::class.java, "database_base").build() }
    single { get<DataBase>().infoUserDAO() }
}