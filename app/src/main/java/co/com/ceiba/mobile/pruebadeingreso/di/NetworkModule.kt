package co.com.ceiba.mobile.pruebadeingreso.di

import co.com.ceiba.mobile.pruebadeingreso.core.Network
import co.com.ceiba.mobile.pruebadeingreso.data.remote.api.ApiService
import org.koin.dsl.module

val networkModule = module {
    single { Network().createWebService().create(ApiService::class.java) }
}