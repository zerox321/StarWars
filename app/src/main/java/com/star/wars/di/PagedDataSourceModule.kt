package com.star.wars.di

import com.star.core.usecase.home.GetFilms
import com.star.core.usecase.home.GetPeople
import com.star.core.usecase.home.GetStarships
import com.star.core.usecase.home.GetVehicles
import com.star.wars.datasource.remote.FilmsDataSource
import com.star.wars.datasource.remote.PeopleDataSource
import com.star.wars.datasource.remote.StarshipsDataSource
import com.star.wars.datasource.remote.VehiclesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PagedDataSourceModule {

    //FilmsDataSource
    @Provides
    @ViewModelScoped
    fun provideFilmsDataSource(getFilms: GetFilms): FilmsDataSource = FilmsDataSource(getFilms)

    //PeopleDataSource
    @Provides
    @ViewModelScoped
    fun providePeopleDataSource(getPeople: GetPeople): PeopleDataSource =
        PeopleDataSource(getPeople)

    //VehiclesDataSource
    @Provides
    @ViewModelScoped
    fun provideVehiclesDataSource(getVehicles: GetVehicles): VehiclesDataSource =
        VehiclesDataSource(getVehicles)

    //StarshipsDataSource
    @Provides
    @ViewModelScoped
    fun provideStarshipsDataSource(getStarships: GetStarships): StarshipsDataSource =
        StarshipsDataSource(getStarships)
}
