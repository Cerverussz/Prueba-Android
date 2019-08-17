package co.com.ceiba.mobile.pruebadeingreso.app

import android.app.Application
import co.com.ceiba.mobile.pruebadeingreso.di.applicationModule
import co.com.ceiba.mobile.pruebadeingreso.di.networkModule
import co.com.ceiba.mobile.pruebadeingreso.di.roomModule
import co.com.ceiba.mobile.pruebadeingreso.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            modules(
                    listOf(
                            applicationModule,
                            networkModule,
                            roomModule,
                            viewModelModule
                    )
            )
        }
    }
}