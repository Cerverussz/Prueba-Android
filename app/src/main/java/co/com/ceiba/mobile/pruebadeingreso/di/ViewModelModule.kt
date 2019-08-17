package co.com.ceiba.mobile.pruebadeingreso.di

import co.com.ceiba.mobile.pruebadeingreso.viewmodels.UsersListViewModel
import co.com.ceiba.mobile.pruebadeingreso.viewmodels.UserPostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UsersListViewModel(get()) }
    viewModel { UserPostsViewModel(get()) }
}