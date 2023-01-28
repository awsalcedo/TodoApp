package com.asalcedo.todoapp.addtasks.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/****
 * Project: TodoApp
 * From: com.asalcedo.todoapp.addtasks.data
 * Created by Alex Salcedo Silva on 27/1/23 at 20:57
 * All rights reserve 2022.
 ***/
@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(taskEntity: TaskEntity)
}