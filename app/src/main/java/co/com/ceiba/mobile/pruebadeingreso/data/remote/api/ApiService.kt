package co.com.ceiba.mobile.pruebadeingreso.data.remote.api

import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserPosts
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POST_USER
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_USERS
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GET_USERS)
    fun getUsersList(): Single<List<InfoUser>>

    @GET(GET_POST_USER)
    fun getPosts(): Flowable<List<UserPosts>>

    @GET("posts")
    fun getUserPosts(@Query("userId") id: Int): Single<List<UserPosts>>
}