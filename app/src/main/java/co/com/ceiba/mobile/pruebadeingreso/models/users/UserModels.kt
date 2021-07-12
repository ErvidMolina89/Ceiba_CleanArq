package co.com.ceiba.mobile.pruebadeingreso.models.users

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import co.com.ceiba.mobile.pruebadeingreso.models.Base

@Entity
data class UserModels(
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "Name")      var name        : String? = null,
    @ColumnInfo(name = "UserName")  var username    : String? = null,
    @ColumnInfo(name = "Email")     var email       : String? = null,
    @ColumnInfo(name = "Phone")     var phone       : String? = null

): Base