package h.example.loginapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import h.example.loginapp.database.Model.UserEntity
import h.example.loginapp.database.dao.UserDao

@Database(
    [UserEntity::class] ,
    version = DBHandler.DATABASE_VERSION
)
abstract class DBHandler : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{

        private const val DATABASE_NAME = "user_database"
         const val DATABASE_VERSION = 1

        const val TABLE_NAME = "User"

        private var INSTANCE : DBHandler? = null

        fun getData(context: Context) : DBHandler{

            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    context ,
                    DBHandler::class.java ,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return INSTANCE!!

        }

    }

}