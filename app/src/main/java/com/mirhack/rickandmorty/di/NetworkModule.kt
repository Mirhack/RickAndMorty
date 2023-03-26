package com.mirhack.rickandmorty.di

import com.mirhack.rickandmorty.data.network.RepositoryImpl
import com.mirhack.rickandmorty.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    @Singleton
    fun bindRepository(repository: RepositoryImpl):Repository

}