package com.star.wars.di

import com.star.core.repository.HomeRepo
import com.star.core.usecase.home.*
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

    @Provides
    @ViewModelScoped
    fun provideSearchFilms(repo: HomeRepo): SearchFilms = SearchFilms(repo)

    //GetPeople
    @Provides
    @ViewModelScoped
    fun provideGetPeople(repo: HomeRepo): GetPeople = GetPeople(repo)

    //SearchPeople
    @Provides
    @ViewModelScoped
    fun provideSearchPeople(repo: HomeRepo): SearchPeople = SearchPeople(repo)

    //GetVehicles
    @Provides
    @ViewModelScoped
    fun provideGetVehicles(repo: HomeRepo): GetVehicles = GetVehicles(repo)

    //SearchVehicles
    @Provides
    @ViewModelScoped
    fun provideSearchVehicles(repo: HomeRepo): SearchVehicles = SearchVehicles(repo)

}
