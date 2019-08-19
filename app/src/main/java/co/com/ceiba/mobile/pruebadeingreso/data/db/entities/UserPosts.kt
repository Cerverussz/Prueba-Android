package co.com.ceiba.mobile.pruebadeingreso.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "userPosts")
data class UserPosts(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "userId")
    @field:Json(name = "userId")
    val userId: Int = 0,

    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    @field:Json(name = "title")
    val title: String = "",

    @ColumnInfo(name = "body")
    @field:Json(name = "body")
    val body: String = ""
): Serializable