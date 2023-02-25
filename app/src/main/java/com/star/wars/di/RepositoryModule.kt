package com.star.wars.di

import com.star.core.repository.HomeRepo
import com.star.wars.datasource.remote.service.ApiService
import com.star.wars.datasource.repository.HomeRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepo(service: ApiService): HomeRepo = HomeRepoImp(service = service)

}
