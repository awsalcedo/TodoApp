package com.asalcedo.todoapp.addtasks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asalcedo.todoapp.addtasks.ui.model.TaskModel

/****
 * Project: TodoApp
 * From: com.asalcedo.todoapp.addtasks.data
 * Created by Alex Salcedo Silva on 27/1/23 at 20:46
 * All rights reserve 2022.
 ***/
@Entity(tableName = "")
data class TaskEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "task_name")
    val taskName: String,
    @ColumnInfo(name = "selected")
    val selected: Boolean = false
)
