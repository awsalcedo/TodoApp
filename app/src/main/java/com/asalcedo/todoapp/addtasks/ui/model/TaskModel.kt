package com.asalcedo.todoapp.addtasks.ui.model

import com.asalcedo.todoapp.addtasks.data.TaskEntity

/*
* id -> identificador único
* taskName -> nombre de la tarea
* selected usado para marcar y desmarcar como tarea no realizada
* es decir vamosa modificar, debe ir con var y también debe ser al principio una tarea no realizada
* */
data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val taskName: String,
    var selected: Boolean = false
)

fun TaskEntity.toDomain() = TaskModel(id, taskName, selected)
