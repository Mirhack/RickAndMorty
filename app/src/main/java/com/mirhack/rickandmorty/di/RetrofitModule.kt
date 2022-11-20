package com.mirhack.rickandmorty.di

import com.mirhack.rickandmorty.data.RickAndMortyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApiService(retrofit: Retrofit):
            RickAndMortyApiService = retrofit.create(RickAndMortyApiService::class.java)


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/"
    }

}