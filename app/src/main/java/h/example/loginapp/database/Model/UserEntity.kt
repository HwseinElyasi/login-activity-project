package h.example.loginapp.database.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import h.example.loginapp.database.DBHandler

@Entity(tableName = DBHandler.TABLE_NAME)
data class UserEntity(
   @ColumnInfo val email : String ,
   @ColumnInfo val code : String ,
   @PrimaryKey val androidId : String
)
