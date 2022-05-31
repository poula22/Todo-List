package com.example.todo_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo_app.database.dao.TodoDao
import com.example.todo_app.model.DateConverter
import com.example.todo_app.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MyDataBase : RoomDatabase() {
    abstract fun todoDao():TodoDao
    companion object{
        private var myDataBase:MyDataBase?=null
        fun getInstance(context:Context):MyDataBase{
            if (myDataBase==null)
                myDataBase=Room.databaseBuilder(context,MyDataBase::class.java,"Todo_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            return myDataBase!!
        }
    }
}