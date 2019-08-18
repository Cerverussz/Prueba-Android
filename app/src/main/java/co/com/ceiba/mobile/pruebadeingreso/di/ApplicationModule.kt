package co.com.ceiba.mobile.pruebadeingreso.di

import co.com.ceiba.mobile.pruebadeingreso.models.repository.UserRepository
import co.com.ceiba.mobile.pruebadeingreso.models.repository.UserRepositoryImp
import org.koin.dsl.module

val applicationModule = module {
    factory<UserRepository> { UserRepositoryImp(get(), get()) }
}