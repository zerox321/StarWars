package com.star.wars.di

import com.star.core.repository.HomeRepo
import com.star.core.usecase.home.GetFilms
import com.star.core.usecase.home.GetPeople
import com.star.core.usecase.home.GetStarships
import com.star.core.usecase.home.GetVehicles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    //GetFilms
    @Provides
    @ViewModelScoped
    fun provideGetFilms(repo: HomeRepo): GetFilms = GetFilms(repo)

    //GetPeople
    @Provides
    @ViewModelScoped
    fun provideGetPeople(repo: HomeRepo): GetPeople = GetPeople(repo)

    //GetStarships
    @Provides
    @ViewModelScoped
    fun provideGetStarships(repo: HomeRepo): GetStarships = GetStarships(repo)

    //GetVehicles
    @Provides
    @ViewModelScoped
    fun provideGetVehicles(repo: HomeRepo): GetVehicles = GetVehicles(repo)

}
