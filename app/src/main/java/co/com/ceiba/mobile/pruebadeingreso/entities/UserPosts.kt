package co.com.ceiba.mobile.pruebadeingreso.entities

import com.squareup.moshi.Json

data class UserPosts(

    @field:Json(name = "userId")
    val userId: Int,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "body")
    val body: String
)