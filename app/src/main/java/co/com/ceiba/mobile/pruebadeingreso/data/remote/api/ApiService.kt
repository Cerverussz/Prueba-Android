package co.com.ceiba.mobile.pruebadeingreso.data.remote.api

import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.entities.PostUser
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POST_USER
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_USERS
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(GET_USERS)
    fun serachsUsers(): Observable<List<InfoUser>>

    @GET(GET_POST_USER)
    fun getPosts(): Observable<List<PostUser>>

    @GET("posts?userId={id}")
    fun getUserPosts(@Path("id") id: Int): Observable<List<PostUser>>
}