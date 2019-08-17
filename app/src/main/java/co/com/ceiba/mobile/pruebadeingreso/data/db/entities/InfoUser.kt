package co.com.ceiba.mobile.pruebadeingreso.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "infoUser")
data class InfoUser (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @field:Json(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    @field:Json(name = "name")
    val name: String = "",

    @ColumnInfo(name = "email")
    @field:Json(name = "email")
    val email: String = "",

    @ColumnInfo(name = "phone")
    @field:Json(name = "phone")
    val phone: String = ""
)