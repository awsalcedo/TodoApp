package com.asalcedo.todoapp.addtasks.data

import com.asalcedo.todoapp.addtasks.ui.model.TaskModel
import com.asalcedo.todoapp.addtasks.ui.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/****
 * Project: TodoApp
 * From: com.asalcedo.todoapp.addtasks.data
 * Created by Alex Salcedo Silva on 27/1/23 at 21:55
 * All rights reserve 2022.
 ***/
@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(TaskEntity(taskModel.id, taskModel.taskName, taskModel.selected))
    }

    val tasks: Flow<List<TaskModel>> = taskDao.getAllTask().map {
        items -> items.map { it.toDomain() }
    }
}