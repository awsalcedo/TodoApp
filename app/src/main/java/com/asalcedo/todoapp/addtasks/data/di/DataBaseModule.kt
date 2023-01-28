package com.asalcedo.todoapp.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.asalcedo.todoapp.addtasks.data.TaskDao
import com.asalcedo.todoapp.addtasks.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/****
 * Project: TodoApp
 * From: com.asalcedo.todoapp.addtasks.data.di
 * Created by Alex Salcedo Silva on 27/1/23 at 21:04
 * All rights reserve 2022.
 ***/
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext appContext: Context): TodoDatabase {
        return Room.databaseBuilder(appContext, TodoDatabase::class.java, "TaskDatabase").build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(todoDatabase: TodoDatabase): TaskDao {
        return todoDatabase.taskDao()
    }
}