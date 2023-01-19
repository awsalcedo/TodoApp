package com.asalcedo.todoapp.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.asalcedo.todoapp.addtasks.ui.model.TaskModel

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(initial = false)
    Box(modifier = Modifier.fillMaxSize()) {
        AddTasksDialog(
            showDialog,
            onDismiss = { tasksViewModel.onDialogClose() },
            onTaskAdded = { tasksViewModel.onTasksCreated(it) })
        FabDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)
        TaskList(tasksViewModel)
    }
}

@Composable
fun TaskList(tasksViewModel: TasksViewModel) {
    val myTasks: List<TaskModel> = tasksViewModel.tasks
    LazyColumn {
        /*
        * con key optimizamos al LazyColumn
        * */
        items(myTasks, key = { it.id }) { task ->
            ItemTask(taskModel = task, tasksViewModel = tasksViewModel)
        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel, tasksViewModel: TasksViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 8.dp
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.taskName, modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { tasksViewModel.onCheckBoxSelected(taskModel) })
        }
    }

}

@Composable
fun FabDialog(modifier: Modifier, tasksViewModel: TasksViewModel) {
    FloatingActionButton(
        onClick = { tasksViewModel.onShowDialog() }, modifier = modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
    }
}

/*
* show para saber si se muestra o no el diálog
* la función lambda onDismiss para que se oculte el diálogo
* la función lambda onTaskAdded sirve para devolver la tarea ingresada en el TextField
* */
@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTask by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add you task",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(0xFFB2B2B2),
                        backgroundColor = Color(0xFFFAFAFA),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Add new task") },
                    maxLines = 1,
                    singleLine = true
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = {
                        onTaskAdded(myTask)
                        myTask = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Add task")
                }
            }
        }
    }
}