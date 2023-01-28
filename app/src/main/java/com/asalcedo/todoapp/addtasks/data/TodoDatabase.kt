package com.asalcedo.todoapp.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

/****
 * Project: TodoApp
 * From: com.asalcedo.todoapp.addtasks.data
 * Created by Alex Salcedo Silva on 27/1/23 at 20:24
 * All rights reserve 2022.
 ***/
@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}