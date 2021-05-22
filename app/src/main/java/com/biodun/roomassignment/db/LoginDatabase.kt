package com.biodun.roomassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.biodun.roomassignment.db.dao.LoginDao
import com.biodun.roomassignment.db.entities.LoginEntity

@Database(
    entities = [LoginEntity::class],
    version = 1
)
abstract class LoginDatabase : RoomDatabase() {
    abstract fun loginDao() : LoginDao

    companion object{
        @Volatile private var instance: LoginDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context, LoginDatabase::class.java, "note.db").build()
    }
}