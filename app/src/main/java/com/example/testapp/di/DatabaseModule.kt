package com.example.testapp.di

import android.content.Context
import androidx.room.Room
import com.example.testapp.data.localData.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDB(@ApplicationContext context: Context):Database{
        return Room.databaseBuilder(context,Database::class.java,"Database").build()
    }
}