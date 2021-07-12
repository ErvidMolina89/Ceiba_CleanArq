package co.com.ceiba.mobile.pruebadeingreso.models.publications

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import co.com.ceiba.mobile.pruebadeingreso.models.Base
import co.com.ceiba.mobile.pruebadeingreso.models.users.UserModels

@Entity(foreignKeys = [
    ForeignKey(entity = UserModels::class,
        parentColumns = ["Id"],
        childColumns = ["UserID"])
])
class PublicationsModels (

    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "UserID")    var userId      : Int? = null,
    @ColumnInfo(name = "Title")     var title       : String? = null,
    @ColumnInfo(name = "Body")      var body        : String? = null

): Base