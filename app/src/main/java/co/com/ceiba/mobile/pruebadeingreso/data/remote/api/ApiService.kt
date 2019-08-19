package co.com.ceiba.mobile.pruebadeingreso.data.remote.api

import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.entities.PostUser
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    fun serachsUsers(): Flowable<List<InfoUser>>

    @GET("/posts")
    fun getPosts(): Flowable<List<PostUser>>
}