package com.mirhack.rickandmorty.di

import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import com.mirhack.rickandmorty.data.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun room(@ApplicationContext context: Context): Database {
        return Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .build()
    }
}