package h.example.loginapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import h.example.loginapp.database.DBHandler
import h.example.loginapp.database.Model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun saveUser(userInfo : UserEntity)

    @Query("SELECT * FROM ${DBHandler.TABLE_NAME}")
    fun getUser() : Flow<List<UserEntity>>

}